package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.TiposMovimientoDAO;
import py.edu.ucsa.aso.web.jdbc.dto.TiposMovimiento;

public class TiposMovimientoDAOImpl implements TiposMovimientoDAO {

	@Override
	public List<TiposMovimiento> listar() {
		Connection c = ConexionBD.getConexion();
		List<TiposMovimiento> listaMovimientos = new ArrayList<>();
		String select = "SELECT  * FROM tipos_movimiento;";
		try {

			ResultSet rs = c.createStatement().executeQuery(select);
			while (rs.next()) {
				TiposMovimiento tipo = SetTiposMovimiento(rs);
				listaMovimientos.add(tipo);
			}
			rs.close();
			c.close();

		} catch (SQLException e) {
			System.out.println("fallo al listar los tipos de movimientos " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return listaMovimientos;
	}

	private TiposMovimiento SetTiposMovimiento(ResultSet rs) throws SQLException {
		TiposMovimiento tipo = new TiposMovimiento();
		tipo.setId(rs.getInt("id"));
		tipo.setCodigo(rs.getString("codigo"));
		tipo.setDescripcion(rs.getString("descripcion"));
		tipo.setEstado(rs.getString("estado"));
		tipo.setTipoDebCred(rs.getString("tipo_deb_cred"));
		return tipo;
	}

	@Override
	public TiposMovimiento getById(int id) {
		Connection c = ConexionBD.getConexion();
		String select = "SELECT *  FROM tipos_movimiento where id=?;";
		TiposMovimiento tipom = null;
		try {
			PreparedStatement ps = c.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tipom = SetTiposMovimiento(rs);
			}
			rs.close();
			c.close();
		} catch (Exception e) {
			System.out.println("Fallo al ejecutar la query " + e.getMessage());

		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return tipom;
	}

	@Override
	public TiposMovimiento insertar(TiposMovimiento tiposmovimiento) {
		Connection c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
			String setenciaInsert = "INSERT INTO tipos_movimiento(" + " codigo, descripcion, estado, tipo_deb_cred)"
					+ "	VALUES (?, ?, ?, ?); ";
			ps = c.prepareStatement(setenciaInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tiposmovimiento.getCodigo());
			ps.setString(2, tiposmovimiento.getDescripcion());
			ps.setString(3, tiposmovimiento.getEstado());
			ps.setString(4, tiposmovimiento.getTipoDebCred());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int clave = rs.getInt(1);
				tiposmovimiento.setId(clave);
				rs.close();
			}
			ps.close();
		} catch (Exception e) {
			System.out.println("ERROR AL INSERTAR: " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return tiposmovimiento;
	}

	@Override
	public TiposMovimiento modificar(TiposMovimiento tipomov) {
		Connection c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
			String setenciaUpdate = "UPDATE tipos_movimiento"
					+ "	SET codigo=?, descripcion=?, estado=?, tipo_deb_cred=?" + "	WHERE id=?;";
			ps = c.prepareStatement(setenciaUpdate);
			ps.setString(1, tipomov.getCodigo());
			ps.setString(2, tipomov.getDescripcion());
			ps.setString(3, tipomov.getEstado());
			ps.setString(4, tipomov.getTipoDebCred());
			ps.setInt(5, tipomov.getId());
			int cant = ps.executeUpdate();
			System.out.println("Registro modificado: " + cant);
			ps.close();
		} catch (Exception e) {
			System.out.println("ERROR AL MODIFICAR: " + e.getMessage());
		}
		return tipomov;
	}

	@Override
	public void eliminar(int id) {
		Connection c = ConexionBD.getConexion();
		try {
			PreparedStatement ps = c.prepareStatement("DELETE FROM tipos_movimiento Where id=?");
			ps.setInt(1, id);
			int cant = ps.executeUpdate();
			System.out.println("REGISTRO ELIMINADO: " + cant);
			ps.close();
			c.close();

		} catch (Exception e) {
			System.out.println("NO SE ELIMINO REGISTRO: " + e.getMessage());

		} finally {
			ConexionBD.cerrarConexion(c);
		}
	}

}
