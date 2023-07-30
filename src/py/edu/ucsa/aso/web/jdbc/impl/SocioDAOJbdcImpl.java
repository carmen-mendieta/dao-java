package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.SocioDAO;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;

public class SocioDAOJbdcImpl implements SocioDAO {

	@Override
	public List<Socio> listar() {
		Connection c;
		List<Socio> listaSocios = new ArrayList<>();
		String select = "SELECT * FROM socio ";

		try {

			c = ConexionBD.getConexion();
			ResultSet rs = c.createStatement().executeQuery(select);
			Socio s = null;
			while (rs.next()) {
				s = new Socio();
				s.setNrosocio(rs.getInt("NumeroSocio"));
				s.setNombre(rs.getString("Nombre"));
				s.setApellido(rs.getString("Apellido"));
				s.setCedula(rs.getString("Cedula"));
				s.setCelular(rs.getString("Celular"));
				listaSocios.add(s);

			}
			c.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Fallo al ejecutar la query " + e.getMessage());
		}
		return listaSocios;
	}

	@Override
	public Socio getById(int id) {
		Connection c;
		String select = "SELECT * FROM socio where NumeroSocio = ? ";
		Socio s = null;
		try {
			c = ConexionBD.getConexion();
			PreparedStatement ps = c.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Socio();
				s.setNrosocio(rs.getInt("NumeroSocio"));
				s.setNombre(rs.getString("Nombre"));
				s.setApellido(rs.getString("Apellido"));
				s.setCedula(rs.getString("Cedula"));
				s.setCelular(rs.getString("Celular"));
			}

		} catch (Exception e) {
			System.out.println("Fallo al ejecutar la query" + e.getMessage());
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
			ps.setInt(1, socio.getNrosocio());
			ps.setString(2, socio.getNombre());
			ps.setString(3, socio.getApellido());
			ps.setString(4, socio.getCedula());
			ps.setString(5, socio.getCelular());
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
			ps.setString(1, socio.getNombre());
			ps.setString(2, socio.getApellido());
			ps.setString(3, socio.getCedula());
			ps.setString(4, socio.getCelular());
			ps.setInt(5, socio.getNrosocio());
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
				s.setNrosocio(rs.getInt("NumeroSocio"));
				s.setNombre(rs.getString("Nombre"));
				s.setApellido(rs.getString("Apellido"));
				s.setCedula(rs.getString("Cedula"));
				s.setCelular(rs.getString("Celular"));
			}

		} catch (Exception e) {
			System.out.println("Fallo al ejecutar la query" + e.getMessage());
		}

		return s;
	}

}
