package com.example.Nomadly.service;

import com.example.Nomadly.model.Pago;
import com.example.Nomadly.repository.PagoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    void registrarPago_sinFecha_asignaHoy() {
        Pago pago = new Pago();
        when(pagoRepository.save(pago)).thenReturn(pago);

        Pago resultado = pagoService.registrarPago(pago);

        assertThat(resultado.getFechaPago()).isEqualTo(LocalDate.now());
        verify(pagoRepository).save(pago);
    }

    @Test
    void registrarPago_conFecha_noSobrescribe() {
        Pago pago = new Pago();
        pago.setFechaPago(LocalDate.of(2026, 1, 1));
        when(pagoRepository.save(pago)).thenReturn(pago);

        Pago resultado = pagoService.registrarPago(pago);

        assertThat(resultado.getFechaPago()).isEqualTo(LocalDate.of(2026, 1, 1));
        verify(pagoRepository).save(pago);
    }

    @Test
    void obtenerTodos_retornaLista() {
        when(pagoRepository.findAll()).thenReturn(List.of(new Pago()));

        List<Pago> resultado = pagoService.obtenerTodos();

        assertThat(resultado).hasSize(1);
        verify(pagoRepository).findAll();
    }

    @Test
    void obtenerPorReferencia_retornaLista() {
        when(pagoRepository.findByIdReferenciaAndTipoDestino(1L, Pago.TipoDestino.ALUMNO))
                .thenReturn(List.of(new Pago()));

        List<Pago> resultado = pagoService.obtenerPorReferencia(1L, Pago.TipoDestino.ALUMNO);

        assertThat(resultado).hasSize(1);
        verify(pagoRepository).findByIdReferenciaAndTipoDestino(1L, Pago.TipoDestino.ALUMNO);
    }
}
