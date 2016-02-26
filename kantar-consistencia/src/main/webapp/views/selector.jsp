<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kantar VLD</title>
<link href="../css/estilos.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/selector.js"></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";
</script>
</head>

</head>
<body>
	<div id="wrap">
		<header>
		<div class="logo">
			<a href="index.cl"><img src="../img/logo-kantar.png" width="191"
				height="21" title="Kantar VLD"></a>
		</div>
		</header>

		<div class="contenedor_general">

			<nav>
			<ul>
				<li class="active"><a href="#">Liberador de Bases</a></li>
				<li><a href="#">Work Flow</a></li>
				<li><a href="#">Reportes</a></li>
				<li><a href="#">KPI's</a></li>
				<li><a href="#">Mantenedor</a></li>
			</ul>
			</nav>

			<hr></hr>

			<div class="controles_fila">
				<div class="control"></div>
				<div>
					<input type="button" id="todo" class="validar" value="Validar todas la bases" style="
    margin-right: 1%;">
				</div>

				<div>
					<input type="button" id="reporte" class="validar" value="Reportes">
				</div>
			</div>

			<!--<div class="caja_login">
        <div class="caja_titulo"><h1>Bienvenido a Kantar VLD</h1></div>
        	<form>
            <input id="correo" name="" type="text" placeholder="Ingesa tu correo">
            <input id="pass" name="" type="text" placeholder="Ingesa tu password">
            <input id="iniciar" name="enviar" type="submit" value="Iniciar Sesi√≥n">
            </form>
        </div>-->


			<hr></hr>

			<div class="controles_fila">
				<div class="control">
					<div class="tit_control"></div>

				</div>
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
		<h2>Kantar Worldpanel ¬Æ 2015</h2>
		<h3>Sistema Kantar de Validaci”n y Liberaci”n de Datos</h3>
	</div>

	<!--     <div class="caja_link"><p><a href="#">&raquo; Env√≠o de ticket para soporte y ayuda.</a></p></div> -->
	</footer>

</body>
</html>