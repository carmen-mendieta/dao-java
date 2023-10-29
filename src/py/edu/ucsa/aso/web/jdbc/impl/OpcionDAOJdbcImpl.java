package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Statement;
import java.sql.Types;
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
	private final String QueryBase = "select o.*, d.codigo cod_dominio, d.descripcion desc_dominio, "
			+ "padre.codigo cod_opcion_padre, padre.descripcion desc_opcion_padre "
			+ "from opciones o "
			+ "join dominios d on o.id_dominio = d.id "
			+ "left outer join opciones padre on o.id_opcion_padre = padre.id ";

	@Override
	public List<Opcion> listar() {
		List<Opcion> opciones = new ArrayList<>();
		Connection c = ConexionBD.getConexion();
		Statement s;
		try {
			String selectStmt = QueryBase;
			s = c.createStatement();
			ResultSet rs = s.executeQuery(selectStmt);
			while (rs.next()) {
				opciones.add(setDatosOpcionFromDB(rs));
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al generar la lista " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return opciones;
	}

	@Override
	public Opcion getById(int id) {
		Opcion opcion = null;
		Connection c = ConexionBD.getConexion();
		PreparedStatement s;
		try {
			String selectStmt = QueryBase + "where  o.id = ? ";
			s = c.prepareStatement(selectStmt);
			s.setInt(1, id);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				opcion = setDatosOpcionFromDB(rs);
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al buscar por ById " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return opcion;
	}

	@Override
	public Opcion insertar(Opcion opcion) {
		Connection c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
			String setenciaInsert = "insert into opciones (codigo, descripcion, estado, "
					+ "id_dominio, id_opcion_padre) values(?,?,?,?,?)";
			ps = c.prepareStatement(setenciaInsert, Statement.RETURN_GENERATED_KEYS);
			SetValoresParaGuardar(opcion, ps);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int clave = rs.getInt(1);
				opcion.setId(clave);
				rs.close();
			}
			ps.close();
		} catch (Exception e) {
			System.out.println("ERROR AL INSERTAR: " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}
		return opcion;
	}

	@Override
	public Opcion modificar(Opcion opcion) {
		Connection c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
			String sentenciaUpdate = "update "
											+ "opciones set codigo = ?, "
											+ "descripcion = ?, "
											+ "estado = ?, "
											+ "id_dominio = ?, "
											+ "id_opcion_padre = ? "
										+ "where id= ?";
			ps = c.prepareStatement(sentenciaUpdate);
			SetValoresParaGuardar(opcion, ps);
			ps.setInt(6, opcion.getId());
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			System.out.println("ERROR AL MODIFICAR: " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}
		return opcion;
	}

	private void SetValoresParaGuardar(Opcion opcion, PreparedStatement ps) throws SQLException {
		ps.setString(1, opcion.getCodigo());
		ps.setString(2, opcion.getDescripcion());
		ps.setString(3, opcion.getEstado());
		ps.setInt(4, opcion.getDominio().getId());
		if (Objects.nonNull(opcion.getOpcionPadre()))
			ps.setInt(5, opcion.getOpcionPadre().getId());
		else
			ps.setNull(5, Types.INTEGER);
	}

	@Override
	public void eliminar(int id) {
		Connection c;
		c = ConexionBD.getConexion();
		try {
			PreparedStatement ps = c.prepareStatement(" DELETE  FROM opciones  WHERE id = ?");
			ps.setInt(1, id);
			int cant = ps.executeUpdate();
			System.out.println("REGISTRO ELIMINADO: " + cant);
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexionBD.cerrarConexion(c);
		}
	}

	@Override
	public Opcion getOpcionByDominioCodOpcion(String codDominio, String codOpcion) {
		Opcion opcion = null;
		Connection c = ConexionBD.getConexion();
		PreparedStatement s;
		try {
			String selectStmt = QueryBase + "where d.codigo= ? and o.codigo= ? ";
			s = c.prepareStatement(selectStmt);
			s.setString(1, codDominio);
			s.setString(2, codOpcion);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				opcion = setDatosOpcionFromDB(rs);
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al generar la lista " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return opcion;
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
			opcPadre.setId(rs.getInt("id_opcion_padre"));
			opcPadre.setCodigo(rs.getString("cod_opcion_padre"));
			opcPadre.setDescripcion(rs.getString("desc_opcion_padre"));
			opcion.setOpcionPadre(opcPadre);

		}

		return opcion;
	}

	@Override
	public List<Opcion> getOpcionesByCodDominio(String codDominio) {
		List<Opcion> opciones = new ArrayList<>();
		Connection c = ConexionBD.getConexion();
		PreparedStatement s;
		try {
			String selectStmt = QueryBase + "where d.codigo= ? order by descripcion asc";
			s = c.prepareStatement(selectStmt);
			s.setString(1, codDominio);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				opciones.add(setDatosOpcionFromDB(rs));
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al generar la lista " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return opciones;
	}

	@Override
	public List<Opcion> getOpcionesByCodOpciones(List<String> opcionesParams) {
		
		
		StringBuilder opcionesBuilder = new StringBuilder();
		 for (String elemento : opcionesParams) {
		        opcionesBuilder.append("'").append(elemento).append("',");
		    }

		    if (opcionesBuilder.length() > 0) {
		        opcionesBuilder.deleteCharAt(opcionesBuilder.length() - 1);
		    }
		List<Opcion> opciones = new ArrayList<>();
		Connection c = ConexionBD.getConexion();
		PreparedStatement s;
		try {
			String selectStmt = QueryBase + " where o.codigo in (" + opcionesBuilder.toString() + ") order by descripcion asc";
			s = c.prepareStatement(selectStmt);
		  //  s.setString(1, opcionesBuilder.toString());
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				opciones.add(setDatosOpcionFromDB(rs));
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			System.out.println("Error al generar la lista " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return opciones;
	}

}
