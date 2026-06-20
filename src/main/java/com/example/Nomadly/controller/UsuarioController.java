package com.example.Nomadly.controller;

import com.example.Nomadly.model.Usuario;
import com.example.Nomadly.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/curso/{idCurso}/alumnos")
    public ResponseEntity<List<Usuario>> obtenerAlumnosPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(usuarioService.obtenerAlumnosPorCurso(idCurso));
    }

    @GetMapping("/alumno/{idAlumno}/apoderado")
    public ResponseEntity<Usuario> obtenerApoderado(@PathVariable Long idAlumno) {
        return usuarioService.obtenerApoderadoDeAlumno(idAlumno)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}