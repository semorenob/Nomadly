package com.example.Nomadly.service;

import com.example.Nomadly.model.Pago;
import com.example.Nomadly.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public Pago registrarPago(Pago pago) {

        if (pago.getFechaPago() == null) {
            pago.setFechaPago(LocalDate.now());
        }
        return pagoRepository.save(pago);
    }

    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    public List<Pago> obtenerPorReferencia(Long idReferencia, Pago.TipoDestino tipoDestino) {
        return pagoRepository.findByIdReferenciaAndTipoDestino(idReferencia, tipoDestino);
    }
}