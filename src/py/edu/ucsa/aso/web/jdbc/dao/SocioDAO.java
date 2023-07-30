package py.edu.ucsa.aso.web.jdbc.dao;

import py.edu.ucsa.aso.web.jdbc.dto.Socio;

public interface SocioDAO extends GenericDAO<Socio> {
	Socio getSocioByNroCedula(String nroCedula);

}
