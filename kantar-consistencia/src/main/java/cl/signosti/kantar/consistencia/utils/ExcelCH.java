//package cl.signosti.kantar.consistencia.utils;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.SQLException;
//
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
//public class ExcelCH {
//
//
//	public File excel(String ruta, Map<Integer,String[]> map) throws IOException, SQLException {
//
//		// lista = report.getdetallenom(c);
//
//		HSSFWorkbook objWB = new HSSFWorkbook();
//
//
//
//		String strNombreArchivo = ruta;
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
//	
//
//	public HSSFWorkbook hoja3(HSSFWorkbook objWB, Map<Integer, String[]> map) {
//		short numfilaA = 2;
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
//		int k=0;
//		for(int i=1;i<11;i++){
//			
//			HSSFCell celda = fila.createCell((short) i);
//			celda.setCellValue(vector[i]);
//			
//		}
//		String [] CH= new String[11];
//		
//        Iterator<Integer> it =map.keySet().iterator();
//        while (it.hasNext()) {
//            int key = (Integer) it.next();
//            CH = map.get(key);
//            	for(int i=1;i<11;i++){
//            		HSSFRow filab = hoja3.createRow(numfilaA);
//            		
//        			HSSFCell celdab = filab.createCell(k);
//					celdab.setCellValue(CH[k]);
//					
//    			numfilaA ++;
//    			
//            	}
//    		
//			
//            k++;
//            
//            if(k==11){
//            	k=0;
//            }
//
//        }
//
//			
//			
//
//		
//
//		return objWB;
//
//	}
//
//}
