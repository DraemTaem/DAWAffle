package modelo;

import java.util.ArrayList;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Pedido {

    private ArrayList<LineaPedido> lineasPedido;
    private Usuario usuario;
    private float precioTotal;

    private int id;
    private Date fecha;

    public Pedido(Tienda tienda, Usuario usuario) {

        this.usuario = usuario;
        Carrito carrito = usuario.getCarrito();
        /*Antes de crear el pedido actualizamos todos los datos
         del carrito para tener información correcta*/
        carrito.actualizar(tienda);

        this.lineasPedido = new ArrayList<>();

        ArrayList<LineaCarrito> lineasCarrito = carrito.getLineasCarrito();

        /*Para todas las lineas de carrito, instanciamos
         una linea de pedido y la anadimos a la lista*/
        for (LineaCarrito lc : lineasCarrito) {
            this.lineasPedido.add(new LineaPedido(lc));
        }

        this.precioTotal = carrito.getPrecioTotal();

    }

    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void enviarCorreoUsuario(String from, String pass) {

        String to = this.usuario.getCorreoElectronico();

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
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
            message.setSubject("Pedido DAWA: ID [" + this.getId() + "]");

            String contenido = "<h1>Saludos sr/sra " + this.usuario.getNombre() + "</h1>"
                    + "<p>Nos ponemos en contacto con uster para realizar la confirmación del pago del pedido que acaba de realizar en nuestra tiendaDAWA.com un planeta de diversión.<p>"
                    + "<h2>Información del comprador:</h2>"
                    //TODO insertar aquí los datos del usuario que estén disponibles
                    + "<h2>Artículos comprados:</h2>"
                    + "<ul>";

            for (LineaPedido lp : lineasPedido) {

                String contenidoAux = "<l1>ID: [" + lp.getProducto().getID() + "] ----- Nombre del producto: [" + lp.getProducto().getNombre() + "] ----- Cantidad: [" + lp.getCantidad() + "] ----- Precio unitario: [" + lp.getProducto().getPrecio() + "] ----- Precio: [" + lp.getPrecioLinea() + "]</li>";

                contenidoAux += "<br>";

                contenido += contenidoAux;

            }

            contenido += "</ul>";
            contenido += "<h3>Precio Total: [" + this.precioTotal + "]</h3>";

            //Introducimos el mensaje con HTML
            message.setContent(contenido, "text/html");

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
