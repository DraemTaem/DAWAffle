package modelo;

import java.util.StringTokenizer;

public class CD extends Producto {

    private String autor;
    private String pais;

    public CD(int ID, String nombre, float precio, String autor, String pais) {
        super(ID, nombre, precio);
        this.autor = autor;
        this.pais = pais;
    }

    public CD(int ID, String lineaDescriptiva) {

        super(ID, "aux", 10.0f);

        StringTokenizer t = new StringTokenizer(lineaDescriptiva, "|");
        String nombreAux = t.nextToken();
        this.autor = t.nextToken();
        this.pais = t.nextToken();
        String strPrecioAux = t.nextToken();
        strPrecioAux = strPrecioAux.replace('$', ' ').trim();
        float precioAux = Float.parseFloat(strPrecioAux);

        super.setID(ID);
        super.setNombre(nombreAux);
        super.setPrecio(precioAux);

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
