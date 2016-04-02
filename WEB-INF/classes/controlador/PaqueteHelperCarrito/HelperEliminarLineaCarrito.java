package controlador.PaqueteHelperCarrito;

import modelo.Carrito;
import modelo.pckUsuarios.Usuario;

public class HelperEliminarLineaCarrito implements controlador.Helper {

    private Usuario usuario;
    private int idProducto;

    public HelperEliminarLineaCarrito(Usuario usuario, int idProducto) {
        this.usuario = usuario;
        this.idProducto = idProducto;
    }

    public boolean ejecutar() {
        
        Carrito carrito = usuario.getCarrito();
        return carrito.eliminarLineaById(idProducto);
        
    }

}
