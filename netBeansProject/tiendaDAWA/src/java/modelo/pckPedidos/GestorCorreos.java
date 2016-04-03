package modelo.pckPedidos;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author gladi
 */
public class GestorCorreos {
    
    private String host;
    private String from;
    private String pass;

    public GestorCorreos() {
        
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public void enviarCorreoGmail(String to, String subject ,String content){
    
        try {
            
            
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml");
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(input));
            XPath xpath = XPathFactory.newInstance().newXPath();
            String login_aux = (String) xpath.compile("//config//mail//login").evaluate(document, XPathConstants.STRING);
            String host_aux = (String) xpath.compile("//config//mail//host").evaluate(document, XPathConstants.STRING);
            String password_aux = (String) xpath.compile("//config//mail//password").evaluate(document, XPathConstants.STRING);
            
            this.setFrom(login_aux);
            
            this.setHost(host_aux);
            
            this.setPass(password_aux);
            
            Properties props = System.getProperties();
            
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            
            Session session = Session.getDefaultInstance(props);
            
            try {
                //Creamos el objeto mensaje default
                MimeMessage message = new MimeMessage(session);
                
                //Damos la dirección desde la que enviaremos el correo
                message.setFrom(new InternetAddress(from));
                
                //Damos el remitente
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                
                //Completamos el asunto del email
                message.setSubject(subject);
                
                //Introducimos el mensaje con HTML
                message.setContent(content, "text/html");
                
                //Send message
                Transport transport = session.getTransport("smtp");
                transport.connect(host, from, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                
                System.out.println("Sent message successfully....");
                
            } catch (AddressException ae) {
                ae.printStackTrace();
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(GestorCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GestorCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GestorCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
}