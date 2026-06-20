package com.example.Nomadly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idColegio;

    @Column(nullable = false)
    private Long idCurso;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private LocalDate fechaViaje;

    @Column(nullable = false)
    private LocalDate fechaTopePago;

    @Column(nullable = false)
    private Integer numeroAlumnos;

    @Column(nullable = false)
    private Double montoTotalMeta;

}