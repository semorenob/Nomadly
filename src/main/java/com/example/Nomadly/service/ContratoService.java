package com.example.Nomadly.service;

import com.example.Nomadly.model.Contrato;
import com.example.Nomadly.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    public Contrato crearContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    public List<Contrato> obtenerTodos() {
        return contratoRepository.findAll();
    }

    public Optional<Contrato> obtenerPorId(Long id) {
        return contratoRepository.findById(id);
    }

    public List<Contrato> obtenerPorCurso(Long idCurso) {
        return contratoRepository.findByIdCurso(idCurso);
    }
}