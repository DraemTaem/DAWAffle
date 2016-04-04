package modelo.pckPedidos;

import java.util.ArrayList;
import java.util.*;
import modelo.pckAccesoADatos.pckDAOInterfaz.DAOPedido;
import modelo.pckAccesoADatos.pckDAOInterfaz.DAOProducto;
import modelo.pckAccesoADatos.pckDAOInterfaz.DAOUsuario;
import modelo.pckAccesoADatos.pckDAOInterfaz.FactoriaDAO;
import modelo.pckAccesoADatos.pckVO.VOLineaPedido;
import modelo.pckAccesoADatos.pckVO.VOPedido;
import modelo.pckProductos.Tienda;
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

        if (this.usuario.getCategoria() == 1) {
            this.precioTotal = this.precioTotal * 0.8f;
        }
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

        FactoriaDAO factoria = FactoriaDAO.newFactoria();

        DAOPedido daoPedido = factoria.crearDAOPedido();

        DAOUsuario daoUsuario = factoria.crearDAOUsuario();

        VOPedido vop = new VOPedido(-1, daoUsuario.getUsuarioByAlias(this.usuario.getAlias()).getId(), this.usuario.getCategoria(), precioTotal);

        ArrayList<VOLineaPedido> alv = new ArrayList<>();

        for (LineaPedido lp : this.lineasPedido) {
            VOLineaPedido vol = new VOLineaPedido(-1, lp.getProducto().getId(), lp.getCantidad());
            alv.add(vol);
        }

        vop.setLineas(alv);

        daoPedido.insertarPedido(vop);

        return true;

    }

    public void enviarCorreoUsuario() {

        String to = this.usuario.getCorreoElectronico();

        //Completamos el asunto del email
        String subject = "Pedido DAWA: ID [" + this.getId() + "]";

        String cate = "";

        if (this.getUsuario().getCategoria() == 0) {
            cate = "Básico";
        } else if (this.getUsuario().getCategoria() == 1) {
            cate = "VIP";
        } else {
            cate = "Error";
        }

        String contenido = "<h1>Saludos sr/sra " + this.usuario.getNombre() + "</h1>"
                + "<p>Nos ponemos en contacto con usted para realizar la confirmación del pago del pedido que acaba de realizar en nuestra http://www.tiendaDAWA.com un planeta de diversión.<p>"
                + "<h2>Información del comprador:</h2>"
                + "<p>Nombre: " + this.getUsuario().getNombre() + "</p>"
                + "<p>Alias: " + this.getUsuario().getAlias() + "</p>"
                + "<p>Dirección: " + this.getUsuario().getDireccion() + "</p>"
                + "<p>Correo Electrónico: " + this.getUsuario().getCorreoElectronico() + "</p>"
                + "<p>Categoría: " + cate + "</p>"
                + "<h2>Artículos comprados:</h2>"
                + "<ul>";

        for (LineaPedido lp : lineasPedido) {

            String contenidoAux = "<l1>ID: [" + lp.getProducto().getId() + "] ----- Nombre del producto: [" + lp.getProducto().getNombre() + "] ----- Cantidad: [" + lp.getCantidad() + "] ----- Precio unitario: [" + lp.getProducto().getPrecio() + " £] ----- Precio: [" + lp.getPrecioLinea() + " £]</li>";

            contenidoAux += "<br>";

            contenido += contenidoAux;

        }

        contenido += "</ul>";
        contenido += "<h3>Precio Total: [" + this.precioTotal + " £]</h3>";

        GestorCorreos gc = new GestorCorreos();

        gc.enviarCorreoGmail(to, subject, contenido);

    }

}
