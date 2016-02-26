<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kantar VLD</title>
<link href="../css/estilos.css" rel="stylesheet">
<link href="../css/menu.css" rel="stylesheet">
<link rel="stylesheet" href="../css/menu.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/menu.js" ></script>
<script type="text/javascript" src="../js/WorkFlow.js" ></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";

</script>
</head>

<body>
	<div id="wrap">
		<header>
		<div class="logo">
			<a href="index.cl"><img src="../img/logo-kantar.png" width="191"
				height="21" title="Kantar VLD"></a>
		</div>
		</header>

		<div class="contenedor_general">
		<div id="load">
		    	<nav>
<jsp:include page="menu.jsp" flush="true"/>
        </nav>
<img alt="" src="../img/cargando.gif " style="margin-left: 40%; position=absolute; "></img>

</div>
			<nav>
<jsp:include page="calendar.jsp" flush="true"/>
			</nav>

			<hr></hr>

			<div class="controles_fila">
				<div class="control">
					<div class="tit_control">
						<p>País</p>
					</div>
					<div>
						<p>
							<select id="listpais">

							</select>

						</p>
					</div>
				</div>

				<div class="control">
					<div class="tit_control">
						<p>Fecha</p>
					</div>
					<div >
						<p>	<input type="text"  class="llama_periodo_actual"  placeholder="AAAA-MM"	 id="periodo"		></p>
					</div>
				</div>

				<div class="control">
					<div class="tit_control">
						<p>Measure Set</p>
					</div>
					<div 	>
						<p><select id="measure">

							</select></p>
					</div>
				</div>

			</div>

			<div class="controles_fila">
				<div class="control">
					<div class="tit_control">
						<p>Nomenclatura</p>
					</div>
					<form>
						<input type="checkbox" id="casilla1" name="casilla1" /> <label
							for="casilla1"><span>IT0</span></label> <input type="checkbox"
							id="casilla1" name="casilla1" /> <label for="casilla1"><span>IT2</span></label>
					
					</form>

			</div>
										<div>
							<input type="button" id="buscar" class="validar" value="Buscar BD" >
							</div>
			</div>

			<!--<div class="caja_login">
        <div class="caja_titulo"><h1>Bienvenido a Kantar VLD</h1></div>
        	<form>
            <input id="correo" name="" type="text" placeholder="Ingesa tu correo">
            <input id="pass" name="" type="text" placeholder="Ingesa tu password">
            <input id="iniciar" name="enviar" type="submit" value="Iniciar SesiÃ³n">
            </form>
        </div>-->


			<hr></hr>

			<div class="controles_fila">
				<div class="control">
					<div class="tit_control">
						<p>Base PV</p>
					</div>
					<form>
						<input id="base" name="" type="text"
							placeholder="CÓdigo / Cliente">
					</form>
				</div>
			</div>

			<div class="caja_tablas">
				<table id="polizas">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">CÓdigo</th>
						<th scope="col">Categoría / DescripciÓn</th>
						<th scope="col">Cliente(s)</th>
						<th scope="col">Ponderador</th>
					</tr>
					</thead>
					<tbody>
	
					</tbody>
				</table>
			</div>

			<hr></hr>

			<div class="caja_final">
				<div class="inferior_home">
					<form>
						<input  class="validar" id="validar" name="enviar" type="button" value="Validar">
					</form>
				</div>
				<div class="kantar_world"></div>
			</div>

		</div>

	</div>

	<footer>
	<div class="caja_text">
		<h2>Kantar Worldpanel Â® 2015</h2>
		<h3>Sistema Kantar de ValidaciÓn y LiberaciÓn de Datos</h3>
	</div>

	<!--     <div class="caja_link"><p><a href="#">&raquo; EnvÃ­o de ticket para soporte y ayuda.</a></p></div> -->
	</footer>

</body>
</html>
