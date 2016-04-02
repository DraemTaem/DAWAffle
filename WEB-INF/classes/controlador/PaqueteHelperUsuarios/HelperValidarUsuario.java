package controlador.PaqueteHelperUsuarios;

import modelo.pckUsuarios.GestorUsuarios;

public class HelperValidarUsuario implements controlador.Helper {

    private String alias;
    private String contrasena;

    public HelperValidarUsuario(String alias, String contrasena) {
        this.alias = alias;
        this.contrasena = contrasena;
    }

    @Override
    public boolean ejecutar() {

        return GestorUsuarios.validarUserPass(alias, contrasena) == null;

    }

}
