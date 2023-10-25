package py.edu.ucsa.aso.web.jdbc.dao;

import py.edu.ucsa.aso.web.jdbc.impl.DominioDAOImpl;
import py.edu.ucsa.aso.web.jdbc.impl.MontoCuotaDAOImpl;
import py.edu.ucsa.aso.web.jdbc.impl.MovimientosSociosDAOImpl;
import py.edu.ucsa.aso.web.jdbc.impl.OpcionDAOJdbcImpl;
import py.edu.ucsa.aso.web.jdbc.impl.PagoCuotaDAOImpl;
import py.edu.ucsa.aso.web.jdbc.impl.SocioDAOJbdcImpl;
import py.edu.ucsa.aso.web.jdbc.impl.TiposMovimientoDAOImpl;
import py.edu.ucsa.aso.web.jdbc.impl.UsuarioDAOImpl;

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

	public static MovimientosSociosDAO getMovimientosSociosDAO() {
		return new MovimientosSociosDAOImpl();
	}

	public static OpcionDAO getOpcionDAO() {
		return new OpcionDAOJdbcImpl();

	}

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();

	}
	
	public static DominioDAO getDominioDAO() {
		return new DominioDAOImpl();
	}
	
	public static PagoCuotaDAO getPagoCuotaDAO() {
		return new PagoCuotaDAOImpl();
	}

}
