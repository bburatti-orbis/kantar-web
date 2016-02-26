<jsp:include page="generales/header.jsp" flush="true"/>
<style>
	.nombreYFecha{
		float:right;
	}
	
	#grillaTareasContent {
		
	}
	
th.ui-th-column div {
    white-space: normal !important;
    height: auto !important;
    padding: 2px;
}


#grillaTareasContent{
	padding-left: 2.5%;
}

.formsGrid{
	float:left;
}

label {
	color:white;
}

.dataTables_info {
	color:white!important;
}

.dataTables_wrapper .dataTables_paginate .fg-button {
	color:white!important;
}

.ui-button{
	color:white!important;
}
</style>
    <link rel='stylesheet' type='text/css' href='../../css/ui/mint-choc/jquery-ui.css' />
	<link rel='stylesheet' type='text/css' href='../../js/jqgrid/ui.jqgrid.css' />
<!-- 	<link rel='stylesheet' type='text/css' href='https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css' /> -->
    <script type='text/javascript' src='../../js/jqgrid/jquery-ui-custom.min.js'></script>
<!--     <script type='text/javascript' src='../../js/jqgrid/grid.locale-en2.js'></script> -->
<!--     <script type='text/javascript' src='../../js/jqgrid/jquery.jqGrid.js'></script> -->
<!--     <script type='text/javascript' src='https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js'></script> -->

<link rel="stylesheet" type="text/css" href="../../css/datatables.min.css"/>
 
<script type="text/javascript" src="../../js/datatables.min.js"></script>
    
<style>
	.nombreYFecha{
		float:right;
	}
	
	#grillaTareasContent {
		
	}
	
th.ui-th-column div {
    white-space: normal !important;
    height: auto !important;
    padding: 2px;
}


#grillaTareasContent{
	padding-left: 2.5%;
}

.iconGrid {
	width:20px;
/* 	-webkit-filter: invert(100%); */
}

.formsGrid{
	float:left;
}

label {
	color:white;
}

.dataTables_info {
	color:white!important;
}

.dataTables_wrapper .dataTables_paginate .fg-button {
	color:white!important;
}

.ui-button{
	color:white!important;
}

.ui-widget-header{
	border:#000000;
	background: #000000;
}

.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
	border:#000000;
	background: #000000;
}
</style>

<script>

function filtrar(){
	var inicio = $("#inicio").val();
	var fin = $("#fin").val();
	console.log("Inicio:" + inicio);
	console.log("Fin:" + fin);
	if(inicio == "" || fin == ""){
		alert("Debe ingresar fecha de inicio y fin antes de filtrar");
		return;
	}
	
	  var oTable = $('#example').dataTable();
	  oTable.fnClearTable();
	
	cargaGrilla2(inicio, fin);
}

var idUsuario = '${user.id}'
$(document).ready(function(){
	$.fn.dataTableExt.sErrMode = 'throw';
// 	jQuery("#gridid").jqGrid({
// 		   onSelectRow: function(id){ 
// 		      if(id && id!==lastSel){ 
// 		         jQuery('#gridid').restoreRow(lastSel); 
// 		         lastSel=id; 
// 		      } 
// 		      jQuery('#gridid').editRow(id, true); 
// 		   },
// 		});
	
	var m = new Date();
	var dateString = m.getUTCFullYear() +"/"+
		  ("0" + (m.getUTCMonth()+1)).slice(-2) +"/"+
		  ("0" + m.getUTCDate()).slice(-2) + " " +
		  ("0" + m.getUTCHours()).slice(-2) + ":" +
		  ("0" + m.getUTCMinutes()).slice(-2) + ":" +
		  ("0" + m.getUTCSeconds()).slice(-2);
	$("#fecha").html(dateString);
	
	//Carga grilla
	cargaGrilla(null, null);
	
});




function cargaGrilla2(inicio, fin){
	KantarDWR.getEjecutarProyectoXUserAndProyecto(idUsuario,0,inicio,fin,{
		callback:function(resp){
			if(resp != null){
				if(resp.length > 0){
					$.each(resp, function(){
						this.acciones = "<a href='#' class='formsGrid' title='Asignar responsable'><form method='post' action='../Ejecutar_proyecto/asig_resp'><input type='hidden' name='idTarea' value='"+this.id+"'/></form><img src='../../img/png/user197.png' class='iconGrid'/></a> <a href='#' class='formsGrid'  title='Ver bit&aacute;cora'><form method='post' action='../Ejecutar_proyecto/bitacora'><input type='hidden' name='idTarea' value='"+this.id+"'/></form><img src='../../img/png/configure.png' class='iconGrid'/></a>";
						if(this.desviacion < 8){
							this.desviacion = this.desviacion + " <img class='flechaInd' src='../../img/png/flecha-verde.png'/>";
						}
						if(this.desviacion >= 8 && this.desviacion < 16){
							this.desviacion = this.desviacion + " <img class='flechaInd' src='../../img/png/flecha-amarilla.png'/>";
						}
						if(this.desviacion >= 16){
							this.desviacion = this.desviacion + " <img class='flechaInd' src='../../img/png/flecha-roja.png'/>";
						}
						
						if(this.desviacionInicio < 8){
							this.desviacionInicio = this.desviacionInicio + " <img class='flechaInd' src='../../img/png/flecha-verde.png'/>";
						}
						if(this.desviacionInicio >= 8 && this.desviacion < 16){
							this.desviacionInicio = this.desviacionInicio + " <img class='flechaInd' src='../../img/png/flecha-amarilla.png'/>";
						}
						if(this.desviacionInicio >= 16){
							this.desviacionInicio = this.desviacionInicio + " <img class='flechaInd' src='../../img/png/flecha-roja.png'/>";
						}
						
						if(this.desviacionFin < 8){
							this.desviacionFin = this.desviacionFin + " <img class='flechaInd' src='../../img/png/flecha-verde.png'/>";
						}
						if(this.desviacionFin >= 8 && this.desviacionFin < 16){
							this.desviacionFin = this.desviacionFin + " <img class='flechaInd' src='../../img/png/flecha-amarilla.png'/>";
						}
						if(this.desviacionFin >= 16){
							this.desviacionFin = this.desviacionFin + " <img class='flechaInd' src='../../img/png/flecha-roja.png'/>";
						}
					});
				}
				tareasGrid3(resp);
			}else{
				tareasGrid3([]);
				alert("No hay tareas para mostrar");
			}
		}
	});	
}

function cargaGrilla(inicio, fin){
	KantarDWR.getEjecutarProyectoXUserAndProyecto(idUsuario,0,inicio,fin,{
		callback:function(resp){
			if(resp != null){
				if(resp.length > 0){
					$.each(resp, function(){
						this.acciones = "<a href='#' class='formsGrid' title='Asignar responsable'><form method='post' action='../Ejecutar_proyecto/asig_resp'><input type='hidden' name='idTarea' value='"+this.id+"'/></form><img src='../../img/png/user197.png' class='iconGrid'/></a> <a href='#' class='formsGrid'  title='Ver bit&aacute;cora'><form method='post' action='../Ejecutar_proyecto/bitacora'><input type='hidden' name='idTarea' value='"+this.id+"'/></form><img src='../../img/png/configure.png' class='iconGrid'/></a>";
						if(this.desviacion < 8){
							this.desviacion = this.desviacion + " <img class='flechaInd' src='../../img/png/flecha-verde.png'/>";
						}
						if(this.desviacion >= 8 && this.desviacion < 16){
							this.desviacion = this.desviacion + " <img class='flechaInd' src='../../img/png/flecha-amarilla.png'/>";
						}
						if(this.desviacion >= 16){
							this.desviacion = this.desviacion + " <img class='flechaInd' src='../../img/png/flecha-roja.png'/>";
						}
						
						if(this.desviacionInicio < 8){
							this.desviacionInicio = this.desviacionInicio + " <img class='flechaInd' src='../../img/png/flecha-verde.png'/>";
						}
						if(this.desviacionInicio >= 8 && this.desviacion < 16){
							this.desviacionInicio = this.desviacionInicio + " <img class='flechaInd' src='../../img/png/flecha-amarilla.png'/>";
						}
						if(this.desviacionInicio >= 16){
							this.desviacionInicio = this.desviacionInicio + " <img class='flechaInd' src='../../img/png/flecha-roja.png'/>";
						}
						
						if(this.desviacionFin < 8){
							this.desviacionFin = this.desviacionFin + " <img class='flechaInd' src='../../img/png/flecha-verde.png'/>";
						}
						if(this.desviacionFin >= 8 && this.desviacionFin < 16){
							this.desviacionFin = this.desviacionFin + " <img class='flechaInd' src='../../img/png/flecha-amarilla.png'/>";
						}
						if(this.desviacionFin >= 16){
							this.desviacionFin = this.desviacionFin + " <img class='flechaInd' src='../../img/png/flecha-roja.png'/>";
						}
						
					});
				}
				tareasGrid2(resp);
			}else{
				tareasGrid2([]);
				alert("No hay tareas para mostrar");
			}
		}
	});	
}

function accionesEvent(){
	$(".formsGrid").click(function(){
		var form = $(this).find("form").submit();
	});
}

function tareasGrid(data){
	
	var fecha = 60;
	var glosas = 80;
	var indicador = 40;

        $("#jqGrid").jqGrid({
        	beforeSelectRow: function(id){ 
		      console.log("Nada");
 		   	},
            data:data,
            mtype: "GET",
            datatype: "local",
            colModel: [
                { label: 'Nombre proyecto', name: 'nombreProyecto', key: true, width: glosas },
                { label: 'Nombre tarea', name: 'nombre', width: glosas },
                { label: 'Estado', name: 'estadoS', width: glosas },
                { label: '&Aacute;rea', name: 'area', width: glosas },
                { label:'Responsable', name: 'responsable', width: glosas },
                { label: 'Inicio estimado', name: 'inicioEstimado', width: fecha },
                { label: 'Inicio real', name: 'inicioReal', width: fecha },
                { label: 'Variaci&oacute;n inicio', name: '', width: indicador },
                { label: 'Fin estimado', name: 'finEstimado', width: fecha },
                { label: 'Fin real', name: 'finReal', width: fecha },
                { label: 'Variaci&oacute;n fin', name: 'a', width: indicador },
                { label: 'Horas de desviaci&oacute;n', name: '', width: indicador },
                { label: 'Horas dedicadas', name: 'horasDedicadas', width: indicador },
                { label: 'Acciones', name: 'acciones', width: indicador }
                
            ],
			viewrecords: true,
            width: screen.width*0.65,
            height: 250,
            rowNum: 20,
            pager: "#jqGridPager"
        });
        accionesEvent();
}


function parseDataJswonToDatatables(data){
	var d = [];
	
	$.each(data, function(){
		var row = [
		           this.nombreProyecto,
		           this.nombre,
		           this.estadoS,
		           this.area,
		           this.responsable,
		           this.inicioEstimado,
		           this.inicioReal,
		           this.desviacionInicio,
		           this.finEstimado,
		           this.finReal,
		           this.desviacionFin,
		           this.desviacion,
		           this.horasDedicadas,
		           this.acciones
		           ];
		d.push(row);
	});
	
	return d;
}



function tareasGrid3(data){
	$.fn.dataTableExt.sErrMode = 'throw';
	if(data.length > 0){
		$('#example').dataTable().fnAddData(parseDataJswonToDatatables(data));	
	}
	
    accionesEvent();
}


function tareasGrid2(data){
	
	var fecha = 60;
	var glosas = 80;
	var indicador = 40;
	
    $('#example').DataTable( {
        data: parseDataJswonToDatatables(data),
        columns: [
            { title: "Nombre proyecto" },
            { title: "Nombre tarea" },
            { title: "Estado" },
            { title: "&Aacute;rea" },
            { title: "Responsable" },
            { title: "Inicio estimado" },
            { title: "Inicio real" },
            { title: 'Variaci&oacute;n inicio'},
            { title: 'Fin estimado'},
            { title: 'Fin real'},
            { title: 'Variaci&oacute;n fin'},
            { title: 'Horas de desviaci&oacute;n'},
            { title: 'Horas dedicadas'},
            { title: 'Acciones'}            
        ]
    } );
    accionesEvent();
}
</script>
<hr/>
<div class="nombreYFecha">
	<div>${user.nombre} ${user.apellido}</div>
	<div id="fecha">cargando...</div>
</div>
<div class="tituloPag">Bandeja de Tareas</div>
<div class="divControl">
	<div class="labelControl">Fecha inicio</div>
	<div class="controlBBM">
		<input type="date" id="inicio"/>
	</div>		
</div>
<div class="divControl">
	<div class="labelControl">Fecha fin</div>
	<div class="controlBBM">
		<input type="date" id="fin"/>
	</div>		
</div>
<div>
	<input class="boton3" type="button" value="filtrar" onclick="filtrar();"/>
</div>
<br/>
<br/>
<div>
	<div class="subTituloPag">Lista de Tareas</div>
	<div id="grillaTareasContent">
	    <table id="jqGrid"></table>
	    <div id="jqGridPager"></div>
	</div>
	<div style="color:black;!important;font-size: 12px!important;overflow-x:scroll;" width="65%">
		<table id="example" class="display" width="65%"></table>
	</div>
	
</div>

        


<jsp:include page="generales/footer.jsp" flush="true"/>