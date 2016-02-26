<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kantar VLD</title>
<link href="../../css/menu.css" rel="stylesheet">
<link href="../../css/estilos.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../../js/jquery.min.js"></script>
<script src="../../js/crear_conjunto.js"></script>
<script type="text/javascript" src="../../js/menu.js" ></script>
  <link rel="stylesheet" href="../../css/jquery-ui.css">

  <script src="../../js/jquery-ui.js"></script>

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

  <div class="controles_fila">
        	<div class="control">
            	<div class="tit_control"><p></p></div>
                <div class="tit_control">
 					
					 </div>
   	  		</div>
         

      </div>

      	
           
        
        <hr></hr>
            <div class="titulo">  <div class="caja_titulo">Crear Conjunto</div></div>  
           <div class="caja_tablas">
  
      
         
            
            	<div class="control_fila2">
	 	<div class="control">
		Nombre: 
		</div>
		 	<div class="control">
		 	 <input type="text"  class="llama_periodo_actual"  placeholder="Ingrese Nombre" 	 id="nombre" name="nombre"		>
		 	</div></div>
		 	<div class="control_fila2"><div class="control">Seleccione Pais</div><div class="control"><select id="pais"><option></option></select></div></div>
            <div id="disponibles">
	        <table id="listapaises" class="tablas"  >  
                <thead>          
                  <tr>

                    
                    <th scope="col">Bases Disponibles</th>

                  </tr>
                  </thead>
                  
                  			<tbody id="sortable1" class="connectedSortable">

					</tbody>
                </table>
</div>
<div id="botones">
				<div class="controles_fila">

</div>
<div class="controles_fila">
            
            	</div>
</div>
<div id="conjunto">
            
                <table id="conjuntos" class="tablas" >  
                <thead>          
                  <tr>

                  
                    <th scope="col">Bases EN CONJUNTO</th>

                  </tr>
                  </thead>
                  
                  			<tbody  id="sortable2"  class="connectedSortable">
                  		

					</tbody>
                </table>
</div>
<div class="controles_fila" > 
<div class="control">

   <input id="volver" name="enviar" type="submit" class="boton2" value="Volver">
 </div><div class="control">
 <input id="iniciar" name="enviar" type="submit" value="Guardar">
 </div></div>
</div>
        <hr></hr>
              

        
        <div class="caja_final">
            <div class="inferior_home">

                
            
                
            </div>
            
            <div class="kantar_world"></div>
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