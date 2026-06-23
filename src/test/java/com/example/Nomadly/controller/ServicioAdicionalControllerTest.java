package com.example.Nomadly.controller;

import com.example.Nomadly.model.ServicioAdicional;
import com.example.Nomadly.service.ServicioAdicionalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ServicioAdicionalController.class)
class ServicioAdicionalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ServicioAdicionalService servicioAdicionalService;

    @Test
    void registrarServicio_retorna200() throws Exception {
        ServicioAdicional servicio = new ServicioAdicional();
        servicio.setNombre("Seguro VIP");
        when(servicioAdicionalService.registrarServicio(any())).thenReturn(servicio);

        mockMvc.perform(post("/api/servicios-adicionales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(servicio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Seguro VIP"));
    }

    @Test
    void listarServicios_retorna200() throws Exception {
        when(servicioAdicionalService.obtenerTodos()).thenReturn(List.of(new ServicioAdicional()));

        mockMvc.perform(get("/api/servicios-adicionales"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerPorContrato_retorna200() throws Exception {
        when(servicioAdicionalService.obtenerPorContrato(1L)).thenReturn(List.of(new ServicioAdicional()));

        mockMvc.perform(get("/api/servicios-adicionales/contrato/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
