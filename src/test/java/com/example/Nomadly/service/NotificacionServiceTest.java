package com.example.Nomadly.service;

import com.example.Nomadly.client.NotificacionFeignClient;
import com.example.Nomadly.client.dto.NotificacionResponse;
import com.example.Nomadly.model.Notificacion;
import com.example.Nomadly.repository.NotificacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @Mock
    private NotificacionFeignClient notificacionFeignClient;

    @InjectMocks
    private NotificacionService notificacionService;

    @Captor
    private ArgumentCaptor<Notificacion> notificacionCaptor;

    @Test
    void registrarNotificacion_sinFecha_asignaAhora() {
        Notificacion notificacion = new Notificacion();
        notificacion.setCorreoDestino("test@test.com");
        notificacion.setAsunto("Asunto");
        notificacion.setMensaje("Mensaje");
        notificacion.setTipoNotificacion(Notificacion.TipoNotificacion.AVISO_GENERAL);

        when(notificacionFeignClient.enviarNotificacion(any())).thenReturn(new NotificacionResponse());
        when(notificacionRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Notificacion resultado = notificacionService.registrarNotificacion(notificacion);

        assertThat(resultado.getFechaEnvio()).isNotNull();
        verify(notificacionFeignClient).enviarNotificacion(any());
        verify(notificacionRepository).save(notificacionCaptor.capture());
        assertThat(notificacionCaptor.getValue().getCorreoDestino()).isEqualTo("test@test.com");
    }

    @Test
    void registrarNotificacion_conFecha_noSobrescribe() {
        Notificacion notificacion = new Notificacion();
        notificacion.setCorreoDestino("test@test.com");
        notificacion.setAsunto("Asunto");
        notificacion.setMensaje("Mensaje");
        notificacion.setTipoNotificacion(Notificacion.TipoNotificacion.AVISO_GENERAL);
        notificacion.setFechaEnvio(LocalDateTime.of(2026, 1, 1, 10, 0));

        when(notificacionFeignClient.enviarNotificacion(any())).thenReturn(new NotificacionResponse());
        when(notificacionRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Notificacion resultado = notificacionService.registrarNotificacion(notificacion);

        assertThat(resultado.getFechaEnvio()).isEqualTo(LocalDateTime.of(2026, 1, 1, 10, 0));
        verify(notificacionFeignClient).enviarNotificacion(any());
        verify(notificacionRepository).save(notificacion);
    }

    @Test
    void obtenerTodas_retornaLista() {
        when(notificacionRepository.findAll()).thenReturn(List.of(new Notificacion()));

        List<Notificacion> resultado = notificacionService.obtenerTodas();

        assertThat(resultado).hasSize(1);
        verify(notificacionRepository).findAll();
    }

    @Test
    void obtenerPorCorreo_retornaLista() {
        when(notificacionRepository.findByCorreoDestino("test@test.com"))
                .thenReturn(List.of(new Notificacion()));

        List<Notificacion> resultado = notificacionService.obtenerPorCorreo("test@test.com");

        assertThat(resultado).hasSize(1);
        verify(notificacionRepository).findByCorreoDestino("test@test.com");
    }
}
