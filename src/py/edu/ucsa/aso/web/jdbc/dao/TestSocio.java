package py.edu.ucsa.aso.web.jdbc.dao;

import java.util.ArrayList;
import java.util.List;

import py.edu.ucsa.aso.web.jdbc.dto.MontoCuota;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;

public class TestSocio {
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
		
		
		  List<MontoCuota> montocuotas = new ArrayList<>(); 
		  montocuotas = DAOFactory.getMontoCuotaDAO().listar();
		  for (MontoCuota cuota : montocuotas) {
		  System.out.println(cuota.getId());
		  System.out.println(cuota.getMontoCuota()); }
		 
	}

}
