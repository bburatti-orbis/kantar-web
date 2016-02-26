package cl.signosti.kantar.consistencia.utils;

import java.util.Iterator;
import java.util.Map;

import cl.signosti.kantar.consistencia.modelo.Marcam;

public class ValidaNomenclaturas {
	
	
	
	public boolean validacion(Map<Integer, Marcam> marc1, Map<Integer, Marcam> marc2){
		boolean retorno = false;
		
        int k = 0;
       
        Marcam[] marca1 = new Marcam[marc1.size()];
        Iterator<Integer> it = marc1.keySet().iterator();
        
        while (it.hasNext()) {
            int key = (Integer) it.next();
            marca1[k] = marc1.get(key);

            k++;

        }

        int i = 0;
        Marcam[] marca2 = new Marcam[marc2.size()];
        Iterator<Integer> ite = marc2.keySet().iterator();
        
        while (ite.hasNext()) {
            int key = (Integer) ite.next();
            marca2[i] = marc2.get(key);
            i++;

        }
		
        
        if(marca1.length==marca2.length){

            for(int j=0;j<marca1.length;j++){
            	
            	
            	if((marca1[j].getLinea()== marca2[j].getLinea() ) && (marca1[j].getGlosa().equals(marca2[j].getGlosa()))){
            		retorno=true;
            	}
            	else{
            	}
            	
        	
        }
        }
        else{
        	retorno=false;
        }
        

        	 	
        	
        
		
		
		
		
		return retorno;
		
	}
	

}
