package modelo.pckAccesoADatos.pckDAOInterfaz;


import modelo.pckAccesoADatos.pckVO.VOCd;
import modelo.pckAccesoADatos.pckVO.VOColeccionProductos;
import modelo.pckAccesoADatos.pckVO.VOProducto;
import modelo.pckAccesoADatos.pckVO.VOUsuario;
import modelo.pckAccesoADatos.pckVO.VOValoracion;

public interface DAOProducto {

    VOColeccionProductos getProductos();
    VOProducto getDetallesProducto(int id);

    /*Solo completar los parametros necesarios, si no se requiere
    * algun parametro se tendra que pasar un -1 en precio y año
    * y un string vacio en titulo y autor*/
    VOColeccionProductos getProductos(float precioMaximo, String nombre, String autor, int ano);
    VOColeccionProductos getProductosByPrecioMaximo(float precioMaximo);
    VOColeccionProductos getProductosByNombre(String nombre);
    VOColeccionProductos getProductosByAutor(String autor);
    VOColeccionProductos getProductosByAno(int ano);

    boolean registrarProducto(VOProducto producto);
    boolean registrarProducto(String nombre, String descripcion, float precio, String imagen, String tipo);
    boolean registrarCD(VOCd producto);

    /*Devuelve -1 si no existe el producto o is hay un error*/
    int getStockByID(VOProducto producto);
    boolean anadirUnidades(VOProducto producto, int unidades);

    boolean anadirValoracion(VOValoracion valoracion);
    
    boolean checkCompra(VOUsuario usuario, VOProducto producto);




}
