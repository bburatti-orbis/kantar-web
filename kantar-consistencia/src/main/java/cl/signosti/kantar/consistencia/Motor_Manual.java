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

@Path("/Motor_manual")
public class Motor_Manual {
	
	private static final Logger log = Logger.getLogger(Motor_Manual.class);
	
	@GET
	@Produces({ MediaType.TEXT_HTML })
	public Response bla(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {	

			return Response.ok(new Viewable("/views/motor_manual")).build();
		}
	}
	
	@GET
	@Path("/motor")
	@Produces({ MediaType.TEXT_HTML })
	public Response getgest_proyec(@Context HttpServletRequest req) {

		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Object user = session.getAttribute("user");
		if (user == null) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/views/index", map)).build();

		} else {	

			return Response.ok(new Viewable("/views/motor_manual")).build();
		}
	}
	
}
