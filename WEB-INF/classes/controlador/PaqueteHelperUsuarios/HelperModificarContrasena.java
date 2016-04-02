package controlador.PaqueteHelperUsuarios;

import modelo.VOUsuario;    //TODO CAMBIAR RUTA!
import modelo.pckUsuarios.GestorUsuarios;

public class HelperModificarContrasena implements controlador.Helper {

    private Integer id;
    private String nuevaContrasena;

    public HelperModificarContrasena(Integer id, String nuevaContrasena) {
        this.id = id;
        this.nuevaContrasena = nuevaContrasena;
    }

    @Override
    public boolean ejecutar() {
        //TODO 
        VOUsuario user = GestorUsuarios.modificarContrasena(id, nuevaContrasena);
        if (user == null) {

            return false;
        }    // Devuelve NULL si la contraseña no se ha podido cambiar, si no devuelve un VO

        // Si es necesario, podemos meter el VO en request o sesión
        return true;
    }

}
