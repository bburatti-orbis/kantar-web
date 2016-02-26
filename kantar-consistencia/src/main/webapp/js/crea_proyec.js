$(function() {
	
	window.criterio = {};

var url=ruta+"Work_Flow/listadatos";
	$.getJSON(url).done(function(dat) {
		window.dat = dat;
		var list= "<option value='0'></option>";
		$.each(dat , function(j,i){
			 list+= "<option value='"+j+"'>"+i+"</option>";
			
		} );
			
		 $("#pais").html(list);  
		
	});	


	$("#volver").on("click",function() {
		location.href=ruta+"gestion";
	});
	
	$('#iniciar').on("click", function (){
		if($("#id_ciclo").val()==""){
			
			alert("debe ingresar una programacion para el proyecto");
		}
		else{
			
			
			criterio.id_ciclo=$("#id_ciclo").val();
			criterio.nombre=$('#nombre').val();
			criterio.pais=$('select#pais').val();
			criterio.calendario=$('select#calendario').val();
			criterio.calendarioex=$('select#calendarioex').val();
			document.getElementById("iniciar").disabled = true;
			$.post(ruta + "gestion/inserproyec", criterio, function(
					data, textStatus) {
			 $('#id_ciclo').val("");
			
				var r=ruta+"gestion";
				location.href=r;

			}, "json");
		}
	});
	
	

});
