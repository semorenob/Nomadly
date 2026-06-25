package com.example.Nomadly.config;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;

@Configuration
public class GatewayConfig {

    private RouterFunction<ServerResponse> routeService(String routeId, String path) {
        return GatewayRouterFunctions.route(routeId)
                .route(RequestPredicates.path(path), HandlerFunctions.http())
                .filter(lb("Nomadly"))
                .build();
    }

    @Bean public RouterFunction<ServerResponse> gatewayUsuarios() { return routeService("usuarios", "/api/usuarios/**"); }
    @Bean public RouterFunction<ServerResponse> gatewayActividades() { return routeService("actividades", "/api/actividades/**"); }
    @Bean public RouterFunction<ServerResponse> gatewayContratos() { return routeService("contratos", "/api/contratos/**"); }
    @Bean public RouterFunction<ServerResponse> gatewayCuentas() { return routeService("cuentas", "/api/cuentas/**"); }
    @Bean public RouterFunction<ServerResponse> gatewayDocumentos() { return routeService("documentos", "/api/documentos/**"); }
    @Bean public RouterFunction<ServerResponse> gatewayNotificaciones() { return routeService("notificaciones", "/api/notificaciones/**"); }
    @Bean public RouterFunction<ServerResponse> gatewayPagos() { return routeService("pagos", "/api/pagos/**"); }
    @Bean public RouterFunction<ServerResponse> gatewayReportes() { return routeService("reportes", "/api/reportes/**"); }
    @Bean public RouterFunction<ServerResponse> gatewaySeguros() { return routeService("seguros", "/api/seguros/**"); }
    @Bean public RouterFunction<ServerResponse> gatewayServiciosAdicionales() { return routeService("servicios-adicionales", "/api/servicios-adicionales/**"); }
}