package cl.signosti.kantar.consistencia.utils;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.NomesclaturaDao;
import cl.signosti.kantar.consistencia.dao.ReportesDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.EjecucionesCH;
import cl.signosti.kantar.consistencia.modelo.Nomenclaturam;

public class CH {
	private static final Logger logger = Logger.getLogger(CH.class);
	
    Map<Integer, EjecucionesCH> consistencia = new HashMap<Integer, EjecucionesCH>();
    Funcionesvarias fun = new Funcionesvarias();
    EjecucionesDao nomen = LocatorDao.getEjecucionesDao();
    EjecucionesCH ch = new EjecucionesCH();
    EjecucionesCH[] ejecucion;
    int proceso=0;

    public void getConsistenciaH(int pro) throws SQLException {
    	proceso=pro;
        consistencia = nomen.getejecucion();
        int validador = 0;
        ejecucion = new EjecucionesCH[consistencia.size()];
        Iterator<Integer> it = consistencia.keySet().iterator();

        int t = 0;

        while (it.hasNext()) {
            int key = (Integer) it.next();
            ejecucion[t] = consistencia.get(key);

            t++;

        }

        for (int i = ejecucion.length; i > 0; i--) {
            String periodo = corte(ejecucion[i - 1].getPeriodo());
            String pais = ejecucion[i - 1].getPais();
            String base = ejecucion[i - 1].getBase();
            boolean encontrada = false;
            for (int a = 0; a < ejecucion.length; a++) {

                if (periodo.equals(ejecucion[a].getPeriodo()) && ejecucion[a].getBase().equals(base) && ejecucion[a].getPais().equals(pais)) {

                    try {
                        compararnomenclatura(ejecucion[a], ejecucion[i - 1]);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    encontrada = true;
                    break;
                }

            }
            if (!encontrada) {
                validador = 1;
                System.out.println("error en el periodo base no encontrada");
            }
            encontrada = false;

        }

        if (validador == 0) {

        } else {
            System.out.println("Error en la ejecucion");
        }
        
       
		
        
    }

    private String corte(String periodo) {
        String vuelta = null;
        String año = periodo.substring(0, 4);
        String mes = periodo.substring(5, 7);
        int i = Integer.parseInt(mes);
        i = i - 1;

        if (i < 9) {
            vuelta = año + "-0" + i;
        }
        if (i > 9) {
            vuelta = año + "-" + i;
        }

        return vuelta;

    }

    private boolean compararnomenclatura(EjecucionesCH ejecucion1, EjecucionesCH ejecucion2) throws InterruptedException {
    	int k = 0;
        int validador = 0;
        Nomenclaturam[] nomen1 = new Nomenclaturam[ejecucion1.getNomenclatura().size()];
        Iterator<Integer> it = ejecucion1.getNomenclatura().keySet().iterator();
        while (it.hasNext()) {
            int key = (Integer) it.next();
            nomen1[k] = ejecucion1.getNomenclatura().get(key);

            k++;

        }

        int i = 0;

        
        Nomenclaturam[] nomen2 = new Nomenclaturam[ejecucion2.getNomenclatura().size()];
        Iterator<Integer> ite = ejecucion2.getNomenclatura().keySet().iterator();
        while (ite.hasNext()) {
            int key = (Integer) ite.next();
            nomen2[i] = ejecucion2.getNomenclatura().get(key);

            i++;

        }
        boolean encontrada = false;


        ConsultasMDB consultaA= new ConsultasMDB();
        ConsultasMDB consultaB= new ConsultasMDB();
        
        
        String[][] Resultado1 = null;
        String[][] Resultado2=null;

        for (int j = 0; j < nomen1.length; j++) {

            for (int y = 0; y < nomen2.length; y++) {
                if (nomen1[j].getGlosa().equals(nomen2[y].getGlosa())) {
                	
                	String ruta=ejecucion1.getRuta()+ejecucion1.getBase()+"\\tables\\"+nomen1[j].getGlosa().substring(0,nomen1[j].getGlosa().length()-3)+"mdb";
                	
                	File f= new File(ruta);
                	
                	
                	if(!f.exists()){
                		 archivoejecutor(ejecucion1.getRuta(), nomen1[j].getGlosa(), ejecucion1.getBase());
                		
                		
                	}
                	
                	
                	ruta=ejecucion2.getRuta()+ ejecucion2.getBase()+"\\tables\\"+nomen2[y].getGlosa().substring(0,nomen2[y].getGlosa().length()-3)+"mdb";
                	f= new File(ruta);
                	
                	if(!f.exists()){
                		
                		 archivoejecutor(ejecucion2.getRuta(), nomen2[y].getGlosa(), ejecucion2.getBase());
                		
                		
                	}
                	
                	
                	 Resultado1=  consultaA.Coordinador(ejecucion1.getRuta(), nomen1[j].getGlosa(),  ejecucion1.getBase());
                	 Resultado2= consultaB.Coordinador(ejecucion2.getRuta(), nomen2[y].getGlosa(), ejecucion2.getBase());
                
                    String ruta1=ejecucion2.getPais()+"\\"+ejecucion2.getPeriodo();
                    
                    Comparar(Resultado1,Resultado2,ruta1, ejecucion2.getBase(),ejecucion2.getPeriodo(),nomen2[y].getGlosa(),ejecucion2.getPais(),nomen2[y].getTipo());
                    encontrada = true;

                    break;
                }

            }
            if (!encontrada) {
                validador = 1;
                System.out.println("Error en la nomenclatura");
            }
            encontrada = false;

        }

        if (validador == 0) {
        } else {
            System.out.println("Error en la nomenclatura");
        }

        return false;

    }


    public void archivoejecutor(String ruta, String nomenclarura, String base) {
        FileWriter fichero = null;

        String rut = ruta + base;
        PrintWriter pw = null;
        String ROCLCL = "DEFAULT.mea";
        
        String format = "";
        format = "TbItem00" + repeat("\u0000", 5) + "Table_00 " + nomenclarura + repeat("\u0000", 26 - nomenclarura.length()) + "\u00ff\u00ff" + repeat("\u0000", 2) + "Not Specified" + "\u0000"
                + repeat(" ", 19) + repeat("\u0000", 12) + "\u00ff\u00ff\u00ff\u00ff" + repeat("\u0000", 2) + "\02" + repeat("\u0000", 3) + ROCLCL + repeat("\u0000", 13 - ROCLCL.length()) + "\u0012"
                + "\u0000" + "\u00ff\u00ff" + repeat("\u0000", 211) + "MONTHS.ni2" + repeat("\u0000", 3) + "3\u0000\u00ff\u00ff" + repeat("\u0000", 13) + repeat(" ", 4) + repeat("\u0000", 3)
                + repeat(" ", 14) + "\u0000\u00ff\u00ff" + repeat("\u0000", 18) + " \u0000" + repeat(" ", 14) + "\u0000\u00ff\u00ff" + repeat("\u0000", 18) + " \u0000" + repeat(" ", 14)
                + "\u0000\u00ff\u00ff" + repeat("\u0000", 18) + " \u0000" + nomenclarura + repeat("\u0000", 13 - nomenclarura.length()) + "\01" + "\u0000\u00ff\u00ff" + repeat("\u0000", 13) + "    " + repeat("\u0000", 3) + repeat(" ", 14)
                + "\u0000\u00ff\u00ff" + repeat("\u0000", 18) + " \u0000" + repeat(" ", 14) + "\u0000\u00ff\u00ff" + repeat("\u0000", 18) + " \u0000" + repeat(" ", 14)
                + "\u0000\u00ff\u00ff" + repeat("\u0000", 18) + " \u0000" + ROCLCL + repeat("\u0000", 13 - ROCLCL.length()) + "\u0012" + "\u0000\u00ff\u00ff" + repeat("\u0000", 20) + repeat(" ", 14) + "\u0000\u00ff\u00ff" + repeat("\u0000", 18)
                + " \u0000" + repeat(" ", 14) + "\u0000\u00ff\u00ff" + repeat("\u0000", 18) + " \u0000" + repeat(" ", 14) + "\u0000\u00ff\u00ff" + repeat("\u0000", 18) + " "
                + repeat("\u0000", 27) + "`" + "y" + "\u00fe" + "\u00ff" + repeat("\u0000", 50) + "Total" + "\u0000" + repeat(" ", 27) + "All Other" + "\u0000" + repeat(" ", 23) + "\01" + repeat("\u0000", 3);

        try {

            fichero = new FileWriter(ruta + base + "\\specs\\"+nomenclarura.substring(0,nomenclarura.length()-3)+"tab");
            pw = new PrintWriter(fichero);
            pw.write(format);

        } catch (Exception e) {
			 logger.error("Error, causa:" ,e);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
 logger.error("Error, causa:" ,e2);
	
            }
            format = "{0} {0}  0 {1}  0 {0} {0}  0 {1} {2}  0 NOFILTER whtSpez.tmp NOLINK NOLNG  0";
            String texto = MessageFormat.format(format, ruta, base.substring(0, base.length() - 3), nomenclarura.substring(0,nomenclarura.length()-3)+"tab");

            StringBuilder str = new StringBuilder();

            str.append(texto);
            str.append(repeat(" ", 518 - texto.length()) + "\u0000" + "Total" + "\u0000" + "\u0000" + repeat(" ", 27) + repeat("\u0000", 15));

            try {

                fichero = new FileWriter(rut + "\\tables\\ejecutar.tmp");
                pw = new PrintWriter(fichero);
                pw.write(str.toString());

            } catch (Exception e) {
 logger.error("Error, causa:" ,e);
				
            } finally {
                try {
                    if (null != fichero) {
                        fichero.close();
                    }
                } catch (Exception e2) {
	 logger.error("Error, causa:" ,e2);
				

                }

            }
        }

        try {
            ejecutartab(rut,nomenclarura);
        } catch (InterruptedException e) {
          
		 logger.error("Error, causa:" ,e);
			
        }

    }

    private void ejecutartab(String ruta,String nomenclatura) throws InterruptedException {
        File archivo= null;  
        boolean a=false;
        String nom=nomenclatura.substring(0,nomenclatura.length()-3);
        String tiempo = PropertiesUtil.getInstance().recuperaValor("Tiempoejec");
        String comando1 = "START c:\\powrview\\Dexecute.exe " + ruta + "\\tables\\ejecutar.tmp \r\n timeout /nobreak " + tiempo + " \r\n";
        String comando2 = "START c:\\powrview\\rptwrite.exe " + ruta + "\\tables\\"+nom+"tbl \r\n timeout /nobreak " + tiempo + " \r\n";
        String comandoborrar = "del /q " + ruta + "\\tables\\ejecutar.tmp \r\n";

        try {
            boolean res=false;
            String cmd = fun.crearbat(comando1);

            ejecutarcmd(cmd);
            while(!res){
             archivo = new File(ruta + "\\tables\\"+nom+"tbl");
            a = archivo.canRead();
            
            if (a == true) {
                System.out.println("leyo el archivo");

                ejecutarcmd(fun.crearbat(comandoborrar));
                res=true;
            } else {
                System.out.println("voy al tread");
                esperarXsegundos(Integer.parseInt(tiempo));
            }
            }
            
            
            ejecutarcmd(fun.crearbat(comando2));
            res=false;
            String rut=ruta+"\\tables\\"+nom;
            while(!res){
            archivo = new File(ruta + "\\tables\\"+nom+"mdb");
            a = archivo.canRead();
            comandoborrar = "del /q " + rut +"tbl \r\n del /q " + rut +"mat \r\n del /q " + rut +"ldb \r\n"; 
            if (a == true) {
                System.out.println("leyo el archivo");
                ejecutarcmd(fun.crearbat(comandoborrar));
                ejecutarcmd("tskill rptwrite");
                res=true;

            } else {
                System.out.println("voy al tread");
                esperarXsegundos(Integer.parseInt(tiempo));
            }
            }
        } catch (IOException ioe) {
		 logger.error("Error, causa:" ,ioe);
					
        }

    }

    private String repeat(String str, int n) {

        StringBuilder resp = new StringBuilder();
        for (int i = 0; i < n; i++) {
            resp.append(str);
        }
        return resp.toString();

    }

    private void ejecutarcmd(String comando) {
        String osName = System.getProperty("os.name");
        String system = "";
        if (osName.toUpperCase().contains("WIN")) {   //Windows
            system += comando;
        } else {                                    //Linux
            system += comando;
        }
        Process hijo;
        try {
            hijo = Runtime.getRuntime().exec(system);
            hijo.waitFor();
            if (hijo.exitValue() == 0) {
                System.out.println("ejecutado");
            } else {
                System.out.println("no ejecutado " + hijo.exitValue());
            }
        } catch (IOException e) {
            System.out.println("Incapaz de matar soffice.");
            logger.error("Error, causa:" +
					 e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Incapaz de matar soffice.");
            logger.error("Error, causa:" +
					 e.getMessage());
        }

    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    
    
    private void Comparar(String[][] resultado1,String[][] resultado2, String ruta1, String base, String periodo, String nomenclatura, String pais, int tipo){
//    	String ruta = PropertiesUtil.getInstance().recuperaValor("consistencias");
    	 LocatorDao.getInstance();
		ReportesDao report = LocatorDao.getReportesDao();
		NomesclaturaDao alter = LocatorDao.getNomenclarurasDao();
		EjecucionesDao ejec= LocatorDao.getEjecucionesDao();
    	 int[] codigos= new int[2];
    	 boolean validador= false;
    	 try {
			codigos=report.getCodigosCH(proceso, nomenclatura, periodo, base);
		} catch (SQLException e) {
		 logger.error("Error, causa:" ,e);
					
		}
    	 
    	 String tip= null;
    	 
    	 if(tipo==2){
    		 tip="IT2";
    	 }
    	 else{
    		 tip="IT0";
    	 }
    	
//    	ruta+=ruta1+"\\"+base+"\\"+base+"CH.xls";
    	
    	String[] matriz = new String[12];
    	
    	Map<Integer,String[]> map= new HashMap<Integer, String[]>();
    	
    	int guia= 0;
    	boolean nomen= true;
    	
    	for(int i=1; i<resultado1.length;i++){
    		for(int j=1;j<resultado2[0].length;j++){
    			
    			if(resultado1[i][j].equals(resultado2[i][j])){
    				if(nomen){
    					
    				}
    				
    			}
    			else{

    					
    					
    					matriz[0]=pais;
    					matriz[1]=base;
    					matriz[2]=periodo;
    					matriz[3]="";
    					matriz[4]=nomenclatura;
    					matriz[5]=tip;
    					matriz[6]="";
    					matriz[7]=resultado1[0][j];//buscar la variable a mostrar 
    					matriz[8]=periodo;
    					matriz[9]=resultado1[i][j];
    					matriz[10]=resultado2[i][j];
    					String sum1= resultado1[i][j];
    					String sum2= resultado2[i][j];
    					Float a= Float.parseFloat(sum1)- Float.parseFloat(sum2);
    					matriz[11]=Float.toString(a);
    					nomen= false;		
    					map.put(guia, matriz);
    					
    					guia++;

						validador=true;

    			}
    			
    		}
    	}
    	
    	
    	if(nomen){
    		try {
				alter.setErrornomenclaturaCH(codigos[1],3);
			} catch (SQLException e) {
 logger.error("Error, causa:" ,e);
						
			}
    	}
    	else{
			try {
				ejec.setErrorejecucionCH(codigos[0],4);
			} catch (SQLException e) {
 logger.error("Error, causa:" ,e);
				
			}
			try {
				alter.setErrornomenclaturaCH(codigos[1],4);
			} catch (SQLException e) {
 logger.error("Error, causa:" ,e);
					
			}
    	}
    	
    	
    	
    	if(!validador){
    		try {
				ejec.setErrorejecucionCH(codigos[0],3);
			} catch (SQLException e) {
 logger.error("Error, causa:" ,e);
				
			}
    	
    	}
    	
////    	ExcelCH ex= new ExcelCH();
//    	try {
////			ex.excel(ruta, map);
//		} catch (SQLException e) {
//		 logger.error("Error, causa:" ,e);
//			
//		}catch(IOException e){
//		 logger.error("Error, causa:" ,e);
//					
//    }
        
    	
    	
    }
    
    
    
    
    

}
