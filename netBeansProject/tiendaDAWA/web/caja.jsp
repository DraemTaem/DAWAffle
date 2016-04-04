<%-- 
    Document   : caja
    Created on : 03-abr-2016, 18:16:36
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
            <h1 class="nombre">TOTAL A PAGAR:</h1>
            <c:set var="p" value="${sessionScope.pedido}"></c:set>
                <c:set var="precio" value="${p.precioTotal}"></c:set>
            <p>${precio}</p>
            
            <c:choose>
                <c:when test="${p.usuario.categoria == 1}">
                    <p>Usted es vip, por lo que se le ha descontado un 20% en la factura</p>
                </c:when>
                <c:when test="${p.usuario.categoria == 0}">
                    <p>Usted es un usuario básico, por lo que no aplica ningún tipo de descuento en la factura</p>
                </c:when>
            </c:choose>
            

            <p>¿Deseas finalizar la compra?</p>
            <form action="Controlador" method="post">
                <input type="hidden" name="action" value="confirmarPago">
                <input type="submit" name="confirmar" id="comprar" value="Comprar">
            </form>
        </article>
        </section>
        </center>
    </body>
</html>

