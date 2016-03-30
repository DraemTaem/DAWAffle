-- CREACION DE TABLAS

CREATE TABLE usuarios (
	id integer AUTO_INCREMENT,
	nombre varchar(25) NOT NULL,
	email varchar(30) NOT NULL,
	categoria integer NOT NULL DEFAULT 0,
	direccion varchar(50),
	administrador boolean NOT NULL,
	alias varchar(20) NOT NULL,
	contrasena varchar(30) NOT NULL,	
	registrado boolean NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE pedidos (
	id integer AUTO_INCREMENT,
	idUsuario integer NOT NULL,
	categoriaUsuario integer NOT NULL,
	precio decimal NOT NULL,
	fecha timestamp DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY (id),
	FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);

CREATE TABLE productos (
	id integer AUTO_INCREMENT,
	nombre varchar(25) NOT NULL,
	descripcion varchar(500) NOT NULL,
	precio decimal NOT NULL,
	imagen varchar(100),

	PRIMARY KEY (id)
);

CREATE TABLE inventario (
	idProducto integer,
	stock integer NOT NULL DEFAULT 0,

	PRIMARY KEY (idProducto),
	FOREIGN KEY (idProducto) REFERENCES productos(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE valoraciones (
	idProducto integer,
	idUsuario integer,
	nota integer NOT NULL DEFAULT -1,
	comentario varchar(500),

	PRIMARY KEY (idProducto, idUsuario)
	FOREIGN KEY (idProducto) REFERENCES productos(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);

CREATE TABLE lineaspedido (
	idPedido integer,
	idProducto integer,
	cantidad integer NOT NULL,

	PRIMARY KEY (idPedido, idProducto),
	FOREIGN KEY (idPedido) REFERENCES pedidos(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (idProducto) REFERENCES productos(id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);




