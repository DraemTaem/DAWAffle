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
                        <p>Año: ${p.ano}</p>
                        <p>Unidades: ${p.stock}</p>
                        <p>Precio: ${p.precio}</p>

                        <form method="post" id="seleccionar" action="Controlador">
                            <input type="hidden" name="action" value="anadirItem">
                            <input type="hidden" name="idProducto" value="${p.id}">
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
                        <input type="number" name="valoracion" placeholder="Valoración" min="1" max="10" required>(Valoración entre 1 y 10)
                    </div>

                    <div>
                        <textarea id="textarea" rows="5" name="comentario" placeholder="Introduce tu comentario" pattern="*{3,}">
                        </textarea>
                    </div>
                    <input type="hidden" name="idProducto" value="${p.id}">
                    <input type="hidden" name="action" value="realizarComentario">
                    <input type="submit" name="comentar" id="comentar" value="Comentar">
                </form>

                <table>
                    <c:forEach var="c" items="${p.valoraciones.valoraciones}">
                        <tr><th>${c.usuario.alias} --- Valoración: ${c.nota}</th></tr>
                        <tr><td>${c.comentario}</td></tr>
                    </c:forEach>
                </table>
            </article>
        </section>
    </center>
</body>
</html>

