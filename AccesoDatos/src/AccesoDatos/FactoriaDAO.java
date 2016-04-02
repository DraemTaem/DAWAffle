package AccesoDatos;


import MySQL.MySQLFactoriaDAO;

public abstract class FactoriaDAO {

    public static FactoriaDAO newFactoria(){
        //TODO
        return new MySQLFactoriaDAO();
    }

    public abstract DAOUsuario crearDAOUsuario();
    public abstract DAOProducto crearDAOProducto();








}
