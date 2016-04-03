package controlador.PaqueteHelperPago;

import javax.servlet.http.HttpSession;
import modelo.pckPedidos.Carrito;
import modelo.pckPedidos.Pedido;
import modelo.pckUsuarios.Usuario;

public class HelperConfirmarPago implements controlador.Helper {

    private HttpSession sesion;

    public HelperConfirmarPago(HttpSession sesion) {
        this.sesion = sesion;
    }

    @Override
    public boolean ejecutar() {

        Pedido pedido = (Pedido) sesion.getAttribute("pedido");

        if (pedido.registrarPedido() == false) {
            return false;
        }

        if (pedido.getUsuario().checkCategoria() == false) {
            return false;
        }

        pedido.enviarCorreoUsuario();

        sesion.setAttribute("mensaje", "Se envia el correo a: " + pedido.getUsuario().getCorreoElectronico());

        //Borramos el pedido de la sesión y reseteamos el carrito al usuario
        sesion.removeAttribute("pedido");
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        usuario.setCarrito(new Carrito());

        return true;
    }

}
