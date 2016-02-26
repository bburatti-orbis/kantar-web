package cl.signosti.kantar.consistencia.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class Funcionesvarias {
	private static final Logger logger = Logger.getLogger(Funcionesvarias.class);
	public String fecha() {
		String fecha = null;
		Date date = new Date();
		// Caso 1: obtener la hora y salida por pantalla con formato:
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		System.out.println("Hora: " + hourFormat.format(date));
		// Caso 2: obtener la fecha y salida por pantalla con formato:
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Fecha: " + dateFormat.format(date));
		// Caso 3: obtenerhora y fecha y salida por pantalla con formato:
		DateFormat hourdateFormat = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");

		fecha = hourdateFormat.format(date);

		return fecha;

	}
	
	public String crearbat(String parametro) throws IOException{
		
		
		String cabecera="@ECHO OFF";
		
		String fin="exit";
		String ruta = PropertiesUtil.getInstance().recuperaValor("temporal");
		File archivo = new File(ruta+"\\ejecucion.bat");
		BufferedWriter bw;
		
		if(archivo.exists()) {
			 bw = new BufferedWriter(new FileWriter(archivo));
			 PrintWriter wr = new PrintWriter(bw); 
			 wr.write(cabecera +" \r\n");
			 wr.write(parametro);
			 wr.write(fin+" \r\n");
			 wr.close();
			 bw.close();
		} else {
			 bw = new BufferedWriter(new FileWriter(archivo));
			 PrintWriter wr = new PrintWriter(bw); 
			 wr.write(cabecera);
			 wr.write(parametro);
			 wr.write(fin);
			 wr.close();
			 bw.close();
		}
		return ruta+"\\ejecucion.bat";
		
		
	}
	
    public void copiaarchivo(String destino, String base) {

        String ruta = PropertiesUtil.getInstance().recuperaValor("Medidas");

        File f = new File(ruta);
        File[] ficheros = f.listFiles();
        for (int i = 0; i < ficheros.length; i++) {
            Path FROM = Paths.get(ficheros[i].getAbsolutePath());
            Path TO = Paths.get(destino+"\\" + base + "\\specs\\" + ficheros[i].getName());
        //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
            try {
                Files.copy(FROM, TO, options);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                	 logger.error("Error, causa:" ,e);
            }
        }
    }
    public String repeat(String str, int n) {

        StringBuilder resp = new StringBuilder();
        for (int i = 0; i < n; i++) {
            resp.append(str);
        }
        return resp.toString();

    }


}
