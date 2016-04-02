package modelo;

public class LineaCarrito {

    private Producto producto;
    private int cantidad;
    private float precioLinea;

    public LineaCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        actualizarPrecioDeLaLinea();
    }

    public boolean actualizar(Tienda tienda) {

        Producto p = tienda.getProductoById(this.producto.getID());
        if (p == null) {
            return false;
        }

        this.producto = p;
        actualizarPrecioDeLaLinea();

        return true;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        actualizarPrecioDeLaLinea();
    }

    public Producto getProducto() {
        return producto;
    }

    public final void actualizarPrecioDeLaLinea() {
        this.precioLinea = this.producto.getPrecio() * this.cantidad;
    }

    public float getPrecioLinea() {
        actualizarPrecioDeLaLinea();
        return precioLinea;
    }

}
