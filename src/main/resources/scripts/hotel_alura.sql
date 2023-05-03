
DROP DATABASE IF EXISTS hotel_alura;
CREATE DATABASE hotel_alura DEFAULT CHARACTER SET = utf8mb4;

USE hotel_alura;

DROP TABLE IF EXISTS reservas;
CREATE TABLE reservas (
  id_reservas INT NOT NULL AUTO_INCREMENT,
  fecha_entrada DATE NOT NULL,
  fecha_salida DATE NOT NULL,
  valor DECIMAL NOT NULL,
  forma_de_pago VARCHAR(155) NOT NULL,
  PRIMARY KEY (id_reservas));
  
INSERT INTO reservas
(fecha_entrada, fecha_salida, valor, forma_de_pago)
VALUES
("2022-12-09", "2022-12-22", 2500, "Tarjeta de Crédito"),
("2023-02-19", "2023-02-25", 3000, "Tarjeta de Débito"),
("2023-05-23", "2023-05-30", 2890, "Dinero en efectivo");

DROP TABLE IF EXISTS huespedes;
CREATE TABLE huespedes (
  id_huespedes INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(155) NOT NULL,
  apellido VARCHAR(155) NOT NULL,
  fecha_nacimiento DATE NOT NULL,
  nacionalidad VARCHAR(155) NOT NULL,
  telefono LONG NOT NULL,
  id_reservas INT NULL,
  PRIMARY KEY (id_huespedes),
  INDEX idreservas_fk_idx (id_reservas ASC) VISIBLE,
  CONSTRAINT idreservas_fk FOREIGN KEY (id_reservas)
    REFERENCES reservas (id_reservas)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO huespedes 
(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reservas) 
VALUES
("Lucas", "Altamirano", '1982-03-14', "España", 345676543, 1),
("Noelia", "Poblete", '2004-11-02', "El Salvador", 503211325, 2),
("Frank", "Witherhorst", '1998-05-15', "Inglaterra", 079876541, 3);
