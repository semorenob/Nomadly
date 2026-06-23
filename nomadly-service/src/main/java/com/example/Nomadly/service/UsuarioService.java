package com.example.Nomadly.service;

import com.example.Nomadly.model.Usuario;
import com.example.Nomadly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        if (usuario.getRol() == Usuario.RolUsuario.ALUMNO && usuario.getIdCurso() == null) {
            throw new IllegalArgumentException("Un alumno debe pertenecer a un curso.");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> obtenerAlumnosPorCurso(Long idCurso) {
        return usuarioRepository.findByIdCursoAndRol(idCurso, Usuario.RolUsuario.ALUMNO);
    }

    public Optional<Usuario> obtenerApoderadoDeAlumno(Long idAlumno) {
        return usuarioRepository.findByIdAlumnoAsociadoAndRol(idAlumno, Usuario.RolUsuario.APODERADO);
    }
}