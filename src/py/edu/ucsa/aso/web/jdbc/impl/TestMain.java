package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import py.edu.ucsa.aso.web.jdbc.dao.DAOFactory;
import py.edu.ucsa.aso.web.jdbc.dto.MontoCuota;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public class TestMain {
	public static void main(String[] args) {

		/*
		 * List<Socio> listaSocios = DAOFactory.getSocioDAO().listar();
		 * 
		 * for (Socio socio : listaSocios) { System.out.println(socio); }
		 * 
		 * 
		 * // Socio socioById = DAOFactory.getSocioDAO().getById(1);
		 * 
		 * 
		 * /* Socio socio = new Socio(); socio.setNombres("Julia");
		 * socio.setApellidos("Ramirez"); socio.setEmail("email");
		 * socio.setNroSocio(56987); socio.setNroCedula(89541);
		 * socio.setFechaIngreso(LocalDateTime.now()); socio.setEstadoActual(new
		 * Opcion(1)); socio.setFechaEstadoActual(LocalDateTime.now());
		 * socio.setFundador(true); socio.setUsuarioCreacion(new Usuario(1));
		 * socio.setFechaCreacion(LocalDateTime.now()); socio.setSocioPoponente(new
		 * Socio(1)); socio.setTipoSocio(new Opcion(1));
		 * DAOFactory.getSocioDAO().insertar(socio);
	

		/*
		 * Socio sociom = new Socio(); sociom.setNombres("Ariel");
		 * sociom.setApellidos("Ayala"); sociom.setEmail("ariel@gmail");
		 * sociom.setNroSocio(154727); sociom.setNroCedula(4845813);
		 * sociom.setFechaIngreso(LocalDateTime.now()); sociom.setEstadoActual(new
		 * Opcion(1)); sociom.setFechaEstadoActual(LocalDateTime.now());
		 * sociom.setFundador(true); sociom.setUsuarioCreacion(new Usuario(1));
		 * sociom.setFechaCreacion(LocalDateTime.now()); sociom.setSocioPoponente(new
		 * Socio(1)); sociom.setTipoSocio(new Opcion(1)); sociom.setId(1);
		 * DAOFactory.getSocioDAO().modificar(sociom);
		 */

		// DAOFactory.getSocioDAO().eliminar(3);

		// Socio sociod=DAOFactory.getSocioDAO().getSocioByNroCedula(12355);
		// System.out.println(sociod);
		

		/*Montos cuotas*/
		
		
		 // List<MontoCuota> listaMontosc = DAOFactory.getMontoCuotaDAO().listar(); for
		// (MontoCuota monto : listaMontosc) { System.out.println(monto); }
		 

		/*
		 * MontoCuota montoc = DAOFactory.getMontoCuotaDAO().getById(2);
		 * System.out.println(montoc);
		 */
		
		// tanto para insertar y modificar 
		/*
		 * MontoCuota mc= new MontoCuota(); mc.setMonto(10000);
		 * mc.setFechaCreacion(LocalDateTime.now());
		 * mc.setFechaIniVigencia(LocalDateTime.of(2019, 8, 01, 0, 0));
		 * mc.setFechaFinVigencia(LocalDateTime.of(2020, 8, 01, 0, 0));
		 * mc.setEstado("A"); mc.setFechaInactivacion(LocalDateTime.of(2020, 8, 24, 0,
		 * 0, 0)); mc.setUsuarioInactivacion(new Usuario(1)); mc.setId(5);
		 * DAOFactory.getMontoCuotaDAO().modificar(mc);
		 */
		  
	     //	DAOFactory.getMontoCuotaDAO().eliminar(8);
		 
		
		// MontoCuota montocuota=
				// DAOFactory.getMontoCuotaDAO().getMontoCuotaByMesAnho(04, 2016);
				// System.out.println(montocuota);

		
	}

}
