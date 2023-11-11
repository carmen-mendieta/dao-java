package py.edu.ucsa.aso.web.jdbc.dao;

import java.util.List;

import py.edu.ucsa.aso.web.jdbc.dto.Opcion;

public interface OpcionDAO extends GenericDAO<Opcion> {
	Opcion getOpcionByDominioCodOpcion(String dominio, String codOpcion);

	List<Opcion> getOpcionesByCodDominio(String dominio);

	List<Opcion> getOpcionesByIDDominio(int idDominio);
	
}
