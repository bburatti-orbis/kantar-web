<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kantar VLD</title>
<link href="../css/estilos.css" rel="stylesheet">
</head>

<body>


<div id="wrap">


	<header>
    	<div class="logo"><a href="index.cl"><img src="../img/logo-kantar.png" width="191" height="21" title="Kantar VLD"></a></div>
    </header>

    
    <div class="contenedor_general">
    	<div class="caja_login">
        <div class="caja_titulo0"><h1>Bienvenido a Kantar VLD</h1></div>
        <form action="login" method="POST">
        	<input id="correo" name="correo" type="text" placeholder="Ingresa tu correo">
            <input id="pass" name="pass" type="Password" placeholder="Ingresa tu password">
            <input id="iniciar" name="enviar" type="submit" value="Iniciar Sesión">
            
            
            
            </form>
        </div>
        <c:choose>
  					<c:when test="${it.cod==1}">
    					  <h1>Debes registrarte antes de ingresar</h1>
 					 </c:when>
  					<c:otherwise>
    					 <h1></h1>
 					 </c:otherwise>
					</c:choose>
        
        <div class="caja_high"><img src="../img/high-definition-inspiration.png" width="395" height="113"></div>
        
        
        <hr></hr>
    	<!--<div class="separador_interior"></div>-->
        
<!--         <div class="inferior_home"> -->
<!--         	<p><a href="#">¿Olvidaste tu contraseña?</a></p> -->
<!--             <p><a href="#">Regístrate ahora</a></p> -->
<!--         </div> -->
        <div class="kantar_world"></div>
    </div>
</div>

<footer>
	<div class="caja_text">
    	<h2>Kantar Worldpanel® 2015</h2>
        <h3>Sistema Kantar de Validación y Liberación de Datos</h3>
    </div>
    
<!--     <div class="caja_link"><p><a href="#">&raquo; Envío de ticket para soporte y ayuda.</a></p></div> -->
</footer>

</body>
</html>