package com.example.Nomadly.service;

import com.example.Nomadly.model.Documento;
import com.example.Nomadly.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public Documento registrarDocumento(Documento documento) {
        if (documento.getFechaSubida() == null) {
            documento.setFechaSubida(LocalDate.now());
        }
        return documentoRepository.save(documento);
    }

    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }

    public List<Documento> obtenerPorContrato(Long idContrato) {
        return documentoRepository.findByIdContrato(idContrato);
    }

    public Optional<Documento> obtenerPorId(Long id) {
        return documentoRepository.findById(id);
    }
}