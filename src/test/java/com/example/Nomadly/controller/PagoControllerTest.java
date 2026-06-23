package com.example.Nomadly.controller;

import com.example.Nomadly.model.Pago;
import com.example.Nomadly.service.PagoService;
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

@WebMvcTest(PagoController.class)
class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PagoService pagoService;

    @Test
    void registrarPago_retorna200() throws Exception {
        Pago pago = new Pago();
        pago.setMonto(100.0);
        when(pagoService.registrarPago(any())).thenReturn(pago);

        mockMvc.perform(post("/api/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pago)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monto").value(100.0));
    }

    @Test
    void listarPagos_retorna200() throws Exception {
        when(pagoService.obtenerTodos()).thenReturn(List.of(new Pago()));

        mockMvc.perform(get("/api/pagos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void obtenerPorReferencia_retorna200() throws Exception {
        when(pagoService.obtenerPorReferencia(1L, Pago.TipoDestino.ALUMNO))
                .thenReturn(List.of(new Pago()));

        mockMvc.perform(get("/api/pagos/ALUMNO/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
