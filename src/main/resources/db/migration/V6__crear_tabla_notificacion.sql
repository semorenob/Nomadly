CREATE TABLE notificaciones (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                correo_destino VARCHAR(255) NOT NULL,
                                asunto VARCHAR(255) NOT NULL,
                                mensaje VARCHAR(1000) NOT NULL,
                                fecha_envio DATETIME NOT NULL,
                                tipo_notificacion VARCHAR(50) NOT NULL
);