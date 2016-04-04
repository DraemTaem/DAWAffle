/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.PaqueteHelperProductos;

import javax.servlet.http.HttpSession;
import modelo.pckProductos.Tienda;

/**
 *
 * @author gladi
 */
public class HelperAnadirStock implements controlador.Helper {

    private int unidades;
    private int idProducto;
    private HttpSession sesion;

    public HelperAnadirStock(int unidades, int idProducto, HttpSession sesion) {
        this.unidades = unidades;
        this.idProducto = idProducto;
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
            tienda.leerProductos();
        }

        return tienda.anadirStock(idProducto, unidades);

    }

}
