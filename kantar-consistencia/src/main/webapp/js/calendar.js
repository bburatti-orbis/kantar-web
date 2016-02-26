$(document)
		.ready(
				function() {
					 $(document).load("Content-Type:application/json; charset=UTF-8");
					
					
					
					window.criterio = {};
					document.getElementById('1').style.display = 'none';
					document.getElementById('5').style.display = 'none';
					document.getElementById('6').style.display = 'none';
					document.getElementById('7').style.display = 'none';
					document.getElementById('repeticiones').disabled = true;
					document.getElementById('repeticiones1').disabled = true;
					document.getElementById('repeticiones2').disabled = true;
					document.getElementById('repeticiones3').disabled = true;
					document.getElementById('fecha_ter').disabled = true;
					document.getElementById('fecha_ter1').disabled = true;
					document.getElementById('fecha_ter2').disabled = true;
					document.getElementById('fecha_ter3').disabled = true;

					valorant = 0;
					textodia = "";
					textoveces = "";
					dias = "";
					$("input[name='repetir']").click(function(event) {

						$('#ex1').modal();

					});
					$(".Listo")
							.on(
									"click",
									function(event) {

										var selec = $('select#tiempo').val();
										var text = "";
										if (selec == 1) {

											criterio.cod = 1;
											criterio.Repetircada = $(
													'select#dia').val();
											criterio.fechaini = $(
													"#fecha_actual").val();
											if ($(
													'input[name=finaliza]:checked')
													.val() == 1) {
												criterio.Tipotermino = $(
														'input[name=finaliza]:checked')
														.val();
											} else if ($(
													'input[name=finaliza]:checked')
													.val() == 2) {
												criterio.Tipotermino = $(
														'input[name=finaliza]:checked')
														.val();
												criterio.repeticiones = $(
														'#repeticiones').val();
											} else if ($(
													'input[name=finaliza]:checked')
													.val() == 3) {
												criterio.Tipotermino = $(
														'input[name=finaliza]:checked')
														.val();
												criterio.termina = $(
														'#fecha_ter').val();
											}
											$("#resumengeneral").html(
													$("#resumen").html());

										}
										if (selec == 5) {
											getRepetirEl();
											criterio.cod = 2;
											criterio.Repetircada = $(
													'select#semana').val();
											criterio.fechaini = $(
													"#fecha_actual1").val();
											criterio.Reperitel = window.RepetirEl;
											if ($(
													'input[name=finaliza1]:checked')
													.val() == 1) {
												criterio.Tipotermino = $(
														'input[name=finaliza1]:checked')
														.val();
											} else if ($(
													'input[name=finaliza1]:checked')
													.val() == 2) {
												criterio.Tipotermino = $(
														'input[name=finaliza1]:checked')
														.val();
												criterio.repeticiones = $(
														'#repeticiones1').val();
											} else if ($(
													'input[name=finaliza1]:checked')
													.val() == 3) {
												criterio.Tipotermino = $(
														'input[name=finaliza1]:checked')
														.val();
												criterio.termina = $(
														'#fecha_ter1').val();
											}
											$("#resumengeneral").html(
													$("#resumen1").html());

										}
										if (selec == 6) {

											criterio.cod = 3;
											criterio.Repetircada = $(
													'select#mes').val();
											criterio.fechaini = $(
													"#fecha_actual2").val();
											if ($(
													'input[name=finaliza2]:checked')
													.val() == 1) {
												criterio.Tipotermino = $(
														'input[name=finaliza2]:checked')
														.val();
											} else if ($(
													'input[name=finaliza2]:checked')
													.val() == 2) {
												criterio.Tipotermino = $(
														'input[name=finaliza2]:checked')
														.val();
												criterio.repeticiones = $(
														'#repeticiones2').val();
											} else if ($(
													'input[name=finaliza2]:checked')
													.val() == 3) {
												criterio.Tipotermino = $(
														'input[name=finaliza2]:checked')
														.val();
												criterio.termina = $(
														'#fecha_ter2').val();
											}
											$("#resumengeneral").html(
													$("#resumen2").html());

										}
										if (selec == 7) {

											criterio.cod = 4;
											criterio.Repetircada = $(
													'select#ano').val();
											criterio.fechaini = $(
													"#fecha_actual3").val();
											if ($(
													'input[name=finaliza3]:checked')
													.val() == 1) {
												criterio.Tipotermino = $(
														'input[name=finaliza3]:checked')
														.val();
											} else if ($(
													'input[name=finaliza3]:checked')
													.val() == 2) {
												criterio.Tipotermino = $(
														'input[name=finaliza3]:checked')
														.val();
												criterio.repeticiones = $(
														'#repeticiones3').val();
											} else if ($(
													'input[name=finaliza3]:checked')
													.val() == 3) {
												criterio.Tipotermino = $(
												'input[name=finaliza3]:checked')
												.val();
												criterio.finaliza = $(
														'input[name=finaliza3]:checked')
														.val();
												criterio.termina = $(
														'#fecha_ter3').val();
											}

											$("#resumengeneral").html(
													$("#resumen3").html());
										}
										$.post(ruta
												+ "gestion/crearproyec/ciclo",
												criterio, function(data,
														textStatus) {
													$('#id_ciclo').val(data);
												}, "json");

									});

					$('select#tiempo')
							.change(
									function() {
										var valor = $(this).val();

										if (valor == 1) {

											activar(valor);
											llenardias("dia");
											fechaactual("fecha_actual");
											valorant = valor;
											textofindia(1, "");
										}

										else if (valor == 5) {

											activar(valor);
											llenardias("semana");
											fechaactual("fecha_actual1");
											valorant = valor;
											textofinsemana(1, "");
										} else if (valor == 6) {

											activar(valor);
											llenardias("mes");
											fechaactual("fecha_actual2");
											valorant = valor;
											textofinmes(1, "");
										} else if (valor == 7) {

											activar(valor);
											llenardias("ano");
											fechaactual("fecha_actual3");
											valorant = valor;
											textofinano(1, "");
										} else if (valor == 0) {
											document.getElementById('1').style.display = 'none';
											document.getElementById('5').style.display = 'none';
											document.getElementById('6').style.display = 'none';
											document.getElementById('7').style.display = 'none';

										}

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
					function fechaactual(F) {

						var d = new Date();

						var month = d.getMonth() + 1;
						var day = d.getDate();
						var minuto = d.getMinutes();

						if (minuto < 9) {
							minuto = "0" + minuto;
						}
						
						var output =  d.getFullYear()+ '-' + (('' + month).length < 2 ? '0' : '')
								+ month + '-' + (('' + day).length < 2 ? '0' : '') + day+ " "
								+ d.getHours() + ":" + minuto + ":"
								+ d.getSeconds();

						$("#" + F).val(output);
						document.getElementById(F).disabled = false;

					}

					$("input[name=finaliza]").change(function() {

						finaliza1($(this).val());
					});

					function finaliza1(cod) {
						if (cod == 1) {
							document.getElementById('repeticiones').disabled = true;
							document.getElementById('fecha_ter').disabled = true;
							document.getElementById('fecha_ter').value = "";
							document.getElementById('repeticiones').value = "";
							textoveces = "";
							textonunca();

						} else if (cod == 2) {
							document.getElementById('repeticiones').disabled = false;
							document.getElementById('repeticiones').value = "";
							document.getElementById('fecha_ter').value = "";
							document.getElementById('fecha_ter').disabled = true;
							textofinrepit();

						} else if (cod == 3) {
							document.getElementById('fecha_ter').disabled = false;
							document.getElementById('repeticiones').value = "";
							document.getElementById('repeticiones').disabled = true;
							fechaactual("fecha_ter");
							document.getElementById('fecha_ter').disabled = false;
							textoveces = "";
							textofinfecha();

						}

					}

					function textofindia(cod, texto) {

						var tex = "";

						if (cod == 1) {
							tex = textodia = "Cada dia";
							cod = 31;
						} else if (cod > 1 && cod < 31) {
							tex = textodia = "Cada " + cod + " dias";

						} else if (cod == 31) {
							tex = textodia + texto;
						} else if (cod == 32) {

							tex = textodia + texto;
						}
						if(textodia==""){
							
							tex="Cada Dia"+texto;
						}
						$("#resumen").html(tex);
					}

					function textofinrepit() {

						texto = ","
								+ document.getElementById('repeticiones').value
								+ " veces";
						textoveces = texto;
						textofindia(31, texto)

					}
					function textonunca() {
						var a = $('select#dia').val();

						textofindia(a, "");

					}
					function textofinfecha() {

						texto = ", hasta el "
								+ document.getElementById('fecha_ter').value;
						textoveces = texto;
						textofindia(32, texto)

					}
					function textofinrepit() {

						texto = ","
								+ document.getElementById('repeticiones').value
								+ " veces";

						textofindia(31, texto)

					}

					$('select#dia').change(function() {
						var valor = $(this).val();
						textofindia(valor);
					});

					$("#repeticiones").blur(function() {

						textofinrepit();
					});
					$("#fecha_ter").blur(function() {

						textofinfecha();
					});

					$('select#semana').change(function() {
						var valor = $(this).val();
						textofinsemana(valor);
					});

					$("input[name=finaliza1]").change(function() {

						finaliza2($(this).val());
					});

					function finaliza2(cod) {
						if (cod == 1) {
							document.getElementById('repeticiones1').disabled = true;
							document.getElementById('fecha_ter1').disabled = true;
							document.getElementById('fecha_ter1').value = "";
							document.getElementById('repeticiones1').value = "";
							textoveces = "";
							textonuncasemana();
						} else if (cod == 2) {
							document.getElementById('repeticiones1').disabled = false;
							document.getElementById('repeticiones1').value = "";
							document.getElementById('fecha_ter1').disabled = true;
							textofinrepitsemana();

						} else if (cod == 3) {

							document.getElementById('repeticiones1').value = "";
							fechaactual("fecha_ter1");
							document.getElementById('repeticiones1').disabled = true;
							document.getElementById('fecha_ter1').disabled = false;
							textoveces = "";
							textofinfechasemana();
						}
					}

					function textofinsemana(cod, texto) {

						var tex = "";

						if (cod == 1) {
							tex = textodia = "Cada semana " + dias;
							cod = 31;
						} else if (cod > 1 && cod < 31) {
							tex = textodia = "Cada " + cod + " semanas, "
									+ dias;

						} else if (cod == 31) {
							tex = textodia + texto;
						} else if (cod == 32) {

							tex = textodia + texto;
						}
						if(textodia==""){
							
							tex="Cada Semana"+texto;
						}
						$("#resumen1").html(tex);
					}

					function textofinrepitsemana() {

						texto = ""
								+ document.getElementById('repeticiones1').value
								+ " veces";
						textoveces = texto;
						textofinsemana(31, texto)

					}
					function textonuncasemana() {
						var a = $('select#semana').val();

						textofinsemana(a, "");

					}
					function textofinfechasemana() {

						texto = " hasta el "
								+ document.getElementById('fecha_ter1').value;
						textoveces = texto;
						textofinsemana(32, texto)

					}
					$("#repeticiones1").blur(function() {

						textofinrepitsemana();
					});
					$("#fecha_ter1").blur(function() {

						textofinfechasemana();
					});

					$('input[type=checkbox]').change(function() {
						text = "";
						dias = "";
						suma = 0;
						if ($('input[name=lunes]').prop("checked")) {
							text += "lunes, ";
							suma++;
						}
						if ($('input[name=Martes]').prop("checked")) {
							text += "Martes, ";
							suma++;
						}
						if ($('input[name=Miercoles]').prop("checked")) {
							text += "Miercoles, ";
							suma++;
						}
						if ($('input[name=Jueves]').prop("checked")) {
							text += "Jueves, ";
							suma++;
						}
						if ($('input[name=Viernes]').prop("checked")) {
							text += "Viernes, ";
							suma++;
						}
						if ($('input[name=Sabado]').prop("checked")) {
							text += "Sabado, ";
							suma++;
						}
						if ($('input[name=Domingo]').prop("checked")) {
							text += "Domingo, ";
							suma++;
						}

						if (suma == 7) {
							dias = "Todos los dias de la semana ";
						} else if (suma > 0) {
							dias += "los dias " + text;
						}

						textonuncasemana()
						text = "";
					});

					$("input[name=finaliza2]").change(function() {

						finaliza3($(this).val());
					});

					function finaliza3(cod) {
						if (cod == 1) {
							document.getElementById('repeticiones2').disabled = true;
							document.getElementById('fecha_ter2').disabled = true;
							document.getElementById('fecha_ter2').value = "";
							document.getElementById('repeticiones2').value = "";
							textonuncames();
							textoveces = "";
						} else if (cod == 2) {
							document.getElementById('repeticiones2').disabled = false;
							document.getElementById('repeticiones2').value = "";
							document.getElementById('fecha_ter2').value = "";
							document.getElementById('fecha_ter2').disabled = true;
							textofinrepitmes();

						} else if (cod == 3) {
							fechaactual("fecha_ter2");
							document.getElementById('fecha_ter2').disabled = false;
							document.getElementById('repeticiones2').value = "";
							document.getElementById('repeticiones2').disabled = true;
							textofinfechames();
							textoveces = "";
						}

					}

					function textofinmes(cod, texto) {

						var tex = "";

						if (cod == 1) {
							tex = textodia = "Cada mes";
							cod = 31;
						} else if (cod > 1 && cod < 31) {
							tex = textodia = "Cada " + cod + " mes";

						} else if (cod == 31) {
							tex = textodia + texto;
						} else if (cod == 32) {

							tex = textodia + texto;
						}
						
						if(textodia==""){
							
							tex="Cada mes"+texto;
						}

						$("#resumen2").html(tex);
					}

					function textofinrepitmes() {

						texto = ","
								+ document.getElementById('repeticiones2').value
								+ " veces";
						textoveces = texto;
						textofinmes(31, texto)

					}
					function textonuncames() {
						var a = $('select#mes').val();

						textofinmes(a, "");

					}
					function textofinfechames() {

						texto = ", hasta el "
								+ document.getElementById('fecha_ter2').value;
						textoveces = texto;
						textofinmes(32, texto)

					}
					function textofinrepitmes() {

						texto = ","
								+ document.getElementById('repeticiones2').value
								+ " veces";

						textofinmes(31, texto)

					}

					$('select#mes').change(function() {
						var valor = $(this).val();
						textofinmes(valor);
					});

					$("#repeticiones2").blur(function() {

						textofinrepitmes();
					});
					$("#fecha_ter2").blur(function() {

						textofinfechames();
					});

					$("input[name=finaliza3]").change(function() {

						finaliza4($(this).val());
					});

					function finaliza4(cod) {
						if (cod == 1) {
							document.getElementById('repeticiones3').disabled = true;
							document.getElementById('fecha_ter3').disabled = true;
							document.getElementById('fecha_ter3').value = "";
							document.getElementById('repeticiones3').value = "";
							textonuncaano();
							textoveces = "";
						} else if (cod == 2) {
							document.getElementById('repeticiones3').disabled = false;
							document.getElementById('repeticiones3').value = "";
							document.getElementById('fecha_ter3').value = "";
							document.getElementById('fecha_ter3').disabled = true;
							textofinrepitano();

						} else if (cod == 3) {
							fechaactual("fecha_ter3");
							document.getElementById('fecha_ter3').disabled = false;
							document.getElementById('repeticiones3').value = "";
							document.getElementById('repeticiones3').disabled = true;
							textofinfechaano();
							textoveces = "";
						}

					}

					function textofinano(cod, texto) {

						var tex = "";

						if (cod == 1) {
							tex = textodia = "Cada a\u00f1o";
							cod = 31;
						} else if (cod > 1 && cod < 31) {
							tex = textodia = "Cada " + cod + " a\u00f1os";

						} else if (cod == 31) {
							tex = textodia + texto;
						} else if (cod == 32) {

							tex = textodia + texto;
						}
						if(textodia==""){
							
							tex="Cada A\u00f1o"+texto;
						}
						$("#resumen3").html(tex);
					}

					function textofinrepitano() {

						texto = ","
								+ document.getElementById('repeticiones3').value
								+ " veces";
						textoveces = texto;
						textofinano(31, texto)

					}
					function textonuncaano() {
						var a = $('select#ano').val();

						textofinano(a, "");

					}
					function textofinfechaano() {

						texto = ", hasta el "
								+ document.getElementById('fecha_ter3').value;
						textoveces = texto;
						textofinano(32, texto)

					}
					function textofinrepitano() {

						texto = ","
								+ document.getElementById('repeticiones3').value
								+ " veces";

						textofinano(31, texto)

					}

					$('select#ano').change(function() {
						var valor = $(this).val();
						textofinano(valor);
					});

					$("#repeticiones3").blur(function() {

						textofinrepitano();
					});
					$("#fecha_ter3").blur(function() {

						textofinfechaano();
					});

					function getRepetirEl(event) {
						/*
						 * Procedimiento que convierte un conjunto de 7 checkbox
						 * en un tinyInt (0 a 127) i = 0 => todos los checkbox
						 * en false i = 127 => todos los checkox en true
						 */
						var i = 0;
						if ($('input[name="lunes"]').prop("checked"))
							i += Math.pow(2, 0);
						if ($('input[name="Martes"]').prop("checked"))
							i += Math.pow(2, 1);
						if ($('input[name="Miercoles"]').prop("checked"))
							i += Math.pow(2, 2);
						if ($('input[name="Jueves"]').prop("checked"))
							i += Math.pow(2, 3);
						if ($('input[name="Viernes"]').prop("checked"))
							i += Math.pow(2, 4);
						if ($('input[name="Sabado"]').prop("checked"))
							i += Math.pow(2, 5);
						if ($('input[name="Domingo"]').prop("checked"))
							i += Math.pow(2, 6);
						window.RepetirEl = i;
					}

				});