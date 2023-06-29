package com.test.test.dto;


import lombok.Data;

import javax.validation.constraints.*;

import java.math.BigDecimal;

@Data
public class DetalleFacturaDTO {
    @NotEmpty(message = "El nombre del producto no puede estar vacio.")
    private String producto;
    @NotEmpty(message = "La cantidad del producto no puede estar vacia.")
    private int cantidad;
    private BigDecimal precioProducto;
    private BigDecimal montoTotal;

}
