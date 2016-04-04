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
		    		<c:set var="u" value="${requestScope.usuarioSolicitado}"></c:set>
                                <form method="post" action="Controlador">
                                    <h1 class="nombre">${u.nombre}, ${u.alias}</h1>
				    		
				    		<td>
					    		<p>Email: ${u.email}"></p>
                                                        
                                                        <p>Categoría:
                                                        <c:choose>
                                                            <c:when test="${u.categoria == 1}">
                                                                <p>usuario VIP</p>
                                                            </c:when>
                                                            <c:when test="${u.categoria == 0}">
                                                                <p>usuario básico</p>
                                                            </c:when>
                                                        </c:choose>
					    		</p>

                                                        <p>Tipo:
					    		<c:choose>
                                                            <c:when test="${u.administrador == true}">
                                                                <p>Administrador</p>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <p>No administrador</p>
                                                            </c:otherwise>
                                                        </c:choose>
					    		</p>
                                                </td>
                                                <td>
					    		<p>Dirección: ${u.direccion}</p>

					    		<p>Contraseña:
                                                            <input type="text" name="contrasena" value="${u.contrasena}" pattern="[A-Za-z0-9\.\,\-\_]{6,}">
					    		</p>
                                                        <input type="hidden" name="action" value="modificarContrasena">
                                                        <input type="hidden" name="idUsuario" value="${u.id}">
					    		<input type="submit" value="Actualizar contraseña" name="guardarUsuario" id="actualizar" class="mover">
				    		</td>

						</form>
				</table>
		  	</article>
	  	</section>
	  </center>
    </body>
</html>
