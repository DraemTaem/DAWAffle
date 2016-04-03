<%-- 
    Document   : usuariosAdmin
    Created on : 03-abr-2016, 18:21:46
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
		<link rel="stylesheet" type="text/css" href="resources/css/admin.css">
    </head>
    <body>
    	<center>
    	<%@include file="cabecera.jsp" %>

    	<section id="contenido">
		    <div id="productos">
			  <table>

			  		<!--Donde se pillarían los elementos de la BD-->
				  <c:forEach var="u" items="${sessionScope.usuarios}">
					  <tr>
					  	<div id="datosUsuario">
					  		<td>${u.getImagen()}</td>
						  	<td><a href="descripcionAdminUsuario.html"><p class=""nombre>${u.getNombre()}</p></a>
						  	<p>Correo electrónico: ${u.getEmail()}, dirección: ${u.getdirección()}</p></td>
					  	</div>
					  	<form method="post" action="Controlador">
                                                    <input type="hidden" value="${u.getId()}"
                                                    <td><input type="submit" class="eliminar" name="eliminarUsuario" value="Eliminar"></td>
					  	</form>
					  </tr>
				  </c:forEach>
			  </table>
			  </div>
	  	</section>
	  </center>
    </body>
</html>
