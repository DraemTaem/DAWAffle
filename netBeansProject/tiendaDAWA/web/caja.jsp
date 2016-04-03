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
            <h1 class="nombre">TOTAL A PAGAR:</h1>
                <c:set var="precio" value="${sessionScope.productos.precioTotal()}"></c:set>
            <p>${precio}</p>

            <p>¿Deseas finalizar la compra?</p>
            <form action="Controlador" method="post">
                <input type="submit" name="confirmar" id="comprar" value="Comprar">
            </form>
        </article>
        </section>
        </center>
    </body>
</html>

