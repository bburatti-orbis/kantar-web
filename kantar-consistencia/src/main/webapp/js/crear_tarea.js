$(function() {
	
	window.criterio = {};
	window.codigo= getParameterByName("codigo");
	$("#idproyecto").val(codigo);
	window.nom= getParameterByName("nombre");
	$("#nombreproyec").val(nom);

	var url=ruta+"Work_Flow/getarea";
	$.getJSON(url).done(function(dat) {
		window.dat = dat;
		var list= "<option value='0'></option>";
		$.each(dat , function(j,i){
			 list+= "<option value='"+i.id+"'>"+i.descripcion+"</option>";
			
		} );
			
		 $("#area").html(list);  
		
	});
	
	var url=ruta+"gestion/getciclo?accion="+codigo;
	$.getJSON(url).done(function(dat) {
		window.dat = dat;
		var list= "<option value='0'></option>";
		$.each(dat , function(j,i){
			 list+= "<option value='"+i.id+"'>"+i.nombre+"</option>";
			
		} );
			
		 $("#conjuntos").html(list);  
		
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
	});
	
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	$('#creartarea').on("click", function (){
		var marcado = $('input[name="option1"]').prop("checked") ? true : false;
		criterio.automatica=$('input[name="automatica"]').prop("checked") ? true : false;
		criterio.nombre=$("#Nombre").val();
		criterio.idresponsable=$('select#users').val();
		criterio.plazo=$('#plazo').val();
		criterio.tiempo=$('select#tiempo').val();
		criterio.tiempofrecuencia=$('select#frecuencialist').val();
		
//		
		criterio.tipo_calendario=$('select#calendario').val();	
		criterio.frecuencia=$("#frecuencia").val();
		criterio.delay=$("#delay").val();
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
		if(criterio.idantecesora==""){
			criterio.idantecesora=1;
		}
		if(marcado==true){ 
			criterio.conjunto=0;	
		}
		else{
			criterio.conjunto=$('select#conjuntos').val();
		}
		
		
		
		criterio.id_proyec=$('#idproyecto').val();
		
		
		if(criterio.nombre==""){
			alert("Debe ingresar el nombre de la tarea");
			$("#Nombre").focus();
		}
		else if(criterio.idresponsable==0){
			alert("Debe ingresar al responsable de la tarea");
		}
		else if(criterio.plazo==""){
			alert("Debe ingresar un plazo para la tarea");
			$("#plazo").focus();
		}
		else if(criterio.tiempo==0){
			alert("Debe ingresar una medida de tiempo para la tarea");
		}
		else if(criterio.delay==""){
			alert("Debe ingresar un Delay para la tarea");
		}
		else if(criterio.tiempodelay==0){
			alert("Debe ingresar una medida de tiempo el delay de la tarea");
		}
		else if(criterio.frecuencia==""){
			alert("Debe ingresar una Frecuencia para la tarea");
		}
		else if(criterio.tipo_calendario==0){
			alert("Debe ingresar el Tipo de calendario de la tarea");
		}
		else if(criterio.conjunto==0 && marcado==false ){
			alert("Debe Elegir el conjunto de la tarea");		
		}
		else{
			document.getElementById("creartarea").disabled = true;
			$.post(ruta + "gestion/insertarea", criterio, function(
					data, textStatus) {
				var r=ruta+"gestion/ver_tareas?accion="+$('#idproyecto').val()+"&&nombre="+$('#nombreproyec').val();
				location.href=r;
				
				setTimeout ("redireccionar()", 1);

			}, "json");
		}
		
		
		
		
		
		
		
	});
	
	$("#volver").on("click",function() {
		location.href=ruta+"gestion/ver_tareas?accion="+window.codigo+"&&nombre="+window.nom;
	});
	
	$('input[name=option1]').on('click', function(){
		if($('input[name="option1"]').prop("checked")){
			 $('#conjuntos > option[value="0"]').attr('selected', 'selected');
			$('#conjuntos').prop('disabled', true);	
		}
		else{
			$('#conjuntos').prop('disabled', false);
		}
		
		
		});
	
	
	$('select#area').change(function(){
	    var valor = $(this).val();
	    var url=ruta+"gestion/listusers?cod="+valor;
		$.getJSON(url).done(function(dat) {
			window.dat = dat;
			var list= "<option value='0'></option>";
			$.each(dat , function(j,i){
				
				 list+= "<option value='"+i.id+"'>"+i.nombre+" "+i.apellido+"</option>";
				 
				
			} );
				
			   
			$("#users").html(list);  
			
		});	
	    });
	
	

    
    
	
	});
	
	
	


