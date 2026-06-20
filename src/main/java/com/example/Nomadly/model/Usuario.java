package com.example.Nomadly.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolUsuario rol;

    @Column(name = "id_curso")
    private Long idCurso;

    @Column(name = "id_alumno_asociado")
    private Long idAlumnoAsociado;

    public enum RolUsuario {
        ALUMNO, APODERADO, REPRESENTANTE_CURSO, EJECUTIVO_VENTAS
    }
}