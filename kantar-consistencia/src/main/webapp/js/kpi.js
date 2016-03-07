
	var tRow = "<tr id='$_reg_$' class='filaParam'><td>$_orden_$</td><td idCombo='$_selected_$'>$_parametro_$</td><td>&_valor_&</td><td>$_editar_$</td></tr>";
	var tRowSem = "<tr id='$_fila_$' class='filaRango'><td idUmb='$_idUmbral_$'>$_umbral_$</td><td idOp='$_idOperador_$'>$_operador_$</td><td>$_valor_$</td><td>$_accion_$</td></tr>";
	var cOpt = "<option value='$_valor_$'>$_glosa_$</option>";
	var corre = 2;
	var sCorre = 0;
	var editando = 0;
	
	$(document).ready(function(){
		ModuloKpiDWR.listadoPaises({
			callback:function(resp){
				if(resp == null){
					alert("No fue posible encontrar paises para desplegar");
				}else{
					ModuloKpiDWR.listadoAlcances({
						callback:function(alcan){
							if(alcan == null){
								alert("No fue posible encontrar alcances para desplegar");
							}else{
								ModuloKpiDWR.listadoAreas({
									callback:function(resp){
										if(resp == null){
											alert("No fue posible encontrar áreas para desplegar");
										}else{
											ModuloKpiDWR.listadoCateg({
												callback:function(resp){
													if(resp == null){
														alert("No fue posible encontrar categorias para desplegar");
													}else{
														var filas = "<option value='-'>Seleccione...</option>";
														$.each(resp, function(){
															var fila = "<option value='$_valor_$'>$_glosa_$</option>";
															fila = fila.replace("$_valor_$", this.id);
															fila = fila.replace("$_glosa_$", this.nombre);
															filas += fila;
														});
														$("#categorias").html(filas);
													}
												}
											});
											var filas = "<option value='-'>Seleccione...</option>";
											$.each(resp, function(){
												var fila = "<option value='$_valor_$'>$_glosa_$</option>";
												fila = fila.replace("$_valor_$", this.idArea);
												fila = fila.replace("$_glosa_$", this.desc);
												filas += fila;
											});
											$("#areas").html(filas);
										}
									}
								})
								var filas = "<option value='-'>Seleccione...</option>";
								$.each(alcan, function(){
									var fila = "<option value='$_valor_$'>$_glosa_$</option>";
									fila = fila.replace("$_valor_$", this.id);
									fila = fila.replace("$_glosa_$", this.nombre);
									filas += fila;
								});
								$("#alcances").html(filas);
							}
						}
					})
					var filas = "<option value='-'>Seleccione...</option>";
					$.each(resp, function(){
						var fila = cOpt;
						fila = fila.replace("$_valor_$", this.id);
						fila = fila.replace("$_glosa_$", this.nombre);
						filas += fila;
					});
					console.log("Carga paises");
					$("#paises").html(filas);
				}
				
			}
		})
		$("#paises").css("display", "none");
		$("#paisesL").css("display", "none");
		/* *************** TABLA DE PARAMETROS ************** */
		ModuloKpiDWR.listParametros({
			callback:function(resp){
				if(resp == null){
					alert("No fue posible encontrar parametros para desplegar");
				}else{
					var filas = "";
					$.each(resp, function(){
						var fila = tRow;
						fila = fila.replace("$_orden_$", this.orden);
						fila = fila.replace("$_parametro_$", this.nombreP);
						if(this.defaultI != null){
							fila = fila.replace("&_valor_&", this.defaultI);
						}
						if(this.defaultT != null){
							fila = fila.replace("&_valor_&", this.defaultT);
						}
						if(this.defaultD != null){
							fila = fila.replace("&_valor_&", this.defaultD);
						}
						if(this.defaultS != null){
							fila = fila.replace("&_valor_&", this.defaultS);
						}
						fila = fila.replace("$_editar_$", "--");
						filas += fila;
					});
					$("#parametrosT").html(filas);
				}
			}
		})
	});
	function spExterno(){
		$("#paises").css("display", "");
		$("#paisesL").css("display", "");
		
		ModuloKpiDWR.listadoPaises({
			callback:function(resp){
				if(resp == null){
					alert("No fue posible encontrar sp externos para desplegar");
				}else{
					var filas = "<option value='-'>Seleccione...</option>";
					$.each(resp, function(){
						var fila = cOpt;
						fila = fila.replace("$_valor_$", this.id);
						fila = fila.replace("$_glosa_$", this.nombre);
						filas += fila;
					});
					$("#paises").html(filas);
				}
			}
		})
		$("#sp").html("<option value='-'>Seleccione...</option>");
	}
	function spInterno(){
		$("#paises").css("display", "none");
		$("#paisesL").css("display", "none");
		ModuloKpiDWR.cargaSpInterno({
			callback:function(resp){
				if(resp == null){
					alert("No fue posible encontrar sp internos para desplegar");
				}else{
					var filas = "<option value='-'>Seleccione...</option>";
					$.each(resp, function(){
						var fila = cOpt;
						fila = fila.replace("$_valor_$", this.nombre);
						fila = fila.replace("$_glosa_$", this.nombre);
						filas += fila;
					});
					console.log("Carga interno");
					$("#sp").html(filas);
				}
			}
		})
	}
	function tipoChange(){
		var id = $("#tipo").val();
		if(0 == parseInt(id)){
			spInterno();
		}else if(1 == parseInt(id)){
			spExterno();
		}
	}
	
	function paisChange(){
		var id = $("#paises").val();
		if(id=="-"){
			$("#sp").html("<option value='-'>Seleccione...</option>")
		}else{
			ModuloKpiDWR.cargaSpExterno({
				callback:function(resp){
					if(resp == null){
						alert("No fue posible encontrar sp externos para desplegar");
					}else{
						var filas = "<option value='-'>Seleccione...</option>";
						$.each(resp, function(){
							var fila = cOpt;
							fila = fila.replace("$_valor_$", this.nombre);
							fila = fila.replace("$_glosa_$", this.nombre);
							filas += fila;
						});
						console.log("Carga externo");
						$("#sp").html(filas);
					}
				}
			})	
		}
	}
	
	function semaforo(){
		$("#agregaRango").css("display", "");
		$("#editaRango").css("display", "none");
		var cOpt = "<option value='$_valor_$'>$_glosa_$</option>";
		ModuloKpiDWR.cargaColores({
			callback:function(resp){
				if(resp == null){
					alert("No fue posible encontrar colores de semaforo para desplegar");
				}else{
					ModuloKpiDWR.cargaOperadores({
						callback:function(resp){
							if(resp == null){
								alert("No fue posible encontrar operadores de semaforo para desplegar");
							}else{
								var filas = "<option value='-'>Seleccione...</option>";
								$.each(resp, function(){
									var fila = "<option value='$_valor_$'>$_glosa_$</option>";
									fila = fila.replace("$_valor_$", this.id);
									fila = fila.replace("$_glosa_$", this.operador);
									filas += fila;
								})
								$("#operador").html(filas);
							}
						}
					})
					var filas = "<option value='-'>Seleccione...</option>";
					$.each(resp, function(){
						var fila = "<option value='$_valor_$'>$_glosa_$</option>";
						fila = fila.replace("$_valor_$", this.id);
						fila = fila.replace("$_glosa_$", this.color);
						filas += fila;
					});
					$("#umbral").html(filas);
					$('#valorSema').val('');
					$('#sema').modal();
				}
			}
		})
	}
	
	function agregaParam(){
		$("#agregaParam").css("display", "");
		$("#editaParam").css("display", "none");
		var cOpt = "<option value='$_valor_$'>$_glosa_$</option>";
			ModuloKpiDWR.cargaParametros({
				callback:function(resp){
					if(resp == null){
						alert("No fue posible encontrar paises para desplegar");
					}else{
						var filas = "<option value='-'>Seleccione...</option>";
						$.each(resp, function(){
							var fila = "<option value='$_valor_$'>$_glosa_$</option>";
							fila = fila.replace("$_valor_$", this.tipoDato+"-"+this.idP);
							fila = fila.replace("$_glosa_$", this.nombreP);
							filas += fila;
						});
						$("#nombreParam").html(filas);
						$("#valorParam").val("");
						$('#ex').modal();
					}
				}
			})
	}
	
	function agregarParam(grabar){
		var flag = true;
		if($('#nombreParam').val() == "-"){
			alert("Es necesario especificar un parametro");
			flag = false;
		}
		if($('#valorParam').val() == ""){
			alert("Es necesario especificar un valor");
			flag = false;
		}
		if($('#nombreParam').val().substring(0,1)=='S'){
			/* validar string */
			
		}else if($('#nombreParam').val().substring(0,1)=='I' || $('#nombreParam').val().substring(0,1)=='D'){
			/* validar numeros */
			if(isNaN($('#valorParam').val())){
				alert("El valor ingresado debe ser numerico");
				flag = false;
			}
		}
		if(flag == true){
			if(grabar){
				corre = corre + 1;
				var filas = "";
				var fila = tRow;
				fila = fila.replace("$_selected_$", $('#nombreParam').val());
				fila = fila.replace("$_reg_$", "reg"+corre);
				fila = fila.replace("$_orden_$", corre);
				fila = fila.replace("$_parametro_$", $('#nombreParam option:selected').html());
				fila = fila.replace("&_valor_&", $('#valorParam').val());
				fila = fila.replace("$_editar_$", "<input type='button' value='Editar' onclick='editar("+corre+");' /> <input type='button' value='Eliminar'onclick='borrar("+corre+");' />");
				filas += fila;
				$("#parametrosT").html($("#parametrosT").html()+filas);
				$.modal.close();	
			}else{
				var arrTd = $('#reg'+editando).find('td');
				var nro = $(arrTd[0]).html();
				var idParam = $('#nombreParam option:selected').val();
				var parametro = $('#nombreParam option:selected').html();
				$(arrTd[1]).html(parametro);
				$(arrTd[1]).attr("idCombo", idParam);
				var valor = $('#valorParam').val();
				$(arrTd[2]).html(valor);
				console.log("valores editados parametro: "+parametro+" idParam: "+idParam+" valor defecto: "+valor);
				editando = 0;
				$.modal.close();
			}
		}
	}
	
	function editar(nro){
		editando = nro;
		var arrTd = $('#reg'+nro).find('td');
		var nro = $(arrTd[0]).html();
		var glosa = $(arrTd[1]).html();
		var valor = $(arrTd[2]).html();
		var idCombo = $(arrTd[1]).attr('idCombo');
		
		ModuloKpiDWR.cargaParametros({
			callback:function(resp){
				if(resp == null){
					alert("No fue posible encontrar paises para desplegar");
				}else{
					var filas = "<option value='-'>Seleccione...</option>";
					$.each(resp, function(){
						var fila = "<option value='$_valor_$'>$_glosa_$</option>";
						fila = fila.replace("$_valor_$", this.tipoDato+"-"+this.idP);
						fila = fila.replace("$_glosa_$", this.nombreP);
						filas += fila;
					});
					$("#nombreParam").html(filas);
				}
				console.log("asignar mbox");
				$("#nombreParam").val(idCombo).change();
			}
			
		});
		$('#valorParam').val(valor);
		$("#agregaParam").css("display", "none");
		$("#editaParam").css("display", "");
		$('#ex').modal();
	}
	
	function borrar(nro){
		console.log("$('#reg'+nro).remove(); --->"+nro);
		$("#reg"+nro).remove();
		fixCorre();
	}
	
	function editarS(nro){
		editando = nro;
		var arrTd = $('#fila'+nro).find('td');
		var umbral = $(arrTd[0]).html();
		var operador = $(arrTd[1]).html();
		var valor = $(arrTd[2]).html();
		
		ModuloKpiDWR.cargaColores({
			callback:function(resp){
				if(resp == null){
					alert("No fue posible encontrar colores de semaforo para desplegar");
				}else{
					ModuloKpiDWR.cargaOperadores({
						callback:function(resp){
							if(resp == null){
								alert("No fue posible encontrar operadores de semaforo para desplegar");
							}else{
								var filas = "<option value='-'>Seleccione...</option>";
								$.each(resp, function(){
									var fila = "<option value='$_valor_$'>$_glosa_$</option>";
									fila = fila.replace("$_valor_$", this.id);
									fila = fila.replace("$_glosa_$", this.operador);
									filas += fila;
								})
								$("#operador").html(filas);
							}
							
							$("#operador").val($(arrTd[1]).attr('idOp')).change();
						}
					})
					var filas = "<option value='-'>Seleccione...</option>";
					$.each(resp, function(){
						var fila = "<option value='$_valor_$'>$_glosa_$</option>";
						fila = fila.replace("$_valor_$", this.id);
						fila = fila.replace("$_glosa_$", this.color);
						filas += fila;
					});
					$("#umbral").html(filas);
				}
				
				$("#umbral").val($(arrTd[0]).attr('idUmb')).change();
			}
		})
		$('#valorSema').val(valor);
		$("#agregaRango").css("display", "none");
		$("#editaRango").css("display", "");
		$('#sema').modal();
		
	}
	
	function borrarS(nro){
		$("#fila"+nro).remove();
		fixCorreS();
	}
	
	function fixCorreS(){
		var arrF = $(".filaRango");
		var correl = 1;
		$.each(arrF, function(){
			var arrTD = $(this).find("td");
			$(arrTD[0]).html(correl);
			$(this).attr("id", "fila"+correl);
			if(correl > 2)
				$(arrTD[3]).html("<input type='button' value='Editar' onclick='editarS("+correl+");' /> <input type='button' value='Eliminar'onclick='borrarS("+correl+");' />");
			correl++;
		});
		sCorre = arrF.length;
	}
	
	function fixCorre(){
		var arrF = $(".filaParam");
		var correl = 1;
		$.each(arrF, function(){
			var arrTD = $(this).find("td");
			$(arrTD[0]).html(correl);
			$(this).attr("id", "reg"+correl);
			if(correl > 2)
				$(arrTD[3]).html("<input type='button' value='Editar' onclick='editar("+correl+");' /> <input type='button' value='Eliminar'onclick='borrar("+correl+");' />");
			correl++;
		});
		corre = arrF.length;
	}
	
	function agregarRango(grabar){
		var flag = true;
		if($('#operador').val() == "-"){
			alert("Es necesario especificar un operador");
			flag = false;
		}
		if($('#umbral').val() == "-"){
			alert("Es necesario especificar un umbral");
			flag = false;
		}
		if($('#valorSema').val() == ""){
			alert("Es necesario especificar un valor");
			flag = false;
		} else if(isNaN($('#valorSema').val())){
				alert("El valor ingresado debe ser numerico");
				flag = false;
		}
			
		if(flag == true){
			if(grabar){
				sCorre = sCorre + 1;
				var filas = "";
				var fila = tRowSem;
				fila = fila.replace("$_idUmbral_$", $('#umbral').val());
				fila = fila.replace("$_idOperador_$", $('#operador').val());
				fila = fila.replace("$_fila_$", "fila"+sCorre);
				fila = fila.replace("$_umbral_$", $('#umbral option:selected').html());
				fila = fila.replace("$_operador_$", $('#operador option:selected').html());
				fila = fila.replace("$_valor_$", $('#valorSema').val());
				fila = fila.replace("$_accion_$", "<input type='button' value='Editar' onclick='editarS("+sCorre+");' /> <input type='button' value='Eliminar' onclick='borrarS("+sCorre+");' />");
				filas += fila;
				$("#semaforoT").html($("#semaforoT").html()+filas);
				$.modal.close();
			}else{
				var arrTd = $('#fila'+editando).find('td');
				var umbral = $('#umbral option:selected').html();
				var idUmbral = $('#umbral option:selected').val();
				var operador = $('#operador option:selected').html();
				var idOperador = $('#operador option:selected').val();
				var valor = $('#valorSema').val();
				
				$(arrTd[0]).html(umbral);
				$(arrTd[0]).attr("idUmb", idUmbral);
				$(arrTd[1]).html(operador);
				$(arrTd[1]).attr("idOp", idOperador);
				$(arrTd[2]).html(valor);
				
				console.log("semaforo: umbral "+umbral+" operador: "+operador+" valor "+valor);
				editando = 0;
				$.modal.close();
			}
		}
	}
	
	function grabarKpi(){
		var flag = true;
		var gKpi = true;
		var gParam = true;
		var gSema = true;
		if($('#nombre').val() == ""){
			alert("Es necesario especificar un nombre");
			flag = false;
		}
		if($('#tipo').val() == "-"){
			alert("Es necesario especificar un tipo");
			flag = false;
		}
		if($('#sp').val() == "-"){
			alert("Es necesario especificar un SP asociado");
			flag = false;
		}
		if($('#alcances').val() == "-"){
			alert("Es necesario especificar un alcance");
			flag = false;
		}
		if($('#areas').val() == "-"){
			alert("Es necesario especificar un area");
			flag = false;
		}
		if($('#categorias').val() == "-"){
			alert("Es necesario especificar una categoria");
			flag = false;
		}
		if(flag){
			var idKpi= 0;
			var pais="";
			console.log("generando el var kpim");
			if($('#paises').css('display') == 'none'){
				pais=0;
			}else{
				pais=$('#paises').val();
			}
			console.log("pais quedo como: "+pais);
			var kpim = {
				glosa:$('#nombre').val(),
				tipo:$('#tipo').val(),
				nombreSp:$('#sp option:selected').html(),
				valorTeoricoD:0,
				pais:pais,
				/* estado:$('#estados').val(), */
				categoria:$('#categorias').val(),
				alcance:$('#alcances').val(),
				area:$('#areas').val()
			};
			
			console.log("antes de llamar a ModuloKpiDWR.grabarKpi");
			ModuloKpiDWR.grabarKpi(kpim,{
				callback:function(id){
					console.log("dentro de ModuloKpiDWR.grabarKpi");
					if(parseInt(id) != 0){
						idKpi = id;
						var arrParam = [];
						var arrPara = $(".filaParam");
						var fila = 0;
						var param = false;
						$.each(arrPara, function(){
							if(parseInt($($(this).find("td")[0]).html())>2){
								console.log("[Param]Kpi id: "+idKpi);
								param = true;
								var arrTD = $(this).find("td");
								var descripcion=$(arrTD[1]).html();
								var kpi=idKpi;
								var parametro=$(arrTD[1]).attr("idCombo");
								parametro = parametro.substring(2,parametro.length);
								var orden=$(arrTD[0]).html();
								var defecto=$(arrTD[2]).html();
								var tipo = $(arrTD[1]).attr("idCombo");
								tipo = tipo.substring(0,1);
								var ParamKpi = {
										descripcion:descripcion,
										kpi:kpi,
										parametro:parametro,
										size:0,
										orden:orden,
										defecto:defecto,
										tipo:tipo
								}
								arrParam.push(ParamKpi);
							}
						});
						console.log("antes del dwr");
						if(param){
							ModuloKpiDWR.grabarParam(arrParam,{
								callback:function(id){
									console.log("dentro dwr");
									if(parseInt(id) != 0){
																				
									}else{
										var gParam = false;
									}
								}
							});
						}
						
						console.log("id de parametro: "+id);
						console.log("dentro callbcak");
						var arrSemen = [];
						var arrSem = $(".filaRango");
						var fila = 0;
						console.log("arrSem:  "+arrSem);
						if(arrSem.length>0){
							$.each(arrSem, function(){
								console.log("[Sema]Kpi id: "+idKpi);
								fila = fila+1;
								console.log("[Sema]Orden de semaforo: "+fila);
								var arrTD = $(this).find("td");
								var umbralId = $(arrTD[0]).attr("idUmb");
								console.log("umbralId: "+umbralId);
								var operadorId = $(arrTD[1]).attr("idOp");
								console.log("operadorId: "+operadorId);
								var valor = $(arrTD[2]).html();
								console.log("valor:   "+valor);
								var SemaforoKpi = {
										pais:pais,
										kpi:idKpi,
										orden:fila,
										operador:operadorId,
										valor:valor,
										color:umbralId
									}
								arrSemen.push(SemaforoKpi);
							});
							
							ModuloKpiDWR.grabarSema(arrSemen,{
								callback:function(id){
									if(parseInt(id) != 0){
										
									}else{
										gSema = false; 
									}
								}
							});
						}
						
					}else{
						var gKpi = false;
					}
				}
			});
			var msg="";
			if(gKpi){
				msg+="El KPI ha sigo grabado exitosamente \n";
			}else{
				msg+="Error al generar KPI \n";
			}
			if(gParam){
				//msg+="El Parametro de KPI ha sigo grabado exitosamente \n";
			}else{
				msg+="Error al generar Parametro KPI \n";
			}
			if(gSema){
				//msg+="El Rango de Semaforo ha sigo grabado exitosamente \n";
			}else{
				msg+="Error al generar Rango de Semaforo \n";
			}
			alert(msg);
			if(msg == "El KPI ha sigo grabado exitosamente \n"){
				history.back();
			}
			//window.location.reload();
		}
		
	}
