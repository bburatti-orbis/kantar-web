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
function flujo1(inicio, fin){
	KantarDWR.flujo1DWR({
		callback:function(resp){
			alert(resp);
		}
	});	
}
</script>
<hr/>
<div class="tituloPag">Flujos motor</div>
<div class="divControl">
	<div class="labelControl">Flujo 1 para crear las tareas ejecucion segun logica de negocios de template de proyectos</div>
	<div class="controlBBM">
		<input type="button" id="inicio" value="Flujo 1" onclick="flujo1();"/>
	</div>		
</div>
<jsp:include page="generales/footer.jsp" flush="true"/>