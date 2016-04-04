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
                    <c:choose>
                    <c:when test="${requestScope.resultadoBusqueda != null}">
                        <c:forEach var="p" items="${requestScope.resultadoBusqueda}">
                        <tr>
                        <div id="datosCD">
                            <td><img src="${p.imagen}" width="200" height="200"></td>
                            <td><a href="Controlador?action=mostrarProductoAdmin&id=${p.id}"><p class=""nombre>${p.nombre}, ${p.autor}</p></a>
                                <p>Precio: ${p.precio}€, código: ${p.id}</p></td>
                        </div>
                        </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="p" items="${sessionScope.tienda.productosDisponibles}">
                        <tr>
                        <div id="datosCD">
                            <td><img src="${p.imagen}" width="200" height="200"></td>
                            <td><a href="Controlador?action=mostrarProductoAdmin&id=${p.id}"><p class=""nombre>${p.nombre}, ${p.autor}</p></a>
                                <p>Precio: ${p.precio}€, código: ${p.id}</p></td>
                        </div>
                        </tr>
                        </c:forEach>
                    </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </section>
    </center>
</body>
</html>


