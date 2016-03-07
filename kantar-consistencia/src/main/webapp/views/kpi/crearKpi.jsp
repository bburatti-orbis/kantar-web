<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>

<style>
.tablaFormKPI tr td {
	color: white;
	background-color: black;
	padding-bottom: 1%;
}

.labelT{
	text-align: right;
	padding-right: 5%;
}

.fieldT {
	text-align: left;
}

#botonVerde {
    width: 115px;
    height: 32px;
    border: 0px;
    background: #A5D131;
    font-family: Arial;
    font-size: 0.8em;
    color: #fff;
    -webkit-border-radius: 1px;
    -ms-border-radius: 1px;
    -moz-border-radius: 1px;
    -o-border-radius: 1px;
    float: left;
    text-align: center;
    padding-top: 5px;
    padding-bottom: 3px;
    padding-left: 0px;
    border-bottom: 3px solid #A5D131;
	float: right;margin-right: 10%;
}

#agregaParam {
    width: 115px;
    height: 32px;
    border: 0px;
    background: #A5D131;
    font-family: Arial;
    font-size: 0.8em;
    color: #fff;
    -webkit-border-radius: 1px;
    -ms-border-radius: 1px;
    -moz-border-radius: 1px;
    -o-border-radius: 1px;
    float: left;
    text-align: center;
    padding-top: 5px;
    padding-bottom: 3px;
    padding-left: 0px;
    border-bottom: 3px solid #A5D131;
    float: right;margin-right: 10%;
}

#editaParam {
    width: 115px;
    height: 32px;
    border: 0px;
    background: #A5D131;
    font-family: Arial;
    font-size: 0.8em;
    color: #fff;
    -webkit-border-radius: 1px;
    -ms-border-radius: 1px;
    -moz-border-radius: 1px;
    -o-border-radius: 1px;
    float: left;
    text-align: center;
    padding-top: 5px;
    padding-bottom: 3px;
    padding-left: 0px;
    border-bottom: 3px solid #A5D131;
    float: right;margin-right: 10%;
}


#agregaRango {
    width: 115px;
    height: 32px;
    border: 0px;
    background: #A5D131;
    font-family: Arial;
    font-size: 0.8em;
    color: #fff;
    -webkit-border-radius: 1px;
    -ms-border-radius: 1px;
    -moz-border-radius: 1px;
    -o-border-radius: 1px;
    float: left;
    text-align: center;
    padding-top: 5px;
    padding-bottom: 3px;
    padding-left: 0px;
    border-bottom: 3px solid #A5D131;
    float: right;margin-right: 10%;
}

#editaRango{
    width: 115px;
    height: 32px;
    border: 0px;
    background: #A5D131;
    font-family: Arial;
    font-size: 0.8em;
    color: #fff;
    -webkit-border-radius: 1px;
    -ms-border-radius: 1px;
    -moz-border-radius: 1px;
    -o-border-radius: 1px;
    float: left;
    text-align: center;
    padding-top: 5px;
    padding-bottom: 3px;
    padding-left: 0px;
    border-bottom: 3px solid #A5D131;
    float: right;margin-right: 10%;
}

</style>

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
					
					<table class="tablaFormKPI" >
						<tr>
							<td class="labelT">Nombre Kpi</td>
							<td class="fieldT"><input type="text" id="nombre" /></td>
							<td  class="labelT">&Aacute;lcance</td>
							<td class="fieldT"><select id="alcances"><option value='-'>Seleccione...</option></select></td>
						</tr>
						<tr>
							<td  class="labelT">Tipo</td>
							<td class="fieldT"><select id="tipo" onchange="tipoChange();">
									<option value='-'>Seleccione...</option>
									<option value='0'>Interno</option>
									<option value='1'>Externo</option>
								</select>
							</td>
							<td  class="labelT">&Aacute;rea</td>
							<td class="fieldT"><select id="areas"><option value='-'>Seleccione...</option></select></td>
						</tr>
						<tr>
							<td  class="labelT"><label id="paisesL">Pa&iacute;s</label></td>
							<td class="fieldT">
								<select id="paises" onchange="paisChange();">
									<option value='-'>Seleccione...</option>
								</select>
							</td>
							<td  class="labelT">Categor&iacute;a</td>
							<td class="fieldT"><select id="categorias">
							<option value='-'>Seleccione...</option>
						</select></td>
						</tr>
						
						<tr>
							<td class="labelT">SP asociado</td>
							<td class="fieldT">
								<select id="sp">
							<option value='-'>Seleccione...</option>
						</select>
							</td>
							<td></td>
							<td></td>
						</tr>
						
					</table>

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
				<table style="background-color: black;width: 50%;float: right;">
					<tr>
						<td><input class="boton3" type="button" value="Salir sin grabar"	onclick="history.back();" style="margin-right: 10%;"/></td>
						<td><input id="botonVerde" type="button" value="Grabar Kpi"	onclick="grabarKpi();" style="margin-right: 10%;"/></td>
					</tr>
				</table>
<hr />
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
			<br/>
			<br/>
			<input  type="button" id="agregaParam" value="Agregar" onclick="agregarParam(true);" style="float: right;margin-right: 10%;" />
			<input  type="button" id="editaParam" value="Editar"
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
			<br/>
			<br/>
			<input type="button" id="agregaRango" value="Agregar" onclick="agregarRango(true);" style="float: right;margin-right: 10%;"/>
			<input class="boton3" type="button" id="editaRango" value="Editar" onclick="agregarRango(false);" />	
		</form>
	</div>
</body>
</html>