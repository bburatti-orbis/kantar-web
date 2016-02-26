
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="../../dwr/engine.js"></script>
<script src="../../dwr/interface/MonitorDWR.js"></script>
<script>
$(function () {
	//DWR
	MonitorDWR.performance({
		callback:function(resp){
			if(resp == null){
				alert("No hay data disponible");
			}else{
				var data = [];
				$.each(resp, function(){
					var dato = {
						idArea:this.areaId,
						glosaArea:this.areaGlosa,
						idEstado:this.estadoId,
						glosaEstado:this.estadoGlosa,
						total:this.total
					}
					data.push(dato);
				}
				);
				if(data.length > 0){
					renderTorta(data);	
				}else{
					alert("No hay data disponible 2");	
				}
			}
		}
	});
});

function renderTorta(datos){
	var colors = Highcharts.getOptions().colors;
	var areas = [];
	var datosG = [];
	var idArea = 0;
	var idEstado = 0;
	var i;
	var d = null;
	var j;
	var drillDataLen, brightness;
	var sumArea = 0;
	var sumArea = 0;
	for(i=0;i<datos.length;i++){
		if(idArea != datos[i].idArea){
			idArea = datos[i].idArea;
			areas.push(datos[i].glosaArea);
			d = {
				y:datos[i].total,
		        color: colors[i],
		        drilldown: {
		            name: datos[i].glosaArea,
		            categories: [],
		            data: [],
		            color: colors[i]			
				}
			};
			d.drilldown.categories.push(datos[i].glosaEstado);
			d.drilldown.data.push(datos[i].total);
			
			datosG.push(d);
		}else{
			d.drilldown.categories.push(datos[i].glosaEstado);
			d.drilldown.data.push(datos[i].total);
			d.y = d.y+datos[i].total;
		}
	}
	var k;
	var sumTotal = 0;
	for(k=0;k<datosG.length;k++){
		sumTotal+=datosG[k].y;
		var m;
		for(m=0;m<datosG[k].drilldown.data.length;m++){
			//datosG[k].drilldown.data[m]= Math.round((100*datosG[k].drilldown.data[m]/datosG[k].y) * 100) / 100 ;
			//datosG[k].dirlldown.data[m]=Math.round();
		}
	}
	for(k=0;k<datosG.length;k++){
		datosG[k].y=Math.round((100*datosG[k].y/sumTotal) * 100) / 100;
	}
	
    categories = areas,
    data = datosG,
    browserData = [],
    versionsData = [],
    i,
    j,
    dataLen = data.length,
    drillDataLen,
    brightness;
//Build the data arrays
for (i = 0; i < dataLen; i += 1) {
    // add browser data
    browserData.push({
        name: categories[i],
        y: data[i].y,
        color: data[i].color
    });
    // add version data
    drillDataLen = data[i].drilldown.data.length;
    for (j = 0; j < drillDataLen; j += 1) {
        brightness = 0.2 - (j / drillDataLen) / 5;
        versionsData.push({
            name: data[i].drilldown.categories[j],
            y: data[i].drilldown.data[j],
            color: Highcharts.Color(data[i].color).brighten(brightness).get()
        });
    }
}

render2(browserData, versionsData);

}

function render2(browserData, versionsData){

	// Create the chart
	$('#perfomance').highcharts({
	    chart: {
	        type: 'pie'
	    },
	    title: {
	        text: 'Perfomance de Ejecutivos por Área, Marzo 2015'
	    },
	    yAxis: {
	        title: {
	            text: 'Total percent market share'
	        }
	    },
	    plotOptions: {
	        pie: {
	            shadow: false,
	            center: ['50%', '50%']
	        }
	    },
// 	    tooltip: {
// 	        valueSuffix: '%'
// 	    },
	    series: [{
	        name: 'Areas',
	        data: browserData,
	        size: '60%',
	        dataLabels: {
	            formatter: function () {
	                return this.y > 5 ? this.point.name : null;
	            },
	            color: '#ffffff',
	            distance: -30
	        }
	    ,tooltip: {
	        valueSuffix: '%'
	    }
	    }, {
	        name: 'Estados',
	        data: versionsData,
	        size: '80%',
	        innerSize: '60%',
	        dataLabels: {
	            formatter: function () {
	                // display only if larger than 1
	                return this.y > 1 ? '<b>' + this.point.name + ':</b> ' + this.y + '' : null;
	            }
	        }
	    ,tooltip: {
	        valueSuffix: ''
	    }
	    }]
	});
	
}
</script>

	<div id="perfomance" style="width:50%; height:400px;float: left;"></div>