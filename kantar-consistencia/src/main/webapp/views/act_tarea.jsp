<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
<title>Kantar VLD</title>
<link href="../css/estilos.css" rel="stylesheet">
<link href="../css/jquery.modal.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery-1.9.1.js"></script>
<link href="../css/menu.css" rel="stylesheet">
<script type="text/javascript" src="../js/menu.js" ></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";
	

	</script>
</head>

<body>
<div id="wrap">
	<header>
    	<div class="logo"><a href="index.cl"><img src="../img/logo-kantar.png" width="191" height="21" title="Kantar VLD"></a></div>
    </header>
    
    <div class="contenedor_general">
    	<nav>
<jsp:include page="menu.jsp" flush="true"/>
        </nav>
        
        <hr></hr>
        
  <div class="titulo"> <div class="controles_fila">
  	<div class="tit_control"><p>Actualiza Tarea</p> </div>
   </div>
      </div>
<!--       <div class="controles_fila2"> -->
<!--   	<div class="tit_control"><p>Datos Proyecto</p> </div> -->

<!--       </div> -->
   
        <div class="controles_fila">
        	<div class="control2">
            	<div class="tit_control"><p>Proyecto</p></div>
                <div class="llama_pais"><p > Proyecto A</p></div>
   	  		</div>
            
            <div class="control2">
            	<div class="tit_control"><p>Pais</p></div>
                <div class="llama_base"><p> Pais 1</p></div>
        	</div>
<!--      </div> -->
<!--       <div class="controles_fila"> -->
            <div class="control2">
            	<div class="tit_control"><p>Producción</p></div>
                <div class="llama_base"><p> Marzo 2014</p></div>
        	</div>
            
            
            <div class="control2">
            	<div class="tit_control2"><p>Fecha Produccion</p></div>
                <div class="fecha_proyecto"><p > 01/04/2015 - 01/05/2015 </p></div>
        	</div>
            
      </div>
            		
     
      <hr ></hr>
        <div class="controles_fila">
        <div class="control2">
        <div class="tit_control"><p>Datos Tarea</p></div>
        </div>
        	<div class="control2">
            	<div class="tit_control"><p>Tarea</p></div>
                <div class="llama_pais"><p > Tarea 5</p></div>
   	  		</div>
            
            <div class="control2">
            	<div class="tit_control2"><p>Fecha real</p></div>
                <div class="fecha_proyecto"><p>  01/04/2015 - 01/05/2015</p></div>
        	</div>
<!--      </div> -->
<!--       <div class="controles_fila"> -->
            <div class="control2">
            	<div class="tit_control"><p>Estado</p></div>
                <div class="llama_base"><p>Pendiente</p></div>
        	</div>
             </div>
          <div class="controles_fila">
                  <div class="control2">
        </div>
             <div class="control2">
            	<div class="tit_control"><p>Desviación</p></div>
                <div class="llama_base"><p>0,5 Días</p></div>
        	</div>
            <div class="control2">
            	<div class="tit_control2"><p>Fecha Estimada</p></div>
                <div class="fecha_proyecto"><p > 01/04/2015 - 01/05/2015 </p></div>
        	</div>
        	<div class="control2">
            	<div class="tit_control"><p>Responsable</p></div>
                <div class="llama_base"><p>Responsable 3</p></div>
        	</div>
            
   		  </div>
            		
     
      <hr ></hr>
       <div class="caja">
               <div class="controles_fila">
        <div class="control2">
        <div class="tit_control"><p class="subtitulos">Actualizar Datos Tarea</p></div>
        </div>
        </div>	
        
    <div class="control_fila2">
 
        	<div class="control">
          
    <div class="tit_control"><p >Fecha fin Proyecto</p></div>	
            		</div>
                <div class="control">
                	
              <input type="text"  class="llama_periodo_actual"  placeholder="Ingrese Fecha" id="fechafin" name="fechafin">
					 </div>


      </div>
             <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Porcentaje avance</p></div>	
            		</div>
                <div class="control" >

                	<select class="select">
                	<option></option>
                	<option>0%</option>
                	<option>25%</option>
                	<option>50%</option>
                	<option>75%</option>
                	<option>100%</option>
                	</select>

</div>

      </div>
                   <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Agregar Comentario</p></div>	
            		</div>
                <div class="control" >
<textarea rows="3" cols="50" >
</textarea>

</div>

      </div>

            
   		  </div>
    
    </div>
                <div class="inferior_home">
                <form>
                      <input id="volver"  type="button" class="boton2" value="Volver">
                        
                       
                </form>
                
            
                
            </div>
</div>

<footer>
	<div class="caja_text">
    	<h2>Kantar Worldpanel ® 2015</h2>
        <h3>Sistema Kantar de Validación y Liberación de Datos</h3>
    </div>
    
    
</footer>
<jsp:include page="calendar.jsp" flush="true"/>
</body>
</html>
