package controlador.PaqueteHelperCarrito;

import modelo.Carrito;
import modelo.TiendaAuxiliarArchivo;
import modelo.Usuario;

public class HelperEliminarLineaCarrito implements controlador.Helper {

    private Usuario usuario;
    private int idProducto;

    public HelperEliminarLineaCarrito(Usuario usuario, int idProducto) {
        this.usuario = usuario;
        this.idProducto = idProducto;
    }

    public void ejecutar() {
        Carrito carrito = usuario.getCarrito();
        carrito.eliminarLineaById(idProducto);
        carrito.actualizar(new TiendaAuxiliarArchivo());
    }

}
