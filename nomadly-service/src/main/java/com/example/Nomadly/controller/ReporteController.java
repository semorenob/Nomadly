package com.example.Nomadly.controller;

import com.example.Nomadly.model.Reporte;
import com.example.Nomadly.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public ResponseEntity<Reporte> registrarReporte(@RequestBody Reporte reporte) {
        Reporte nuevoReporte = reporteService.generarReporte(reporte);
        return ResponseEntity.ok(nuevoReporte);
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> listarReportes() {
        return ResponseEntity.ok(reporteService.obtenerTodos());
    }

    @GetMapping("/colegio/{idColegio}")
    public ResponseEntity<List<Reporte>> obtenerPorColegio(@PathVariable Long idColegio) {
        return ResponseEntity.ok(reporteService.obtenerPorColegio(idColegio));
    }

    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Reporte>> obtenerPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(reporteService.obtenerPorCurso(idCurso));
    }
}