package controlador.PaqueteHelperPago;

import javax.servlet.http.HttpSession;
import modelo.Pedido;
import modelo.TiendaAuxiliarArchivo;
import modelo.Usuario;


public class HelperRealizarPago implements controlador.Helper {

    private Usuario usuario;
    private String nombre;
    private String email;
    private HttpSession sesion;

    public HelperRealizarPago(HttpSession sesion,Usuario usuario, String nombre, String email) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
        this.sesion=sesion;
    }

    public void ejecutar() {

        usuario.setNombre(nombre);
        usuario.setCorreoElectronico(email);
        
        
        Pedido pedido=new Pedido(new TiendaAuxiliarArchivo(),usuario);
        
        sesion.setAttribute("pedido", pedido);
        
        
    }

}
