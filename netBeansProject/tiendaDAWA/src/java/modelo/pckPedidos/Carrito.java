package modelo.pckPedidos;

import java.util.ArrayList;
import modelo.pckProductos.Producto;
import modelo.pckProductos.Tienda;

public class Carrito {

    private ArrayList<LineaCarrito> lineasCarrito;
    private float precioTotal;

    public Carrito() {
        this.lineasCarrito = new ArrayList<>();
    }

    public boolean insertarLinea(LineaCarrito lc) {

        for (LineaCarrito lcaux : lineasCarrito) {
            if (lcaux.getProducto().getId() == lc.getProducto().getId()) {
                lcaux.setCantidad((lcaux.getCantidad() + lc.getCantidad()));
                this.actualizarPrecioTotal();
                return true;
            }
        }
        this.lineasCarrito.add(lc);
        this.actualizarPrecioTotal();
        return true;
    }

    /*Esta función si es necesaria en carrito pues vamos a 
     estar cambiando con frecuencia sus lineas y no queremos que
     cada vez que se cambie una línea se acceda a donde estén guardados
     los productos para actualizar todos los objetos Producto de 
     las lineas*/
    public boolean actualizarPrecioTotal() {
        float precioAux = 0.0f;

        for (LineaCarrito lc : lineasCarrito) {
            precioAux += lc.getPrecioLinea();
        }

        if (precioAux >= 0) {
            this.precioTotal = precioAux;
            return true;
        } else {
            return false;
        }

    }

    /*Usaremos esta función para actualizar el carrito siempre
     que accedamos a el, lo que se hace es actualizar el objeto producto
     que se encuentra en cada linea de carrito para así tener sus datos
     al día, sobre todo el precio, que se recalculará a nivel de linea
     y tambien a nivel de carrito una vez que tengamos todas las
     líneas actualizadas*/
    public String actualizar(Tienda tienda) {
        
        String mensaje="";
        
        for (LineaCarrito lc : lineasCarrito) {
            int unidadesAnteriores= lc.getCantidad();
            int res=lc.actualizar(tienda);
            
            if(res==-2){
            mensaje+="Para el producto "+lc.getProducto().getNombre()+" se ha actualizado"
                    + "la cantidad a la máxima disponible ["+lc.getCantidad()+"] pues no había la cantidad solicitada ["+unidadesAnteriores+"]\n\n";
            }else if(res==-1){
                this.eliminarLineaById(lc.getProducto().getId());
                mensaje+="El producto "+lc.getProducto().getNombre()+" no está disponible, se ha eliminado del carrito\n\n";
            }
            
        }
        this.actualizarPrecioTotal();

        return mensaje;
    }

    public boolean eliminarLineaById(int id) {

        for (LineaCarrito lc : lineasCarrito) {
            if (lc.getProducto().getId() == id) {
                this.eliminarLineaByObj(lc);
                this.actualizarPrecioTotal();
                return true;
            }
        }
        return false;
    }

    public boolean eliminarLineaByObj(LineaCarrito lc) {
        return this.lineasCarrito.remove(lc);
    }

    public ArrayList<LineaCarrito> getLineasCarrito() {
        return lineasCarrito;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

}
