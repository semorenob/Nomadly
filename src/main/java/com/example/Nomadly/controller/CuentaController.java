package com.example.Nomadly.controller;

import com.example.Nomadly.model.Cuenta;
import com.example.Nomadly.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);
        return ResponseEntity.ok(nuevaCuenta);
    }

    @GetMapping("/{tipoCuenta}/{idReferencia}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable Cuenta.TipoCuenta tipoCuenta, @PathVariable Long idReferencia) {
        return cuentaService.obtenerCuentaPorReferencia(idReferencia, tipoCuenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{tipoCuenta}/{idReferencia}/agregar-saldo")
    public ResponseEntity<Cuenta> agregarSaldo(@PathVariable Cuenta.TipoCuenta tipoCuenta,
                                               @PathVariable Long idReferencia,
                                               @RequestParam Double monto) {
        try {
            Cuenta cuentaActualizada = cuentaService.agregarSaldo(idReferencia, tipoCuenta, monto);
            return ResponseEntity.ok(cuentaActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}