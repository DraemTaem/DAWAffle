package controlador.PaqueteHelperUsuarios;

public class HelperRegistrarUsuario implements controlador.Helper {

    private String nombre;
    private String alias;
    private String contrasena;
    private String email;
    private String direccion;

    public HelperRegistrarUsuario(String nombre, String alias, String contrasena, String email, String direccion) {
        this.nombre = nombre;
        this.alias = alias;
        this.contrasena = contrasena;
        this.email = email;
        this.direccion = direccion;
    }
    
    @Override
    public void ejecutar() {
        
    }
    
    
    
}
