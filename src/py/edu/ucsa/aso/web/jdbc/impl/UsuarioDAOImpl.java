package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.UsuarioDAO;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	private final String QUERY_BASE = "SELECT * "
			+ "FROM usuarios ";

	@Override
	public Usuario autenticar(String usuario, String clave) {
		Connection c;
		String select = QUERY_BASE + " where usuario= ? and clave= ? "
				+ "and habilitado=true and cuenta_bloqueada=false and cuenta_expirada=false";
		c = ConexionBD.getConexion();
		Usuario us = new Usuario();
		try {

			PreparedStatement ps = c.prepareStatement(select);
			ps.setString(1, usuario);
			ps.setString(2, clave);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				us.setUsuario(rs.getString("usuario"));
				us.setEmail(rs.getString("email"));
				

			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println("Fallo al ejecutar la query" + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return us;
	}

	@Override
	public Usuario getRolesByUsuario(int id) {
	
		return null;
	}

}
