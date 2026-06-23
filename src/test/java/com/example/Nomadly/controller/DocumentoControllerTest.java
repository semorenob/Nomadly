package com.example.Nomadly.controller;

import com.example.Nomadly.model.Documento;
import com.example.Nomadly.service.DocumentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DocumentoController.class)
class DocumentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DocumentoService documentoService;

    @Test
    void registrarDocumento_retorna200() throws Exception {
        Documento documento = new Documento();
        documento.setNombre("Contrato.pdf");
        when(documentoService.registrarDocumento(any())).thenReturn(documento);

        mockMvc.perform(post("/api/documentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(documento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Contrato.pdf"));
    }

    @Test
    void listarDocumentos_retorna200() throws Exception {
        when(documentoService.obtenerTodos()).thenReturn(List.of(new Documento()));

        mockMvc.perform(get("/api/documentos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerPorContrato_retorna200() throws Exception {
        when(documentoService.obtenerPorContrato(1L)).thenReturn(List.of(new Documento()));

        mockMvc.perform(get("/api/documentos/contrato/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
