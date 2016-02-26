$(function() {
	ruta2="/parser/webresources/";
	
	$('#verproyecto').on('click',function (){
		location.href=ruta+"gestion";
	});
	$('#creaproyecto').on('click',function (){
		location.href=ruta+"gestion/crearproyec";
	});
	$('#creaconjunto').on('click',function (){
		location.href=ruta+"gestion/crearconjunto";
	});
	$('#verprocesos').on('click',function (){
		location.href=ruta+"Reportes";
	});
	$('#act_tarea').on('click',function (){
		location.href=ruta+"Ejecutar_proyecto";
	});
	$('#asig_resp').on('click',function (){
		location.href=ruta+"Ejecutar_proyecto/asig_resp";
	});
	$('#bitacora').on('click',function (){
		location.href=ruta+"Ejecutar_proyecto/bitacora";
	});	
	$('#ejecucion').on('click',function (){
		location.href=ruta+"Ejecutar_proyecto/ejecucion";
	});
	$('#formulas').on('click',function (){
		location.href=ruta2;
	});
	$('#status').on('click',function (){
		location.href=ruta+"Monitor_workflow/statusProyecto";
	});
	$('#performance').on('click',function (){
		location.href=ruta+"Monitor_workflow/perforEjecutivo";
	});
	$('#plazos').on('click',function (){
		location.href=ruta+"Monitor_workflow/revisarPlazos";
	});
	$('#prueba').on('click',function (){
		location.href=ruta+"Monitor_workflow/prueba";
	});
	$('#performanceEjec').on('click',function (){
		location.href=ruta+"Reportes/perforEjecutivo";
	});
	$('#revisarProyecto').on('click',function (){
		location.href=ruta+"Reportes/revisarProyecto";
	});
	$('#mantenedor').on('click',function (){
		location.href=ruta+"KPI/kpi_kpi";
	});
});
