-- Limpiar la tabla antes de insertar datos (opcional)
DROP TABLE IF EXISTS tipo_cambio;

-- Crear la tabla tipo_cambio
CREATE TABLE tipo_cambio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    moneda_origen VARCHAR(3) NOT NULL,
    moneda_destino VARCHAR(3) NOT NULL,
    tipo_cambio DECIMAL(10, 4) NOT NULL
);

-- Insertar algunos registros iniciales
INSERT INTO tipo_cambio (moneda_origen, moneda_destino, tipo_cambio) VALUES ('USD', 'EUR', 0.85);
INSERT INTO tipo_cambio (moneda_origen, moneda_destino, tipo_cambio) VALUES ('EUR', 'USD', 1.15);
INSERT INTO tipo_cambio (moneda_origen, moneda_destino, tipo_cambio) VALUES ('USD', 'GBP', 0.75);
INSERT INTO tipo_cambio (moneda_origen, moneda_destino, tipo_cambio) VALUES ('GBP', 'USD', 1.33);
INSERT INTO tipo_cambio (moneda_origen, moneda_destino, tipo_cambio) VALUES ('USD', 'JPY', 110.00);
INSERT INTO tipo_cambio (moneda_origen, moneda_destino, tipo_cambio) VALUES ('JPY', 'USD', 0.0091);