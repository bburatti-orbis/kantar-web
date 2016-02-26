$(function() {
	window.codigo=0;
	var valor=getParameterByName("tarea");
	
	var url = ruta + "gestion/gettarea/tarea?codigo="+valor;
	
	$.getJSON(url).done(function(dat) {
		
		$("#id_tarea").val(dat.id);
		$("#Nombre").val(dat.nombre);
		$("#plazo").val(dat.plazo);
		$("#delay").val(dat.delay);
		window.codigo=dat.proyecto_id;
		$("#frecuencia").val(dat.delay);
		
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
		
			var url = ruta + "gestion/gettarea/tarea?codigo="+valor;
			var idantesesora= "";
			$.getJSON(url).done(function(dat) {
				idantesesora = dat.idantesesora;
			});
			var url = ruta + "gestion/getlistatareas?codigo="+dat.proyecto_id+"&&tarea="+valor;
			$.getJSON(url).done(function(dat) {


				var list= "<option value='0'></option>";
				$.each(dat , function(j,i){
						if(idantesesora==i.id){
							list+= "<option value='"+i.id+"' selected>"+i.nombre+"</option>";
						}
						else{
							 list+= "<option value='"+i.id+"' >"+i.nombre+"</option>";
						}
					
					
						

						
					
					 
			});
				 $("#tarea").html(list);  

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
			 

			 
	});
	
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	$('input[name=option1]').on('click', function(){
		if($('input[name="option1"]').prop("checked")){
			
			$('#conjuntos').prop('disabled', true);	
			 $('#conjuntos > option[value="0"]').attr('selected', 'selected');
		}
		else{
			$('#conjuntos').prop('disabled', false);
		}
		
		
		});
	
	$("#predecesoras").on("click",function() {
		
		
		
		var url = ruta + "gestion/gettareas?codigo="+window.codigo;
		
		
		$.getJSON(url).done(function(dat) {
			window.dattabla = dat;

			var list= "Tareas Predecesoras </br>";
			var contador=1;
			$.each(dat , function(j,i){
				if(contador%3==1){
					list+="<div class=control_fila2'><div class='control'><input type='checkbox' name='"+i.nombre+"' value='"+i.id+"'> "+i.nombre+"</div>";
					contador++;
				}
				else if(contador%3==2){
					list+=	"<div class='control'><input type='checkbox' name='"+i.nombre+"' value='"+i.id+"'> "+i.nombre+"</div>";
					contador++;
				}
				else if(contador%3==0){
					list+=	"<div class='control'><input type='checkbox' name='"+i.nombre+"' value='"+i.id+"'> "+i.nombre+"</div></div>";
					contador=1;
				}
		});
			if(contador==2){
				list+="</div><br>";
			}
			else if(contador==1){
				list+="</div><br>";
			}
//			list+="<div class=control_fila2'><div class='control'><input id='creartarea'  class='boton_gestion' name='enviar' type='button' value='Agregar Tarea'></div></div>";
			 $("#divpredecesora").html(list);  
			 $("#divboton").html("<div class=control_fila2'><div class='control'><input id='tareaspre'  class='boton_gestion' name='enviar' type='button' value='Guardar'></div></div>");
		});
		

		
		$('#ex').modal();
	});
	
	
	$("#volver").on("click",function() {
		location.href=ruta+"gestion";
	});
	
	$("#tareaspre").on("click",function() {
		
		$('#divpredecesora input:checked').each(function() {
		  alert($(this).attr('name'));
		    
		});
	});


});