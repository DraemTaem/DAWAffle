<%-- 
    Document   : carrito
    Created on : 03-abr-2016, 18:15:40
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
    </head>
    <body>
        <center>
        <%@include file="cabecera.jsp" %>
        <section id="contenido">
        <article id="productos">
        <h1 class="nombre">Lista de artículos</h1>
        
        <p>${requestScope.mensajeError}</p>
        
        <table align="center">
            <tr>
                <th>Descripcion del cd</th>
                <th>Cantidad</th>
                <th>Importe</th>
            </tr>
            <form method="post" action="Controlador">
            <c:forEach var="lc" items="${sessionScope.usuario.carrito.lineasCarrito}">
            <tr>
                <td><p>${lc.producto.nombre}, ${lc.producto.autor}, ${lc.producto.pais}</p>
                <p>${lc.producto.precio}</p></td>
                <td>${lc.cantidad}</td>
                <td>${lc.precioLinea}</td>
                <td>
                    <input type="radio" id="seleccionar" name="botonEliminar" value="${lc.producto.id}">
                </td>
            </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td class="nombre">IMPORTE TOTAL</td>
            <c:set var="precio" value="${sessionScope.usuario.carrito.precioTotal}"></c:set>
                <td>${precio}</td>
                <td>
                    <input type="submit" id="actualizar" name="eliminar" value="Eliminar">
                </td>
            </tr>
            </form>
        
        <tr>
        <form action="Controlador" method="post">
            <td><input type="submit" id="comprar" name="seguirComprando" value="Seguir comprando"></td>
        </form>
        <form action="usuarios.html" method="post">
            <td></td>
            <td></td>
            <td><input type="submit" id="comprar" name="pagar" value="Continuar"></td>
        </form>

        </tr>

        </table>
        </article>
        </section>
        </center>
    </body>
</html>
