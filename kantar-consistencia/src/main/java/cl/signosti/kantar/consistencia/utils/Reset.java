package cl.signosti.kantar.consistencia.utils;

import java.io.File;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.dao.BasesDao;
import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.PaisesDao;
import cl.signosti.kantar.consistencia.dao.PeriodosDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Basesm;
import cl.signosti.kantar.consistencia.modelo.Ejecucionesm;
import cl.signosti.kantar.consistencia.modelo.Paisesm;
import cl.signosti.kantar.consistencia.modelo.Periodom;

public class Reset {

	private static final Logger log = Logger.getLogger(Reset.class);
	
	public static boolean ejecucion(int idEjecucion){
		LocatorDao.getInstance();
		EjecucionesDao ejecucionesDao = LocatorDao.getEjecucionesDao();
		Ejecucionesm ejecucion = null;
		try {
			ejecucion = ejecucionesDao.getEjecucion(idEjecucion);
		} catch (Exception e) {
			log.error("Error interno", e);
			return true;
		}
		
		BasesDao basesDao = LocatorDao.getBasesDao();
		Basesm base = null;
		try {
			base = basesDao.getBase(ejecucion.getBase());
		} catch (SQLException e) {
			log.error("Error interno al leer la base", e);
			return true;
		}
		
		PaisesDao paisDao = LocatorDao.getPaisesDao();
		Paisesm pais = paisDao.getPais(base.getPaisid());
		
		PeriodosDao periodosDao = LocatorDao.getPeriodosDao();
		Periodom periodo = periodosDao.getPeriodo(base.getPeriodoid());
		
		String ruta = pais.getRuta()+"\\"+periodo.getDirName()+"\\"+base.getPanel()+"\\"+base.getGlosa()+"\\tables";
		File folderTables = new File(ruta);
		if (folderTables.isDirectory()){
			File[] archivos = folderTables.listFiles();
			for(File file: archivos){
				file.delete();
			}
		}
		
		ejecucionesDao.delete(idEjecucion);
		basesDao.delete(base.getId());
		
		return false;
	}
}
