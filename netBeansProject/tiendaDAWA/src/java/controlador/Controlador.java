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
import controlador.PaqueteHelperUsuarios.HelperIniciarSesion;
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

                    helper = new HelperAnadirLineaCarrito(sesion, (Usuario) sesion.getAttribute("usuario"), Integer.parseInt(request.getParameter("producto")), Integer.parseInt(request.getParameter("cantidad")));
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al agregar linea al carrito.");
                        goToPage("/error.jsp", request, response);
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
                        goToPage("/pagando.jsp", request, response);
                    } else {
                        request.setAttribute("mensajeError", "No tienes items en el carrito para comprar.");
                        goToPage("/error.jsp", request, response);
                    }
                    break;

                case ("insertarDatosPedido"):

                    if (sesion.getAttribute("usuario") == null) {
                        request.setAttribute("mensajeError", "Necesitas estar logueado para acceder a esta sección .");
                        goToPage("/error.jsp", request, response);
                    }

                    Usuario usuarioDatos = (Usuario) sesion.getAttribute("usuario");

                    if (usuarioDatos.getCarrito().getLineasCarrito().size() > 0) {
                        helper = new HelperRealizarPago(request.getSession(), (Usuario) sesion.getAttribute("usuario"), request.getParameter("nombreDeUsuario"), request.getParameter("email"));
                        if (!helper.ejecutar()) {
                            request.setAttribute("mensajeError", "Error al insertar datos de pedido.");
                            goToPage("/error.jsp", request, response);
                        }
                        goToPage("/confirmarPago.jsp", request, response);
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
                    goToPage("/exitoEnElPago.jsp", request, response);
                    break;

                case ("irAIniciarSesion"):

                    goToPage("/usuarios.jsp", request, response);
                    break;

                case ("iniciarSesion"):

                    helper = new HelperIniciarSesion((String) request.getAttribute("alias"), (String) request.getAttribute("contrasena"), sesion);
                    if (!helper.ejecutar()) {
                        request.setAttribute("mensajeError", "Error al iniciar sesion (Usuario/Contrasena incorrectos).");
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
