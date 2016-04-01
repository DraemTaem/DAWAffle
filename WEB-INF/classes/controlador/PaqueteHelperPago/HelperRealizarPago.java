package controlador.PaqueteHelperPago;

import javax.servlet.http.HttpSession;
import modelo.Pedido;
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

        //Anhadir datos de usuario a this.usuario si é necesario.
        
        //Aqui si necesitamos coller os datos da tienda actualizados para facer o pedido

        Pedido pedido=new Pedido(tienda, usuario);
        
        sesion.setAttribute("pedido", pedido);
        
        
    }

}
