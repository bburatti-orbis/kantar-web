package cl.signosti.kantar.consistencia;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

import cl.signosti.kantar.consistencia.modelo.ResultadoGeneral;
import cl.signosti.kantar.consistencia.utils.EnvioMail;
import cl.signosti.kantar.consistencia.utils.PropertiesUtil;

@Path("/Correo")
public class Correos {

	@GET
	@Produces({ MediaType.TEXT_HTML })
	public Response getIt(){
		return Response.ok(new Viewable("/views/pruebaCorreo")).build();
	}
	
	@GET
	@Path("/send")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoGeneral getSend(@QueryParam("body") String body){
		ResultadoGeneral rslt = new ResultadoGeneral();
		EnvioMail envioMail = new EnvioMail();
		
		String email = PropertiesUtil.getInstance().recuperaValor("encargado");
		String envio = envioMail.send(email, body);
		if (envio != null){
			// Algo fallo
			rslt.set_mensaje(envio);
			rslt.set_rslt("Error");
		} else {
			rslt.set_mensaje("Mensaje enviado");
			rslt.set_rslt("OK");
		}
		return rslt;
	}
}
