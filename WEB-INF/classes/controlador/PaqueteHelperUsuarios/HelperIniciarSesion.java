package controlador.PaqueteHelperUsuarios;

import javax.servlet.http.HttpSession;
import modelo.VOUsuario;    //TODO CAMBIAR RUTA!
import modelo.pckUsuarios.GestorUsuarios;
import modelo.pckUsuarios.Usuario;

public class HelperIniciarSesion implements controlador.Helper {

    private String alias;
    private String contrasena;
    private HttpSession sesion;

    public HelperIniciarSesion(String alias, String contrasena, HttpSession sesion) {
        this.alias = alias;
        this.contrasena = contrasena;
        this.sesion = sesion;
    }

    @Override
    public boolean ejecutar() {
        VOUsuario user = GestorUsuarios.validarUserPass(alias, contrasena);

        if (user != null) {
            Usuario u = new Usuario();  //TODO rellenar con los datos del VO
            sesion.setAttribute("usuario", u);   // Lo incrusta en sesión si se insertó con éxito
            return true;
        } else {
            return false;
        }

    }

}
