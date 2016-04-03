package modelo.pckAccesoADatos.pckDAOInterfaz;

import modelo.pckAccesoADatos.pckVO.VOColeccionPedidos;
import modelo.pckAccesoADatos.pckVO.VOPedido;
import modelo.pckAccesoADatos.pckVO.VOUsuario;


public interface DAOPedido {
    
    boolean insertarPedido(VOPedido pedido);
    VOColeccionPedidos getPedidosByUsuario(VOUsuario usuario);
    
            
}
