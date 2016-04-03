<%-- 
    Document   : nuevoProducto
    Created on : 03-abr-2016, 18:19:42
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
	    	<article id="productos">
	    	<h1 class="nombre">Introducción de un nuevo producto</h1>
	    	<table>
	    		<form method="post" action="">
	    			<h1><input type="text" placeholder="Nombre producto" pattern="[A-Za-z]"{3,}></h1>
		    		<td>
		    		<p>Imagen:</p>
		    		<input type="file" name="file"></td>
		    		<td>
			    		<p>Descripción:</p>
			    		<textarea id="textarea" placeholder="Introduce una breve descripción sobre el producto"></textarea></p>
			    		<p>Unidades: </p>
			    		<input type="number" min="0">
			    		</p>
			    		<p>Precio: </p>
			    		<input type="text" pattern="[0-9\.]{1,}">
			    		</p>
			    		<input type="submit" value="Guardar" name="anadirProducto" id="actualizar" class="mover">
		    		</td>

				</form>
				
			</table>
		  	</article>
	  	</section>
	  </center>
    </body>
</html>
