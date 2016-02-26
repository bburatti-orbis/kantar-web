package cl.signosti.kantar.consistencia;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import com.sun.jersey.api.view.Viewable;
import cl.signosti.kantar.consistencia.modelo.Usuariom;

@Path("/KPI")
public class Kpi {
		
	private static final Logger log = Logger.getLogger(Kpi.class);
	
	@GET
	@Path("/kpi_kpi")
	@Produces({ MediaType.TEXT_HTML })
	public Response getKpi(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		System.out.println("por acato");

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom)session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/kpi/listar")).build();
		}
	}
	
	@GET
	@Path("kpi_kpi/crear")
	@Produces({ MediaType.TEXT_HTML })
	public Response getCrear(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		System.out.println("por acato");

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom)session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/kpi/crearKpi")).build();
		}
	}
	
	@POST
	@Path("kpi_kpi/editar")
	@Produces({ MediaType.TEXT_HTML })
	public Response getasignarresponable(@Context HttpServletRequest req, @FormParam(value = "id") String idKpi) {
		HttpSession session = req.getSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		Usuariom user = (Usuariom)session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);
			return Response.ok(new Viewable("/views/index", map)).build();
		} else {
			try{
				req.setAttribute("idKpi", idKpi);
				req.setAttribute("usuario", user);
			}catch(Exception e){
				e.printStackTrace();
				log.error("Error al cargar tarea para asginar responsable", e);
			}
			return Response.ok(new Viewable("/views/kpi/modificarKpi")).build();
		}
	}

}
