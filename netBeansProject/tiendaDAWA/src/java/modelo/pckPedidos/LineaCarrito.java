package modelo.pckPedidos;

import modelo.pckProductos.Producto;
import modelo.pckProductos.Tienda;

public class LineaCarrito {

    private Producto producto;
    private int cantidad;
    private float precioLinea;

    public LineaCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        actualizarPrecioDeLaLinea();
    }

    public int actualizar(Tienda tienda) {

        Producto p = tienda.getProductoById(this.producto.getID());
        if (p == null) {
            return -1;
        }

        this.producto = p;
        
        if(this.cantidad>this.producto.getStock()){
            this.cantidad=this.producto.getStock();
            return -2;
        }
        
        actualizarPrecioDeLaLinea();

        return 0;
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
