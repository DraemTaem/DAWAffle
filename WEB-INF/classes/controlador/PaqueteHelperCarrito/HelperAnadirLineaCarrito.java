package controlador.PaqueteHelperCarrito;

import modelo.Carrito;
import modelo.LineaCarrito;
import modelo.Producto;
import modelo.Tienda;
import modelo.TiendaAuxiliarArchivo;
import modelo.Usuario;

public class HelperAnadirLineaCarrito implements controlador.Helper {

    private Usuario usuario;
    private int idProducto;
    private int cantidad;

    public HelperAnadirLineaCarrito(Usuario usuario, int idProducto, int cantidad) {
        this.usuario = usuario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public void ejecutar() {

        Tienda tienda = new TiendaAuxiliarArchivo();
        Producto producto = tienda.getProductoById(idProducto);

        if (producto != null) {
            Carrito carrito = usuario.getCarrito();
            carrito.insertarLinea(new LineaCarrito(producto, cantidad));
        }

    }

}
