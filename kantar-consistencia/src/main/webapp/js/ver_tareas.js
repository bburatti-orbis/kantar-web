$(function() {
	
	 window.criterio = {};
	var codigo= getParameterByName("accion");
	var nombre= getParameterByName("nombre");
	$("#nombre").html(nombre);
	$("#id").html(codigo);
	window.cod = {};

	var url = ruta + "gestion/gettareas?codigo="+codigo;
	
	$.getJSON(url).done(function(dat) {
		window.dattabla = dat;

		armartabla();

	});
	function armartabla() {
		var list="";
		$.each(dattabla, function(index,value) {
			list += "<tr class='alternar' value='"+value.id+"'>";
			list += "<td class='linea'>" + value.id + "</td>";
			list += "<td class='linea'>" + value.nombre + "</td>";
			list += "<td class='linea'>" + value.plazo + " "+value.tiempo+ "</td>";
			list += "<td class='linea'>" + value.nomencargado + "</td>";
			list += "<td class='linea'>" + value.idantesesora + "</td>";
			list += "<td>"
			list +=   "<img SRC='../../img/guion.png' class='eliminar' value='"+value.id+"'>"
			//list +=   "<a><IMG SRC='../../img/suma.png' class='agregar' BORDER=2 ALT='DETALLE'></a>"
			list +=   "<IMG SRC='../../img/editar.png' class='editar' BORDER=2 ALT='EDITAR'>"
			list += "</td></tr>";
		});
		$("#polizas").find('tbody').html(list);
	}
	
	$('#tareas').on("click", function (){
		location.href=ruta+"gestion/creartarea?codigo="+$("#id").html()+"&&nombre="+$("#nombre").html();
	
	setTimeout ("redireccionar()", 1);
	});
	
	
	
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	
	$(document).on("click",'.eliminar', function (event){
		var tr=$(this).closest('tr');
		 cod.valor=tr.attr('value');

		$.post(ruta + "gestion/setestadotarea", cod, function(
				data, textStatus) {
			tr.remove();
			

		}, "json");



	});
	
	
	$(document).on("click",'.editar', function (event){
		var tr=$(this).closest('tr');
		var valor=tr.attr('value');
		
		var url = ruta + "gestion/gettarea/tarea?codigo="+valor;
		
		$.getJSON(url).done(function(dat) {
			
			$("#id_tarea").val(dat.id);
			$("#Nombre").val(dat.nombre);
			$("#plazo").val(dat.plazo);
			$("#delay").val(dat.delay);
			window.codigo=dat.proyecto_id;
			$("#frecuencia").val(dat.delay);
			var antecesoras=dat.idantesesora;
			if(dat.idconjunto==0){
				$('input[name="option1"]').prop("checked", true);
				$('#conjuntos').prop('disabled', true);	
			}
			else{
				var url=ruta+"gestion/getciclo?accion="+dat.idconjunto;
				$.getJSON(url).done(function(dato) {

					var list= "<option value='0'></option>";
					$.each(dato , function(j,i){
						if(dat.idconjunto==i.id){
							 list+= "<option value='"+i.id+"'selected >"+i.nombre+"</option>";
						}
						else {
							list+= "<option value='"+i.id+"' >"+i.nombre+"</option>";
						}
						
						
					} );
						
					 $("#conjuntos").html(list);  
					
				});
			}
			
			
			var url=ruta+"gestion/listusuarios";
			var area="";
			$.getJSON(url).done(function(dato) {
			
				var list= "<option value='0'></option>";
				$.each(dato , function(j,i){
					if(dat.idresponsable==i.id){
						list+= "<option value='"+i.id+"' selected>"+i.nombre+" "+i.apellido+"</option>";
						area=i.area;
					}
					else{
						list+= "<option value='"+i.id+"'>"+i.nombre+" "+i.apellido+"</option>";
					}
					
					 
					
				} );
				 $("#users").html(list);  
					
			});
			var url=ruta+"Work_Flow/getarea";
			$.getJSON(url).done(function(dat) {
				window.dat = dat;
				var list= "<option value='0'></option>";
				$.each(dat , function(j,i){
					if (area== i.id){
						list+= "<option value='"+i.id+"' selected>"+i.descripcion+"</option>";	
					}
					else{
						list+= "<option value='"+i.id+"'>"+i.descripcion+"</option>";	
					}
					 
					
				} );
					
				 $("#area").html(list);  
				
			});
			
			var url = ruta + "gestion/gettareas?codigo="+codigo;

			
			$.getJSON(url).done(function(dat) {
				window.dattabla = dat;

				var list= "<select id='tareas' class='select'  multiple='multiple' >";
				$.each(dat , function(j,i){
					 list+= "<option value='"+i.id+"'>"+i.nombre+"</option>";
					
					

			});
				
			list+= "</select>";
			$("#sele").html(list);  
				
			    $("select#tareas").multipleSelect({
			        filter: true
			    });
			    $("select#tareas").multipleSelect("setSelects", antecesoras);
			});
			
			var url=ruta+"gestion/getciclo?accion="+dat.idconjunto;
			$.getJSON(url).done(function(dato) {
				var list= "<option value='0'></option>";
				$.each(dato , function(j,i){
					
					if(dat.idconjunto==i.id){
						list+= "<option value='"+i.id+"' selected>"+i.nombre+"</option>";
					}
					else{
						list+= "<option value='"+i.id+"'>"+i.nombre+"</option>";
					}
					
					
				} );
					
				 $("#conjuntos").html(list);  
				
			});
				
			$('#tiempo > option[value="'+dat.tiempo+'"]').attr('selected', 'selected');
			$('#tiempodelay > option[value="'+dat.tiempodelay+'"]').attr('selected', 'selected');
			$('#tareas > option[value="'+dat.idantesesora+'"]').attr('selected', 'selected');
			$('#calendario > option[value="'+dat.tipo_calendario+'"]').attr('selected', 'selected');
			 

			$('#ex').modal();
		});
		
		});
	
	
	
	$('#editartarea').on("click", function (){
		criterio.Nombre=$("#Nombre").val();
		criterio.id=$('#id_tarea').val();
		criterio.users=$('select#users').val();
		criterio.plazo=$('#plazo').val();
		
		if($('select#tiempo').val() === null){
			alert('Debe indicar una unidad de tiempo para el Plazo!')
			return
		}
		criterio.tiempo=$('select#tiempo').val();	
		criterio.tareas=$('select#tarea').val();	
		criterio.calendario=$('select#calendario').val();	
		criterio.conjunto=$('select#conjuntos').val();	
		criterio.frecuencia=$("#frecuencia").val();
		criterio.delay=$("#delay").val();
		
		if($('select#tiempodelay').val() === null){
			alert('Debe indicar una unidad del tiempos para el Delay!')
			return
		}
		criterio.tiempodelay=$('select#tiempodelay').val();
		
		var vector =$("select#tareas").multipleSelect("getSelects");
		var arreglo="";
		var s=true;
		$.each(vector, function (ind, elem) { 
			if (!s){
				arreglo+=";";  
			}
			arreglo+=elem;
			s=false;
		}); 
		criterio.idantecesora=arreglo;
		
		$.post(ruta + "gestion/actualizatarea", criterio, function(
				data, textStatus) {
			 window.location.reload();
		}, "json");
		
	});
	
	$("#volver").on("click",function() {
		location.href=ruta+"gestion";
	});
	
	
	$('input[name=option1]').on('click', function(){
		if($('input[name="option1"]').prop("checked")){
			
			$('#conjuntos').prop('disabled', true);	
			 $('#conjuntos > option[value="0"]').attr('selected', 'selected');
		}
		else{
			$('#conjuntos').prop('disabled', false);
		}
		
		
		});

});
