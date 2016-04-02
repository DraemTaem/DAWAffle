package controlador.PaqueteHelperUsuarios;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import modelo.VOUsuario;    //TODO CAMBIAR RUTA!
import modelo.pckUsuarios.GestorUsuarios;

public class HelperVisualizacionDeListaCuentas implements controlador.Helper {

    private HttpServletRequest peticion;

    public HelperVisualizacionDeListaCuentas(HttpServletRequest peticion) {
        this.peticion = peticion;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<VOUsuario> coleccion = null;
        coleccion = GestorUsuarios.visualizacionDeListaCuentas();

        if (coleccion == null) {
            return false;
        } else {
            peticion.setAttribute("listaCuentas", coleccion);
            return true;
        }
    }

}
