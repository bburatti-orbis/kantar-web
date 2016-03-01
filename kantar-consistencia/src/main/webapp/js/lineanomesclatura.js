$(function() {
	var fecha="";
	var codigo= getParameterByName("codigo");
	
	
	var url = ruta + "Reportes/nomenclatura/detallenomenc?codigo="+codigo;
	
	$.getJSON(url).done(function(dat) {
		window.dattabla = dat;

		armartabla();

	});
	
	function armartabla() {
		var list="";
		var error="";
		var pais="";
		var base="";
		var fecha="";
		var nom="";
		var tipo="";
		var clase="";
		$.each(dattabla, function(index, value) {
			pais=value.pais;
			base=value.base;
			fecha=value.fecha;
			nom=value.nomenclatura;
			if(value.tipo_nomencl==2){
				tipo="IT2";
			}
			else if(value.tipo_nomencl==0){
				tipo="IT0";
			}
			list += "<tr class='alternar'>";
			var espacio="";
			for(var t=0; t<value.nivel; t++){
				espacio+="&nbsp;&nbsp;";
			}
			list += "<td class='linea'>"+ value.linea + "</td>";
			list += "<td>" + espacio + value.glosa + "</td>";
			list += "<td>" + value.nivel + "</td>";
			list += "<td>" + value.totalInformado + "</td>";
			list += "<td>" + value.totalCalculado + "</td>";
			var a =value.totalInformado-value.totalCalculado;
			if(a==0){
				clase="class='error'";
			}
			else{
				clase="class='error'";
			}
			
			list += "<td  "+clase+" >" + a + "</td>";
			clase="";
			if(value.estado=="ERRONEA"){
				clase="class='error'";
			}
			else{
				clase="class='ok'";
			}
			list += "<td  "+clase+"  >" + value.estado + "</td></tr>";
		});

		$("#polizas").find('tbody').html(list);
		$("#fecha").html(fecha);
		$("#pais").html(pais);
		$("#base").html(base);
		$("#Nomenclatura").html(nom);
		$("#tipo").html(tipo);
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