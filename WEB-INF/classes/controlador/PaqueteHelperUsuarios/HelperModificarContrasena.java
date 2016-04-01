package controlador.PaqueteHelperUsuarios;

public class HelperModificarContrasena implements controlador.Helper{
    
    private Integer id;
    private String nuevaContrasena;

    public HelperModificarContrasena(Integer id, String nuevaContrasena) {
        this.id = id;
        this.nuevaContrasena = nuevaContrasena;
    }

    @Override
    public void ejecutar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
