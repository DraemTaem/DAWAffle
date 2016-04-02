package Main;


import AccesoDatos.DAOUsuario;
import AccesoDatos.FactoriaDAO;
import Modelo.VOUsuario;

public class Main {
    public static void main (String[] args){

        FactoriaDAO miFactoria = FactoriaDAO.newFactoria();

        DAOUsuario midao = miFactoria.crearDAOUsuario();





        VOUsuario mivo = new VOUsuario("Pablo", "pablotomico@hotmail.com", "Calle falsa 123", "ptomico", "qwertee");

        midao.registrarUsuario(mivo);

        mivo = midao.getUsuarioByEmail("pablotomico@hotmail.com");
        System.out.println("Nombre: " + mivo.getNombre());
        System.out.println("Registrado: " + mivo.isRegistrado());

        System.out.println("Categoria: " + midao.checkCategoria(mivo));




    }
}
