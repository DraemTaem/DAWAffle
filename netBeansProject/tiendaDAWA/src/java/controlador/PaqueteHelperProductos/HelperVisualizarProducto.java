package controlador.PaqueteHelperProductos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.pckProductos.Producto;
import modelo.pckProductos.Tienda;

public class HelperVisualizarProducto implements controlador.Helper {

    private Integer id;
    private HttpSession session;
    private HttpServletRequest request;

    public HelperVisualizarProducto(Integer id, HttpSession session, HttpServletRequest request) {
        this.id = id;
        this.session = session;
        this.request = request;
    }

    @Override
    public boolean ejecutar() {

        Tienda tienda;
        Tienda tiendaDesdeSesion = (Tienda) session.getAttribute("tienda");

        if (tiendaDesdeSesion == null) {
            Tienda nuevaTienda = new Tienda();
            session.setAttribute("tienda", nuevaTienda);
            tienda = nuevaTienda;
        } else {
            tienda = tiendaDesdeSesion;
        }

        Producto p = tienda.getProductoById(id);

        if (p != null) {
            p.leerValoraciones();
            this.request.setAttribute("producto", p);
            return true;
        } else {
            return false;
        }

    }

}
