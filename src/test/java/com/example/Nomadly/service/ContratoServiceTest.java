package com.example.Nomadly.service;

import com.example.Nomadly.model.Contrato;
import com.example.Nomadly.repository.ContratoRepository;
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
class ContratoServiceTest {

    @Mock
    private ContratoRepository contratoRepository;

    @InjectMocks
    private ContratoService contratoService;

    @Test
    void crearContrato_guardaYRetorna() {
        Contrato contrato = new Contrato();
        when(contratoRepository.save(contrato)).thenReturn(contrato);

        Contrato resultado = contratoService.crearContrato(contrato);

        assertThat(resultado).isEqualTo(contrato);
        verify(contratoRepository).save(contrato);
    }

    @Test
    void obtenerTodos_retornaLista() {
        when(contratoRepository.findAll()).thenReturn(List.of(new Contrato()));

        List<Contrato> resultado = contratoService.obtenerTodos();

        assertThat(resultado).hasSize(1);
        verify(contratoRepository).findAll();
    }

    @Test
    void obtenerPorId_retornaOptional() {
        Contrato contrato = new Contrato();
        when(contratoRepository.findById(1L)).thenReturn(Optional.of(contrato));

        Optional<Contrato> resultado = contratoService.obtenerPorId(1L);

        assertThat(resultado).isPresent().contains(contrato);
        verify(contratoRepository).findById(1L);
    }

    @Test
    void obtenerPorCurso_retornaLista() {
        when(contratoRepository.findByIdCurso(1L)).thenReturn(List.of(new Contrato()));

        List<Contrato> resultado = contratoService.obtenerPorCurso(1L);

        assertThat(resultado).hasSize(1);
        verify(contratoRepository).findByIdCurso(1L);
    }
}
