package modelo;

import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private String contrasena;
    private String correoElectronico;
    private Carrito carrito;
    private Integer categoria;  // categoria 0 indica que es normal, 1 indica VIP
    private boolean isAdmin; // isAdmin 0 indica que es un usuario comun, 1 indica que es administrador

    public Usuario(String nombre, String contrasena, String correoElectronico, Integer categoria, boolean isAdmin) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.carrito = new Carrito();
        this.categoria = categoria;
        this.isAdmin = isAdmin;
    }

    public Usuario(String nombre, String contrasena, String correoElectronico) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.carrito = new Carrito();
        this.categoria = 0;
        this.isAdmin = false;
    }

    public Usuario() {//Crea el usuario con un carrito vacio, categoria normal (=0) y de tipo normal (no admin)
        this.carrito = new Carrito();
        categoria = 0;
        isAdmin = false;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void checkCategoria() {    // Metodo que sirve para comprobar si el usuario debe actualizar su categoria en funcion de sus pedidos realizados
        float importe = 0.0f;

        //TODO
        ArrayList ColeccionPedidos = null;  //Llamada a DAO para traer los pedidos del usuario y comprobar que tiene pedidos

        if (ColeccionPedidos != null) {

            /*for (VOPedido in ColeccionPedidos) {
             importe = importe + VOPedido.getPrecioTotal;
             }*/
            if (importe >= 100) {
                this.categoria = 1;
                // Llamada a DAO para actualizar categoria en BD del usuario
            }
        }

    }

}
