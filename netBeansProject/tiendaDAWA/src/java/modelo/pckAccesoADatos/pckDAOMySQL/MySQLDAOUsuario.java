package modelo.pckAccesoADatos.pckDAOMySQL;

import modelo.pckAccesoADatos.pckDAOInterfaz.DAOUsuario;
import modelo.pckAccesoADatos.pckVO.VOColeccionUsuarios;
import modelo.pckAccesoADatos.pckVO.VOUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MySQLDAOUsuario implements DAOUsuario {

    @Override
    public VOUsuario getUsuarioByID(int id) {

        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt = null;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE id = ?;";


            pstmt = con.prepareStatement(sqlSelect);
            //pstmt.setString(1, "id");
            pstmt.setInt(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                VOUsuario usuario = new VOUsuario(id, res.getString("nombre"), res.getString("email"), res.getInt("categoria"), res.getString("direccion"), res.getBoolean("administrador"), res.getString("alias"), res.getString("contrasena"), res.getBoolean("registrado"));
                System.out.println(id);
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOUsuario getUsuarioByNombre(String nombre) {

        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt = null;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE nombre = ?;";

            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, nombre);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                VOUsuario usuario = new VOUsuario(res.getInt("id"), nombre, res.getString("email"), res.getInt("categoria"), res.getString("direccion"), res.getBoolean("administrador"), res.getString("alias"), res.getString("contrasena"), res.getBoolean("registrado"));
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOUsuario getUsuarioByEmail(String email) {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt = null;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE email = ?;";


            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, email);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                VOUsuario usuario = new VOUsuario(res.getInt("id"), res.getString("nombre"), email, res.getInt("categoria"), res.getString("direccion"), res.getBoolean("administrador"), res.getString("alias"), res.getString("contrasena"), res.getBoolean("registrado"));
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOUsuario getUsuarioByAlias(String alias) {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt = null;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE alias = ?;";


            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, alias);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                VOUsuario usuario = new VOUsuario(res.getInt("id"), res.getString("nombre"), res.getString("email"), res.getInt("categoria"), res.getString("direccion"), res.getBoolean("administrador"), alias, res.getString("contrasena"), res.getBoolean("registrado"));
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOColeccionUsuarios getUsuarios() {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt = null;

            String sqlSelect =
                    "SELECT * FROM usuarios;";


            pstmt = con.prepareStatement(sqlSelect);


            ResultSet res = pstmt.executeQuery();

            ArrayList<VOUsuario> resultado = new ArrayList<>();

            while (res.next()) {
                VOUsuario usuario = new VOUsuario(res.getInt("id"), res.getString("nombre"), res.getString("email"), res.getInt("categoria"), res.getString("direccion"), res.getBoolean("administrador"), res.getString("alias"), res.getString("contrasena"), res.getBoolean("registrado"));
                resultado.add(usuario);
            }

            return new VOColeccionUsuarios(resultado);

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOUsuario validarUsuario(String alias, String contrasena) {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE alias = ? AND contrasena = ?;";


            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, alias);
            pstmt.setString(2, contrasena);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                VOUsuario usuario = new VOUsuario(res.getInt("id"), res.getString("nombre"), res.getString("email"), res.getInt("categoria"), res.getString("direccion"), res.getBoolean("administrador"), alias, contrasena, res.getBoolean("registrado"));
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registrarUsuario(VOUsuario usuario) {

        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {


            PreparedStatement pstmt, pstmt2;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE alias = ? OR email = ?;";

            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, usuario.getAlias());
            pstmt.setString(2, usuario.getEmail());


            String sqlInsert =
                    "INSERT INTO usuarios" +
                            "(nombre, email, direccion, administrador, alias, contrasena, registrado)" +
                            " VALUES ( ?, ?, ?, ?, ?, ?, ?)";

            pstmt2 = con.prepareStatement(sqlInsert);
            pstmt2.setString(1, usuario.getNombre());
            pstmt2.setString(2, usuario.getEmail());
            if (usuario.getDireccion() != null)
                pstmt2.setString(3, usuario.getDireccion());
            else
                pstmt2.setString(3, "");
            pstmt2.setBoolean(4, false);
            pstmt2.setString(5, usuario.getAlias());
            pstmt2.setString(6, usuario.getContrasena());
            pstmt2.setBoolean(7, true);

            con.setAutoCommit(false);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                con.rollback();
                return false;
            } else {
                pstmt2.executeUpdate();
                con.commit();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } catch (NullPointerException e) {
            System.out.println("Faltan argumentos");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean registrarUsuario(String nombre, String email, String direccion, String alias, String contrasena) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {


            PreparedStatement pstmt, pstmt2;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE alias = ? OR email = ?;";

            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, alias);
            pstmt.setString(2, email);


            String sqlInsert =
                    "INSERT INTO usuarios" +
                            "(nombre, email, direccion, administrador, alias, contrasena, registrado)" +
                            " VALUES ( ?, ?, ?, ?, ?, ?, ?)";

            pstmt2 = con.prepareStatement(sqlInsert);
            pstmt2.setString(1, nombre);
            pstmt2.setString(2, email);
            if (direccion != null)
                pstmt2.setString(3, direccion);
            else
                pstmt2.setString(3, "");
            pstmt2.setBoolean(4, false);
            pstmt2.setString(5, alias);
            pstmt2.setString(6, contrasena);
            pstmt2.setBoolean(7, true);

            con.setAutoCommit(false);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                con.rollback();
                return false;
            } else {
                pstmt2.executeUpdate();
                con.commit();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } catch (NullPointerException e) {
            System.out.println("Faltan argumentos");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public int checkCategoria(VOUsuario usuario) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {

            PreparedStatement pstmt, pstmt2;

            float gasto = 0;


            String sqlSelect =
                    "SELECT precio FROM pedidos " +
                            "WHERE idUsuario = ?;";


            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setInt(1, usuario.getId());

            String sqlUpdate =
                    "UPDATE usuarios" +
                            "SET categoria = 1" +
                            "WHERE id = ?";
            pstmt2 = con.prepareStatement(sqlUpdate);
            pstmt2.setInt(1, usuario.getId());


            con.setAutoCommit(false);

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                gasto += res.getFloat("precio");
            }

            if (gasto >= 100){
                pstmt2.executeUpdate();
            }

            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateUsuario(VOUsuario usuario) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {


            PreparedStatement pstmt, pstmt2;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE id = ?;";

            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setInt(1, usuario.getId());


            String sqlUpdate =
                    "UPDATE usuarios " +
                            "SET nombre = ?, " +
                            "email = ?, " +
                            "direccion = ?, " +
                            "alias = ?, " +
                            "contrasena = ? " +
                            "WHERE id = ?;";

            pstmt2 = con.prepareStatement(sqlUpdate);
            pstmt2.setString(1, usuario.getNombre());
            pstmt2.setString(2, usuario.getEmail());
            if (usuario.getDireccion() != null)
                pstmt2.setString(3, usuario.getDireccion());
            else
                pstmt2.setString(3, "");

            pstmt2.setString(4, usuario.getAlias());
            pstmt2.setString(5, usuario.getContrasena());
            pstmt2.setInt(6, usuario.getId());


            con.setAutoCommit(false);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                pstmt2.executeUpdate();
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } catch (NullPointerException e) {
            System.out.println("Faltan argumentos");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean bloquearUsuario(VOUsuario usuario) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {


            PreparedStatement pstmt, pstmt2;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE id = ?;";

            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setInt(1, usuario.getId());


            String sqlUpdate =
                    "UPDATE usuarios " +
                            "SET registrado = false, " +
                            "WHERE id = ?;";

            pstmt2 = con.prepareStatement(sqlUpdate);
            pstmt2.setInt(1, usuario.getId());


            con.setAutoCommit(false);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                pstmt2.executeUpdate();
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } catch (NullPointerException e) {
            System.out.println("Faltan argumentos");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(VOUsuario usuario) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {


            PreparedStatement pstmt, pstmt2;

            String sqlSelect =
                    "SELECT * FROM usuarios "
                            + " WHERE id = ?;";

            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setInt(1, usuario.getId());


            String sqlUpdate =
                    "DELETE FROM usuarios " +
                            "WHERE id = ?;";

            pstmt2 = con.prepareStatement(sqlUpdate);
            pstmt2.setInt(1, usuario.getId());


            con.setAutoCommit(false);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                pstmt2.executeUpdate();
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } catch (NullPointerException e) {
            System.out.println("Faltan argumentos");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }


}
