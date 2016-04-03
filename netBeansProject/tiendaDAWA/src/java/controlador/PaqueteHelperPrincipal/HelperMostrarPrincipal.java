package controlador.PaqueteHelperPrincipal;

import java.util.Calendar;
import javax.servlet.http.HttpSession;


public class HelperMostrarPrincipal implements controlador.Helper {

    private HttpSession session;

    public HelperMostrarPrincipal(HttpSession session) {
        this.session = session;
    }
    
    

    @Override
    public boolean ejecutar() {
        
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        this.session.setAttribute("anoActual", ano);
        
        return true;
    }

}
