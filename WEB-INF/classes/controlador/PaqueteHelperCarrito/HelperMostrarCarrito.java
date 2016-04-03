package controlador.PaqueteHelperCarrito;

import modelo.Carrito;
import modelo.pckUsuarios.Usuario;


public class HelperMostrarCarrito implements controlador.Helper {

    private Usuario usuario;

    public HelperMostrarCarrito(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean ejecutar() {
        //Poderíamos actualizar carrito desde a base de datos antes de mostralo se quixeramos
        
        Carrito carrito = usuario.getCarrito();
        return carrito.actualizarPrecioTotal();
        
    }

}
