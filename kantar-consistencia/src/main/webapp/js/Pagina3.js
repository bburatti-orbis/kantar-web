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
		var url = ruta + "Reportes/Report";
		if (window.dattabla != null) {
			window.dattabla.desde = $("#desde").val().substr(6, 4)
					+ $("#desde").val().substr(3, 2)
					+ $("#desde").val().substr(0, 2);
			window.dattabla.hasta = $("#hasta").val().substr(6, 4)
					+ $("#hasta").val().substr(3, 2)
					+ $("#hasta").val().substr(0, 2);
			url = ruta + "Reportes/Report?inc=" + dattabla.inc + "&limit="
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
							list += "<td>" + value.nombre + "</td>";
							list += "<td>" + value.glosa + "</td>";
							list += "<td>" + value.descripcion + "</td>";
							list += "<td>" + value.cliente + "</td>";
							list += "<td>" + value.usuario + "</td>";
							list += "<td>" + value.periodo + "</td>";
							if (value.ci == "ERRONEA") {
								clase = "class='error'";
								errorCI = true;
							} else {
								clase = "class='ok'";
							}

							list += "<td><a href='" + ruta
									+ "Reportes/nomenclatura?codigo="
									+ value.idBases + "' " + clase + " >"
									+ value.ci + "</a>";
							if (errorCI) {
								list += " <i class='ui-icon ui-icon-check errorCI' data-id='"
											+ value.id + "'></i>";
							}
							list += "</td>";
							if (value.ch == "ERRONEA") {
								clase = "class='error'";
								errorCH = true;
							} else {
								clase = "class='ok'";
							}

							if (value.ch == "NO APLICA") {
								list += "<td> <a  " + clase + ">" + value.ch
										+ "</a></td>";
							} else {
								list += "<td> <a href='" + value.link + "' "
										+ clase + ">" + value.ch + "</a>";
								if (errorCH) {
									list += " <i class='ui-icon ui-icon-check errorCH' data-id='"
											+ value.id + "'></i>";
								}
								list += "</td>";
							}

							list += "<td>" + value.fecha_ini + "</td>";
							list += "<td>" + value.fecha_ter + "</td>";
							list += "<td><div style='white-space:nowrap;'>" + value.estado;
							if(value.estado == "TERMINADA" && (value.ci != "ERRONEA" && value.ch != "ERRONEA")){
								list += "<i class='ui-icon ui-icon-mail-closed'></i>"
							}
							list += "</div></td></tr>";
						});

		$("#polizas").find('tbody').html(list);
		$("#fecha").html(fecha);
		// $("#desde").val(window.dattabla.desde);
		$("#desde").val(
				$.datepicker.formatDate('dd/mm/yy', new Date(
						window.dattabla.desde.substr(0, 4),
						window.dattabla.desde.substr(4, 2) - 1,
						window.dattabla.desde.substr(6, 2))));
		// $("#hasta").val(window.dattabla.hasta);
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

	function confirmDialog(msg) {
		var def = $.Deferred();
		$('#dialog').dialog('option', 'title', msg);

		$('#dialog').dialog({
			resizable : false,
			autoOpen : true,
			modal : true,
			buttons : {
				'OK' : function() {
					def.resolve();
					$(this).dialog("close");
				},
				'Cancel' : function() {
					def.reject();
					$(this).dialog("close");
				}
			}
		});
		return def.promise();
	}

	$("#polizas").on(
			"click",
			".errorCH",
			function() {
				event.preventDefault();
				$("#glosa").val("");
				datos.id = $(this).data('id');
				confirmDialog("Autorizacion Consistencia Historica")
					.done(
						function() {
							datos.glosa = $("#glosa").val();
							datos.autoriza = "historica";
							$.ajax({
								type: "POST",
								contentType: "application/json; charset=utf-8",
								url: ruta+ "Reportes/autoriza",
								data: JSON.stringify(
										datos
								),
								dataType: "json",
								success: function(response){
									if(response._rslt == "ERROR"){
										alert(response._mensaje);
									}else{
										actualiza();
									}
								}
							});
							
						})
					.fail(function() {
						// cry a little
					});
			});
	$("#polizas").on(
			"click", 
			".errorCI", 
			function() {
				event.preventDefault();
				$("#glosa").val("");
				datos.id = $(this).data('id');
				confirmDialog("Autorizacion Consistencia Interna")
					.done(function() {
						datos.glosa = $("#glosa").val();
						datos.autoriza = "interna";
						$.ajax({
							type: "POST",
							contentType: "application/json; charset=utf-8",
							url: ruta+ "Reportes/autoriza",
							data: JSON.stringify(
									datos
							),
							dataType: "json",
							success: function(response){
								if(response._rslt == "ERROR"){
									alert(response._mensaje);
								}else{
									actualiza();
								}
							}
						})
					})
					.fail(function() {
						// cry a little
					});
			});

	$("#dialog").dialog({
		modal : true,
		autoOpen : false,
		width : 'auto',
		buttons : {
			'OK' : function() {
				def.resolve();
				$(this).dialog("close");
			},
			'Cancel' : function() {
				def.reject();
				$(this).dialog("close");
			}
		},
	// close: {
	// $( this ).remove();
	// }
	});

	setInterval(actualiza, 60000);

});