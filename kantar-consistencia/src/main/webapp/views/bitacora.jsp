<jsp:include page="generales/header.jsp" flush="true"/>
<script>

	$(document).ready(function(){
		init();
	});

	function init(){
		cargarBitacora();
		cargaComboAccion();
		cargaComboHorasMinutos(23, 59);
		$("#fechaFin").val("");
		$("#horasDedidcadas").val("");
		$("#comentarios").val("");
		
	}
	
	var usuarioID = '${user.id}';
	
	function agregarBitacora(){
		
		var bitacora = {
				idTarea:idTarea,
				idAccion:$("#accionCombo").val(),
				fechaEstimadaFinTareaS:$("#fechaFin").val(),
				horas:$("#horas").val(),
				minutos:$("#minutos").val(),
				horasDedicadas:$("#horasDedidcadas").val(),
				comentarios:$("#comentarios").val(),
				idUsuario:usuarioID,
				cl:$("#cl").val(),
				avance:$("#avance").val()
		}
		//Validaciones
		var errores = "";
		if($.trim(bitacora.comentarios) == ""){
			errores += "Debe ingresar comentarios\n";
		}
		if(parseInt($.trim(bitacora.horasDedicadas)) <= 0){
			errores += "Debe especificar las horas dedicadas\n";
		}
		if($.trim(bitacora.fechaEstimadaFinTareaS) == ""){
			bitacora.fechaEstimadaFinTareaS = "2999-10-15";
		}
		if(isNaN(bitacora.avance)){
			errores += "Avance no válido\n";
		}else if(parseInt(bitacora.avance) > 100 || parseInt(bitacora.avance) < 0){
			errores += "Avance no válido\n";
		}
		if(errores != ""){
			alert(errores);
			return;
		}
		console.log(bitacora);
		//Grabar
		KantarDWR.ingresarBitacora(bitacora, idEstadoTarea, {
			callback:function(ok){
				if(ok){
					//Actualizar estado tarea
					//TODO
					//Recargar
					init();
					alert("Accion registrada");
				}else{
					alert("Error al grabar bitacora");
				}
			}
		});
	}
	
	var idEstadoTarea = '${estado.id}';
	var accionT = "<option value='$_idAccion_$'>$_nombreAccion_$</option>";
	var accionTNoResult = "<option value=''>Sin acciones disponibles</option>";
	
	function cargaComboAccion(){
		if(!isNaN(idEstadoTarea)){
			KantarDWR.getAccionBitacora(idEstadoTarea, {
				callback:function(resp){
					if(resp != null && resp.length > 0){
						var html = "";
						$.each(resp, function(){
							var row = accionT;
							//Replaces
 							row = row.replace("$_idAccion_$", this.idAccion);
 							row = row.replace("$_nombreAccion_$", this.nombreAccion);
							html+=row;
						});
						$("#accionCombo").html(html);
						campoHoras($("#accionCombo"));
					}else{
						$("#accionCombo").html(accionTNoResult);
					}
				}
			});
		}
	}
	
	var comboT = "<option value='$_id_$'>$_glosa_$</option>";
	
	function cargaComboHorasMinutos(maxHoras, maxMinutos){
		var html = null;
		var horas = [];
		for(i=0;i<=maxHoras;i++){
			if(i<10){
				horas.push("0"+i);
			}else{
				horas.push(""+i);
			}
		}
		var minutos = [];
		for(i=0;i<=maxMinutos;i++){
			if(i<10){
				minutos.push("0"+i);
			}else{
				minutos.push(""+i);
			}
		}
		html = "";
		$.each(horas, function(){
			var row = comboT;
			//Replaces
			row = row.replace("$_id_$", this);
			row = row.replace("$_glosa_$", this);
			html+=row;
		});
		$("#horas").html(html);
		
		html = "";
		$.each(minutos, function(){
			var row = comboT;
			//Replaces
			row = row.replace("$_id_$", this);
			row = row.replace("$_glosa_$", this);
			html+=row;
		});
		$("#minutos").html(html);
	}
	
	var idTarea = '${idTarea}';
	var bitacoraT = "<tr class='filaBitacora'><td>$_fecha_$</td><td>$_comen_$</td><td>$_actor_$</td><td>$_accion_$</td><td>$_avance_$</td><td>$_cl_$</td></tr>";
	var bitacoraTNoResult = "<tr class='filaBitacora'><td colspan='6'>No hay registros en bit&aacute;cora</td></tr>";
	
	function cargarBitacora(){
		if(!isNaN(idTarea)){
			KantarDWR.getBitacoraTarea(idTarea, {
				callback:function(resp){
					if(resp != null && resp.length > 0){
						var html = "";
						$.each(resp, function(){
							var row = bitacoraT;
							//Replaces
 							row = row.replace("$_fecha_$", this.fechaRegistroS);
 							row = row.replace("$_comen_$", this.comentarios);
 							row = row.replace("$_actor_$", this.actor);
 							row = row.replace("$_accion_$", this.accion);
 							row = row.replace("$_avance_$", this.avance + "%");
 							row = row.replace("$_cl_$", this.cl==1?"SI":(this.cl==0?"NO":"NO APLICA"));
							html+=row;
						});
						$("#bitacoraBody").html(html);
					}else{
						$("#bitacoraBody").html(bitacoraTNoResult);
					}
				}
			});
		}		
	}
	
	function campoHoras(combo){
		var idAccion = $(combo).val();
		if(idAccion == 3 || idAccion == 5){
			$("#campoHorasD").show();	
		}else{
			$("#campoHorasD").hide();	
		}
		
// 		Iniciar tarea
// 		Dejar stand by
// 		Retomar stand by

		if(idAccion == 1 || idAccion == 3 || idAccion == 4){
			$("#campoFechaD").show();	
		}else{
			$("#campoFechaD").hide();	
		}

	}
	
</script>
<div class='tituloPag'>Bit&aacute;cora tarea</div>

<jsp:include page="datosTarea.jsp" flush="true"/>

<div class='subTituloPag'>Actualizar datos tarea</div>

<div>
	<div class="divControl">
		<div class="labelControl">Acci&oacute;n</div>
		<div class="controlBBM">
			<select id="accionCombo" onchange="campoHoras(this);">
				<option>Cargando...</option>
			</select>
		</div>
	</div>
	<div class="divControl" id="campoFechaD" style="display:none;">
		<div class="labelControl">Fecha estimada fin tarea</div>
		<div class="controlBBM"><input type="date" id="fechaFin"/>
			<select id="horas">
			</select>
			<select id="minutos">
			</select>
		</div>
	</div>
	<div class="divControl">
		<div class="labelControl">Check List</div>
		<div class="controlBBM">
			<select id="cl">
				<option value="-1">NO APLICA</option>
				<option value="1">SI</option>
				<option value="0">NO</option>
			</select>
		</div>
	</div>
	<div class="divControl" id="campoHorasD" style="display:none;">
		<div class="labelControl">Horas dedicadas</div>
		<div class="controlBBM">
			<input type="number" id="horasDedidcadas" value="0"/>
		</div>
	</div>
	<div class="divControl">
		<div class="labelControl">% de Avance</div>
		<div class="controlBBM2">
			<input type="text" id="avance" value="0" maxlength="3" style="width: 30px;"/>%
		</div>
	</div>
	<div class="divControl">
		<div class="labelControl">Agregar comentarios</div>
		<div class="controlBBM">
			<textarea cols="50" rows="10" id="comentarios"></textarea>
		</div>
	</div>
	<div style="width: 100%;text-align: right;">
		<input class="boton2" type="button" value="Agregar" onclick="agregarBitacora();" style="float:none!important;margin-right: 20%;"/>
	</div>
</div>
<div class='subTituloPag'>Resumen de Actividades</div>
<div class="tablaContent">
	<table class="tablaBitacora">
		<thead>
			<tr class="cabeceraBitacora">
				<td>Fecha Actividad</td>
				<td>Comentario</td>
				<td>Actor</td>
				<td>Acci&oacute;n</td>
				<td>Avance</td>
				<td>Checklist</td>
			</tr>
		</thead>
		<tbody id="bitacoraBody">
			<tr class="filaBitacora">
				<td colspan='4'>cargando...</td>
			</tr>
		</tbody>
	</table>
</div>

<div>
	<input class="boton2" type="button" value="Volver" onclick="history.back();"/>
	<input class="boton2" type="button" value="Finalizar" onclick="history.back();"/>
</div>

<jsp:include page="generales/footer.jsp" flush="true"/>
