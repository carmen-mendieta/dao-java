package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.OpcionDAO;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;
import py.edu.ucsa.aso.web.jdbc.dto.Dominio;

public class OpcionDAOJdbcImpl implements OpcionDAO {
	private final String QueryBase = "select o.*, d.codigo cod_dominio, d.descripcion desc_dominio" + "from opciones o "
			+ "join dominios d on o.id_dominio=d.id ";

	@Override
	public List<Opcion> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Opcion getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Opcion insertar(Opcion objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Opcion modificar(Opcion objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Opcion getOpcionByDominioCodOpcion(String dominio, String codOpcion) {
		// TODO Auto-generated method stub
		return null;
	}

	private Opcion setDatosOpcionFromDB(ResultSet rs) throws SQLException {
		Opcion opcion = new Opcion();
		opcion.setId(rs.getInt("id"));
		opcion.setCodigo(rs.getString("codigo"));
		opcion.setDescripcion(rs.getString("descripcion"));
		opcion.setEstado(rs.getString("estado"));
		Dominio dominio = new Dominio();
		dominio.setId(rs.getInt("id_dominio"));
		dominio.setCodigo(rs.getString("cod_dominio"));
		dominio.setDescripcion(rs.getString("desc_dominio"));
		opcion.setDominio(dominio);
		if (Objects.nonNull(rs.getInt("id_opcion_padre"))) {
			Opcion opcPadre = new Opcion();
			opcPadre.setId(rs.getInt("d_opcion_padre"));
			opcPadre.setCodigo(rs.getString("cod_opcion_padre"));
			opcPadre.setDescripcion(rs.getString("desc_opcion_padre"));
			opcion.setOpcionPadre(opcPadre);

		}

		return opcion;
	}

	@Override
	public List<Opcion> getOpcionesByCodDominio(String dominio) {
	  List<Opcion> opciones= new ArrayList<>();
	  Connection c=ConexionBD.getConexion();
	  PreparedStatement s;
	  try {
		  String selectStmt=QueryBase +"";
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	  
		
		
		return null;
	}

}
