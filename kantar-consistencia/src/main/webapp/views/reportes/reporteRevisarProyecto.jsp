<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="../../dwr/engine.js"></script>
<script src="../../dwr/interface/KantarDWR.js"></script>
<jsp:include page="../../views/generales/header.jsp" flush="true"/>
<script>
var tRow = "<tr><td>$_Proyecto_$</td><td>$_Tarea_$</td><td>$_Area_$</td><td>$_Responsable_$</td><td>$_Estado_$</td><td>$_Fecha inicio estimada_$</td><td>$_Fecha termino estimada_$</td><td>$_Hrs estimadas_$</td><td>$_Hrs dedicadas_$</td><td>$_¿Atrasada?_$</td><td>$_Hrs atraso_$</td><td title='$_comentarios_$' >Ver</td></tr>";
$(document).ready(function(idProyecto){
	idProyecto='${i}';
	KantarDWR.reporteProyecto(idProyecto,{
		callback:function(resp){
			if(resp == null){
				alert("No hay proyectos en ejecución");
			}else{
				var filas = "";
				$.each(resp, function(){
					var fila = tRow;
					//Aca se hacen los replace
					fila = fila.replace("$_Proyecto_$",this.nombreProyecto);
					fila = fila.replace("$_Tarea_$",this.nombreTarea);
					fila = fila.replace("$_Area_$",this.nombreArea);
					fila = fila.replace("$_Responsable_$",this.nombreResponsable);
					fila = fila.replace("$_Estado_$",this.estado);
					fila = fila.replace("$_Fecha inicio estimada_$",this.inicioEstimada);
					fila = fila.replace("$_Fecha termino estimada_$",this.terminoEstimada);
					fila = fila.replace("$_Hrs estimadas_$",this.hrsEstimadas);
					fila = fila.replace("$_Hrs dedicadas_$",this.hrsDedicadas);
					fila = fila.replace("$_¿Atrasada?_$",this.atrasada);
					fila = fila.replace("$_Hrs atraso_$",this.hrsAtrasado);
					fila = fila.replace("$_comentarios_$",this.comentario);
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
    /* width:100%; */
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

<div class="caja_titulo"><p>Detalle de proyecto</p></div>
<br/>
<br/>
<br/>

<table>
		 <thead>
		  <tr>
		   <th>Proyecto</th>
		   <th>Tarea</th>
		   <th>Area</th>
		   <th>Responsable</th>
		   <th>Estado</th>
		   <th>Fecha inicio estimada</th>
		   <th>Fecha termino estimada</th>
		   <th>Hrs estimadas</th>
		   <th>Hrs dedicadas</th>
		   <th>Atrasada</th>
		   <th>Hrs atraso</th>
		   <th>Comentarios</th>
		  </tr>
		 </thead>
		 <tbody id="cuerpoTabla">
		   <tr>
		    <td colspan="12">cargando...</td>
		   </tr>
		 </tbody>
	</table>
<br/>
<br/>
<br/>
	<input type="button" class="boton2" value="Volver" onclick="history.back();"/>
</body>
<jsp:include page="../../views/generales/footer.jsp" flush="true"/>
</html>