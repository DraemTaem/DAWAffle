package modelo.pckAccesoADatos.pckDAOMySQL;

import modelo.pckAccesoADatos.pckDAOInterfaz.DAOProducto;
import modelo.pckAccesoADatos.pckDAOInterfaz.DAOUsuario;
import modelo.pckAccesoADatos.pckDAOInterfaz.FactoriaDAO;


public class MySQLFactoriaDAO extends FactoriaDAO {

    @Override
    public DAOUsuario crearDAOUsuario() {
        return new MySQLDAOUsuario();
    }

    @Override
    public DAOProducto crearDAOProducto() {
        return new MySQLDAOProducto();
    }
}
