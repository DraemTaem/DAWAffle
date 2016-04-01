package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Tienda {

    ArrayList<Producto> productosDisponibles;

    
    public Tienda(){
    
        //TODO
        
        this.productosDisponibles= new ArrayList<>();
        
        Producto producto = new Producto(1, "Cacota", 10.3f);
        
        this.productosDisponibles.add(producto);
        
    }
    
    public Producto getProductoById(int id) {

        for (Producto p : this.productosDisponibles) {
            if (p.getID() == id) {
                return p;
            }
        }
        return null;
    }

    public int getNumeroDeProductos() {
        return this.productosDisponibles.size();
    }

    public Producto getProductoByIndex(int index) {
        return this.productosDisponibles.get(index);
    }

    public final boolean leerProductos(ArrayList<Producto> lista, String path) {

        //TODO
        return true;
    }

    public ArrayList<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

}
