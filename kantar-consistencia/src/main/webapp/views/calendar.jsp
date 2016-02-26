|


<div id="ex1" style="display:none;">

<div  class="titulo">Programación  de Proyecto </div>
  <div class="caja">
	<div class="control_fila2">
	 	<div class="control">
		<p id="cabecera">Se Repite:</p>
		</div>
		 	<div class="control">
		 <select id="tiempo">
			<option value="0" selected></option>
			<option value="1">Cada dia</option>
			<option value="5">Cada semana</option>
			<option value="6">Cada mes</option>
			<option value="7">Cada año</option>
		</select>
		</div>
		</div>
		<div class="" id="0"></div>
		<div class="p2" id="1">
		 <div class="caja">
			<div class="control_fila2">
			<div class="control">
				<input type="hidden" value="dia" name="cod">
				Repetir cada 
				</div>
				<div class="control">
				<select id="dia"></select> dia 
				
				</div></div>
				<div class="control_fila2">
				<div class="control">
				Empieza el
				</div>
			
				<div class="control">
				 <input	type="text" value="" id="fecha_actual">
				 </div>
				 </div>
				 
				 	<div class="control_fila2">
			<div class="control">
					Iteraciones: 
					</div>
					<div class="control">
					 <INPUT type="radio" id="finaliza" name="finaliza" checked value="1">Sin termino</div></div>
					
					 	 	<div class="control_fila2">
					 	 	<div class="control"></div>
			<div class="control">
				<INPUT type="radio" id="finaliza" name="finaliza" value="2">Cantidad de veces:</div>  <div class="control"><input type="text" value="" id="repeticiones"></div></div>
				<div class="control_fila2">
					<div class="control"></div>
					 	 	<div class="control">
				  <INPUT type="radio" id="finaliza" name="finaliza" value="3">
				 Hasta fecha:		</div>
				 	<div class="control">
						<input type="text" class="" id="fecha_ter"></div>
 </div>
 <div class="control_fila2">
 <div class="control">
				 Resumen
				 </div>
				 <div class="controlresumen">
				<p  id="resumen" class="p2"></p>
				</div>
				</div>
	<div class="control">  <div class="control"></div> <div class="control"></div> <div class="control"> <a  class="Listo" rel="modal:close"><input type="button"   id="iniciar" value="Guardar"></a></div></div>
		</div>
</div>
	
		<div class="p2" id="5">
		  <div class="caja">
	<div class="control_fila2">
	 	<div class="control">	
			<input type="hidden" value="semana" name="cod">
				Repetir cada</div>	<div class="control">	 <select id="semana"></select> semana </div>
				</div>
				<div class="control_fila2">
	 	<div class="control"> Repetir cada </div>
	 	<div class="divdias">
	 	<input type="checkbox" name="lunes" id="lunes" value="l"> L 
	 	<input type="checkbox" name="Martes" value="M" > M 
	 	<input type="checkbox" name="Miercoles" value="X" > Mi 
	 	<input type="checkbox" name="Jueves" value="J" > J 

	 	<input type="checkbox" name="Viernes" value="V" > V 
	 	<input type="checkbox" name="Sabado" value="S" > S 
	 	<input type="checkbox" name="Domingo" value="D" > D
		</div>
		</div>
			<div class="control_fila2">
	 	<div class="control"> Empieza el</div>
	 	 <div class="control"> <input type="text" value="" id="fecha_actual1"></div></div>
	 	 <div class="control_fila2">
	 	<div class="control"> Iteraciones:</div>
	 	<div class="control"><INPUT	type="radio" id="finaliza" name="finaliza1" checked value="1">Sin termino</div></div>
	 	<div class="control_fila2">
	 	<div class="control"></div>
	 	<div class="control"><INPUT type="radio" id="finaliza" name="finaliza1" value="2">Cantidad de veces:</div>
	 	  <div class="control"><input type="text" value="" id="repeticiones1"></div></div>
	 	  <div class="control_fila2">
	 	<div class="control"></div>
	 	<div class="control"><INPUT type="radio" id="finaliza" name="finaliza1"	value="3"> Hasta fecha:</div>	
		<div class="control"><input type="text" class="" id="fecha_ter1"></div></div>
		<div class="control_fila2">
		<div class="control">Resumen</div>
				 <div class="controlresumen">
				<p  id="resumen1" class="p2"></p>
				</div>
				</div>
	<div class="control">  <div class="control"></div> <div class="control"></div> <div class="control"> <a  class="Listo" rel="modal:close"><input type="button"   id="iniciar" value="Guardar"></a></div></div>

			

		</div></div>


			
		<div class="p2" id="6">
			  <div class="caja">
	
			<input type="hidden" value="mes" name="cod">
			<div class="control_fila2">
					<div class="control">Repetir cada</div> 
				<div class="control">	<select id="mes"></select> mes </div></div>
				<div class="control_fila2">
					<div class="control"> Empieza el</div>
					
	 	 <div class="control"> <input type="text" value="" id="fecha_actual2"></div>
	 	 </div>
	 	 <div class="control_fila2">
	 	<div class="control"> Iteraciones:</div>
	 	<div class="control"><INPUT	type="radio" id="finaliza" name="finaliza2" checked value="1">Sin termino</div></div>
	 	<div class="control_fila2">
	 	<div class="control"></div>
	 	<div class="control"><INPUT type="radio" id="finaliza" name="finaliza2" value="2">Cantidad de veces:</div>
	 	  <div class="control"><input type="text" value="" id="repeticiones2"></div></div>
	 	  <div class="control_fila2">
	 	<div class="control"></div>
	 	<div class="control"><INPUT type="radio" id="finaliza" name="finaliza2"	value="3"> Hasta fecha:</div>	
		<div class="control"><input type="text" class="" id="fecha_ter2"></div></div>
		<div class="control_fila2">
		<div class="control">Resumen</div>
				 <div class="controlresumen">
				<p  id="resumen2" class="p2"></p>
				</div>
				</div>
	<div class="control">  <div class="control"></div> <div class="control"></div> <div class="control"> <a  class="Listo" rel="modal:close"><input type="button"   id="iniciar" value="Guardar"></a></div></div>

			

		</div></div>
				
			




		<div class="p2" id="7">
					  <div class="caja">
	<div class="control_fila2">
	 	<div class="control">	
			<input type="hidden" value="anio" name="cod">
			Repetir cada</div> 
			<div class="control">	
			<select id="ano">
			
			</select> años
			</div>
	</div>
		 <div class="control_fila2">
				<div class="control"> Empieza el</div>
	 	 <div class="control"> <input type="text" value="" id="fecha_actual3"></div></div>
	 	 <div class="control_fila2">
	 	<div class="control"> Iteraciones:</div>
	 	<div class="control"><INPUT	type="radio" id="finaliza" name="finaliza3" checked value="1">Sin termino</div></div>
	 	<div class="control_fila2">
	 	<div class="control"></div>
	 	<div class="control"><INPUT type="radio" id="finaliza" name="finaliza3" value="2">Cantidad de veces:</div>
	 	  <div class="control"><input type="text" value="" id="repeticiones3"></div></div>
	 	  <div class="control_fila2">
	 	<div class="control"></div>
	 	<div class="control"><INPUT type="radio" id="finaliza" name="finaliza3"	value="3"> Hasta fecha:</div>	
		<div class="control"><input type="text" class="" id="fecha_ter3"></div></div>
		<div class="control_fila2">
		<div class="control">Resumen</div>
				 <div class="controlresumen">
				<p  id="resumen3" class="p2"></p>
				</div>
				</div>
	<div class="control">  <div class="control"></div> <div class="control"></div> <div class="control"> <a  class="Listo" rel="modal:close"><input type="button"   id="iniciar" value="Guardar"></a></div></div>

			

		</div></div>
	
</div></div>


