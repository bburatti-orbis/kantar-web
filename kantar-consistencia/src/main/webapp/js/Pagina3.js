$(function() {
	var fecha = "";
	actualiza();
	
	var url = ruta + "Reportes/fecha";
	$.getJSON(url).done(function(data) {

		fecha = data;

	});

	function actualiza() {
		var url = ruta + "Reportes/Report";
		if(window.dattabla != null){
			url = ruta + "Reportes/Report?inc="+dattabla.inc+"&limit="+dattabla.limit+"&desde="+$("#desde").val()+"&hasta="+$("#hasta").val()+"&estado=";
		}
		$.getJSON(url).done(function(dat) {
			window.dattabla = dat;

			armartabla();

		});

	}

	function armartabla() {
		var list = "";
		var error = "";
		var clase = "";
		$.each(dattabla.lista, function(index, value) {

			list += "<tr class='alternar'>";
			list += "<td>" + value.nombre + "</td>";
			list += "<td>" + value.glosa + "</td>";
			list += "<td>" + value.descripcion + "</td>";
			list += "<td>" + value.cliente + "</td>";
			list += "<td>" + value.usuario + "</td>";
			list += "<td>" + value.periodo + "</td>";
			if (value.ci == "ERRONEA") {
				clase = "class='error'";
			} else {
				clase = "class='ok'";
			}

			list += "<td><a href='" + ruta + "Reportes/nomenclatura?codigo="
					+ value.idBases + "' " + clase + " >" + value.ci
					+ "</a></td>";
			if (value.ch == "ERRONEA") {
				clase = "class='error'";
			} else {
				clase = "class='ok'";
			}

			if (value.ch == "NO APLICA") {
				list += "<td> <a  " + clase + ">" + value.ch + "</a></td>";
			} else {
				list += "<td> <a href='" + value.link + "' " + clase + ">"
						+ value.ch + "</a></td>";
			}

			list += "<td>" + value.fecha_ini + "</td>";
			list += "<td>" + value.fecha_ter + "</td>";
			list += "<td>" + value.estado + "</td></tr>";
		});

		$("#polizas").find('tbody').html(list);
		$("#fecha").html(fecha);
		$("#desde").val(window.dattabla.desde);
		$("#hasta").val(window.dattabla.hasta);
	}
	$("#volver").on("click", function() {
		event.preventDefault();
		history.back(1);
	});

	setInterval(actualiza, 60000);

});