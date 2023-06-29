package com.test.test.controller;

import com.test.test.dto.FacturasDTO;
import com.test.test.dto.Respuesta;
import com.test.test.model.Facturas;
import com.test.test.service.impl.FacturaServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturas")
@CrossOrigin("*")
public class FacturaController {

    private FacturaServiceImpl facturaService;

    public FacturaController(FacturaServiceImpl facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/verFacturas")
    public Respuesta verFacturas() {
        return facturaService.verFacturas();
    }

    @PostMapping("/guardarFactura")
    public Respuesta guardarFactura(@RequestBody FacturasDTO facturasDTO) {
        Respuesta respuesta = facturaService.guardarFactura(facturasDTO);
        return respuesta;
    }

    @GetMapping("/verFacturasNit")
    public Respuesta verFacturasNit(@RequestParam String nit) {
        return facturaService.verFacturasNit(nit);
    }

    @GetMapping("/verFactura")
    public Respuesta verFactura(@RequestParam Integer id) throws Exception {

        return facturaService.verFactura(id);


    }

    @GetMapping("/borrarFactura")
    public Respuesta borrarFactura(@RequestParam Integer id) throws Exception {
            return facturaService.borrarFactura(id);


    }

    @GetMapping("/borrarFacturasNit")
    public Respuesta borrarFacturasNit(@RequestParam String nit) {
            return facturaService.borrarFacturas(nit);
    }

    @PutMapping("/editarFactura")
    public Respuesta editarFactura(@RequestBody Facturas facturas) {
            return facturaService.editarFacturas(facturas);
    }


}
