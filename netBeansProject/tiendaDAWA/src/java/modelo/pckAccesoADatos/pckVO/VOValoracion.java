package modelo.pckAccesoADatos.pckVO;


public class VOValoracion {
    private int idProducto;
    private VOUsuario usuario;
    private String comentario;
    private int nota;


    public VOValoracion(VOProducto producto, VOUsuario usuario, String comentario, int nota) {
        this.idProducto = producto.getId();
        this.usuario = usuario;
        this.comentario = comentario;
        this.nota = nota;
    }

    public VOValoracion(int idProducto, VOUsuario usuario, String comentario, int nota) {
        this.idProducto = idProducto;
        this.usuario = usuario;
        this.comentario = comentario;
        this.nota = nota;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public VOUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(VOUsuario usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
