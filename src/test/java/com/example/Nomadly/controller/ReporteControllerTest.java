package com.example.Nomadly.controller;

import com.example.Nomadly.model.Reporte;
import com.example.Nomadly.service.ReporteService;
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

@WebMvcTest(ReporteController.class)
class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReporteService reporteService;

    @Test
    void registrarReporte_retorna200() throws Exception {
        Reporte reporte = new Reporte();
        reporte.setPorcentajeLogrado(85.0);
        when(reporteService.generarReporte(any())).thenReturn(reporte);

        mockMvc.perform(post("/api/reportes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reporte)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.porcentajeLogrado").value(85.0));
    }

    @Test
    void listarReportes_retorna200() throws Exception {
        when(reporteService.obtenerTodos()).thenReturn(List.of(new Reporte()));

        mockMvc.perform(get("/api/reportes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerPorColegio_retorna200() throws Exception {
        when(reporteService.obtenerPorColegio(1L)).thenReturn(List.of(new Reporte()));

        mockMvc.perform(get("/api/reportes/colegio/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerPorCurso_retorna200() throws Exception {
        when(reporteService.obtenerPorCurso(1L)).thenReturn(List.of(new Reporte()));

        mockMvc.perform(get("/api/reportes/curso/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
