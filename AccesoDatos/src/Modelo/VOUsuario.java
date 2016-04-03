package Modelo;


public class VOUsuario {

    private int id;
    private String nombre;
    private String email;
    private int categoria;
    private String direccion;
    private boolean administrador;
    private String alias;
    private String contrasena;
    private boolean registrado;


    public VOUsuario(int id) {
        this.id = id;
    }

    public VOUsuario(int id, String nombre, String email, int categoria, String direccion, boolean administrador, String alias, String contrasena, boolean registrado) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.categoria = categoria;
        this.direccion = direccion;
        this.administrador = administrador;
        this.alias = alias;
        this.contrasena = contrasena;
        this.registrado = registrado;
    }

    public VOUsuario(String nombre, String email, String direccion, String alias, String contrasena) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.alias = alias;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isRegistrado() {
        return registrado;
    }

    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }
}
