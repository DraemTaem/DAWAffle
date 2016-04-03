package modelo.pckAccesoADatos.pckDAOInterfaz;


import modelo.pckAccesoADatos.pckDAOMySQL.MySQLFactoriaDAO;

public abstract class FactoriaDAO {

    public static FactoriaDAO newFactoria(){
        //TODO
        return new MySQLFactoriaDAO();
    }

    public abstract DAOUsuario crearDAOUsuario();
    public abstract DAOProducto crearDAOProducto();
    public abstract DAOPedido crearDAOPedido();








}
