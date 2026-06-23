CREATE TABLE seguros (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         id_contrato BIGINT NOT NULL,
                         nombre_aseguradora VARCHAR(255) NOT NULL,
                         detalle_cobertura VARCHAR(255) NOT NULL,
                         url_poliza VARCHAR(255)
);