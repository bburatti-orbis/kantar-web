package cl.signosti.kantar.consistencia;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.dao.BasesDao;
import cl.signosti.kantar.consistencia.dao.CicloDao;
import cl.signosti.kantar.consistencia.dao.ConjuntoDao;
import cl.signosti.kantar.consistencia.dao.ProyectoDao;
import cl.signosti.kantar.consistencia.dao.TareasDao;
import cl.signosti.kantar.consistencia.dao.UsuariosDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;

import cl.signosti.kantar.consistencia.modelo.Conjuntom;
import cl.signosti.kantar.consistencia.modelo.Proyectom;
import cl.signosti.kantar.consistencia.modelo.Tareasm;
import cl.signosti.kantar.consistencia.modelo.Usuariom;
import cl.signosti.kantar.consistencia.modelo.ciclom;
import cl.signosti.kantar.consistencia.modelo.vproyecm;


import com.google.gson.Gson;
import com.sun.jersey.api.view.Viewable;
@Path("/gestion")
public class GestionProyecto {
	private static final Logger logger = Logger.getLogger(GestionProyecto.class);
	
	@GET
	@Produces({ MediaType.TEXT_HTML })
	public Response getgest_proyec(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {	

			return Response.ok(new Viewable("/views/ver_proyectos")).build();
		}

	}
	
	@GET
	@Path("/crearproyec")
	@Produces({ MediaType.TEXT_HTML })
	public Response getcrea_proyec(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/crea_proyec")).build();
		}

	}
	@GET
	@Path("/creartarea")
	@Produces({ MediaType.TEXT_HTML })
	public Response getcrea_tarea(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/crear_tarea")).build();
		}

	}
	@POST
	@Path("/inserproyec")
	@Produces({ MediaType.TEXT_HTML })
	public Response setproyec(@Context HttpServletRequest req,MultivaluedMap<String, String> formParams ) {

  		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {
			
			Proyectom proyec= new Proyectom();
			proyec.setNombre(formParams.getFirst("nombre"));
			proyec.setCalendario(formParams.getFirst("calendario"));
			proyec.setId_paises(formParams.getFirst("pais"));
			proyec.setId_ciclo(Integer.parseInt(formParams.getFirst("id_ciclo")));
			
			LocatorDao.getInstance();
			ProyectoDao p = LocatorDao.getProyectoDao();
			p.setProyecto(proyec);
			
			Gson gson = new Gson();	
			String resp = gson.toJson(true);
			return Response.ok(resp).build();

			
		}

	}
	
	@GET
	@Path("/getciclo")
	@Produces({ MediaType.TEXT_HTML })
	public Response getciclo(@Context HttpServletRequest req,@QueryParam("accion") int cod) {
		

		
		LocatorDao.getInstance();
		ConjuntoDao p = LocatorDao.getConjuntoDao();	
		
		List<Conjuntom> list = null;
		try {
			list = p.getconjunto(cod);
		} catch (SQLException e) {

				 logger.error("Error, causa:" ,e);
		}
		Gson gson = new Gson();	
		String resp = gson.toJson(list);
		return Response.ok(resp).build();

	}
	
	
	
	
	@POST
	@Path("/crearproyec/ciclo")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response setciclo(@Context HttpServletRequest req,MultivaluedMap<String, String> formParams) {
	
		String cod= formParams.getFirst("cod");
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ciclom ciclo= new ciclom();
		if(cod.equals("1")){
			ciclo.setSe_repite(1);
			ciclo.setRepetircada(Integer.parseInt( formParams.getFirst("Repetircada")));
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				String fechaini= formParams.getFirst("fechaini");
				
				try {
					Date data=formatoDelTexto.parse(fechaini);
					ciclo.setFechaini(formato.format(data));
				} catch (ParseException e1) {
					
					 logger.error("Error, causa:" ,e1);
				}
				
			ciclo.setTipotermino(Integer.parseInt(  formParams.getFirst("Tipotermino")));
			if(ciclo.getTipotermino()==1){
				
			}
			if(ciclo.getTipotermino()==2){
				
				ciclo.setRepeticiones(Integer.parseInt(formParams.getFirst("repeticiones")));
				 	
			}
			if(ciclo.getTipotermino()==3){
			

				String fechater= formParams.getFirst("termina");
				
				try {
					Date data=formatoDelTexto.parse(fechater);
					ciclo.setRepetirhasta(formato.format(data));
				} catch (ParseException e1) {
					
					 logger.error("Error, causa:" ,e1);
				}
			}
		}
		else if(cod.equals("2")){
			
			ciclo.setSe_repite(2);
			ciclo.setRepetircada(Integer.parseInt( formParams.getFirst("Repetircada")));
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				String fechaini= formParams.getFirst("fechaini");
				
				try {
					Date data=formatoDelTexto.parse(fechaini);
					ciclo.setFechaini(formato.format(data));
				} catch (ParseException e1) {
				
					 logger.error("Error, causa:" ,e1);
				}
				
			ciclo.setTipotermino(Integer.parseInt(  formParams.getFirst("Tipotermino")));
			if(ciclo.getTipotermino()==1){
				
			}
			if(ciclo.getTipotermino()==2){
				
				ciclo.setRepeticiones(Integer.parseInt(formParams.getFirst("repeticiones")));
				 	
			}
			if(ciclo.getTipotermino()==3){
			

				String fechater= formParams.getFirst("termina");
				
				try {
					Date data=formatoDelTexto.parse(fechater);
					ciclo.setRepetirhasta(formato.format(data));
				} catch (ParseException e1) {
				
					 logger.error("Error, causa:" ,e1);
				}
			}
			ciclo.setRepetirel(Integer.parseInt(formParams.getFirst("Reperitel")));
			
			
		}
		else if(cod.equals("3")){
			ciclo.setSe_repite(3);
			ciclo.setRepetircada(Integer.parseInt( formParams.getFirst("Repetircada")));
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				String fechaini= formParams.getFirst("fechaini");
				
				try {
					Date data=formatoDelTexto.parse(fechaini);
					ciclo.setFechaini(formato.format(data));
				} catch (ParseException e1) {
				
					 logger.error("Error, causa:" ,e1);
				}
				
			ciclo.setTipotermino(Integer.parseInt(  formParams.getFirst("Tipotermino")));
			if(ciclo.getTipotermino()==1){
				
			}
			if(ciclo.getTipotermino()==2){
				
				ciclo.setRepeticiones(Integer.parseInt(formParams.getFirst("repeticiones")));
				 	
			}
			if(ciclo.getTipotermino()==3){
			

				String fechater= formParams.getFirst("termina");
				
				try {
					Date data=formatoDelTexto.parse(fechater);
					ciclo.setRepetirhasta(formato.format(data));
				} catch (ParseException e1) {
			
					 logger.error("Error, causa:" ,e1);
				}
			}
		}
			
		
		else if(cod.equals("4")){
			ciclo.setSe_repite(4);
			ciclo.setRepetircada(Integer.parseInt( formParams.getFirst("Repetircada")));
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				String fechaini= formParams.getFirst("fechaini");
				
				try {
					Date data=formatoDelTexto.parse(fechaini);
					ciclo.setFechaini(formato.format(data));
				} catch (ParseException e1) {
		
					 logger.error("Error, causa:" ,e1);
				}
				
				ciclo.setTipotermino(Integer.parseInt(  formParams.getFirst("Tipotermino")));
			if(ciclo.getTipotermino()==1){
				
			}
			if(ciclo.getTipotermino()==2){
				
				ciclo.setRepeticiones(Integer.parseInt(formParams.getFirst("repeticiones")));
				 	
			}
			if(ciclo.getTipotermino()==3){
			

				String fechater= formParams.getFirst("termina");
				
				try {
					Date data=formatoDelTexto.parse(fechater);
					ciclo.setRepetirhasta(formato.format(data));
				} catch (ParseException e1) {
				
					 logger.error("Error, causa:" ,e1);
				}
			}
		
			
		}
		LocatorDao.getInstance();
		CicloDao p = LocatorDao.getCicloDao();
		int id=p.setCiclo(ciclo);
		
		
		Gson gson = new Gson();	
		String resp = gson.toJson(id);
		return Response.ok(resp).build();
		
	

	}
	
	@GET
	@Path("/crearconjunto")
	@Produces({ MediaType.TEXT_HTML })
	public Response getcrearconjunto(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/crearconjunto")).build();
		}

	}
	
	@POST
	@Path("/insertarea")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response settarea(@Context HttpServletRequest req,MultivaluedMap<String, String> formParams ) {

  		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {
			
			Tareasm tarea= new Tareasm();
			
			tarea.setAutomatica( Boolean.parseBoolean(formParams.getFirst("automatica")));
			tarea.setNombre(formParams.getFirst("nombre"));
			tarea.setIdresponsable(Integer.parseInt(formParams.getFirst("idresponsable")));
			tarea.setPlazo(Integer.parseInt(formParams.getFirst("plazo")));
			tarea.setTiempo(Integer.parseInt(formParams.getFirst("tiempo")));
			tarea.setTiempodelay(Integer.parseInt(formParams.getFirst("tiempodelay")));
			
			String idante= formParams.getFirst("conjunto");
			if(idante.equals("0")){
				tarea.setIdconjunto(null);
			}
			else{
				tarea.setIdconjunto(Integer.parseInt(idante));
			}
					
					
			tarea.setDelay(Integer.parseInt(formParams.getFirst("delay")));
			tarea.setFrecuencia(Integer.parseInt(formParams.getFirst("frecuencia")));
			tarea.setTiempofrecuencia(formParams.getFirst("tiempofrecuencia"));
			String predecesoras=formParams.getFirst("idantecesora");
			
			String[] t= predecesoras.split(";");
			Integer[] arreglo= new Integer[t.length];
			for(int i =0; i<t.length; i++){
				arreglo[i]=Integer.parseInt(t[i]);
			}
				
			tarea.setIdantesesora(arreglo);	
			
			
			
			tarea.setTipo_calendario(Integer.parseInt(formParams.getFirst("tipo_calendario")));
			tarea.setProyecto_id(Integer.parseInt(formParams.getFirst("id_proyec")));
			
			LocatorDao.getInstance();
			TareasDao p = LocatorDao.getTareasDao();
			p.setTarea(tarea);
			
			
			Gson gson = new Gson();	
			String resp = gson.toJson(true);
			return Response.ok(resp).build();
		}

	}
	@GET
	@Path("/ver_tareas")
	@Produces({ MediaType.TEXT_HTML })
	public Response getver_tareas(@Context HttpServletRequest req,@QueryParam("accion") int cod) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			
			return Response.ok(new Viewable("/views/gest_proyec")).build();
		}

	}

	
	@POST
	@Path("/setestado")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response setestado_proyec(@Context HttpServletRequest req,MultivaluedMap<String, String> formParams) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			int cod= Integer.parseInt(formParams.getFirst("valor"));
			LocatorDao.getInstance();
			ProyectoDao o=LocatorDao.getProyectoDao();
			o.setestado(cod);

			Gson gson = new Gson();	
			String resp = gson.toJson(true);
			return Response.ok(resp).build();
		}

	}
	
	@GET
	@Path("/listusers")
	@Produces({ MediaType.TEXT_HTML })
	public Response getusers(@Context HttpServletRequest req,@QueryParam("cod") int cod) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			List<Usuariom> list=new ArrayList<Usuariom>();
			LocatorDao.getInstance();
			UsuariosDao o=LocatorDao.getusuariodao();
			try {
				list=o.getusers(cod);
			} catch (SQLException e) {
				
					 logger.error("Error, causa:" ,e);
			}
			Gson gson = new Gson();	
			String resp = gson.toJson(list);
			return Response.ok(resp).build();
		}

	}
	
	
	
	@GET
	@Path("/gettareas")
	@Produces({ MediaType.TEXT_HTML })
	public Response gettareas(@Context HttpServletRequest req,@QueryParam("codigo") int cod) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			List<Tareasm> list=null;
			LocatorDao.getInstance();
			TareasDao tareasDao = LocatorDao.getTareasDao();
			try {
				list = tareasDao.gettarea(cod);
			} catch (SQLException e) {
				
					 logger.error("Error, causa:" ,e);
			}
			Gson gson = new Gson();	
			String resp = gson.toJson(list);
			return Response.ok(resp).build();
		}
	}


	@POST
	@Path("/setestadotarea")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response setestado_tarea(@Context HttpServletRequest req,MultivaluedMap<String, String> formParams) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			int cod= Integer.parseInt(formParams.getFirst("valor"));
			LocatorDao.getInstance();
			TareasDao o=LocatorDao.getTareasDao();
			o.setestado(cod);

			Gson gson = new Gson();	
			String resp = gson.toJson(true);
			return Response.ok(resp).build();
		}

	}
	
	
	
	@GET
	@Path("/getproyec")
	@Produces({ MediaType.TEXT_HTML })
	public Response get_proyec(@Context HttpServletRequest req,@QueryParam("codigo") int cod) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	

			LocatorDao.getInstance();
			ProyectoDao o=LocatorDao.getProyectoDao();
			List<vproyecm> list=new ArrayList<vproyecm>();
			list=o.getproyectos(cod);
			Gson gson = new Gson();	
			String resp = gson.toJson(list);
			return Response.ok(resp).build();
		}
	}
	
	@POST
	@Path("/actualizaproyec")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizaproyec(@Context HttpServletRequest req,MultivaluedMap<String, String> formParams ) {

  		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {
			
			Proyectom proyec= new Proyectom();
			proyec.setId(formParams.getFirst("id"));
			proyec.setNombre(formParams.getFirst("nombre"));
			proyec.setCalendario(formParams.getFirst("calendario"));
			proyec.setId_paises(formParams.getFirst("pais"));
			proyec.setId_ciclo(Integer.parseInt(formParams.getFirst("id_ciclo")));
			
			LocatorDao.getInstance();
			ProyectoDao p = LocatorDao.getProyectoDao();
			p.setactualizaproyecto(proyec);
			
			Gson gson = new Gson();	
			String resp = gson.toJson(true);
			return Response.ok(resp).build();
		}

	}
	

	@GET
	@Path("/gettarea/tarea")
	@Produces({ MediaType.TEXT_HTML })
	public Response gettareax(@Context HttpServletRequest req,@QueryParam("codigo") int cod) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			Tareasm proyec= new Tareasm();
			LocatorDao.getInstance();
			TareasDao o=LocatorDao.getTareasDao();
			try {
				proyec=o.gettareax(cod);
			} catch (SQLException e) {
				
					 logger.error("Error, causa:" ,e);
			}
			Gson gson = new Gson();	
			String resp = gson.toJson(proyec);
			return Response.ok(resp).build();
		}
	}
	
	
	@POST
	@Path("/actualizatarea")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizatarea(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams ) {

  		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {
			
			Tareasm proyec= new Tareasm();
			proyec.setId(Integer.parseInt(formParams.getFirst("id")));
			proyec.setNombre(formParams.getFirst("Nombre"));
			proyec.setIdresponsable(Integer.parseInt(formParams.getFirst("users")));
			proyec.setPlazo(Integer.parseInt(formParams.getFirst("plazo")));
			proyec.setTipo_calendario(Integer.parseInt(formParams.getFirst("calendario")));
			proyec.setTiempo(Integer.parseInt(formParams.getFirst("tiempo")));
			String predecesoras=formParams.getFirst("idantecesora");
			
			String[] t = predecesoras.split(";");
			Integer[] arreglo= new Integer[t.length];
			for(int i=0; i < t.length; i++){
				try{
					arreglo[i] = Integer.parseInt(t[i]);
				} catch (Exception e) {
					
				}
			}
				
			proyec.setIdantesesora(arreglo);		
			proyec.setDelay(Integer.parseInt(formParams.getFirst("delay")));
			proyec.setFrecuencia(Integer.parseInt(formParams.getFirst("frecuencia")));
			proyec.setTiempodelay(Integer.parseInt(formParams.getFirst("tiempodelay")));
			String idante= formParams.getFirst("conjunto");
			if(idante.equals("0")){
				proyec.setIdconjunto(null);
			}
			else{
				proyec.setIdconjunto(Integer.parseInt(idante));
			}
		
			LocatorDao.getInstance();
			TareasDao p = LocatorDao.getTareasDao();
			p.setUpdateTarea(proyec);
			
			Gson gson = new Gson();	
			String resp = gson.toJson(true);
			return Response.ok(resp).build();
		}

	}
	
	@POST
	@Path("/setconjunto")
	@Produces( MediaType.APPLICATION_JSON )
	public Response  setconjunto(String  formParams) {

			LocatorDao.getInstance();
			int id=0;
			String[]  a= formParams.split(";");
			for(int i=0;i<a.length;i++){
				if(i==0){
					
					ConjuntoDao p = LocatorDao.getConjuntoDao();
					id=p.setbase(a[i]);
				}
				else{
					BasesDao b=  LocatorDao.getBasesDao();
					b.setconjunto(id,Integer.parseInt(a[i]));
				}
			}
			
			Gson gson = new Gson();	
			String resp = gson.toJson(true);
			return Response.ok(resp).build();
			
		}
	
	@GET
	@Path("/getconjunto")
	@Produces({ MediaType.TEXT_HTML })
	public Response getconjunto(@Context HttpServletRequest req,@QueryParam("codigo") int cod) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			List<Tareasm> list=null;
			LocatorDao.getInstance();
			TareasDao o=LocatorDao.getTareasDao();
			try {
				list=o.gettarea(cod);
			} catch (SQLException e) {
				
					 logger.error("Error, causa:" ,e);
			}
			Gson gson = new Gson();	
			String resp = gson.toJson(list);
			return Response.ok(resp).build();
		}
	}
	
	@GET
	@Path("/getproyec/proyec")
	@Produces({ MediaType.TEXT_HTML })
	public Response getproyec(@Context HttpServletRequest req,@QueryParam("codigo") int cod) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			Proyectom p= new Proyectom();
			LocatorDao.getInstance();
			ProyectoDao o=LocatorDao.getProyectoDao();
			p=o.getproyecto(cod);
			Gson gson = new Gson();	
			String resp = gson.toJson(p);
			return Response.ok(resp).build();
		}
	}

	

	@GET
	@Path("/mod_proyec")
	@Produces({ MediaType.TEXT_HTML })
	public Response getmod_proyec(@Context HttpServletRequest req,@QueryParam("codigo") int cod) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {
			return Response.ok(new Viewable("/views/mod_proyecto")).build();
		}

	}
	@GET
	@Path("/mod_tarea")
	@Produces({ MediaType.TEXT_HTML })
	public Response getmod_proyectarea(@Context HttpServletRequest req,@QueryParam("tarea") int cod) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {
			return Response.ok(new Viewable("/views/mod_tareas")).build();
		}

	}
	
	
	
	@GET
	@Path("/getlistatareas")
	@Produces({ MediaType.TEXT_HTML })
	public Response gettarea(@Context HttpServletRequest req,@QueryParam("codigo") int cod,@QueryParam("tarea") int tarea) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			List<Tareasm> list=null;
			LocatorDao.getInstance();
			TareasDao o=LocatorDao.getTareasDao();
			try {
				list=o.gettareas(cod,tarea);
			} catch (SQLException e) {
				
					 logger.error("Error, causa:" ,e);
			}
			Gson gson = new Gson();	
			String resp = gson.toJson(list);
			return Response.ok(resp).build();
		}
	}
	
	@GET
	@Path("/listusuarios")
	@Produces({ MediaType.TEXT_HTML })
	public Response getusuarios(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("index", map)).build();

		} else {	
			List<Usuariom> list=new ArrayList<Usuariom>();
			LocatorDao.getInstance();
			UsuariosDao o=LocatorDao.getusuariodao();
			try {
				list=o.getuser();
			} catch (SQLException e) {
				
					 logger.error("Error, causa:" ,e);
			}
			Gson gson = new Gson();	
			String resp = gson.toJson(list);
			return Response.ok(resp).build();
		}

	}
	
	
}

