INSERT INTO usuarios (nombre, email, direccion, administrador, alias, contrasena, registrado)
	VALUES ('Pablo Tomico', 'pablotomico@hotmail.com', 'Calle falsa 123', 0, 'ptomico', 'qwerty', 1);

UPDATE usuarios 
	SET nombre = 'Pablo Tomico', email = 'tomicopablo@hotmail.com', direccion = 'Calle aun mas falsa 123', alias = 'ptomico', contrasena = 'qwertee'
	WHERE id = 1;


INSERT INTO pedidos (idUsuario, categoriaUsuario, precio, fecha)
	VALUES (1, 0, 10, '1-1-2016');


INSERT INTO productos (nombre, descripcion, precio, imagen, tipo)
	VALUES ('prod1', 'este es el producto 1', 10, 'url1', 'cd');

INSERT INTO cd (idProducto, autor, pais)
	VALUES (1, 'autor 1', 'pais 1');