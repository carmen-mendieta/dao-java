package py.edu.ucsa.aso.web.jdbc.dao;

import py.edu.ucsa.aso.web.jdbc.dto.MontoCuota;

public interface MontoCuotaDAO extends GenericDAO<MontoCuota>{
	MontoCuota getMontoCuotaByMesAnho(int mes, int anho);

}
