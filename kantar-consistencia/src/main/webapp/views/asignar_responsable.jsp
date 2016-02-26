<jsp:include page="generales/header.jsp" flush="true"/>
<script>

$(document).ready(function(){
	cargaComboAreas();
});

var comboT = "<option value='$_valor_$'>$_glosa_$</option>";
var comboTNoResult = "<option value=''>No hay areas para mostrar</option>";

function cargaComboAreas(){
	KantarDWR.getAreas({
		callback:function(resp){
			if(resp != null && resp.length > 0){
				var html = "<option value=''>Seleccione...</option>";
				$.each(resp, function(){
					var row = comboT;
					row = row.replace("$_valor_$", this.idArea);
					row = row.replace("$_glosa_$", this.desc);
					html += row;
				});
				$("#comboAreas").html(html);
			}else{
				$("#comboAreas").html(comboTNoResult);
			}
		}
	});
}

var comboTNoResult2 = "<option value=''>Seleccione area...</option>";

function renderComboUser(area){
	var idArea = $(area).val();
	KantarDWR.getUserPorArea(idArea, {
		callback:function(resp){
			if(resp != null && resp.length > 0){
				var html = "<option value=''>Seleccione responsable...</option>";
				$.each(resp, function(){
					var row = comboT;
					row = row.replace("$_valor_$", this.id);
					row = row.replace("$_glosa_$", this.nombre + " " + this.apellido);
					html += row;
				});
				$("#comboUser").html(html);
			}else{
				$("#comboUser").html(comboTNoResult2);
			}
		}
	});	
}

var usuarioID = '${user.id}';
var idEstadoTarea = '${estado.id}';
var idTarea = '${idTarea}';
function agregarBitacora(){	
	var bitacora = {
			idTarea:idTarea,
			idAccion:6,
			fechaEstimadaFinTareaS:"2100-10-15",
			horas:$("#comboUser").val(),//usaremos este campo para enviar el nuevo responsable:Pendiente de regularizar
			minutos:"00",
			horasDedicadas:"00",
			comentarios:$("#comentarios").val(),
			idUsuario:usuarioID
	}
	//Validaciones
	var errores = "";
	if($.trim(bitacora.comentarios) == ""){
		errores += "Debe ingresar comentario al cambiar el responsable\n";
	}
	if($.trim(bitacora.horas) == ""){
		errores += "Debe especificar un usuario para cambiar el responsable\n";
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
				alert("Accion registrada");
				history.back();
			}else{
				alert("Error al reasignar responsable");
			}
		}
	});
}
</script>        
<div class='tituloPag'>Asignar responsable</div>

<jsp:include page="datosTarea.jsp" flush="true"/>

<div class='subTituloPag'>Cambiar Responsable</div>

<div>
	<div class="divControl">
		<div class="labelControl">&Aacute;rea responsable</div>
		<div class="controlBBM">
			<select id="comboAreas" onchange="renderComboUser(this);">
				<option>Cargando...</option>
			</select>
		</div>
	</div>
	<div class="divControl">
		<div class="labelControl">Nuevo Responsable</div>
		<div class="controlBBM">
			<select id="comboUser">
				<option value=''>Seleccione &aacute;rea</option>
			</select>
		</div>
	</div>
	<div class="divControl">
		<div class="labelControl">Agregar Comentarios</div>
		<div class="controlBBM">
			<textarea cols="50" rows="10" id="comentarios"></textarea>
		</div>
	</div>
</div>

<div>
	<input class="boton2" type="button" value="Volver" onclick="history.back();"/>
	<input class="boton2" type="button" value="Finalizar" onclick="agregarBitacora();"/>
</div>

<jsp:include page="generales/footer.jsp" flush="true"/>