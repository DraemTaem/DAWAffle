<%-- 
    Document   : index
    Created on : 03-abr-2016, 18:14:22
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
            <form id="filtro" method="post" action="Controlador">
                <div id="primeros">

                    <!--<label for="titulo">Titulo:</label>-->
                    <input type="text" class="caja" name="titulo" placeholder="Título" pattern="[A-Za-z]{1,}">

                    <!--<label for="autor">Autor:</label>-->
                    <input type="text" class="caja" name="autor" placeholder="Autor" pattern="[A-Za-z]{1,}">

                    <!--<label for="ano">Año:</label>-->
                    <input type="number" class="caja" placeholder="Año" name="ano" min="1399" max="${sessionScope.anoActual}">
                </div>

                <div id="segundos">
                    <label for="precio">Precio hasta:</label>
                    <input type="number" name="precio" min="1"> 
                    <input type="hidden" name="action" value="buscarItems">
                    <input type="submit" id="actualizar" name="actualizar" value="Actualizar">
                </div>



            </form>
            <hr>
            <div id="productos">
                <table>
                    <c:choose>
                        <c:when test="${requestScope.resultadoBusqueda != null}">
                            <c:choose>
                                <c:when test="${requestScope.resultadoBusqueda.size() != 0}">
                                    <c:forEach var="p" items="${requestScope.resultadoBusqueda}">
                                        <tr>
                                        <div id="datosCD">
                                            <td><img src="${p.imagen}" width="200" height="200"></td>
                                            <td><a href="Controlador?action=mostrarProducto&id=${p.id}"><p class=""nombre>${p.nombre}, ${p.autor}</p></a>
                                                <p>Precio: ${p.precio}€, código: ${p.id}</p></td>
                                        </div>
                                        <form method="post" id="seleccionar" action="Controlador">
                                            <input type="hidden" name="action" value="anadirItem">
                                            <input type="hidden" name="idProducto" value="${p.id}">
                                            <td><input type="number" id="cantidad" placeholder="Cantidad" name="cantidad" min="1" required></td>
                                            <td><input type="submit" id="anadir" name="seleccionar" value="Seleccionar"></td>
                                        </form>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                    <c:otherwise>
                                        <p>No hay productos que se ajusten a esa búsqueda</p>
                                    </c:otherwise>   
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="p" items="${sessionScope.tienda.productosDisponibles}">
                                <tr>
                                <div id="datosCD">
                                    <td><img src="${p.imagen}" width="200" height="200"></td>
                                    <td><a href="Controlador?action=mostrarProducto&id=${p.id}"><p class=""nombre>${p.nombre}, ${p.autor}</p></a>
                                        <p>Precio: ${p.precio}€, código: ${p.id}</p></td>
                                </div>
                                <form method="post" id="seleccionar" action="Controlador">
                                    <input type="hidden" name="action" value="anadirItem">
                                    <input type="hidden" name="idProducto" value="${p.id}">
                                    <td><input type="number" id="cantidad" placeholder="Cantidad" name="cantidad" min="1" required></td>
                                    <td><input type="submit" id="anadir" name="seleccionar" value="Seleccionar"></td>
                                </form>
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
