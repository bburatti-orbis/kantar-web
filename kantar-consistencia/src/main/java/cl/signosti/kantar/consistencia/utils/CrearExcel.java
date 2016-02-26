//package cl.signosti.kantar.consistencia.utils;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//
//
//
//
//
//
//
//import cl.signosti.kantar.consistencia.dao.ReportesDao;
//import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
//import cl.signosti.kantar.consistencia.modelo.Hoja1_2;
//import cl.signosti.kantar.consistencia.modelo.Hoja1m;
//import cl.signosti.kantar.consistencia.modelo.Hoja2m;
//
//public class CrearExcel {
////	private static final Logger logger = Logger.getLogger(CrearExcel.class);
//	ReportesDao ejec = LocatorDao.getReportesDao();
//
//	public File excel(String nombre, int cod_proceso, int cod_ejec, String periodo, String pais)
//			throws IOException, SQLException {
//
//		// lista = report.getdetallenom(c);
//
//		HSSFWorkbook objWB = new HSSFWorkbook();
////		objWB = hoja1(objWB, cod_proceso, cod_ejec);
//
////		objWB = hoja3(objWB);
//
//		// Finalmente, establecemos el valor
//		
//		String ruta_pais=PropertiesUtil.getInstance().recuperaValor("consistencias")+"\\"+pais+"\\"+periodo+"\\"+nombre;
//		
//		File a= new File(ruta_pais);
//		
//		if(!a.exists()){
//			a.mkdirs();
//		}
//		
//		
//		
//		
//		
//		
//		String strNombreArchivo = PropertiesUtil.getInstance().recuperaValor("consistencias")+"\\"+pais+"\\"+periodo+"\\"+ nombre+"\\" +nombre+ ".xls";
//		
//		File objFile = new File(strNombreArchivo);
//		
//		
//		FileOutputStream archivoSalida = new FileOutputStream(objFile);
//		objWB.write(archivoSalida);
//		objWB.close();
//		archivoSalida.close();
//		
//		return objFile;
//		
//
//	}
//
//	public HSSFWorkbook hoja1(HSSFWorkbook objWB, int cod_proceso, int cod_ejec)
//			throws SQLException {
//
//		HSSFSheet hoja1 = objWB.createSheet("Resumen");
//		hoja1.autoSizeColumn((short) 1);
//
//		HSSFRow fila = hoja1.createRow(1);
//		Map<Integer, Hoja1m> lista = new HashMap<Integer, Hoja1m>();
//
//		lista = ejec.hoja1(cod_proceso);
//		Hoja1m b = new Hoja1m();
//		Hoja1m[] vectorv_b = new Hoja1m[lista.size()];
//		Iterator<Integer> iter = lista.keySet().iterator();
//		int t = 0;
//
//		while (iter.hasNext()) {
//			int key = (Integer) iter.next();
//			b = lista.get(key);
//			vectorv_b[t] = b;
//			t++;
//		}
//
//		HSSFRow fila8 = null;
//
//		String[] vector = new String[15];
//		vector[0] = "ID ejecuciones";
//		vector[1] = "Fecha Inicio";
//		vector[2] = "Fecha Fin";
//		vector[3] = "Cant Bases";
//		vector[4] = "Cant. Bases OK";
//		vector[5] = "Cant. Bases NOK";
//		vector[6] = "Paises";
//		vector[7] = "Bases";
//		vector[8] = "Categoria";
//		vector[9] = "Responsable";
//		vector[10] = "Estado CI";
//		vector[11] = "Estado CH";
//		vector[12] = "Periodo Actual";
//		vector[13] = "Periodo Anterior";
//		vector[14] = "Origen Base";
//		short celda1 = 0;
//		short cont = 0;
//		short numfilaA = 2;
//		for (int i = 0; i < vector.length; i++) {
//			if (i <= 5) {
//
//				HSSFCell celda = fila.createCell((short) celda1);
//				celda.setCellValue(vector[i]);
//				celda1++;
//
//			}
//			if (i == 5) {
//				for (int k = 0; k < vectorv_b.length; k++) {
//					HSSFRow filab = hoja1.createRow(numfilaA);
//					
//
//					HSSFCell celdab = filab.createCell(0);
//					celdab.setCellValue(vectorv_b[k].getId());
//					
//					HSSFCell celdab1 = filab.createCell(1);
//					celdab1.setCellValue(vectorv_b[k].getFecha_ini());
//					HSSFCell celdab2 = filab.createCell(2);
//
//					celdab2.setCellValue(vectorv_b[k].getFecha_ter());
//					HSSFCell celdab3 = filab.createCell(3);
//					celdab3.setCellValue(vectorv_b[k].getCanbases());
//					HSSFCell celdab4 = filab.createCell(4);
//					celdab4.setCellValue(vectorv_b[k].getBasesOK());
//					HSSFCell celdab5 = filab.createCell(5);
//					celdab5.setCellValue(vectorv_b[k].getBasesNOK());
//					numfilaA++;
//				}
//				numfilaA = (short) (numfilaA + 2);
//				fila8 = hoja1.createRow((short) numfilaA);
//
//			}
//
//			if (i > 5) {
//
//				HSSFCell celda2 = fila8.createCell((short) cont);
//				celda2.setCellValue(vector[i]);
//				cont++;
//			}
//
//			if (i == 14) {
//				cont = 1;
//				for (int k = 0; k < vectorv_b.length; k++) {
//
//					Map<Integer, Hoja1_2> lista2 = new HashMap<Integer, Hoja1_2>();
//					Hoja1_2 hoja = new Hoja1_2();
//					lista2 = ejec.hoja1_2(cod_ejec);
//
//					hoja = lista2.get(1);
//					Iterator<Integer> iter1 = lista2.keySet().iterator();
//					t = 0;
//
//					while (iter1.hasNext()) {
//						int key = (Integer) iter1.next();
//						hoja = lista2.get(key);
//
//						HSSFRow filab = hoja1.createRow(numfilaA + cont);
//
//						HSSFCell celdab = filab.createCell(0);
//						celdab.setCellValue(hoja.getPais());
//						HSSFCell celda2 = filab.createCell(1);
//						celda2.setCellValue(hoja.getBase());
//						HSSFCell celda3 = filab.createCell(2);
//						celda3.setCellValue(hoja.getCategoria());
//						HSSFCell celda4 = filab.createCell(3);
//						celda4.setCellValue(hoja.getResponsable());
//						HSSFCell celda5 = filab.createCell(4);
//						celda5.setCellValue(hoja.getCI());
//						HSSFCell celda6 = filab.createCell(5);
//						celda6.setCellValue(hoja.getCH());
//						HSSFCell celda7 = filab.createCell(6);
//						celda7.setCellValue(hoja.getPeriodoAc());
//						HSSFCell celda8 = filab.createCell(7);
//						celda8.setCellValue(hoja.getPeriodoAnt());
//						HSSFCell celda9 = filab.createCell(8);
//						celda9.setCellValue(hoja.getOrigen());
//
//						cont++;
//					}
//
//				}
//			}
//
//		}
//
//		objWB = hoja2(objWB, cod_ejec);
//
//		return objWB;
//
//	}
//
//	public HSSFWorkbook hoja2(HSSFWorkbook objWB, int cod) throws SQLException {
//
//		HSSFSheet hoja2 = objWB.createSheet("CI");
//		hoja2.autoSizeColumn((short) 1);
//		HSSFRow fila = hoja2.createRow(1);
//
//		String[] vector = new String[13];
//		vector[0] = "Pais";
//		vector[1] = "Base";
//		vector[2] = "Periodo";
//		vector[3] = "C.H";
//		vector[4] = "Origen Base";
//		vector[5] = "Nom.";
//		vector[6] = "Tipo";
//		vector[7] = "Linea";
//		vector[8] = "N° Linea";
//		vector[9] = "Nivel";
//		vector[10] = "Total Informado";
//		vector[11] = "Total Calculado";
//		vector[12] = "Diferencia";
//
//		for (int i = 0; i < vector.length; i++) {
//
//			HSSFCell celda = fila.createCell((short) i);
//			celda.setCellValue(vector[i]);
//
//		}
//		Map<Integer, Hoja2m> lista2 = new HashMap<Integer, Hoja2m>();
//		Hoja2m hoja = new Hoja2m();
//		lista2 = ejec.hoja2(cod);
//		Iterator<Integer> iter1 = lista2.keySet().iterator();
//		int cont = 2;
//
//		while (iter1.hasNext()) {
//			int key = (Integer) iter1.next();
//			hoja = lista2.get(key);
//
//			HSSFRow filab = hoja2.createRow(cont);
//
//			HSSFCell celdab = filab.createCell(0);
//			celdab.setCellValue(hoja.getPais());
//			HSSFCell celda2 = filab.createCell(1);
//			celda2.setCellValue(hoja.getBase());
//			HSSFCell celda3 = filab.createCell(2);
//			celda3.setCellValue(hoja.getPeriodo());
//			HSSFCell celda4 = filab.createCell(3);
//			celda4.setCellValue(hoja.getCH());
//			HSSFCell celda5 = filab.createCell(4);
//			celda5.setCellValue(hoja.getEstado());
//			HSSFCell celda6 = filab.createCell(5);
//			celda6.setCellValue(hoja.getNomenclatura());
//			HSSFCell celda7 = filab.createCell(6);
//			celda7.setCellValue(hoja.getTipo());
//			HSSFCell celda8 = filab.createCell(7);
//			celda8.setCellValue(hoja.getGlosa());
//			HSSFCell celda9 = filab.createCell(8);
//			celda9.setCellValue(hoja.getLinea());
//			HSSFCell celda10 = filab.createCell(9);
//			celda10.setCellValue(hoja.getNivel());
//			HSSFCell celda11 = filab.createCell(10);
//			celda11.setCellValue(hoja.getTotalinformado());
//			HSSFCell celda12 = filab.createCell(11);
//			celda12.setCellValue(hoja.getTotalcalculado());
//			HSSFCell celda13 = filab.createCell(12);
//			celda13.setCellValue(hoja.getDiferencia());
//			cont++;
//		}
//
//		return objWB;
//
//	}
//
//	public HSSFWorkbook hoja3(HSSFWorkbook objWB) {
//
//		HSSFSheet hoja3 = objWB.createSheet("CH");
//		hoja3.autoSizeColumn((short) 1);
//		HSSFRow fila = hoja3.createRow(1);
//
//		String[] vector = new String[13];
//		vector[0] = "Pais";
//		vector[1] = "Base";
//		vector[2] = "Periodo";
//		vector[3] = "C.I";
//		vector[4] = "Origen Base";
//		vector[5] = "Nom.";
//		vector[6] = "Tipo";
//		vector[7] = "Linea";
//		vector[8] = "Variable";
//		vector[9] = "Periodo Anterior";
//		vector[10] = "Valor P. Anterior";
//		vector[11] = "Valor P. Actual";
//		vector[12] = "Diferencia";
//
//		for (int i = 0; i < vector.length; i++) {
//
//			HSSFCell celda = fila.createCell((short) i);
//			celda.setCellValue(vector[i]);
//
//		}
//
//		return objWB;
//
//	}
//
//}
