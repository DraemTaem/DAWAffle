package modelo.pckAccesoADatos.pckDAOMySQL;

import modelo.pckAccesoADatos.pckDAOInterfaz.DAOProducto;
import modelo.pckAccesoADatos.pckVO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MySQLDAOProducto implements DAOProducto {

    @Override
    public VOColeccionProductos getProductos() {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt, pstmt2;

            String sqlSelect
                    = "SELECT * FROM productos;";

            pstmt = con.prepareStatement(sqlSelect);

            String sqlCD
                    = "SELECT * FROM cd "
                    + "WHERE id = ?;";
            pstmt2 = con.prepareStatement(sqlCD);

            ResultSet res = pstmt.executeQuery();

            ArrayList<VOProducto> coleccion = new ArrayList<>();
            String tipo = "";
            VOProducto prod;

            while (res.next()) {
                tipo = res.getString("tipo");

                switch (tipo) {
                    case "cd":
                        pstmt2.setInt(1, res.getInt("id"));
                        ResultSet res2 = pstmt2.executeQuery();
                        if (res2.next()) {
                            prod = new VOCd(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getFloat("precio"), res.getString("imagen"), res2.getString("autor"), res2.getString("pais"), res.getString("tipo"));
                            coleccion.add(prod);
                        }
                        break;

                    default:
                        prod = new VOProducto(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getFloat("precio"), res.getString("imagen"), res.getString("tipo"));
                        coleccion.add(prod);
                        break;
                }
            }

            return new VOColeccionProductos(coleccion);
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOProducto getDetallesProducto(int id) {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt, pstmt2, pstmt3, pstmt4, pstmt5;

            String sqlSelect
                    = "SELECT * FROM productos"
                    + "WHERE id = ?;";

            pstmt = con.prepareStatement(sqlSelect);

            pstmt.setInt(1, id);

            String sqlCD
                    = "SELECT * FROM cd "
                    + "WHERE id = ?;";
            pstmt2 = con.prepareStatement(sqlCD);

            String sqlStock
                    = "SELECT stock FROM inventario "
                    + "WHERE id = ?;";

            pstmt3 = con.prepareStatement(sqlStock);

            String sqlValoraciones
                    = "SELECT * FROM valoraciones "
                    + "WHERE idProducto = ?;";

            pstmt4 = con.prepareStatement(sqlValoraciones);

            String sqlUsuario
                    = "SELECT * FROM usuarios "
                    + "WHERE id = ?;";

            pstmt5 = con.prepareStatement(sqlValoraciones);

            ResultSet res = pstmt.executeQuery();

            String tipo = "";
            VOProducto prod;

            if (res.next()) {
                tipo = res.getString("tipo");

                switch (tipo) {
                    case "cd":
                        pstmt2.setInt(1, res.getInt("id"));
                        pstmt4.setInt(1, res.getInt("id"));
                        ResultSet res2 = pstmt2.executeQuery();
                        ResultSet res3 = pstmt3.executeQuery();
                        ResultSet res4 = pstmt4.executeQuery();
                        ResultSet res5;
                        if (res2.next() && res3.next()) {
                            VOValoracion val;
                            VOUsuario user;
                            ArrayList<VOValoracion> valoraciones = new ArrayList<>();
                            prod = new VOCd(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getFloat("precio"), res.getString("imagen"), res.getString("tipo"), res3.getInt("stock"), res2.getString("autor"), res2.getString("pais"));
                            while (res4.next()) {
                                pstmt5.setInt(1, res4.getInt("idUsuario"));
                                res5 = pstmt5.executeQuery();
                                user = new VOUsuario(res4.getInt("idUsuario"));
                                user.setNombre(res5.getString("nombre"));
                                user.setAlias(res5.getString("alias"));
                                user.setEmail(res5.getString("email"));
                                val = new VOValoracion(res.getInt("id"), user, res4.getString("comentario"), res4.getInt("nota"));
                                valoraciones.add(val);
                            }
                            prod.setValoraciones(new VOColeccionValoraciones(valoraciones));
                            return prod;
                        }
                        return null;

                    default:
                        res3 = pstmt3.executeQuery();
                        res4 = pstmt4.executeQuery();
                        if (res3.next()) {
                            VOValoracion val;
                            VOUsuario user;
                            ArrayList<VOValoracion> valoraciones = new ArrayList<>();
                            prod = new VOProducto(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getFloat("precio"), res.getString("imagen"), res.getString("tipo"), res3.getInt("stock"));
                            while (res4.next()) {
                                pstmt5.setInt(1, res4.getInt("idUsuario"));
                                res5 = pstmt5.executeQuery();
                                user = new VOUsuario(res4.getInt("idUsuario"));
                                user.setNombre(res5.getString("nombre"));
                                user.setAlias(res5.getString("alias"));
                                user.setEmail(res5.getString("email"));
                                val = new VOValoracion(res.getInt("id"), user, res4.getString("comentario"), res4.getInt("nota"));
                                valoraciones.add(val);
                            }
                            prod.setValoraciones(new VOColeccionValoraciones(valoraciones));
                            return prod;
                        }
                        return null;

                }
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOColeccionProductos getProductos(float precioMaximo, String nombre, String autor, int ano) {
        VOColeccionProductos byPrecio = getProductosByPrecioMaximo(precioMaximo);
        VOColeccionProductos byNombre = getProductosByNombre(nombre);
        VOColeccionProductos byAutor = getProductosByAutor(autor);
        //VOColeccionProductos byAno = getProductosByAno(ano);

        HashMap<Integer, VOProducto> resultado = new HashMap<>();

        for (VOProducto producto : byPrecio.getProductos()) {
            resultado.put(producto.getId(), producto);
        }
        for (VOProducto producto : byNombre.getProductos()) {
            resultado.put(producto.getId(), producto);
        }
        for (VOProducto producto : byAutor.getProductos()) {
            resultado.put(producto.getId(), producto);
        }
        /*for (VOProducto producto : byAno.getProductos()){
         resultado.put(producto.getId(), producto);
         }*/

        Iterator<Integer> i = resultado.keySet().iterator();
        ArrayList<VOProducto> coleccion = new ArrayList<>();
        Integer e;

        while (i.hasNext()) {
            e = i.next();
            coleccion.add(resultado.get(e));
        }
        return new VOColeccionProductos(coleccion);
    }

    @Override
    public VOColeccionProductos getProductosByPrecioMaximo(float precioMaximo) {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt, pstmt2;

            String sqlSelect
                    = "SELECT * FROM productos "
                    + "WHERE precio < ?;";

            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setFloat(1, precioMaximo);

            String sqlCD
                    = "SELECT * FROM cd "
                    + "WHERE id = ?;";
            pstmt2 = con.prepareStatement(sqlCD);

            ResultSet res = pstmt.executeQuery();

            ArrayList<VOProducto> coleccion = new ArrayList<>();
            String tipo = "";
            VOProducto prod;

            while (res.next()) {
                tipo = res.getString("tipo");

                switch (tipo) {
                    case "cd":
                        pstmt2.setInt(1, res.getInt("id"));
                        ResultSet res2 = pstmt2.executeQuery();
                        if (res2.next()) {
                            prod = new VOCd(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getFloat("precio"), res.getString("imagen"), res2.getString("autor"), res2.getString("pais"), res.getString("tipo"));
                            coleccion.add(prod);
                        }
                        break;

                    default:
                        prod = new VOProducto(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getFloat("precio"), res.getString("imagen"), res.getString("tipo"));
                        coleccion.add(prod);
                        break;
                }
            }

            return new VOColeccionProductos(coleccion);
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOColeccionProductos getProductosByNombre(String nombre) {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt, pstmt2;

            String sqlSelect
                    = "SELECT * FROM productos "
                    + "WHERE nombre = ?;";

            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, nombre);

            String sqlCD
                    = "SELECT * FROM cd "
                    + "WHERE id = ?;";
            pstmt2 = con.prepareStatement(sqlCD);

            ResultSet res = pstmt.executeQuery();

            ArrayList<VOProducto> coleccion = new ArrayList<>();
            String tipo = "";
            VOProducto prod;

            while (res.next()) {
                tipo = res.getString("tipo");

                switch (tipo) {
                    case "cd":
                        pstmt2.setInt(1, res.getInt("id"));
                        ResultSet res2 = pstmt2.executeQuery();
                        if (res2.next()) {
                            prod = new VOCd(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getFloat("precio"), res.getString("imagen"), res2.getString("autor"), res2.getString("pais"), res.getString("tipo"));
                            coleccion.add(prod);
                        }
                        break;

                    default:
                        prod = new VOProducto(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getFloat("precio"), res.getString("imagen"), res.getString("tipo"));
                        coleccion.add(prod);
                        break;
                }
            }

            return new VOColeccionProductos(coleccion);
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOColeccionProductos getProductosByAutor(String autor) {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt, pstmt2;

            String sqlSelect
                    = "SELECT * FROM productos "
                    + "WHERE id = ?;";

            pstmt = con.prepareStatement(sqlSelect);

            String sqlCD
                    = "SELECT * FROM cd "
                    + "WHERE autor = ?;";
            pstmt2 = con.prepareStatement(sqlCD);
            pstmt2.setString(1, autor);

            ResultSet res = pstmt2.executeQuery();

            ArrayList<VOProducto> coleccion = new ArrayList<>();
            VOProducto prod;

            while (res.next()) {

                pstmt.setInt(1, res.getInt("id"));
                ResultSet res2 = pstmt.executeQuery();
                if (res2.next()) {
                    prod = new VOCd(res2.getInt("id"), res2.getString("nombre"), res2.getString("descripcion"), res2.getFloat("precio"), res2.getString("imagen"), res.getString("autor"), res.getString("pais"), res2.getString("tipo"));
                    coleccion.add(prod);
                }

            }

            return new VOColeccionProductos(coleccion);
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VOColeccionProductos getProductosByAno(int ano) {
        //TODO
        return null;
    }

    @Override
    public boolean registrarProducto(VOProducto producto) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {

            PreparedStatement pstmt;

            String sqlInsert
                    = "INSERT INTO productos (nombre, descripcion, precio, imagen, tipo)"
                    + "VALUES ( ?, ?, ?, ?, ?);";

            pstmt = con.prepareStatement(sqlInsert);
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setFloat(3, producto.getPrecio());
            pstmt.setString(4, producto.getImagen());
            if (producto.getTipo() != null) {
                pstmt.setString(5, producto.getTipo());
            } else {
                pstmt.setString(5, "");
            }

            con.setAutoCommit(false);
            pstmt.executeUpdate();

            con.commit();
            return true;

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
    public boolean registrarProducto(String nombre, String descripcion, float precio, String imagen, String tipo) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {

            PreparedStatement pstmt, pstmt2;

            String sqlInsert
                    = "INSERT INTO productos (nombre, descripcion, precio, imagen, tipo)"
                    + "VALUES ( ?, ?, ?, ?, ?);";

            pstmt = con.prepareStatement(sqlInsert);
            pstmt.setString(1, nombre);
            pstmt.setString(2, descripcion);
            pstmt.setFloat(3, precio);
            pstmt.setString(4, imagen);
            if (tipo != null) {
                pstmt.setString(5, tipo);
            } else {
                pstmt.setString(5, "");
            }

            con.setAutoCommit(false);
            pstmt.executeUpdate();
            String sqlInsert2
                    = "INSERT INTO inventario (idProducto, cantidad) "
                    + "VALUES (?, 0);";

            pstmt2 = con.prepareStatement(sqlInsert2);

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                pstmt2.setInt(1, generatedKeys.getInt("id"));
            } else {
                con.rollback();
                return false;
            }
            
            pstmt2.executeUpdate();
            con.commit();
            return true;

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
    public boolean registrarCD(VOCd producto) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {

            PreparedStatement pstmt, pstmt2, pstmt3, pstmt4;

            String sqlInsert
                    = "INSERT INTO productos (nombre, descripcion, precio, imagen, tipo)"
                    + "VALUES ( ?, ?, ?, ?, ?);";

            pstmt = con.prepareStatement(sqlInsert);
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setFloat(3, producto.getPrecio());
            pstmt.setString(4, producto.getImagen());
            if (producto.getTipo() != null) {
                pstmt.setString(5, producto.getTipo());
            } else {
                pstmt.setString(5, "");
            }

            con.setAutoCommit(false);
            pstmt.executeUpdate();
            //select max(id) from productos;
            String sqlInsert2
                    = "INSERT INTO cd (idProducto, autor, pais)"
                    + "VALUES ( ?, ?, ?);";

            pstmt3 = con.prepareStatement(sqlInsert2);

            String sqlInsert3
                    = "INSERT INTO inventario (idProducto, cantidad) "
                    + "VALUES (?, 0);";

            pstmt4 = con.prepareStatement(sqlInsert3);

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                pstmt3.setInt(1, generatedKeys.getInt("id"));
                pstmt4.setInt(1, generatedKeys.getInt("id"));
            } else {
                con.rollback();
                return false;
            }

            pstmt3.setString(2, producto.getAutor());
            pstmt3.setString(3, producto.getPais());

            pstmt3.executeUpdate();
            pstmt4.executeUpdate();

            con.commit();
            return true;

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
    public int getStockByID(VOProducto producto) {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getConnection();
            PreparedStatement pstmt;

            String sqlStock
                    = "SELECT stock FROM inventario "
                    + "WHERE id = ?;";

            pstmt = con.prepareStatement(sqlStock);
            pstmt.setInt(1, producto.getId());

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                return res.getInt("stock");
            } else {
                return -1;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public boolean anadirUnidades(VOProducto producto, int unidades) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {

            PreparedStatement pstmt, pstmt2;

            String sqlStock
                    = "SELECT stock FROM inventario "
                    + "WHERE id = ?;";

            pstmt = con.prepareStatement(sqlStock);
            pstmt.setInt(1, producto.getId());

            String sqlUpdate
                    = "UPDATE inventario "
                    + "SET stock = ? "
                    + "WHERE id = ?;";

            pstmt2 = con.prepareStatement(sqlUpdate);

            con.setAutoCommit(false);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                int stock = res.getInt("stock");
                stock += unidades;

                if (stock >= 0) {
                    pstmt2.setInt(1, stock);
                } else {
                    pstmt2.setInt(1, 0);
                }
                pstmt2.setInt(2, producto.getId());

                pstmt2.executeUpdate();

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
            }
            System.out.println("Error en la consulta");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean anadirValoracion(VOValoracion valoracion) {
        MySQLConnector connector = new MySQLConnector();
        Connection con = connector.getConnection();
        try {

            PreparedStatement pstmt, pstmt2;

            String sqlStock
                    = "SELECT * FROM productos "
                    + "WHERE id = ?;";

            pstmt = con.prepareStatement(sqlStock);
            pstmt.setInt(1, valoracion.getIdProducto());

            String sqlInsert
                    = "INSERT INTO valoraciones "
                    + "( idProducto, idUsuario, comentario, nota) "
                    + "VALUES ( ?, ?, ?, ?);";

            pstmt2 = con.prepareStatement(sqlInsert);

            con.setAutoCommit(false);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                pstmt2.setInt(1, valoracion.getIdProducto());
                pstmt2.setInt(2, valoracion.getUsuario().getId());
                pstmt2.setString(3, valoracion.getComentario());
                pstmt2.setInt(4, valoracion.getNota());

                pstmt2.executeUpdate();

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
            }
            System.out.println("Error en la consulta");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkCompra(VOUsuario usuario, VOProducto producto) {
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

            while (res.next()) {
                pstmt2.setInt(1, res.getInt("id"));
                ResultSet res2 = pstmt2.executeQuery();
                while (res2.next()) {
                    if (res2.getInt("idProducto") == producto.getId()) {
                        return true;
                    }
                }
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
            return false;
        }
    }

}
