$(function() {
	var fecha="";
	var codigo= getParameterByName("codigo");
	
	
	var url = ruta + "Reportes/nomenclatura/nomenc?codigo="+codigo;
	
	$.getJSON(url).done(function(dat) {
		window.dattabla = dat;

		armartabla();

	});
	
	function armartabla() {
		var list="";
		var error="";
		var pais="";
		var periodo="";
		var base="";
		var fecha="";
		var clase="";
		var hola = "linea";
		$.each(dattabla, function(index, value) {
			pais=value.pais;
			periodo=value.periodo;
			base=value.base;
			fecha=value.fecha;
			list += "<tr class='alternar'>";
			list += "<td class='linea'>" + value.nomenclatura + "</td>";
			if(value.tipo==2){
				list += "<td>IT2</td>";
			}
			else{
				list += "<td>IT0</td>";
			}
			if(value.estado=="ERRONEA"){
				clase="class='error'";
			}
			else{
				clase="class='ok'";
			}
			
			list += "<td class='monto'>" + value.diferencia + "</td>";
			list += "<td  "+clase+" >" + value.estado + "</td>";

			list += "<td><a href='"+ruta+"Reportes/detallenom?codigo="+value.id_nom+"'>DETALLE</a></td></tr>";
		});

		$("#polizas").find('tbody').html(list);
		$("#fecha").html(fecha);
		$("#pais").html(pais);
		$('#periodo').html(periodo);
		$("#base").html(base);
	}
	
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	$("#volver").on("click",function() {
	    event.preventDefault();
	    history.back(1);
	});
});