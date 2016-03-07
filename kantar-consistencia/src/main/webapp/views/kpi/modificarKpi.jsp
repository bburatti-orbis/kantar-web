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
<script type="text/javascript">

$(document).ready(function(){
	var tRow = "<tr id='$_reg_$' class='filaParam'><td>$_orden_$</td><td idCombo='$_selected_$'>$_parametro_$</td><td>&_valor_&</td><td>$_editar_$</td></tr>";
	var tRowSem = "<tr id='$_fila_$' class='filaRango'><td idUmb='$_idUmbral_$'>$_umbral_$</td><td idOp='$_idOperador_$'>$_operador_$</td><td>$_valor_$</td><td>$_accion_$</td></tr>";
	//var corre = 2;
	//var sCorre = 0;
	//var editando = 0;
	var idKpi = '${idKpi}';
	ModuloKpiDWR.rescataKpi(idKpi,{
		callback:function(resp){
			if(resp == null){
				alert("No fue posible encontrar KPI para desplegar");
			}else{
				var id = resp.id;
				var nombreSp = resp.nombreSp;
				
				console.log("id "+id);
				$('#nombre').val(resp.glosa);
				$('#tipo').val(resp.tipo);
				var externo = false;
				setTimeout(function(){
					if(resp.tipo == parseInt(0)){
						console.log("es tipo interno");
						$("#paises").css("display", "none");
						$("#paisesL").css("display", "none");
						spInterno();
					}else{
						console.log("es tipo externo");
						$("#paises").css("display", "");
						$("#paisesL").css("display", "");
						console.log("Pais:"+ resp.pais + $("#paises").html());
						$("#paises").val(resp.pais).change();
						setTimeout(function(){
							$("#sp").val(resp.nombreSp).change;
						},400);
					}
					$('#alcances').val(resp.alcance).change();
					$('#areas').val(resp.area).change();
					$('#categorias').val(resp.categoria).change()
					console.log("******resp.nombreSp "+resp.nombreSp);
					
				}, 850);
				
				ModuloKpiDWR.getParam(idKpi,{
					callback:function(resp){
						if(resp == null){
							alert("No existen parametros de KPI para desplegar");
						}else{
							var filas = "";
							$.each(resp, function(){
								corre++;
								var fila = tRow;
								fila = fila.replace("$_reg_$", "reg" + this.orden);
								fila = fila.replace("$_orden_$", this.orden);
								fila = fila.replace("$_selected_$", this.tipoDato+"-"+this.paramId);
								fila = fila.replace("$_parametro_$", this.nombreP);
								
								if(this.defaultI != parseInt('0')){
									fila = fila.replace("&_valor_&", this.defaultI);
									console.log("int "+this.defaultI);
								}
								if(this.defaultD != parseInt('0')){
									fila = fila.replace("&_valor_&", this.defaultD);
									console.log("double "+this.defaultD);
								}
								if(this.defaultS != null){
									fila = fila.replace("&_valor_&", this.defaultS);
									console.log("string "+this.defaultS);
								}
								
								fila = fila.replace("$_editar_$", "<input type='button' value='Editar' onclick='editar("+corre+");' /> <input type='button' value='Eliminar' onclick='borrar("+corre+");' />");
								filas += fila;
								console.log("Correlativo : " + corre);
							});
							console.log("Correlativo 2: " + corre);
							$("#parametrosT").html($("#parametrosT").html()+filas);
							var semaforo = false;
							ModuloKpiDWR.getUmbrales(idKpi,{
								callback:function(resp){
									if(resp == null){
										alert("No existen parametros de KPI para desplegar");
									}else{
										semaforo = true;
										var filas = "";
										$.each(resp, function(){
											var fila = tRowSem;
											sCorre = sCorre + 1;
											fila = fila.replace("$_fila_$", "fila"+sCorre);
											console.log("this.idRango "+this.idRango);
											fila = fila.replace("$_idUmbral_$", this.idUmbral);
											fila = fila.replace("$_umbral_$", this.color);
											fila = fila.replace("$_idOperador_$", this.idOper);
											fila = fila.replace("$_operador_$", this.oper);
											fila = fila.replace("$_valor_$", this.valor);
											fila = fila.replace("$_accion_$", "<input type='button' value='Editar' onclick='editarS("+sCorre+");' /> <input type='button' value='Eliminar'onclick='borrarS("+sCorre+");' />");
											filas += fila;
										});
										
										$("#semaforoT").html(filas);
										if(semaforo){
											$('#rangos').prop('checked', true);
											$('#semaforoDiv').show();
										}
									}
								}
							})
						}
					}
				})
				
			}
			
		}
	})
})

function ActualizaKpi(){
	var idKpi = '${idKpi}';
	var flag = true;
	var gKpi = true;
	var gParam = true;
	var gSema = true;
	if($('#nombre').val() == ""){
		alert("Es necesario especificar un nombre");
		flag = false;
	}
	if($('#tipo').val() == "-"){
		alert("Es necesario especificar un tipo");
		flag = false;
	}
	if($('#sp').val() == "-"){
		alert("Es necesario especificar un SP asociado");
		flag = false;
	}
	if($('#alcances').val() == "-"){
		alert("Es necesario especificar un alcance");
		flag = false;
	}
	if($('#areas').val() == "-"){
		alert("Es necesario especificar un area");
		flag = false;
	}
	if($('#categorias').val() == "-"){
		alert("Es necesario especificar una categoria");
		flag = false;
	}
	if(flag){
		var pais="";
		if($('#paises').css('display') == 'none'){
			pais=0;
			console.log("pais vacio");
			console.log("el tipo "+$('#tipo').val());
		}else{
			pais=$('#paises').val();
		}
		
		var kpim = {
			id:idKpi,
			glosa:$('#nombre').val(),
			tipo:$('#tipo').val(),
			nombreSp:$('#sp option:selected').html(),
			valorTeoricoD:0,
			pais:pais,
			categoria:$('#categorias').val(),
			alcance:$('#alcances').val(),
			area:$('#areas').val()
		};
		
		console.log("antes de llamar a ModuloKpiDWR.actualizaKpi");
		ModuloKpiDWR.actualizaKpi(kpim,{
			callback:function(resp){
				console.log("dentro de ModuloKpiDWR.actualizaKpi");
				if(resp){
					console.log("ModuloKpiDWR.actualizaKpi OK");
					/* 
					idKpi = id;
					var arrParam = [];
					var arrPara = $(".filaParam");
					var fila = 0;
					var param = false;
					$.each(arrPara, function(){
						if(parseInt($($(this).find("td")[0]).html())>2){
							console.log("[Param]Kpi id: "+idKpi);
							param = true;
							var arrTD = $(this).find("td");
							var descripcion=$(arrTD[1]).html();
							var kpi=idKpi;
							var parametro=$(arrTD[1]).attr("idCombo");
							parametro = parametro.substring(2,parametro.length);
							var orden=$(arrTD[0]).html();
							var defecto=$(arrTD[2]).html();
							var tipo = $(arrTD[1]).attr("idCombo");
							tipo = tipo.substring(0,1);
							var ParamKpi = {
									descripcion:descripcion,
									kpi:kpi,
									parametro:parametro,
									size:0,
									orden:orden,
									defecto:defecto,
									tipo:tipo
							}
							arrParam.push(ParamKpi);
						}
					});
					console.log("antes del dwr");
					if(param){
						ModuloKpiDWR.grabarParam(arrParam,{
							callback:function(id){
								console.log("dentro dwr");
								if(parseInt(id) != 0){
																			
								}else{
									var gParam = false;
								}
							}
						});
					}
					
					console.log("id de parametro: "+id);
					console.log("dentro callbcak");
					var arrSemen = [];
					var arrSem = $(".filaRango");
					var fila = 0;
					console.log("arrSem:  "+arrSem);
					if(arrSem.length>0){
						$.each(arrSem, function(){
							console.log("[Sema]Kpi id: "+idKpi);
							fila = fila+1;
							console.log("[Sema]Orden de semaforo: "+fila);
							var arrTD = $(this).find("td");
							var umbralId = $(arrTD[0]).attr("idUmb");
							console.log("umbralId: "+umbralId);
							var operadorId = $(arrTD[1]).attr("idOp");
							console.log("operadorId: "+operadorId);
							var valor = $(arrTD[2]).html();
							console.log("valor:   "+valor);
							var SemaforoKpi = {
									pais:pais,
									kpi:idKpi,
									orden:fila,
									operador:operadorId,
									valor:valor,
									color:umbralId
								}
							arrSemen.push(SemaforoKpi);
						});
						
						ModuloKpiDWR.grabarSema(arrSemen,{
							callback:function(id){
								if(parseInt(id) != 0){
									
								}else{
									gSema = false; 
								}
							}
						});
					} */
					
				}else{
					console.log("actualizacion mala");
				}
			}
		});
		var msg="Actualización exitosa";
		/* if(gKpi){
			msg+="El KPI ha sigo grabado exitosamente \n";
		}else{
			msg+="Error al generar KPI \n";
		}
		if(gParam){
			msg+="El Parametro de KPI ha sigo grabado exitosamente \n";
		}else{
			msg+="Error al generar Parametro KPI \n";
		}
		if(gSema){
			msg+="El Rango de Semaforo ha sigo grabado exitosamente \n";
		}else{
			msg+="Error al generar Rango de Semaforo \n";
		} */
		alert(msg);
		history.back();
		//window.location.reload();
	}
	
}	

</script>

			<div id="general">
				<div id="datos" title="Datos Generales">
					<h1>Datos Generales</h1><br /> 
<!-- 					<div id="datosA"> -->
<!-- 						Nombre Kpi: <input type="text" id="nombre" /><br /><br />   -->
<!-- 						Tipo: <select id="tipo" onchange="tipoChange();"> -->
<!-- 							<option value='-'>Seleccione...</option> -->
<!-- 							<option value='0'>Interno</option> -->
<!-- 							<option value='1'>Externo</option> -->
<!-- 						</select><br /> <br />  -->
<!-- 						<label id="paisesL">Pa&iacute;s: </label> -->
<!-- 						<select id="paises" onchange="paisChange();"> -->
<!-- 							<option value='-'>Seleccione...</option> -->
<!-- 						</select> <br /> <br />  -->
<!-- 						SP asociado: <select id="sp"> -->
<!-- 							<option value='-'>Seleccione...</option> -->
<!-- 						</select> <br /> -->
<!-- 					</div> -->
<!-- 					<div id="datosB"> -->
<!-- 						&Aacute;lcance: <select id="alcances"> -->
<!-- 							<option value='-'>Seleccione...</option> -->
<!-- 						</select> <br /> <br />  -->
<!-- 						&Aacute;rea: <select id="areas"> -->
<!-- 							<option value='-'>Seleccione...</option> -->
<!-- 						</select> <br /> <br />  -->
<!-- 						Categor&iacute;a: <select id="categorias"> -->
<!-- 							<option value='-'>Seleccione...</option> -->
<!-- 						</select> <br /> -->
<!-- 					</div> -->

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
						<td>
						<input id="botonVerde" type="button" value="Actualizar Kpi" onclick="ActualizaKpi();" />
						</td>
					</tr>
				</table>
				
					
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
			<input type="button" id="agregaParam" value="Agregar"
				onclick="agregarParam(true);" />
			<input type="button" id="editaParam" value="Editar"
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
			<input class="" type="button" id="agregaRango" value="Agregar"
				onclick="agregarRango(true);" />
			<input class="" type="button" id="editaRango" value="Editar"
				onclick="agregarRango(false);" />	
		</form>
	</div>
</body>
</html>