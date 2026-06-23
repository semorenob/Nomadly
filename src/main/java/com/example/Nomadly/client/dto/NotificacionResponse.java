package com.example.Nomadly.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionResponse {

    private Long id;
    private String estado;
    private String mensaje;
}
