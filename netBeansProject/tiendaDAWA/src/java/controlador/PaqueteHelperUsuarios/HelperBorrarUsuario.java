package controlador.PaqueteHelperUsuarios;

import modelo.pckUsuarios.GestorUsuarios;

public class HelperBorrarUsuario implements controlador.Helper {

    private Integer id;

    public HelperBorrarUsuario(Integer id) {
        this.id = id;
    }

    @Override
    public boolean ejecutar() {
        
        return GestorUsuarios.borrarUsuario(id); 

    }

}
