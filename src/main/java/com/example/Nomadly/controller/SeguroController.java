package com.example.Nomadly.controller;

import com.example.Nomadly.model.Seguro;
import com.example.Nomadly.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguros")
public class SeguroController {

    @Autowired
    private SeguroService seguroService;

    @PostMapping
    public ResponseEntity<Seguro> registrarSeguro(@RequestBody Seguro seguro) {
        Seguro nuevoSeguro = seguroService.registrarSeguro(seguro);
        return ResponseEntity.ok(nuevoSeguro);
    }

    @GetMapping
    public ResponseEntity<List<Seguro>> listarSeguros() {
        return ResponseEntity.ok(seguroService.obtenerTodos());
    }

    @GetMapping("/contrato/{idContrato}")
    public ResponseEntity<List<Seguro>> obtenerPorContrato(@PathVariable Long idContrato) {
        return ResponseEntity.ok(seguroService.obtenerPorContrato(idContrato));
    }
}