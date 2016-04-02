package controlador.PaqueteHelperUsuarios;

import javax.servlet.http.HttpServletRequest;
import modelo.VOUsuario;    //TODO CAMBIAR RUTA!
import modelo.pckUsuarios.GestorUsuarios;
import modelo.pckUsuarios.Usuario;

public class HelperValidarUsuario implements controlador.Helper {

    private String alias;
    private String contrasena;
    private HttpServletRequest peticion;

    public HelperValidarUsuario(String alias, String contrasena, HttpServletRequest peticion) {
        this.alias = alias;
        this.contrasena = contrasena;
        this.peticion = peticion;
    }

    @Override
    public boolean ejecutar() {
        VOUsuario user = GestorUsuarios.validarUserPass(alias, contrasena);

        if (user != null) {
            peticion.setAttribute("usuario", user);
            return true;
        }
        return false;
    }

}
