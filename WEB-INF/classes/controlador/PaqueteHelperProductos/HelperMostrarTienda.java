package controlador.PaqueteHelperProductos;

import javax.servlet.http.HttpSession;
import modelo.pckProductos.Tienda;

public class HelperMostrarTienda implements controlador.Helper{

    private HttpSession sesion;

    public HelperMostrarTienda(HttpSession sesion) {
        this.sesion = sesion;
    }

    @Override
    public boolean ejecutar() {
        
        Tienda tienda;
        Tienda tiendaDesdeSesion= (Tienda) sesion.getAttribute("tienda");
        
        if(tiendaDesdeSesion==null){
            Tienda nuevaTienda= new Tienda();
            sesion.setAttribute("tienda", nuevaTienda);
            tienda=nuevaTienda;
        }else{
            tienda=tiendaDesdeSesion;
        }
        
        
        return true;
    }
    
    
}
