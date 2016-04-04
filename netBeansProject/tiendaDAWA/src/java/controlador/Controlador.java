package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.PaqueteHelperCarrito.*;
import controlador.PaqueteHelperPago.*;
import controlador.PaqueteHelperPrincipal.*;
import controlador.PaqueteHelperProductos.HelperAnadirStock;
import controlador.PaqueteHelperProductos.HelperBusquedaCD;
import controlador.PaqueteHelperProductos.HelperMostrarTienda;
import controlador.PaqueteHelperProductos.HelperVisualizarProducto;
import controlador.PaqueteHelperUsuarios.HelperIniciarSesion;
import controlador.PaqueteHelperUsuarios.HelperRegistrarUsuario;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import modelo.pckPedidos.Pedido;
import modelo.pckUsuarios.Usuario;

public class Controlador extends HttpServlet {

    private Helper helper;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Miramos si la sesión está creada
         */
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            sesion = request.getSession(true);
        }

        String action = request.getParameter("action");

        if (action == null) {

            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            if (usuario != null && usuario.isIsAdmin()) {
                goToPage("/inicioAdmin.jsp", request, response);
            }

            helper = new HelperMostrarPrincipal(sesion);
            if (!helper.ejecutar()) {
                request.setAttribute("mensajeError", "Error al ir a la página principal.");
                goToPage("/error.jsp", request, response);
            }

            helper = new HelperMostrarTienda(sesion);
            if (!helper.ejecutar()) {
                request.setAttribute("mensajeError", "Error al acceder a los datos de la tienda");
                goToPage("/error.jsp", request, response);
            }

            goToPage("/index.jsp", request, response);

        } else {

            if (sesion.getAttribute("usuario") != null) {
                Usuario usuario = (Usuario) sesion.getAttribute("usuario");
                if (usuario.isIsAdmin()) {
                    /*----------------------------------------------------------------------------------------------*/
                    /*-------------------------------SECCIÓN DEL ADMINISTRADOR--------------------------------------*/
                    /*----------------------------------------------------------------------------------------------*/
                    System.out.println("ADMIN MODE");
                    switch (action) {

                        case ("agregarUnidades"):
                            this.validarRequest("unidades", request, response);
                            this.validarRequest("idProducto", request, response);

                            helper = new HelperAnadirStock(Integer.parseInt(request.getParameter("unidades")), Integer.parseInt(request.getParameter("idProducto")), sesion);
                            if (!helper.ejecutar()) {
                                request.setAttribute("mensajeError", "Error al anadir stock a un producto [ADMIN]");
                                goToPage("/error.jsp", request, response);
                            }
                            goToPage("/Controlador?action=mostrarProductoAdmin&id="+request.getParameter("idProducto"), request, response);

                            break;

                        case ("mostrarProductoAdmin"):

                            this.validarRequest("id", request, response);

                            helper = new HelperVisualizarProducto(Integer.parseInt(request.getParameter("id")), sesion, request);
                            if (!helper.ejecutar()) {
                                request.setAttribute("mensajeError", "Error al acceder a la página de ver producto [ADMIN]");
                                goToPage("/error.jsp", request, response);
                            }
                            goToPage("/descripcionAdminProducto.jsp", request, response);

                            break;

                        case ("cerrarSesion"):

                            if (sesion.getAttribute("usuario") == null) {
                                request.setAttribute("mensajeError", "Necesitas estar logueado para cerrar sesión.");
                                goToPage("/error.jsp", request, response);
                            }

                            sesion.removeAttribute("usuario");

                            goToPage("/index.jsp", request, response);

                            break;

                        default:

                            helper = new HelperMostrarPrincipal(sesion);
                            if (!helper.ejecutar()) {
                                request.setAttribute("mensajeError", "Error al ir a la página principal.");
                                goToPage("/error.jsp", request, response);
                            }

                            helper = new HelperMostrarTienda(sesion);
                            if (!helper.ejecutar()) {
                                request.setAttribute("mensajeError", "Error al acceder a los datos de la tienda");
                                goToPage("/error.jsp", request, response);
                            }
                            goToPage("/inicioAdmin.jsp", request, response);

                            break;

                    }

                }
            }

            /*----------------------------------------------------------------------------------------------*/
            /*-------------------------------SECCIÓN DEL USUARIO NORMAL-------------------------------------*/
            /*----------------------------------------------------------------------------------------------*/
            switch (action) {
                case ("buscarItems"):

                    this.validarRequest("titulo", request, response);
                    this.validarRequest("autor", request, response);
                    this.validarRequest("ano", request, response);
                    this.validarRequest("precio", request, response);

                    float preciof;
                    if (request.getParameter("precio") != null && !request.getParameter("precio").equals("")) {
                        preciof = Float.parseFloat(request.getParameter("precio"));
                    } else {
                        preciof = -1;
                    }

                    helper = new HelperBusquedaCD(sesion, request, preciof, request.getParameter("titulo"), request.getParameter("autor"), request.getParameter("ano"));
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error en la busqueda de items");
                        goToPage("/error.jsp", request, response);
                    }
                    goToPage("/index.jsp", request, response);
                    break;

                case ("anadirItem"):

                    this.validarRequest("idProducto", request, response);
                    this.validarRequest("cantidad", request, response);

                    if (sesion.getAttribute("usuario") == null) {
                        request.setAttribute("mensajeError", "Necesitas estar logueado para acceder a esta sección .");
                        goToPage("/usuarios.jsp", request, response);
                    }

                    helper = new HelperAnadirLineaCarrito(sesion, (Usuario) sesion.getAttribute("usuario"), Integer.parseInt(request.getParameter("idProducto")), Integer.parseInt(request.getParameter("cantidad")));
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "La cantidad solicitada no está disponible");
                        goToPage("/carrito.jsp", request, response);
                    }
                    goToPage("/carrito.jsp", request, response);
                    break;

                case ("eliminarLinea"):

                    this.validarRequest("idEliminar", request, response);

                    helper = new HelperEliminarLineaCarrito((Usuario) sesion.getAttribute("usuario"), Integer.parseInt(request.getParameter("idEliminar")));
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al eliminar linea del carrito.");
                        goToPage("/error.jsp", request, response);
                    }
                    goToPage("/carrito.jsp", request, response);
                    break;

                case ("irAlCarrito"):

                    if (sesion.getAttribute("usuario") == null) {
                        goToPage("/usuarios.jsp", request, response);
                    }

                    helper = new HelperMostrarCarrito((Usuario) sesion.getAttribute("usuario"));
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al acceder al carrito.");
                        goToPage("/error.jsp", request, response);
                    }
                    goToPage("/carrito.jsp", request, response);
                    break;

                case ("irAPrincipal"):

                    helper = new HelperMostrarPrincipal(sesion);
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al ir a la página principal.");
                        goToPage("/error.jsp", request, response);
                    }

                    helper = new HelperMostrarTienda(sesion);
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al acceder a los datos de la tienda");
                        goToPage("/error.jsp", request, response);
                    }

                    goToPage("/index.jsp", request, response);
                    break;

                case ("mostrarVentanaDePago"):

                    if (sesion.getAttribute("usuario") == null) {
                        request.setAttribute("mensajeError", "Necesitas estar logueado para acceder a esta sección .");
                        goToPage("/error.jsp", request, response);
                    }

                    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
                    if (usuario.getCarrito().getLineasCarrito().size() > 0) {
                        helper = new HelperMostrarVentanaDePago((Usuario) sesion.getAttribute("usuario"), request);
                        if (!helper.ejecutar()) {
                            request.setAttribute("mensajeError", "Error al mostrar ventana de pago.");
                            goToPage("/error.jsp", request, response);
                        }
                        goToPage("/ventanaPago.jsp", request, response);
                    } else {
                        request.setAttribute("mensajeError", "No tienes items en el carrito para comprar.");
                        goToPage("/carrito.jsp", request, response);
                    }
                    break;

                case ("insertarDatosPedido"):

                    if (sesion.getAttribute("usuario") == null) {
                        request.setAttribute("mensajeError", "Necesitas estar logueado para acceder a esta sección .");
                        goToPage("/error.jsp", request, response);
                    }

                    Usuario usuarioDatos = (Usuario) sesion.getAttribute("usuario");

                    if (usuarioDatos.getCarrito().getLineasCarrito().size() > 0) {
                        helper = new HelperRealizarPago((Usuario) sesion.getAttribute("usuario"), request.getSession());
                        if (!helper.ejecutar()) {
                            request.setAttribute("mensajeError", "Error al insertar datos de pedido.");
                            goToPage("/error.jsp", request, response);
                        }
                        goToPage("/caja.jsp", request, response);
                    } else {
                        request.setAttribute("mensajeError", "No tienes items en el carrito para completar el pedido.");
                        goToPage("/error.jsp", request, response);
                    }
                    break;

                case ("confirmarPago"):

                    if (sesion.getAttribute("usuario") == null) {
                        request.setAttribute("mensajeError", "Necesitas estar logueado para acceder a esta sección.");
                        goToPage("/error.jsp", request, response);
                    }

                    Pedido pedido = (Pedido) sesion.getAttribute("pedido");

                    if (pedido == null) {
                        request.setAttribute("mensajeError", "No hay un pedido registrado para confirmar.");
                        goToPage("/error.jsp", request, response);
                    }

                    helper = new HelperConfirmarPago(request.getSession());
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al confirmar pago.");
                        goToPage("/error.jsp", request, response);
                    }
                    goToPage("/confirmacion.jsp", request, response);
                    break;

                case ("irAIniciarSesion"):

                    goToPage("/usuarios.jsp", request, response);
                    break;

                case ("iniciarSesion"):

                    this.validarRequest("alias", request, response);
                    this.validarRequest("contrasena", request, response);

                    helper = new HelperIniciarSesion(request.getParameter("alias"), request.getParameter("contrasena"), sesion);
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al iniciar sesion (Usuario/Contrasena incorrectos).");
                        goToPage("/error.jsp", request, response);
                    }
                    Usuario aux = (Usuario) sesion.getAttribute("usuario");
                    if (aux.isIsAdmin()) {
                        goToPage("/inicioAdmin.jsp", request, response);
                    } else {
                        goToPage("/index.jsp", request, response);
                    }
                    break;

                case ("mostrarProducto"):

                    this.validarRequest("id", request, response);

                    helper = new HelperVisualizarProducto(Integer.parseInt(request.getParameter("id")), sesion, request);
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al acceder a la página principal");
                        goToPage("/error.jsp", request, response);
                    }
                    goToPage("/descripcion.jsp", request, response);
                    break;

                case ("cerrarSesion"):

                    if (sesion.getAttribute("usuario") == null) {
                        request.setAttribute("mensajeError", "Necesitas estar logueado para cerrar sesión.");
                        goToPage("/error.jsp", request, response);
                    }

                    sesion.removeAttribute("usuario");

                    goToPage("/index.jsp", request, response);

                    break;

                case ("registrarUsuario"):

                    this.validarRequest("nombre", request, response);
                    this.validarRequest("alias", request, response);
                    this.validarRequest("contrasena", request, response);
                    this.validarRequest("email", request, response);
                    this.validarRequest("direccion", request, response);

                    helper = new HelperRegistrarUsuario(request.getParameter("nombre"), request.getParameter("alias"), request.getParameter("contrasena"), request.getParameter("email"), request.getParameter("direccion"));
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al registrar usuario (Datos duplicados).");
                        goToPage("/error.jsp", request, response);
                    }
                    goToPage("/index.jsp", request, response);

                    break;

                default:
                    helper = new HelperMostrarPrincipal(sesion);
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al acceder a la página principal");
                        goToPage("/error.jsp", request, response);
                    }
                    helper = new HelperMostrarTienda(sesion);
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al acceder a los datos de la tienda");
                        goToPage("/error.jsp", request, response);
                    }
                    goToPage("/index.jsp", request, response);
                    break;
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador de la aplicación de nuestra tienda.";
    }

    private void goToPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(address);
        rd.forward(request, response);
    }

    private void validarRequest(String aValidar, HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter(aValidar) == null) {
            try {
                request.setAttribute("mensajeError", "Falta el campo [" + aValidar + "]");
                goToPage("/error.jsp", request, response);
            } catch (ServletException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
