package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Tienda {

    ArrayList<Producto> productosDisponibles;

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

        productosDisponibles = new ArrayList<>();

        File file = new File(path);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {
                CD cd;
                cd = new CD(i, line);
                this.productosDisponibles.add((Producto) cd);
                i++;
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            //Podríamos crear una vista de error para mostrar este tipo de cosas
            return false;
        }
        return true;
    }

    public ArrayList<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

}
