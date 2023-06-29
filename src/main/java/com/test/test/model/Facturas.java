package com.test.test.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "facturas")
@Data
public class Facturas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "nit")
    private String nit;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "correlativo")
    private int correlativo;

    @Column(name="precio_total")
    private BigDecimal precioTotal;
}
