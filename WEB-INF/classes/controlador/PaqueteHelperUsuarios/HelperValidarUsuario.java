package controlador.PaqueteHelperUsuarios;

public class HelperValidarUsuario implements controlador.Helper{
    
    private String alias;
    private String contrasena;

    public HelperValidarUsuario(String alias, String contrasena) {
        this.alias = alias;
        this.contrasena = contrasena;
    }
    
    @Override
    public void ejecutar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
