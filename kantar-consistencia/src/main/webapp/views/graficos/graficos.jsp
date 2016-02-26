<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
<!-- <script src="http://code.highcharts.com/highcharts.js"></script> -->
<!-- </head> -->
<!-- <body> -->

<jsp:include page="/views/generales/header_wf.jsp" flush="true"/>

<jsp:include page="/views/graficos/pEjecutivoArea.jsp" flush="true"/>
<jsp:include page="/views/graficos/revisarPlazos.jsp" flush="true"/>
<jsp:include page="/views/graficos/sProyecto.jsp" flush="true"/>
<script>
	$(document).ready(function(){
		window.setInterval(function(){location.reload();}, 50000);
	});
</script>

<jsp:include page="/views/generales/footer.jsp" flush="true"/>

<!-- </body> -->
<!-- </html> -->