package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.MontoCuotaDAO;
import py.edu.ucsa.aso.web.jdbc.dto.MontoCuota;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public class MontoCuotaDAOImpl implements MontoCuotaDAO {

	@Override
	public List<MontoCuota> listar() {
		Connection c;
		List<MontoCuota> listMontoCuotas = new ArrayList<>();
		String select = "SELECT * FROM montos_cuota ";
		c = ConexionBD.getConexion();
		try {
			
			ResultSet rs = c.createStatement().executeQuery(select);
			MontoCuota m = null;
			while (rs.next()) {
				m = setValoresMontoCuota(rs);
				listMontoCuotas.add(m);

			}
			c.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Fallo al ejecutar la query " + e.getMessage());
		}finally {
			ConexionBD.cerrarConexion(c);
		}
		return listMontoCuotas;
	}

	private MontoCuota setValoresMontoCuota(ResultSet rs) throws SQLException {
		MontoCuota m;
		m = new MontoCuota();
		m.setId(rs.getInt("id"));
		m.setMonto(rs.getDouble("monto"));
		m.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
		m.setFechaIniVigencia(rs.getTimestamp("fecha_inicio_vigencia").toLocalDateTime());
		m.setFechaFinVigencia(rs.getTimestamp("fecha_fin_vigencia").toLocalDateTime());
		m.setEstado(rs.getString("estado"));
		m.setFechaInactivacion(rs.getTimestamp("fecha_inactivacion").toLocalDateTime());
		m.setUsuarioInactivacion(new Usuario(rs.getInt("id_usuario_inactivacion")));
		return m;
	}

	@Override
	public MontoCuota getById(int id) {
		Connection c;
		String select = "SELECT * FROM montos_cuota where id = ? ";
		MontoCuota mt = null;
		c = ConexionBD.getConexion();
		try {

			PreparedStatement ps = c.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				mt = setValoresMontoCuota(rs);
				return mt;
			}

		} catch (Exception e) {
			System.out.println("Fallo al ejecutar la query" + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return null;

	}

	@Override
	public MontoCuota insertar(MontoCuota montoc) {
		Connection c;
		c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
			String sentenciaInsert = ("INSERT INTO montos_cuota "+ "( monto,fecha_creacion,fecha_inicio_vigencia,"
					+ "fecha_fin_vigencia,estado,fecha_inactivacion)"
					+ " VALUES(?,?,?,?,?,?)");
			ps = c.prepareStatement(sentenciaInsert,Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, montoc.getMonto());
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setTimestamp(3, Timestamp.valueOf(montoc.getFechaIniVigencia()));
			ps.setTimestamp(4, Timestamp.valueOf(montoc.getFechaFinVigencia()));
			ps.setString(5, montoc.getEstado());
			ps.setTimestamp(6, Timestamp.valueOf(montoc.getFechaInactivacion()));
			ps.executeUpdate(); 
			ResultSet rs=ps.getGeneratedKeys();
			if ( rs.next()) {
				int clave = rs.getInt(1);
                  montoc.setId(clave);
                  rs.close();
				}
					ps.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConexionBD.cerrarConexion(c);
		}
		return montoc;
	}

	@Override
	public MontoCuota modificar(MontoCuota mca) {
		Connection c;
		c = ConexionBD.getConexion();
		PreparedStatement ps;
		try {
	      String sentenciaUpdate=	("UPDATE montos_cuota set monto = ?, fecha_creacion = ?,"
	    + " fecha_inicio_vigencia = ?,fecha_fin_vigencia = ?, estado = ?, fecha_inactivacion = ? "
		+ " WHERE id = ?");
			ps=c.prepareStatement(sentenciaUpdate);
			ps.setDouble(1, mca.getMonto());
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setTimestamp(3, Timestamp.valueOf(mca.getFechaIniVigencia()));
			ps.setTimestamp(4, Timestamp.valueOf(mca.getFechaFinVigencia()));
			ps.setString(5, mca.getEstado());
			ps.setTimestamp(6, Timestamp.valueOf(mca.getFechaInactivacion()));
			ps.setInt(7, mca.getId());
			ps.executeUpdate(); 
			ps.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConexionBD.cerrarConexion(c);
		}

		return mca;
	}

	@Override
	public void eliminar(int id) {
		Connection c;
		c = ConexionBD.getConexion();
		try {
			PreparedStatement ps = c.prepareStatement(" DELETE  FROM montos_cuota  WHERE id = ?");
			ps.setInt(1, id);
		    int cant= ps.executeUpdate(); 
			System.out.println("REGISTRO ELIMINADO: " + cant);
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConexionBD.cerrarConexion(c);
		}

	}

	@Override
	public MontoCuota getMontoCuotaByMesAnho(int mes, int anho) {
		
		Connection c;
		String select = "SELECT * FROM montos_cuota where"
				+ " extract (month from fecha_creacion) = ? and  extract (year from fecha_creacion) = ?  ";
		MontoCuota mt = null;
		c = ConexionBD.getConexion();
		try {
			PreparedStatement ps = c.prepareStatement(select);
			ps.setInt(1, mes);
			ps.setInt(2, anho);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				mt = new MontoCuota();
				mt.setId(rs.getInt("id"));
				mt.setMonto(rs.getDouble("monto"));
				mt.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
				mt.setFechaIniVigencia(rs.getTimestamp("fecha_inicio_vigencia").toLocalDateTime());
				mt.setFechaFinVigencia(rs.getTimestamp("fecha_fin_vigencia").toLocalDateTime());
				mt.setEstado(rs.getString("estado"));
				mt.setFechaInactivacion(rs.getTimestamp("fecha_inactivacion").toLocalDateTime());
				mt.setUsuarioInactivacion(new Usuario(rs.getInt("id_usuario_inactivacion")));
				return mt;
			}

		} catch (Exception e) {
			System.out.println("Fallo al ejecutar la query" + e.getMessage());
		} finally {
			ConexionBD.cerrarConexion(c);
		}

		return null;

	}

	
}
