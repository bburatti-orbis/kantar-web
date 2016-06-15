package cl.signosti.kantar.consistencia.motorSeguimiento;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cl.signosti.kantar.consistencia.constans.Constans;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Proyectom;
import cl.signosti.kantar.consistencia.modelo.Tareasm;
import cl.signosti.kantar.consistencia.modelo.Usuariom;
import cl.signosti.kantar.consistencia.utils.PropertiesUtil;
import cl.signosti.kantar.consistencia.modelo.TareasEjecuciones;
import cl.signosti.kantar.consistencia.modelo.ProyectoEjecuciones;

public class MotorHelper {

	protected Calendar activarNuevaEjecucionProyecto(Calendar fechaInicio, Proyectom proyecto){
//			si (el proyecto se repite en forma = 'semana' (proyecto.ciclo.serepite == 2)) 
			if(proyecto.getCiclo().getSe_repite() == 2){
//				fechaRetornada = * Activar según procedimiento semanal ( fecha de hoy )
				return activarSemanal(fechaInicio, proyecto);
			}
			else{
//				fechaRetornada = * Activar proyecto con fecha ( parametro fecha )
				return activarConFecha(fechaInicio, proyecto);
			}
	}
	
//	* Activar según procedimiento semanal
	private Calendar activarSemanal(Calendar fechaInicio, Proyectom proyecto){
//		Nota: el campo proyecto.ciclo.repetirel es un campo binario de 7 dígitos con los días a ejecutar el proyecto
//		por ejemplo: 0010101 = valor decimal "21" significa que se ejecuta los vie, mie y lun en donde
//		lun es el primer bit de la derecha, mie es el 3er bit de derecha a izquierda y vie el quinto bit
//		de derecha a izquierda.
		
//		El procedimiento de activación sería el siguiente (Crear todos los id de Ejecución que corresponda ejecutar
//		la próxima semana):
		
//		Determinar el día de la semana de la fecha actual, invocando: getDayOfTheWeek(new Date())
		int dia = getDayOfTheWeek(new Date());
//		Determinar cuantos días faltan para el próximo lunes: "dias" = 8 - día de la semana de la fecha actual
		int diasParaProximoLunes = 8 - dia;
//		Sumar el número de días que faltan para el próximo lunes a la fecha actual: Date nexEjecucion = new Date(new Date() + (1000 * 60 * 60 * 24 * "dias"))
		Date proximaEjecucion = new Date((new Date()).getTime() + (1000 * 60 * 60 * 24 * diasParaProximoLunes));
		Date proximaEjecucionCiclo = new Date((new Date()).getTime() + (1000 * 60 * 60 * 24 * (diasParaProximoLunes + 7)));
//		Determinar si se debe activar una ejecución de lunes a domingo: 
//		-Contar repeticiones del proyecto (SELECT COUNT(*) FROM proyecto_ejecuciones pj WHERE pj.proyecto_id = id del proyecto)
		int repeticiones = LocatorDao.getMotorDao().ctdaEjecucionesProy(proyecto.getId());
		int x;
		for(x=0; x < 7; x++)
		{
			if((proyecto.getCiclo().getRepetirel() / (int)Math.pow(2,x)) % 2 == 1) // Vemos si el bit "x" está en 1
			{
				if(proyecto.getCiclo().getTipotermino() == 2)
				{
					if (repeticiones < proyecto.getCiclo().getRepeticiones())
					{
//						incrementar repeticiones (repeticiones++)
						repeticiones++;
					}
					else
					{
						break;
					}
				}
				if(proyecto.getCiclo().getTipotermino() == 3)
				{
					if(proximaEjecucion.getTime() > proyecto.getCiclo().getRepetirhastaC().getTime().getTime())
					{
						break;
					}
				}
//				* Activar proyecto con fecha (nexEjecucion)
				Calendar fechaInicioEjecu = GregorianCalendar.getInstance();
				fechaInicioEjecu.setTime(proximaEjecucion);
				activarConFecha(fechaInicioEjecu, proyecto);
//				-Sumar un día a nexEjecucion
//				nexEjecucion = new Date(nexEjecucion + (1000 * 60 * 60 * 24))
				proximaEjecucion = new Date(proximaEjecucion.getTime() + (1000 * 60 * 60 * 24));
			}
		}
		Calendar retorno = GregorianCalendar.getInstance();
		retorno.setTime(proximaEjecucionCiclo);
		return retorno;
	}

	public static int getDayOfTheWeek(Date d){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		int dia = cal.get(Calendar.DAY_OF_WEEK);
		if (dia == 0) dia = 7;
		return dia;		
	}

	private void crearT(TareasEjecuciones tarea, int idEjecucion, Calendar inicioProyecto, Proyectom proyecto){
		//obtener tarea
		TareasEjecuciones t = LocatorDao.getMotorDao().obtenerTareaEjecucionProyecto(tarea, idEjecucion, proyecto);
		if(t == null)
		{
			//Tarea aun no es creada, veremos si las antecesoras estan listas
			List<Integer> antecesoras = LocatorDao.getMotorDao().obtenerAntecesorasTarea(tarea, proyecto);
			if(antecesoras != null){
				for(Integer idA:antecesoras){
					TareasEjecuciones ta = new TareasEjecuciones();
					ta.setId(idA);
					ta = LocatorDao.getMotorDao().obtenerTareaEjecucionProyecto(ta, idEjecucion, proyecto);
					if(ta == null){
						ta = new TareasEjecuciones();
						ta.setId(idA);
						crearT(ta, idEjecucion, inicioProyecto, proyecto);
					}
				}
				Date inicioTarea = LocatorDao.getMotorDao().obtenerFechaMayorFinAntecesor(tarea, idEjecucion, proyecto);
				Calendar inicio = GregorianCalendar.getInstance();
				inicio.setTime(inicioTarea);
				crearTEjecucion(tarea, idEjecucion, inicio, proyecto);
			}else{
				//Insertar nueva ejecución de tarea con inicio de proyecto
				crearTEjecucion(tarea, idEjecucion, inicioProyecto, proyecto);
			}
		}else{
			//Tarea ya existe
			return;
		}
	}
	
	private void crearTEjecucion(TareasEjecuciones tarea, int idEjecucion, Calendar inicioTarea, Proyectom proyecto){
		Tareasm template = LocatorDao.getMotorDao().obtenerTareaTemplate(tarea, proyecto);
		//Calcular fin de tarea
		Calendar fin = new GregorianCalendar().getInstance();
		fin.setTime(inicioTarea.getTime());
		//1 Dias
		if(template.getTiempoInt() == 1){
			fin.add(Calendar.DAY_OF_YEAR, 1*template.getPlazo());
		}
		//2 Semanas
		if(template.getTiempoInt() == 2){
			fin.add(Calendar.WEEK_OF_YEAR, template.getPlazo());
		}
		//3 Meses
		if(template.getTiempoInt() == 3){
			fin.add(Calendar.MONTH, template.getPlazo());
		}
		//hacer el insert de la tarea con estado pendiente
		LocatorDao.getMotorDao().insertTareaEjecucion(tarea, proyecto, template, idEjecucion, inicioTarea, fin);
	}
	
	private Calendar activarConFecha(Calendar fechaInicio, Proyectom proyecto){
//		* Activar proyecto con fecha (Fecha de Inicio del Proyecto)
//		- Crear un id Ejecución del proyecto en tabla Proyectos_Ejecuciones con estado "Iniciada"
		int idEjecucion = LocatorDao.getMotorDao().crearNuevaEjecucionProyecto(fechaInicio, proyecto);
//		- Crear todas las Tareas dependientes del Proyecto asociadas al id de Ejecucion, en la tabla Tareas_Ejecuciones con los plazos y dependencias definidas en el proyecto, con estado "Iniciada"
		List<TareasEjecuciones> lt = LocatorDao.getMotorDao().obtenerTareasProyectoTemplate(proyecto);
		if(lt != null){
			for(TareasEjecuciones t:lt){
				crearT(t, idEjecucion, fechaInicio, proyecto);	
			}
		}
		return fechaInicioProximoCiclo(proyecto);
	}
	
	private Calendar fechaInicioProximoCiclo(Proyectom proyecto){
		//			* Calcular fechaInicioProximoCiclo, según procedimiento siguiente
		//			- Leer último Proyecto_Ejecuciones y tomar fecha_inicio_produccion
		Calendar retorno = GregorianCalendar.getInstance();
		ProyectoEjecuciones ultimaEjecu = LocatorDao.getMotorDao().obtenerUltimoProyectoEjecucion(proyecto);
					if(proyecto.getCiclo().getSe_repite() ==  1) //'dia' 
					{
		//				sumar un día a último Proyecto_Ejecuciones.fecha_inicio_produccion y almacenar en proyecto.fechaInicioProximoCiclo
						ultimaEjecu.setFecha_inicio_produccion(new Date(ultimaEjecu.getFecha_inicio_produccion().getTime()+(1000 * 60 * 60 * 24)));
						retorno.setTime(ultimaEjecu.getFecha_inicio_produccion());
					}
					else if(proyecto.getCiclo().getSe_repite() ==  2) //'semana' 
					{
		//				sumar una semana a proyecto.ciclo.serepite
						ultimaEjecu.setFecha_inicio_produccion(new Date(ultimaEjecu.getFecha_inicio_produccion().getTime()+(1000 * 60 * 60 * 24 * 7)));
						retorno.setTime(ultimaEjecu.getFecha_inicio_produccion());
					}
					else if(proyecto.getCiclo().getSe_repite() ==  3) //'mes' 
					{
		//				sumar un mes a proyecto.ciclo.serepite
						retorno.setTime(ultimaEjecu.getFecha_inicio_produccion());
						retorno.add(Calendar.MONTH, 1);
					}
					else{
						retorno = null;						
					}
		return retorno;
	}
	
	protected void notificar(Tareasm t, Proyectom p, String asunto){
		Usuariom u = LocatorDao.getMotorDao().obtenerDatosResponsable(t);
        String host = PropertiesUtil.getInstance().recuperaValor("Servidor_SMTP");
        String port = PropertiesUtil.getInstance().recuperaValor("Puerto_SMTP");
        String mailFrom = PropertiesUtil.getInstance().recuperaValor("Correo");
        String password = PropertiesUtil.getInstance().recuperaValor("Password");
        
        String msg = PropertiesUtil.getInstance().recuperaValor(Constans.MENSAJE_TAREA_PENDIENTE);
        
////        msg = msg.replaceFirst("$_nombreUsuario_$", u.getNombre() + " " +u.getApellido());
////        msg = msg.replaceFirst("$_contenido_$", t.getNombre());        
//		try {
////			cl.signosti.kantar.consistencia.utils.Mail.sendEmailWithAttachments(host, port, mailFrom, password, u.getEmai(), asunto, msg, null);
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}


	
}
