package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import py.edu.ucsa.aso.web.jdbc.dao.DAOFactory;
import py.edu.ucsa.aso.web.jdbc.dto.Dominio;
import py.edu.ucsa.aso.web.jdbc.dto.MontoCuota;
import py.edu.ucsa.aso.web.jdbc.dto.MovimientosSocios;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;
import py.edu.ucsa.aso.web.jdbc.dto.TiposMovimiento;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public class TestMain {
	public static void main(String[] args) {
		// Opcion
//		List<Opcion> listaopciones = DAOFactory.getOpcionDAO().listar();
//
//		for (Opcion opcion : listaopciones) {
//			System.out.println(opcion);
//		}

//		Opcion opcionById = DAOFactory.getOpcionDAO().getById(2);
//	       System.out.println(opcionById);

//		Opcion opcion = new Opcion();
//		opcion.setDominio(new Dominio(1));
//		opcion.setCodigo("ARGEN");
//		opcion.setDescripcion("PALERMO");
//		opcion.setEstado("I");
//		opcion.setOpcionPadre(new Opcion(2));
//		opcion.setId(65);
//		DAOFactory.getOpcionDAO().modificar(opcion);
		
	//	DAOFactory.getOpcionDAO().eliminar(64);
		
//		Opcion opcionByDominioCodOpcion = DAOFactory.getOpcionDAO().getOpcionByDominioCodOpcion("PAIS", "PAR");
//       System.out.println(opcionByDominioCodOpcion);
//		
//		List<Opcion> listaOpcionesByCodDominio = DAOFactory.getOpcionDAO().getOpcionesByCodDominio("CIUD");
//		 //  System.out.println(listaOpcionesByCodDominio);
//			for (Opcion listaCoddominio : listaOpcionesByCodDominio) {
//			System.out.println(listaCoddominio);
//			}
		

		// MovimientosSocios
//		 List<MovimientosSocios> listamovimientossocios = DAOFactory.getMovimientosSociosDAO().listar();
//	  
//		  for (MovimientosSocios movimientoSocio : listamovimientossocios) {
//		  System.out.println(movimientoSocio);
//		  }
		
//		MovimientosSocios movimientosByid = DAOFactory.getMovimientosSociosDAO().getById(61);
//       System.out.println(movimientosByid);
		
		MovimientosSocios ms= new MovimientosSocios();
		ms.setFechaPago(LocalDateTime.now());
		ms.setMonto(450000);
		ms.setConcepto(new Opcion(20));
		ms.setEstado(new Opcion(24));
		ms.setMedioPago(new Opcion(20));
		ms.setSocio(new Socio(2));
		ms.setTipoMovimiento(new TiposMovimiento(1));
		ms.setUsuarioAprobacion(new Usuario(1));
		ms.setUsuarioCreacion(new Usuario(1));
		ms.setId(82);
      DAOFactory.getMovimientosSociosDAO().modificar(ms);
//
//		

		// TiposMovimientos
//		 List<TiposMovimiento> listamovimientos = DAOFactory.getTiposMovimientoDAO().listar();
//		  
//		  for (TiposMovimiento tipomovimiento : listamovimientos) {
//			  System.out.println(tipomovimiento);
//			  }

//		TiposMovimiento tipoById = DAOFactory.getTiposMovimientoDAO().getById(2);
//			 System.out.println(tipoById);
//			
//          TiposMovimiento tipomovimiento = new TiposMovimiento();
//          tipomovimiento.setCodigo("PAGOCS");
//          tipomovimiento.setDescripcion("PAGO CUOTA SOCIAL");
//          tipomovimiento.setEstado("A");
//          tipomovimiento.setTipoDebCred("DEB");
//          DAOFactory.getTiposMovimientoDAO().insertar(tipomovimiento);
//		  

//        TiposMovimiento tipomovimiento = new TiposMovimiento(); 
//        tipomovimiento.setCodigo("PAGODA");
//        tipomovimiento.setDescripcion("PAGO DE DEVOLUCION POR APORTE");
//        tipomovimiento.setEstado("A");
//        tipomovimiento.setTipoDebCred("CRE");
//        tipomovimiento.setId(4);
//         DAOFactory.getTiposMovimientoDAO().modificar(tipomovimiento);	

		// DAOFactory.getTiposMovimientoDAO().eliminar(5);

		/// Socios
//		  Socio socio = new Socio();
//		  socio.setNombres("Julia");
//		  socio.setApellidos("Rojas");
//		  socio.setEmail("email");
//		  socio.setNroSocio(56954); 
//		  socio.setNroCedula(8954);
//		  socio.setFechaIngreso(LocalDateTime.now());
//		  socio.setEstadoActual(new Opcion(6));
//		  socio.setFechaEstadoActual(LocalDateTime.now());
//		  socio.setFundador(true);
//		  socio.setUsuarioCreacion(new Usuario(1));
//		  socio.setFechaCreacion(LocalDateTime.now());
//		  socio.setSocioPoponente(new
//		  Socio(1));
//		  socio.setTipoSocio(new Opcion(1));
//		  socio.setId(14);
//		  DAOFactory.getSocioDAO().modificar(socio);
//	

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
		 * 
		 * 
		 * // DAOFactory.getSocioDAO().eliminar(3);
		 * 
		 * // Socio sociod=DAOFactory.getSocioDAO().getSocioByNroCedula(12355); //
		 * System.out.println(sociod);
		 * 
		 * 
		 * /*Montos cuotas
		 */

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

		// DAOFactory.getMontoCuotaDAO().eliminar(8);

		/*
		 * MontoCuota montocuota=
		 * DAOFactory.getMontoCuotaDAO().getMontoCuotaByMesAnho(04, 2016);
		 * System.out.println(montocuota);
		 */

	}

}
