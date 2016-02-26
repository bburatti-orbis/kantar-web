<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="../../dwr/engine.js"></script>
<script src="../../dwr/interface/KantarDWR.js"></script>
<jsp:include page="../../views/generales/header.jsp" flush="true"/>
<script>
var tRow = "<tr><td><a href='#' id='link-$_idL_$' onclick='doalert(this); return false;'>$_proyecto_$</td><td>&_fecha_&</td></tr>";
$(document).ready(function(){
	KantarDWR.ejecucionProyectos({
		callback:function(resp){
			if(resp == null){
				alert("No hay proyectos en ejecución");
			}else{
				var filas = "";
				$.each(resp, function(){
					var fila = tRow;
					fila = fila.replace("$_proyecto_$", this.nombre);
					fila = fila.replace("&_fecha_&", this.fechaInicio);
					/* fila = fila.replace("$_id_$", this.id); */
					fila = fila.replace("$_idL_$", this.id);
					filas += fila;
				});
				$("#cuerpoTabla").html(filas);
			}
		}
	})
}),
function renderTabla(){
}
function doalert(obj) {
    location.href = "reporteRevisarProyecto?i=" + obj.getAttribute("id").split("-")[1];
}
</script>
<style type="text/css">
table{
	background-color: white;
    color: black;
    border-collapse: collapse;
    width:100%;
}
th, td {
    border: 1px solid black;
    height: 20px; 
    width:180px;
    text-align:center; 
    vertical-align:middle;
}
th{
	color: black;
	font-weight: bold;
}
}
</style>
</head>
<body>
<div class="caja_titulo"><p>Proyectos a revisar</p></div>
<br/>
<br/>
<br/>
<br/>
<table>
		 <thead>
		  <tr>
		   <th>Proyecto</th>
		   <th>Fecha Inicio Producci&oacuten</th>
		   <!-- <th>id</th> -->
		  </tr>
		 </thead>
		 <tbody id="cuerpoTabla">
		   <tr>
		    <td colspan="3">cargando...</td>
		   </tr>
		 </tbody>
	</table>
</body>
<jsp:include page="../../views/generales/footer.jsp" flush="true"/>
</html>