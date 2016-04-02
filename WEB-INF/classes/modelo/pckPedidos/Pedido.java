package modelo.pckProductos;

import java.util.ArrayList;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import modelo.pckUsuarios.Usuario;

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

    public boolean registrarPedido() {
        
        //TODO things con las cosas de TOMI
        return true;
        
    }

    public void enviarCorreoUsuario() {

        String to = this.usuario.getCorreoElectronico();

        //Completamos el asunto del email
        String subject = "Pedido DAWA: ID [" + this.getId() + "]";

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

        Modelo.GestorCorreos gc = new Modelo.GestorCorreos();

        gc.enviarCorreoGmail(to, subject, contenido);

    }

}
