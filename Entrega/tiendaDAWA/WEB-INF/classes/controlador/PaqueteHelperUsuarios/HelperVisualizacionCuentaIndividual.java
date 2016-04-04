package controlador.PaqueteHelperUsuarios;

import javax.servlet.http.HttpServletRequest;   //TODO CAMBIAR RUTA!
import modelo.pckAccesoADatos.pckVO.VOUsuario;
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

        VOUsuario vou = GestorUsuarios.visualizarCuenta(id);

        if (vou == null) {
            return false;
        } else {
            peticion.setAttribute("usuarioSolicitado", vou);
            return true;
        }

    }

}
