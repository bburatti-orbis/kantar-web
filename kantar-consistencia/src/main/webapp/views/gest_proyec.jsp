<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kantar VLD</title>
<link href="../../css/estilos.css" rel="stylesheet">
<link href="../../css/jquery.modal.css" rel="stylesheet">
<link href="../../css/menu.css" rel="stylesheet">
<link href="../../css/multiple-select.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/menu.js"></script>
<script type="text/javascript" src="../../js/ver_tareas.js"></script>
<script type="text/javascript" src="../../js/jquery.modal.js"></script>
<script type="text/javascript" src="../../js/jquery.modal.min.js"></script>
<script type="text/javascript" src="../../js/JSBUSCAR.js"></script>
<script type="text/javascript" src="../../js/jquery.multiple.select.js"></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";
	</script>
</head>

<body>
	<div id="wrap">
		<header>
			<div class="logo">
				<a href="index.cl"><img src="../../img/logo-kantar.png"
					width="191" height="21" title="Kantar VLD"></a>
			</div>
		</header>

		<div class="contenedor_general">
			<nav>
				<jsp:include page="menu.jsp" flush="true" />
			</nav>

			<hr></hr>

			<div class="controles_fila">
				<div class="control">
					<div class="tit_control">
						<p>Proyecto:</p>
					</div>
					<div class="llama_base">
						<p id="nombre"></p>
					</div>

				</div>
				<div class="control">
					<div class="tit_control">
						<p>Id Proyecto:</p>
					</div>
					<div class="llama_base">
						<p id="id"></p>
					</div>

				</div>

				<div class="control">
					<input id="tareas" class="boton_gestion" name="enviar"
						type="button" value="Agregar Tarea">
				</div>

			</div>

		</div>

		<hr></hr>

		<div class="caja_titulo">
			<p>Lista de tareas Por Proyecto</p>
		</div>

		<div class="caja_tablas">
			<table id="polizas">
				<thead>
					<tr>
						<th scope="col">N° tarea</th>
						<th scope="col">Nombre Tarea</th>
						<th scope="col">Plazo</th>
						<th scope="col">Responsable</th>
						<th scope="col">Predecesora</th>
						<th scope="col">Acciones</th>
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
					<input id="volver" name="enviar" class="boton2" type="button"
						value="Volver">
				</form>
			</div>
			<div class="kantar_world"></div>
		</div>
	</div>

	<footer>
		<div class="caja_text">
			<h2>Kantar Worldpanel ® 2015</h2>
			<h3>Sistema Kantar de Validación y Liberación de Datos</h3>
		</div>


	</footer>
	
	<!-- MODAL actualizacion de tareas -->
	<div id="ex" style="display: none;">
		<div class="caja">
			<div class="control_fila2">
				<div class="control">
					<div class="tit_control">
						<p>Editar</p>
					</div>
				</div>
			</div>

			<div class="control_fila2">
				<div class="control">
					<div class="tit_control">
						<p>Nombre de la tarea</p>
					</div>
				</div>
				<div class="control">
					<input type="text" class="nomtarea" placeholder="Ingrese Nombre"
						id="Nombre">
				</div>
			</div>
			<div class="control_fila2">
				<div class="control">
					<div class="tit_control">
						<p>Area</p>
					</div>
				</div>
				<div class="control">
					<select class="select" id="area">
					</select>
				</div>
			</div>
			<div class="control_fila2">
				<div class="control">
					<div class="tit_control">
						<p>Responsable</p>
					</div>
				</div>
				<div class="control">
					<select class="select" id="users">
					</select>
				</div>
			</div>
			<div class="control_fila2">
				<div class="control">
					<div class="tit_control">
						<p>Plazo</p>
					</div>
				</div>
				<div class="control">
					<input type="text" class="llama_periodo_actual" id="plazo">
				</div>
				<div class="control">
					<select class="select" id="tiempo">
						<option disabled selected>Unidad de tiempo</option>
						<option value="1">Horas</option>
						<option value="2">Dias</option>
						<option value="3">Meses</option>
						<option value="4">Años</option>
					</select>
				</div>
			</div>
			<div class="control_fila2">
				<div class="control">
					<div class="tit_control">
						<p>Delay</p>
					</div>
				</div>
				<div class="control">
					<input type="text" class="llama_periodo_actual" id="delay">
				</div>
				<div class="control">
					<select class="select" id="tiempodelay">
						<option disabled selected>Unidad de tiempo</option>
						<option value="1">Horas</option>
						<option value="2">Dias</option>
						<option value="3">Meses</option>
						<option value="4">Años</option>
					</select>
				</div>
			</div>
			<div class="control_fila2">
				<div class="control">
					<div class="tit_control">
						<p>Frecuencia</p>
					</div>
				</div>
				<div class="control">
					<input type="text" class="llama_periodo_actual" id="frecuencia">
				</div>
			</div>
			<div class="control_fila2">
				<div class="control">
					<div class="tit_control">
						<p>Tarea Predecesora</p>
					</div>
				</div>
				<div id="sele"></div>
			</div>
			<div class="control_fila2">
				<div class="control">
					<div class="tit_control">
						<p>Tipo Calendario</p>
					</div>
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
					<div class="tit_control">
						<p>Conjunto de bases</p>
					</div>
				</div>
				<div class="control">
					<input type="checkbox" name="option1" value="0">
					<div class="tit_control">
						<p>No Aplica</p>
					</div>
				</div>
				<div class="control">
					<select class="select" id="conjuntos">
					</select>
				</div>
			</div>
			<div class="control_fila2">
				<div class="control">
					<input id="editartarea" class="boton_gestion" name="enviar"
						type="button" value="Guardar Tarea">
				</div>
			</div>
			<input type="hidden" id="id_tarea" value="">
		</div>
	</div>
	
</body>
</html>