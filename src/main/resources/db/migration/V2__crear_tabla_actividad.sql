CREATE TABLE actividades (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             id_curso BIGINT NOT NULL,
                             nombre VARCHAR(255) NOT NULL,
                             tipo_actividad VARCHAR(50) NOT NULL,
                             monto_recaudado DOUBLE NOT NULL,
                             fecha DATE NOT NULL
);