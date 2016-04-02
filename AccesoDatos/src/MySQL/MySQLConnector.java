package MySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    public static String HOST = "localhost";
    public static String DATABASE = "dbtienda";
    public static String USERNAME = "root";
    public static String PASSWORD = "root";

    public Connection getConnection(){
        String url = "";
        try {
             url = "jdbc:mysql://" + HOST + "/" + DATABASE;
            Connection con = DriverManager.getConnection(url , this.USERNAME, this.PASSWORD);
            return con;
        } catch (SQLException e) {
            System.out.println("Conexion NO establecida con " + url);
            e.printStackTrace();
        }
        return null;
    }
}
