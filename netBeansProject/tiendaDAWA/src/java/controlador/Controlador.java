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
import controlador.PaqueteHelperProductos.HelperMostrarTienda;
import controlador.PaqueteHelperProductos.HelperVisualizarProducto;
import controlador.PaqueteHelperUsuarios.HelperIniciarSesion;
import controlador.PaqueteHelperUsuarios.HelperRegistrarUsuario;
import java.io.InputStream;
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

            switch (action) {
                case ("anadirItem"):

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

                    if (sesion.getAttribute("usuario") == null) {
                        request.setAttribute("mensajeError", "Necesitas estar logueado para acceder a esta sección .");
                        goToPage("/usuarios.jsp", request, response);
                    }

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
                        helper = new HelperRealizarPago((Usuario) sesion.getAttribute("usuario"),request.getSession());
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

                    helper = new HelperIniciarSesion(request.getParameter("alias"), request.getParameter("contrasena"), sesion);
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al iniciar sesion (Usuario/Contrasena incorrectos).");
                        goToPage("/error.jsp", request, response);
                    }
                    goToPage("/index.jsp", request, response);

                    break;

                case ("mostrarProducto"):

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
                    helper = new HelperRegistrarUsuario(request.getParameter("nombre"), request.getParameter("alias"), request.getParameter("contrasena"),request.getParameter("email"),request.getParameter("direccion"));
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

}
