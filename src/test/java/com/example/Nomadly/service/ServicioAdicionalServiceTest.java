package com.example.Nomadly.service;

import com.example.Nomadly.model.ServicioAdicional;
import com.example.Nomadly.repository.ServicioAdicionalRepository;
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
class ServicioAdicionalServiceTest {

    @Mock
    private ServicioAdicionalRepository servicioAdicionalRepository;

    @InjectMocks
    private ServicioAdicionalService servicioAdicionalService;

    @Test
    void registrarServicio_guardaYRetorna() {
        ServicioAdicional servicio = new ServicioAdicional();
        when(servicioAdicionalRepository.save(servicio)).thenReturn(servicio);

        ServicioAdicional resultado = servicioAdicionalService.registrarServicio(servicio);

        assertThat(resultado).isEqualTo(servicio);
        verify(servicioAdicionalRepository).save(servicio);
    }

    @Test
    void obtenerTodos_retornaLista() {
        when(servicioAdicionalRepository.findAll()).thenReturn(List.of(new ServicioAdicional()));

        List<ServicioAdicional> resultado = servicioAdicionalService.obtenerTodos();

        assertThat(resultado).hasSize(1);
        verify(servicioAdicionalRepository).findAll();
    }

    @Test
    void obtenerPorContrato_retornaLista() {
        when(servicioAdicionalRepository.findByIdContrato(1L)).thenReturn(List.of(new ServicioAdicional()));

        List<ServicioAdicional> resultado = servicioAdicionalService.obtenerPorContrato(1L);

        assertThat(resultado).hasSize(1);
        verify(servicioAdicionalRepository).findByIdContrato(1L);
    }

    @Test
    void obtenerPorId_retornaOptional() {
        ServicioAdicional servicio = new ServicioAdicional();
        when(servicioAdicionalRepository.findById(1L)).thenReturn(Optional.of(servicio));

        Optional<ServicioAdicional> resultado = servicioAdicionalService.obtenerPorId(1L);

        assertThat(resultado).isPresent().contains(servicio);
        verify(servicioAdicionalRepository).findById(1L);
    }
}
