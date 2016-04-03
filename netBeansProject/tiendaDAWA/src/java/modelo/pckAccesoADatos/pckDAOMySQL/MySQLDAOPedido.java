package modelo.pckAccesoADatos.pckDAOMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.pckAccesoADatos.pckDAOInterfaz.DAOPedido;
import modelo.pckAccesoADatos.pckVO.VOColeccionPedidos;
import modelo.pckAccesoADatos.pckVO.VOLineaPedido;
import modelo.pckAccesoADatos.pckVO.VOPedido;
import modelo.pckAccesoADatos.pckVO.VOUsuario;

public class MySQLDAOPedido implements DAOPedido {

    @Override
    public boolean insertarPedido(VOPedido pedido) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {

            PreparedStatement pstmt, pstmt2, pstmt3;

            String sqlSelectCategoria
                    = "SELECT categoria FROM usuarios "
                    + "WHERE id = ?;";

            pstmt = con.prepareStatement(sqlSelectCategoria);
            pstmt.setInt(1, pedido.getIdUsuario());

            String sqlInsertPedido
                    = "INSERT INTO pedidos "
                    + "(idUsuario, categoriaUsuario, precio) "
                    + "VALUES (?, ?, ?);";

            pstmt2 = con.prepareStatement(sqlInsertPedido);
            pstmt2.setInt(1, pedido.getId());
            pstmt2.setFloat(3, pedido.getPrecio());

            String sqlInsertLineaPedido
                    = "INSERT INTO lineaspedido "
                    + "( idPedido, idProducto, cantidad) "
                    + "VALUES ( ?, ?, ?);";

            pstmt3 = con.prepareStatement(sqlInsertLineaPedido);

            con.setAutoCommit(false);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                pstmt2.setInt(2, res.getInt("categoria"));
                pstmt2.executeUpdate();
                ResultSet generatedKeys = pstmt2.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pstmt3.setInt(1, generatedKeys.getInt("id"));
                } else {
                    return false;
                }

                for (VOLineaPedido linea : pedido.getLineas()) {
                    pstmt2.setInt(2, linea.getIdProducto());
                    pstmt2.setInt(3, linea.getCantidad());
                    pstmt2.executeUpdate();
                }
                con.commit();
                return true;

            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                return false;
            }
            System.out.println("Error en la consulta");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public VOColeccionPedidos getPedidosByUsuario(VOUsuario usuario) {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt, pstmt2;

            String sqlPedidos
                    = "SELECT * FROM pedidos "
                    + "WHERE idUsuario = ?;";

            pstmt = con.prepareStatement(sqlPedidos);
            pstmt.setInt(1, usuario.getId());

            String sqlLineasPedido
                    = "SELECT * FROM lineaspedido "
                    + "WHERE idPedido = ?;";

            pstmt2 = con.prepareStatement(sqlPedidos);

            ResultSet res = pstmt.executeQuery();
            ArrayList<VOPedido> coleccion = new ArrayList<>();
            ArrayList<VOLineaPedido> lineas = new ArrayList<>();
            VOLineaPedido linea;
            VOPedido pedido;
            
            while (res.next()) {
                pedido = new VOPedido(res.getInt("id"), res.getInt("idUsuario"), res.getInt("categoriaUsuario"), res.getFloat("precio"));
                pstmt2.setInt(1, pedido.getId());
                ResultSet res2 = pstmt2.executeQuery();
                while (res2.next()) {
                    linea = new VOLineaPedido(pedido.getId(), res2.getInt("idProducto"), res2.getInt("cantidad"));
                    lineas.add(linea);
                }
                coleccion.add(pedido);
            }
            
            return new VOColeccionPedidos(coleccion);

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
            return null;
        }
    }

}
