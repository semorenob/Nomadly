CREATE TABLE pagos (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       id_referencia BIGINT NOT NULL,
                       tipo_destino VARCHAR(50) NOT NULL,
                       monto DOUBLE NOT NULL,
                       fecha_pago DATE NOT NULL,
                       codigo_comprobante VARCHAR(255)
);