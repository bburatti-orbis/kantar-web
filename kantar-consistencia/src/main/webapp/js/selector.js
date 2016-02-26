$(function() {	

$('#todo').on("click", function(){
	
	$.ajax({
		url:ruta+ 'selector/valida',
		type: 'GET',
		dataType: 'json',
		success: function (data) {
			
		alert('Base de datos Validada')
		
		},
	});


	window.location.href = ruta+"Reportes";
		});	
$('#reporte').on("click", function(){



	window.location.href = ruta+"Reportes";
		});	
});