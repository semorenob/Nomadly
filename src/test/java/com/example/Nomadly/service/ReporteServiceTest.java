package com.example.Nomadly.service;

import com.example.Nomadly.model.Reporte;
import com.example.Nomadly.repository.ReporteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @Test
    void generarReporte_sinFecha_asignaHoy() {
        Reporte reporte = new Reporte();
        when(reporteRepository.save(reporte)).thenReturn(reporte);

        Reporte resultado = reporteService.generarReporte(reporte);

        assertThat(resultado.getFechaGeneracion()).isEqualTo(LocalDate.now());
        verify(reporteRepository).save(reporte);
    }

    @Test
    void generarReporte_conFecha_noSobrescribe() {
        Reporte reporte = new Reporte();
        reporte.setFechaGeneracion(LocalDate.of(2026, 1, 1));
        when(reporteRepository.save(reporte)).thenReturn(reporte);

        Reporte resultado = reporteService.generarReporte(reporte);

        assertThat(resultado.getFechaGeneracion()).isEqualTo(LocalDate.of(2026, 1, 1));
        verify(reporteRepository).save(reporte);
    }

    @Test
    void obtenerTodos_retornaLista() {
        when(reporteRepository.findAll()).thenReturn(List.of(new Reporte()));

        List<Reporte> resultado = reporteService.obtenerTodos();

        assertThat(resultado).hasSize(1);
        verify(reporteRepository).findAll();
    }

    @Test
    void obtenerPorColegio_retornaLista() {
        when(reporteRepository.findByIdColegio(1L)).thenReturn(List.of(new Reporte()));

        List<Reporte> resultado = reporteService.obtenerPorColegio(1L);

        assertThat(resultado).hasSize(1);
        verify(reporteRepository).findByIdColegio(1L);
    }

    @Test
    void obtenerPorCurso_retornaLista() {
        when(reporteRepository.findByIdCurso(1L)).thenReturn(List.of(new Reporte()));

        List<Reporte> resultado = reporteService.obtenerPorCurso(1L);

        assertThat(resultado).hasSize(1);
        verify(reporteRepository).findByIdCurso(1L);
    }

    @Test
    void obtenerPorId_retornaOptional() {
        Reporte reporte = new Reporte();
        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));

        Optional<Reporte> resultado = reporteService.obtenerPorId(1L);

        assertThat(resultado).isPresent().contains(reporte);
        verify(reporteRepository).findById(1L);
    }
}
