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
    	<header>
    		<h1> Música </h1>
    		<nav>
    			<ul id="menu">
    				<li><a href="Controlador?pagina=inicio">Inicio</a></li>
    				<li><a href="Controlador?pagina=productos">Añadir productos</a></li>
	    			<li><a href="Controlador?pagina=usuarios">Gestionar usuarios</a></li>
	    			<li><a href="Controlador?pagina=cerrar">Cerrar sesión</a></li>
    			</ul>
    		</nav>
    	</header>

    	<figure id="guitarra">
    		<img src="resources/imagenes/guitarra.jpg" width="1080" height="200">
    	</figure>

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
