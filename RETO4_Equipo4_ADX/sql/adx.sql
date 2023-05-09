-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-05-2023 a las 03:01:54
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `adx`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sacarCitasDisponibles` (IN `sanitarioDeseado` CHAR(9), IN `fechaDeseada` DATE)   begin
declare franjaDisponible int;
declare fin bool default 0;
declare c cursor for select idFranja
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `sacarFechasDisponibles` (IN `tipoSanitario` VARCHAR(30), IN `ambulatorioDeseado` INT)   begin
declare fechaDisponible date;
declare fin bool default 0;
declare c cursor for select j.fecha from jornada j right join jornadasanitario js on j.idJornada=js.idJornada join sanitario s on js.dniSanitario=s.dniSanitario where tipo=tipoSanitario and s.idAmbulatorio = ambulatorioDeseado group by js.dniSanitario and fecha;
declare continue handler for not found set fin = 1;
open c;
FETCH c into fechaDisponible;
while fin = 0 DO
	select (fechaDisponible) "Fecha";
   	Fetch c into fechaDisponible;
end while;
close c;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ambulatorio`
--

CREATE TABLE `ambulatorio` (
  `idAmbulatorio` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `telefono` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `ambulatorio`
--

INSERT INTO `ambulatorio` (`idAmbulatorio`, `nombre`, `direccion`, `telefono`) VALUES
(1, 'a', 'a', '12345'),
(2, 'b', 'b', '7787'),
(3, 'c', 'c', '648674'),
(4, 'd', 'd', '5897'),
(5, 'e', 'e', '635');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `ambulatoriocita`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `ambulatoriocita` (
`idAmbulatorio` int(11)
,`idCita` int(11)
,`NumPersonal` int(11)
,`Fecha` date
,`Hora` time
,`Nombre` varchar(50)
,`Sexo` enum('hombre','mujer')
,`FechaNac` date
,`Telefono` char(9)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `idCita` int(11) NOT NULL,
  `dniPaciente` char(9) NOT NULL,
  `dniSanitario` char(9) NOT NULL,
  `idAmbulatorio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`idCita`, `dniPaciente`, `dniSanitario`, `idAmbulatorio`) VALUES
(1, '11111111P', '00000000E', 1),
(2, '00000000P', '00000000D', 1),
(3, '00000000P', '11111111E', 2),
(4, '11111111P', '11111111D', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citajornadafranja`
--

CREATE TABLE `citajornadafranja` (
  `idCita` int(11) NOT NULL,
  `idJornada` int(11) NOT NULL,
  `idFranja` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `citajornadafranja`
--

INSERT INTO `citajornadafranja` (`idCita`, `idJornada`, `idFranja`) VALUES
(1, 3, 5),
(2, 1, 1),
(3, 3, 4),
(4, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `franja`
--

CREATE TABLE `franja` (
  `idFranja` int(11) NOT NULL,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `franja`
--

INSERT INTO `franja` (`idFranja`, `horaInicio`, `horaFin`) VALUES
(1, '08:00:00', '09:00:00'),
(2, '09:00:00', '10:00:00'),
(3, '10:00:00', '11:00:00'),
(4, '11:30:00', '12:30:00'),
(5, '12:30:00', '13:30:00'),
(6, '13:30:00', '14:30:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jornada`
--

CREATE TABLE `jornada` (
  `idJornada` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `jornada`
--

INSERT INTO `jornada` (`idJornada`, `fecha`, `horaInicio`, `horaFin`) VALUES
(1, '2023-04-05', '08:00:00', '15:00:00'),
(2, '2023-04-20', '08:00:00', '15:00:00'),
(3, '2023-04-28', '08:00:00', '15:00:00'),
(4, '2023-05-04', '08:00:00', '15:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jornadasanitario`
--

CREATE TABLE `jornadasanitario` (
  `idJornada` int(11) NOT NULL,
  `dniSanitario` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `jornadasanitario`
--

INSERT INTO `jornadasanitario` (`idJornada`, `dniSanitario`) VALUES
(1, '00000000E'),
(1, '11111111E'),
(2, '00000000E'),
(2, '11111111E'),
(3, '00000000E');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `dniPaciente` char(9) NOT NULL,
  `telefono` char(9) DEFAULT NULL,
  `direccion` varchar(70) NOT NULL,
  `bloqueado` enum('true','false') DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`dniPaciente`, `telefono`, `direccion`, `bloqueado`) VALUES
('00000000P', '6666666', 'Casco Viejo', 'false'),
('11111111P', '7777777', 'Gasteiz', 'true');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sanitario`
--

CREATE TABLE `sanitario` (
  `dniSanitario` char(9) NOT NULL,
  `numPersonal` int(11) NOT NULL,
  `salario` float NOT NULL CHECK (`salario` > 0),
  `idAmbulatorio` int(11) DEFAULT NULL,
  `tipo` enum('Medicina','Enfermeria') DEFAULT NULL,
  `especialidad` varchar(30) DEFAULT NULL,
  `MIR` enum('true','false') DEFAULT NULL,
  `categoria` varchar(30) DEFAULT NULL,
  `EIR` enum('true','false') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `sanitario`
--

INSERT INTO `sanitario` (`dniSanitario`, `numPersonal`, `salario`, `idAmbulatorio`, `tipo`, `especialidad`, `MIR`, `categoria`, `EIR`) VALUES
('00000000D', 0, 3000, 1, 'Medicina', 'Cardiología', 'true', NULL, NULL),
('00000000E', 1, 1500, 3, 'Enfermeria', NULL, NULL, 'Familiar', 'true'),
('11111111D', 3, 5000, 2, 'Medicina', 'Neurología', 'false', NULL, NULL),
('11111111E', 2, 1400, 5, 'Enfermeria', NULL, NULL, 'Pediátrica', 'false');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `dni` char(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `sexo` enum('hombre','mujer') DEFAULT NULL,
  `fechaNac` date NOT NULL,
  `contrasena` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`dni`, `nombre`, `apellido`, `sexo`, `fechaNac`, `contrasena`) VALUES
('00000000D', 'Jon', 'Urretabizkaia', 'hombre', '1983-01-05', '123'),
('00000000E', 'Borja', 'Saiz', 'hombre', '1975-04-01', '123'),
('00000000P', 'Xabi', 'Buide', 'hombre', '2003-05-08', '123'),
('11111111D', 'Ana', 'Zorrilla', 'mujer', '1990-01-05', '123'),
('11111111E', 'Amaia', 'Alonso', 'mujer', '1998-04-16', '123'),
('11111111P', 'Daniel', 'Floyd', NULL, '2023-05-09', '123');

-- --------------------------------------------------------

--
-- Estructura para la vista `ambulatoriocita`
--
DROP TABLE IF EXISTS `ambulatoriocita`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ambulatoriocita`  AS SELECT `ci`.`idAmbulatorio` AS `idAmbulatorio`, `c`.`idCita` AS `idCita`, `s`.`numPersonal` AS `NumPersonal`, `j`.`fecha` AS `Fecha`, `f`.`horaInicio` AS `Hora`, `u`.`nombre` AS `Nombre`, `u`.`sexo` AS `Sexo`, `u`.`fechaNac` AS `FechaNac`, `p`.`telefono` AS `Telefono` FROM ((((((`citajornadafranja` `c` join `jornada` `j` on(`c`.`idJornada` = `j`.`idJornada`)) join `franja` `f` on(`c`.`idFranja` = `f`.`idFranja`)) join `cita` `ci` on(`c`.`idCita` = `ci`.`idCita`)) join `paciente` `p` on(`ci`.`dniPaciente` = `p`.`dniPaciente`)) join `usuario` `u` on(`p`.`dniPaciente` = `u`.`dni`)) join `sanitario` `s` on(`ci`.`dniSanitario` = `s`.`dniSanitario`)) GROUP BY `s`.`numPersonal``numPersonal`  ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ambulatorio`
--
ALTER TABLE `ambulatorio`
  ADD PRIMARY KEY (`idAmbulatorio`);

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`idCita`),
  ADD KEY `dniPaciente` (`dniPaciente`),
  ADD KEY `dniSanitario` (`dniSanitario`),
  ADD KEY `idAmbulatorio` (`idAmbulatorio`);

--
-- Indices de la tabla `citajornadafranja`
--
ALTER TABLE `citajornadafranja`
  ADD PRIMARY KEY (`idCita`,`idJornada`,`idFranja`),
  ADD KEY `idJornada` (`idJornada`),
  ADD KEY `idFranja` (`idFranja`);

--
-- Indices de la tabla `franja`
--
ALTER TABLE `franja`
  ADD PRIMARY KEY (`idFranja`);

--
-- Indices de la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD PRIMARY KEY (`idJornada`);

--
-- Indices de la tabla `jornadasanitario`
--
ALTER TABLE `jornadasanitario`
  ADD PRIMARY KEY (`idJornada`,`dniSanitario`),
  ADD KEY `dniSanitario` (`dniSanitario`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`dniPaciente`);

--
-- Indices de la tabla `sanitario`
--
ALTER TABLE `sanitario`
  ADD PRIMARY KEY (`dniSanitario`),
  ADD UNIQUE KEY `numPersonal` (`numPersonal`),
  ADD KEY `idAmbulatorio` (`idAmbulatorio`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ambulatorio`
--
ALTER TABLE `ambulatorio`
  MODIFY `idAmbulatorio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `idCita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `franja`
--
ALTER TABLE `franja`
  MODIFY `idFranja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1002;

--
-- AUTO_INCREMENT de la tabla `jornada`
--
ALTER TABLE `jornada`
  MODIFY `idJornada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `cita_ibfk_1` FOREIGN KEY (`dniPaciente`) REFERENCES `paciente` (`dniPaciente`) ON DELETE CASCADE,
  ADD CONSTRAINT `cita_ibfk_2` FOREIGN KEY (`dniSanitario`) REFERENCES `sanitario` (`dniSanitario`) ON DELETE CASCADE,
  ADD CONSTRAINT `cita_ibfk_3` FOREIGN KEY (`idAmbulatorio`) REFERENCES `ambulatorio` (`idAmbulatorio`);

--
-- Filtros para la tabla `citajornadafranja`
--
ALTER TABLE `citajornadafranja`
  ADD CONSTRAINT `citajornadafranja_ibfk_1` FOREIGN KEY (`idCita`) REFERENCES `cita` (`idCita`),
  ADD CONSTRAINT `citajornadafranja_ibfk_2` FOREIGN KEY (`idJornada`) REFERENCES `jornada` (`idJornada`),
  ADD CONSTRAINT `citajornadafranja_ibfk_3` FOREIGN KEY (`idFranja`) REFERENCES `franja` (`idFranja`),
  ADD CONSTRAINT `citajornadafranja_ibfk_4` FOREIGN KEY (`idCita`) REFERENCES `cita` (`idCita`) ON DELETE CASCADE,
  ADD CONSTRAINT `citajornadafranja_ibfk_5` FOREIGN KEY (`idJornada`) REFERENCES `jornada` (`idJornada`) ON DELETE CASCADE,
  ADD CONSTRAINT `citajornadafranja_ibfk_6` FOREIGN KEY (`idFranja`) REFERENCES `franja` (`idFranja`) ON DELETE CASCADE;

--
-- Filtros para la tabla `jornadasanitario`
--
ALTER TABLE `jornadasanitario`
  ADD CONSTRAINT `jornadasanitario_ibfk_1` FOREIGN KEY (`idJornada`) REFERENCES `jornada` (`idJornada`) ON DELETE CASCADE,
  ADD CONSTRAINT `jornadasanitario_ibfk_2` FOREIGN KEY (`dniSanitario`) REFERENCES `sanitario` (`dniSanitario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD CONSTRAINT `paciente_ibfk_1` FOREIGN KEY (`dniPaciente`) REFERENCES `usuario` (`dni`) ON DELETE CASCADE;

--
-- Filtros para la tabla `sanitario`
--
ALTER TABLE `sanitario`
  ADD CONSTRAINT `sanitario_ibfk_1` FOREIGN KEY (`idAmbulatorio`) REFERENCES `ambulatorio` (`idAmbulatorio`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
