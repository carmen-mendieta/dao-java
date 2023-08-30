package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private final String QUERY_BASE="SELECT ms.*,estado.codigo cod_estado, estado.descripcion estado_descripcion, "
			+ "medio_pago.descripcion medio_pago_descripcion, "
			+ "conceptopago.descripcion concepto_descripcion "
			+ "FROM movimientos_socios ms "
			+ "	INNER JOIN opciones estado ON ms.id_estado = estado.id "
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
		ms.setConcepto(new Opcion(rs.getInt("id_concepto"),rs.getString("cod_estado"),rs.getString("estado_descripcion")));
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
		
		return null;
	}

	@Override
	public MovimientosSocios insertar(MovimientosSocios objeto) {
		// TODO Auto-generated method stub
		return null;
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
