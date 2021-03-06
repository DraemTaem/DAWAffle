package controlador.PaqueteHelperProductos;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.pckAccesoADatos.pckVO.VOColeccionProductos;
import modelo.pckProductos.CD;
import modelo.pckProductos.Tienda;

public class HelperBusquedaCD implements controlador.Helper {

    private Float precioMaximo;
    private String titulo;
    private String autor;
    private String ano;
    private HttpServletRequest request;
    private HttpSession sesion;

    public HelperBusquedaCD(HttpSession sesion, HttpServletRequest request, Float precioMaximo, String titulo, String autor, String ano) {
        this.precioMaximo = precioMaximo;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.request = request;
        this.sesion = sesion;
    }

    @Override
    public boolean ejecutar() {

        Tienda tienda;
        Tienda tiendaDesdeSesion = (Tienda) sesion.getAttribute("tienda");

        if (tiendaDesdeSesion == null) {
            Tienda nuevaTienda = new Tienda();
            sesion.setAttribute("tienda", nuevaTienda);
            tienda = nuevaTienda;
        } else {
            tienda = tiendaDesdeSesion;
        }

        int anoi;

        if (ano != null && !ano.equals("")) {
            anoi = Integer.parseInt(ano);
        } else {
            anoi = -1;
        }

        ArrayList<CD> alc = tienda.busquedaLocal(precioMaximo, titulo, autor, anoi);

        if (alc != null) {
            request.setAttribute("resultadoBusqueda", alc);
            return true;
        } else {
            return false;
        }
    }

}
