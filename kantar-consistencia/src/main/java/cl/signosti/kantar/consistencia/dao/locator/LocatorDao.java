package cl.signosti.kantar.consistencia.dao.locator;

import java.io.Serializable;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.signosti.kantar.consistencia.dao.AutorizacionesDao;
import cl.signosti.kantar.consistencia.dao.BasesDao;
import cl.signosti.kantar.consistencia.dao.CicloDao;
import cl.signosti.kantar.consistencia.dao.ClienteDao;
import cl.signosti.kantar.consistencia.dao.ConjuntoDao;
import cl.signosti.kantar.consistencia.dao.MarcaDao;
import cl.signosti.kantar.consistencia.dao.NomesclaturaDao;
import cl.signosti.kantar.consistencia.dao.ProcesoDao;
import cl.signosti.kantar.consistencia.dao.ProyectoDao;
import cl.signosti.kantar.consistencia.dao.MonitorDao;
import cl.signosti.kantar.consistencia.dao.ReportesDao;
import cl.signosti.kantar.consistencia.dao.TareasDao;
import cl.signosti.kantar.consistencia.dao.UsuariosDao;
import cl.signosti.kantar.consistencia.dao.PaisesDao;
import cl.signosti.kantar.consistencia.dao.PeriodosDao;
import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.KpiDao;
import cl.signosti.kantar.consistencia.dao.BitacoraDao;

public class LocatorDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2591440895912183790L;

	private static ClassPathXmlApplicationContext context = null;

	private static LocatorDao instance;

	private LocatorDao() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	public static LocatorDao getInstance() {
		if (instance == null) {
			instance = new LocatorDao();
		}
		return instance;
	}

	public static UsuariosDao getusuariodao() {
		return (UsuariosDao) context.getBean("usuariosdao");
	}

	public static PaisesDao getPaisesDao() {
		return (PaisesDao) context.getBean("PaisesDao");
	}

	public static ClienteDao getClienteDao() {
		return (ClienteDao) context.getBean("ClienteDao");
	}

	public static BasesDao getBasesDao() {
		return (BasesDao) context.getBean("BasesDao");
	}

	public static EjecucionesDao getEjecucionesDao() {
		return (EjecucionesDao) context.getBean("EjecucionesDao");
	}

	public static NomesclaturaDao getNomenclarurasDao() {
		return (NomesclaturaDao) context.getBean("NomesclaturaDao");
	}

	public static MarcaDao getMarcaDao() {
		return (MarcaDao) context.getBean("MarcaDao");
	}

	public static ReportesDao getReportesDao() {
		return (ReportesDao) context.getBean("ReportesDao");
	}

	public static ProcesoDao getProcesoDao() {
		return (ProcesoDao) context.getBean("ProcesoDao");
	}
	public static ProyectoDao getProyectoDao() {
		return (ProyectoDao) context.getBean("ProyectoDao");
	}
	public static CicloDao getCicloDao() {
		return (CicloDao) context.getBean("CicloDao");
	}
	public static TareasDao getTareasDao() {
		return (TareasDao) context.getBean("TareasDao");
	}
	public static ConjuntoDao getConjuntoDao() {
		return (ConjuntoDao) context.getBean("ConjuntoDao");
	}
	public static BitacoraDao getBitacoraDao() {
		return (BitacoraDao) context.getBean("BitacoraDao");
	}
	public static MonitorDao getMonitor() {
		return (MonitorDao) context.getBean("MonitorDao");
	}
	public static KpiDao getKpi() {
		return (KpiDao) context.getBean("KpiDao");
	}
	public static AutorizacionesDao getAutorizacionesDao() {
		return (AutorizacionesDao) context.getBean("AutorizacionesDao");
	}
	
	public static PeriodosDao getPeriodosDao() {
		return (PeriodosDao) context.getBean("PeriodosDao");
	}

}
