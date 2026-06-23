CREATE TABLE reportes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          id_colegio BIGINT NOT NULL,
                          id_curso BIGINT NOT NULL,
                          porcentaje_logrado DOUBLE NOT NULL,
                          fecha_generacion DATE NOT NULL,
                          resumen_actividades VARCHAR(1000)
);