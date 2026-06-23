package com.example.Nomadly.service;

import com.example.Nomadly.model.Usuario;
import com.example.Nomadly.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void crearUsuario_alumnoSinCurso_lanzaExcepcion() {
        Usuario alumno = new Usuario();
        alumno.setRol(Usuario.RolUsuario.ALUMNO);

        assertThatThrownBy(() -> usuarioService.crearUsuario(alumno))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("debe pertenecer a un curso");

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void crearUsuario_alumnoConCurso_guarda() {
        Usuario alumno = new Usuario();
        alumno.setRol(Usuario.RolUsuario.ALUMNO);
        alumno.setIdCurso(1L);
        when(usuarioRepository.save(alumno)).thenReturn(alumno);

        Usuario resultado = usuarioService.crearUsuario(alumno);

        assertThat(resultado).isEqualTo(alumno);
        verify(usuarioRepository).save(alumno);
    }

    @Test
    void crearUsuario_noAlumno_guarda() {
        Usuario apoderado = new Usuario();
        apoderado.setRol(Usuario.RolUsuario.APODERADO);
        when(usuarioRepository.save(apoderado)).thenReturn(apoderado);

        Usuario resultado = usuarioService.crearUsuario(apoderado);

        assertThat(resultado).isEqualTo(apoderado);
        verify(usuarioRepository).save(apoderado);
    }

    @Test
    void obtenerTodos_retornaLista() {
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario()));

        List<Usuario> resultado = usuarioService.obtenerTodos();

        assertThat(resultado).hasSize(1);
        verify(usuarioRepository).findAll();
    }

    @Test
    void obtenerPorId_retornaOptional() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.obtenerPorId(1L);

        assertThat(resultado).isPresent().contains(usuario);
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void obtenerAlumnosPorCurso_retornaLista() {
        when(usuarioRepository.findByIdCursoAndRol(1L, Usuario.RolUsuario.ALUMNO))
                .thenReturn(List.of(new Usuario()));

        List<Usuario> resultado = usuarioService.obtenerAlumnosPorCurso(1L);

        assertThat(resultado).hasSize(1);
        verify(usuarioRepository).findByIdCursoAndRol(1L, Usuario.RolUsuario.ALUMNO);
    }

    @Test
    void obtenerApoderadoDeAlumno_retornaOptional() {
        Usuario apoderado = new Usuario();
        when(usuarioRepository.findByIdAlumnoAsociadoAndRol(1L, Usuario.RolUsuario.APODERADO))
                .thenReturn(Optional.of(apoderado));

        Optional<Usuario> resultado = usuarioService.obtenerApoderadoDeAlumno(1L);

        assertThat(resultado).isPresent().contains(apoderado);
        verify(usuarioRepository).findByIdAlumnoAsociadoAndRol(1L, Usuario.RolUsuario.APODERADO);
    }
}
