package modelo.pckAccesoADatos.pckDAOMySQL;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnector {


    public Connection getConnection() {
        String url = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/config.xml");
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(input));
            XPath xpath = XPathFactory.newInstance().newXPath();
            url = (String) xpath.compile("//config//jdbc//url").evaluate(document, XPathConstants.STRING);
            String driver = (String) xpath.compile("//config//jdbc//driver").evaluate(document, XPathConstants.STRING);
            String username = (String) xpath.compile("//config//jdbc//username").evaluate(document, XPathConstants.STRING);
            String password = (String) xpath.compile("//config//jdbc//password").evaluate(document, XPathConstants.STRING);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtienda" , "daw", "etse");
            return con;
        } catch (SQLException e) {
            System.out.println("Conexion NO establecida con " + url);
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MySQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MySQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
