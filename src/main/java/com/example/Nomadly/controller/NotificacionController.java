package com.example.Nomadly.controller;

import com.example.Nomadly.model.Notificacion;
import com.example.Nomadly.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<Notificacion> enviarNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionService.registrarNotificacion(notificacion);
        return ResponseEntity.ok(nuevaNotificacion);
    }

    @GetMapping
    public ResponseEntity<List<Notificacion>> listarNotificaciones() {
        return ResponseEntity.ok(notificacionService.obtenerTodas());
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<List<Notificacion>> obtenerPorCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(notificacionService.obtenerPorCorreo(correo));
    }
}