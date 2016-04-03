package modelo.pckProductos;

import java.util.ArrayList;

public class Tienda {

    ArrayList<Producto> productosDisponibles;

    
    public Tienda(){
    
       //TODO con el DAO
        
        this.productosDisponibles= new ArrayList<>();
        
        
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

    public final boolean leerProductos() {

        //TODO
        return true;
    }

    public ArrayList<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

}
