package com.example.Nomadly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_contrato", nullable = false)
    private Long idContrato;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "url_archivo", nullable = false)
    private String urlArchivo;

    @Column(name = "fecha_subida", nullable = false)
    private LocalDate fechaSubida;

    public enum TipoDocumento {
        CONTRATO_PDF, COMPROBANTE, ITINERARIO, OTRO
    }
}