<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
<title>Kantar VLD</title>
<link href="../../css/estilos.css" rel="stylesheet">
<link href="../../css/jquery.modal.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../../js/jquery.min.js"></script>
<script src="../../js/jquery-1.9.1.js"></script>
<link href="../../css/menu.css" rel="stylesheet">
<script type="text/javascript" src="../../js/menu.js" ></script>
<script type="text/javascript" src="../../js/mod_proyecto.js" ></script>
<script type="text/javascript" src="../../js/calendar.js" ></script>
<script type="text/javascript" src="../../js/cada_semana.js" ></script>
<script type="text/javascript" src="../../js/jquery.modal.js" ></script>
<script type="text/javascript" src="../../js/jquery.modal.min.js" ></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";
	

	</script>
</head>

<body>
<div id="wrap">
	<header>
    	<div class="logo"><a href="index.cl"><img src="../../img/logo-kantar.png" width="191" height="21" title="Kantar VLD"></a></div>
    </header>
    
    <div class="contenedor_general">
    	<nav>
<jsp:include page="menu.jsp" flush="true"/>
        </nav>
        
        <hr></hr>
        
  <div class="titulo"> <div class="controles_fila">
  	<div class="tit_control"><p>Modificar Proyecto</p> </div>

      </div>
      </div>
       <div class="caja">

 <div class="control_fila2">
 
        	<div class="control">
          
    <div class="tit_control"><p>Nombre del proyecto</p></div>	
            		</div>
                <div class="control">
                	 <input type="hidden" value="" name="id_ciclo" id="id_ciclo">
                	 <input type="hidden" value="" name="id_proyecto" id="id_proyecto">
              <input type="text"  class="llama_periodo_actual"  placeholder="Ingrese Nombre" id="nombre" name="nombre"		>
					 </div>


      </div>

       <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Programacion: </p></div>	
            		</div>
                <div class="control">
                	<p id="resumengeneral"> </p>
              
					 </div>
                <div class="control">
                	
             <input type="button" class="boton2" id="" name="repetir" value="Editar">
					 </div>

      </div>

       <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Pais</p></div>	
            		</div>
                <div class="control" >

                	<select class="select" name ="pais" id="pais">
                	
                	</select>

</div>

      </div>
       <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Calendario Interno</p></div>	
            		</div>
                <div class="control">
                	
                            	<select class="select" id="calendario" name="calendario">
                	<option value="0"></option>
                	<option value="1">Chile</option>
                	<option value="2">Peru</option>
                	<option value="3">Brazil</option>
                	<option value="4">Colombia</option>
                	
                	</select>
					 </div>
			
 				
            </div> 
<!--             <div class="control_fila2"> -->
<!--         	<div class="control">               -->
<!--     <div class="tit_control"><p>Calendario Externo</p></div>	 -->
<!--             		</div> -->
<!--                 <div class="control"> -->
                	
<!--                             	<select class="select" id="calendarioex" name="calendario"> -->
<!--                 	<option value="0"></option> -->
<!--                 	<option value="1">Chile</option> -->
<!--                 	<option value="2">Peru</option> -->
<!--                 	<option value="3">Brazil</option> -->
<!--                 	<option value="4">Colombia</option> -->
                	
<!--                 	</select> -->
<!-- 					 </div> -->
			
 				
<!--             </div>  -->
            
<div class="control_fila2" > 
<div class="control">
				
				</div>
				<div class="control"><input id="iniciar" name="enviar" type="button" value="Guardar" ></div>
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