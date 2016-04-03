INSERT INTO usuarios (nombre, email, direccion, administrador, alias, contrasena, registrado)
	VALUES ('Pablo Tomico', 'pablotomico@hotmail.com', 'Calle falsa 123', 1, 'ptomico', 'qwerty', 1),
		('Bruno Quintela', 'brunoquintela@hotmail.com', 'Calle falsa 321', 0, 'bquintela', 'qwerty', 1),
		('Ruben Osorio', 'rubenosorio@hotmail.com', 'Calle falsa 132', 0, 'rosorio', 'qwerty', 1);




INSERT INTO pedidos (idUsuario, categoriaUsuario, precio, fecha)
	VALUES (1, 0, 10, '1-1-2016');


INSERT INTO productos (nombre, descripcion, precio, imagen, tipo)
	VALUES ('prod1', 'este es el producto 1', 10, 'url1', 'cd');

INSERT INTO cd (idProducto, autor, pais, ano)
	VALUES (1, 'autor 1', 'pais 1', 1900);

INSERT INTO lineaspedido (idPedido, idProducto, cantidad)
	VALUES (1, 1, 10);

INSERT INTO inventario (idProducto, stock)
	VALUES (1, 10);


UPDATE usuarios 
	SET nombre = 'Pablo Tomico', email = 'tomicopablo@hotmail.com', direccion = 'Calle aun mas falsa 123', alias = 'ptomico', contrasena = 'qwertee'
	WHERE id = 1;
