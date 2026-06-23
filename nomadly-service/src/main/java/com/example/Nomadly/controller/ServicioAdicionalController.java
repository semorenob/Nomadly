package com.example.Nomadly.controller;

import com.example.Nomadly.model.ServicioAdicional;
import com.example.Nomadly.service.ServicioAdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios-adicionales")
public class ServicioAdicionalController {

    @Autowired
    private ServicioAdicionalService servicioAdicionalService;

    @PostMapping
    public ResponseEntity<ServicioAdicional> registrarServicio(@RequestBody ServicioAdicional servicio) {
        ServicioAdicional nuevoServicio = servicioAdicionalService.registrarServicio(servicio);
        return ResponseEntity.ok(nuevoServicio);
    }

    @GetMapping
    public ResponseEntity<List<ServicioAdicional>> listarServicios() {
        return ResponseEntity.ok(servicioAdicionalService.obtenerTodos());
    }

    @GetMapping("/contrato/{idContrato}")
    public ResponseEntity<List<ServicioAdicional>> obtenerPorContrato(@PathVariable Long idContrato) {
        return ResponseEntity.ok(servicioAdicionalService.obtenerPorContrato(idContrato));
    }
}