package cl.signosti.kantar.consistencia;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.api.view.Viewable;

import cl.signosti.kantar.consistencia.modelo.Usuariom;


@Path("/Monitor_workflow")
public class MonitorWorkflow {
	
	private static final Logger log = Logger.getLogger(MonitorWorkflow.class);
	
	@GET
	@Path("/statusProyecto")
	@Produces({ MediaType.TEXT_HTML })
	public Response getStatus(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom)session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/graficos/graficos")).build();
		}
	}
	
	@GET
	@Path("/perforEjecutivo")
	@Produces({ MediaType.TEXT_HTML })
	public Response getPerformance(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom)session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/graficos/pEjecutivoArea")).build();
		}
	}
	
	@GET
	@Path("/revisarPlazos")
	@Produces({ MediaType.TEXT_HTML })
	public Response getPlazos(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom)session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/graficos/revisarPlazos")).build();
		}
	}
	
	@GET
	@Path("/prueba")
	@Produces({ MediaType.TEXT_HTML })
	public Response getPrueba(@Context HttpServletRequest req) {
		
		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom)session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/graficos/graficos")).build();
			
		}
	}
	
}
