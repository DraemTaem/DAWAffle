package controlador.PaqueteHelperProductos;

import javax.servlet.http.HttpSession;
import modelo.pckProductos.Tienda;

public class HelperIntroducirValoracion implements controlador.Helper {
    
    private Integer idUsuario;
    private Integer idProducto;
    private Integer valoracion;
    private String comentario;
    private HttpSession sesion;

    public HelperIntroducirValoracion(HttpSession sesion, Integer idUsuario, Integer idProducto, Integer valoracion, String comentario) {
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.sesion = sesion;
    }

    @Override
    public boolean ejecutar() {
        
        Tienda tienda;
        Tienda tiendaDesdeSesion = (Tienda) sesion.getAttribute("tienda");

        if (tiendaDesdeSesion == null) {
            Tienda nuevaTienda = new Tienda();
            sesion.setAttribute("tienda", nuevaTienda);
            tienda = nuevaTienda;
        } else {
            tienda = tiendaDesdeSesion;
        }
        
        return tienda.getProductoById(idProducto).anadirValoracion(idUsuario, valoracion, comentario);
        
    }
    
}
