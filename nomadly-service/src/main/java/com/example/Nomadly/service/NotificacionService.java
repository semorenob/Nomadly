package com.example.Nomadly.service;

import com.example.Nomadly.model.Notificacion;
import com.example.Nomadly.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public Notificacion registrarNotificacion(Notificacion notificacion) {
        if (notificacion.getFechaEnvio() == null) {
            notificacion.setFechaEnvio(LocalDateTime.now());
        }

        // En una implementación real, aquí se llamaría a un servicio de correo (como JavaMailSender)
        // Por ahora, registramos la intención de envío en la base de datos.
        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }

    public List<Notificacion> obtenerPorCorreo(String correo) {
        return notificacionRepository.findByCorreoDestino(correo);
    }
}