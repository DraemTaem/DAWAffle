package modelo.pckPedidos;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    
        //TODO leer desde archivo host from past
        
        this.setFrom("pedidostiendadawa");
        
        this.setHost("smtp.gmail.com");
        
        this.setPass("reversal");
        
        Properties props = System.getProperties();
        host = "smtp.gmail.com";
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
    
    }
    
    
}