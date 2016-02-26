package cl.signosti.kantar.consistencia;


import java.net.URISyntaxException;
import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


import cl.signosti.kantar.consistencia.dao.UsuariosDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Usuariom;

import com.sun.jersey.api.view.Viewable;

@Path("/")
public class MyRoot {
	private static final Logger logger = Logger.getLogger(MyRoot.class);

	@GET
	@Produces({ MediaType.TEXT_HTML })
	public Response getIt() {
		return Response.ok(new Viewable("/index")).build();
	}

	@POST
	@Path("login")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response crea_user(@Context HttpServletRequest req,
			MultivaluedMap<String, String> formParams) throws SQLException,
			URISyntaxException {
		String user = formParams.getFirst("correo");
		String pass = formParams.getFirst("pass");
		HttpSession session = req.getSession(true);
		if (user.equals("")) {
			logger.info("Error de Login >Falta User<");
			session = null;

		} else if (pass.equals("")) {
			logger.info("Error de Login >Falta Pass<");
			session = null;

		} 

		LocatorDao.getInstance();
		UsuariosDao ldm = LocatorDao.getusuariodao();
		Usuariom lista = ldm.getUsuario(user, pass);

		if (lista.getId() == 0) {
			logger.info("Error de Login >Cliente Incorrecto <");
		} else {
			logger.info("Error de Login >Cliente correcto <");	
			session.setAttribute("user", lista);

		}
		java.net.URI location = new java.net.URI("Reportes");
		return Response.seeOther(location).build();

	}

	@GET
	@Path("user")
	@Produces({ MediaType.TEXT_HTML })
	public Response listauser() {
		return Response.ok(new Viewable("/views/listausuarios")).build();
	}

	@GET
	@Path("selector")
	@Produces({ MediaType.TEXT_HTML })
	public Response selector() {
		return Response.ok(new Viewable("/views/selector")).build();
	}

}
