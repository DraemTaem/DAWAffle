package controlador.PaqueteHelperUsuarios;

import javax.servlet.http.HttpSession;
import modelo.VOUsuario;    //TODO cambiar RUTA!
import modelo.pckUsuarios.Usuario;

public class HelperRegistrarUsuario implements controlador.Helper {

    private String nombre;
    private String alias;
    private String contrasena;
    private String email;
    private String direccion;
    private HttpSession sesion;

    public HelperRegistrarUsuario(String nombre, String alias, String contrasena, String email, String direccion, HttpSession sesion) {
        this.nombre = nombre;
        this.alias = alias;
        this.contrasena = contrasena;
        this.email = email;
        this.direccion = direccion;
        this.sesion = sesion;
    }

    @Override
    public boolean ejecutar() {
        Usuario u = new Usuario(nombre, contrasena, email, alias, direccion);
        VOUsuario user = u.RegistrarUsuario();
       
        if (user != null) {
            sesion.setAttribute("usuario", u);   // Lo incrusta en sesión si se insertó con éxito
            return true;
        } else {
            return false;
        }

    }

}
