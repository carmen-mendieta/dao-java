package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.DominioDAO;
import py.edu.ucsa.aso.web.jdbc.dto.Dominio;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;

public class DominioDAOImpl implements DominioDAO {

	@Override
	public List<Dominio> listar() {
		
		List<Dominio> dominios = new ArrayList<>();
		Connection c = ConexionBD.getConexion();
		Statement s;
		
		try {
			String selectStmt = "select * from dominios";
			s = c.createStatement();
			ResultSet rs = s.executeQuery(selectStmt);
			while (rs.next()) {
				Dominio dominio= new Dominio();
				dominio.setId(rs.getInt("id"));
				dominio.setCodigo(rs.getString("codigo"));
				dominio.setDescripcion(rs.getString("descripcion"));
				dominios.add(dominio);
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al generar la lista " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return dominios;
	}

	@Override
	public Dominio getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dominio insertar(Dominio objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dominio modificar(Dominio objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

}
