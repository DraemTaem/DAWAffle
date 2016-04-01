package modelo;

import java.util.ArrayList;

public class Pedido {

    private ArrayList<LineaPedido> lineasPedido;
    private Usuario usuario;
    private float precioTotal;

    public Pedido(Tienda tienda, Usuario usuario) {

        this.usuario = usuario;
        Carrito carrito = usuario.getCarrito();
        /*Antes de crear el pedido actualizamos todos los datos
         del carrito para tener información correcta*/
        carrito.actualizar(tienda);

        this.lineasPedido = new ArrayList<>();

        ArrayList<LineaCarrito> lineasCarrito = carrito.getLineasCarrito();

        /*Para todas las lineas de carrito, instanciamos
         una linea de pedido y la anadimos a la lista*/
        for (LineaCarrito lc : lineasCarrito) {
            this.lineasPedido.add(new LineaPedido(lc));
        }

        this.precioTotal = carrito.getPrecioTotal();

    }

    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

}
