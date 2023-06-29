package com.test.test.service;

import com.test.test.dto.DetalleFacturaDTO;
import com.test.test.dto.Respuesta;
import com.test.test.model.DetalleFactura;

import java.util.List;

public interface DetalleFacturaService {
    Respuesta guardarDetalle(List<DetalleFacturaDTO>detalleProducto, Integer idFactura);


    Respuesta eliminarDetalle(Integer idDetalle);

    Respuesta eliminarDetalles(Integer facturaId);

    Respuesta verDetallesFactura(Integer idFactura);

    Respuesta verDetalle(Integer idDetalle);


}
