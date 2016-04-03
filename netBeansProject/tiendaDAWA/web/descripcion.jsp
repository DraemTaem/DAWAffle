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
    	<header>
    		<h1> Música </h1>
    		<nav>
    			<ul id="menu">
    				<li><a href="Controlador?pagina=inicio">Inicio</a></li>
    				<li><a href="Controlador?pagina=carrito">Ver carrito</a></li>
	    			<li><a href="Controlador?pagina=usuarios">Inicio sesión/Registrarse</a></li>
    			</ul>
    		</nav>
    	</header>

    	<figure id="guitarra">
    		<img src="resources/imagenes/guitarra.jpg" width="1080" height="200">
    	</figure>

    	<section id="contenido">
	    	<article id="productos">
	    	<table>
	    		<c:set var="p" value="${sessionScope.producto}"></c:set>
	    		<h1 class="nombre">${p.getNombre()}</h1>
	    		<td><figure id="imagen">${p.getImagen()}</figure></td>
	    		<td><p>${p.getDescripcion()}</p>
	    		<p>Unidades: ${p.getUnidades()}</p>
	    		<p>Precio: ${p.getPrecio()}</p>
	    		<form method="post" id="seleccionar" action="carrito.html">
				  	<input type="number" id="cantidad" placeholder="Cantidad" name="cantidad" min="1" required>
				  	<input type="submit" id="anadir" name="seleccionar" value="Seleccionar">
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
		  		<c:forEach var="c" value="${sessionScope.comentarios}">
		  			<tr><th>${c.getAutor()} ${c.getValoracion}</th></tr>
		  			<tr><td>${c.getComentario()}</td></tr>
		  		</c:forEach>
		  		</table>
		  	</article>
	  	</section>
	  </center>
    </body>
</html>

