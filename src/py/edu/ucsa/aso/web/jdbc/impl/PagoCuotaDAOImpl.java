package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.PagoCuotaDAO;
import py.edu.ucsa.aso.web.jdbc.dto.MontoCuota;
import py.edu.ucsa.aso.web.jdbc.dto.MovimientosSocios;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;
import py.edu.ucsa.aso.web.jdbc.dto.PagosCuotaSocios;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;
import py.edu.ucsa.aso.web.jdbc.dto.TiposMovimiento;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public class PagoCuotaDAOImpl implements PagoCuotaDAO {
	
	private final String QUERY_BASE = "SELECT ps.*, e.codigo AS cod_estado, e.descripcion AS desc_estado, " +
		    "tp.codigo AS codigo_tipo, tp.descripcion AS descripcion_tipo, " +
		    "s.nombres AS nombres_socio, s.apellidos AS apellidos_socio  " +
		    "FROM pagos_cuotas_socios ps " +
		    "JOIN opciones e ON ps.id_estado = e.id " +
		    "JOIN movimientos_socios ms ON ps.id_movimiento_socio = ms.id " +
		    "JOIN tipos_movimiento tp ON ms.id_tipo_movimiento = tp.id " +
		    "JOIN socios s ON ps.id_socio = s.id ";



	@Override
	public List<PagosCuotaSocios> listar() {
		Connection c;
		List<PagosCuotaSocios> listPagosCuotas = new ArrayList<>();
		String select =  QUERY_BASE;
		c = ConexionBD.getConexion();
		try {
			
			ResultSet rs = c.createStatement().executeQuery(select);
			PagosCuotaSocios p = null;
			while (rs.next()) {
			    p = setValoresPagosCuota(rs); 
				listPagosCuotas.add(p);

			}
			c.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Fallo al ejecutar la query " + e.getMessage());
		}finally {
			ConexionBD.cerrarConexion(c);
		}
		return listPagosCuotas ;
		
		
	}

	private PagosCuotaSocios setValoresPagosCuota(ResultSet rs) throws SQLException {
		PagosCuotaSocios p;
		p = new PagosCuotaSocios();
		p.setId(rs.getInt("id"));
		p.setAnhoCuota(rs.getInt("anho_cuota"));  
		p.setExonerado(rs.getBoolean("exonerado"));
		p.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
		p.setMesCuota(rs.getInt("mes_cuota")); 
		p.setMontoCuota(rs.getInt("monto_cuota"));  
		p.setEstado(new Opcion(rs.getInt("id_estado"),rs.getString("cod_estado"),rs.getString("desc_estado"))); 
		if(rs.getInt("id_motivo_exoneracion")!=0) {
		p.setMotivoExoneracion(new Opcion(rs.getInt("id_motivo_exoneracion")));  
		}
		
		TiposMovimiento tipoMovimientoObjeto = new TiposMovimiento(rs.getInt("id")
				,rs.getString("codigo_tipo"),rs.getString("descripcion_tipo"));
		p.setMovimientoSocio(new MovimientosSocios(rs.getInt("id_movimiento_socio"),tipoMovimientoObjeto));  
		p.setSocio(new Socio(rs.getInt("id_socio"),rs.getString("nombres_socio"),rs.getString("apellidos_socio")));  
		p.setUsuarioCreacion(new Usuario(rs.getInt("id_usuario_creacion")));
		return p;
	}

	@Override
	public PagosCuotaSocios getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagosCuotaSocios insertar(PagosCuotaSocios pagoc) {
		Connection c;
		c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
			String sentenciaInsert = ("INSERT INTO pagos_cuotas_socios "+ "( anho_cuota,exonerado,fecha_creacion,"
					+ "mes_cuota,monto_cuota,id_estado,id_motivo_exoneracion,id_movimiento_socio,id_socio,id_usuario_creacion)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?)");
			ps = c.prepareStatement(sentenciaInsert,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, pagoc.getAnhoCuota());
		    ps.setBoolean(2, pagoc.isExonerado());
		    ps.setTimestamp(3, Timestamp.valueOf(pagoc.getFechaCreacion()));
		    ps.setInt(4, pagoc.getMesCuota());
		    ps.setInt(5, pagoc.getMontoCuota());
		    ps.setInt(6, pagoc.getEstado().getId()); 
		    ps.setInt(7, pagoc.getMotivoExoneracion().getId()); 
		    ps.setInt(8, pagoc.getMovimientoSocio().getId());  
		    ps.setInt(9, pagoc.getSocio().getId());  
		    ps.setInt(10,pagoc.getUsuarioCreacion().getId());
		    ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if ( rs.next()) {
				int clave = rs.getInt(1);
                  pagoc.setId(clave);
                  rs.close();
				}
					ps.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConexionBD.cerrarConexion(c);
		}
		return pagoc;
		
	}

	@Override
	public PagosCuotaSocios modificar(PagosCuotaSocios objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUltimoMesAnhoCuotaPagada(int idSocio) {
		String querySelect = "select *,concat(cast(round(mes_cuota) as integer), '-', cast(round(anho_cuota) as integer)) as mes_anho_cuota "
                + "from pagos_cuotas_socios "
                + "where id_socio = ? ;";

		Connection c;
		c = ConexionBD.getConexion();
		try {
			PreparedStatement ps = c.prepareStatement(querySelect);
			ps.setInt(1, idSocio);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			    return rs.getString("mes_anho_cuota");
			}
			c.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Fallo al ejecutar la query " + e.getMessage());
		}finally {
			ConexionBD.cerrarConexion(c);
		}
		
		return null;
	}

}
