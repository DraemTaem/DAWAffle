package modelo.pckAccesoADatos.pckDAOInterfaz;


import modelo.pckAccesoADatos.pckVO.VOColeccionUsuarios;
import modelo.pckAccesoADatos.pckVO.VOUsuario;

public interface DAOUsuario {


    VOUsuario getUsuarioByID(int id);
    VOUsuario getUsuarioByNombre(String nombre);
    VOUsuario getUsuarioByEmail(String email);
    VOUsuario getUsuarioByAlias(String alias);

    VOColeccionUsuarios getUsuarios();

    VOUsuario validarUsuario(String alias, String contrasena);

    boolean registrarUsuario(VOUsuario usuario);
    boolean registrarUsuario(String nombre, String email, String direccion, String alias, String contrasena);

    /*Devuelve el int con la categoria, -1 si no existe el usuario
    * No se comprueba la categoria del usuario antes de sumar, por lo que
    * habra que llamar a esta funcion si la categoria del usuario en ese momento
    * no es vip
    * Siempre se le va a asignar la que corrasponda, asi que si se llama sin comprobar
    * tampoco pasa nada*/
    int checkCategoria(VOUsuario usuario);

    /*El input de este metodo debera ser el VO con los datos nuevos del usuario,
    * lmanteniendo el id al anterior. Si se desconoce el id, realizar antes el
    * getUsuarioBy correspondiente :D
    * El metodo devuelve true si se ha conseguido modificar el usuario y false
    * tanto si ha ocurrido un error como si no se pasan argumentos suficientes*/
    boolean updateUsuario(VOUsuario usuario);

    /*Marca el usuario como no registrado, pero sin eliminarlo de la base de datos
    * Requiere que se pase como argumento un VO con minimo el id para funcionar*/
    boolean bloquearUsuario(VOUsuario usuario);

    /*Elimina a un usuario de la base de datos
    * Requiere que se pase como argumento un VO con minimo el id para funcionar*/
    boolean eliminarUsuario(VOUsuario usuario);




}
