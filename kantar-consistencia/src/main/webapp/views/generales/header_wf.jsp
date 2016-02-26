<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Kantar VLD</title>
<link href="../../css/menu.css" rel="stylesheet">
<link href="../../css/estilos.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js'></script>
    
        <!--DWR-->
        <script src="../../dwr/engine.js"></script>
        <script src="../../dwr/interface/KantarDWR.js"></script>
        
<script type="text/javascript" src="../../js/JSBUSCAR.js" ></script>
<script type="text/javascript" src="../../js/menu.js" ></script>
<script type="text/javascript">
	var ruta = "${pageContext.request.contextPath}/webresources/";
	</script>
</head>

<body>
<div id="wrap">
	<header>
    	<div class="logo"><a href="index.cl"><img src="../../img/logo-kantar.png" width="191" height="21" title="Kantar VLD"></a></div>
    </header>
    
    <div class="contenedor_general">
    	<nav>
			<jsp:include page="../menu_workflow.jsp" flush="true"/>
        </nav>