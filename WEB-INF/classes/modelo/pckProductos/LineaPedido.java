package modelo;

public class LineaPedido {

    private Producto producto;
    private int cantidad;
    private float precioLinea;

    public LineaPedido(LineaCarrito lc) {
        this.producto = lc.getProducto();
        this.cantidad = lc.getCantidad();
        this.precioLinea = lc.getPrecioLinea();
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecioLinea() {
        return precioLinea;
    }

}
