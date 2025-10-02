-- Script para poblar la tabla 'persons'
-- Asegúrate de que esta tabla se recree antes de ejecutar este script (configurando ddl-auto=create-drop en application.properties).
-- Documentación de las columnas:
-- age: Edad de la persona.
-- created_at: Fecha de creación (YYYY-MM-DD).
-- last_name: Apellido de la persona.
-- name: Nombre de la persona.
-- updated_at: Última fecha de modificación (YYYY-MM-DD).
-- birth_date: Fecha de nacimiento (YYYY-MM-DD HH:MM:SS.ms).
-- email: Correo electrónico.
-- gender: Género.
-- marital_status: Estado civil (TRUE/FALSE).
-- note: Observaciones/descripción.
-- profession: Profesión.
-- salary: Salario (permite NULL).

-- Insertar Saul Echeverri
INSERT INTO persons (age, created_at, last_name, name, updated_at, birth_date, email, gender,
                     marital_status, note, profession, salary)
VALUES (40, '2025-09-16', 'Echeverri', 'Saul', '2025-09-16', '2006-01-20 00:07:00.000',
        'saulolo@example.com', 'Masculino', FALSE, 'Desarrollador de Software en IAS',
        'Desarrollador', 700000);

-- Insertar Felipe Vasquez
INSERT INTO persons (age, created_at, last_name, name, updated_at, birth_date, email, gender,
                     marital_status, note, profession, salary)
VALUES (40, '2025-09-16', 'Vasquez', 'Felipe', '2025-09-16', '2020-01-05 00:02:00.000',
        'vasquez@example', 'Masculino', TRUE, 'Soy medico', 'Desarrollador', 500000);

-- Insertar Alejandra Arenas
INSERT INTO persons (age, created_at, last_name, name, updated_at, birth_date, email, gender,
                     marital_status, note, profession, salary)
VALUES (40, '2025-09-16', 'Arenas', 'Alejandra', '2025-09-16', '2026-01-13 00:10:00.000',
        'arenas@example.com', 'Femenino', TRUE, 'Soy administradora de empresas', 'Tester', 600000);

-- Insertar Leidy Zapata (salario es NULL)
INSERT INTO persons (age, created_at, last_name, name, updated_at, birth_date, email, gender,
                     marital_status, note, profession, salary)
VALUES (40, '2025-09-16', 'Zapata', 'Leidy', '2025-09-16', '2016-01-08 00:11:00.000',
        'zapata@example.com', 'Femenino', TRUE, 'Soy Administradora de Empresas', 'Tester', NULL);

-- Insertar Diego Martinez
INSERT INTO persons (age, created_at, last_name, name, updated_at, birth_date, email, gender,
                     marital_status, note, profession, salary)
VALUES (30, '2025-09-16', 'Martinez', 'Diego', '2025-09-16', '2017-01-13 00:02:00.000',
        'martinez@example.com', 'Masculino', TRUE, 'Soy Ingeniero Biomédico', 'Arquitecto', 200000);


-- Script para poblar la tabla 'users'
-- Asegúrate de que esta tabla se recree antes de ejecutar este script (configurando ddl-auto=create-drop)

-- Documentación de las columnas:
-- user_id (Auto generado)
-- enabled: Estado de activación del usuario (TRUE/FALSE).
-- password: Contraseña cifrada con BCrypt (60 caracteres de longitud).
-- role: Rol del usuario (ROLE_ADMIN, ROLE_USER).
-- username: Nombre de usuario único.

-- Insertar Saul Echeverri
INSERT INTO users (enabled, password, role, username)
VALUES (TRUE, '$2a$10$qAOQoAfL4cMcocyY8CEN.OFUJoi4naNSQSf7XVqOvUoc5q3/HKCZe', 'ROLE_ADMIN', 'saulolo');

-- Insertar Felipe Vasquez
INSERT INTO users (enabled, password, role, username)
VALUES (TRUE, '$2a$10$qAOQoAfL4cMcocyY8CEN.OFUJoi4naNSQSf7XVqOvUoc5q3/HKCZe', 'ROLE_USER', 'pipe');

-- Insertar Alejandra Arenas
INSERT INTO users (enabled, password, role, username)
VALUES (TRUE, '$2a$10$qAOQoAfL4cMcocyY8CEN.OFUJoi4naNSQSf7XVqOvUoc5q3/HKCZe', 'ROLE_USER', 'aleja');
