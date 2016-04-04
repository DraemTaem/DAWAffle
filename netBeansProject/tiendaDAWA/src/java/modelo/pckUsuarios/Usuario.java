package modelo.pckUsuarios;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.pckAccesoADatos.pckDAOInterfaz.DAOUsuario;
import modelo.pckAccesoADatos.pckDAOInterfaz.FactoriaDAO;
import modelo.pckPedidos.Carrito;
import modelo.pckAccesoADatos.pckVO.VOUsuario;

public class Usuario {

    private Integer id;
    private String nombre;
    private String contrasena;
    private String correoElectronico;
    private Carrito carrito;
    private Integer categoria;  // categoria 0 indica que es normal, 1 indica VIP
    private boolean isAdmin; // isAdmin 0 indica que es un usuario comun, 1 indica que es administrador
    private String alias;
    private String direccion;

    public Usuario(Integer id, String nombre, String contrasena, String correoElectronico, Integer categoria, boolean isAdmin, String alias, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.carrito = new Carrito();
        this.categoria = categoria;
        this.isAdmin = isAdmin;
        this.alias = alias;
        this.direccion = direccion;
    }

    
    public Usuario(String nombre, String contrasena, String correoElectronico, Integer categoria, boolean isAdmin, String alias, String direccion) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.carrito = new Carrito();
        this.categoria = categoria;
        this.isAdmin = isAdmin;
        this.alias = alias;
        this.direccion = direccion;
    }

    public Usuario(String nombre, String contrasena, String correoElectronico, Integer categoria, boolean isAdmin) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.carrito = new Carrito();
        this.categoria = categoria;
        this.isAdmin = isAdmin;
    }

    public Usuario(String nombre, String contrasena, String correoElectronico, String alias, String direccion) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.carrito = new Carrito();
        this.categoria = 0;
        this.isAdmin = false;
        this.alias = alias;
        this.direccion = direccion;
    }

    public Usuario() {//Crea el usuario con un carrito vacio, categoria normal (=0) y de tipo normal (no admin)
        this.carrito = new Carrito();
        categoria = 0;
        isAdmin = false;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean checkCategoria() {

        if (this.getCategoria() == 1) {
            return true;
        }

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOUsuario daoUsuario = factoria.crearDAOUsuario();

        VOUsuario vou = daoUsuario.getUsuarioByAlias(this.alias);

        int res = daoUsuario.checkCategoria(vou);

        if (res == -1) {
            return false;
        } else if (res == 0 || res == 1) {
            this.setCategoria(res);
            return true;
        }

        return false;

    }

    
    public boolean RegistrarUsuario() {
        VOUsuario user = null;

        user = new VOUsuario(nombre, correoElectronico, direccion, alias, contrasena);

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOUsuario daoUsuario = factoria.crearDAOUsuario();

        return daoUsuario.registrarUsuario(user);

    }

}
