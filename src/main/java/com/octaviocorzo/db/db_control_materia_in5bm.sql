-- Nombre: Octavio Alejandro Corzo Reyes
-- Carnet: 2021084
-- Código técnico: IN5BM
-- Materia: Taller
-- Profesor: Jorge Perez Luis Canto

DROP DATABASE IF EXISTS db_control_materia_in5bm;
CREATE DATABASE  db_control_materia_in5bm;
USE db_control_materia_in5bm;

DROP TABLE IF EXISTS materias;
CREATE TABLE materias(
	id_materia INT NOT NULL AUTO_INCREMENT,
    nombre_materia VARCHAR(45) NOT NULL,
    ciclo_escolar YEAR,
    horario_inicio TIME,
    horario_final TIME,
    catedratico VARCHAR(45) NOT NULL,
    salon VARCHAR(45) NOT NULL,
    cupo_maximo INT,
    cupo_minimo INT,
    nota INT NOT NULL,
    PRIMARY KEY(id_materia)
);

-- Insersiones
INSERT INTO materias(nombre_materia, catedratico, salon, nota)
VALUES("Matemáticas", "Luis Guillen", "C-38", 91);

INSERT INTO materias(nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota)
VALUES("Taller", '2022', '07:00:00', '12:00:00', "Jorge Perez","C-29", 35, 20, 100);

INSERT INTO materias(nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota)
VALUES("Cálculo", '2022', '07:00:00', '12:00:00', "Jorge Perez","C-29", 35, 20, 100);

INSERT INTO materias(nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota)
VALUES("Dibujo", '2022', '07:00:00', '12:00:00', "Jorge Perez","C-29", 35, 20, 97);

INSERT INTO materias(nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota)
VALUES("Tecnología", '2022', '07:00:00', '12:00:00', "Jorge Perez","C-29", 35, 20, 83);

INSERT INTO materias(nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota)
VALUES("Tics", '2022', '07:00:00', '12:00:00', "Jorge Perez","C-29", 35, 20, 100);

INSERT INTO materias(nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota)
VALUES("Ética", '2022', '07:00:00', '12:00:00', "Jaime Muralles","C-31", 35, 20, 89);

INSERT INTO materias(nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota)
VALUES("Inglés", '2022', '07:00:00', '12:00:00', "Carlos Cabrera","C-38", 35, 20, 77);

INSERT INTO materias(nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota)
VALUES("Química", '2022', '07:00:00', '12:00:00', "Isidoro Esquite","C-36", 35, 20, 69);

INSERT INTO materias(nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota)
VALUES("Estadística", '2022', '07:00:00', '12:00:00', "Juan Rivas","C-38", 35, 20, 98);

SELECT * FROM materias;

SELECT id_materia, nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo, nota FROM materias;