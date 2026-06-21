package com.example.Nomadly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_referencia", nullable = false)
    private Long idReferencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_destino", nullable = false)
    private TipoDestino tipoDestino;

    @Column(nullable = false)
    private Double monto;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @Column(name = "codigo_comprobante")
    private String codigoComprobante;

    public enum TipoDestino {
        ALUMNO, CURSO
    }
}