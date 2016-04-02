package controlador.PaqueteHelperUsuarios;

import javax.servlet.http.HttpServletRequest;   //TODO CAMBIAR RUTA!
import modelo.VOUsuario;
import modelo.pckUsuarios.GestorUsuarios;

public class HelperVisualizacionCuentaIndividual implements controlador.Helper {

    private Integer id;
    private HttpServletRequest peticion;

    public HelperVisualizacionCuentaIndividual(Integer id, HttpServletRequest peticion) {
        this.id = id;
        this.peticion = peticion;
    }

    @Override
    public boolean ejecutar() {
        VOUsuario user = null;
        if (user != null) {
            peticion.setAttribute("usuarioSolicitado", GestorUsuarios.visualizarCuenta(id));
            return true;
        }
        return false;
    }

}
