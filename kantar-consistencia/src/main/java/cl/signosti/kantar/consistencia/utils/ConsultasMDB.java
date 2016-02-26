/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.signosti.kantar.consistencia.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;  
import java.util.Map;

import org.apache.log4j.Logger;


/**
 *
 * @author Jose
 */
public class ConsultasMDB {
	private static final Logger logger = Logger.getLogger(ConsultasMDB.class);
	
	
	public String[][] Coordinador(String ruta,String nomenclatura, String base){
		String espacio= "\u0020\u0020";
		String nomen=nomenclatura.substring(0,nomenclatura.length()-4);

		
		
		
		String espa=repeat("\u0020",8-nomen.length()+1);
		conectar(ruta+base+"\\tables\\"+nomen+".mdb");
		 Map<Integer, String> volumen = new HashMap<Integer, String>();
		 
		String sqlvol= "Select * from VOLUME"+espacio;
		
		try {
			volumen=consultavol(sqlvol);
		} catch (IOException e) {
		 logger.error("Error, causa:" ,e);
					
		}
  	  	String sqlmonths="SELECT Count(LineNum) FROM MONTHS"+espacio;
  	  	int ultmonth=0;
  	  	int ancho= volumen.size();
  	  	
  	  	try {
  	  	ultmonth=	consultamonths(sqlmonths);
		} catch (IOException e) {
		 logger.error("Error, causa:" ,e);
				
		}
  	  	
		
		try{
			
		 Integer.parseInt(nomen);
			nomen="Z_"+nomen;
			
		}catch(Exception e){
			
			
		}
  	  	
  	  	 sqlmonths="SELECT Count(LineNum) FROM "+nomen+espa;
  	  	int largo=0;
  	  
  	  	
  	  	try {
  	  	largo=	consultamonths(sqlmonths);
		} catch (IOException e) {
		 logger.error("Error, causa:" ,e);
					
		}
  	  	
  	  	
  	  	
  	  	
  	  	String[] lineas= new String[ancho];
        Iterator<Integer> it =volumen.keySet().iterator();
        int k=0;
        while (it.hasNext()) {
            int key = (Integer) it.next();
            lineas[k] = volumen.get(key);

            k++;

        }
        String sql = " SELECT a.Codes as linea";
        String [][] resultado=new String[largo+1][ancho+1];
        resultado[0][0]="Linea";
        for(int i=0; i<lineas.length; i++){
        	sql+=",b."+lineas[i].replace(" ", "_")+espacio;
        	
        	if(i<ancho+1){
        	
        	resultado[0][i+1]=lineas[i];
        	}
        	
        }
        sql+= " from MATRIX b,"+nomen+espa+" a where b.MONTHS"+espacio+"="+ultmonth +" and b."+nomen+espa+"=a.LineNum";
        
        
        
        try {
        	resultado=	consultamatrix(sql,resultado,ancho);
		} catch (IOException e) {
		 logger.error("Error, causa:" ,e);
					
		}
        
        desconectar();
        
		
		
		return resultado;
		
	}
	
	

   private Connection conn = null;   
   
   
    public void conectar(String ruta) {
      try{
    	  Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
    	   conn = DriverManager.getConnection("jdbc:ucanaccess://"+ruta);
    	   
         //si la conexion tuvo exito
          if (conn!=null){
        	  System.out.println("Conexion Exitosa");
          }

      }catch(SQLException e){
		 logger.error("Error, causa:" ,e);
					
      }catch(Exception e){
		 logger.error("Error, causa:" ,e);
					
      }
   }


   public void desconectar(){
        try {
            conn.close();            
            System.out.println("La conexion a la base de datos a terminado");
        } catch (SQLException ex) {
		 logger.error("Error, causa:" ,ex);
					
        }       
   }


   
   public Map<Integer, String> consultavol(String sql) throws IOException
   {
	   Map<Integer, String> vuelta = new HashMap<Integer, String>();
    try{
    	

  	PreparedStatement est;
	
 	est= conn.prepareStatement(sql);
     ResultSet res = est.executeQuery();  

     
     while(res.next()){
    	 vuelta.put(res.getInt(1), res.getString(2));
     }
        
    }catch( SQLException e ){
	 logger.error("Error, causa:" ,e);
				
   }
	return vuelta;

    
   }
   
   
   public int consultamonths(String sql) throws IOException
   {
	   int resultado=0;
    try{
    	

  	PreparedStatement est;

 	est= conn.prepareStatement(sql);
     ResultSet res = est.executeQuery();  
   
     while(res.next()){
     	resultado= res.getInt(1);
     }
        
    }catch( SQLException e ){
	 logger.error("Error, causa:" ,e);
				
   }
	
	return resultado;

    
   }
   
   public String[][] consultamatrix(String sql, String[][] resultado, int ancho) throws IOException
   {
	  
    try{
    	

  	PreparedStatement est;

 	est= conn.prepareStatement(sql);
     ResultSet res = est.executeQuery();  
   int x=1;
     while(res.next()){
    	 
    	 for (int i=1;i<=ancho+1; i++){
    		 resultado[x][i-1]=res.getString(i);
    	 }
     	x++;
     }
        
    }catch( SQLException e ){
	 logger.error("Error, causa:" ,e);
				
   }
	
	return resultado;
   }
 
   
   
   private String repeat(String str, int n) {

       StringBuilder resp = new StringBuilder();
       for (int i = 0; i < n; i++) {
           resp.append(str);
       }
       return resp.toString();

   }
   
}
