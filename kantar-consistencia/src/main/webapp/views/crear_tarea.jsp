<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kantar VLD</title>
<link href="../../css/estilos.css" rel="stylesheet">
<link href="../../css/menu.css" rel="stylesheet">
<link href="../../css/multiple-select.css" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/crear_tarea.js" ></script>
<script type="text/javascript" src="../../js/menu.js" ></script>
<script type="text/javascript" src="../../js/JSBUSCAR.js" ></script>
<script type="text/javascript" src="../../js/jquery.multiple.select.js" ></script>

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
        <div class="titulo">

  	<div class="caja_titulo">Agregar tarea al proyecto</div>
  	

      </div>
       <div class="caja_tablas">
        <div class="control_fila2">
        	<div class="control">
          
    <div class="subtitulos"><p>Seleccione Características de la tarea</p></div>	
            		</div>


      </div>

 <div class="control_fila2">

        	<div class="control">
          
    <div class="tit_control"><p>Nombre de la tarea</p></div>	
            		</div>
                <div class="control">
                	
              <input type="text"  class="nomtarea"  placeholder="Ingrese Nombre" id="Nombre">
					 </div>


      </div>
             <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Area</p></div>	
            		</div>
                <div class="control">
                	<select class="select" id="area">

                	</select>
					 </div>


      </div>
       <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Responsable</p></div>	
            		</div>
                <div class="control">
                	<select class="select" id="users">

                	</select>
					 </div>


      </div>
       <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Plazo</p></div>	
            		</div>
                <div class="control">
                	
              <input type="text"  class="llama_periodo_actual"  	 id="plazo"		> </div>
                   <div class="control">
                   	<select class="select" id="tiempo" >
                   	<option disabled selected>Unidad de tiempo</option>
                	<option value="Horas">Horas</option>
                	<option value="Dias">Dias</option>
                	<option value="Meses">Meses</option>
                	<option value="Años">Años</option>
                	
                	
                	</select>
					
					 </div>


      </div>
 <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Delay</p></div>	
            		</div>
                <div class="control">
                	
              <input type="text"  class="llama_periodo_actual"  	 id="delay"		> </div>
                   <div class="control">
                   	<select class="select" id="tiempodelay" >
                   	<option disabled selected>Unidad de tiempo</option>
                	<option value="Horas">Horas</option>
                	<option value="Dias">Dias</option>
                	<option value="Meses">Meses</option>
                	<option value="Años">Años</option>
                	
                	
                	</select>
					
					 </div>


      </div>
      
       <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Frecuencia</p></div>	
            		</div>
                <div class="control">
                	
              <input type="text"  class="llama_periodo_actual"  	 id="frecuencia"		> </div>
              <div class="control">
              	<select class="select" id="frecuencialist" >
                   	<option disabled selected>Frecuencia</option>
                	<option value="Mensual">Mensual</option>
                	<option value="Bimestral">Bimestral</option>
                	<option value="Trimestral">Trimestral</option>
                	<option value="Semestral">Semestral</option>
                	<option value="Anual">Anual</option>
               	</select>
              </div>
      </div>
       <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Tarea Predecesora</p></div>	
            		</div>
<!--                 <div class="control"> -->
                	
					<div id ="sele">
					
					</div>	
<!-- 					 </div> -->


      </div>
             <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Tipo Calendario</p></div>	
            		</div>
                <div class="control">
                	
            	<select class="select" id="calendario">
                	<option></option>
                	<option value="1">Interno</option>
                	<option value="2">Externo</option>
                	
                	
                	</select>
					 </div>


      </div>
       <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Conjunto de bases</p></div>	
            		</div>
                <div class="control">

		<input type="checkbox" name="option1" value="0"> <div class="tit_control"><p>No Aplica </p></div>	
		</div>
		             <div class="control">
				  	<select class="select" id ="conjuntos">
                	
                	</select>

</div>

      </div>
             <div class="control_fila2">
        	<div class="control">
          
    <div class="tit_control"><p>Tarea Automatica</p></div>	
            		</div>
                <div class="control">

		<input type="checkbox" name="automatica" value="0"> <div class="tit_control"><p>SI </p></div>		
		</div>

      </div>
       <input id="volver" name="enviar" class="boton2" type="button" value="Volver">
      <input type="hidden" name="idproyecto" id="idproyecto" value="">
      <input id="creartarea"  class="boton_gestion"name="enviar" type="button" value="Agregar Tarea">
   
<input type="hidden"  id="nombreproyec" value="">
      	
      </div>      
        
    
    </div>
    
</div>

<footer>
	<div class="caja_text">
    	<h2>Kantar Worldpanel ® 2015</h2>
        <h3>Sistema Kantar de Validación y Liberación de Datos</h3>
    </div>
    
    
</footer>

</body>
</html>