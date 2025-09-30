INSERT INTO empleados
(id, nombre, identificacion, cargo, cantidad_vacunas, cantidad_alimento, cantidad_productos_aseo, cantidad_pesas_avicolas)
VALUES
    (RANDOM_UUID(), 'María Pérez',   'CC-1001', 'Operaria',    5, 20, 3, 2),
    (RANDOM_UUID(), 'Juan Gómez',    'CC-1002', 'Veterinario', 10, 15, 4, 1),
    (RANDOM_UUID(), 'Angie Ramírez', 'CC-1003', 'Supervisor',  2, 30, 6, 3);
