package controlador.PaqueteHelperPago;

import javax.servlet.http.HttpServletRequest;
import modelo.Carrito;
import modelo.Tienda;
import modelo.pckUsuarios.Usuario;

public class HelperMostrarVentanaDePago implements controlador.Helper {

    private Usuario usuario;
    private HttpServletRequest request;

    public HelperMostrarVentanaDePago(Usuario usuario, HttpServletRequest request) {
        this.usuario = usuario;
        this.request = request;
    }


    public boolean ejecutar() {

        Carrito carrito = usuario.getCarrito();
        
        String msg=carrito.actualizar(new Tienda());
        
        if(msg.length()>0){
            this.request.setAttribute("mensajeCarrito", msg);
        }

        return true;
    }

}
