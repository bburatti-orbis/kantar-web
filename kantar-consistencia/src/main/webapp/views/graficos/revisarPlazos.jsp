
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
				MonitorDWR.tareasAtrasadas({
					callback:function(resp){
						if(resp == null){
							alert("No se pudo obtener las tareas atrasadas");
						}else{
							var tareas = resp;
							MonitorDWR.desviacion({
								callback:function(resp){
									if(resp == null){
										alert("No se pudo calcular las desviaciones");
									}else{
										var desviaciones = resp;
										renderGrafico3(proyectos, tareas, desviaciones);
									}
								}
							})
							
						}
					}
			})
		}
	}});
});

function renderGrafico3(proyectos, tareas, desviaciones){
	
	var arrPro = [];
	$.each(proyectos, function(){
		arrPro.push(this.nombre);
	});
	
	$('#plazos').highcharts({
        chart: {
            zoomType: 'xy'
        },
        title: {
            text: 'Tareas en Ejecución'
        },
        subtitle: {
            text: ''
        },
        xAxis: [{
            categories: arrPro,
            crosshair: true
        }],
        yAxis: [{ // Primary yAxis
            labels: {
                format: '{value}%',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            },
            title: {
                text: '% Tareas Atrasadas',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            }
        }, { // Secondary yAxis
            title: {
                text: '% Horas desaviación',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            },
            labels: {
                format: '{value}%',
                style: {
                    color: Highcharts.getOptions().colors[0]
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
        series: [{
            name: 'Desviación',
            type: 'column',
            yAxis: 1,
            data: desviaciones,
            tooltip: {
                valueSuffix: '%'
            }

        }, {
            name: 'Atrasadas',
            type: 'spline',
            data: tareas,
            tooltip: {
                valueSuffix: '%'
            }
        }]
    });
}
</script>

	<div id="plazos" style="width:50%; height:400px;float: left;"></div>

