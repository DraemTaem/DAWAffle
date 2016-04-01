package controlador.PaqueteHelperPrincipal;

import javax.servlet.http.HttpServletRequest;
import modelo.Tienda;
import modelo.TiendaAuxiliarArchivo;

public class HelperMostrarPrincipal implements controlador.Helper {

    HttpServletRequest request;

    public HelperMostrarPrincipal(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void ejecutar() {
        /*La tienda ahora está disponible en el requestScope*/
        request.setAttribute("tienda", new TiendaAuxiliarArchivo());
    }

}
