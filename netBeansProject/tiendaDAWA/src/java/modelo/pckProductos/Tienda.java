package modelo.pckProductos;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import modelo.pckAccesoADatos.pckDAOInterfaz.DAOProducto;
import modelo.pckAccesoADatos.pckDAOInterfaz.FactoriaDAO;
import modelo.pckAccesoADatos.pckVO.VOCd;
import modelo.pckAccesoADatos.pckVO.VOColeccionProductos;
import modelo.pckAccesoADatos.pckVO.VOProducto;

public class Tienda {

    ArrayList<Producto> productosDisponibles;

    public Tienda() {

        this.productosDisponibles = new ArrayList<>();

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOProducto daoProducto = factoria.crearDAOProducto();

        VOColeccionProductos coleccion = daoProducto.getProductos();

        for (VOProducto voP : coleccion.getProductos()) {
            if (voP instanceof VOCd) {
                VOCd aux = (VOCd) voP;
                CD cd = new CD(aux.getAutor(), aux.getPais(), aux.getAno(), aux.getId(), aux.getNombre(), aux.getDescripcion(), aux.getPrecio(), aux.getImagen(), aux.getTipo(), aux.getStock());
                this.productosDisponibles.add(cd);
            } else {
                Producto producto = new Producto(voP.getId(), voP.getNombre(), voP.getDescripcion(), voP.getPrecio(), voP.getImagen(), voP.getTipo(), voP.getStock());
                this.productosDisponibles.add(producto);
            }

        }

    }

    public Producto getProductoById(int id) {

        for (Producto p : this.productosDisponibles) {
            if (p.getId() == id) {
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

        this.productosDisponibles = new ArrayList<>();

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOProducto daoProducto = factoria.crearDAOProducto();

        VOColeccionProductos coleccion = daoProducto.getProductos();

        for (VOProducto voP : coleccion.getProductos()) {

            if (voP instanceof VOCd) {
                VOCd aux = (VOCd) voP;
                CD cd = new CD(aux.getAutor(), aux.getPais(), aux.getAno(), aux.getId(), aux.getNombre(), aux.getDescripcion(), aux.getPrecio(), aux.getImagen(), aux.getTipo(), aux.getStock());
                this.productosDisponibles.add(cd);
            } else {
                Producto producto = new Producto(voP.getId(), voP.getNombre(), voP.getDescripcion(), voP.getPrecio(), voP.getImagen(), voP.getTipo(), voP.getStock());
                this.productosDisponibles.add(producto);
            }

        }

        return true;
    }

    public ArrayList<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public boolean anadirCD(String nombre, String descripcion, float precio, String imagen, String tipo, Integer stock, String autor, String pais, Integer ano) {

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOProducto daoProducto = factoria.crearDAOProducto();

        VOCd voc = new VOCd(-1, nombre, descripcion, precio, imagen, tipo, stock, autor, pais, ano);

        return daoProducto.registrarCD(voc);
        

    }

    public VOColeccionProductos busquedaCDs(Float precioMaximo, String titulo, String autor, Integer ano) {

        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (ano < 1399 || ano > year) {
            return null;
        }

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOProducto daoProducto = factoria.crearDAOProducto();

        VOColeccionProductos voc = daoProducto.getProductos(precioMaximo, titulo, autor, ano);

        return voc;

    }

    public boolean anadirStock(Integer idProducto, Integer unidades) {

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOProducto daoProducto = factoria.crearDAOProducto();

        return daoProducto.anadirUnidades(new VOProducto(idProducto), unidades);

    }

    public ArrayList<CD> busquedaLocal(Float precioMaximo, String titulo, String autor, Integer ano) {

        ArrayList<CD> alvop = (ArrayList<CD>) this.productosDisponibles.clone();

        ArrayList<CD> toRemove = new ArrayList<>();

        if (precioMaximo != -1) {
            for (CD p : alvop) {
                if (p.getPrecio() > precioMaximo) {
                    toRemove.add(p);
                }
            }
        }

        if (titulo != null && !titulo.equals("")) {
            for (CD p : alvop) {
                if (!p.getNombre().contains(titulo)) {
                    toRemove.add(p);
                }
            }
        }

        if (autor != null && !autor.equals("")) {
            for (CD p : alvop) {
                if (!p.getAutor().contains(autor)) {
                    toRemove.add(p);
                }
            }
        }

        if (ano != -1) {
            for (CD p : alvop) {
                if (!Objects.equals(p.getAno(), ano)) {
                    toRemove.add(p);
                }
            }
        }

        for (CD cd : toRemove) {
            alvop.remove(cd);
        }

        return alvop;

    }

    public VOColeccionProductos getProductosActualizados() {

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOProducto daoProducto = factoria.crearDAOProducto();

        VOColeccionProductos voc = daoProducto.getProductos();

        return voc;

    }

}
