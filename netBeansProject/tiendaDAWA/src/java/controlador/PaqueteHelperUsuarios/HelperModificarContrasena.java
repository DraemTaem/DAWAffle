package controlador.PaqueteHelperUsuarios;

import javax.servlet.http.HttpServletRequest;
import modelo.pckAccesoADatos.pckVO.VOUsuario;
import modelo.pckUsuarios.GestorUsuarios;

public class HelperModificarContrasena implements controlador.Helper {

    private Integer id;
    private String nuevaContrasena;
    private HttpServletRequest request;

    public HelperModificarContrasena(HttpServletRequest request, Integer id, String nuevaContrasena) {
        this.id = id;
        this.nuevaContrasena = nuevaContrasena;
        this.request = request;
    }

    @Override
    public boolean ejecutar() {
       
        VOUsuario user = GestorUsuarios.modificarContrasena(id, nuevaContrasena);
        if (user == null) {
            return false;
        }    // Devuelve NULL si la contraseña no se ha podido cambiar, si no devuelve un VO

        this.request.setAttribute("usuarioModificado", user);
        
        return true;
    }

}
