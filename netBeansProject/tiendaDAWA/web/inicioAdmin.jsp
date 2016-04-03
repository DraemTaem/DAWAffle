<%-- 
    Document   : inicioAdmin
    Created on : 03-abr-2016, 18:19:01
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
    	<form id="filtro" method="post" action="Controlador">
    		<div id="primeros">

	    		<!--<label for="titulo">Titulo:</label>-->
	    		<input type="text" class="caja" name="titulo" placeholder="Título" pattern="[A-Za-z]{2,}">

	    		<!--<label for="autor">Autor:</label>-->
	    		<input type="text" class="caja" name="autor" placeholder="Autor" pattern="[A-Za-z]{2,}">

	    		<!--<label for="ano">Año:</label>-->
	    		<input type="number" class="caja" placeholder="Año" name="ano" min="1900" max="2016">
    		</div>

    		<div id="segundos">
	    		<label for="precio">Precio:</label>
	    		<select name="precio" id="precio">
		    		<option>Hasta 10€</option>
		    		<option>Hasta 15€</option>
		    		<option>Hasta 20€</option>
		    		<option>Hasta 30€</option>
		    		<option>Hasta 40€</option>
		    		<option>Hasta 50€</option>
		    		<option>Más de 50€</option>
	    		</select>

	    		<input type="submit" id="actualizar" name="actualizar" value="Actualizar">
    		</div>

    		

    	</form>
    <hr>
    <div id="productos">
	  <table>

	  		<!--Donde se pillarían los elementos de la BD-->
		  <c:forEach var="p" items="${sessionScope.productos.lista}">
			  <tr>
			  	<div id="datosCD">
			  		<td>${p.getImagen()}</td>
				  	<td><a href="descripcionAdminProducto.html"><p class=""nombre>${p.getTitulo()}, ${p.getAutor()}</p></a>
				  	<p>Precio: ${p.getPrecio()}€, código: ${p.getCodigo()}</p></td>
			  	</div>
			  	<form method="post" id="seleccionar">
				  	<td><input type="submit" class="eliminar" name="eliminarProducto" value="Eliminar"></td>
			  	</form>
			  </tr>
		  </c:forEach>
	  </table>
	  </div>
	  </section>
	  </center>
    </body>
</html>
