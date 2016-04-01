package controlador.PaqueteHelperProductos;

public class HelperModificarCD implements controlador.Helper {
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private String autor;
    private String pais;
    private Integer stock;
    private Float precio;
    private String url;

    public HelperModificarCD(Integer id, String nombre, String descripcion, String autor, String pais, Integer stock, Float precio, String url) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor = autor;
        this.pais = pais;
        this.stock = stock;
        this.precio = precio;
        this.url = url;
    }
    

    @Override
    public void ejecutar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
