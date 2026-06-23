CREATE TABLE servicios_adicionales (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       id_contrato BIGINT NOT NULL,
                                       nombre VARCHAR(255) NOT NULL,
                                       descripcion VARCHAR(255) NOT NULL,
                                       costo DOUBLE NOT NULL
);