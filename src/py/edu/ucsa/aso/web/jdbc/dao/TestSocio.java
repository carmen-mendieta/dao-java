package py.edu.ucsa.aso.web.jdbc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import py.edu.ucsa.aso.web.jdbc.dto.MontoCuota;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;

public class TestSocio {

	/*
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); Date fecha =
	 * sdf.parse("2023-08-10");
	 */
	public static void main(String[] args) {
		/*
		 * lista de socios List<Socio> socios = new ArrayList<>(); socios =
		 * DAOFactory.getSocioDAO().listar(); for (Socio socio : socios) {
		 * System.out.println(socio.getNombre());
		 * System.out.println(socio.getApellido()); }
		 */

		/*
		 * obtiene un socio en base a su id Socio
		 * socio=DAOFactory.getSocioDAO().getById(110876); System.out.println(socio);
		 * 
		 */

//		Socio socioi = new Socio();
//		socioi.setNrosocio(154727);
//		socioi.setNombre("Ariel");
//		socioi.setApellido("Ayala Mendieta");
//		socioi.setCedula("4913845");
//		socioi.setCelular("0982691234");
//		DAOFactory.getSocioDAO().modificar(socioi);
//
//		DAOFactory.getSocioDAO().eliminar(154727);

//	Socio sociod=DAOFactory.getSocioDAO().getSocioByNroCedula("5848038");
//       System.out.println(sociod);

		/*
		 * List<MontoCuota> montocuotas = new ArrayList<>(); montocuotas =
		 * DAOFactory.getMontoCuotaDAO().listar(); for (MontoCuota cuota : montocuotas)
		 * { System.out.println(cuota.getId());
		 * System.out.println(cuota.getMontoCuota()); }
		 */

		/*
		 * MontoCuota mto =DAOFactory.getMontoCuotaDAO().getById(3);
		 * System.out.println(mto);
		 */

		/*
		 * MontoCuota mc= new MontoCuota(); mc.setId(5); mc.setMontoCuota(60000);
		 * mc.setNumeroCuota(4);
		 * mc.setFechaVencimiento(java.sql.Date.valueOf("2023-09-01"));
		 * DAOFactory.getMontoCuotaDAO().modificar(mc);
		 */

		DAOFactory.getMontoCuotaDAO().eliminar(4);

	}

}
