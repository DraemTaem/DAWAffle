package controlador.PaqueteHelperProductos;

public class HelperIntroducirValoracion implements controlador.Helper {
    
    private Integer idUsuario;
    private Integer idProducto;
    private Integer valoracion;
    private String comentario;

    public HelperIntroducirValoracion(Integer idUsuario, Integer idProducto, Integer valoracion, String comentario) {
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

    @Override
    public void ejecutar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
