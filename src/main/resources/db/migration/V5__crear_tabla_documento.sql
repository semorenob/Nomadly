CREATE TABLE documentos (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            id_contrato BIGINT NOT NULL,
                            nombre VARCHAR(255) NOT NULL,
                            tipo_documento VARCHAR(50) NOT NULL,
                            url_archivo VARCHAR(255) NOT NULL,
                            fecha_subida DATE NOT NULL
);