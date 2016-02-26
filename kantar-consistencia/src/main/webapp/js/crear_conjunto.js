$(function() {
window.envio={};
var url=ruta+"Work_Flow/listadatos";
	$.getJSON(url).done(function(dat) {
		window.dat = dat;
		var list="<option value=></option>";
//		var list="";
		$.each(dat , function(j,i){
			 list+= "<option value='"+j+"'>"+i+"</option>";
			 
//			  list+= " <li  value='"+j+"' class='ui-state-default'>"+i+"</li>";
		} );
			
		 $("#pais").html(list);  
		
	});	
	
	$('select#pais').change(function(){
	    var valor = $(this).val();
	    var url=ruta+"Work_Flow/listabaseconjunto?pais="+valor;
		$.getJSON(url).done(function(dat) {
			window.dat = dat;
			var list="";
			$.each(dat , function(j,i){
				
				 list+= "<tr value='"+i.id+"'><td>"+i.glosa+"</td></tr>";
				
			} );
				
			 $("#listapaises").find('tbody').html(list);  
			 list="<tr id='0'><td>&nbsp;&nbsp;&nbsp;</td></tr>";
			 $("#conjuntos").find('tbody').html(list);  
			
		});	
	    });
	
	

	 $( "#sortable1, #sortable2" ).sortable({
	      connectWith: ".connectedSortable"
	    }).disableSelection();
	
	 $( "#sortable1" ).draggable();
	 $( "#sortable2" ).draggable();
	  $( "#sortable2" ).droppable({
	      drop: function( event, ui ) {
           $('table#conjuntos tr#0').remove();

	            if ($('#listapaises >tbody >tr').length == 1){
	   			 var list="<tr id='0'><td>&nbsp;&nbsp;&nbsp;</td></tr>";
	   			$('#listapaises tr:last').after(list);
//				 $("#listapaises").find('tbody').html(list);  
	            }
	      }
	    });
	  $( "#sortable1" ).droppable({
	      drop: function( event, ui ) {
           $('table#listapaises tr#0').remove();

	            if ($('#conjuntos >tbody >tr').length == 1){
	   			 var list="<tr id='0'><td>&nbsp;&nbsp;&nbsp;</td></tr>";
	   			$('#conjuntos tr:last').after(list);
//				 $("#listapaises").find('tbody').html(list);  
	            }
	      }
	    });
	  
		$('#iniciar').on("click",function(){
//			envio.nombre=$('#nombre').val();
			var paquete="";
			var conteo=1;
			var validador=true;
			var obj=$('#nombre').val();
			if(obj==""){
				alert('Debe ingresar el nombre del Conjunto');
				validador= false;
			}
			else{
				
				$('#conjuntos tbody tr').each(function () {
					var tr=$(this).closest('tr');
					obj+=";";
					var xx=tr.attr('value');
						obj+=xx;
						conteo++;
						validador= true;					
					});
				
			
			
				
			}
			var r = ruta + "gestion/setconjunto";
			if(validador){
				document.getElementById("iniciar").disabled = true;
				$.post(r, obj, function(
						data, textStatus) {
					$('#nombre').val("");
					var r=ruta+"gestion";
					location.href=r;

				});

			}
			
		
		   });
		
		$("#volver").on("click",function() {
			location.href=ruta+"gestion";
		});
		
});