package modelo.pckProductos;

import modelo.pckAccesoADatos.pckDAOInterfaz.DAOProducto;
import modelo.pckAccesoADatos.pckDAOInterfaz.FactoriaDAO;

public class CD extends Producto {

    private String autor;
    private String pais;
    private Integer ano;

    public CD(String autor, String pais, Integer ano, int id, String nombre, String descripcion, float precio, String imagen, String tipo, int stock) {
        super(id, nombre, descripcion, precio, imagen, tipo, stock);
        this.autor = autor;
        this.pais = pais;
        this.ano = ano;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


}
