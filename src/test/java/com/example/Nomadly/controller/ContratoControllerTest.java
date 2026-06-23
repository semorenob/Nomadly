package com.example.Nomadly.controller;

import com.example.Nomadly.model.Contrato;
import com.example.Nomadly.service.ContratoService;
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

@WebMvcTest(ContratoController.class)
class ContratoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ContratoService contratoService;

    @Test
    void crearContrato_retorna200() throws Exception {
        Contrato contrato = new Contrato();
        contrato.setDestino("Lima");
        when(contratoService.crearContrato(any())).thenReturn(contrato);

        mockMvc.perform(post("/api/contratos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contrato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.destino").value("Lima"));
    }

    @Test
    void listarContratos_retorna200() throws Exception {
        when(contratoService.obtenerTodos()).thenReturn(List.of(new Contrato()));

        mockMvc.perform(get("/api/contratos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerPorCurso_retorna200() throws Exception {
        when(contratoService.obtenerPorCurso(1L)).thenReturn(List.of(new Contrato()));

        mockMvc.perform(get("/api/contratos/curso/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
