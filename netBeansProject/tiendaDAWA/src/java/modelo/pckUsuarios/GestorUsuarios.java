package modelo.pckUsuarios;

import java.util.ArrayList;
import modelo.pckAccesoADatos.pckDAOInterfaz.DAOUsuario;
import modelo.pckAccesoADatos.pckDAOInterfaz.FactoriaDAO;
import modelo.pckAccesoADatos.pckVO.VOColeccionUsuarios;
import modelo.pckAccesoADatos.pckVO.VOUsuario;

public class GestorUsuarios {

    public static VOUsuario validarUserPass(String nombre, String contrasena) {
        VOUsuario user = null;

        if (nombre != null && nombre.length() > 0 && contrasena != null && contrasena.length() > 0) {

            FactoriaDAO factoria = FactoriaDAO.newFactoria();

            DAOUsuario daoUsuario = factoria.crearDAOUsuario();

            user = daoUsuario.validarUsuario(nombre, contrasena);

            return user;
        }

        return null;

    }

    public static VOUsuario modificarContrasena(Integer id, String nuevaContrasena) {   // Puede ser static?

        VOUsuario user = null;

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOUsuario daoUsuario = factoria.crearDAOUsuario();

        user = daoUsuario.getUsuarioByID(id);

        if (user.getContrasena().equals(nuevaContrasena)) {
            return null;
        }

        user.setContrasena(nuevaContrasena);

        daoUsuario.updateUsuario(user);

        return user;

    }

    public static boolean borrarUsuario(Integer id) {

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOUsuario daoUsuario = factoria.crearDAOUsuario();

        VOUsuario voUsuario = new VOUsuario(id);

        return daoUsuario.eliminarUsuario(voUsuario);

    }

    public static VOUsuario visualizarCuenta(Integer id) {
        VOUsuario user = null;

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOUsuario daoUsuario = factoria.crearDAOUsuario();

        user = daoUsuario.getUsuarioByID(id);

        return user;
    }

    public static VOColeccionUsuarios visualizacionDeListaCuentas() {
        VOColeccionUsuarios usuarios = null;

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOUsuario daoUsuario = factoria.crearDAOUsuario();

        usuarios = daoUsuario.getUsuarios();
        
        return usuarios;
    }

}
