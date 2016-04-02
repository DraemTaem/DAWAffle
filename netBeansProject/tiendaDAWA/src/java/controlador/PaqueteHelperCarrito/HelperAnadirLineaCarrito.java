package controlador.PaqueteHelperCarrito;

import javax.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.LineaCarrito;
import modelo.Producto;
import modelo.Tienda;
import modelo.pckUsuarios.Usuario;


public class HelperAnadirLineaCarrito implements controlador.Helper {

    private Usuario usuario;
    private int idProducto;
    private int cantidad;
    private HttpSession sesion;

    public HelperAnadirLineaCarrito(HttpSession sesion, Usuario usuario, int idProducto, int cantidad) {
        this.usuario = usuario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.sesion=sesion;
    }

    public boolean ejecutar() {

        Tienda tienda;
        Tienda tiendaDesdeSesion= (Tienda) sesion.getAttribute("tienda");
        
        if(tiendaDesdeSesion==null){
            Tienda nuevaTienda= new Tienda();
            sesion.setAttribute("tienda", nuevaTienda);
            tienda=nuevaTienda;
        }else{
            tienda=tiendaDesdeSesion;
        }
        
        Producto producto = tienda.getProductoById(idProducto);

        boolean resultado=false;
        
        if (producto != null) {
            Carrito carrito = usuario.getCarrito();
            resultado=carrito.insertarLinea(new LineaCarrito(producto, cantidad));
        }
        
        return resultado;

    }

}
