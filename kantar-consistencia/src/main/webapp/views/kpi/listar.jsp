<html>
<jsp:include page="../../views/generales/header.jsp" flush="true"/>
<head>
	<link rel='stylesheet' type='text/css' href='../../css/ui/mint-choc/jquery-ui.css' />
	<link rel='stylesheet' type='text/css' href='../../js/jqgrid/ui.jqgrid.css' />
    <script type='text/javascript' src='../../js/jqgrid/jquery-ui-custom.min.js'></script>
	<link href="../../css/menu.css" rel="stylesheet">
	<link href="../../css/estilos.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../../css/datatables.min.css"/>
	<script type="text/javascript" src="../../js/datatables.min.js"></script>
	<script src="../../dwr/engine.js"></script>
	<script src="../../dwr/interface/ModuloKpiDWR.js"></script>
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


$(document).ready(function(){
	$.fn.dataTableExt.sErrMode = 'throw';
	
	//Carga grilla
	cargaGrilla(null);
});

 function cargaGrilla(nombre_kpi){
	ModuloKpiDWR.listadoKpi(nombre_kpi,{
		callback:function(resp){
			if(resp != null){
				tareasGrid2(resp);
			}else{
				alert("No existen Kpi's para mostrar");
			}
		}
	});
}

 function parseDataJswonToDatatables(data){
		var d = [];
		/* var editTemplate = "<form action='Editar' method='post'><input type='hidden' name='id' value='$_id_$'/><input type='submit' value='Editar'/></form>"; */
		var editTemplate = "<form action='kpi_kpi/editar' method='post'><input type='hidden' name='id' value='$_id_$'/><input type='submit' value='Editar'/></form>";
		$.each(data, function(){
			var editar = editTemplate;
			editar = editar.replace("$_id_$", this.id);
			var row = [
			           this.nombreKpi,
			           this.tipoKpi,
			           this.pais,
			           this.alcance,
			           this.area,
			           this.categoria,
			           editar			           
			           ];
			d.push(row);
		});
		
		return d;
	}

function tareasGrid2(data){
	
    $('#example').DataTable( {
        data: parseDataJswonToDatatables(data),
        columns: [
            { title: "Nombre Kpi" },
            { title: "Tipo Kpi" },
            { title: "Pais" },
            { title: "Alcance" },
            { title: "&Aacute;rea" },
            { title: "Categor&iacute;a" },
            { title: "Editar" }
        ]
    } );
}

function crear(){
   	location.href = document.URL+"/crear";
}
</script>
</head>
<body>
<div>
	<div class="subTituloPag">Listado de Kpi's</div>
	<div style="color:black;!important;font-size: 12px!important;overflow-x:scroll;" width="65%">
		<table id="example" class="display" width="65%"></table>
		<br/>
		<input class="boton3" type="button" value="Crear" onclick="crear();"/>
	</div>
</div>
</body>
<jsp:include page="../../views/generales/footer.jsp" flush="true"/>



</html>