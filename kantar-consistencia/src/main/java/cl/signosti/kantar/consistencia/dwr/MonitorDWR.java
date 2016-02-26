package cl.signosti.kantar.consistencia.dwr;

import java.util.List;

import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.PerformanceAreas;
import cl.signosti.kantar.consistencia.modelo.Proyectom;

public class MonitorDWR {

	public int[] proyectosEnEjecucion(){
		int[] p = null;
		try{
			p = (LocatorDao.getMonitor()).getProyectos();
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}
	
	public Proyectom[] proyectosEnEjecucionV2(){
		Proyectom[] p = null;
		try{
			List<Proyectom> lp = (LocatorDao.getMonitor()).getProyectosV2();
			if(lp != null && lp.size() > 0){
				p = new Proyectom[lp.size()];
				p = lp.toArray(p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}
	
	public int[] correlativoTareas(){
		int[] p = null;
		int[] pEjec = null;
		try{
			pEjec = (LocatorDao.getMonitor()).getProyectos2();
			p = new int[pEjec.length];
			for(int i = 0; i<pEjec.length;i++){
				p[i] = (LocatorDao.getMonitor()).getCorrelaTareas(pEjec[i]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}
	
	public int[] porcentajeAvance(){
		int[] p = null;
		int[] pEjec = null;
		try{
			pEjec = (LocatorDao.getMonitor()).getProyectos2();
			p = new int[pEjec.length];
			for(int i = 0; i<pEjec.length;i++){
				p[i] = (LocatorDao.getMonitor()).getProcentajeAvance(pEjec[i]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	} 
	
	public int[] tareasAtrasadas(){
		int[] p = null;
		int[] pEjec = null;
		try{
			pEjec = (LocatorDao.getMonitor()).getProyectos2();
			p = new int[pEjec.length];
			for(int i = 0; i<pEjec.length;i++){
				p[i] = (LocatorDao.getMonitor()).getTareasAtrasadas(pEjec[i]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}
	
	public int[] desviacion(){
		int[] p = null;
		int[] pEjec = null;
		int ffr = 0, ffe = 0;
		try{
			pEjec = (LocatorDao.getMonitor()).getProyectos2();
			p = new int[pEjec.length];
			for(int i = 0; i<pEjec.length;i++){
				ffr = (LocatorDao.getMonitor()).getFFR(pEjec[i]);
				ffe = (LocatorDao.getMonitor()).getFFE(pEjec[i]);
				if (ffe != 0){
					p[i]=((ffr/ffe)-1);
				}else{
					p[i]=-1;
				}
				ffr = 0;
				ffe = 0;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}
	
	public PerformanceAreas[] performance(){
		PerformanceAreas[] arr = null;
		try{
			List<PerformanceAreas> lp = null;
			lp = (LocatorDao.getMonitor()).getPerformance();
			if(lp != null && lp.size() > 0){
				arr = new PerformanceAreas[lp.size()];
				arr = lp.toArray(arr);
			}
			System.out.println("largo: "+arr.length);
		}catch(Exception e){
			e.printStackTrace();
		}
		return arr;
	}
}
