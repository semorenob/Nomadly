package com.example.Nomadly.repository;

import com.example.Nomadly.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByIdReferenciaAndTipoDestino(Long idReferencia, Pago.TipoDestino tipoDestino);

}