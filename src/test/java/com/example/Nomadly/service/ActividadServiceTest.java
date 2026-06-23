package com.example.Nomadly.service;

import com.example.Nomadly.model.Actividad;
import com.example.Nomadly.repository.ActividadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActividadServiceTest {

    @Mock
    private ActividadRepository actividadRepository;

    @InjectMocks
    private ActividadService actividadService;

    @Test
    void registrarActividad_guardaYRetorna() {
        Actividad actividad = new Actividad();
        when(actividadRepository.save(actividad)).thenReturn(actividad);

        Actividad resultado = actividadService.registrarActividad(actividad);

        assertThat(resultado).isEqualTo(actividad);
        verify(actividadRepository).save(actividad);
    }

    @Test
    void obtenerTodas_retornaLista() {
        when(actividadRepository.findAll()).thenReturn(List.of(new Actividad()));

        List<Actividad> resultado = actividadService.obtenerTodas();

        assertThat(resultado).hasSize(1);
        verify(actividadRepository).findAll();
    }

    @Test
    void obtenerPorCurso_retornaLista() {
        when(actividadRepository.findByIdCurso(1L)).thenReturn(List.of(new Actividad()));

        List<Actividad> resultado = actividadService.obtenerPorCurso(1L);

        assertThat(resultado).hasSize(1);
        verify(actividadRepository).findByIdCurso(1L);
    }

    @Test
    void obtenerPorId_retornaOptional() {
        Actividad actividad = new Actividad();
        when(actividadRepository.findById(1L)).thenReturn(Optional.of(actividad));

        Optional<Actividad> resultado = actividadService.obtenerPorId(1L);

        assertThat(resultado).isPresent().contains(actividad);
        verify(actividadRepository).findById(1L);
    }

    @Test
    void obtenerPorId_noEncontrado_retornaVacio() {
        when(actividadRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Actividad> resultado = actividadService.obtenerPorId(99L);

        assertThat(resultado).isEmpty();
        verify(actividadRepository).findById(99L);
    }
}
