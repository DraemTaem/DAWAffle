package modelo.pckProductos;

public class Producto {

    private int ID;
    private String nombre;
    private float precio;
    private int stock;

    public Producto(int ID, String nombre, float precio, int stock) {
        this.ID = ID;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
