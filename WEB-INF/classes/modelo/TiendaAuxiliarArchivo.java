/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gladi
 */
public class TiendaAuxiliarArchivo extends Tienda {

    //Función auxiliar para esta implementación de la tienda
    public TiendaAuxiliarArchivo() {
        this.leerProductos(this.productosDisponibles, controlador.Controlador.path);
    }

    public TiendaAuxiliarArchivo(String path) {
        if (this.leerProductos(this.productosDisponibles, path) == false) {
            //Error
        }
    }

}
