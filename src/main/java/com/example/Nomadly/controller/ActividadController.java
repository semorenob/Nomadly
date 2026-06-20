package com.example.Nomadly.controller;

import com.example.Nomadly.model.Actividad;
import com.example.Nomadly.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @PostMapping
    public ResponseEntity<Actividad> registrarActividad(@RequestBody Actividad actividad) {
        Actividad nuevaActividad = actividadService.registrarActividad(actividad);
        return ResponseEntity.ok(nuevaActividad);
    }

    @GetMapping
    public ResponseEntity<List<Actividad>> listarActividades() {
        return ResponseEntity.ok(actividadService.obtenerTodas());
    }

    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Actividad>> obtenerPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(actividadService.obtenerPorCurso(idCurso));
    }
}