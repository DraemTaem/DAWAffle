package Modelo;


public class VOCd extends VOProducto {

    private String autor;
    private String pais;


    public VOCd(int id) {
        super(id);
    }


    public VOCd(int id, String nombre, String descripcion, float precio, String imagen, String tipo, String autor, String pais) {
        super(id, nombre, descripcion, precio, imagen, tipo);
        this.autor = autor;
        this.pais = pais;
    }

    public VOCd(int id, String nombre, String descripcion, float precio, String imagen, String tipo, int stock, String autor, String pais) {
        super(id, nombre, descripcion, precio, imagen, tipo, stock);
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
