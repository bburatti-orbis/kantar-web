$(function() {
	window.datos = {};
	$("#desde").datepicker({
		inline : true
	});
	$("#hasta").datepicker({
		inline : true
	});
	var fecha = "";
	actualiza();

	var url = ruta + "Reportes/fecha";
	$.getJSON(url).done(function(data) {

		fecha = data;

	});

	function actualiza() {
		var url = ruta + "Reportes/estado/detalle";
		if (window.dattabla != null) {
			window.dattabla.desde = $("#desde").val().substr(6, 4)
					+ $("#desde").val().substr(3, 2)
					+ $("#desde").val().substr(0, 2);
			window.dattabla.hasta = $("#hasta").val().substr(6, 4)
					+ $("#hasta").val().substr(3, 2)
					+ $("#hasta").val().substr(0, 2);
			url = ruta + "Reportes/estado/detalle?inc=" + dattabla.inc + "&limit="
					+ dattabla.limit + "&desde=" + window.dattabla.desde
					+ "&hasta=" + window.dattabla.hasta;
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
		var errorCH = false;
		var errorCI = false;
		$
				.each(
						dattabla.lista,
						function(index, value) {
							errorCI = false;
							errorCH = false;
							list += "<tr class='alternar' >";
							list += "<td>" + value.idEjecucion + "</td>";
							list += "<td>" + value.pais + "</td>";
							list += "<td>" + value.periodo + "</td>";
							list += "<td>" + value.nombreBase + "</td>";
							list += "<td>" + value.ci + "</td>";
							list += "<td>" + value.ch + "</td>";
							list += "<td>" + value.fecha_ini + "</td>";
							list += "<td>" + value.fecha_ter + "</td>";
							list += "<td><div style='white-space:nowrap;'>" + value.estado + "</div></td>";
							list += "</tr>";
						});

		$("#polizas").find('tbody').html(list);
		$("#fecha").html(fecha);
		$("#desde").val(
				$.datepicker.formatDate('dd/mm/yy', new Date(
						window.dattabla.desde.substr(0, 4),
						window.dattabla.desde.substr(4, 2) - 1,
						window.dattabla.desde.substr(6, 2))));
		$("#hasta").val(
				$.datepicker.formatDate('dd/mm/yy', new Date(
						window.dattabla.hasta.substr(0, 4),
						window.dattabla.hasta.substr(4, 2) - 1,
						window.dattabla.hasta.substr(6, 2))));
		doSearchAll();
	}
	$("#volver").on("click", function() {
		event.preventDefault();
		history.back(1);
	});
	$("#actualiza").on("click", function() {
		event.preventDefault();
		actualiza();
	});

	setInterval(actualiza, 60000);

});