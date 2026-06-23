CREATE TABLE contratos (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           id_colegio BIGINT NOT NULL,
                           id_curso BIGINT NOT NULL,
                           destino VARCHAR(255) NOT NULL,
                           fecha_viaje DATE NOT NULL,
                           fecha_tope_pago DATE NOT NULL,
                           numero_alumnos INT NOT NULL,
                           monto_total_meta DOUBLE NOT NULL
);