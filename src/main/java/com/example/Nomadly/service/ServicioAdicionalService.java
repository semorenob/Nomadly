package com.example.Nomadly.service;

import com.example.Nomadly.model.ServicioAdicional;
import com.example.Nomadly.repository.ServicioAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioAdicionalService {

    @Autowired
    private ServicioAdicionalRepository servicioAdicionalRepository;

    public ServicioAdicional registrarServicio(ServicioAdicional servicio) {
        return servicioAdicionalRepository.save(servicio);
    }

    public List<ServicioAdicional> obtenerTodos() {
        return servicioAdicionalRepository.findAll();
    }

    public List<ServicioAdicional> obtenerPorContrato(Long idContrato) {
        return servicioAdicionalRepository.findByIdContrato(idContrato);
    }

    public Optional<ServicioAdicional> obtenerPorId(Long id) {
        return servicioAdicionalRepository.findById(id);
    }
}