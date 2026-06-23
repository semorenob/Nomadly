package com.example.Nomadly.repository;

import com.example.Nomadly.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByIdCursoAndRol(Long idCurso, Usuario.RolUsuario rol);

    Optional<Usuario> findByIdAlumnoAsociadoAndRol(Long idAlumnoAsociado, Usuario.RolUsuario rol);
}