package modelo;


public class Usuario {

    private String nombre;
    private String correoElectronico;
    private Carrito carrito;

    public Usuario(String nombre, String correoElectronico, Carrito carrito) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.carrito = carrito;
    }

    public Usuario() {//Crea el carrito para si mismo
        this.carrito=new Carrito();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

}
