CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          rol VARCHAR(50) NOT NULL,
                          id_curso BIGINT,
                          id_alumno_asociado BIGINT
);