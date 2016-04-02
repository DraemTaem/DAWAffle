package controlador.PaqueteHelperCarrito;

import modelo.Carrito;
import modelo.TiendaAuxiliarArchivo;
import modelo.pckUsuarios.Usuario;


public class HelperMostrarCarrito implements controlador.Helper {

    private Usuario usuario;

    public HelperMostrarCarrito(Usuario usuario) {
        this.usuario = usuario;
    }

    public void ejecutar() {

        Carrito carrito = usuario.getCarrito();
        carrito.actualizar(new TiendaAuxiliarArchivo());
        
    }

}
