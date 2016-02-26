package cl.signosti.kantar.consistencia;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.ReportesDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.DetalleNomesclm;
import cl.signosti.kantar.consistencia.modelo.ReportNomenm;
import cl.signosti.kantar.consistencia.modelo.ResultadoGeneral;
import cl.signosti.kantar.consistencia.modelo.ResutadoGeneralm;
import cl.signosti.kantar.consistencia.modelo.Usuariom;
import cl.signosti.kantar.consistencia.utils.PropertiesUtil;

import com.google.gson.Gson;
import com.sun.jersey.api.view.Viewable;

@Path("/Reportes")
public class Reportes {
	
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
	public ResultadoGeneral getreportg(@QueryParam("inc") int inc, @QueryParam("limit") int limit) {

		LocatorDao.getInstance();
		ReportesDao report = LocatorDao.getReportesDao();
		Map<Integer, ResutadoGeneralm> lista = new HashMap<Integer, ResutadoGeneralm>();
		ResultadoGeneral rslt = new ResultadoGeneral();
		
		if(inc <= 0){
			inc = 0;
		}
		if(limit <= 0){
			limit = 100;
		}
		
		lista = report.getResultGeneral(inc, limit);
		rslt.setLista(lista);
		rslt.setInc(inc);
		rslt.setLimit(limit);

//		Gson gson = new Gson();
//		String resp = gson.toJson(lista);
//
//		return Response.ok(resp).build();
		
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

//		Gson gson = new Gson();
//		String resp = gson.toJson(lista);
//
//		return Response.ok(resp).build();
		
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

}
