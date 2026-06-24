package com.example.Nomadly.config;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayUsuarios() {
        return GatewayRouterFunctions.route("usuarios")
                .route(RequestPredicates.path("/api/usuarios/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayActividades() {
        return GatewayRouterFunctions.route("actividades")
                .route(RequestPredicates.path("/api/actividades/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayContratos() {
        return GatewayRouterFunctions.route("contratos")
                .route(RequestPredicates.path("/api/contratos/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayCuentas() {
        return GatewayRouterFunctions.route("cuentas")
                .route(RequestPredicates.path("/api/cuentas/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayDocumentos() {
        return GatewayRouterFunctions.route("documentos")
                .route(RequestPredicates.path("/api/documentos/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayNotificaciones() {
        return GatewayRouterFunctions.route("notificaciones")
                .route(RequestPredicates.path("/api/notificaciones/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayPagos() {
        return GatewayRouterFunctions.route("pagos")
                .route(RequestPredicates.path("/api/pagos/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayReportes() {
        return GatewayRouterFunctions.route("reportes")
                .route(RequestPredicates.path("/api/reportes/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewaySeguros() {
        return GatewayRouterFunctions.route("seguros")
                .route(RequestPredicates.path("/api/seguros/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayServiciosAdicionales() {
        return GatewayRouterFunctions.route("servicios-adicionales")
                .route(RequestPredicates.path("/api/servicios-adicionales/**"), HandlerFunctions.http())
                .before(uri("lb://Nomadly"))
                .build();
    }
}
