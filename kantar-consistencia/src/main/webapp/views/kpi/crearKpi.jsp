<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Kantar VLD</title>
<link href="../../../css/menu.css" rel="stylesheet"></link>
<link href="../../../css/estilos.css" rel="stylesheet"></link>

<script type='text/javascript'
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js'></script>
<link href="../../../css/jquery.modal.css" rel="stylesheet" />
<script type="text/javascript" src="../../../js/jquery.modal.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js'></script> -->

<!--DWR-->
<script src="../../../dwr/engine.js"></script>
<script src="../../../dwr/interface/KantarDWR.js"></script>

<script type="text/javascript" src="../../../js/JSBUSCAR.js"></script>

<script type="text/javascript" src="../../../js/menu.js"></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";
	</script>
<link href="../../../css/kpis.css" rel="stylesheet"></link>
</head>

<body>
	<div id="wrap">
		<div>
			<div class="logo">
				<a href="index.cl"><img src="../../../img/logo-kantar.png"
					width="191" height="21" title="Kantar VLD" /></a>
			</div>
		</div>

		<div class="contenedor_general">
			<div>
				<jsp:include page="../menu.jsp" flush="true" />
			</div>


<script src="../../../dwr/interface/ModuloKpiDWR.js"></script>
<script type="text/javascript" src="../../../js/kpi.js"></script>
			<div id="general">
				<div id="datos" title="Datos Generales">
					<h1>Datos Generales</h1><br /> 
					<div id="datosA">
						Nombre Kpi: <input type="text" id="nombre" /><br /><br />  
						Tipo: <select id="tipo" onchange="tipoChange();">
							<option value='-'>Seleccione...</option>
							<option value='0'>Interno</option>
							<option value='1'>Externo</option>
						</select><br /> <br /> 
						<label id="paisesL">Pa&iacute;s: </label>
						<select id="paises" onchange="paisChange();">
							<option value='-'>Seleccione...</option>
						</select> <br /> <br /> 
						SP asociado: <select id="sp">
							<option value='-'>Seleccione...</option>
						</select> <br />
					</div>
					<div id="datosB">
						&Aacute;lcance: <select id="alcances">
							<option value='-'>Seleccione...</option>
						</select> <br /> <br /> 
						&Aacute;rea: <select id="areas">
							<option value='-'>Seleccione...</option>
						</select> <br /> <br /> 
						Categor&iacute;a: <select id="categorias">
							<option value='-'>Seleccione...</option>
						</select> <br />
					</div>
				</div>
				<div id="parametros">
					<h1>Par&aacute;metros</h1>
					<br /><br />
					<table>
						<thead>
							<tr>
								<th id="">Orden</th>
								<th>Par&aacute;metro</th>
								<th>Valor por defecto</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody id="parametrosT">
							<tr>
								<td colspan="4">cargando...</td>
							</tr>
						</tbody>
					</table>
					<input class="boton3" type="button" value="Agregar Parametro"
						onclick="agregaParam();" />
				</div>
				<div><input id="rangos" name="rangos" type="checkbox" onchange="if($('#rangos').is(':checked'))$('#semaforoDiv').show();else $('#semaforoDiv').hide();"><h1>Rangos para sem&aacute;foro</h1></div>
				<br /><br />
				<div id="semaforoDiv" style="display:none;">
					<h1>Sem&aacute;foro</h1>
					<br /><br />
					<table>
						<thead>
							<tr>
								<th>Umbral</th>
								<th>Operador</th>
								<th>Valor</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody id="semaforoT">
						</tbody>
					</table>
					<input class="boton3" type="button" value="Agregar Rango"
						onclick="semaforo();" />
				</div>
				<hr />
					<input class="boton3" type="button" value="Grabar Kpi"
							onclick="grabarKpi();" />
			</div>
			<div>
				<div class="kantar_world"></div>
			</div>
		</div>
	</div>
	<div>
		<div class="caja_text">
			<h2>Kantar Worldpanel &reg; 2015</h2>
			<h3>Sistema Kantar de Validaci&oacute;n y Liberaci&oacute;n de
				Datos</h3>
		</div>
	</div>
	<div id="ex" style="display: none;">
		<h1>CARGAR PARAMETRO KPI</h1><br /><br />
		<h2>Seleccione parametro a agregar</h2><br />
		<form action="post">
			Nombre:
			<select id="nombreParam">
				<option value='-'>Seleccione...</option>
			</select>
			<br />
			<br /> 
			Valor por defecto:
			<input type="text" id="valorParam" value=""/>
			<input class="boton3" type="button" id="agregaParam" value="Agregar"
				onclick="agregarParam(true);" />
			<input class="boton3" type="button" id="editaParam" value="Editar"
				onclick="agregarParam(false);" />	
		</form>
	</div>
	
	<div id="sema" style="display: none;">
		<h1>AGREGAR RANGO PARA SEMAFORO</h1><br /><br />
		<h2>Seleccione rango a agregar</h2><br />
		<form action="post">
			Umbral:
			<select id="umbral">
				<option value='-'>Seleccione...</option>
			</select>
			<br />
			<br /> 
			Operador:
			<select id="operador">
				<option value='-'>Seleccione...</option>
			</select>
			<br />
			<br />
			Valor:
			<input type="text" id="valorSema" value=""/>
			<input class="boton3" type="button" id="agregaRango" value="Agregar"
				onclick="agregarRango(true);" />
			<input class="boton3" type="button" id="editaRango" value="Editar"
				onclick="agregarRango(false);" />	
		</form>
	</div>
</body>
</html>