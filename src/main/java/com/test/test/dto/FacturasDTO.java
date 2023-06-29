package com.test.test.dto;


import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Date;

@Data
public class FacturasDTO {
    @NotEmpty(message = "El NIT no puede estar vacio.")
    @Size(min = 13, max = 13, message = ("El NIT debe tener 13 digitos."))
    private String nit;
    @NotEmpty(message = "El nombre no puede estar vacio.")
    private String nombre;
    private Date fecha;
    private int correlativo;
}
