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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.sun.jersey.api.view.Viewable;

import cl.signosti.kantar.consistencia.dao.AutorizacionesDao;
import cl.signosti.kantar.consistencia.dao.BasesDao;
import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.PaisesDao;
import cl.signosti.kantar.consistencia.dao.ReportesDao;
import cl.signosti.kantar.consistencia.dao.UsuariosDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Basesm;
import cl.signosti.kantar.consistencia.modelo.DetalleNomesclm;
import cl.signosti.kantar.consistencia.modelo.Ejecucionesm;
import cl.signosti.kantar.consistencia.modelo.Paisesm;
import cl.signosti.kantar.consistencia.modelo.ReportNomenm;
import cl.signosti.kantar.consistencia.modelo.Resultado;
import cl.signosti.kantar.consistencia.modelo.ResultadoGeneral;
import cl.signosti.kantar.consistencia.modelo.ResultadoGeneralm;
import cl.signosti.kantar.consistencia.modelo.Usuariom;
import cl.signosti.kantar.consistencia.utils.EnvioMail;
import cl.signosti.kantar.consistencia.utils.GlosaAprobacion;
import cl.signosti.kantar.consistencia.utils.PropertiesUtil;
import cl.signosti.kantar.consistencia.utils.Reset;

@Path("Reportes")
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
	@Path("Report")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoGeneral getreportg(@QueryParam("inc") int inc, @QueryParam("limit") int limit,
			@QueryParam("desde") String desde, @QueryParam("hasta") String hasta) {

		if (inc <= 0 || limit <= 0) {
			inc = 0;
			limit = 500;
		}
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (desde == null || desde.length() < 8) {
			desde = sdf.format(date)+" 00:00:00";
		}
		if (hasta == null || hasta.length() < 8) {
			hasta = sdf.format(date)+" 23:59:59";
		}
		sdf.setLenient(false);
		try {
			Date f1 = sdf.parse(desde);
			Date f2 = sdf.parse(hasta);
			if (f2.getTime() < f1.getTime()) {
				hasta = desde;
			}
		} catch (Exception e) {
			desde = sdf.format(date);
			hasta = sdf.format(date);
		}

		LocatorDao.getInstance();
		ReportesDao report = LocatorDao.getReportesDao();
		Map<Integer, ResultadoGeneralm> lista = new HashMap<Integer, ResultadoGeneralm>();
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
	@Path("fecha")
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
	@Path("nomenclatura")
	@Produces({ MediaType.TEXT_HTML })
	public Response getnomenclatura(@QueryParam("codigo") String codigo) throws SQLException {

		return Response.ok(new Viewable("/views/vistanomenclaturas")).build();

	}

	@GET
	@Path("nomenclatura/nomenc")
	@Produces({ MediaType.TEXT_HTML })
	public Response getnomenclaturalista(@QueryParam("codigo") int codigo) throws SQLException {

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
	@Path("nomenclatura/detallenomenc")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Integer, DetalleNomesclm> getdetallenomenc(@QueryParam("codigo") int codigo) throws SQLException {

		LocatorDao.getInstance();
		int c = codigo;
		ReportesDao report = LocatorDao.getReportesDao();
		Map<Integer, DetalleNomesclm> lista = new HashMap<Integer, DetalleNomesclm>();
		lista = report.getdetallenom(c);

		return lista;

	}

	@GET
	@Path("detallenom")
	@Produces({ MediaType.TEXT_HTML })
	public Response getdetallenom(@QueryParam("codigo") String codigo) throws SQLException {

		return Response.ok(new Viewable("/views/lineanomesclatura")).build();

	}

	@GET
	@Path("descexcel")
	@Produces("application/vnd.ms-excel")
	public Response getdescexcel(@QueryParam("ruta") String ruta) throws SQLException, IOException {

		String consistencias = PropertiesUtil.getInstance().recuperaValor("consistencias");

		File objFile = new File(consistencias + ruta);
		ResponseBuilder response = Response.ok((Object) objFile);
		response.header("Content-Disposition", "attachment; filename=" + objFile.getName());
		return response.build();

	}

	@GET
	@Path("gest_proyec")
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
	@Path("revisarProyecto")
	@Produces({ MediaType.TEXT_HTML })
	public Response getLitadoProyectos(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom) session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/reportes/listarProyectos")).build();
		}
	}

	@GET
	@Path("perforEjecutivo")
	@Produces({ MediaType.TEXT_HTML })
	public Response getPlazos(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom) session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/reportes/reportePerformance")).build();
		}
	}

	@GET
	@Path("reporteRevisarProyecto")
	@Produces({ MediaType.TEXT_HTML })
	public Response getReporteRevisaP(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom) session.getAttribute("user");

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
	@Path("reset/{idEjecucion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Resultado getReset(@Context HttpServletRequest req, @PathParam("idEjecucion") int idEjecucion){
		Resultado rslt = new Resultado("0", "Recalculando ejecución");
		HttpSession session = req.getSession(true);
		Usuariom user = (Usuariom) session.getAttribute("user");
		if (user == null) {
			return new Resultado(101, "Usuario no conectado");
		}
		
		if (Reset.ejecucion(idEjecucion)){
			rslt = new Resultado("201", "Error Interno de la aplicación");
		} 
		
		return rslt;
	}
	
	
	@POST
	@Path("autoriza")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Resultado getAutorizaHistorica(@Context HttpServletRequest req, GlosaAprobacion glosaAprobacion) {
		Resultado rslt = null;
		HttpSession session = req.getSession(true);
		Usuariom user = (Usuariom) session.getAttribute("user");
		if (user == null) {
			return new Resultado(101, "Usuario no conectado");
		}

		LocatorDao.getInstance();
		AutorizacionesDao autorizacion = LocatorDao.getAutorizacionesDao();
		try {
			int codAutoriza = 0;
			if ("historica".equalsIgnoreCase(glosaAprobacion.getAutoriza())) {
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
	@Path("emailTerminada")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Resultado getEmailTerminada(@Context HttpServletRequest req, GlosaAprobacion glosaAprobacion) {
		HttpSession session = req.getSession(true);
		Usuariom user = (Usuariom) session.getAttribute("user");
		if (user == null) {
			return new Resultado(101, "Usuario no conectado");
		}

		EjecucionesDao ejecucionesDao = LocatorDao.getEjecucionesDao();
		PaisesDao paisesDao = LocatorDao.getPaisesDao();
		UsuariosDao usuariosDao = LocatorDao.getusuariodao();
		BasesDao basesDao = LocatorDao.getBasesDao();

		Ejecucionesm ejecucion = null;
		Basesm base = null;
		Paisesm pais = null;
		Usuariom supervisor = null;
		
		String email = user.getEmail();

		try {
			ejecucion = ejecucionesDao.getEjecucion(glosaAprobacion.getId());
			base = basesDao.getBase(ejecucion.getBase());
			pais = paisesDao.getPais(base.getPaisid());
			supervisor = usuariosDao.getUsuario(pais.getIdSupervisor());
			
			/* Regla para obtener el correo:
			 * 
			 * 		1.- Se usa como destino el correo del usuario supervisor de la Base, sino
			 * 
			 * 		2.- El correo del usuario supervisor del país, sino
			 * 
			 * 		3.- El correo del usuario conectado en la sesion.
			 * 
			 */
			if (base.getCorreo() != null && base.getCorreo().length() > 0) {
				email = base.getCorreo();
			} else {
				if (supervisor != null && supervisor.getEmail() != null && supervisor.getEmail().length() > 0) {
					email = supervisor.getEmail();
				}
			}
		} catch (Exception e) {
			return new Resultado(201, "Ejecución o Base de Datos no existe");
		}

		String ruta_informes = PropertiesUtil.getInstance().recuperaValor("ruta_informes");
		String ruta = pais.getNombre() + 
				"/" + ejecucion.getPeriodo() + 
				"/" + base.getGlosa() +
				"/" + base.getGlosa() + ".xlsx";

		String emailBody = "Su Base >" + base.getGlosa() + "< del período >" + ejecucion.getPeriodo()
				+ "< está disponible para su descarga en: " + " <a href='" + ruta_informes + "?ruta=" + ruta + "' >haz click aquí</a>";

		new EnvioMail().send(email, emailBody);

		log.info("Se envio un email a >"+email+"< con mensaje >"+ emailBody +"<");
		
		return new Resultado("Email enviado");
	}
	
	@GET
	@Path("estado")
	@Produces({ MediaType.TEXT_HTML })
	public Response getPaginaEstado(@Context HttpServletRequest req) {
		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

//			return Response.ok(new Viewable("/index", map)).build();
			return Response.ok(new Viewable("/views/estado")).build();

		} else {

			return Response.ok(new Viewable("/views/estado")).build();
		}

	}

	@GET
	@Path("estado/detalle")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoGeneral getEstado(@QueryParam("inc") int inc, @QueryParam("limit") int limit,
			@QueryParam("desde") String desde, @QueryParam("hasta") String hasta) {

		if (inc <= 0 || limit <= 0) {
			inc = 0;
			limit = 100;
		}
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (desde == null || desde.length() < 8) {
			desde = sdf.format(date)+" 00:00:00";
		}
		if (hasta == null || hasta.length() < 8) {
			hasta = sdf.format(date)+" 23:59:59";
		}
		sdf.setLenient(false);
		try {
			Date f1 = sdf.parse(desde);
			Date f2 = sdf.parse(hasta);
			if (f2.getTime() < f1.getTime()) {
				hasta = desde;
			}
		} catch (Exception e) {
			desde = sdf.format(date);
			hasta = sdf.format(date);
		}

		LocatorDao.getInstance();
		ReportesDao report = LocatorDao.getReportesDao();
		Map<Integer, ResultadoGeneralm> lista = new HashMap<Integer, ResultadoGeneralm>();
		ResultadoGeneral rslt = new ResultadoGeneral();

		lista = report.getResultGeneral(inc, limit, desde, hasta);
		rslt.setLista(lista);
		rslt.setInc(inc);
		rslt.setLimit(limit);
		rslt.setDesde(desde);
		rslt.setHasta(hasta);

		return rslt;

	}
}
