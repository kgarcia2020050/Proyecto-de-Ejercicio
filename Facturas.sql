CREATE DATABASE facturas;
USE facturas;

CREATE TABLE facturas (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nit VARCHAR(20) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  fecha DATE NOT NULL,
  correlativo INT NOT NULL
);

select * from facturas;



CREATE TABLE detalle_factura (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  factura_id INT NOT NULL,
  producto VARCHAR(100) NOT NULL,
  cantidad INT NOT NULL,
  monto_total DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (factura_id) REFERENCES facturas(id)
);
