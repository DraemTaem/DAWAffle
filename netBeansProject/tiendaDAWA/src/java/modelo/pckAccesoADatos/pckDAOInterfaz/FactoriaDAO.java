package modelo.pckAccesoADatos.pckDAOInterfaz;

import modelo.pckAccesoADatos.pckDAOMySQL.MySQLFactoriaDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class FactoriaDAO {

    public static FactoriaDAO newFactoria() {
        try {
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml");
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(input));
            XPath xpath = XPathFactory.newInstance().newXPath();
            String url = (String) xpath.compile("//config//jdbc//url").evaluate(document, XPathConstants.STRING);
            String[] cut = url.split(":");
            switch (cut[1]) {
                case "mysql":
                    return new MySQLFactoriaDAO();
            }

        } catch (IOException ex) {
            Logger.getLogger(FactoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract DAOUsuario crearDAOUsuario();

    public abstract DAOProducto crearDAOProducto();

    public abstract DAOPedido crearDAOPedido();

}
