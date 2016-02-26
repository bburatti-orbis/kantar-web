 $(function() {
	 window.criterio = {};
	window.cod = {};
	$(document).on("click",'.eliminar', function (event){
		var tr=$(this).closest('tr');
		 cod.valor=tr.attr('value');
		$.post(ruta + "gestion/setestado", cod, function(
				data, textStatus) {
			tr.remove();
			


		}, "json");
	});
	
	
	window.str="";
	var fecha="";
	var codigo= getParameterByName("codigo");
	
	
	var url = ruta + "gestion/getproyec";
	
	$.getJSON(url).done(function(dat) {
		window.dattabla = dat;

		armartabla();

	});
	
	function armartabla() {
		var list="";
		var nombre="";
		var fecha_ini="";
		var serepite="";
		var repetircada="";
		var tipo_termino="";
		var clase="";
		var tipo_termino="";
		var repeticiones="";
		var repetirel="";
		var pais="";
		var text="";
		var textfin="";
		var repetir_hasta="";
		$.each(dattabla, function(index, value) {
			nombre=value.nombre;
			fecha_ini=value.fecha_ini;
			serepite=value.serepite;
			repetircada=value.repetircada;
			tipo_termino=value.tipo_termino;
			repeticiones=value.repeticiones;
			repetirel=value.repetirel;
			repetir_hasta=value.repetir_hasta;
			pais=value.pais;
			
			if(serepite==1){
				if(repetircada>1){
					text= "Cada "+repetircada+" Dias";
				}else{
					text= "Cada dia";
				}
				if(tipo_termino==1){
					textfin="Nunca";
				}
				else if(tipo_termino==2){
					textfin="Se repite "+repeticiones;
					
				}
				else if(tipo_termino==3){
					textfin=repetir_hasta;
				}
			
			}
			if(serepite==2){
				 window.RepetirEl=repetirel;
				 setRepetirEl();
				if(repetircada>1){
					text= "Cada "+repetircada+" semana";
				}else{
					text= "Cada semana";
				}
				if(tipo_termino==1){
					textfin="Nunca";
				}
				else if(tipo_termino==2){
					textfin="Se repite "+repeticiones;
					
				}
				else if(tipo_termino==3){
					textfin=repetir_hasta;
				}
				
				text+=window.str;
			}
			
			
			if(serepite==3){
				if(repetircada>1){
					text= "Cada "+repetircada+" mes";
				}else{
					text= "Cada mes,";
				}
				if(tipo_termino==1){
					textfin="Nunca";
				}
				else if(tipo_termino==2){
					textfin="Se repite "+repeticiones;
					
				}
				else if(tipo_termino==3){
					textfin=repetir_hasta;
				}
			}
			
			
			if(serepite==4){
				if(repetircada>1){
					text= "Cada "+repetircada+" Ano";
				}else{
					text= "Cada ano,";
				}
				if(tipo_termino==1){
					textfin="Nunca";
				}
				else if(tipo_termino==2){
					textfin="Se repite "+repeticiones;
					
				}
				else if(tipo_termino==3){
					textfin=repetir_hasta;
				}
			}

			list += "<tr class='alternar' value='"+value.id+"'>";
			list += "<td class='linea'>" +nombre+ "</td>";
			list += "<td>" + fecha_ini + "</td>";
			list += "<td>" + textfin + "</td>";
			list += "<td>" + text + "</td>";
			
			list += "<td >" + pais + "</td>";
			list += " <td>  <img SRC='../img/guion.png' class='eliminar' value='"+value.id+"'><a href='"+ruta+"gestion/ver_tareas?accion="+value.id+"&&nombre="+nombre+"'><IMG SRC='../img/suma.png' class='agregar' BORDER=2 ALT='DETALLE'></a><IMG SRC='../img/editar.png' class='editar' ALT='EDITAR' rel='modal:open'></td></tr>";
		});

		$("#polizas").find('tbody').html(list);
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
	
	function setRepetirEl(){
		/*
		 * Procedimiento que interpreta el valor booleano de un tinyInt (0 a 127) 
		 * en un conjunto de checkbox activados (1 implica activar el checkbox, 
		 * 0 implica apagar el checkbox) 
		 */
		var dias = window.RepetirEl.toString(2).split("");

		var checked = [];
		for(var i=0;i < dias.length; i++){
			checked[6-i] = parseInt(dias[dias.length-1-i]);
		}
		var str=",Los dias: ";
		var primero=true;
		var todos=0;
		if(checked[6] == 1){
			$('input[name="lunes"]').prop("checked", true);
			str+="Lunes";
			primero=false;
			todos++;
		}

		if(checked[5] == 1){
			$('input[name="Martes"]').prop("checked", true);
			if(!primero){
				str+=",";
			}
				
			str+="Martes";
			primero=false;
			todos++;
		}
			
		if(checked[4] == 1){
			$('input[name="Miercoles"]').prop("checked", true);
			if(!primero){
				str+=",";
			}
				
			str+="Miercoles";
			primero=false;
			todos++;
		}
		if(checked[3] == 1){
			if(!primero){
				str+=",";
			}
			$('input[name="Jueves"]').prop("checked", true);
			str+="Jueves";
			primero=false;
			todos++;
		}
		if(checked[2] == 1){
			if(!primero){
				str+=",";
			}
			$('input[name="Viernes"]').prop("checked", true);
			str+="Viernes";
			primero=false;
			todos++;
		}
		if(checked[1] == 1) {
			if(!primero){
				str+=",";
			}
			$('input[name="Sabado"]').prop("checked", true);
			str+="Sabado";
			primero=false;
			todos++;
		}
		if(checked[0] == 1){
			if(!primero){
				str+=",";
			}
			$('input[name="Domingo"]').prop("checked", true);
			str+="Domingo";
			primero=false;
			todos++;
		}
		
		if(todos==7){
			str="Todos los dias";
		}
		window.str=str;
	};
	

	
	
	$('#proyecto').on("click", function (){
		location.href=ruta+"gestion/crearproyec";
	
	setTimeout ("redireccionar()", 1);
	});
	
	$('#conjunto').on("click", function (){
		location.href=ruta+"gestion/crearconjunto";
	
	setTimeout ("redireccionar()", 1);
	});
	
	
	
	
	$(document).on("click",'.editar', function (event){
		var tr=$(this).closest('tr');
		var valor=tr.attr('value');
		location.href=ruta+"gestion/mod_proyec?codigo="+valor;
		
//		$('#ex').modal();
//		
//		var url = ruta + "gestion/getproyec/proyec?codigo="+valor;
//		
//		$.getJSON(url).done(function(dat) {
//			id_proyecto
//			$('#id_proyecto').val(dat.id);
//			$('#id_ciclo').val(dat.id_ciclo);
//			$('#nombre').val(dat.nombre);
//			
//			var url=ruta+"Work_Flow/listadatos";
//			$.getJSON(url).done(function(dato) {
//				
//				var list= "<option value=></option>";
//				$.each(dato , function(j,i){
//					if(dat.id_paises==j){
//						list+= "<option value='"+j+"' selected>"+i+"</option>";
//					}
//					else{
//						list+= "<option value='"+j+"'>"+i+"</option>";	
//					}
//					
//					
//					
//				} );
//				 $('#id_ciclo').val(dat.ciclo.id); 
//				 $('#id_ciclo').val(dat.ciclo.id);
//				 $("#pais").html(list);  
//				 $('#calendario > option[value="'+dat.calendario+'"]').attr('selected', 'selected');
//				 var text="";
//				 var textfin="";
//					if(dat.ciclo.se_repite==1){
//						$('#dia > option[value="'+dat.ciclo.repetircada+'"]').attr('selected', 'selected');
//						$('#fecha_actual').val(dat.ciclo.fechaini);
//						
//						if(dat.ciclo.repetircada>1){
//							
//							
//							textfin= "Cada "+dat.ciclo.repetircada+" Dias";
//						}else{
//							text= "Cada dia";
//						}
//						if(dat.ciclo.tipo_termino==1){
//							textfin="Nunca";
//						}
//						else if(dat.ciclo.tipo_termino==2){
//							
//							textfin="Se repite "+dat.ciclo.repeticiones;
//							 $('#repeticiones').val(dat.ciclo.repeticiones); 
//							
//						}
//						else if(dat.ciclo.tipo_termino==3){
//							textfin=dat.ciclo.repetir_hasta;
//							 $('#finaliza').val(dat.ciclo.repetirhasta); 
//						}
//					
//					}
//					if(dat.ciclo.se_repite==2){
//						 window.RepetirEl=dat.ciclo.repetirel;
//						 setRepetirEl();
//						 $('#semana > option[value="'+dat.ciclo.repetircada+'"]').attr('selected', 'selected');
//							$('#fecha_actual1').val(dat.ciclo.fechaini);
//						if(dat.ciclo.repetircada>1){
//							textfin= "Cada "+dat.ciclo.repetircada+" semana";
//							
//						}else{
//							textfin= "Cada semana";
//						}
//						if(dat.ciclo.tipo_termino==1){
//							textfin="Nunca";
//						}
//						else if(dat.ciclo.tipo_termino==2){
//							$('#repeticiones1').val(dat.ciclo.repeticiones);
//							$("repeticiones1").prop('disabled', false);
//							$("fecha_ter1").prop('disabled', true);
//							textfin="Se repite "+dat.ciclo.repeticiones;
//							
//						}
//						else if(dat.ciclo.tipo_termino==3){
//							$('#fecha_ter1').val(dat.ciclo.repetirhasta);
//							$("repeticiones1").prop('disabled', true);
//							$("fecha_ter1").prop('disabled', false);
//							textfin=dat.ciclo.repetir_hasta;
//						}
//						
//						text+=window.str;
//					}
//					
//					
//					if(dat.ciclo.se_repite==3){
//						 $('#mes > option[value="'+dat.ciclo.repetircada+'"]').attr('selected', 'selected');
//							$('#fecha_actual2').val(dat.ciclo.fechaini);
//						if(dat.ciclo.repetircada>1){
//							text= "Cada "+dat.ciclo.repetircada+" mes";
//						}else{
//							textfin= "Cada mes,";
//						}
//						if(dat.ciclo.tipo_termino==1){
//							textfin="Nunca";
//						}
//						else if(dat.ciclo.tipo_termino==2){
//							$('#repeticiones2').val(dat.ciclo.repeticiones);
//							$("repeticiones2").prop('disabled', false);
//							$("fecha_ter2").prop('disabled', true);
//							textfin="Se repite "+dat.ciclo.repeticiones;
//							
//						}
//						else if(dat.ciclo.tipo_termino==3){
//							$('#fecha_ter2').val(dat.ciclo.repetirhasta);
//							$("repeticiones2").prop('disabled', true);
//							$("fecha_ter2").prop('disabled', false);
//							textfin=dat.ciclo.repetir_hasta;
//						}
//					}
//					
//					
//					if(dat.ciclo.se_repite==4){
//						 $('#ano > option[value="'+dat.ciclo.repetircada+'"]').attr('selected', 'selected');
//							$('#fecha_actual3').val(dat.ciclo.fechaini);
//						if(dat.ciclo.repetircada>1){
//							text= "Cada "+dat.ciclo.repetircada+" Ano";
//						}else{
//							textfin= "Cada ano,";
//						}
//						if(dat.ciclo.tipo_termino==1){
//							textfin="Nunca";
//						}
//						else if(dat.ciclo.tipo_termino==2){
//							$('#repeticiones3').val(dat.ciclo.repeticiones);
//							$("repeticiones3").prop('disabled', false);
//							$("fecha_ter3").prop('disabled', true);
//							textfin="Se repite "+dat.ciclo.repeticiones;
//							
//						}
//						else if(dat.ciclo.tipo_termino==3){
//							$('#fecha_ter3').val(dat.ciclo.repetirhasta);
//							$("repeticiones3").prop('disabled', true);
//							$("fecha_ter3").prop('disabled', false);
//							textfin=dat.ciclo.repetir_hasta;
//						}
//					}
//					
//					 $("#resumengeneral").html(textfin);  
//					
//				
//			});	
//			
//
//
//
//		});
//		
		});
	
	
	$('#editarproyecto').on("click", function (){
		criterio.id_ciclo=$("#id_ciclo").val();
		criterio.id=$('#id_proyecto').val();
		criterio.nombre=$('#nombre').val();
		criterio.pais=$('select#pais').val();
		criterio.calendario=$('select#calendario').val();
		criterio.calendario=$('select#calendario').val();	
		$.post(ruta + "gestion/actualizaproyec", criterio, function(
				data, textStatus) {
			 window.location.reload();
		}, "json");
		
		
		

		
	});
	
	
	
	
	
	
	
	
	

});