package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.UsuarioDAO;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;
import py.edu.ucsa.aso.web.jdbc.dto.Rol;
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
	public List<Rol> getRolesByUsuario(int idUsuario) {
		List<Rol> roles = new ArrayList<>();
	 	Connection c = ConexionBD.getConexion();
		PreparedStatement s;
		try {
		  String selectStmt=" select r.*, r.nombre_rol as nombre_rol, ru.id_usuario as usuario_id "
			     		+ "from roles r inner join roles_usuarios ru on ru.id_rol= r.id "
			     		+ " where id_usuario= ? ";
			s = c.prepareStatement(selectStmt);
			s.setInt(1, idUsuario);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
			      Rol rol= new Rol();
			      rol.setId(rs.getInt("id"));
			      rol.setNombreRol(rs.getString("nombre_rol"));
			      roles.add(rol);
			    }
			       rs.close();
			       s.close();
		     } catch (Exception e) {
			System.out.println("Error al generar la lista " + e.getMessage());
		      } finally {
			ConexionBD.cerrarConexion(c);
		   }

		   return roles;
	}
   
	}

	


