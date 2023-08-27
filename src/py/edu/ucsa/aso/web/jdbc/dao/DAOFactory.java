package py.edu.ucsa.aso.web.jdbc.dao;

import py.edu.ucsa.aso.web.jdbc.impl.MontoCuotaDAOImpl;
import py.edu.ucsa.aso.web.jdbc.impl.SocioDAOJbdcImpl;
import py.edu.ucsa.aso.web.jdbc.impl.TiposMovimientoDAOImpl;

public abstract class DAOFactory {
	public static SocioDAO getSocioDAO() {
		return new SocioDAOJbdcImpl();
	}

	public static MontoCuotaDAO getMontoCuotaDAO() {
		return new MontoCuotaDAOImpl();
	}

	public static TiposMovimientoDAO getTiposMovimientoDAO() {
		return new TiposMovimientoDAOImpl();
	}

}
