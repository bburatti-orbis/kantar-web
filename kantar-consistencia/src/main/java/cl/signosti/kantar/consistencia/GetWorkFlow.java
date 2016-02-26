package cl.signosti.kantar.consistencia;

import java.io.File;
import java.io.IOException;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.dao.BasesDao;
import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.NomesclaturaDao;
import cl.signosti.kantar.consistencia.dao.PaisesDao;
import cl.signosti.kantar.consistencia.dao.ReportesDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Aream;
import cl.signosti.kantar.consistencia.modelo.Basesm;
import cl.signosti.kantar.consistencia.modelo.Ejecucionesm;
import cl.signosti.kantar.consistencia.modelo.Nomenclaturam;
import cl.signosti.kantar.consistencia.modelo.Paisesm;


import com.google.gson.Gson;
import com.sun.jersey.api.view.Viewable;

@Path("/Work_Flow")
public class GetWorkFlow {

	private static final Logger logger = Logger.getLogger(GetWorkFlow.class);

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

			return Response.ok(new Viewable("/views/pagina2")).build();
		}

	}

	@GET
	@Path("listadatos/")
	@Produces({ MediaType.TEXT_HTML })
	public Response getpaises() {


		LocatorDao.getInstance();
		PaisesDao paises = LocatorDao.getPaisesDao();
		Map<Integer, String> list = paises.getpaises();

		Gson gson = new Gson();
		String resp = gson.toJson(list);

		return Response.ok(resp).build();

	}

	@GET
	@Path("listameasure/")
	@Produces({ MediaType.TEXT_HTML })
	public Response getmeasure() {

		String sDirectorio = "c:\\Measure";
		File f = new File(sDirectorio);
		Map<Integer, String> list = new HashMap<Integer, String>();

		if (f.exists()) {
			File[] ficheros = f.listFiles();
			for (int x = 0; x < ficheros.length; x++) {
				System.out.println();
				list.put(x, ficheros[x].getName());
			}

		} else {

		}
		Gson gson = new Gson();
		String resp = gson.toJson(list);

		return Response.ok(resp).build();

	}

	@GET
	@Path("listabases/")
	@Produces({ MediaType.TEXT_HTML })
	public Response getbases(@QueryParam("pais") String pais) {

		LocatorDao.getInstance();

		PaisesDao paises = LocatorDao.getPaisesDao();
		Paisesm paisb = paises.getbasespaises(Integer.parseInt(pais));

		Gson gson = new Gson();
		String resp = gson.toJson(paisb);

		return Response.ok(resp).build();

	}

	@GET
	@Path("procesarbd")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getproceso(@QueryParam("codigos") String formParams,
			@QueryParam("fecha") String fecha,
			@QueryParam("measure") String measure,
			@QueryParam("pais") String pais) throws IOException, ParseException {

		Date date = new Date();

                
                
		String[] val = formParams.split(",");
		SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd HH:mm");
		for (String va : val) {
			String a = format.format(date);
			int id = 0;
			LocatorDao.getInstance();
			EjecucionesDao ejec = LocatorDao.getEjecucionesDao();
			Ejecucionesm ejecucion = new Ejecucionesm();
			ejecucion.setCod(1);
			ejecucion.setFecha(a);
			int proceso = 1;
			ejecucion.setProceso(proceso);
			;
			ejecucion.setBase(1);
			ejecucion.setPeriodo(fecha + "-01");

			try {
				id = ejec.setejecucion(ejecucion);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
					 logger.error("Error, causa:" ,e);
			}

			Nomenclaturam nomes = new Nomenclaturam();

			LocatorDao.getInstance();
			NomesclaturaDao nom = LocatorDao.getNomenclarurasDao();
//			int id_nomen = 0;
			String ruta = "C:\\Users\\Jose\\Desktop\\Powerview\\Bases\\Chile\\"
					+ fecha + "\\" + va;
			String sDirectorio = ruta;

			File f = new File(sDirectorio);
//			Funciones fun = new Funciones();
			try {
				if (f.exists()) {
					File[] ficheros = f.listFiles();
					for (int x = 0; x < ficheros.length; x++) {
						String[] fich = ficheros[x].getName().split(".",
								ficheros[x].getName().length() - 2);
						String variable = fich[fich.length - 1].toUpperCase();
						if (variable.equals("IT2")) {
							nomes.setGlosa(ficheros[x].getName());
							nomes.setTipo(2);
							nomes.setId_ejecuciones(id);
							try {
							nom.getejecucion(nomes);

							} catch (SQLException e) {
								// TODO Auto-generated catch block
									 logger.error("Error, causa:" ,e);
							}

						}

					}

				} else {

				}
			} catch (Exception e) {
				e.getStackTrace();

			}
		}
		// // int z= ruta2.length();
		Gson gson = new Gson();
		String resp = "holi";
		String d = gson.toJson(resp);
		return Response.ok(d).build();

	}
	
	@GET
	@Path("listabase/")
	@Produces({ MediaType.TEXT_HTML })
	public Response getbase(@QueryParam("pais") String pais) {

		LocatorDao.getInstance();

		BasesDao paises = LocatorDao.getBasesDao();
		List<Basesm> paisb = null;
		try {
			paisb = paises.getbases(Integer.parseInt(pais));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
				 logger.error("Error, causa:" ,e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				 logger.error("Error, causa:" ,e);
		}

		Gson gson = new Gson();
		String resp = gson.toJson(paisb);

		return Response.ok(resp).build();

	}
	@GET
	@Path("listabaseconjunto/")
	@Produces({ MediaType.TEXT_HTML })
	public Response getbaseconjunto(@QueryParam("pais") String pais) {

		LocatorDao.getInstance();

		BasesDao paises = LocatorDao.getBasesDao();
		List<Basesm> paisb = null;
		try {
			paisb = paises.getbasesconjunto(Integer.parseInt(pais));
		} catch (NumberFormatException e) {
	
				 logger.error("Error, causa:" ,e);
		} catch (SQLException e) {

				 logger.error("Error, causa:" ,e);
		}

		Gson gson = new Gson();
		String resp = gson.toJson(paisb);

		return Response.ok(resp).build();

	}
	@GET
	@Path("getarea/")
	@Produces({ MediaType.TEXT_HTML })
	public Response getarea(@QueryParam("cod") int cod) {

		LocatorDao.getInstance();

		ReportesDao report =LocatorDao.getReportesDao();
		List<Aream> listarea = new ArrayList<Aream>();
		try {
			listarea = report.getareas();
		} catch (NumberFormatException e) {
	
				 logger.error("Error, causa:" ,e);
		} catch (SQLException e) {

				 logger.error("Error, causa:" ,e);
		}

		Gson gson = new Gson();
		String resp = gson.toJson(listarea);

		return Response.ok(resp).build();

	}


}
