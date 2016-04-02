package Modelo;


public class VOProducto {
    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    private String imagen;
    private String tipo;
    private int stock;
    private VOColeccionValoraciones valoraciones;

    public VOProducto(int id) {
        this.id = id;
    }

    public VOProducto(int id, String nombre, String descripcion, float precio, String imagen, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.tipo = tipo;
    }

    public VOProducto(int id, String nombre, String descripcion, float precio, String imagen, String tipo, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.tipo = tipo;
        this.stock = stock;
    }

    public VOProducto(int id, String nombre, String descripcion, float precio, String imagen, String tipo, int stock, VOColeccionValoraciones valoraciones) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.tipo = tipo;
        this.stock = stock;
        this.valoraciones = valoraciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public VOColeccionValoraciones getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(VOColeccionValoraciones valoraciones) {
        this.valoraciones = valoraciones;
    }
}
