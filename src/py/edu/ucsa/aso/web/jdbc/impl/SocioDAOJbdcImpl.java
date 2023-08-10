package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.SocioDAO;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public class SocioDAOJbdcImpl implements SocioDAO {
	private final String QUERY_BASE=" select s.*, e.codigo cod_estado, e.descripcion desc_estado,"
			+ "			ts.codigo cod_tipo_socio, ts.descripcion desc_tipo_socio"
			+ "			from socios s join opciones e on s.id_estado_actual = e.id "
			+ "			join opciones ts on s.id_tipo_socio= ts. id  ";

	@Override
	public List<Socio> listar() {
		Connection c;
		List<Socio> listaSocios = new ArrayList<>();
		String select =  QUERY_BASE  + " order by nombres asc ";
		c = ConexionBD.getConexion();
		try {

			ResultSet rs = c.createStatement().executeQuery(select);
			while (rs.next()) {
				listaSocios.add(SetDatosSocios(rs));

			}
			c.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Fallo al ejecutar la query " + e.getMessage());
		} finally {
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
		
		Opcion estado= new Opcion();
		estado.setId(rs.getInt("id_estado_actual"));
		estado.setCodigo(rs.getString("cod_estado"));
		estado.setDescripcion(rs.getString("desc_estado"));
		socio.setEstadoActual(estado);
		
		socio.setFechaEstadoActual(rs.getTimestamp("fecha_estado_actual").toLocalDateTime());
		socio.setFundador(rs.getBoolean("fundador"));
		socio.setUsuarioCreacion(new Usuario(rs.getInt("id_usuario_creacion")));
		socio.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
		socio.setSocioPoponente(new Socio(rs.getInt("id_socio_proponente")));
		socio.setTipoSocio(new Opcion(rs.getInt("id_tipo_socio"),
				rs.getString("cod_tipo_socio"),
			    rs.getString("desc_tipo_socio")));
		return socio;
	}

	@Override
	public Socio getById(int id) {
		Connection c;
		String select =  QUERY_BASE + " where s.id = ? ";
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
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return s;
	}

	@Override
	public Socio insertar(Socio socio) {
		Connection c;
		c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
			c.setAutoCommit(false);
			String sentenciaInsert = "INSERT INTO socios "
					+ "(nombres, apellidos, email, nro_socio, nro_cedula, fecha_ingreso, "
					+ "id_estado_actual, fecha_estado_actual, fundador, id_usuario_creacion, "
					+ "fecha_creacion, id_socio_proponente, id_tipo_socio) " + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = c.prepareStatement(sentenciaInsert,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, socio.getNombres());
			ps.setString(2, socio.getApellidos());
			ps.setString(3, socio.getEmail());
			ps.setInt(4, socio.getNroSocio());
			ps.setInt(5, socio.getNroCedula());
			ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			ps.setInt(7, socio.getEstadoActual().getId());
			ps.setTimestamp(8, Timestamp.valueOf(socio.getFechaEstadoActual()));
			ps.setBoolean(9, socio.isFundador());
			ps.setInt(10, socio.getUsuarioCreacion().getId());
			ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
			if(Objects.nonNull(socio.getSocioPoponente()))
				ps.setInt(12, socio.getSocioPoponente().getId());
			else
				ps.setNull(12, Types.INTEGER);
			
			ps.setInt(13, socio.getTipoSocio().getId());
			ps.executeUpdate(); 
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int clave = rs.getInt(1);
				socio.setId(clave);
				rs.close();
			}
			ps.close();
			String subSelectEstado = "(SELECT e.id "
					+ "FROM opciones e "
					+ "JOIN dominios d "
						+ "ON e.id_dominio = d.id "
					+ "WHERE d.codigo = 'ESTSOC' "
						+ "AND e.codigo = 'PEN')";
			sentenciaInsert = "INSERT INTO estados_socios("
					+ "	id_socio ,id_estado, fecha_estado, "
					+ " id_usuario_creacion, fecha_creacion, observacion) "
					+ "	VALUES (?," +subSelectEstado+ ", ?, ?, ?, ?)";
			ps = c.prepareStatement(sentenciaInsert);
			ps.setInt(1, socio.getId());
			//ps.setTimestamp(2, Timestamp.valueOf(socio.getFechaEstadoActual()));
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setInt(3, socio.getUsuarioCreacion().getId());
			ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(5, "SE DA DE ALTTA COMO SOCIO ");
			ps.executeUpdate();	
			ps.close();
			c.commit();

		} catch (SQLException e) {
		     System.out.println(e.getMessage());
			try {
				c.rollback();
			} catch (SQLException e1) {
			   System.out.println("ERROR AL HACER ROLLBACK: "+e1.getMessage());
				e1.printStackTrace();
			}
		} finally {
			ConexionBD.cerrarConexion(c);
		}
		return socio;
	}

	@Override
	public Socio modificar(Socio socio) {
		Connection c;
		c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
			String sentenciaUpdate = ("UPDATE socios set nombres = ?, apellidos = ?, email = ?, nro_socio = ?"
					+ ", nro_cedula= ?, fecha_ingreso = ?, id_estado_actual = ?, fecha_estado_actual = ?, fundador = ?,"
					+ "id_usuario_creacion = ?, fecha_creacion = ?, id_socio_proponente = ?, "
					+ " id_tipo_socio = ?  WHERE id = ?");
			ps = c.prepareStatement(sentenciaUpdate);
			ps.setString(1, socio.getNombres());
			ps.setString(2, socio.getApellidos());
			ps.setString(3, socio.getEmail());
			ps.setInt(4, socio.getNroSocio());
			ps.setInt(5, socio.getNroCedula());
			ps.setTimestamp(6, Timestamp.valueOf(socio.getFechaIngreso()));
			ps.setInt(7,socio.getEstadoActual().getId());
			ps.setTimestamp(8, Timestamp.valueOf(socio.getFechaEstadoActual()));
			ps.setBoolean(9, socio.isFundador());
			ps.setInt(10, socio.getUsuarioCreacion().getId());
			ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
			ps.setInt(12, socio.getSocioPoponente().getId());
			ps.setInt(13, socio.getTipoSocio().getId());
			ps.setInt(14, socio.getId());
			ps.executeUpdate(); 
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConexionBD.cerrarConexion(c);
		}
		return socio;
	}

	@Override
	public void eliminar(int id) {

		Connection c;
		c = ConexionBD.getConexion();
		try {
			PreparedStatement ps = c.prepareStatement(" DELETE  FROM socios  WHERE id = ?");
			ps.setInt(1, id);
			int cant = ps.executeUpdate(); 
			System.out.println("REGISTRO ELIMINADO: " + cant);
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConexionBD.cerrarConexion(c);
		}

	}

	@Override
	public Socio getSocioByNroCedula(int nroCedula) {
		Connection c;
		c = ConexionBD.getConexion();
		String select =  QUERY_BASE   + " where nro_cedula = ? ";
		Socio s = null;
		try {
			PreparedStatement ps = c.prepareStatement(select);
			ps.setInt(1, nroCedula);
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

}
