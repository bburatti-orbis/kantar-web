package cl.signosti.kantar.consistencia.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.Service.ModFunciones;
import cl.signosti.kantar.consistencia.dao.BasesDao;
import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.NomesclaturaDao;
import cl.signosti.kantar.consistencia.dao.PaisesDao;
import cl.signosti.kantar.consistencia.dao.ProcesoDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Basesm;
import cl.signosti.kantar.consistencia.modelo.Ejecucionesm;
import cl.signosti.kantar.consistencia.modelo.Nomenclaturam;
import cl.signosti.kantar.consistencia.modelo.Paisesm;
import cl.signosti.kantar.consistencia.modelo.Procesom;

public class ValidaRutas {
	private static final Logger logger = Logger.getLogger(ValidaRutas.class);
	
	private int proceso1 = 0;
	private int coduser = 0;
	String correo = null;
	String periodo =null;
	String pais=null;

	public String[] pais( int user, String mail) {
		coduser = user;

		correo = mail;
		
		String ruta = null;
		Paisesm pais = new Paisesm();
		LocatorDao.getInstance();
		PaisesDao ejec = LocatorDao.getPaisesDao();
		Map<Integer, Paisesm> lista = ejec.getlistapaises();
		Paisesm[] paises = new Paisesm[lista.size()];
		Iterator<Integer> it = lista.keySet().iterator();

		int t = 0;
		while (it.hasNext()) {
			int key = (Integer) it.next();
			pais = lista.get(key);
			paises[t] = pais;
			t++;
		}

		t = 0;

		ruta = PropertiesUtil.getInstance().recuperaValor("Rutas_Bases");

		File f = new File(ruta);
		File[] ficheros = f.listFiles();
		String[] ruta2 = new String[ficheros.length];
		boolean existe = false;
		for (int q = 0; q < paises.length; q++) {

			for (int w = 0; w < ruta2.length; w++) {

				if (paises[q].getNombre().equals(ficheros[w].getName())) {
					existe = true;
					break;
				}

			}
			if (existe == false) {
			}

		}

		for (int x = 0; x < ficheros.length; x++) {
			ruta2[x] = "";
			ruta2[x] += ruta + "\\" + ficheros[x].getName();
			for (int k = 0; k < paises.length; k++) {

				String p = ficheros[x].getName();
				if (paises[k].getNombre().equals(p)) {
					existe = true;
					break;

				}

			}
			if (!existe) {
			}
			existe = false;

		}

		return ruta2;
	}

	public int  periodo(String[] ruta2) throws SQLException {
		File f;
		String ruta;

		for (int a = 0; a < ruta2.length; a++) {
			f = null;
			f = new File(ruta2[a]);
			 pais= f.getName();
			File[] ficheros = f.listFiles();

			for (int i = 0; i < ficheros.length; i++) {
				ruta = ruta2[a] + "\\" + ficheros[i].getName();
				 periodo = ficheros[i].getName();
				bases(ruta, periodo);
			}

		}
		return proceso1;

	}

	private void bases(String ruta, String periodo) throws SQLException {

		int idbase = 0;
		boolean existe = false;
		File f2 = new File(ruta);
		File[] ficheros1 = f2.listFiles();
		LocatorDao.getInstance();
		BasesDao bas = LocatorDao.getBasesDao();
		LocatorDao.getInstance();
		PaisesDao ejec = LocatorDao.getPaisesDao();
		Map<Integer, Basesm> listbases = bas.getlistapaises();
		Basesm[] vectorv_b = new Basesm[listbases.size()];
		boolean fech = false;
		int t = 0;
		int id_b = 0;
		String p;
		int ult = 0;
		Basesm b = new Basesm();
		Iterator<Integer> iter = listbases.keySet().iterator();

		while (iter.hasNext()) {
			int key = (Integer) iter.next();
			b = listbases.get(key);
			vectorv_b[t] = b;
			t++;
		}

		for (int l = 0; l < ficheros1.length; l++) {



			String fecha_mod = fechaModificacion(ficheros1[l]);

			for (int k = 0; k < vectorv_b.length; k++) {
				p = ficheros1[l].getName();

				if (vectorv_b[k].getGlosa().equals(p) && vectorv_b[k].getFechamod().equals(fecha_mod)) {
					existe = true;
					fech = true;
					ult = k;
					break;

				}
			}
			p = ficheros1[l].getName();

			if (!existe) {
				String[] texto = ruta.split("\\\\");
				Basesm v2 = new Basesm();
				int id = ejec.getcodpais(texto[texto.length - 2]);
				v2.setPaisid(id);
				v2.setGlosa(p);
				v2.setEstado(1);
				v2.setFechamod(fecha_mod);

				v2.setCoduser(coduser);

				id_b = bas.setbase(v2);

			}

			existe = false;
			if (id_b >= 0) {
				idbase = id_b;
			} else {
				idbase = vectorv_b[ult].getId();
			}

			if (!fech) {
				int id = ejecuciones(ruta, p, periodo, idbase);				
				Funcionesvarias fun=new Funcionesvarias();
				fun.copiaarchivo(ruta, p);
				EnvioMail email = new EnvioMail();
				email.envio(idbase, id, proceso1, correo);
			}
			fech = false;
		}
		//
	}

	private int ejecuciones(String ruta, String nombre, String periodo,
			int idbase) {
		if(proceso1==0){
			proceso1=creaproceso();	
		}
		
		
		Calendar calendario = GregorianCalendar.getInstance();
		Date fecha = calendario.getTime();
		System.out.println(fecha);
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String day = formatoDeFecha.format(fecha);
		int id_nomen = 0;

		// SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		// // String fecha=formateador.format(ahora);
		File f8 = new File(ruta + "\\" + nombre);
		File[] ficheros3 = f8.listFiles();
		LocatorDao.getInstance();
		EjecucionesDao ejecuciones = LocatorDao.getEjecucionesDao();
		Ejecucionesm ej = new Ejecucionesm();
		ej.setBase(idbase);
		ej.setPeriodo(periodo);
		ej.setFecha(day);
		ej.setCI(3);
		ej.setProceso(proceso1);
		
		int id = 0;
		int contador = 0;
		try {
			id = ejecuciones.setejecucion(ej);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		Funciones fun = new Funciones();
		Nomenclaturam nomes = new Nomenclaturam();
		LocatorDao.getInstance();
		NomesclaturaDao nom = LocatorDao.getNomenclarurasDao();
		String variable = null;

		for (int w = 0; w < ficheros3.length; w++) {
			String[] fich = ficheros3[w].getName().split(".",
					ficheros3[w].getName().length() - 2);
			try {
				variable = fich[fich.length - 1].toUpperCase();
			} catch (Exception e) {

				System.out.print("me cai " + contador);
				contador++;
			}
			if (variable.equals("IT2")) {
				nomes.setGlosa(ficheros3[w].getName());
				nomes.setTipo(2);
				nomes.setEstadocinterna(3);
				nomes.setId_ejecuciones(id);
				try {
					id_nomen = nom.getejecucion(nomes);

				} catch (SQLException e) {
					
						 logger.error("Error, causa:" ,e);
				}

				try {
					 fun.procesarIT2(ficheros3[w] + "", id_nomen, id);
				} catch (IOException e) {
			
						 logger.error("Error, causa:" ,e);
				}

			} else if (variable.equals("IT0")) {
				ModFunciones mod = new ModFunciones();
				nomes.setGlosa(ficheros3[w].getName());
				nomes.setTipo(0);
				nomes.setEstadocinterna(3);
				nomes.setId_ejecuciones(id);
				try {
					id_nomen = nom.getejecucion(nomes);
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				try {
					String mon = f8.getPath() + "\\MONTHS.nv0";
					mod.ejecutarVerificacion(ficheros3[w], id_nomen, mon, id);
					
			
				} catch (IOException e) {
					
						 logger.error("Error, causa:" ,e);
				} 
			}

		}
		
//		CrearExcel ex= new CrearExcel();
//		
//		try {
//			ex.excel(nombre ,proceso1,id,periodo,pais);
//		} catch (IOException e) {
//		
//				 logger.error("Error, causa:" ,e);
//		} catch (SQLException e) {
//			
//				 logger.error("Error, causa:" ,e);
//		}
//		

		return id;

	}

	public String fechaModificacion(File fichero) {

		long ms = fichero.lastModified();
		Date d = new Date(ms);
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));

		String annio = Integer.toString(c.get(Calendar.YEAR));
		String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		String minuto = Integer.toString(c.get(Calendar.MINUTE));
		String segundo = Integer.toString(c.get(Calendar.SECOND));
		int mm = 1 + Integer.parseInt(mes);
		String cero = "";
		if (mes.length() == 1) {
			cero = "0";

		}
		if (dia.length() == 1) {
			dia = "0" + dia;
		}
		if (segundo.length() == 1) {
			segundo = "0" + segundo;
		}
		if(minuto.length() == 1){
			minuto= "0"+ minuto;
		}

		String fecha = annio + "-" + cero + mm + "-" + dia + "  " + hora + ":"
				+ minuto + ":" + segundo;
		return fecha;

	}
	
	
	
	private int  creaproceso(){
		Procesom pro = new Procesom();
		Funcionesvarias funcion = new Funcionesvarias();
		String fecha = funcion.fecha();

		pro.setFecha_inicio(fecha);
		pro.setEstado(6);
		pro.setMessureset("MONTHS.nv0");
	

		LocatorDao.getInstance();
		ProcesoDao proceso = LocatorDao.getProcesoDao();
		int cod = proceso.setproceso(pro);
		return cod;
	}
	
	public void terminoproceso(){
		LocatorDao.getInstance();
		ProcesoDao proceso = LocatorDao.getProcesoDao();
		
		Funcionesvarias funcion = new Funcionesvarias();
		String fecha = funcion.fecha();
		proceso.setfin(proceso1, fecha);
	}
	
	

}
