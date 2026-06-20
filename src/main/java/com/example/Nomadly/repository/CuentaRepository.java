package com.example.Nomadly.repository;

import com.example.Nomadly.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<Cuenta> findByIdReferenciaAndTipoCuenta(Long idReferencia, Cuenta.TipoCuenta tipoCuenta);

}