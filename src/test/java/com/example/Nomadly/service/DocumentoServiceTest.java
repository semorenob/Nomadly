package com.example.Nomadly.service;

import com.example.Nomadly.model.Documento;
import com.example.Nomadly.repository.DocumentoRepository;
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
class DocumentoServiceTest {

    @Mock
    private DocumentoRepository documentoRepository;

    @InjectMocks
    private DocumentoService documentoService;

    @Test
    void registrarDocumento_sinFecha_asignaHoy() {
        Documento documento = new Documento();
        when(documentoRepository.save(documento)).thenReturn(documento);

        Documento resultado = documentoService.registrarDocumento(documento);

        assertThat(resultado.getFechaSubida()).isEqualTo(LocalDate.now());
        verify(documentoRepository).save(documento);
    }

    @Test
    void registrarDocumento_conFecha_noSobrescribe() {
        Documento documento = new Documento();
        documento.setFechaSubida(LocalDate.of(2026, 1, 1));
        when(documentoRepository.save(documento)).thenReturn(documento);

        Documento resultado = documentoService.registrarDocumento(documento);

        assertThat(resultado.getFechaSubida()).isEqualTo(LocalDate.of(2026, 1, 1));
        verify(documentoRepository).save(documento);
    }

    @Test
    void obtenerTodos_retornaLista() {
        when(documentoRepository.findAll()).thenReturn(List.of(new Documento()));

        List<Documento> resultado = documentoService.obtenerTodos();

        assertThat(resultado).hasSize(1);
        verify(documentoRepository).findAll();
    }

    @Test
    void obtenerPorContrato_retornaLista() {
        when(documentoRepository.findByIdContrato(1L)).thenReturn(List.of(new Documento()));

        List<Documento> resultado = documentoService.obtenerPorContrato(1L);

        assertThat(resultado).hasSize(1);
        verify(documentoRepository).findByIdContrato(1L);
    }

    @Test
    void obtenerPorId_retornaOptional() {
        Documento documento = new Documento();
        when(documentoRepository.findById(1L)).thenReturn(Optional.of(documento));

        Optional<Documento> resultado = documentoService.obtenerPorId(1L);

        assertThat(resultado).isPresent().contains(documento);
        verify(documentoRepository).findById(1L);
    }
}
