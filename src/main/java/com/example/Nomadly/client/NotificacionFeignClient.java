package com.example.Nomadly.client;

import com.example.Nomadly.client.dto.NotificacionRequest;
import com.example.Nomadly.client.dto.NotificacionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacion-service")
public interface NotificacionFeignClient {

    @PostMapping("/api/notificaciones/enviar")
    NotificacionResponse enviarNotificacion(@RequestBody NotificacionRequest request);
}
