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
	    		<form method="post" action="Controlador">
                            <input type="hidden" name="action" value="anadirCD">
                            <h1><input type="text" name="nombre" placeholder="Nombre producto" pattern="[A-Za-z áéíóúÁÉÍÓÚ]{3,}"></h1>
		    		<td>
		    		<p>Imagen:</p>
                                <input type="text" name="url"</td>
		    		
			    		<p>Descripción:</p>
                                        <textarea id="textarea"name="descripcion" placeholder="Introduce una breve descripción sobre el producto"></textarea></p>
			    		<p>Unidades: </p>
			    		<input type="number" name="unidades" min="0">
			    		</p>
                                        </td>
                                        <td>
			    		<p>Precio: </p>
                                        <input type="text" name="precio" pattern="[0-9\.]{1,}">
                                        <p>Autor: </p>
                                        <input type="text" name="autor">
                                        <p>País: </p>
                                        <input type="text" name="pais">
                                        <p>Año: </p>
                                        <input type="text" name="ano">
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
