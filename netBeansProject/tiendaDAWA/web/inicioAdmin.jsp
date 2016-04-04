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
        <%@include file="cabecera.jsp" %>

        <section id="contenido">
            <div id="productos">
                <table>

                    <c:forEach var="p" items="${sessionScope.productos.lista}">
                        <tr>
                        <div id="datosCD">
                            <td>${p.getImagen()}</td>
                            <td><a href="descripcionAdminProducto.html"><p class=""nombre>${p.getTitulo()}, ${p.getAutor()}</p></a>
                                <p>Precio: ${p.getPrecio()}€, código: ${p.getCodigo()}</p></td>
                        </div>
                        <form method="post" id="seleccionar" onsubmit="return validar()">
                            <input type="hidden" value="${p.getCodigo()}">
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
