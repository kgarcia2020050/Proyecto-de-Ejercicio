package com.test.test.service.impl;

import com.test.test.dto.FacturasDTO;
import com.test.test.dto.Respuesta;
import com.test.test.model.Facturas;
import com.test.test.repositories.FacturaRepository;
import com.test.test.service.FacturaService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public Date generarFecha() {

        LocalDate fechaLocal = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String fechaFormateada = fechaLocal.format(formatter);

        Date fecha = Date.valueOf(LocalDate.parse(fechaFormateada, formatter));
        return fecha;
    }

    public Integer generarCorrelativo() {
        List<Facturas> facturas = facturaRepository.findAll();
        if (facturas.size() == 0) {
            return 0;
        } else {
            return facturas.get(facturas.size() - 1).getCorrelativo() + 1;
        }
    }

    @Override
    public Respuesta guardarFactura(FacturasDTO factura) {
        Respuesta respuesta = new Respuesta();

        if (factura == null || factura.getNombre() == null || factura.getNit() == null) {
            respuesta.setMensaje("Los datos vienen vacios");
            return respuesta;
        }

        Facturas facturaModel = new Facturas();

        factura.setFecha(generarFecha());
        factura.setCorrelativo(generarCorrelativo());
        BeanUtils.copyProperties(factura, facturaModel);


        facturaRepository.save(facturaModel);

        respuesta.setMensaje("Factura guardada correctamente.");
        respuesta.setDatos(facturaModel);

        return respuesta;

    }


    @Override
    public Respuesta verFacturas() {
        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje("Facturas encontradas.");
        respuesta.setDatos(facturaRepository.findAll());
        return respuesta;
    }

    @Override
    public Respuesta verFacturasNit(String nit) {
        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje("Facturas encontradas.");
        respuesta.setDatos(facturaRepository.findAllByNit(nit));
        return respuesta;
    }

    @Override
    public Respuesta verFactura(Integer idFactura) {

        Respuesta respuesta = new Respuesta();
        Facturas facturaEncontrada = facturaRepository.findById(idFactura);
        FacturasDTO factura = new FacturasDTO();
        if (idFactura == null || facturaEncontrada == null) {
            respuesta.setMensaje("Factura no encontrada");
            return respuesta;
        }

        if (facturaEncontrada != null) {
            BeanUtils.copyProperties(facturaEncontrada, factura);
            respuesta.setMensaje("Factura encontrada.");
            respuesta.setDatos(factura);
        }


        return respuesta;


    }


    @Override
    public Respuesta borrarFactura(Integer idFactura) throws Exception {

        Facturas factura = facturaRepository.findById(idFactura);
        Respuesta respuesta = new Respuesta();
        if (idFactura == null || factura == null) {
            respuesta.setMensaje("Factura no encontrada.");
            return respuesta;
        } else {
            respuesta.setMensaje("Factura eliminada correctamente.");
            return respuesta;
        }


    }

    @Override
    public Respuesta borrarFacturas(String nit) {
        List<Facturas> facturas = facturaRepository.findAllByNit(nit);
        Respuesta respuesta = new Respuesta();
        if (nit == null || facturas == null) {
            respuesta.setMensaje("El nit es nulo");
            return respuesta;
        }
        if (facturas.size() < 1) {
            respuesta.setMensaje("No hay facturas con el nit especificado");
            return respuesta;
        } else {
            facturaRepository.deleteAll(facturas);
            respuesta.setMensaje("Facturas con el nit " + nit + " borradas correctamente");
            return respuesta;
        }
    }

    @Override
    public Respuesta editarFacturas(Facturas facturas) {
        Facturas factura = facturaRepository.findById(facturas.getId());
        Respuesta respuesta = new Respuesta();
        if (facturas == null || facturas.getId() == null || factura == null) {
            respuesta.setMensaje("No se encuentra la factura con el id especificado");
            return respuesta;
        } else {
            BeanUtils.copyProperties(facturas, factura);
            facturaRepository.save(factura);
            respuesta.setMensaje("Factura modificada correctamente");
            return respuesta;
        }
    }
}
