package com.example.Nomadly.service;

import com.example.Nomadly.client.NotificacionFeignClient;
import com.example.Nomadly.client.dto.NotificacionRequest;
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

    @Autowired
    private NotificacionFeignClient notificacionFeignClient;

    public Notificacion registrarNotificacion(Notificacion notificacion) {
        if (notificacion.getFechaEnvio() == null) {
            notificacion.setFechaEnvio(LocalDateTime.now());
        }

        NotificacionRequest request = new NotificacionRequest(
                notificacion.getCorreoDestino(),
                notificacion.getAsunto(),
                notificacion.getMensaje(),
                notificacion.getTipoNotificacion().name()
        );

        notificacionFeignClient.enviarNotificacion(request);

        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }

    public List<Notificacion> obtenerPorCorreo(String correo) {
        return notificacionRepository.findByCorreoDestino(correo);
    }
}