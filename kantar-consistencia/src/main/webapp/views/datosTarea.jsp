<div class="divContent">

	<div class='subTituloPag'>Datos proyecto</div>
	<div class="divDato">
		<div class="dato">Proyecto : ${proyecto.nombre}</div>
		<div class="dato">Producci&oacute;n : ${proyectoE.mes} ${proyectoE.ano}</div>
	</div>
	
	<div class="divDato">
		<div class="dato">Pa&iacute;s: ${pais.nombre}</div>
		<div class="dato">Fecha producci&oacute;n : ${proyectoE.fechaInicioProduccionS} - ${proyectoE.fechaFinProduccionS}</div>
	</div>
	

	<div class='subTituloPag'>Datos tarea</div>
	<div class="divDato">
		<div class="dato">Tarea : ${tarea.nombre}</div>
		<div class="dato">Fecha real : ${tarea.inicioReal} - ${tarea.inicioReal}</div>
	</div>
	<div class="divDato">
		<div class="dato">Estado : ${estado.nombreEstado}</div>
		<div class="dato">Fecha estimada : ${tarea.inicioEstimado} - ${tarea.inicioEstimado}</div>
	</div>
	<div class="divDato">
		<div class="dato">Desviaci&oacute;n : ${tarea.desviacion}</div>
		<div class="dato">Responsable : ${responsable.nombre} ${responsable.apellido}</div>
	</div>
</div>