<%-- 
    Document   : descripcionAdminProducto
    Created on : 03-abr-2016, 18:22:41
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
	    	<h1 class="nombre">Editar producto</h1>
	    	<table>
	    		<c:set var="p" value="${sessionScope.producto}"></c:set>
	    		<form method="post" action="Controlador">
	    			<h1><input type="text" value="${p.getNombre()}" pattern="[A-Za-z]"{3,}></h1>
		    		<td><figure id="imagen">${p.getImagen()}</figure>
		    		<input type="file" name="file"></td>
		    		<td>
			    		<p>Descripción:</p>
			    		<textarea id="textarea">${p.getDescripcion()}</textarea></p>
			    		<p>Unidades: </p>
			    		<input type="number" value="${p.getUnidades()}" min="0">
			    		</p>
			    		<p>Precio: </p>
			    		<input type="text" value="${p.getPrecio()}" pattern="[0-9\.]{1,}">
			    		</p>
			    		<input type="submit" value="Guardar" name="guardarProducto" id="actualizar" class="mover">
			    		<input type="submit" value="Eliminar" name="eliminarProducto" class="eliminar mover">
		    		</td>

				</form>
				
			</table>
		  	</article>

		  	<article id="comentarios">
		  		<!--Sección de comentarios-->
		  		<h1 class="nombre">Comentarios</h1>

		  		<table>
			  		<form method="post" action="Controlador" onsubmit="return validar()">
				  		<c:set var="c" value="${sessionScope.comentarios}">
				  			<tr><th>
					  			<input type="text" value="${c.getAutor()}" pattern="[A-Za-z]{3,}"> 
					  			<input type="number" value="${c.getValoracion}" min="1">
				  			</th></tr>
				  			<tr>
				  				<td>
				  					<textarea id="textarea">${c.getComentario()}</textarea>
				  				</td>
				  				<td>
				  					<input type="submit" value="Guardar" name="guardarComentario" id="actualizar">
				  					<input type="submit" value="Eliminar" name="eliminarComentario" class="eliminar">
				  				</td>
				  			</tr>
				  		</c:set>
			  		</form>
		  		</table>
		  	</article>
	  	</section>
	  </center>
    </body>
</html>

