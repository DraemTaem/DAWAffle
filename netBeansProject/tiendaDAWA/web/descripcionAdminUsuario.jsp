<%-- 
    Document   : descripcionAdminUsuario
    Created on : 03-abr-2016, 18:23:15
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
		    	<table>
		    		<c:set var="u" value="${sessionScope.usuario}"></c:set>
			    		<form method="post" action="">
			    			<h1>
			    				<input type="text" value="${u.getNombre()}" pattern="[A-Za-z]"{3,}>
			    				<input type="text" value="${u.getAlias()}" pattern="[A-Za-z]"{3,}>
			    			</h1>
				    		<td>
				    			<figure id="imagen">${p.getImagen()}</figure>
				    			<input type="file" name="file">
				    		</td>
				    		<td>
					    		<p>Email:</p>
					    			<input type="email" value="${u.getEmail()}">
					    		</p>
					    		<p>Categoría:</p>
					    			<select>
					    				<option value="basico">Básico</option>
					    				<option value="vip">VIP</option>
					    			</select>
					    		</p>

					    		<p>Administrador: </p>
					    			<input type="number" value="${u.getAdministrador()}" min="0" max="1">
					    		</p>

					    		<p>Dirección:</p>
					    		<input type="text" value="${u.getDireccion()}" pattern="[A-Za-z0-9\.\/]{5,}">
					    		</p>

					    		<p>Contraseña:</p>
					    			<input type="text" value="u.getContrasena()" pattern="[A-Za-z0-9\.\,\-\_]{8,}">
					    		</p>
					    		<input type="submit" value="Guardar" name="guardarUsuario" id="actualizar" class="mover">
					    		<input type="submit" value="Eliminar" name="eliminarUsuario" class="eliminar mover">
				    		</td>

						</form>
				</table>
		  	</article>
	  	</section>
	  </center>
    </body>
</html>
