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
			String sentenciaInsert = ("INSERT INTO montos_cuota "+ "( monto,fecha_creacion,fecha_inicio_vigencia,fecha_fin_vigencia,estado,fecha_inactivacion)"
					+ " VALUES(?,?,?,?,?,?)");
			ps = c.prepareStatement(sentenciaInsert);
			ps.setDouble(1, montoc.getMonto());
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setTimestamp(3, Timestamp.valueOf(montoc.getFechaIniVigencia()));
			ps.setTimestamp(4, Timestamp.valueOf(montoc.getFechaFinVigencia()));
			ps.setString(5, montoc.getEstado());
			ps.setTimestamp(6, Timestamp.valueOf(montoc.getFechaInactivacion()));
			ps.executeUpdate(sentenciaInsert,Statement.RETURN_GENERATED_KEYS); 
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
		try {
			c = ConexionBD.getConexion();
			PreparedStatement ps = c.prepareStatement(
					"UPDATE montocuota set monto = ?, numerocuota = ?,fechavencimiento= ?  WHERE idmontocuota = ?");
			// ps.setInt(1, mca.getMontoCuota());
			ps.setInt(2, mca.getNumeroCuota());
			ps.setDate(3, mca.getFechaVencimiento());
			ps.setInt(4, mca.getId());
			int cant = ps.executeUpdate(); // devuelve la cantidad de registros afectados, cuando es insert siempre es 1
			// ParameterMetaData parameterMetaData = ps.getParameterMetaData();
			System.out.println("REGISTROS ACTUALIZADOS: " + cant);
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void eliminar(int id) {
		Connection c;
		try {
			c = ConexionBD.getConexion();
			PreparedStatement ps = c.prepareStatement(" DELETE  FROM montocuota  WHERE idmontocuota = ?");
			ps.setInt(1, id);
			int cant = ps.executeUpdate(); // devuelve la cantidad de registros afectados, cuando es insert siempre es 1
			// ParameterMetaData parameterMetaData = ps.getParameterMetaData();
			System.out.println("REGISTRO ELIMINADO: " + cant);
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public MontoCuota getMontoCuotaByMesAnho(int mes, int anho) {
		// TODO Auto-generated method stub
		return null;
	}

}
