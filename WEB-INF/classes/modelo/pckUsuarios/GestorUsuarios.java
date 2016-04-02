package modelo.pckUsuarios;

public class GestorUsuarios {

    public static boolean validarUserPass(String nombre, String contrasena) {   // Puede ser static?

        if (nombre != null && nombre.length() > 0 && contrasena != null && contrasena.length() > 0) {
            //TODO
            //COMPROBAR en BD si existe un usuario con el nombre y contrasena dados
            return true;
        }

        return false;

    }

}
