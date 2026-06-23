package com.example.Nomadly.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequest {

    private String correoDestino;
    private String asunto;
    private String mensaje;
    private String tipoNotificacion;
}
