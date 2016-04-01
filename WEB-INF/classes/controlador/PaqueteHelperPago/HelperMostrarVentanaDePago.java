package controlador.PaqueteHelperPago;

import modelo.Carrito;
import modelo.TiendaAuxiliarArchivo;
import modelo.Usuario;

public class HelperMostrarVentanaDePago implements controlador.Helper {

    private Usuario usuario;

    public HelperMostrarVentanaDePago(Usuario usuario) {
        this.usuario = usuario;
    }

    public void ejecutar() {

        Carrito carrito = usuario.getCarrito();
        carrito.actualizar(new TiendaAuxiliarArchivo());

    }

}
