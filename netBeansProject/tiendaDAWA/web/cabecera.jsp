<%-- 
    Document   : cabecera
    Created on : 03-abr-2016, 19:12:46
    Author     : Rapnika
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
    <h1> Música </h1>
    <c:choose>
        <c:when test="${sessionScope.usuario == null}">
        <nav>
            <ul id="menu">
                <li><a href="Controlador?pagina=inicio">Inicio</a></li>
                <li><a href="Controlador?pagina=carrito">Ver mi carrito</a></li>
                <li><a href="Controlador?pagina=usuarios">Inicio sesión/Registrarse</a></li>
            </ul>
        </nav>
        </c:when>
        <c:when test="${sessionScope.usuario.isAdmin}">
        <nav>
            <ul id="menu">
                <li><a href="Controlador?pagina=inicio">Inicio</a></li>
                <li><a href="Controlador?pagina=productos">Añadir productos</a></li>
                <li><a href="Controlador?pagina=usuarios">Gestionar usuarios</a></li>
                <li><a href="Controlador?pagina=cerrar">Cerrar sesión</a></li>
            </ul>
        </nav>
        </c:when>
        <c:when test="${sessionScope.usuario != null}">
            <nav>
            <ul id="menu">
                <li><a href="Controlador?pagina=inicio">Inicio</a></li>
                <li><a href="Controlador?pagina=productos">Ver carrito</a></li>
                <li><a href="Controlador?pagina=cerrar">Cerrar sesión</a></li>
            </ul>
        </nav>
        </c:when>
    </c:choose>
</header>

<figure id="guitarra">
    <img src="resources/imagenes/guitarra.jpg" width="1080" height="200">
</figure>
