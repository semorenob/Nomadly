CREATE TABLE cuentas (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         id_referencia BIGINT NOT NULL,
                         tipo_cuenta VARCHAR(50) NOT NULL,
                         meta DOUBLE NOT NULL,
                         saldo DOUBLE NOT NULL
);