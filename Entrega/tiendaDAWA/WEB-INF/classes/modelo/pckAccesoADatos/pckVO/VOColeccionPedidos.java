
package modelo.pckAccesoADatos.pckVO;

import java.util.ArrayList;


public class VOColeccionPedidos {
    private ArrayList<VOPedido> pedidos;

    public VOColeccionPedidos(ArrayList<VOPedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<VOPedido> getPedidos() {
        return pedidos;
    }
    
}
