<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kantar VLD</title>
<link href="../css/menu.css" rel="stylesheet">
<link href="../css/estilos.css" rel="stylesheet">
<link rel="stylesheet" href="../css/ui/1.12.0-beta.1/themes/smoothness/jquery-ui.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/jquery-1.12.1.min.js"></script>
<script src="../js/ui/1.12.0-beta.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/Pagina3.js"></script>
<script type="text/javascript" src="../js/JSBUSCAR.js"></script>
<script type="text/javascript" src="../js/menu.js"></script>
<script type="text/javascript" src="../js/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";
</script>
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
				<jsp:include page="menu.jsp" flush="true" />
			</nav>

			<hr></hr>
						<div class="controles_fila">
				<div class="control_filtro">
					<div class="tit_control">
						<p>Búsqueda</p>
					</div>
					<div>
						<p>
							<input type="text" class="llama_periodo_actual" placeholder="Ingrese Búsqueda" onkeyup="doSearch()" id="busqueda">
						</p>
					</div>
				</div>
				<div class="control_filtro">
					<div class="tit_control"><p>Entre</p></div>
					<div>
						<p>
							<input type="text" class="llama_periodo_actual" placeholder="Desde" id="desde">
						</p>
						<p>
							<input type="text" class="llama_periodo_actual" placeholder="Hasta" id="hasta">
						</p>
					</div>
				</div>
				<div class="control_filtro">
					<div class="tit_control"><p>Estado</p></div>
					<div style="width: inherit;">
						<p>
							<select id="estado" onchange="doSearchEstado()">
								<option></option>
								<option>TERMINADA</option>
								<option>REVISAR</option>
								<option>ENTREGAR</option>
								<option>EN PROCESO</option>
							</select>
						</p>
					</div>
				</div>
				<div style="width=15%">
					<input id="actualiza" class="boton2" value="Actualiza">
				</div>
			</div>

			<hr></hr>

			<div class="caja_titulo">
				<p>Resultado General</p>
				<p id="fecha"></p>
			</div>


			<div class="caja_tablas">
				<table id="polizas">
					<thead>
						<tr>
							<th scope="col">País</th>
							<th scope="col">Base</th>
							<th scope="col">Categoría</th>
							<th scope="col">Cliente</th>
							<th scope="col">Encargado</th>
							<th scope="col">Período</th>
							<th scope="col">C. Interna</th>
							<th scope="col">C. Histórica</th>
							<th scope="col">Inicio</th>
							<th scope="col">Término</th>
							<th scope="col">Estado</th>
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
						<input id="volver" name="enviar" class="boton2" type="submit"
							value="Volver">
					</form>

					<div class="excel">
						<a href="#"><img src="../img/excel-icon.png" width="26"
							height="28" title="Descarga archivo Excel"></a>
					</div>

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

		<!--     <div class="caja_link"><p><a href="#">&raquo; Envío de ticket para soporte y ayuda.</a></p></div> -->
	</footer>

</body>
</html>
