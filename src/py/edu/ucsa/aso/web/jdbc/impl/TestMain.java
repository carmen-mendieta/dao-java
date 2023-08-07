package py.edu.ucsa.aso.web.jdbc.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import py.edu.ucsa.aso.web.jdbc.dao.DAOFactory;
import py.edu.ucsa.aso.web.jdbc.dto.Opcion;
import py.edu.ucsa.aso.web.jdbc.dto.Socio;
import py.edu.ucsa.aso.web.jdbc.dto.Usuario;

public class TestMain {
	public static void main(String[] args) {
		
//		List<Socio> listaSocios = DAOFactory.getSocioDAO().listar();
//		
//		for (Socio socio : listaSocios) {
//			System.out.println(socio);
//		}
		
		Socio socioById = DAOFactory.getSocioDAO().getById(1);
		
		
		Socio socio = new Socio();
		socio.setNombres("Maria del Carmen");
		socio.setApellidos("Mendieta");
		socio.setEmail("email test");
		socio.setNroSocio(123488);
		socio.setNroCedula(12355);
		socio.setFechaIngreso(LocalDateTime.now());
		socio.setEstadoActual(new Opcion(1));
		socio.setFechaEstadoActual(LocalDateTime.now());
		socio.setFundador(true);
		socio.setUsuarioCreacion(new Usuario(1));
		socio.setFechaCreacion(LocalDateTime.now());
		socio.setSocioPoponente(new Socio(1));
		socio.setTipoSocio(new Opcion(1));
		DAOFactory.getSocioDAO().insertar(socio);
		
		
	}

}
