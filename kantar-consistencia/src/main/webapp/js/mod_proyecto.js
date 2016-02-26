$(function() {
	window.valorant="";
	var valor= getParameterByName("codigo");
	
	var url = ruta + "gestion/getproyec/proyec?codigo="+valor;
	
	$.getJSON(url).done(function(dat) {
		id_proyecto
		$('#id_proyecto').val(dat.id);
		$('#id_ciclo').val(dat.id_ciclo);
		$('#nombre').val(dat.nombre);
		
		var url=ruta+"Work_Flow/listadatos";
		$.getJSON(url).done(function(dato) {
			
			var list= "<option value=></option>";
			$.each(dato , function(j,i){
				if(dat.id_paises==j){
					list+= "<option value='"+j+"' selected>"+i+"</option>";
				}
				else{
					list+= "<option value='"+j+"'>"+i+"</option>";	
				}
				
				
				
			} );
			 $("#pais").html(list);  
			 $('#calendario > option[value="'+dat.calendario+'"]').attr('selected', 'selected');
			
			 var text="";
			 var textfin="";
				if(dat.ciclo.se_repite==1){
					activar(1);
					window.valorant=1;
					llenardias("dia");
					$('#fecha_actual').val(dat.ciclo.fechaini);
					document.getElementById('fecha_actual').disabled = true;
					 $('#tiempo > option[value="1"]').attr('selected', 'selected');
					$('#dia > option[value="'+dat.ciclo.repetircada+'"]').attr('selected', 'selected');

					if(dat.ciclo.repetircada>1){
						$('#fecha_actual').val(dat.ciclo.fechaini);
						document.getElementById('fecha_actual').disabled = true;
						
						textfin= "Cada "+dat.ciclo.repetircada+" Dias";
					}else{
						textfin= "Cada dia";
					}
					if(dat.ciclo.tipotermino==1){
						textfin="Nunca";
						$('input:radio[name=finaliza]')[0].checked = true;
						document.getElementById('repeticiones').disabled = false;
						document.getElementById('fecha_ter').disabled = true;
					}
					else if(dat.ciclo.tipotermino==2){
						
						textfin+=", Se repite "+dat.ciclo.repeticiones+" veces";
						 $('#repeticiones').val(dat.ciclo.repeticiones); 
						 $('input:radio[name=finaliza]')[1].checked = true;
							document.getElementById('repeticiones').disabled = true;
							document.getElementById('fecha_ter').disabled = false;
						 
						
					}
					else if(dat.ciclo.tipotermino==3){
						textfin+=", hasta "+dat.ciclo.repetirhasta;
						 $('#finaliza').val(dat.ciclo.repetirhasta); 
						 $('#finaliza').val(dat.ciclo.repetirhasta); 
						 $('input:radio[name=finaliza]')[2].checked = true;
					}
					$("#resumen").html(text+textfin);
				
				}
				if(dat.ciclo.se_repite==2){
					$('#fecha_actual1').val(dat.ciclo.fechaini);
					document.getElementById('fecha_actual1').disabled = true;
					activar(5);
					window.valorant=5;
					llenardias("semana");
					 $('#tiempo > option[value="5"]').attr('selected', 'selected');
					 window.RepetirEl=dat.ciclo.repetirel;
					 setRepetirEl();
					 
					 
					 $('#semana > option[value="'+dat.ciclo.repetircada+'"]').attr('selected', 'selected');
						$('#fecha_actual1').val(dat.ciclo.fechaini);
					if(dat.ciclo.repetircada>1){
						textfin= "Cada "+dat.ciclo.repetircada+" semana";
						
					}else{
						textfin= "Cada semana";
					}
					if(dat.ciclo.tipotermino==1){
						textfin="Nunca";
						$('input:radio[name=finaliza1]')[0].checked = true;
					}
					else if(dat.ciclo.tipotermino==2){
						$('#repeticiones1').val(dat.ciclo.repeticiones);
						$("repeticiones1").prop('disabled', false);
						$("fecha_ter1").prop('disabled', true);
						textfin+=", Se repite "+dat.ciclo.repeticiones +" veces"; 
						$('input:radio[name=finaliza1]')[1].checked = true;
						document.getElementById('repeticiones1').disabled = false;
						document.getElementById('fecha_ter1').disabled = true;
						
					}
					else if(dat.ciclo.tipotermino==3){
						$('#fecha_ter1').val(dat.ciclo.repetirhasta);
						$("repeticiones1").prop('disabled', true);
						$("fecha_ter1").prop('disabled', false);
						textfin+=", hasta "+dat.ciclo.repetirhasta;
						$('input:radio[name=finaliza1]')[2].checked = true;
						document.getElementById('repeticiones1').disabled = true;
						document.getElementById('fecha_ter1').disabled = false;
					}
					
					text+=window.str;
					$("#resumen1").html(textfin);
				}
				
				
				if(dat.ciclo.se_repite==3){
					$('#fecha_actual2').val(dat.ciclo.fechaini);
					document.getElementById('fecha_actual2').disabled = true;
					activar(6);
					window.valorant=6;
					llenardias("mes");
					 $('#tiempo > option[value="6"]').attr('selected', 'selected');
					 $('#mes > option[value="'+dat.ciclo.repetircada+'"]').attr('selected', 'selected');
						$('#fecha_actual2').val(dat.ciclo.fechaini);
					if(dat.ciclo.repetircada>1){
						textfin= "Cada "+dat.ciclo.repetircada+" mes";
						
					}else{
						textfin= "Cada mes";
					}
					if(dat.ciclo.tipotermino==1){
						textfin="Nunca";
						$('input:radio[name=finaliza2]')[0].checked = true;
					}
					else if(dat.ciclo.tipotermino==2){
						$('#repeticiones2').val(dat.ciclo.repeticiones);
						$("repeticiones2").prop('disabled', false);
						$("fecha_ter2").prop('disabled', true);
						textfin+=", Se repite "+dat.ciclo.repeticiones +" veces";
						$('input:radio[name=finaliza2]')[1].checked = true;
						document.getElementById('repeticiones2').disabled = false;
						document.getElementById('fecha_ter2').disabled = true;
						
					}
					else if(dat.ciclo.tipotermino==3){
						$('#fecha_ter2').val(dat.ciclo.repetirhasta);
						$("repeticiones2").prop('disabled', true);
						$("fecha_ter2").prop('disabled', false);
						textfin+=", hasta "+dat.ciclo.repetirhasta;
						$('input:radio[name=finaliza2]')[2].checked = true;
						document.getElementById('repeticiones2').disabled = true;
						document.getElementById('fecha_ter2').disabled = false;
					}
					$("#resumen2").html(textfin);
				}
				
				
				if(dat.ciclo.se_repite==4){
					$('#fecha_actual3').val(dat.ciclo.fechaini);
					document.getElementById('fecha_actual3').disabled = true;
					activar(7);
					window.valorant=7;
					llenardias("ano");
					 $('#tiempo > option[value="7"]').attr('selected', 'selected');
					 $('#ano > option[value="'+dat.ciclo.repetircada+'"]').attr('selected', 'selected');
						$('#fecha_actual3').val(dat.ciclo.fechaini);
					if(dat.ciclo.repetircada>1){
						textfin= "Cada "+dat.ciclo.repetircada+" a\u00f1os";
					}else{
						textfin= "Cada a\u00f1o";
					}
					if(dat.ciclo.tipotermino==1){
						textfin="Nunca";
						$('input:radio[name=finaliza3]')[0].checked = true;
					}
					else if(dat.ciclo.tipotermino==2){
						$('#repeticiones3').val(dat.ciclo.repeticiones);
						$("repeticiones3").prop('disabled', false);
						$("fecha_ter3").prop('disabled', true);
						textfin+=", Se repite "+dat.ciclo.repeticiones+" veces";
						$('input:radio[name=finaliza3]')[1].checked = true;
						document.getElementById('repeticiones3').disabled = false;
						document.getElementById('fecha_ter3').disabled = true;
						
						
					}
					else if(dat.ciclo.tipotermino==3){
						$('#fecha_ter3').val(dat.ciclo.repetirhasta);
						$("repeticiones3").prop('disabled', true);
						$("fecha_ter3").prop('disabled', false);
						textfin+=", hasta "+dat.ciclo.repetirhasta;
						$('input:radio[name=finaliza3]')[2].checked = true;
						document.getElementById('repeticiones3').disabled = true;
						document.getElementById('fecha_ter3').disabled = false;
					  
					}
					$("#resumen3").html(textfin);
					 
				}
				
				 $("#resumengeneral").html(textfin);  
				
			
		});	
		



	});
	
	
	
	$('#iniciar').on("click", function (){
		criterio.id_ciclo=$("#id_ciclo").val();
		criterio.id=$('#id_proyecto').val();
		criterio.nombre=$('#nombre').val();
		criterio.pais=$('select#pais').val();
		criterio.calendario=$('select#calendario').val();
		criterio.calendario=$('select#calendario').val();	
		$.post(ruta + "gestion/actualizaproyec", criterio, function(
				data, textStatus) {
			var r=ruta+"gestion";
			location.href=r;
		}, "json");
		
	});
	
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	

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
	
	$("#volver").on("click",function() {
	    event.preventDefault();
	    history.back(1);
	});
	

	function activar(cod) {
		document.getElementById(valorant).style.display = 'none';
		document.getElementById(cod).style.display = 'block';
	}
	function llenardias(ano) {
		var list = "";
		for (var i = 1; i <= 30; i++) {
			list += "<option value='" + i + "'>" + i
					+ "</option>";

		}

		$("#" + ano).html(list);
	}
});
