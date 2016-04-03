package controlador.PaqueteHelperProductos;

import modelo.pckProductos.Tienda;

public class HelperAnadirCD implements controlador.Helper {

    private String nombre;
    private String descripcion;
    private String autor;
    private String pais;
    private Integer stock;
    private Float precio;
    private String url;

    public HelperAnadirCD(String nombre, String descripcion, String autor, String pais, Integer stock, Float precio, String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor = autor;
        this.pais = pais;
        this.stock = stock;
        this.precio = precio;
        this.url = url;
    }

    @Override
    public boolean ejecutar() {

        Tienda tienda = new Tienda();

        return tienda.anadirCD(nombre, descripcion, precio, url, "cd", stock, autor, pais);

    }

}
