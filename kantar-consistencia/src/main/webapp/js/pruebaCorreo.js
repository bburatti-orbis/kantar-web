$(function() {
	$(".send").on("click", function () {
		alert("Enviando");
		
		var url = ruta + "Correo/send?body="+$("#body").val(); 
		
		$.getJSON(url).done(function(data) {

			alert(data._mensaje);

		});
		
	});
})