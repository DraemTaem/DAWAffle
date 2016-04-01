package modelo;

public class Producto {

    private int ID;
    private String nombre;
    private float precio;

    public Producto(int ID, String nombre, float precio) {
        this.ID = ID;
        this.nombre = nombre;
        this.precio = precio;
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
