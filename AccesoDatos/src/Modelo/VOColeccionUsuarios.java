package Modelo;

import java.util.ArrayList;

public class VOColeccionUsuarios {
    private ArrayList<VOUsuario> usuarios;

    public VOColeccionUsuarios(ArrayList<VOUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<VOUsuario> getUsuarios() {
        return usuarios;
    }
}
