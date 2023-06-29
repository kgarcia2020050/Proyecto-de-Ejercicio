package com.test.test.service.impl;

import com.test.test.dto.DetalleFacturaDTO;
import com.test.test.dto.Respuesta;
import com.test.test.model.DetalleFactura;
import com.test.test.model.Facturas;
import com.test.test.repositories.DetalleRepository;
import com.test.test.repositories.FacturaRepository;
import com.test.test.service.DetalleFacturaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleService implements DetalleFacturaService {

    private final FacturaRepository facturaRepository;


    private final DetalleRepository detalleRepository;

    public DetalleService(FacturaRepository facturaRepository, DetalleRepository detalleRepository) {
        this.facturaRepository = facturaRepository;
        this.detalleRepository = detalleRepository;
    }

    @Override
    public Respuesta guardarDetalle(List<DetalleFacturaDTO> detalleProducto, Integer idFactura) {
        Respuesta respuesta = new Respuesta();
        if (detalleProducto == null || detalleProducto.size() == 0 || idFactura == null || facturaRepository.findById(idFactura) == null) {
            respuesta.setMensaje("No hay detalles para agregar");
            return respuesta;
        }
        List<DetalleFactura> detalles = new ArrayList<>();
        Facturas factura = facturaRepository.findById(idFactura);
        factura.setPrecioTotal(new BigDecimal(0));
        for (int i = 0; i < detalleProducto.size(); i++) {
            DetalleFactura detalle = new DetalleFactura();
            detalle.setCantidad(detalleProducto.get(i).getCantidad());
            detalle.setProducto(detalleProducto.get(i).getProducto());
            detalle.setPrecioProducto(detalleProducto.get(i).getPrecioProducto());
            detalle.setMontoTotal(detalle.getPrecioProducto().multiply(new BigDecimal(detalle.getCantidad())));
            detalle.setFacturaId(idFactura);
            factura.setPrecioTotal(factura.getPrecioTotal().add(detalle.getMontoTotal()));
            detalles.add(detalle);
        }
        detalleRepository.saveAll(detalles);
        facturaRepository.save(factura);
        respuesta.setMensaje("Detalles de la factura guardados.");
        respuesta.setDatos(detalles);
        return respuesta;
    }


    @Override
    public Respuesta eliminarDetalle(Integer idDetalle) {
        Respuesta respuesta = new Respuesta();
        if (idDetalle == null || detalleRepository.findById(idDetalle) == null) {
            respuesta.setMensaje("Detalle no encontrado");
            return respuesta;
        }
        DetalleFactura detalle = detalleRepository.findById(idDetalle);
        detalleRepository.delete(detalleRepository.findById(idDetalle));
        Facturas facturas = facturaRepository.findById(detalle.getFacturaId());
        facturas.setPrecioTotal(facturas.getPrecioTotal().subtract(detalle.getMontoTotal()));
        facturaRepository.save(facturas);
        respuesta.setMensaje("Detalle eliminado");
        return respuesta;
    }

    @Override
    public Respuesta eliminarDetalles(Integer facturaId) {
        Respuesta respuesta = new Respuesta();
        if (facturaId == null || facturaRepository.findById(facturaId) == null) {
            respuesta.setMensaje("Factura no encontrada");
            return respuesta;
        }
        List<DetalleFactura> detalles = detalleRepository.findAllByFacturaId(facturaId);
        if (detalles.size() > 0) {
            Facturas facturas = facturaRepository.findById(facturaId);
            facturas.setPrecioTotal(new BigDecimal(0));
            facturaRepository.save(facturas);
            detalleRepository.deleteAll(detalles);
            respuesta.setMensaje("Detalles eliminados.");
        } else {
            respuesta.setMensaje("No hay detalles para ser eliminados.");
        }
        return respuesta;
    }

    @Override
    public Respuesta verDetallesFactura(Integer idFactura) {
        Respuesta respuesta = new Respuesta();
        if (idFactura == null || facturaRepository.findById(idFactura) == null) {
            respuesta.setMensaje("Factura no encontrada.");
            return respuesta;

        }
        List<DetalleFactura> detalles = detalleRepository.findAllByFacturaId(idFactura);
        respuesta.setMensaje("Detalles encontrados.");
        respuesta.setDatos(detalles);
        return respuesta;
    }

    @Override
    public Respuesta verDetalle(Integer idDetalle) {
        Respuesta respuesta = new Respuesta();
        if (idDetalle == null || detalleRepository.findById(idDetalle) == null) {
            respuesta.setMensaje("No se encuentra el detalle especificado.");
            return respuesta;
        }
        respuesta.setMensaje("Detalle encontrado.");
        respuesta.setDatos(detalleRepository.findById(idDetalle));
        return respuesta;
    }
}
