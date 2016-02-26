		<script>
			var cOpt = "<option val='$_valor_$'>$_glosa_$</option>";
			$(document).ready(function(){
				ModuloKpiDWR.cargaParametros({
					callback:function(resp){
						if(resp == null){
							alert("No fue posible encontrar paises para desplegar");
						}else{
							var filas = "<option val='-'>Seleccione...</option>";
							$.each(resp, function(){
								var fila = "<option val='$_valor_$'>$_glosa_$</option>";
								fila = fila.replace("$_valor_$", this.idP);
								fila = fila.replace("$_glosa_$", this.nombreP);
								filas += fila;
							});
							$("#nombre").html(filas);
						}
					}
				})
			});
			</script>
		<h1>CARGAR PARAMETRO KPI</h1>
		<h2>Seleccione parametro a agregar</h2>
		<form action="post">
			Nombre:
			<select id="nombre">
				<option val='-'>Seleccione...</option>
			</select>
			<br />
			<br /> 
			Valor por defecto:
			<input type="text" id="valor" />
			<input class="boton3" type="button" value="Agregar"
				onclick="semaforo();" />
		</form>
	
