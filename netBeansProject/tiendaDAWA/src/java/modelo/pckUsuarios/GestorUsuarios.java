package modelo.pckUsuarios;

import java.util.ArrayList;
import modelo.VOUsuario;    //TODO MODIFICAR A RUTA REAL !!

public class GestorUsuarios {

    public static VOUsuario validarUserPass(String nombre, String contrasena) {   // Puede ser static?
        VOUsuario user = null;

        if (nombre != null && nombre.length() > 0 && contrasena != null && contrasena.length() > 0) {
            //TODO
            //COMPROBAR en BD si existe un usuario con el nombre y contrasena dados
            return user;
        }

        return null;

    }

    public static VOUsuario modificarContrasena(Integer id, String nuevaContrasena) {   // Puede ser static?
        VOUsuario user = null;

        //TODO
        // Llamada a DAO para modificar el usuario que posee dicho id con nuevaContrasena
        return user;

    }

    public static boolean borrarUsuario(Integer id) {   // Puede ser static?
        //TODO
        // Llamada a DAO para eliminar el usario

        return true;

    }

    public static VOUsuario visualizarCuenta(Integer id) {
        VOUsuario user = null;
        //TODO

        // Llamada a DAO para traer el VOUsuario del id proporcionado
        return user;
    }

    public static ArrayList<VOUsuario> visualizacionDeListaCuentas() {   //TODO se utiliza colección de VO o sólo un array list?
        ArrayList<VOUsuario> usuarios = null;

        //TODO
        
        return usuarios; // Llamada a DAO para traer la colección de VOs
    }

}
