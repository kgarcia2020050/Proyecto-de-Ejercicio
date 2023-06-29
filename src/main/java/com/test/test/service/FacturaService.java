package com.test.test.service;

import com.test.test.dto.FacturasDTO;
import com.test.test.dto.Respuesta;
import com.test.test.model.Facturas;

public interface FacturaService {

    Respuesta guardarFactura(FacturasDTO factura);

    Respuesta verFacturas();

    Respuesta verFacturasNit(String nit);

    Respuesta verFactura(Integer idFactura);

    Respuesta borrarFactura(Integer idFactura) throws Exception;

    Respuesta borrarFacturas(String nit);

    Respuesta editarFacturas(Facturas facturas);

}
