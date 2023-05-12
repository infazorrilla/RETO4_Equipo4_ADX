--                        BASE DE DATOS: `adx`                       --
-- ----------------------------------------------------------------- --
# CREACIÓN DE LA BASE DE DATOS
CREATE DATABASE IF NOT EXISTS adx;
-- ----------------------------------------------------------------- --
# ACTIVAMOS EL USO DE LA BASE DE DATOS
USE adx;
-- ----------------------------------------------------------------- --


#######################################################################
--                             SPRINT 1                              --
#######################################################################


-- ----------------------------------------------------------------- --
--                         CREACIÓN DE TABLAS                        --
-- ----------------------------------------------------------------- --
/* ACLARACIONES - ESTRUCTURA GENERAL DE LAS TABLAS
> Usuario (dni, nombre, apellido, sexo, fechaNac, contrasena)
> Paciente (dniPaciente(FK), telefono, direccion, boqueado)
> Sanitario (dniSanitario(FK), numPersonal, idAmbulatorio (FK), 
			salario, tipo, especialidad, MIR, categoria, EIR)
> JornadaSanitario(dniSanitario(FK), idJornada(FK))
> Cita (idCita, dniPaciente(FK), dniSanitario(FK), idAmbulatorio (FK))
> Jornada (idJornada,fecha, horaInicio, horaFin)
> Franja (idFranja, horaInicio, horaFin)
> CitaJornadaFranja(idFranja(FK),idJornada(FK), idCita(FK))
> Ambulatorio (idAmbulatorio, nombre, direccion, telefono)
*/
-- ----------------------------------------------------------------- --

# TABLA AMBULATORIO CON DATOS INICIALES
-- Estructura de tabla para la tabla `ambulatorio`
CREATE TABLE  IF NOT EXISTS `ambulatorio` (
  `idAmbulatorio` int PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `telefono` char(9) NOT NULL
);
-- Volcado de datos para la tabla `ambulatorio`
INSERT INTO `ambulatorio` (`idAmbulatorio`, `nombre`, `direccion`, 
`telefono`) VALUES
(1, 'Rekalde', 'Bilbo', '946006700'),
(2, 'Deusto', 'Bilbo', '946006666'),
(3, 'Galdakao', 'Galdakao', '946007400'),
(4, 'Olaguibel', 'Gasteiz', '945007900'),
(5, 'Gros', 'Donosti', '943007909');

-- ----------------------------------------------------------------- --

# TABLA CITA CON DATOS INICIALES
-- Estructura de tabla para la tabla `cita`
CREATE TABLE IF NOT EXISTS `cita` (
  `idCita` int(11) PRIMARY KEY AUTO_INCREMENT,
  `dniPaciente` char(9) NOT NULL,
-- 		FOREIGN KEY (`dniPaciente`) 
--      REFERENCES `paciente` (`dniPaciente`) 
--      ON DELETE CASCADE,
  `dniSanitario` char(9) NOT NULL,
-- 		FOREIGN KEY (`dniSanitario`) 
--      REFERENCES `sanitario` (`dniSanitario`) 
 --     ON DELETE CASCADE,
  `idAmbulatorio` int(11) DEFAULT NULL
-- 		FOREIGN KEY (`idAmbulatorio`) 
--      REFERENCES `ambulatorio` (`idAmbulatorio`)
);
-- Volcado de datos para la tabla `cita`
INSERT INTO `cita` (`idCita`, `dniPaciente`, `dniSanitario`, 
`idAmbulatorio`) VALUES
(1, '11111111P', '00000000E', 1),
(2, '00000000P', '00000000D', 1),
(3, '00000000P', '11111111E', 2),
(4, '11111111P', '11111111D', 1);

-- ----------------------------------------------------------------- --

# TABLA CITA+JORNADA+FRANJA CON DATOS INICIALES
-- Estructura de tabla para la tabla `citajornadafranja`
CREATE TABLE IF NOT EXISTS `citajornadafranja` (
  `idCita` int(11),
-- 		FOREIGN KEY (`idCita`) 
-- 		REFERENCES `cita` (`idCita`),
  `idJornada` int(11),
-- 		FOREIGN KEY (`idJornada`) 
-- 		REFERENCES `jornada` (`idJornada`),
  `idFranja` int(11),
-- 		FOREIGN KEY (`idFranja`) REFERENCES `franja` (`idFranja`),
	PRIMARY KEY(`idCita`, `idJornada`, `idFranja`)
);
-- Volcado de datos para la tabla `citajornadafranja`
INSERT INTO `citajornadafranja` (`idCita`, `idJornada`, `idFranja`) 
VALUES
(1, 3, 5),
(2, 1, 1),
(3, 3, 4),
(4, 1, 2);

-- ----------------------------------------------------------------- --

# TABLA FRANJA CON DATOS INICIALES
-- Estructura de tabla para la tabla `franja`
CREATE TABLE IF NOT EXISTS `franja` (
  `idFranja` int PRIMARY KEY AUTO_INCREMENT,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
	CHECK(`horaFin` > `horaInicio`)
);  
-- Volcado de datos para la tabla `franja`
INSERT INTO `franja` (`idFranja`, `horaInicio`, `horaFin`) VALUES
(1, '08:00:00', '09:00:00'),
(2, '09:00:00', '10:00:00'),
(3, '10:00:00', '11:00:00'),
(4, '11:30:00', '12:30:00'),
(5, '12:30:00', '13:30:00'),
(6, '13:30:00', '14:30:00');

-- ----------------------------------------------------------------- --

# TABLA JORNADA CON DATOS INICIALES
-- Estructura de tabla para la tabla `jornada`
CREATE TABLE IF NOT EXISTS `jornada` (
  `idJornada` int PRIMARY KEY AUTO_INCREMENT,
  `fecha` date,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
	CHECK(`horaFin` > `horaInicio`)
);
-- Volcado de datos para la tabla `jornada`
INSERT INTO `jornada` (`idJornada`, `fecha`, `horaInicio`, `horaFin`) 
VALUES
(1, '2023-04-05', '08:00:00', '15:00:00'),
(2, '2023-04-20', '08:00:00', '15:00:00'),
(3, '2023-04-28', '08:00:00', '15:00:00'),
(4, '2023-05-04', '08:00:00', '15:00:00');

-- ----------------------------------------------------------------- --

# TABLA JORNADA+SANITARIO CON DATOS INICIALES
-- Estructura de tabla para la tabla `jornadasanitario`
CREATE TABLE IF NOT EXISTS `jornadasanitario` (
  `idJornada` int(11) NOT NULL,
-- 		FOREIGN KEY (`idJornada`) 
-- 		REFERENCES `jornada` (`idJornada`) 
-- 		ON DELETE CASCADE,
  `dniSanitario` char(9) NOT NULL,
-- 		FOREIGN KEY (`dniSanitario`) 
-- 		REFERENCES `sanitario` (`dniSanitario`) 
-- 		ON DELETE CASCADE,
    PRIMARY KEY (`idJornada`, `dniSanitario`)
);
-- Volcado de datos para la tabla `jornadasanitario`
INSERT INTO `jornadasanitario` (`idJornada`, `dniSanitario`) VALUES
(1, '00000000E'),
(1, '11111111E'),
(2, '00000000E'),
(2, '11111111E'),
(3, '00000000E');

-- ----------------------------------------------------------------- --

# TABALA PACIENTE CON DATOS INICIALES
-- Estructura de tabla para la tabla `paciente`
CREATE TABLE IF NOT EXISTS `paciente` (
  `dniPaciente` char(9) PRIMARY KEY,
-- 		FOREIGN KEY (`dniPaciente`) 
-- 		REFERENCES `usuario` (`dni`) 
-- 		ON DELETE CASCADE,
  `telefono` char(9) DEFAULT NULL,
  `direccion` varchar(70) NOT NULL,
  `bloqueado` enum('true','false') DEFAULT 'false'
);
-- Volcado de datos para la tabla `paciente`
INSERT INTO `paciente` (`dniPaciente`, `telefono`, `direccion`, 
`bloqueado`) VALUES
('00000000P', '6666666', 'Casco Viejo', 'false'),
('11111111P', '7777777', 'Gasteiz', 'true');

-- ----------------------------------------------------------------- --

# TABLA SANITARIO CON DATOS INICIALES
-- Estructura de tabla para la tabla `sanitario`
CREATE TABLE IF NOT EXISTS `sanitario` (
  `dniSanitario` char(9) PRIMARY KEY,
-- 		  FOREIGN KEY (`dniSanitario`)   		  -- AÑADIDO POR DANIEL
-- 		  REFERENCES `usuario` (`dni`) 
-- 		  ON DELETE CASCADE,
  `numPersonal` int NOT NULL UNIQUE,
  `salario` float NOT NULL, 
		CHECK (`salario` > 0),
  `idAmbulatorio` int(11) DEFAULT NULL,
-- 		FOREIGN KEY (`idAmbulatorio`) 
-- 		REFERENCES `ambulatorio` (`idAmbulatorio`),
  `tipo` enum('Medicina','Enfermeria') DEFAULT NULL,
  `especialidad` varchar(30) DEFAULT NULL,
  `MIR` enum('true','false') DEFAULT NULL,
  `categoria` varchar(30) DEFAULT NULL,
  `EIR` enum('true','false') DEFAULT NULL
);
-- Volcado de datos para la tabla `sanitario`
INSERT INTO `sanitario` (`dniSanitario`, `numPersonal`, `salario`, 
`idAmbulatorio`, `tipo`, `especialidad`, `MIR`, `categoria`, `EIR`) VALUES
('00000000D', 0, 3000, 1, 'Medicina', 'Cardiología', 'true', NULL, NULL),
('00000000E', 1, 1500, 3, 'Enfermeria', NULL, NULL, 'Familiar', 'true'),
('11111111D', 3, 5000, 2, 'Medicina', 'Neurología', 'false', NULL, NULL),
('11111111E', 2, 1400, 5, 'Enfermeria', NULL, NULL, 'Pediátrica', 'false');

-- ----------------------------------------------------------------- --

# TABLA USUARIO CON DATOS INICIALES
/* ACLARACIONES:
Para realizar las pruebas con el programa en Java, se ha
decido determinar una nomencaltura propia, teniendo los 
DNIs terminaciones (D,E,P,A) que corresponden al rol y/o a 
una tabla determinado. 

Tambien se ha decido que estos 
usuarios iniciales deberan tener la contrseña 123 para 
su facil uso y comprobación en la interfaz grafica de java.
*/
-- Estructura de tabla para la tabla `usuario`
CREATE TABLE IF NOT EXISTS `usuario` (
  `dni` char(9) PRIMARY KEY,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `sexo` enum('hombre','mujer') DEFAULT NULL,
  `fechaNac` date NOT NULL,
  `contrasena` varchar(50) NOT NULL
);
-- Volcado de datos para la tabla `usuario`
INSERT INTO `usuario` (`dni`, `nombre`, `apellido`, `sexo`, `fechaNac`, 
`contrasena`) VALUES
('00000000D', 'Jon', 'Urretabizkaia', 'hombre', '1983-01-05', '123'),
('00000000E', 'Borja', 'Saiz', 'hombre', '1975-04-01', '123'),
('00000000P', 'Xabi', 'Buide', 'hombre', '2003-05-08', '123'),
('11111111D', 'Ana', 'Zorrilla', 'mujer', '1990-01-05', '123'),
('11111111E', 'Amaia', 'Alonso', 'mujer', '1998-04-16', '123'),
('11111111P', 'Daniel', 'Floyd', NULL, '2023-05-09', '123');

-- ----------------------------------------------------------------- --

# FOREING KEY 
/* ACLARACIONES
Se han comentando arriba en varias tablas con -- las FK porque al 
crear la DataBase desde cero daría un "Error Code: 1824. Failed to 
open the referenced table"

Por tanto, el metodo aprendido en clase no se podria aplicar en este 
caso. Para agregar las FK se realizara con un Alter Table en todas 
las tablas afectados.
*/
-- ----------------------------------------------------------------- --
-- Filtros para la tabla `cita`
ALTER TABLE `cita`
  ADD CONSTRAINT `cita_ibfk_1` 
	  FOREIGN KEY (`dniPaciente`) 
	  REFERENCES `paciente` (`dniPaciente`) 
	  ON DELETE CASCADE,
  ADD CONSTRAINT `cita_ibfk_2` 
	  FOREIGN KEY (`dniSanitario`) 
	  REFERENCES `sanitario` (`dniSanitario`) 
	  ON DELETE CASCADE,
  ADD CONSTRAINT `cita_ibfk_3` 
	  FOREIGN KEY (`idAmbulatorio`) 
	  REFERENCES `ambulatorio` (`idAmbulatorio`);
-- ----------------------------------------------------------------- --
-- Filtros para la tabla `citajornadafranja`
ALTER TABLE `citajornadafranja`
  ADD CONSTRAINT `citajornadafranja_ibfk_1` 
	  FOREIGN KEY (`idCita`) 
	  REFERENCES `cita` (`idCita`),
  ADD CONSTRAINT `citajornadafranja_ibfk_2` 
	  FOREIGN KEY (`idJornada`) 
	  REFERENCES `jornada` (`idJornada`),
  ADD CONSTRAINT `citajornadafranja_ibfk_3` 
	  FOREIGN KEY (`idFranja`) 
	  REFERENCES `franja` (`idFranja`),
  ADD CONSTRAINT `citajornadafranja_ibfk_4` 
	  FOREIGN KEY (`idCita`) 
	  REFERENCES `cita` (`idCita`) 
	  ON DELETE CASCADE,
  ADD CONSTRAINT `citajornadafranja_ibfk_5` 
	  FOREIGN KEY (`idJornada`) 
	  REFERENCES `jornada` (`idJornada`) 
	  ON DELETE CASCADE,
  ADD CONSTRAINT `citajornadafranja_ibfk_6` 
	  FOREIGN KEY (`idFranja`) 
	  REFERENCES `franja` (`idFranja`) 
	  ON DELETE CASCADE;
-- ----------------------------------------------------------------- --
-- Filtros para la tabla `jornadasanitario`
ALTER TABLE `jornadasanitario`
  ADD CONSTRAINT `jornadasanitario_ibfk_1` 
	  FOREIGN KEY (`idJornada`) 
	  REFERENCES `jornada` (`idJornada`) 
	  ON DELETE CASCADE,
  ADD CONSTRAINT `jornadasanitario_ibfk_2` 
	  FOREIGN KEY (`dniSanitario`) 
	  REFERENCES `sanitario` (`dniSanitario`) 
	  ON DELETE CASCADE;
 -- ----------------------------------------------------------------- -- 
-- Filtros para la tabla `paciente`
ALTER TABLE `paciente`
  ADD CONSTRAINT `paciente_ibfk_1` 
	  FOREIGN KEY (`dniPaciente`) 
	  REFERENCES `usuario` (`dni`) 
	  ON DELETE CASCADE;
-- ----------------------------------------------------------------- --
-- Filtros para la tabla `sanitario`
ALTER TABLE `sanitario`
  ADD CONSTRAINT `sanitario_ibfk_1` 
	  FOREIGN KEY (`idAmbulatorio`) 
	  REFERENCES `ambulatorio` (`idAmbulatorio`),
  ADD CONSTRAINT `sanitario_ibfk_2` 				-- AÑADIDO POR DANIEL
	  FOREIGN KEY (`dniSanitario`) 
	  REFERENCES `usuario` (`dni`) 
	  ON DELETE CASCADE; -- Necesario para borrar desde Java
-- ----------------------------------------------------------------- --





-- ----------------------------------------------------------------- --
--                        CREACIÓN DE INDICES                        --
-- ----------------------------------------------------------------- --

# ÍNDICES DE CLAVE PRIMARIA | PRIMARY KEY
-- Se han añadido en la cración de tablas. 
/* (Debajo estan en la forma alter+ad)
-- ----------------------------------------------------------------- --
-- Indices de la tabla `ambulatorio`
ALTER TABLE `ambulatorio`
  ADD PRIMARY KEY (`idAmbulatorio`);
-- ----------------------------------------------------------------- --
-- Indices de la tabla `cita`
ALTER TABLE `cita`
  ADD PRIMARY KEY (`idCita`),
  ADD KEY `dniPaciente` (`dniPaciente`),
  ADD KEY `dniSanitario` (`dniSanitario`),
  ADD KEY `idAmbulatorio` (`idAmbulatorio`);
-- ----------------------------------------------------------------- --
-- Indices de la tabla `citajornadafranja`
ALTER TABLE `citajornadafranja`
  ADD PRIMARY KEY (`idCita`,`idJornada`,`idFranja`),
  ADD KEY `idJornada` (`idJornada`),
  ADD KEY `idFranja` (`idFranja`);
-- ----------------------------------------------------------------- --
-- Indices de la tabla `franja`
ALTER TABLE `franja`
  ADD PRIMARY KEY (`idFranja`);
-- ----------------------------------------------------------------- --
-- Indices de la tabla `jornada`
ALTER TABLE `jornada`
  ADD PRIMARY KEY (`idJornada`);
-- ----------------------------------------------------------------- --
-- Indices de la tabla `jornadasanitario`
ALTER TABLE `jornadasanitario`
  ADD PRIMARY KEY (`idJornada`,`dniSanitario`),
  ADD KEY `dniSanitario` (`dniSanitario`);
-- ----------------------------------------------------------------- --
-- Indices de la tabla `paciente`
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`dniPaciente`);
-- ----------------------------------------------------------------- --
-- Indices de la tabla `sanitario`
ALTER TABLE `sanitario`
  ADD PRIMARY KEY (`dniSanitario`),
  ADD UNIQUE KEY `numPersonal` (`numPersonal`),
  ADD KEY `idAmbulatorio` (`idAmbulatorio`);
-- ----------------------------------------------------------------- --
-- Indices de la tabla `usuario`
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`dni`);
*/

-- ----------------------------------------------------------------- --

# ÍNDICES ORDINARIOS
CREATE INDEX fechaJornada ON jornada(fecha);
CREATE INDEX horaFranja ON franja(horaInicio);
CREATE INDEX Enfermeria ON Sanitario(eir);
CREATE INDEX DireccionesPaciente ON Paciente(direccion);
CREATE INDEX citaDniPaciente ON Cita(dniPaciente);

-- ----------------------------------------------------------------- --

#  ÍNDICES UNICOS + ÍNDICES COMPUESTOS
/* ACLARACIONES 
Con este índice lo que queremos conseguir es que no se puedan generar
por error ningun ambulatorio con Id y Nombre indenticos 
*/
CREATE UNIQUE INDEX IdAmbulatorioUnico 
		ON ambulatorio (idAmbulatorio, nombre);   -- AÑADIDO POR DANIEL

/* ACLARACIONES 
Con este índice queremos que ninguna persona por cree un un usuario
duplicado. Por eso debe cumplir que la combinación de dni,nombre y 
apellido sean un unico registro en esa tabla
*/
CREATE UNIQUE INDEX UsuarioUnico 
		ON usuario (dni,nombre,apellido);  		 -- AÑADIDO POR DANIEL
/*
Con este índice queremos que al registrarse algun empleado no se cree
por error un mismo usuario. Cumpliendo así que en conjunto dniSanitario
y numPersonal sean unicos.
*/
CREATE UNIQUE INDEX SanitarioUnico 
		ON usuario (dniSanitario, numPersonal);  -- AÑADIDO POR DANIEL

-- ----------------------------------------------------------------- --




#######################################################################
--                             SPRINT 2                              --
#######################################################################





-- ----------------------------------------------------------------- --
--                        CREACIÓN DE VISTAS                         --
-- ----------------------------------------------------------------- --

# Todas las citas de un ambulatorio agrupadas por sanitario
create view ambulatorioCita (idAmbulatorio, idCita, NumPersonal, 
Fecha, Hora, Nombre, Sexo, FechaNac, Telefono) as 
select ci.idAmbulatorio, c.idCita, s.numPersonal, j.fecha, f.horainicio, 
u.nombre, u.sexo, u.fechanac, p.telefono 
from citajornadafranja c join jornada j on  c.idJornada=j.idJornada 
	join franja f on c.idFranja=f.idFranja 
    join cita ci on c.idCita=ci.idCita 
	join paciente p on ci.dniPaciente = p.dniPaciente
    join usuario u on p.dniPaciente=u.dni
    join sanitario s on ci.dniSanitario = s.dniSanitario
group by s.numPersonal;

-- ----------------------------------------------------------------- --

# Modificar una cita (un paciente borra una cita y/o modificar 
# atributos, por ej telefono)
-- Creación de vista
create view modificarPaciente (DNI, Nombre, Apellido, Sexo, FechaNac, 
Telefono, Direccion) as 
select u.dni, u.nombre, u.apellido, u.sexo, u.fechaNac, p.telefono, 
p.direccion
from usuario u join paciente p on u.dni = p.dniPaciente;
/* Modificación de datos de la vista
update modificarPaciente set 
where telefono= ;

update modificarPaciente set 
where direccion= ;
*/

-- ----------------------------------------------------------------- --

# VISTA DE DATOS DE LAS CITAS DE LOS SANITARIOS    -- AÑADIDO POR DANIEL
CREATE VIEW Citas_de_Sanitatarios AS 
SELECT  C.idCita, J.fecha 'FECHA', F.horaInicio 'DE', F.horaFin 'HASTA', 
U.nombre 'NOMBRE', U.apellido 'APELLIDO', P.telefono 'TELEFONO', 
A.nombre 'LUGAR'
FROM citajornadafranja AS CJF 
	JOIN cita AS C ON  C.idCita= CJF.idCita
	JOIN jornada AS J ON J.idJornada = CJF.idJornada
	JOIN franja AS F ON F.idFranja = CJF.idFranja
    JOIN paciente AS P ON P.dniPaciente = C.dniPaciente
	JOIN usuario AS U ON P.dniPaciente = U.dni
    JOIN ambulatorio AS A ON A.idAmbulatorio = C.idAmbulatorio;
/* EXPLICACIÓN O ACLARACIÓN DE LA CONSULTA
-- ----------------------------------------------------------------- --
-- OBJETIVO: Una tabla que muestre todos las citas que tendran los 
sanitarios. Con los siguientes campos: "ID_CITA", "FECHA", "DE", 
"HASTA", "NOMBRE", "APELLIDO", "TELEFONO", "LUGAR"

# PROCESO DE ANALISIS Y COOMPRESIÓN:
-- ----------------------------------------------------------------- --
-- 3ª) AGRUPACION: [CITA+CITAJORNADAFRANJA (C.idCita= CJF.idCita)] + 
-- [CITA+PACIENTE (P.dniPaciente = C.dniPaciente)]
SELECT  C.idCita, J.fecha 'FECHA', F.horaInicio 'DE', F.horaFin 'HASTA', 
U.nombre 'NOMBRE', U.apellido 'APELLIDO', P.telefono 'TELEFONO'
FROM citajornadafranja AS CJF 
	JOIN cita AS C ON  C.idCita= CJF.idCita
	JOIN jornada AS J ON J.idJornada = CJF.idJornada
	JOIN franja AS F ON F.idFranja = CJF.idFranja
    JOIN paciente AS P ON P.dniPaciente = C.dniPaciente
	JOIN usuario AS U ON P.dniPaciente = U.dni; 
-- ----------------------------------------------------------------- --
-- 2ª) AGRUPACIÓN: CITA+PACIENTE+USUARIO (C.dniPaciente=P.dniPaciente=U.dni)
SELECT U.nombre 'NOMBRE', U.apellido 'APELLIDO', P.telefono 'TELEFONO'
FROM usuario AS U 
JOIN paciente AS P ON P.dniPaciente = U.dni
JOIN cita AS C ON P.dniPaciente = C.dniPaciente;
-- ----------------------------------------------------------------- --
-- 1ª) AGRUPACIÓN: CITAJORNAFRANJA = CITA + JORNADA + FRANJA 
-- (C.idCita= CJF.idCita+J.idJornada = CJF.idJornada+F.idFranja = CJF.idFranja)
SELECT  C.idCita, J.fecha 'FECHA', F.horaInicio 'DE', F.horaFin 'HASTA' 
FROM citajornadafranja AS CJF 
JOIN cita AS C ON  C.idCita= CJF.idCita
JOIN jornada AS J ON J.idJornada = CJF.idJornada
JOIN franja AS F ON F.idFranja = CJF.idFranja; 
-- ----------------------------------------------------------------- --
-- 6) CONSULTA PARA SACAR AMBULATORIO 
SELECT A.nombre 'LUGAR'
FROM ambulatorio AS A JOIN cita AS C ON A.idAmbulatorio = C.idAmbulatorio;
-- ----------------------------------------------------------------- --
-- 5) CONSULTA PARA SACAR TELEFONO DE PACIENTE
SELECT P.telefono 'TELEFONO'
FROM paciente AS P JOIN cita AS C ON P.dniPaciente = C.dniPaciente;
-- ----------------------------------------------------------------- --
-- 4) CONSULTA PARA SACAR NOMBRE Y APELLIDO DE PACIENTE
SELECT U.nombre 'NOMBRE', U.apellido 'APELLIDO'
FROM usuario AS U JOIN paciente AS P ON P.dniPaciente = U.dni;
-- ----------------------------------------------------------------- --
-- 3) CONSULTA PARA SACAR HORA DE INICIO Y FIN
SELECT F.horaInicio 'DE', F.horaFin 'HASTA'
FROM franja AS F JOIN citajornadafranja AS CJF ON F.idFranja = CJF.idFranja ;
-- ----------------------------------------------------------------- --
-- 2) CONSULTA PARA SACAR FECHA DE CITAS
SELECT J.fecha 'FECHA'
FROM jornada AS J JOIN citajornadafranja AS CJF ON J.idJornada = CJF.idJornada;
-- ----------------------------------------------------------------- --
-- 1) CONSULTA PARA SACAR EL ID_CITA DE CITA+JORNANADA+FRANJA
SELECT  C.idCita 
FROM cita AS C JOIN citajornadafranja AS CJF ON  C.idCita= CJF.idCita;
*/

-- ----------------------------------------------------------------- --





-- ----------------------------------------------------------------- --
--                       CREACIÓN DE USUARIOS                        --
-- ----------------------------------------------------------------- -- 
 
# USUARIO: ADMIN | ROL AMDIN
CREATE USER IF NOT EXISTS 'user_admin'@'localhost' 
	IDENTIFIED BY '321' 
	DEFAULT ROLE rol_admin;

-- ----------------------------------------------------------------- --

# USUARIO: SANITARIO | ROL SANITARIO
CREATE USER IF NOT EXISTS  'user_sanitario'@'localhost' 
	IDENTIFIED BY '321'
	DEFAULT ROLE rol_sanitario;

-- ----------------------------------------------------------------- --

# USUARIO: PACIENTE 1 | ROL PACIENTE 
CREATE USER IF NOT EXISTS  'user_paciente1'@'localhost' 
	IDENTIFIED BY '321'
	DEFAULT ROLE rol_paciente;

-- ----------------------------------------------------------------- --

# USUARIO: PACIENTE 2 | ROL PACIENTE 
-- A user_paciente2 le asignamos un permiso especial
CREATE USER IF NOT EXISTS  'user_paciente2'@'localhost' 
	IDENTIFIED BY '321'
	DEFAULT ROLE rol_paciente;

-- ----------------------------------------------------------------- --

# USUARIO: | ROL PACIENTE 2
CREATE USER IF NOT EXISTS  'user_paciente2'@'localhost' 
	IDENTIFIED BY '321'
	DEFAULT ROLE rol_paciente
    WITH max_user_connections 5
	PASSWORD EXPIRE INTERVAL 365 DAY;

-- ----------------------------------------------------------------- --





-- ----------------------------------------------------------------- --
--                         CREACIÓN DE ROLES                         --
-- ----------------------------------------------------------------- --

# ROL AMDIN
CREATE ROLE IF NOT EXISTS rol_admin;
# ROL SANITARIO
CREATE ROLE IF NOT EXISTS rol_sanitario;
# ROL PACIENT
CREATE ROLE IF NOT EXISTS rol_paciente;

-- ----------------------------------------------------------------- --





-- ----------------------------------------------------------------- --
--                             PERMISOS                              --
-- ----------------------------------------------------------------- --

# USUARIO: ADMIN | ROL AMDIN
GRANT ALL PRIVILEGES ON  adx.*
TO user_admin 
WITH GRANT OPTION;

-- ----------------------------------------------------------------- --

# USUARIO: SANITARIO | ROL SANITARIO
-- Permiso en la tabla citas
GRANT insert, delete, update
ON adx.cita
TO rol_sanitario;
-- Permiso en la tabla jornada
GRANT update
ON adx.jornada
TO rol_sanitario;
 
 -- ----------------------------------------------------------------- --
 
# USUARIO: PACIENTE 1 | ROL PACIENTE 
GRANT insert, delete
ON adx.cita
TO rol_paciente;

-- ----------------------------------------------------------------- --

# USUARIO: PACIENTE+SANITARIO  
GRANT update (contrasena)
ON adx.usuario
TO rol_sanitario, rol_paciente;

-- ----------------------------------------------------------------- --
# USUARIO: PACIENTE 1 | ROL PACIENTE 
GRANT update(telefono, direccion)
ON adx.paciente
TO rol_paciente;

-- ----------------------------------------------------------------- --

# USUARIO: PACIENTE 2 | ROL PACIENTE 2
GRANT update(nombre)
ON adx.ambulatorio
TO user_paciente2;

-- ----------------------------------------------------------------- --





#######################################################################
--                             SPRINT 3                              --
#######################################################################





-- ----------------------------------------------------------------- --
--                          PROCEDIMIENTOS                           --
-- ----------------------------------------------------------------- --

# PROCEDIMIENTO "SACAR CISTAS DISPONIBLES"
/* ACLARACIONES:
Procedimiento que saca las franjas disponibles (y su hora de inicio) de
un sanitario en una fecha concreta. Se meten como parámetros el DNI del 
sanitario y la fecha. 

Se mete en un cursor el resultado de la consulta (que hace select de 
idFranja de la tabla Franja y le resta las ids de las franjas ocupadas 
para un sanitario en una fecha –resultado de hacer varios joins de 
citajornadafranja, jornadasanitario, cita…–). Se utiliza un bucle, para 
almacenar los posibles resultados (más de 1). También se trata una 
excepción (declare continue handler for not found set fin = 1;), 
para cuando la tabla no devuelva ningún resultado, o deje de devolver 
resultados.
*/
DELIMITER $$
CREATE PROCEDURE `sacarCitasDisponibles` (IN `sanitarioDeseado` CHAR(9), 
IN `fechaDeseada` DATE)   
begin
declare franjaDisponible int;
declare fin bool default 0;
declare c cursor for 
select idFranja
from franja
where idFranja NOT IN (SELECT f.idFranja
from franja f left join citajornadafranja cjf on f.idFranja=cjf.idFranja
join cita c on c.idCita=cjf.idCita
join sanitario s on c.dniSanitario=s.dniSanitario
left join jornada j on j.idJornada=cjf.idJornada
where s.dniSanitario = sanitarioDeseado and j.fecha= fechaDeseada);
declare continue handler for not found set fin = 1;
open c;
Fetch c into franjaDisponible;
	while fin = 0 DO
		select (franjaDisponible) "Franja";
   		Fetch c into franjaDisponible;
	end while;
end$$

-- ----------------------------------------------------------------- --

# PROCEDURE " FECHAS DISPONIBLES"
/* ACLARACIONES
Procedimiento que saca los días y horas de inicio que trabaja un tipo 
sanitario (‘Enfermeria’ o ‘Medicina’) en un ambulatorio.

Se crea un cursor en el que se introducen las fechas en las que trabajan 
profesionales de medicina o enfermería (según se elija). Para ello, se 
realiza una consulta juntando varias tablas (jornada, sanitario, j
ornadasanitario). Como en el procedimiento anterior, se utiliza un bucle 
y se trata una excepción.
*/
CREATE PROCEDURE `sacarFechasDisponibles` (IN `tipoSanitario` VARCHAR(30), 
IN `ambulatorioDeseado` INT)   
begin
declare fechaDisponible date;
declare fin bool default 0;
declare c cursor for 
select j.fecha 
from jornada j 
right join jornadasanitario js on j.idJornada=js.idJornada 
join sanitario s on js.dniSanitario=s.dniSanitario 
where tipo=tipoSanitario and s.idAmbulatorio = ambulatorioDeseado 
group by js.dniSanitario and fecha;
declare continue handler for not found set fin = 1;
open c;
FETCH c into fechaDisponible;
while fin = 0 DO
	select (fechaDisponible) "Fecha";
   	Fetch c into fechaDisponible;
end while;
close c;
end$$
-- Por si nos da error:
-- REPAIR TABLE mysql.proc;

-- ----------------------------------------------------------------- --





-- ----------------------------------------------------------------- --
--                             FUNCIONES                             --
-- ----------------------------------------------------------------- --

# FUNCIÓN "CALCULAR EDAD"
/*
Función que devuelve la edad de una persona restando el año actual 
y la fecha de nacimiento del usuario al que le corresponde el DNI que 
pasamos como parámetro (idUsuario).
*/
delimiter //
create function calcularEdad (idPaciente char(9)) returns int reads sql data
begin
declare edad int;
declare fecha date;
select fechaNac into fecha from usuario where dni = idPaciente;
set edad = year(curdate()) - YEAR(fecha);
return edad;
end;//

-- ----------------------------------------------------------------- --





-- ----------------------------------------------------------------- --
--                         TIGGERS Y EVENTOS                         --
-- ----------------------------------------------------------------- --

# TRIGGER "ELIMANAR PACIENTE DE TABLA USUARIO"
/*
Ambos triggers eliminan la fila de la tabla ‘usuario’ con la que se 
relacionan los atributos ‘dniPaciente’ y ‘dniSanitario’ de las tablas 
‘paciente’ y ‘sanitario’. Es decir, cuando se elimina una fila de la 
tabla ‘paciente’ o sanitario, también se eliminará la fila de la tabla 
‘usuario’ con el mismo DNI.
*/
create trigger eliminarPacienteDeTablaUsuario
after delete on paciente for each row
delete from usuario where dni=OLD.dniPaciente;

-- ----------------------------------------------------------------- --

# TRIGGER "ELIMINAR SANITARIO DE TABLA USUARIO"
create trigger eliminarSanitarioDeTablaUsuario
after delete on sanitario for each row
delete from usuario where dni=OLD.dniSanitario;

-- ----------------------------------------------------------------- --

# EVENTO "SUBIDA SALARIAL"
/**
Evento que cada año le sube el sueldo a los sanitarios, según su tipo 
(‘Enfermeria’ o ‘Medicina’). A los de tipo ‘Medicina’, se les incrementa 
el sueldo en un 10% y a los de ‘Enfermeria’, un 15%.
*/
delimiter //
CREATE EVENT subidaSalarial
ON SCHEDULE EVERY 1 YEAR
 DO
    BEGIN
    declare sueldo float;
    declare profesion varchar(50);
        SELECT salario into sueldo FROM sanitario;
        select tipo into profesion from sanitario;
        if profesion='Medicina' then
			update sanitario set salario= sueldo + (sueldo*0.1);
		elseif profesion='Enfermeria' then
			update sanitario set salario= sueldo + (sueldo*0.15);
        end if;
    END//
delimiter ;

-- ----------------------------------------------------------------- --




/*
DELIMITER $$
CREATE PROCEDURE CALCULA_EDAD (BIRTH_YEAR INT)
	BEGIN
    	DECLARE ACTUAL_YEAR INT DEFAULT 2016;
        DECLARE EDAD INT;
        SET EDAD = ACTUAL_YEAR - BIRTH_YEAR;
        SELECT EDAD;
    END;$$
 DELIMITER ;
 
 DELIMITER $$
 CREATE TRIGGER REVISA_PREVISO_BU BEFORE UPDATE ON PRODUCTOS FOR EACH ROW
 	BEGIN
    	IF (NEW.PRECIO<0) THEN
        	SET NEW.PRECIO=0;
        ELSEIF(NEW.PRECIO>100) THEN
        	SET NEW.PRECIO=OLD.PRECIO;
        END IF;
     END;$$
DELIMITER ;
*/