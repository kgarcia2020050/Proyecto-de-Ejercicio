package com.test.test.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_factura")
@Data
public class DetalleFactura {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "factura_id")
    private int facturaId;
    @Column(name = "producto")
    private String producto;
    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_producto")
    private BigDecimal precioProducto;

    @Column(name = "monto_total")
    private BigDecimal montoTotal;


}
