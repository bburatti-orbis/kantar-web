package cl.signosti.kantar.consistencia;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.dao.AutorizacionesDao;
import cl.signosti.kantar.consistencia.dao.BasesDao;
import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.ReportesDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Basesm;
import cl.signosti.kantar.consistencia.modelo.DetalleNomesclm;
import cl.signosti.kantar.consistencia.modelo.Ejecucionesm;
import cl.signosti.kantar.consistencia.modelo.ReportNomenm;
import cl.signosti.kantar.consistencia.modelo.Resultado;
import cl.signosti.kantar.consistencia.modelo.ResultadoGeneral;
import cl.signosti.kantar.consistencia.modelo.ResutadoGeneralm;
import cl.signosti.kantar.consistencia.modelo.Usuariom;
import cl.signosti.kantar.consistencia.utils.EnvioMail;
import cl.signosti.kantar.consistencia.utils.GlosaAprobacion;
import cl.signosti.kantar.consistencia.utils.PropertiesUtil;

import com.google.gson.Gson;
import com.sun.jersey.api.view.Viewable;

@Path("/Reportes")
public class Reportes {
	
	private static final Logger log = Logger.getLogger(Reportes.class);
	
	@GET
	@Produces({ MediaType.TEXT_HTML })
	public Response getIt(@Context HttpServletRequest req) {
		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/pagina3")).build();
		}

	}

	@GET
	@Path("Report/")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoGeneral getreportg(@QueryParam("inc") int inc, 
			@QueryParam("limit") int limit,
			@QueryParam("desde") String desde,
			@QueryParam("hasta") String hasta) {

		if(inc <= 0 || limit <= 0){
			inc = 0;
			limit = 100;
		}
		Date date = Calendar.getInstance().getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");	
		if(desde == null || desde.length() < 8){				    
			desde = sdf.format(date);
		}
		if(hasta == null || hasta.length() < 8){
			hasta = sdf.format(date);
		}
		sdf.setLenient(false);
		try{
			Date f1 = sdf.parse(desde);
			Date f2 = sdf.parse(hasta);
			if(f2.getTime() < f1.getTime()){
				hasta = desde;
			}
		}catch(Exception e){
			desde = sdf.format(date);
			hasta = sdf.format(date);
		}
		
		LocatorDao.getInstance();
		ReportesDao report = LocatorDao.getReportesDao();
		Map<Integer, ResutadoGeneralm> lista = new HashMap<Integer, ResutadoGeneralm>();
		ResultadoGeneral rslt = new ResultadoGeneral();
		
		lista = report.getResultGeneral(inc, limit, desde, hasta);
		rslt.setLista(lista);
		rslt.setInc(inc);
		rslt.setLimit(limit);
		rslt.setDesde(desde);
		rslt.setHasta(hasta);
		
		return rslt;

	}

	@GET
	@Path("fecha/")
	@Produces({ MediaType.TEXT_HTML })
	public Response getfecha() throws SQLException {

		LocatorDao.getInstance();
		EjecucionesDao report = LocatorDao.getEjecucionesDao();
		String lista = report.getFechaULtima();

		Gson gson = new Gson();
		String resp = gson.toJson(lista);

		return Response.ok(resp).build();

	}

	@GET
	@Path("/nomenclatura")
	@Produces({ MediaType.TEXT_HTML })
	public Response getnomenclatura(@QueryParam("codigo") String codigo)
			throws SQLException {

		return Response.ok(new Viewable("/views/vistanomenclaturas")).build();

	}

	@GET
	@Path("/nomenclatura/nomenc")
	@Produces({ MediaType.TEXT_HTML })
	public Response getnomenclaturalista(@QueryParam("codigo") int codigo)
			throws SQLException {

		LocatorDao.getInstance();
		int c = codigo;
		ReportesDao report = LocatorDao.getReportesDao();
		Map<Integer, ReportNomenm> lista = new HashMap<Integer, ReportNomenm>();
		lista = report.getnomenclatura(c);

		Gson gson = new Gson();
		String resp = gson.toJson(lista);

		return Response.ok(resp).build();

	}

	@GET
	@Path("/nomenclatura/detallenomenc")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Integer, DetalleNomesclm> getdetallenomenc(@QueryParam("codigo") int codigo)
			throws SQLException {

		LocatorDao.getInstance();
		int c = codigo;
		ReportesDao report = LocatorDao.getReportesDao();
		Map<Integer, DetalleNomesclm> lista = new HashMap<Integer, DetalleNomesclm>();
		lista = report.getdetallenom(c);

		return lista;

	}

	@GET
	@Path("/detallenom")
	@Produces({ MediaType.TEXT_HTML })
	public Response getdetallenom(@QueryParam("codigo") String codigo)
			throws SQLException {

		return Response.ok(new Viewable("/views/lineanomesclatura")).build();

	}

	@GET
	@Path("/descexcel")
	@Produces("application/vnd.ms-excel")
	public Response getdescexcel(@QueryParam("ruta") String ruta) throws SQLException,
			IOException {	
		
		String consistencias=PropertiesUtil.getInstance().recuperaValor("consistencias");
		
		File objFile = new File(consistencias+ruta);
		ResponseBuilder response = Response.ok((Object) objFile);
		response.header("Content-Disposition", "attachment; filename="
				+ objFile.getName());
		return response.build();

	}
	
	
	@GET
	@Path("/gest_proyec")
	@Produces({ MediaType.TEXT_HTML })
	public Response getgest_proyec(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/gest_proyec")).build();
		}

	}
	
	@GET
	@Path("/revisarProyecto")
	@Produces({ MediaType.TEXT_HTML })
	public Response getLitadoProyectos(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom)session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/reportes/listarProyectos")).build();
		}
	}
	
	@GET
	@Path("/perforEjecutivo")
	@Produces({ MediaType.TEXT_HTML })
	public Response getPlazos(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom)session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/reportes/reportePerformance")).build();
		}
	}
	
	@GET
	@Path("/reporteRevisarProyecto")
	@Produces({ MediaType.TEXT_HTML })
	public Response getReporteRevisaP(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom)session.getAttribute("user");
		
		String idEjecu = req.getParameter("i");
		
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {
			session.setAttribute("i", idEjecu);
			return Response.ok(new Viewable("/views/reportes/reporteRevisarProyecto")).build();
		}
	}

	@POST
	@Path("/autoriza")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Resultado getAutorizaHistorica(
			@Context HttpServletRequest req,
			GlosaAprobacion glosaAprobacion) {
		Resultado rslt = null;
		HttpSession session = req.getSession(true);
		Usuariom user = (Usuariom) session.getAttribute("user");
		if(user == null){
			return new Resultado(101, "Usuario no conectado");
		}
		
		LocatorDao.getInstance();
		AutorizacionesDao autorizacion = LocatorDao.getAutorizacionesDao();
		try {
			int codAutoriza = 0;
			if("historica".equalsIgnoreCase(glosaAprobacion.getAutoriza())){
				codAutoriza = 1;
			}
			autorizacion.consistencia(glosaAprobacion.getId(), glosaAprobacion.getGlosa(), codAutoriza, user.getId());
			rslt = new Resultado("Aprobacion ingresada");
		} catch (SQLException e) {
			log.error("ERROR al ingresar Autorizacion", e);
			rslt = new Resultado(102, "ERROR al ingresar Autorizacion");
		}
		
		return rslt;
	}
	
	@POST
	@Path("/emailTerminada")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Resultado getEmailTerminada(
			@Context HttpServletRequest req,
			GlosaAprobacion glosaAprobacion) {
		HttpSession session = req.getSession(true);
		Usuariom user = (Usuariom) session.getAttribute("user");
		if(user == null){
			return new Resultado(101, "Usuario no conectado");
		}
		
		EjecucionesDao ejecucionesDao = new EjecucionesDao();
		BasesDao basesDao = new BasesDao();
		Ejecucionesm ejecucion = null;
		Basesm base = null;
		try{
			ejecucion = ejecucionesDao.getEjecucion(glosaAprobacion.getId());
			base = basesDao.getbase(ejecucion.getBase());
		} catch (Exception e) {
			return new Resultado(201, "Error no existe Base de Datos");
		}
		
		
		String email = user.getEmai();  //TODO: Se debe buscar otra solucion
		String emailBody = "Su Base >"+ base.getGlosa() +
				"< del periodo >"+ ejecucion.getPeriodo() +
				"< esta disponible para su descarga en: >"+"<";

		new EnvioMail().send(email, emailBody);
		
		return new Resultado("Email enviado");
	}
}
