package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.MovimientosSociosDAO;
import py.edu.ucsa.aso.web.jdbc.dto.MovimientosSocios;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;
import py.edu.ucsa.aso.web.jdbc.dto.TiposMovimiento;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public class MovimientosSociosDAOImpl implements MovimientosSociosDAO {
	private final String QUERY_BASE = "SELECT ms.*,estado.codigo cod_estado, estado.descripcion estado_descripcion, "
			+ "medio_pago.descripcion medio_pago_descripcion, " + "conceptopago.descripcion concepto_descripcion "
			+ "FROM movimientos_socios ms " + "	INNER JOIN opciones estado ON ms.id_estado = estado.id "
			+ "	INNER JOIN opciones medio_pago ON ms.id_medio_pago = medio_pago.id "
			+ "	INNER JOIN opciones conceptopago ON ms.id_concepto = conceptopago.id ";

	@Override
	public List<MovimientosSocios> listar() {
		Connection c = ConexionBD.getConexion();
		List<MovimientosSocios> listamovimientos = new ArrayList<>();
		String select = QUERY_BASE;
		try {
			ResultSet rs = c.createStatement().executeQuery(select);
			MovimientosSocios ms = null;
			while (rs.next()) {
				ms = SetDatosMovimientosSocios(rs);
				listamovimientos.add(ms);
			}
			c.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("ERROR AL LISTAR: " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return listamovimientos;
	}

	private MovimientosSocios SetDatosMovimientosSocios(ResultSet rs) throws SQLException {
		MovimientosSocios ms;
		ms = new MovimientosSocios();
		ms.setId(rs.getInt("id"));
		ms.setFechaPago(rs.getTimestamp("fecha_pago").toLocalDateTime());
		ms.setMonto(rs.getInt("monto"));
		ms.setConcepto(
				new Opcion(rs.getInt("id_concepto"), rs.getString("cod_estado"), rs.getString("estado_descripcion")));
		ms.setEstado(new Opcion(rs.getInt("id_estado")));
		ms.setMedioPago(new Opcion(rs.getInt("id_medio_pago")));
		ms.setSocio(new Socio(rs.getInt("id_socio")));
		ms.setTipoMovimiento(new TiposMovimiento(rs.getInt("id_tipo_movimiento")));
		ms.setUsuarioAprobacion(new Usuario(rs.getInt("id_usuario_aprobacion")));
		ms.setUsuarioCreacion(new Usuario(rs.getInt("id_usuario_creacion")));
		return ms;
	}

	@Override
	public MovimientosSocios getById(int id) {
		Connection c = ConexionBD.getConexion();
		String select = QUERY_BASE + " where ms.id = ? ";
		MovimientosSocios ms = null;
		try {
			PreparedStatement ps = c.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ms = SetDatosMovimientosSocios(rs);
			}
			c.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("ERROR AL LISTAR POR BYID: " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return ms;

	}

	@Override
	public MovimientosSocios insertar(MovimientosSocios movimiento) {
		Connection c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
			String setenciaInsert = "insert into movimientos_socios (fecha_pago, monto, id_concepto, "
					+ "id_estado, id_medio_pago, id_socio, id_tipo_movimiento, "
					+ "id_usuario_aprobacion, id_usuario_aprobacion) " + "values(?,?,?,?,?,?,?,?,?)";
			ps = c.prepareStatement(setenciaInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, Timestamp.valueOf(movimiento.getFechaPago()));
			ps.setInt(2, movimiento.getMonto());
			ps.setInt(3, movimiento.getConcepto().getId());
			ps.setInt(4, movimiento.getEstado().getId());
			ps.setInt(5, movimiento.getMedioPago().getId());
			ps.setInt(6, movimiento.getSocio().getId());
			ps.setInt(7, movimiento.getTipoMovimiento().getId());
			ps.setInt(8, movimiento.getUsuarioAprobacion().getId());
			ps.setInt(9, movimiento.getUsuarioCreacion().getId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int clave = rs.getInt(1);
				movimiento.setId(clave);
				rs.close();
			}
			ps.close();
		} catch (Exception e) {
			System.out.println("ERROR AL INSERTAR: " + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}
		return movimiento;
	}

	@Override
	public MovimientosSocios modificar(MovimientosSocios objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub

	}

}
