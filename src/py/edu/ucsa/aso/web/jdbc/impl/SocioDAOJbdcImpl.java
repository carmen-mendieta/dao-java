package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.SocioDAO;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public class SocioDAOJbdcImpl implements SocioDAO {

	@Override
	public List<Socio> listar() {
		Connection c;
		List<Socio> listaSocios = new ArrayList<>();
		String select = "SELECT * FROM socios order by nombres asc ";
		c = ConexionBD.getConexion();
		try {

			ResultSet rs = c.createStatement().executeQuery(select);
			Socio socio = null;
			while (rs.next()) {
				socio = SetDatosSocios(rs);
				listaSocios.add(socio);

			}
			c.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Fallo al ejecutar la query " + e.getMessage());
		}finally {
			ConexionBD.cerrarConexion(c);
		}
		return listaSocios;
	}

	private Socio SetDatosSocios(ResultSet rs) throws SQLException {
		Socio socio;
		socio = new Socio();
		socio.setId(rs.getInt("id"));
		socio.setNombres(rs.getString("nombres"));
		socio.setApellidos(rs.getString("apellidos"));
		socio.setEmail(rs.getString("email"));
		socio.setNroSocio(rs.getInt("nro_socio"));
		socio.setNroCedula(rs.getInt("nro_cedula"));
		socio.setFechaIngreso(rs.getTimestamp("fecha_ingreso").toLocalDateTime());
		socio.setEstadoActual(new Opcion (rs.getInt("id_estado_actual")));
		socio.setFechaEstadoActual(rs.getTimestamp("fecha_estado_actual").toLocalDateTime());
		socio.setFundador(rs.getBoolean("fundador"));
		socio.setUsuarioCreacion(new Usuario(rs.getInt("id_usuario_creacion")));
		socio.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
		socio.setSocioPoponente(new Socio(rs.getInt("id_socio_proponente")));
		socio.setTipoSocio(new Opcion (rs.getInt("id_tipo_socio")));
		return socio;
	}

	@Override
	public Socio getById(int id) {
		Connection c;
		String select = "SELECT * FROM socios where id = ? ";
		Socio s = null;
		c = ConexionBD.getConexion();
		try {
			
			PreparedStatement ps = c.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = SetDatosSocios(rs);
			   
			}

		} catch (Exception e) {
			System.out.println("Fallo al ejecutar la query" + e.getMessage());
		}finally {
			ConexionBD.cerrarConexion(c);
		}

		return s;
	}

	@Override
	public Socio insertar(Socio socio) {
		Connection c;
		try {
			c = ConexionBD.getConexion();
			PreparedStatement ps = c.prepareStatement(
					"INSERT INTO Socio " + "(NumeroSocio, Nombre, Apellido, Cedula, Celular)" + " VALUES(?,?,?,?,?)");
			/*
			 * ps.setInt(1, socio.getNrosocio()); ps.setString(2, socio.getNombre());
			 * ps.setString(3, socio.getApellido()); ps.setString(4, socio.getCedula());
			 * ps.setString(5, socio.getCelular());
			 */
			int cant = ps.executeUpdate(); // devuelve la cantidad de registros afectados, cuando es insert siempre es 1
			// ParameterMetaData parameterMetaData = ps.getParameterMetaData();
			System.out.println("REGISTROS INSERTADOS: " + cant);
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Socio modificar(Socio socio) {
		Connection c;
		try {
			c = ConexionBD.getConexion();
			PreparedStatement ps = c.prepareStatement(
					"UPDATE socio set nombre = ?, apellido = ?, cedula = ?, celular = ?  WHERE numerosocio = ?");
			/*
			 * ps.setString(1, socio.getNombre()); ps.setString(2, socio.getApellido());
			 * ps.setString(3, socio.getCedula()); ps.setString(4, socio.getCelular());
			 * ps.setInt(5, socio.getNrosocio());
			 */
			int cant = ps.executeUpdate(); // devuelve la cantidad de registros afectados, cuando es insert siempre es 1
			// ParameterMetaData parameterMetaData = ps.getParameterMetaData();
			System.out.println("REGISTROS ACTUALIZADOS: " + cant);
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void eliminar(int id) {

		Connection c;
		try {
			c = ConexionBD.getConexion();
			PreparedStatement ps = c.prepareStatement(" DELETE  FROM socio  WHERE numerosocio = ?");
			ps.setInt(1, id);
			int cant = ps.executeUpdate(); // devuelve la cantidad de registros afectados, cuando es insert siempre es 1
			// ParameterMetaData parameterMetaData = ps.getParameterMetaData();
			System.out.println("REGISTRO ELIMINADO: " + cant);
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Socio getSocioByNroCedula(String nroCedula) {
		Connection c;
		String select = "SELECT * FROM socio where Cedula = ? ";
		Socio s = null;
		try {
			c = ConexionBD.getConexion();
			PreparedStatement ps = c.prepareStatement(select);
			ps.setString(1, nroCedula);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Socio();
				/*
				 * s.setNrosocio(rs.getInt("NumeroSocio")); s.setNombre(rs.getString("Nombre"));
				 * s.setApellido(rs.getString("Apellido")); s.setCedula(rs.getString("Cedula"));
				 * s.setCelular(rs.getString("Celular"));
				 */
			}

		} catch (Exception e) {
			System.out.println("Fallo al ejecutar la query" + e.getMessage());
		}

		return s;
	}

}
