package com.example.Nomadly.controller;

import com.example.Nomadly.model.Cuenta;
import com.example.Nomadly.service.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuentaController.class)
class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CuentaService cuentaService;

    @Test
    void crearCuenta_retorna200() throws Exception {
        Cuenta cuenta = new Cuenta();
        when(cuentaService.crearCuenta(any())).thenReturn(cuenta);

        mockMvc.perform(post("/api/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerCuenta_existente_retorna200() throws Exception {
        Cuenta cuenta = new Cuenta();
        when(cuentaService.obtenerCuentaPorReferencia(1L, Cuenta.TipoCuenta.CURSO))
                .thenReturn(Optional.of(cuenta));

        mockMvc.perform(get("/api/cuentas/CURSO/1"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerCuenta_noExistente_retorna404() throws Exception {
        when(cuentaService.obtenerCuentaPorReferencia(1L, Cuenta.TipoCuenta.CURSO))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cuentas/CURSO/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void agregarSaldo_existente_retorna200() throws Exception {
        Cuenta cuenta = new Cuenta();
        when(cuentaService.agregarSaldo(1L, Cuenta.TipoCuenta.CURSO, 50.0)).thenReturn(cuenta);

        mockMvc.perform(put("/api/cuentas/CURSO/1/agregar-saldo?monto=50.0"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarSaldo_noExistente_retorna400() throws Exception {
        when(cuentaService.agregarSaldo(1L, Cuenta.TipoCuenta.CURSO, 50.0))
                .thenThrow(new IllegalArgumentException("no existe"));

        mockMvc.perform(put("/api/cuentas/CURSO/1/agregar-saldo?monto=50.0"))
                .andExpect(status().isBadRequest());
    }
}
