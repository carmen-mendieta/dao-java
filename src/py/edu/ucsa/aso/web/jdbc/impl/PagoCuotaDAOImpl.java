package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import py.edu.aso.web.conexion.ConexionBD;
import py.edu.ucsa.aso.web.jdbc.dao.PagoCuotaDAO;
import py.edu.ucsa.aso.web.jdbc.dto.MontoCuota;
import py.edu.ucsa.aso.web.jdbc.dto.PagosCuotaSocios;

public class PagoCuotaDAOImpl implements PagoCuotaDAO {

	@Override
	public List<PagosCuotaSocios> listar() {
		Connection c;
		List<PagosCuotaSocios> listPagosCuotas = new ArrayList<>();
		String select = "SELECT * FROM pagos_cuotas_socios ";
		c = ConexionBD.getConexion();
		try {
			
			ResultSet rs = c.createStatement().executeQuery(select);
			PagosCuotaSocios m = null;
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
		return listPagosCuotas ;
		
		
	}

	@Override
	public PagosCuotaSocios getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagosCuotaSocios insertar(PagosCuotaSocios objeto) {
		// TODO Auto-generated method stub
		return null;
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

}
