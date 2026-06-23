package com.example.Nomadly.service;

import com.example.Nomadly.model.Cuenta;
import com.example.Nomadly.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta crearCuenta(Cuenta cuenta) {

        if (cuenta.getSaldo() == null) {
            cuenta.setSaldo(0.0);
        }
        return cuentaRepository.save(cuenta);
    }

    public Optional<Cuenta> obtenerCuentaPorReferencia(Long idReferencia, Cuenta.TipoCuenta tipoCuenta) {
        return cuentaRepository.findByIdReferenciaAndTipoCuenta(idReferencia, tipoCuenta);
    }

    public Cuenta agregarSaldo(Long idReferencia, Cuenta.TipoCuenta tipoCuenta, Double monto) {
        Optional<Cuenta> cuentaOpt = cuentaRepository.findByIdReferenciaAndTipoCuenta(idReferencia, tipoCuenta);

        if (cuentaOpt.isPresent()) {
            Cuenta cuenta = cuentaOpt.get();
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            return cuentaRepository.save(cuenta);
        } else {
            throw new IllegalArgumentException("La cuenta especificada no existe.");
        }
    }
}