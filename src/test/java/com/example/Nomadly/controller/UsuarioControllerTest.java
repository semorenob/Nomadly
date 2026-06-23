package com.example.Nomadly.controller;

import com.example.Nomadly.model.Usuario;
import com.example.Nomadly.service.UsuarioService;
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

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UsuarioService usuarioService;

    @Test
    void crearUsuario_retorna200() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        when(usuarioService.crearUsuario(any())).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void crearUsuario_alumnoSinCurso_retorna400() throws Exception {
        Usuario alumno = new Usuario();
        alumno.setRol(Usuario.RolUsuario.ALUMNO);
        when(usuarioService.crearUsuario(any()))
                .thenThrow(new IllegalArgumentException("Un alumno debe pertenecer a un curso."));

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumno)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listarUsuarios_retorna200() throws Exception {
        when(usuarioService.obtenerTodos()).thenReturn(List.of(new Usuario()));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
