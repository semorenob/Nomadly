package com.example.Nomadly.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "servicios_adicionales")
public class ServicioAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_contrato", nullable = false)
    private Long idContrato;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double costo;
}