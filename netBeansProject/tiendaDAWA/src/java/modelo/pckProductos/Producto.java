package modelo.pckProductos;

import java.util.ArrayList;
import modelo.pckAccesoADatos.pckDAOInterfaz.DAOProducto;
import modelo.pckAccesoADatos.pckDAOInterfaz.FactoriaDAO;
import modelo.pckAccesoADatos.pckVO.VOColeccionValoraciones;
import modelo.pckAccesoADatos.pckVO.VOProducto;
import modelo.pckAccesoADatos.pckVO.VOUsuario;
import modelo.pckAccesoADatos.pckVO.VOValoracion;

public class Producto {

    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    private String imagen;
    private String tipo;
    private int stock;
    private VOColeccionValoraciones valoraciones;

    public Producto(int id, String nombre, String descripcion, float precio, String imagen, String tipo, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.tipo = tipo;
        this.stock = stock;
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

    public boolean leerValoraciones() {

        if (this.valoraciones == null) {

            FactoriaDAO factoria = FactoriaDAO.newFactoria();

            DAOProducto daoProducto = factoria.crearDAOProducto();

            VOProducto aux = daoProducto.getDetallesProducto(this.getId());

            this.valoraciones = aux.getValoraciones();
            this.stock = aux.getStock();

        }
        return true;

    }

    public boolean anadirValoracion(Integer idUsuario, Integer puntuacion, String comentario) {

        if (puntuacion != null && puntuacion <= 10 && puntuacion >= 0) {

            FactoriaDAO factoria = FactoriaDAO.newFactoria();

            DAOProducto daoProducto = factoria.crearDAOProducto();

            VOUsuario vou = new VOUsuario(idUsuario);

            VOValoracion vov = new VOValoracion(this.getId(), vou, comentario, puntuacion);

            return daoProducto.anadirValoracion(vov);

        } else {
            return false;
        }

    }

    public VOColeccionValoraciones getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(VOColeccionValoraciones valoraciones) {
        this.valoraciones = valoraciones;
    }

}
