package controlador.PaqueteHelperCarrito;

import javax.servlet.http.HttpSession;
import modelo.pckPedidos.Carrito;
import modelo.pckPedidos.LineaCarrito;
import modelo.pckProductos.Producto;
import modelo.pckProductos.Tienda;
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

    @Override
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

        System.out.println(producto.getStock());
        
        boolean resultado=false;
        
        if (producto != null) {
            Carrito carrito = usuario.getCarrito();
            resultado=carrito.insertarLinea(new LineaCarrito(producto, cantidad));
        }
        
        return resultado;

    }

}
