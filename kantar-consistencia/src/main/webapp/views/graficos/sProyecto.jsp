
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
<!-- <script src="http://code.highcharts.com/highcharts.js"></script> -->
<!-- <script src="../../dwr/engine.js"></script> -->
<!-- <script src="../../dwr/interface/MonitorDWR.js"></script> -->
<script>

$(document).ready(function(){
	
	MonitorDWR.proyectosEnEjecucionV2({
		callback:function(resp){
			if(resp == null){
				alert("No hay proyectos en ejecución");
			}else{
				var proyectos = resp;
				MonitorDWR.correlativoTareas({
					callback:function(nro){
						if(nro == null){
							alert("No hay tareas para los proyectos en ejecución");
						}else{
							MonitorDWR.porcentajeAvance({
								callback:function(porc){
									if(porc == null){
										alert("No hay tareas para los proyectos en ejecución");
									}else{
										var data = [{
									        name: 'NroTarea',
									        type: 'column',
											maxPointWidth: 15,
											color: "#BDBF95",
									        yAxis: 1,
									        data: nro
										},{
											name: '% Avance',
									        type: 'line',
									        data: porc,
											color: "#FFBF00"
									    }];
										renderGrafico1(proyectos, data);
									}
								}
							});
						}
					}
				});
			}
		}
	});
});

function renderGrafico1(proyectos, data){
	
	var arrPro = [];
	$.each(proyectos, function(){
		arrPro.push(this.nombre);
	});
	
		$('#status').highcharts({
	        chart: {
				backgroundColor: "#000000",
	            zoomType: 'xy'
	        },
	        title: {
			style: { "color": "#FFFFFF" },
	            text: 'Status Proyecto'
	        },
	        subtitle: {
	            text: ''
	        },
	        xAxis: [{
	            categories: arrPro,
	            crosshair: true
	        }],
	        yAxis: [{ // Primary yAxis
				max: 140,
	            labels: {
	                format: '{value}',
	                style: {
	                    color: Highcharts.getOptions().colors[1]
	                }
	            },
	            title: {
	                text: 'Nro de Tarea',
	                style: {
	                    color: Highcharts.getOptions().colors[1]
	                }
	            }
	        }, { // Secondary yAxis
				max: 90,
	            title: {
	                text: '% Avance',
	                style: {
	                    color: "#FFBF00"
	                }
	            },
	            labels: {
	                format: '{value}%',
	                style: {
	                    color: "#FFBF00"
	                }
	            },
	            opposite: true
	        }],
	        tooltip: {
	            shared: true
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'left',
	            x: 120,
	            verticalAlign: 'top',
	            y: 100,
	            floating: true,
	            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
	        },
	        series: data
	    });
}
</script>

		<div id="status" style="width:50%; height:400px; float: left;"></div>
		<div id="status2" style="width:50%; height:400px; float: left;"></div>

