<%-- 
    Document   : confirmacion
    Created on : 03-abr-2016, 18:17:22
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
            <c:set var="usuario" value="${sessionScope.usuario}"></c:set>
            <p>Hola ${usuario.nombre}, su compra se ha realizado correctamente,</p>
            <p>le enviaremos a ${usuario.correoElectronico} un correo con el detalle de la</p>
            <p>factura.</p>
            <p>Muchas gracias por elegirnos.</p>
            <form action="Controlador" method="post">
                <input type="submit" id="actualizar" name="salir" value="Salir">
            </form>
        </article>
        </section>
        </center>
    </body>
</html>

