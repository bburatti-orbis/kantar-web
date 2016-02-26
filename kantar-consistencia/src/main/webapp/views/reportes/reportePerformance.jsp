<!DOCTYPE html>
<html>
<head>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="../../dwr/engine.js"></script>
<script src="../../dwr/interface/KantarDWR.js"></script> -->
<jsp:include page="../../views/generales/header.jsp" flush="true"/>
<script>
var tRow = "<tr><td>$_proyecto_$</td><td>&_pais_&</td><td>$_area_$</td><td>$_ejecu_$</td><td>$_tareasR_$</td><td>$_tareasTot_$</td><td>$_porcen_$</td></tr>";
$(document).ready(function(){
	KantarDWR.performance({
		callback:function(resp){
			if(resp == null){
				alert("No hay proyectos en ejecución");
			}else{
				var filas = "";
				$.each(resp, function(){
					var fila = tRow;
					//Aca se hacen los replace
					fila = fila.replace("$_proyecto_$", this.nombreProyecto);
					fila = fila.replace("&_pais_&", this.pais);
					fila = fila.replace("$_area_$", this.area);
					fila = fila.replace("$_ejecu_$", this.nombreEjecutivo);
					fila = fila.replace("$_tareasR_$", this.tareasRealizadas);
					fila = fila.replace("$_tareasTot_$", this.tareasTotales);
					fila = fila.replace("$_porcen_$", this.porcParticipacion);
					filas += fila;
				});
				$("#cuerpoTabla").html(filas);
			}
		}
	})
}),
function renderTabla(){
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

<div class="caja_titulo"><p>Performance ejecutivo</p></div>
<br/>
<br/>
<br/>

<table>
		 <thead>
		  <tr>
		   <th>Proyecto</th>
		   <th>Pa&iacutes</th>
		   <th>&Aacuterea</th>
		   <th>Ejecutivo</th>
		   <th>Tareas realizadas</th>
		   <th>Tareas totales por proyecto</th>
		   <th>% de participacion</th>
		  </tr>
		 </thead>
		 <tbody id="cuerpoTabla">
		   <tr>
		    <td colspan="7">cargando...</td>
		   </tr>
		 </tbody>
	</table>
	<div id="perfomance" style="width:50%; height:400px;"></div>
</body>
<jsp:include page="../../views/generales/footer.jsp" flush="true"/>
</html>