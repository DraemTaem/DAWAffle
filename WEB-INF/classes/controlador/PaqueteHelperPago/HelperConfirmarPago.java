package controlador.PaqueteHelperPago;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Pedido;
import modelo.pckUsuarios.Usuario;

public class HelperConfirmarPago implements controlador.Helper {
    
    private HttpSession sesion;
    
    public HelperConfirmarPago(HttpSession sesion) {
        this.sesion = sesion;
    }
    
    @Override
    public void ejecutar() {
        
        Pedido pedido = (Pedido) sesion.getAttribute("pedido");
        //Por ahora no hacemos nada con el pedido a la hora de guardarlo.
        sesion.setAttribute("mensaje", "Se envia el correo a: " + pedido.getUsuario().getCorreoElectronico());

        //Borramos el pedido de la sesión y reseteamos el carrito al usuario
        sesion.removeAttribute("pedido");
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        usuario.setCarrito(new Carrito());
        
    }
    
}
