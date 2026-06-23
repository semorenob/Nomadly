package com.example.Nomadly.service;

import com.example.Nomadly.model.Cuenta;
import com.example.Nomadly.repository.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaService cuentaService;

    @Test
    void crearCuenta_sinSaldo_asignaCero() {
        Cuenta cuenta = new Cuenta();
        when(cuentaRepository.save(cuenta)).thenReturn(cuenta);

        Cuenta resultado = cuentaService.crearCuenta(cuenta);

        assertThat(resultado.getSaldo()).isZero();
        verify(cuentaRepository).save(cuenta);
    }

    @Test
    void crearCuenta_conSaldo_noSobrescribe() {
        Cuenta cuenta = new Cuenta();
        cuenta.setSaldo(100.0);
        when(cuentaRepository.save(cuenta)).thenReturn(cuenta);

        Cuenta resultado = cuentaService.crearCuenta(cuenta);

        assertThat(resultado.getSaldo()).isEqualTo(100.0);
        verify(cuentaRepository).save(cuenta);
    }

    @Test
    void obtenerCuentaPorReferencia_retornaOptional() {
        Cuenta cuenta = new Cuenta();
        when(cuentaRepository.findByIdReferenciaAndTipoCuenta(1L, Cuenta.TipoCuenta.CURSO))
                .thenReturn(Optional.of(cuenta));

        Optional<Cuenta> resultado = cuentaService.obtenerCuentaPorReferencia(1L, Cuenta.TipoCuenta.CURSO);

        assertThat(resultado).isPresent().contains(cuenta);
        verify(cuentaRepository).findByIdReferenciaAndTipoCuenta(1L, Cuenta.TipoCuenta.CURSO);
    }

    @Test
    void agregarSaldo_cuentaExistente_sumaMonto() {
        Cuenta cuenta = new Cuenta();
        cuenta.setSaldo(50.0);
        when(cuentaRepository.findByIdReferenciaAndTipoCuenta(1L, Cuenta.TipoCuenta.CURSO))
                .thenReturn(Optional.of(cuenta));
        when(cuentaRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Cuenta resultado = cuentaService.agregarSaldo(1L, Cuenta.TipoCuenta.CURSO, 30.0);

        assertThat(resultado.getSaldo()).isEqualTo(80.0);
        verify(cuentaRepository).save(cuenta);
    }

    @Test
    void agregarSaldo_cuentaNoExistente_lanzaExcepcion() {
        when(cuentaRepository.findByIdReferenciaAndTipoCuenta(1L, Cuenta.TipoCuenta.CURSO))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> cuentaService.agregarSaldo(1L, Cuenta.TipoCuenta.CURSO, 30.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("no existe");

        verify(cuentaRepository, never()).save(any());
    }
}
