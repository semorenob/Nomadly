package com.example.Nomadly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_colegio", nullable = false)
    private Long idColegio;

    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @Column(name = "porcentaje_logrado", nullable = false)
    private Double porcentajeLogrado;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;

    @Column(length = 1000)
    private String resumenActividades;
}