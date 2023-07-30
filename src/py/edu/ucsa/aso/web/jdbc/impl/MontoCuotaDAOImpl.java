package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.MontoCuotaDAO;
import py.edu.ucsa.aso.web.jdbc.dto.MontoCuota;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;

public class MontoCuotaDAOImpl implements MontoCuotaDAO {

	@Override
	public List<MontoCuota> listar() {
		Connection c;
		List<MontoCuota> listMontoCuotas = new ArrayList<>();
		String select = "SELECT * FROM montocuota ";

		try {

			c = ConexionBD.getConexion();
			ResultSet rs = c.createStatement().executeQuery(select);
			MontoCuota m = null;
			while (rs.next()) {
				m = new MontoCuota();
				m.setId(rs.getInt("idmontocuota"));
				m.setMontoCuota(rs.getInt("monto"));
				m.setNumeroCuota(rs.getInt("numerocuota"));
				m.setFechaVencimiento(rs.getDate("fechavencimiento"));
				
				listMontoCuotas.add(m);

			}
			c.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Fallo al ejecutar la query " + e.getMessage());
		}
		return listMontoCuotas;
	}

	@Override
	public MontoCuota getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MontoCuota insertar(MontoCuota objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MontoCuota modificar(MontoCuota objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

}
