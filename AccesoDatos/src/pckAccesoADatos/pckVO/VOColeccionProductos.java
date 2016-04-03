package modelo.pckAccesoADatos.pckVO;


import java.util.ArrayList;

public class VOColeccionProductos {
    ArrayList<VOProducto> productos;

    public VOColeccionProductos(ArrayList<VOProducto> productos) {
        this.productos = productos;
    }

    public ArrayList<VOProducto> getProductos() {
        return productos;
    }
}
