package Modelo;


import java.util.ArrayList;

public class VOColeccionValoraciones {
    private ArrayList<VOValoracion> valoraciones;

    public VOColeccionValoraciones(ArrayList<VOValoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public ArrayList<VOValoracion> getValoraciones() {
        return valoraciones;
    }
}
