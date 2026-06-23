package com.example.Nomadly.controller;

import com.example.Nomadly.model.Actividad;
import com.example.Nomadly.service.ActividadService;
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

@WebMvcTest(ActividadController.class)
class ActividadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ActividadService actividadService;

    @Test
    void registrarActividad_retorna200() throws Exception {
        Actividad actividad = new Actividad();
        actividad.setNombre("Rifa");
        when(actividadService.registrarActividad(any())).thenReturn(actividad);

        mockMvc.perform(post("/api/actividades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actividad)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Rifa"));
    }

    @Test
    void listarActividades_retorna200() throws Exception {
        when(actividadService.obtenerTodas()).thenReturn(List.of(new Actividad()));

        mockMvc.perform(get("/api/actividades"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerPorCurso_retorna200() throws Exception {
        when(actividadService.obtenerPorCurso(1L)).thenReturn(List.of(new Actividad()));

        mockMvc.perform(get("/api/actividades/curso/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
