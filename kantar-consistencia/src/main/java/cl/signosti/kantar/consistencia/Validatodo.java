package cl.signosti.kantar.consistencia;


import java.sql.SQLException;
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


import cl.signosti.kantar.consistencia.modelo.Usuariom;
import cl.signosti.kantar.consistencia.utils.CH;

import cl.signosti.kantar.consistencia.utils.ValidaRutas;

import com.sun.jersey.api.view.Viewable;

@Path("/selector")
public class Validatodo {
	private int cod = 0;
	private static final Logger logger = Logger.getLogger(MyRoot.class);
	String usuario;

	@GET
	@Produces({ MediaType.TEXT_HTML })
	public Response getIt(@Context HttpServletRequest req) {
		HttpSession session = req.getSession(true);

		Map<String, Object> map = new HashMap<String, Object>();

		Usuariom user = (Usuariom) session.getAttribute("user");

		if (user.equals(null)) {
			map.put("cod", 1);

			return Response.ok(new Viewable("/index", map)).build();

		} else {

			return Response.ok(new Viewable("/views/selector")).build();
		}

	}

	@GET
	@Path("valida/")
	@Produces({ MediaType.TEXT_HTML })
	public Response getpaises(@Context HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		Usuariom user = (Usuariom) session.getAttribute("user");
        


		ValidaRutas valid = new ValidaRutas();

		String ruta2[] = valid.pais(user.getId(), user.getEmai());

		try {
			cod= valid.periodo(ruta2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				 logger.error("Error, causa:" ,e);
		}

		CH consistencia = new CH();
		try {
			consistencia.getConsistenciaH(cod);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				 logger.error("Error, causa:" ,e);
		}
		

		valid.terminoproceso();
		logger.info("termine :D");
		String o = "ok";
		return Response.ok(o).build();

	}

}
