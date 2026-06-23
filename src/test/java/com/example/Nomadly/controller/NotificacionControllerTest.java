package com.example.Nomadly.controller;

import com.example.Nomadly.model.Notificacion;
import com.example.Nomadly.service.NotificacionService;
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

@WebMvcTest(NotificacionController.class)
class NotificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private NotificacionService notificacionService;

    @Test
    void enviarNotificacion_retorna200() throws Exception {
        Notificacion notificacion = new Notificacion();
        notificacion.setAsunto("Bienvenido");
        when(notificacionService.registrarNotificacion(any())).thenReturn(notificacion);

        mockMvc.perform(post("/api/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notificacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.asunto").value("Bienvenido"));
    }

    @Test
    void listarNotificaciones_retorna200() throws Exception {
        when(notificacionService.obtenerTodas()).thenReturn(List.of(new Notificacion()));

        mockMvc.perform(get("/api/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerPorCorreo_retorna200() throws Exception {
        when(notificacionService.obtenerPorCorreo("test@test.com"))
                .thenReturn(List.of(new Notificacion()));

        mockMvc.perform(get("/api/notificaciones/correo/test@test.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
