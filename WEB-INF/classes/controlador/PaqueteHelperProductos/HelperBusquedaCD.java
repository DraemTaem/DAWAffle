package controlador.PaqueteHelperProductos;

import java.util.Date;

public class HelperBusquedaCD implements controlador.Helper {
    
    private Float precioMaximo;
    private String titulo;
    private String autor;
    private Date ano;

    public HelperBusquedaCD(Float precioMaximo, String titulo, String autor, Date ano) {
        this.precioMaximo = precioMaximo;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }
    

    @Override
    public void ejecutar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
