CREATE DATABASE IF NOT EXISTS vetmanager_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE vetmanager_db;

-- =============================================
-- TABLAS
-- =============================================

CREATE TABLE IF NOT EXISTS duenos (
    id_dueno INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    correo VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS mascotas (
    id_mascota INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    tipo_especie VARCHAR(30) NOT NULL,
    edad_meses INT,
    atributo_especifico VARCHAR(100),
    id_dueno INT NOT NULL,
    FOREIGN KEY (id_dueno) REFERENCES duenos(id_dueno)
    );

CREATE TABLE IF NOT EXISTS citas (
    id_cita INT PRIMARY KEY AUTO_INCREMENT,
    fecha_hora DATETIME NOT NULL,
    motivo_consulta TEXT,
    estado_reserva ENUM('EN_ESPERA','CONFIRMADA','CANCELADA') DEFAULT 'EN_ESPERA',
    id_mascota INT NOT NULL,
    FOREIGN KEY (id_mascota) REFERENCES mascotas(id_mascota)
    );

CREATE TABLE IF NOT EXISTS historial_clinico (
    id_historial INT PRIMARY KEY AUTO_INCREMENT,
    estado_salud ENUM('EN_OPERACION','REPOSO','DADO_DE_ALTA') DEFAULT 'EN_OPERACION',
    detalles_medicos TEXT,
    ultima_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    id_mascota INT NOT NULL,
    FOREIGN KEY (id_mascota) REFERENCES mascotas(id_mascota)
    );

-- =============================================
-- DATOS DE PRUEBA
-- =============================================

INSERT INTO duenos (nombre, telefono, correo) VALUES
    ('Carlos Velazco', '987654321', 'carlos@gmail.com'),
    ('Maria Lopez', '912345678', 'maria@gmail.com'),
    ('Juan Quispe', '956789123', 'juan@gmail.com');

INSERT INTO mascotas (nombre, tipo_especie, edad_meses, atributo_especifico, id_dueno) VALUES
    ('Toby', 'Perro', 24, 'Labrador', 1),
    ('Luna', 'Gato', 12, 'true', 2),
    ('Rex', 'Perro', 36, 'Pastor Aleman', 3);

INSERT INTO citas (fecha_hora, motivo_consulta, estado_reserva, id_mascota) VALUES
    ('2026-06-20 09:00:00', 'Control general', 'EN_ESPERA', 1),
    ('2026-06-20 10:00:00', 'Vacunacion', 'CONFIRMADA', 2),
    ('2026-06-21 11:00:00', 'Revision post operacion', 'EN_ESPERA', 3);

INSERT INTO historial_clinico (estado_salud, detalles_medicos, id_mascota) VALUES
    ('DADO_DE_ALTA', 'Paciente sin novedades', 1),
    ('EN_OPERACION', 'Extraccion dental en proceso', 2),
    ('REPOSO', 'Recuperacion post cirugia de cadera', 3);