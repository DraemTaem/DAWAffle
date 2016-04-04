package controlador.PaqueteHelperCarrito;

import modelo.pckPedidos.Carrito;
import modelo.pckUsuarios.Usuario;

public class HelperEliminarLineaCarrito implements controlador.Helper {

    private Usuario usuario;
    private int idProducto;

    public HelperEliminarLineaCarrito(Usuario usuario, int idProducto) {
        this.usuario = usuario;
        this.idProducto = idProducto;
    }

    @Override
    public boolean ejecutar() {
        
        Carrito carrito = usuario.getCarrito();
        return carrito.eliminarLineaById(idProducto);
        
    }

}
