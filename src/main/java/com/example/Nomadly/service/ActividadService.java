package com.example.Nomadly.service;

import com.example.Nomadly.model.Actividad;
import com.example.Nomadly.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    public Actividad registrarActividad(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    public List<Actividad> obtenerTodas() {
        return actividadRepository.findAll();
    }

    public List<Actividad> obtenerPorCurso(Long idCurso) {
        return actividadRepository.findByIdCurso(idCurso);
    }

    public Optional<Actividad> obtenerPorId(Long id) {
        return actividadRepository.findById(id);
    }
}