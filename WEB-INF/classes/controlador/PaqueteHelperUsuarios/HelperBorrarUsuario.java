package controlador.PaqueteHelperUsuarios;

import modelo.pckUsuarios.GestorUsuarios;

public class HelperBorrarUsuario implements controlador.Helper {

    private Integer id;

    public HelperBorrarUsuario(Integer id) {
        this.id = id;
    }

    @Override
    public boolean ejecutar() {
        //TODO
        if (GestorUsuarios.borrarUsuario(id)) {
            // Se ha borrado correctamente el usuario
            return true;
        } else {
            // No se ha logrado borrar el usuario
            return false;
        }

    }

}
