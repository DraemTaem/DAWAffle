package modelo.pckAccesoADatos.pckVO;

import java.util.ArrayList;
import java.util.Date;


public class VOPedido {
    private int id;
    private int idUsuario;
    private int categoriaUsuario;
    private float precio;
    private Date fecha; 
    private ArrayList<VOLineaPedido> lineas;

    public VOPedido(int id) {
        this.id = id;
    }
    
    
    public VOPedido(int id, int idUsuario, int categoriaUsuario, float precio) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.categoriaUsuario = categoriaUsuario;
        this.precio = precio;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCategoriaUsuario() {
        return categoriaUsuario;
    }

    public void setCategoriaUsuario(int categoriaUsuario) {
        this.categoriaUsuario = categoriaUsuario;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<VOLineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<VOLineaPedido> lineas) {
        this.lineas = lineas;
    }

}
