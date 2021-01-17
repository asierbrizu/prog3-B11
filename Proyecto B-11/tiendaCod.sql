DROP TABLE usuario;
CREATE TABLE usuario(
correo varchar(30) NOT NULL,
contrasenya varchar(50) NOT NULL,
PRIMARY KEY(correo));

DROP TABLE pedido;
CREATE TABLE pedido(
id varchar(10) NOT NULL,
importeTotal dec(6,2),
fecha timestamp,
correo varchar(30),
PRIMARY KEY (id),
FOREIGN KEY (correo) REFERENCES usuario(correo));

DROP TABLE productosPedido;
CREATE TABLE productosPedido(
idCamiseta varchar(10),
idZapatos varchar(10),
idPe varchar(10) NOT NULL,
cant integer,
numero integer,
talla varchar(5),
PRIMARY KEY(idPe,idCamiseta,idZapatos,numero,talla),
FOREIGN KEY (idCamiseta) REFERENCES camisetas(idProducto),
FOREIGN KEY (idZapatos) REFERENCES zapatos(idProducto),
FOREIGN KEY (idPe) REFERENCES pedido(id));

DROP TABLE deseados;
CREATE TABLE deseados(
idCamiseta varchar(10),
idZapatos varchar(10),
correo varchar(30) NOT NULL,
PRIMARY KEY(correo,idCamiseta,idZapatos),
FOREIGN KEY (idCamiseta) REFERENCES camisetas(idProducto),
FOREIGN KEY (idZapatos) REFERENCES zapatos(idProducto),
FOREIGN KEY (correo) REFERENCES usuario(correo));

DROP TABLE camiseta;
CREATE TABLE camiseta(
idProducto varchar(10),
nombre varchar(30),
precio dec(5,2),
descuento dec(3,2),
color varchar(15),
imagen varchar(25),
descatalogado bit(1),
talla varchar(3),
material varchar(25),
esCamiseta bit(1),
PRIMARY KEY(idProducto));

DROP TABLE zapatos;
CREATE TABLE zapatos(
idProducto varchar(10),
nombre varchar(30),
precio dec(5,2),
descuento dec(3,2),
color varchar(15),
imagen varchar(25),
descatalogado bit(1),
numero int,
tipoSuela varchar(30),
velcro bit(1),
PRIMARY KEY(idProducto));

INSERT INTO usuario VALUES('asierbrizu@opendeusto.es','contra1');
INSERT INTO usuario VALUES('asierbrizuela@opendeusto.es','contra1');
INSERT INTO usuario VALUES('unai.aguilera@deusto.es','deusto');
INSERT INTO usuario VALUES('easter','egg');

INSERT INTO camiseta VALUES(1,'Graduador en Deusto',7.5,1,'Negro','img/anciano.png',0,'S','Poliester',1);
INSERT INTO camiseta VALUES(2,'Rutina diaria',8,1,'Gris','img/codigo.png',0,'M','Poliester',1);
INSERT INTO camiseta VALUES(3,'En caso de incendio',10,0.8,'Azul oscuro','img/fuego.png',1,'S','Algodon',1);
INSERT INTO camiseta VALUES(4,'Por que gafas?',5.75,1,'Blanco','img/gafas.png',0,'L','Algodon',1);
INSERT INTO camiseta VALUES(5,'Patito depurador',11,1,'Negro','img/pato.jpg',0,'M','Poliester',1);
INSERT INTO camiseta VALUES(6,'La primera regla',6,1,'Azul oscuro','img/regla.jpg',1,'XL','Algodon',1);
INSERT INTO camiseta VALUES(7,'No tengo vida',5,1,'Negro','img/vida.jpg',0,'M','Sintetico',1);
INSERT INTO camiseta VALUES(8,'Pantalones a cuadros',20,0.75,'Rojo','img/cuadros.jpg',0,'L','Algodon',0);
INSERT INTO camiseta VALUES(9,'Pantalones vaqueros',15.0,1,'Azul','img/vaqueros.jpg',0,'S','Poliester',0);
INSERT INTO camiseta VALUES(10,'Pantalones de traje',12.5,1,'Negro','img/formal.jpg',0,'M','Algodon',0);
INSERT INTO camiseta VALUES(11,'Pantalones de campana',11,1,'Negro','img/campana.jpg',0,'XL','Sintetico',0);
INSERT INTO camiseta VALUES(12,'Pantalones de Pascua',99.99,1,'Rosa','img/easter.jpg',1,'S','Sintetico',0);

INSERT INTO zapatos VALUES(13,'Botas',30,1,'Marron','img/botas.jpg',0,30,'TPU',0);
INSERT INTO zapatos VALUES(14,'Converse',20.75,0.75,'Rojo','img/converse.jpg',1,28,'Goma',0);
INSERT INTO zapatos VALUES(15,'Deportivas rosas',15,1,'Rosa','img/deportivas.jpg',0,30,'Goma',0);
INSERT INTO zapatos VALUES(16,'Zapatillas marrones',25,1,'Marron','img/marronesVelcro.jpg',0,35,'Goma',1);
INSERT INTO zapatos VALUES(17,'Zapatillas negras',20,1,'Negro','img/negrasVelcro.jpg',0,25,'Goma',1);
INSERT INTO zapatos VALUES(18,'Zapatillas Nike',40,0.4,'Negro','img/nike.jpg',0,37,'Goma',0);
INSERT INTO zapatos VALUES(19,'Sandalias',15,1,'Negro','img/sandalias.jpg',0,25,'Goma',1);
INSERT INTO zapatos VALUES(20,'Tacones',45,1,'Negro','img/tacones.jpg',0,30,'Cuero',0);
INSERT INTO zapatos VALUES(21,'Zapatos',50,1,'Negro','img/zapatos.jpg',0,40,'Cuero',0);
INSERT INTO zapatos VALUES(22,'Zapatos de Pascua',99.9,1,'Rosa','img/easter.jpg',1,20,'Goma termoplastica',0);

INSERT INTO deseados (idCamiseta,correo) VALUES('1','unai.aguilera@deusto.es');
INSERT INTO deseados (idCamiseta,correo) VALUES('2','unai.aguilera@deusto.es');
INSERT INTO deseados (idCamiseta,correo) VALUES('4','unai.aguilera@deusto.es');
INSERT INTO deseados (idCamiseta,correo) VALUES('5','unai.aguilera@deusto.es');
INSERT INTO deseados (idCamiseta,correo) VALUES('10','unai.aguilera@deusto.es');
INSERT INTO deseados (idZapatos,correo) VALUES('13','unai.aguilera@deusto.es');
INSERT INTO deseados (idZapatos,correo) VALUES('15','unai.aguilera@deusto.es');
INSERT INTO deseados (idZapatos,correo) VALUES('17','unai.aguilera@deusto.es');

INSERT INTO deseados (idCamiseta,correo) VALUES('1','asierbrizu@opendeusto.es');
INSERT INTO deseados (idCamiseta,correo) VALUES('2','asierbrizu@opendeusto.es');
INSERT INTO deseados (idCamiseta,correo) VALUES('4','asierbrizu@opendeusto.es');
INSERT INTO deseados (idCamiseta,correo) VALUES('5','asierbrizu@opendeusto.es');
INSERT INTO deseados (idCamiseta,correo) VALUES('10','asierbrizu@opendeusto.es');
INSERT INTO deseados (idZapatos,correo) VALUES('13','asierbrizu@opendeusto.es');
INSERT INTO deseados (idZapatos,correo) VALUES('15','asierbrizu@opendeusto.es');
INSERT INTO deseados (idZapatos,correo) VALUES('17','asierbrizu@opendeusto.es');

INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('1','1',2,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('1','1',3,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('3','1',1,'L');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('4','1',1,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('5','1',2,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('6','1',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('7','2',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('10','2',2,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('9','2',3,'L');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('10','2',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('11','2',2,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('11','2',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('13','2',1,41);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('9','2',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('15','2',2,40);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('1','3',3,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('2','3',2,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('3','3',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('4','3',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('13','3',1,42);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('5','3',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('6','3',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('7','3',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('8','3',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('9','3',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('10','3',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('15','3',1,50);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('3','3',1,'L');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('11','3',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('13','3',1,43);
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('14','3',1,40);
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('15','3',1,45);
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('16','3',1,48);
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('17','3',1,44);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('8','3',1,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('1','4',2,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('1','4',3,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('3','4',1,'L');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('4','4',1,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('5','4',2,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('6','4',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('7','5',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('10','5',2,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('9','5',3,'L');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('10','5',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('11','5',2,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('11','5',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('13','5',1,41);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('9','5',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('15','5',2,40);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('1','6',3,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('2','6',2,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('3','6',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('4','6',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('13','6',1,42);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('5','6',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('6','6',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('7','6',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('8','6',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('9','6',1,'S');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('10','6',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('15','6',1,50);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('3','6',1,'L');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('11','6',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('13','6',1,43);
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('14','6',1,40);
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('15','6',1,45);
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('16','6',1,48);
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('17','6',1,44);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('8','6',1,'M');
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('12','7',1,'S');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('22','7',2,41);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('12','7',3,'M');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('22','7',1,42);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('12','7',2,'L');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('22','7',1,43);
INSERT INTO productospedido (idCamiseta,idPe,cant,talla) VALUES ('12','7',1,'XL');
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('22','7',1,44);
INSERT INTO productospedido (idZapatos,idPe,cant,numero) VALUES ('22','7',2,45);

INSERT INTO pedido VALUES('1',79.25,'2021-01-15 18:16:34','unai.aguilera@deusto.es');
INSERT INTO pedido VALUES('2',195.5,'2021-01-15 18:16:34','unai.aguilera@deusto.es');
INSERT INTO pedido VALUES('3',301.3,'2021-01-15 18:16:34','unai.aguilera@deusto.es');
INSERT INTO pedido VALUES('4',79.25,'2021-01-15 18:16:34','asierbrizu@opendeusto.es');
INSERT INTO pedido VALUES('5',195.5,'2021-01-15 18:16:34','asierbrizu@opendeusto.es');
INSERT INTO pedido VALUES('6',301.3,'2021-01-15 18:16:34','asierbrizu@opendeusto.es');
INSERT INTO pedido VALUES('7',899.91,'2021-01-15 18:16:34','easter');
