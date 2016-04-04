<%-- 
    Document   : usuarios
    Created on : 03-abr-2016, 18:20:34
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
        <link rel="stylesheet" type="text/css" href="resources/css/estiloUsuarios.css">
    </head>
    <body>
    <center>
        <%@include file="cabecera.jsp" %>

        <section id="contenido">
            <article id="productos">
                <table>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <th>Inicio de sesión</th>
                                </tr>
                                <tr>
                                    <td>
                                        <form method="post" action="Controlador">
                                            <input type="hidden" name="action" value="iniciarSesion">
                                            <div>*<input type="text" name="alias" placeholder="Alias" pattern="[A-Za-z0-9]{2,}" required></div>
                                            <div>*<input type="password" name="contrasena" placeholder="Contraseña" pattern="[A-Za-z0-9\-\.\,\_]{6,}" required></div>
                                            <div><input type="submit" id="comprar" name="entrar" value="Entrar"></div> 
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>

                        <td>
                            <table>
                                <tr>
                                    <th>Regístrate</th>
                                </tr>
                                <tr>
                                    <td>
                                        <form method="post" action="Controlador">
                                            <input type="hidden" name="action" value="registrarUsuario">
                                            <div>*<input type="text" name="nombre" placeholder="Nombre y apellidos" pattern="[A-Za-z báéíóúÁÉÍÓÚ]{2,}" required></div>
                                            <div>*<input type="text" name="alias" placeholder="Alias" pattern="[A-Za-z0-9]{2,}" required></div>
                                            <div>*<input type="email" name="email" placeholder="Correo electrónico"  pattern="^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$" required></div>
                                            <div>*<input type="text" name="direccion" placeholder="Dirección" required></div>
                                            <div>*<input type="password" name="contrasena" placeholder="Contraseña" pattern="[A-Za-z0-9\-\.\,\_]{6,}" required></div>
                                            <div><input type="submit" id="comprar" name="registro" value="Registrarse"></div> 

                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </article>
        </section>
    </center>
</body>
</html>
