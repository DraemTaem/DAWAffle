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
                    <c:set var="p" value="${requestScope.producto}"></c:set>
                        <form method="post" action="Controlador">
                            <h1 class="nombre">${p.nombre}</h1>
                        <td><figure id="imagen"><img src="${p.imagen}" width="300" height="300"></figure></td>
                        <td>
                            <p>Descripción:</p>
                            <p>${p.descripcion}</p>
                            <p>Año: ${p.ano}</p>
                            <p>Precio: ${p.precio}</p>
                            <p>Unidades actuales: ${p.stock}</p>
                            <p>Agregar unidades: </p>
                            <input type="number" name="unidades" min="1">
                            </p>
                            <input type="hidden" name="idProducto" value="${p.id}">
                            <input type="hidden" name="action" value="agregarUnidades">
                            <input type="submit" value="Guardar" name="Agregar unidades" id="actualizar" class="mover">
                        </td>

                    </form>

                </table>
            </article>

            <article id="comentarios">
                <!--Sección de comentarios-->
                <h1 class="nombre">Comentarios</h1>
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

