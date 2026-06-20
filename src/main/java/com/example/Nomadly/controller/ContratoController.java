package com.example.Nomadly.controller;

import com.example.Nomadly.model.Contrato;
import com.example.Nomadly.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public ResponseEntity<Contrato> crearContrato(@RequestBody Contrato contrato) {
        Contrato nuevoContrato = contratoService.crearContrato(contrato);
        return ResponseEntity.ok(nuevoContrato);
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> listarContratos() {
        return ResponseEntity.ok(contratoService.obtenerTodos());
    }

    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Contrato>> obtenerPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(contratoService.obtenerPorCurso(idCurso));
    }
}