package MySQL;

import AccesoDatos.DAOProducto;
import AccesoDatos.DAOUsuario;
import AccesoDatos.FactoriaDAO;


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
