package com.test.test.controller;

import com.test.test.dto.DetalleFacturaDTO;
import com.test.test.dto.Respuesta;
import com.test.test.model.DetalleFactura;
import com.test.test.service.impl.DetalleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle")
@CrossOrigin("*")
public class DetalleController {

    private final DetalleService detalleService;

    public DetalleController(DetalleService detalleService) {
        this.detalleService = detalleService;
    }

    @PostMapping("/guardarDetalle")
    public Respuesta guardarDetalle(@RequestBody List<DetalleFacturaDTO> detalleProducto, @RequestParam Integer idFactura) {
        return detalleService.guardarDetalle(detalleProducto, idFactura);
    }

    @DeleteMapping("/eliminarDetalle")
    public Respuesta eliminarDetalle(@RequestParam Integer idDetalle) {
        return detalleService.eliminarDetalle(idDetalle);
    }

    @DeleteMapping("/eliminarDetalles")
    public Respuesta eliminarDetalles(@RequestParam Integer facturaId) {
        return detalleService.eliminarDetalles(facturaId);
    }

    @GetMapping("/verDetallesFactura")
    public Respuesta verDetallesFactura(@RequestParam Integer idFactura) {
        return detalleService.verDetallesFactura(idFactura);
    }

    @GetMapping("/verDetalle")
    public Respuesta verDetalle(@RequestParam Integer idDetalle) {
        return detalleService.verDetalle(idDetalle);
    }
}
