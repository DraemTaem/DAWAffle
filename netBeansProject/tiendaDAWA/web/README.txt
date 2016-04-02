Parámetros que puede recoger al Controlador:

- si getParameter(actualizar!=null) realizar el filtrado de los cds y volver a la página actualizada

- si ""				seleccionar	""	añadir producto al carrito e ¿(ir al carrito)?

- si ""				comentar	""	añadir comentario realizado a la BD y mostrar la página con el nuevo comentario

- si ""				eliminar	""	eliminar un producto del carrito y mostrar esa página sin el elemento eliminado

- si ""			seguirComprando ""	salir del carrito y volver al inicio para meter más productos

- si ""				pagar 		"" 	ir a la página de la caja (Si ya está iniciada la sesión), dónde se pregunta si se está seguro de que se quiere comprar. Si no está iniciada la sesión, mandar a la página correspondiente.

- si "" 			confirmar	""	introducir todo en la base de datos y mandar a la página de confirmación, en la que se indica que se manda un correo con la factura.

- si ""				salir		"" 	después de haber realizado la compra se pulsa este botón y se inicializa el carrito y la sesión.

- si ""				entrar 		"" 	se produce el inicio de sesión del usuario

- si "" 			registro 	"" 	se produce el registro del usuario

- si "" 		guardarProducto "" 	se actualizan las características del producto

- si ""			eliminarProducto ""	se elimina de la BD el producto seleccionado

- si ""			guardarComentario "" se guardan las modificaciones hechas en los comentarios

- si "" 		eliminarComentario "" se elimina el comentario seleccionado

- si ""			eliminarUsuario  ""	se elimina el usuario de la BD

- si ""			guardarUsuario 	 "" se guardan las modificaciones en la BD que se hicieron de ese usuario

- si "" 		anadirProducto 	"" se guarda un nuevo producto en la BD

