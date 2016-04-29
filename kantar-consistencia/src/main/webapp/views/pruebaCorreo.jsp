<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kantar VLD</title>
<link href="../css/estilos.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/pruebaCorreo.js"></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";
</script>
</head>

</head>
<body>
	<div>
	<input type="text" name="body" id="body" />
	<input type="button" class="send" value="Enviar" />
	</div>
</body>
</html>