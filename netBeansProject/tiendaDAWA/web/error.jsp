<%-- 
    Document   : error
    Created on : 03-abr-2016, 18:59:31
    Author     : Rapnika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     	<title>Musica</title>
		<meta charset="utf-8">
		<meta content="DreamTeam" name="author">
		<meta content="página de venta de cds" name="description">
		<meta content="musica, cds, venta, tienda" name="keywords">
                <link rel="stylesheet" type="text/css" href="resources/css/estilo.css">
    </head>
    <body>
    	<center>
    	<%@include file="cabecera.jsp" %>

    	<section id="contenido">
    	
            <div id="productos">
                
                <p>${requestScope.mensajeError}</p>
            </div>
	 </section>
    </center>
    </body>
</html>
