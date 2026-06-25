package com.example.Nomadly.controller;

import com.example.Nomadly.model.Pago;
import com.example.Nomadly.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.registrarPago(pago);
        return ResponseEntity.ok(nuevoPago);
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos() {
        return ResponseEntity.ok(pagoService.obtenerTodos());
    }

    @GetMapping("/{tipoDestino}/{idReferencia}")
    public ResponseEntity<List<Pago>> obtenerPorReferencia(
            @PathVariable Pago.TipoDestino tipoDestino,
            @PathVariable Long idReferencia) {
        return ResponseEntity.ok(pagoService.obtenerPorReferencia(idReferencia, tipoDestino));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }

}