$(function() {
	window.ajaxCall = 0;
	
	
	$.ajaxSetup({
	    beforeSend:function(){
	        // show gif here, eg:
	    	$("#load").show(1000);
	    	ajaxCall++;
	    },
	    complete:function(){
	        // hide gif here, eg:
	    	ajaxCall--;
	    	if (ajaxCall == 0){
	    		$("#load").hide();
	    		
	    	}
	    }
	});
	
	
	
	
	
	var url=ruta+"Work_Flow/listadatos";
	$.getJSON(url).done(function(dat) {
		window.dat = dat;
		var list;
		$.each(dat , function(j,i){
			 list+= "<option value='"+j+"'>"+i+"</option>";
			
		} );
			
		 $("#listpais").html(list);  
		
	});
	
	var url=ruta+"Work_Flow/listameasure";
	$.getJSON(url).done(function(dat) {
		window.measure = dat;
		var list;
		$.each(measure , function(j,i){
			 list+= "<option value='"+i+"'>"+i+"</option>";
			
		} );
			
		 $("#measure").html(list);  
		
	});
	
	function armartabla(){
		var list;
		var lista;
		var selected;
		var ponder=$('select[id=measure]').val();

// obj.bases ["id","glosa","categoria","clienteid","paisid"];
		$.each(measure , function(j,i){
			 lista+= "<option value='"+i+"'>"+i+"</option>";
			
			
		} );
		$.each(dattabla.bases, function(index,value){
			
				list+= "<tr class='alternar'>";
				list+="<td><input id='check' name='' type='checkbox' value='"+value.glosa+"'></td>";
				list+="<td>"+value.glosa+"</td>"; 
				list+="<td>"+value.categoria+"</td>";
				list+="<td>"+value.nombreCliente+"</td>";
 				list+="<td>	<select>" ;
 				$.each(measure , function(j,i){
 					if(ponder==i){
 						selected="selected";
 					}
 					else{
 						selected="";
 					}
 					 list+= "<option value='"+i+"' "+selected+">"+i+"</option>";
 					 
 					
 				} );
				list+="</select> </td></tr>";
		});
		
		$("#polizas").find('tbody').html(list);

	


	}
	

	$('#buscar').on("click", function(){
	
		var pais= $('select[id=listpais]').val();
		var periodo= $('#periodo').val();
		

	if(periodo!=null && periodo.length==7){
		var url=ruta+"Work_Flow/listabases?pais="+pais;
// alert (url);
		
	
		$.getJSON(url).done(function(dat) {
			window.dattabla = dat;
			
			armartabla();	
		
		} );
	}else{
		alert ('Debe ingresar un periodo ')
	}
	});
	
	
	
	$('#validar').on("click", function(){
		 
		var solicitados ="";
		var objeto={
				"fecha":"",
				"measure":"",
				"codigos":"",
				"pais":""
		};
		var primero= true;
		
		$('#polizas > tbody  > tr').each(function() {
//			Aqui vemos las lineas seleccionadas
			$this = $(this)
			if($this.find("input:checked").length){
				var nombre = $this.find("input:checked").val();
				var ponderador = $this.find("select").val();
			}
		});
		
		$('#polizas input[type=checkbox]').each(function(){
			if (this.checked) {
				if(!primero){
					solicitados+=",";
				}
				primero=false;
				solicitados+=$(this).val();

			}



		});
		objeto.codigos=solicitados;
		objeto.fecha=$('#periodo').val();
		objeto.measure=$('select[id=measure]').val();
		objeto.pais=$("#listpais option:selected").text();
		var enJson = JSON.stringify(solicitados);
		var url=ruta+ 'Work_Flow/procesarbd?'+objeto;
		$.ajax({
			url:ruta+ 'Work_Flow/procesarbd?'+objeto,
			type: 'GET',
			dataType: 'json',
			success: function (data) {
			alert('Base de datos Validada')
			},
			data: objeto
		});
	});
	});
	



