package controlador.PaqueteHelperPago;

import javax.servlet.http.HttpSession;
import modelo.pckPedidos.Pedido;
import modelo.pckProductos.Tienda;
import modelo.pckUsuarios.Usuario;

public class HelperRealizarPago implements controlador.Helper {

    private Usuario usuario;
    private HttpSession sesion;

    public HelperRealizarPago(Usuario usuario, HttpSession sesion) {
        this.usuario = usuario;
        this.sesion = sesion;
    }

    

    @Override
    public boolean ejecutar() {

        if (this.usuario.getCarrito().getLineasCarrito().size() <= 0) {
            return false;
        }

        //Anadir datos de usuario a this.usuario si é necesario.
        //Aqui sí necesitamos coller os datos da tienda actualizados para facer o pedido
        Tienda tienda;
        Tienda tiendaDesdeSesion = (Tienda) sesion.getAttribute("tienda");

        if (tiendaDesdeSesion == null) {
            Tienda nuevaTienda = new Tienda();
            sesion.setAttribute("tienda", nuevaTienda);
            tienda = nuevaTienda;
        } else {
            tienda = tiendaDesdeSesion;
            tienda.leerProductos();
        }

        Pedido pedido = new Pedido(tienda, usuario);

        sesion.setAttribute("pedido", pedido);

        return true;
    }

}
