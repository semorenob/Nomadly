package com.example.Nomadly.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_referencia", nullable = false)
    private Long idReferencia; // Aquí guardaremos el idCurso o el idAlumno según corresponda

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false)
    private TipoCuenta tipoCuenta;

    @Column(nullable = false)
    private Double meta; // Lo que se debe alcanzar

    @Column(nullable = false)
    private Double saldo; // Lo que se lleva ahorrado/depositado

    public enum TipoCuenta {
        CURSO, ALUMNO
    }
}