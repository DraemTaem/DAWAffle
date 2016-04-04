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
                <li><a href="Controlador?action=irAPrincipal">Inicio</a></li>
                <li><a href="Controlador?action=irAlCarrito">Ver mi carrito</a></li>
                <li><a href="Controlador?action=irAIniciarSesion">Inicio sesión/Registrarse</a></li>
            </ul>
        </nav>
        </c:when>
        <c:when test="${sessionScope.usuario.isAdmin}">
        <nav>
            <ul id="menu">
                <li><a href="Controlador?action=irAPrincipal">Inicio</a></li>
                <li><a href="Controlador?action=irAAnadirCD">Añadir productos</a></li>
                <li><a href="Controlador?action=usuarios">Gestionar usuarios TODO</a></li>
                <li><a href="Controlador?action=cerrarSesion">Cerrar sesión</a></li>
            </ul>
        </nav>
        </c:when>
        <c:when test="${sessionScope.usuario != null}">
            <nav>
            <ul id="menu">
                <li><a href="Controlador?action=irAPrincipal">Inicio</a></li>
                <li><a href="Controlador?action=irAlCarrito">Ver carrito</a></li>
                <li><a href="Controlador?action=cerrarSesion">Cerrar sesión</a></li>
            </ul>
        </nav>
        </c:when>
    </c:choose>
</header>

<figure id="guitarra">
    <img src="resources/imagenes/guitarra.jpg" width="1080" height="200">
</figure>
