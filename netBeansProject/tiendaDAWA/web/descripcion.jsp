<%-- 
    Document   : descripcion
    Created on : 03-abr-2016, 18:18:02
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
		<link rel="stylesheet" type="text/css" href="resources/css/estiloDescripcion.css">
    </head>
    <body>
    	<center>
    	<%@include file="cabecera.jsp" %>

    	<section id="contenido">
	    	<article id="productos">
	    	<table>
	    		<c:set var="p" value="${requestScope.producto}"></c:set>
	    		<h1 class="nombre">${p.nombre}</h1>
                        <td><figure id="imagen"><img src="${p.imagen}" width="300" height="300"></figure></td>
	    		<td><p>${p.descripcion}</p>
	    		<p>Unidades: ${p.stock}</p>
	    		<p>Precio: ${p.precio}</p>
	    		<form method="post" id="seleccionar" action="Controlador">
                            <input type="hidden" name="action" value="anadirItem">
                            <input type="hidden" name="idProducto" value="${p.id}">
                            <td><input type="number" id="cantidad" placeholder="Cantidad" name="cantidad" min="1" required></td>
                            <td><input type="submit" id="anadir" name="seleccionar" value="Seleccionar"></td>
                        </form>
				</td>
			</table>
		  	</article>

		  	<article id="comentarios">
		  		<!--Sección de comentarios-->
		  		<h1 class="nombre">Comentarios</h1>
		  		<form method="post" action="Controlador">
		  			<div>
		  			<input type="text" name="alias" placeholder="Introduce tu nombre" pattern="[A-Za-z]{2,}">
		  			<input type="number" name="valoración" placeholder="Valoración" min="1">(Valoración entre 1 y 10)
		  			</div>

		  			<div class="valoracion">
						<a href="#" data-value="1" title="Votar con 1 estrellas">&#9733;</a>
						<a href="#" data-value="2" title="Votar con 2 estrellas">&#9733;</a>
						<a href="#" data-value="3" title="Votar con 3 estrellas">&#9733;</a>
						<a href="#" data-value="4" title="Votar con 4 estrellas">&#9733;</a>
						<a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
					</div>

		  			<div>
		  			<textarea id="textarea" rows="5" name="textarea" placeholder="Introduce tu comentario" pattern="*{3,}">
		  			</textarea>
		  			</div>

		  			<input type="submit" name="comentar" id="comentar" value="Comentar">
		  		</form>

		  		<table>
		  		<c:forEach var="c" items="${p.valoraciones.valoraciones}">
		  			<tr><th>${c.usuario.alias} ${c.nota}</th></tr>
		  			<tr><td>${c.comentario}</td></tr>
		  		</c:forEach>
		  		</table>
		  	</article>
	  	</section>
	  </center>
    </body>
</html>

