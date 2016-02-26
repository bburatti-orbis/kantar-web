package cl.signosti.kantar.consistencia.dwr;

import java.util.List;

import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.AlcancesKpiM;
import cl.signosti.kantar.consistencia.modelo.Area;
import cl.signosti.kantar.consistencia.modelo.CategoriaKpiM;
import cl.signosti.kantar.consistencia.modelo.GrillaSemaforo;
import cl.signosti.kantar.consistencia.modelo.KpiM;
import cl.signosti.kantar.consistencia.modelo.ListadoKpiM;
import cl.signosti.kantar.consistencia.modelo.OperadorSemaforo;
import cl.signosti.kantar.consistencia.modelo.Paisesm;
import cl.signosti.kantar.consistencia.modelo.ParametrosKpi;
import cl.signosti.kantar.consistencia.modelo.ParametrosKpiM;
import cl.signosti.kantar.consistencia.modelo.Semaforo;
import cl.signosti.kantar.consistencia.modelo.SemaforoKpi;
import cl.signosti.kantar.consistencia.modelo.StoreProcedure;

public class ModuloKpiDWR {
	
	public ListadoKpiM[] listadoKpi(String nombreKpi){
		ListadoKpiM[] arr = null;
		try{
			List<ListadoKpiM> lp = null;
			lp = (LocatorDao.getKpi()).getListadoKpi();
			if(lp != null && lp.size() > 0){
				arr = new ListadoKpiM[lp.size()];
				arr = lp.toArray(arr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return arr;
	}
	
	public Paisesm[] listadoPaises(){
		Paisesm[] arr = null;
		try {
			List<Paisesm> lp = null;
			lp = (LocatorDao.getKpi()).getPaises();
			if(lp != null && lp.size() > 0){
				arr = new Paisesm[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arr;
	}
	
	public AlcancesKpiM[] listadoAlcances(){
		AlcancesKpiM[] arr = null;
		try {
			List<AlcancesKpiM> lp = null;
			lp = (LocatorDao.getKpi()).getAlcance();
			if(lp != null && lp.size() > 0){
				arr = new AlcancesKpiM[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arr;
	}
	
	public Area[] listadoAreas(){
		Area[] arr = null;
		try {
			List<Area> lp = null;
			lp = (LocatorDao.getKpi()).getAreas();
			if(lp != null && lp.size() > 0){
				arr = new Area[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arr;
	}
	
	public CategoriaKpiM[] listadoCateg(){
		CategoriaKpiM[] arr = null;
		try {
			List<CategoriaKpiM> lp = null;
			lp = (LocatorDao.getKpi()).getCategorias();
			if(lp != null && lp.size() > 0){
				arr = new CategoriaKpiM[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arr;
	}
	
	public ParametrosKpi[] listParametros(){
		ParametrosKpi[] arr = null;
		
		try {
			List<ParametrosKpi> lp = null;
			lp = (LocatorDao.getKpi()).getParamDefecto();
			if(lp != null && lp.size() > 0){
				arr = new ParametrosKpi[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public ParametrosKpi[] cargaParametros(){
		ParametrosKpi[] arr = null;
		
		try {
			List<ParametrosKpi> lp = null;
			lp = (LocatorDao.getKpi()).getCargaParam();
			if(lp != null && lp.size() > 0){
				arr = new ParametrosKpi[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public StoreProcedure[] cargaSpInterno(){
		StoreProcedure[] arr = null;
		
		try {
			List<StoreProcedure> lp = null;
			lp = (LocatorDao.getKpi()).getSpInterno();
			if(lp != null && lp.size() > 0){
				arr = new StoreProcedure[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public StoreProcedure[] cargaSpExterno(){
		StoreProcedure[] arr = null;
		
		try {
			List<StoreProcedure> lp = null;
			lp = (LocatorDao.getKpi()).getSpExterno();
			if(lp != null && lp.size() > 0){
				arr = new StoreProcedure[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public Semaforo[] cargaColores(){
		Semaforo[] arr = null;
		
		try {
			List<Semaforo> lp = null;
			lp = (LocatorDao.getKpi()).getColores();
			if(lp != null && lp.size() > 0){
				arr = new Semaforo[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public OperadorSemaforo[] cargaOperadores(){
		OperadorSemaforo[] arr = null;
		
		try {
			List<OperadorSemaforo> lp = null;
			lp = (LocatorDao.getKpi()).getOperadores();
			if(lp != null && lp.size() > 0){
				arr = new OperadorSemaforo[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	public int grabarKpi (KpiM kpi){
		int id = 0;
		try{
			if(kpi.getPais() == 0){
				id = (LocatorDao.getKpi()).grabaKpiSinPais(kpi);
			}else{
				id = (LocatorDao.getKpi()).grabaKpi(kpi);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	
	public int grabarParam (ParametrosKpiM[] param){
		int id = 0;
		try{
			if(param.length > 0){
				for(ParametrosKpiM p:param){
					if(p.getTipo().equals("S")){
						p.setDefectoS(p.getDefecto().trim());
						p.setDefectoI(0);
						p.setDefectoD((double) 0);
					}
					if(p.getTipo().equals("I") || p.getTipo().equals("T")){
						p.setDefectoI(Integer.parseInt(p.getDefecto()));
						p.setDefectoD((double) 0);
					}
					if(p.getTipo().equals("D")){
						p.setDefectoD(Double.parseDouble(p.getDefecto()));
						p.setDefectoI(0);
					}
					id=(LocatorDao.getKpi()).grabarParametros(p);				
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	
	public int grabarSema (SemaforoKpi[] sema){
		int id = 0;
		try{
			if(sema.length > 0){
				for(SemaforoKpi s:sema){
					if(s.getPais()==0){
						id=(LocatorDao.getKpi()).grabarSemaforosSinPais(s);
					}else{
						id=(LocatorDao.getKpi()).grabarSemaforos(s);
					}
									
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	
	public KpiM rescataKpi(int id){
		KpiM kpi = new KpiM();
		System.out.println("edita kpi.. dwr");
		try {
			kpi = (LocatorDao.getKpi()).getKpi(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kpi;
	}
	
	public ParametrosKpi[] getParam(int id){
		ParametrosKpi[] arr = null;
		
		try {
			List<ParametrosKpi> lp = null;
			lp = (LocatorDao.getKpi()).getParam(id);
			if(lp != null && lp.size() > 0){
				arr = new ParametrosKpi[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public GrillaSemaforo[] getUmbrales(int id){
		GrillaSemaforo[] arr = null;
		
		try {
			List<GrillaSemaforo> lp = null;
			lp = (LocatorDao.getKpi()).getUmbrales(id);
			if(lp != null && lp.size() > 0){
				arr = new GrillaSemaforo[lp.size()];
				arr = lp.toArray(arr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public boolean actualizaKpi(KpiM kpi){
		boolean flag = false;
		
		try {
			flag = (LocatorDao.getKpi()).actualizaKpi(kpi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}