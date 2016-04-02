package controlador.PaqueteHelperUsuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.pckUsuarios.GestorUsuarios;

public class HelperVisualizacionCuentaIndividual implements controlador.Helper{
    
    private Integer id;
    private HttpServletRequest peticion;

    public HelperVisualizacionCuentaIndividual(Integer id, HttpServletRequest peticion) {
        this.id = id;
        this.peticion = peticion;
    }

    @Override
    public boolean ejecutar() {
        peticion.setAttribute("usuarioSolicitado", GestorUsuarios.visualizarCuenta(id));     
    }
    
    
    
}
