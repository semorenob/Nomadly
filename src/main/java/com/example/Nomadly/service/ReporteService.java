package com.example.Nomadly.service;

import com.example.Nomadly.model.Reporte;
import com.example.Nomadly.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public Reporte generarReporte(Reporte reporte) {
        if (reporte.getFechaGeneracion() == null) {
            reporte.setFechaGeneracion(LocalDate.now());
        }
        return reporteRepository.save(reporte);
    }

    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    public List<Reporte> obtenerPorColegio(Long idColegio) {
        return reporteRepository.findByIdColegio(idColegio);
    }

    public List<Reporte> obtenerPorCurso(Long idCurso) {
        return reporteRepository.findByIdCurso(idCurso);
    }

    public Optional<Reporte> obtenerPorId(Long id) {
        return reporteRepository.findById(id);
    }
}