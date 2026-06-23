package com.example.Nomadly.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seguros")
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_contrato", nullable = false)
    private Long idContrato;

    @Column(name = "nombre_aseguradora", nullable = false)
    private String nombreAseguradora;

    @Column(name = "detalle_cobertura", nullable = false)
    private String detalleCobertura;

    @Column(name = "url_poliza")
    private String urlPoliza;
}