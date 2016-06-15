package cl.signosti.kantar.consistencia.motorSeguimiento;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cl.signosti.kantar.consistencia.constans.Constans;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Proyectom;
import cl.signosti.kantar.consistencia.modelo.Tareasm;
import cl.signosti.kantar.consistencia.utils.PropertiesUtil;

public class Motor extends MotorHelper {
	
	public void flujo1(){
		Calendar fechaActual = new GregorianCalendar().getInstance();
		System.out.println("Fecha actual : " + fechaActual);
		
		fechaActual.setTime(new Date());

		List<Proyectom> lp = null;
		try{
			//Para todos los proyectos que no estén en estado "Inactivo" (proyecto.estados_id != 2) {
			lp = LocatorDao.getMotorDao().obtenerProyectosF1();
		}catch(Exception e){
			e.printStackTrace();
		}

		if(lp != null){
			for(Proyectom p:lp){
				Calendar fechaR = null;
				//Es decir todos los que estén "Activos" (1) o NULL (nunca ejecutados)
				//si (el proyecto tiene estado "Activo" (proyecto.estados_id == 1))
				//si (Fecha_Actual >= proyecto.fechaInicioProximoCiclo - dias del Parámetro de Despliege)
				Calendar fechaProximaEjecucion = new GregorianCalendar().getInstance();
				Calendar fechaPivote = new GregorianCalendar().getInstance();
				if(p.getFechaInicioProximoCiclo() != null){
					fechaProximaEjecucion.setTime(p.getFechaInicioProximoCiclo());
					fechaPivote.setTime(p.getFechaInicioProximoCiclo());
				}else{
					fechaProximaEjecucion.setTime(p.getFechaIni());
					fechaPivote.setTime(p.getFechaIni());
				}
				int diasParametroDespliege = Integer.parseInt(PropertiesUtil.getInstance().recuperaValor(Constans.WORKFLOW_DESPLIEGUE));
				fechaPivote.add(Calendar.DAY_OF_YEAR, -1*diasParametroDespliege);
				
				if(p.getEstados_id().equals("1")){

					if(fechaActual.after(fechaPivote)){
//						si(tipo de termino del proyecto = 'xRepeticiones' (proyecto.ciclo.tipo_termino == 2))
						if(p.getCiclo().getTipotermino() == 2){
//							- Contar cantidad de ejecuciones (SELECT COUNT(*) FROM proyecto_ejecuciones pj WHERE pj.proyecto_id = id del proyecto)
							int cantidadEjecuciones = LocatorDao.getMotorDao().ctdaEjecucionesProy(p.getId());
//							si (repeticiones máximas del proyecto > cantidad de ejecuciones (proyecto.ciclo.repeticiones > cantidad de ejecuciones del paso anterior))
							if(p.getCiclo().getRepeticiones() > cantidadEjecuciones){
//								fechaRetornada = * "Activar nueva ejecución del proyecto" ( proyecto.fechaInicioProximoCiclo )
								fechaR = activarNuevaEjecucionProyecto(fechaProximaEjecucion, p); 
							}
							else{
//								- Dejar proyecto Inactivo:
//								proyecto.estados_id = 2								
								LocatorDao.getMotorDao().dejarProyectoInactivo(p.getId());
							}
						}
//						sino si (tipo de termino del proyecto = 'xFecha' (proyecto.ciclo.tipo_termino == 3) )
						else if(p.getCiclo().getTipotermino() == 3){
//							si(fecha actual < fecha tope  (fechaActual < proyecto.ciclo.repetir_hasta))
							if(fechaActual.before(p.getCiclo().getRepetirhastaC())){
//								fechaRetornada = * "Activar nueva ejecución del proyecto" ( proyecto.fechaInicioProximoCiclo )
								fechaR = activarNuevaEjecucionProyecto(fechaProximaEjecucion, p); 
							}
//							sino
							else{
//								- Dejar proyecto Inactivo:
//								proyecto.estados_id = 2
								LocatorDao.getMotorDao().dejarProyectoInactivo(p.getId());								
							}
						}
//						sino
						else{
//							fechaRetornada = * "Activar nueva ejecución del proyecto" ( proyecto.fechaInicioProximoCiclo )
							fechaR = activarNuevaEjecucionProyecto(fechaProximaEjecucion, p);
						}

					}
				}else if(p.getEstados_id().equals("3")){
//					// Proyectos que nunca han sido ejecutados (proyecto.estados_id == 3)
//					si (Fecha_Actual >= Fecha de Inicio del Proyecto - dias del Parámetro de Despliege)
					if(fechaActual.after(fechaPivote)){
//						fechaRetornada = * "Activar nueva ejecución del proyecto" ( proyecto.ciclo.fecha_ini )
						fechaR = activarNuevaEjecucionProyecto(fechaProximaEjecucion, p); 
					}
				}
				if(fechaR != null){
//					Actualizar fechaInicioProximoCiclo con fecha retornada:
//					proyecto.fechaInicioProximoCiclo = fechaRetornada
					LocatorDao.getMotorDao().actualizarProximaEjecucion(fechaR, p.getId());
				}
			}
		}else{
			System.out.println("No hay proyectos para trabajar");
		}
	}
	
	public void flujo2(){
		//Falta definición		
	}
	
	public void flujo3(){
		List<Proyectom> proyectosPorIniciar = LocatorDao.getMotorDao().proyectosPorIniciarHoy();
		if(proyectosPorIniciar != null){
			for(Proyectom p:proyectosPorIniciar){
				List<Tareasm> lt = null; 
				lt = LocatorDao.getMotorDao().tareasSinPredecesoras(p);
				if(lt !=  null){
					for(Tareasm t:lt){
						LocatorDao.getMotorDao().dejarTareaPendienteYVisible(p, t);
						notificar(t, p, PropertiesUtil.getInstance().recuperaValor(Constans.ASUNTO_TAREA_PENDIENTE));
					}
				}
			}
		}
	}
	
	public void flujo4(){
				List<Tareasm> lt = null; 
				lt = LocatorDao.getMotorDao().tareasConPredecesorasEnEstadoCreadaDeHoy();
				if(lt !=  null){
					for(Tareasm t:lt){
						List<Tareasm> ltpnf = LocatorDao.getMotorDao().obtenerTareasPredecesorasNoFinalizadas(t);
						if(ltpnf != null){
							LocatorDao.getMotorDao().dejarTareaEnEspera(t);
							notificar(t, null,PropertiesUtil.getInstance().recuperaValor(Constans.ASUNTO_TAREA_ENESPERA));
						}else{
							LocatorDao.getMotorDao().dejarTareaPendienteYVisible(null, t);
							notificar(t, null, PropertiesUtil.getInstance().recuperaValor(Constans.ASUNTO_TAREA_PENDIENTE));							
						}
					}
				}
	}
	
	public void flujo5(int idTareaEjecucion){
		LocatorDao.getMotorDao().dejarTareaFinalizada(idTareaEjecucion);
		boolean esUltimaTareaProyecto = LocatorDao.getMotorDao().esUltimaTareaProyecto(idTareaEjecucion);
		if(esUltimaTareaProyecto){
			LocatorDao.getMotorDao().dejarProyectoejecucionFinalizado(idTareaEjecucion);
		}else{
			List<Tareasm> ltSucesoraA = LocatorDao.getMotorDao().tareasSucesorasAutomaticas(idTareaEjecucion);
			if(ltSucesoraA != null){
				for(Tareasm t:ltSucesoraA){
					
				}
			}else{
				//FIN
			}
		}
	}
	
}
