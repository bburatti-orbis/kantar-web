package cl.signosti.kantar.consistencia.dwr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.AccionBitacora;
import cl.signosti.kantar.consistencia.modelo.Area;
import cl.signosti.kantar.consistencia.modelo.Bitacora;
import cl.signosti.kantar.consistencia.modelo.ListadoProyectos;
import cl.signosti.kantar.consistencia.modelo.PerformanceAreas;
import cl.signosti.kantar.consistencia.modelo.ReportePerformance;
import cl.signosti.kantar.consistencia.modelo.ReportesRevisarProyecto;
import cl.signosti.kantar.consistencia.modelo.Tareasm;
import cl.signosti.kantar.consistencia.modelo.Usuariom;
import cl.signosti.kantar.consistencia.motorSeguimiento.Motor;

public class KantarDWR {
	
	private static final Logger log = Logger.getLogger(KantarDWR.class);
	
	public String flujo1DWR(){
		try{(new Motor()).flujo1();}catch(Exception e){e.printStackTrace();return "ERROR";}
		return "OK";
	}
	
	public Tareasm[] getEjecutarProyectoXUserAndProyecto(int idUser, int proyectoId, String inicio, String fin){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date inicioD = null;
		Date finD = null;
		try {
			inicioD = formatter.parse(inicio);
			finD = formatter.parse(fin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Tareasm[] arrTareas = null;
		try{
			List<Tareasm> lt = null;
			lt = (LocatorDao.getTareasDao()).getEjecutarProyectoXUserAndProyecto(idUser, proyectoId, inicioD, finD);
			if(lt != null && lt.size() > 0){
				arrTareas = new Tareasm[lt.size()];
				arrTareas = lt.toArray(arrTareas);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("Error al obtener Tareas para Ejecutar proyecto", e);
		}
		return arrTareas;
	}
	
	public Bitacora[] getBitacoraTarea(int idTarea){
		Bitacora[] arr = null;
		try{
			List<Bitacora> lt = null;
			lt = (LocatorDao.getBitacoraDao()).getBitacoraTarea(idTarea);
			if(lt != null && lt.size() > 0){
				arr = new Bitacora[lt.size()];
				arr = lt.toArray(arr);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("Error al obtener bitacora", e);
		}
		return arr;
	}
	
	public boolean ingresarBitacora(Bitacora bita, int idEstadoTarea){
		boolean result = false;
		try{
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(bita.getFechaEstimadaFinTareaS());
			bita.setFechaEstimadaFinTareaD(date);
			System.out.println(date);
			log.debug(date);
			(LocatorDao.getBitacoraDao()).ingresarBitacora(bita);
			AccionBitacora[] abr = this.getAccionBitacora(idEstadoTarea);
			for(AccionBitacora ab:abr){
				if(bita.getIdAccion()==ab.getIdAccion()){
					if(ab.getEstadoInicial()==idEstadoTarea){
						(LocatorDao.getBitacoraDao()).actualizarEstadoTarea(bita);
					}
				}
			}
			if(bita.getIdAccion()==6){
				(LocatorDao.getBitacoraDao()).actualizarResponsable(bita);
			}
			if(bita.getIdAccion()==1){
				Tareasm tarea = (LocatorDao.getTareasDao()).getTareaEjecucion(bita.getIdTarea());
				System.out.println("Estimado d : " + tarea.getInicioEstimadoD());
				System.out.println("Real d : " + (new Date()));
				Calendar inicioEstimado = this.DateToCalendar(tarea.getInicioEstimadoD());
				Calendar inicioReal = Calendar.getInstance();
				inicioReal.setTime(new Date());
				int diff = this.getDesviacion(inicioEstimado, inicioReal);
				(LocatorDao.getBitacoraDao()).actualizarDesviacionInicio(bita, diff);
			}
			if(bita.getIdAccion()==5){
				Tareasm tarea = (LocatorDao.getTareasDao()).getTareaEjecucion(bita.getIdTarea());
				System.out.println("Estimado d : " + tarea.getInicioEstimadoD());
				System.out.println("Real d : " + (new Date()));
				Calendar inicioEstimado = this.DateToCalendar(tarea.getFinEstimadoD());
				Calendar inicioReal = Calendar.getInstance();
				inicioReal.setTime(new Date());
				int diff = this.getDesviacion(inicioEstimado, inicioReal);
				(LocatorDao.getBitacoraDao()).actualizarDesviacionFin(bita, diff);
			}
			result = true;
		}catch(Exception e){
			e.printStackTrace();
			log.error("Error al obtener bitacora", e);
		}
		return result;
	}
	
	public Calendar DateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	}
	
	public AccionBitacora[] getAccionBitacora(int idEstadoTarea){
		AccionBitacora[] arr = null;
		try{
			List<AccionBitacora> lt = null;
			lt = (LocatorDao.getBitacoraDao()).getAccionBitacora(idEstadoTarea);
			if(lt != null && lt.size() > 0){
				arr = new AccionBitacora[lt.size()];
				arr = lt.toArray(arr);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("Error al obtener Acciones de bitacora", e);
		}
		return arr;
	}
	
	public Area[] getAreas(){
		Area[] arr = null;
		try{
			List<Area> lt = null;
			lt = (LocatorDao.getBitacoraDao()).getAreas();
			if(lt != null && lt.size() > 0){
				arr = new Area[lt.size()];
				arr = lt.toArray(arr);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("Error al obtener areas", e);
		}
		return arr;		
	}
	
	public Usuariom[] getUserPorArea(int idArea){
		Usuariom[] arr = null;
		try{
			List<Usuariom> lt = null;
			lt = (LocatorDao.getBitacoraDao()).getUserPorArea(idArea);
			if(lt != null && lt.size() > 0){
				arr = new Usuariom[lt.size()];
				arr = lt.toArray(arr);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("Error al responsables", e);
		}
		return arr;
	}
	
	public int getDesviacion(Calendar fechaInicial, Calendar fechaFinal) {
		
		System.out.println("Estimada : " + fechaInicial);
		System.out.println("Real : " + fechaFinal);
		
		int diffDays= 0;
		int diffH= 0;
		//mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
		while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
		//si el dia de la semana de la fecha minima es diferente de sabado o domingo
		if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY ||
		fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			diffDays++;
		}
		//se suma 1 dia para hacer la validacion del siguiente dia.
		fechaInicial.add(Calendar.DATE, 1);
		}
		if(fechaInicial.get(Calendar.DAY_OF_YEAR) > fechaFinal.get(Calendar.DAY_OF_YEAR)){
			fechaInicial.add(Calendar.DATE, -1);
			diffDays--;
		}
		if(!(fechaInicial.get(Calendar.DAY_OF_YEAR) > fechaFinal.get(Calendar.DAY_OF_YEAR))){
		if(fechaInicial.before(fechaFinal)){
			while (fechaInicial.before(fechaFinal)) {
				System.out.println("Estimado : " + fechaInicial.getTime());
				System.out.println("Real : " + fechaFinal.getTime());
				if (fechaInicial.get(Calendar.HOUR_OF_DAY) <  fechaFinal.get(Calendar.HOUR_OF_DAY)) {
					diffH++;
					fechaInicial.add(Calendar.HOUR, 1);
				}else{
					break;
				}
			}			
		}
		else if(fechaInicial.after(fechaFinal)){
			while (fechaInicial.after(fechaFinal)) {
				System.out.println("Estimado : " + fechaInicial.getTime());
				System.out.println("Real : " + fechaFinal.getTime());
				if (fechaInicial.get(Calendar.HOUR_OF_DAY) >  fechaFinal.get(Calendar.HOUR_OF_DAY)) {
					diffH--;
					fechaInicial.add(Calendar.HOUR, -1);
				}else{
					break;
				}
			}
		}
	}
		System.out.println("Dias : " + diffDays);
		System.out.println("Horas : " + diffH);
		return diffDays*8+diffH;
	}
	
	public ReportePerformance[] performance(){
		ReportePerformance[] arr = null;
		try{
			List<ReportePerformance> lp = null;
			lp = (LocatorDao.getReportesDao()).getReportePerformance();
			if(lp != null && lp.size() > 0){
				arr = new ReportePerformance[lp.size()];
				arr = lp.toArray(arr);
			}
			System.out.println("largo: "+arr.length);
		}catch(Exception e){
			e.printStackTrace();
		}
		return arr;
	}
	
	public ListadoProyectos[] ejecucionProyectos(){
		ListadoProyectos[] arr = null;
		try{
			List<ListadoProyectos> lp = null;
			lp = (LocatorDao.getReportesDao()).getListadoProyectos();
			if(lp != null && lp.size() > 0){
				arr = new ListadoProyectos[lp.size()];
				arr = lp.toArray(arr);
			}
			System.out.println("largo: "+arr.length);
		}catch(Exception e){
			e.printStackTrace();
		}
		return arr;
	}
	
	public ReportesRevisarProyecto[] reporteProyecto(int idProyecto){
		ReportesRevisarProyecto[] arr = null;
		try{
			List<ReportesRevisarProyecto> lp = null;
			lp = (LocatorDao.getReportesDao()).getDetalleEjecucion(idProyecto);
			if(lp != null && lp.size() > 0){
				arr = new ReportesRevisarProyecto[lp.size()];
				arr = lp.toArray(arr);
			}
			System.out.println("largo: "+arr.length);
		}catch(Exception e){
			e.printStackTrace();
		}
		return arr;
	}
}