package controlador.PaqueteHelperUsuarios;

import javax.servlet.http.HttpServletRequest;
import modelo.pckAccesoADatos.pckVO.VOColeccionUsuarios;
import modelo.pckUsuarios.GestorUsuarios;

public class HelperVisualizacionDeListaCuentas implements controlador.Helper {

    private HttpServletRequest peticion;

    public HelperVisualizacionDeListaCuentas(HttpServletRequest peticion) {
        this.peticion = peticion;
    }

    @Override
    public boolean ejecutar() {

        VOColeccionUsuarios coleccion;
        coleccion = GestorUsuarios.visualizacionDeListaCuentas();

        if (coleccion == null) {
            return false;
        } else {
            peticion.setAttribute("listaCuentas", coleccion);
            return true;
        }
    }

}
