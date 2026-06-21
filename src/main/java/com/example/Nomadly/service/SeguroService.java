package com.example.Nomadly.service;

import com.example.Nomadly.model.Seguro;
import com.example.Nomadly.repository.SeguroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeguroService {

    @Autowired
    private SeguroRepository seguroRepository;

    public Seguro registrarSeguro(Seguro seguro) {
        return seguroRepository.save(seguro);
    }

    public List<Seguro> obtenerTodos() {
        return seguroRepository.findAll();
    }

    public List<Seguro> obtenerPorContrato(Long idContrato) {
        return seguroRepository.findByIdContrato(idContrato);
    }

    public Optional<Seguro> obtenerPorId(Long id) {
        return seguroRepository.findById(id);
    }
}