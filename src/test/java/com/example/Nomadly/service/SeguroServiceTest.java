package com.example.Nomadly.service;

import com.example.Nomadly.model.Seguro;
import com.example.Nomadly.repository.SeguroRepository;
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
class SeguroServiceTest {

    @Mock
    private SeguroRepository seguroRepository;

    @InjectMocks
    private SeguroService seguroService;

    @Test
    void registrarSeguro_guardaYRetorna() {
        Seguro seguro = new Seguro();
        when(seguroRepository.save(seguro)).thenReturn(seguro);

        Seguro resultado = seguroService.registrarSeguro(seguro);

        assertThat(resultado).isEqualTo(seguro);
        verify(seguroRepository).save(seguro);
    }

    @Test
    void obtenerTodos_retornaLista() {
        when(seguroRepository.findAll()).thenReturn(List.of(new Seguro()));

        List<Seguro> resultado = seguroService.obtenerTodos();

        assertThat(resultado).hasSize(1);
        verify(seguroRepository).findAll();
    }

    @Test
    void obtenerPorContrato_retornaLista() {
        when(seguroRepository.findByIdContrato(1L)).thenReturn(List.of(new Seguro()));

        List<Seguro> resultado = seguroService.obtenerPorContrato(1L);

        assertThat(resultado).hasSize(1);
        verify(seguroRepository).findByIdContrato(1L);
    }

    @Test
    void obtenerPorId_retornaOptional() {
        Seguro seguro = new Seguro();
        when(seguroRepository.findById(1L)).thenReturn(Optional.of(seguro));

        Optional<Seguro> resultado = seguroService.obtenerPorId(1L);

        assertThat(resultado).isPresent().contains(seguro);
        verify(seguroRepository).findById(1L);
    }
}
