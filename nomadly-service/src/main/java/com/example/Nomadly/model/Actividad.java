package com.example.Nomadly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad", nullable = false)
    private TipoActividad tipoActividad;

    @Column(name = "monto_recaudado", nullable = false)
    private Double montoRecaudado;

    @Column(nullable = false)
    private LocalDate fecha;

    public enum TipoActividad {
        FIESTA, RIFA, OTRO
    }
}