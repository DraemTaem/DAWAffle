package modelo.pckProductos;

import java.util.StringTokenizer;

public class CD extends Producto {

    private String autor;
    private String pais;

    public CD(int ID, String nombre, float precio, int stock, String autor, String pais) {
        super(ID, nombre, precio, stock);
        this.autor = autor;
        this.pais = pais;
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
