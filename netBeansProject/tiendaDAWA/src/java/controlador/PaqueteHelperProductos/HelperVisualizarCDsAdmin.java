package controlador.PaqueteHelperProductos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.pckAccesoADatos.pckVO.VOColeccionProductos;
import modelo.pckProductos.Tienda;

public class HelperVisualizarCDsAdmin implements controlador.Helper {

    private HttpServletRequest request;
    private HttpSession session;

    public HelperVisualizarCDsAdmin(HttpServletRequest request, HttpSession session) {
        this.request = request;
        this.session = session;
    }

    @Override
    public boolean ejecutar() {

        Tienda tienda = new Tienda();
        session.setAttribute("tienda", tienda);

        VOColeccionProductos voc = tienda.getProductosActualizados();

        if (voc != null) {
            this.request.setAttribute("listaProductosActualizada", voc);
            return true;
        } else {
            return false;
        }

    }

}
