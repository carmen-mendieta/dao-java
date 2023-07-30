package py.edu.ucsa.aso.web.jdbc.dao;

import py.edu.ucsa.aso.web.jdbc.impl.MontoCuotaDAOImpl;
import py.edu.ucsa.aso.web.jdbc.impl.SocioDAOJbdcImpl;

public abstract class DAOFactory {
	public static SocioDAO getSocioDAO() {
		return new SocioDAOJbdcImpl();
	}
	
	public static MontoCuotaDAO getMontoCuotaDAO() {
		return new MontoCuotaDAOImpl();
	}

}
