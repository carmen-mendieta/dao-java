package py.edu.ucsa.aso.web.jdbc.dao;

import py.edu.ucsa.aso.web.jdbc.dto.PagosCuotaSocios;

public interface PagoCuotaDAO extends GenericDAO<PagosCuotaSocios> {
	
	String getUltimoMesAnhoCuotaPagada(int idSocio); 

}
