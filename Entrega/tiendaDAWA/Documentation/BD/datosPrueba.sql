INSERT INTO usuarios (nombre, email, direccion, administrador, alias, contrasena, registrado)
	VALUES ('Pablo Tomico', 'pablotomico@hotmail.com', 'Calle falsa 123', 1, 'ptomico', 'qwerty', 1);


INSERT INTO productos (nombre, descripcion, precio, imagen, tipo) VALUES ('Cesaria Evora', 'este es la descripcion', 14.95, 'http://vignette2.wikia.nocookie.net/ironmaiden/images/e/ef/The-Number-Of-The_Beast.jpg/revision/latest?cb=20110721052953&path-prefix=es', 'cd'),
	 ('Dance the Devil Away', 'Esta es la descripcion', 19.99, 'http://windowstorrent.ru/torrents/images1/Windows%2095.jpg','cd'),
	 ('Record of Change', 'Esta es la descripcion', 12.90, 'http://www.padisgraf.com/wp-content/uploads/CDS1.jpg', 'cd'),
	 ('Drums of Passion', 'Esta es la descripcion', 10, 'https://ubuntulife.files.wordpress.com/2009/10/kkcover1.jpg', 'cd'),
	 ('Djelika', 'Esta es la descripcion', 9.90, 'http://windowstorrent.ru/torrents/images1/Windows%2095.jpg', 'cd');

INSERT INTO cd (idProducto, autor, pais, ano)
	VALUES (1, 'Johny Peperoni', 'Macarroni', 1999),
		(2, 'Br146', 'Nigeria', 2000),
		(3, 'Ruborio Corsorio', '7Mares', 1995),
		(4, 'Commito', 'GitHub', 2016),
		(5, 'Qwerty Master', 'Qwertee', 1937);


INSERT INTO inventario (idProducto, stock)
	VALUES (1, 123),
		(2, 150),
		(3, 101),
		(4, 1032),
		(5, 310);
