package com.example.Nomadly.controller;

import com.example.Nomadly.model.Seguro;
import com.example.Nomadly.service.SeguroService;
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

@WebMvcTest(SeguroController.class)
class SeguroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SeguroService seguroService;

    @Test
    void registrarSeguro_retorna200() throws Exception {
        Seguro seguro = new Seguro();
        seguro.setNombreAseguradora("Rimac");
        when(seguroService.registrarSeguro(any())).thenReturn(seguro);

        mockMvc.perform(post("/api/seguros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seguro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreAseguradora").value("Rimac"));
    }

    @Test
    void listarSeguros_retorna200() throws Exception {
        when(seguroService.obtenerTodos()).thenReturn(List.of(new Seguro()));

        mockMvc.perform(get("/api/seguros"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerPorContrato_retorna200() throws Exception {
        when(seguroService.obtenerPorContrato(1L)).thenReturn(List.of(new Seguro()));

        mockMvc.perform(get("/api/seguros/contrato/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
