/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.PaqueteHelperPago;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Pedido;
import modelo.Usuario;

/**
 *
 * @author gladi
 */
public class HelperConfirmarPago implements controlador.Helper {
    
    private HttpSession sesion;
    private HttpServletRequest request;
    
    public HelperConfirmarPago(HttpSession sesion, HttpServletRequest request) {
        this.sesion = sesion;
        this.request = request;
    }
    
    @Override
    public void ejecutar() {
        
        Pedido pedido = (Pedido) sesion.getAttribute("pedido");
        //Por ahora no hacemos nada con el pedido a la hora de guardarlo.
        request.setAttribute("mensaje", "Se envia el correo a: " + pedido.getUsuario().getCorreoElectronico());

        //Borramos el pedido de la sesión y reseteamos el carrito al usuario
        sesion.removeAttribute("pedido");
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        usuario.setCarrito(new Carrito());
        
    }
    
}
