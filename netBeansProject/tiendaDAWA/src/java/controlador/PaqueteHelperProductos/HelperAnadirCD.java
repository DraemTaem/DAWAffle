package controlador.PaqueteHelperProductos;

import javax.servlet.http.HttpSession;
import modelo.pckProductos.Tienda;

public class HelperAnadirCD implements controlador.Helper {

    private String nombre;
    private String descripcion;
    private String autor;
    private String pais;
    private Integer stock;
    private Float precio;
    private String url;
    private HttpSession session;
    private String ano;

    public HelperAnadirCD(String nombre, String descripcion, String autor, String pais, Integer stock, Float precio, String url, HttpSession session, String ano) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor = autor;
        this.pais = pais;
        this.stock = stock;
        this.precio = precio;
        this.url = url;
        this.session = session;
        this.ano = ano;
    }

    

    @Override
    public boolean ejecutar() {

        Tienda tienda = new Tienda();
        session.setAttribute("tienda", tienda);

        return tienda.anadirCD(nombre, descripcion, precio, url, "cd", stock, autor, pais,Integer.parseInt(ano));

    }

}
