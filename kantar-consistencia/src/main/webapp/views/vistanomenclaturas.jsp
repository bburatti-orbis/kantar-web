<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kantar VLD</title>
<link href="../../css/estilos.css" rel="stylesheet">
<link href="../../css/menu.css" rel="stylesheet">
<script src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/vistanomesclatura.js" ></script>
<script type="text/javascript" src="../../js/menu.js" ></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";
	</script>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
            	<div class="tit_control"><p>País</p></div>
                <div class="llama_pais"><p id="pais"></p></div>
   	  		</div>
            
            <div class="control">
            	<div class="tit_control"><p>Periodo</div>
            	<div class="llama_pais"><p id="periodo"></div>
            </div>
            
            <div class="control">
            	<div class="tit_control"><p>Base</p></div>
                <div class="llama_base"><p id="base"></p></div>
        	</div>
            
            <div class="control">
            	<div class="tit_control"><p>Fecha</p></div>
                <div class="llama_periodo_actual"><p id="fecha"></p></div>
        	</div>
            
      </div>
        
   	  <!--<div class="caja_login">
        <div class="caja_titulo"><h1>Bienvenido a Kantar VLD</h1></div>
        	<form>
            <input id="correo" name="" type="text" placeholder="Ingesa tu correo">
            <input id="pass" name="" type="text" placeholder="Ingesa tu password">
            <input id="iniciar" name="enviar" type="submit" value="Iniciar Sesión">
            </form>
        </div>-->
        
        
        <hr></hr>
    	
        <div class="caja_titulo"><p>Resultado General</p></div><div class="caja_titulo2"><p>Consistencia Interna</p></div>
            
            <div class="caja_tablas">
                <table id="polizas"> 
                 <thead>           
                  <tr>
                    <th scope="col">Nomenclatura</th>
                    <th scope="col">Tipo Nom.</th>
                    <th scope="col">Diferencias</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Detalles</th>
                  </tr>
                  </thead>
                  			<tbody>
	
					</tbody>
                </table>
            </div>
        
        <hr></hr>
        
        <div class="caja_final">
            <div class="inferior_home">
                <form>
                 <input id="volver" class="boton2" name="enviar" type="submit" value="Volver">
                </form>
                
                <div class="excel"><a href="#"><img src="../../img/excel-icon.png" width="26" height="28" title="Descarga archivo Excel"></a></div>
                
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
    
    <div class="caja_link"><p><a href="#">&raquo; Envío de ticket para soporte y ayuda.</a></p></div>
</footer>

</body>
</html>