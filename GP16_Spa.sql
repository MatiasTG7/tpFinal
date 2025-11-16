-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-11-2025 a las 18:33:37
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `spaentrededos`
--
CREATE DATABASE IF NOT EXISTS `spaentrededos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `spaentrededos`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `codCli` int(11) NOT NULL,
  `dni` varchar(15) NOT NULL,
  `nombreCliente` varchar(100) NOT NULL,
  `telefonoCliente` varchar(15) NOT NULL,
  `edad` int(11) NOT NULL,
  `afecciones` tinyint(1) NOT NULL,
  `estadoCliente` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codCli`, `dni`, `nombreCliente`, `telefonoCliente`, `edad`, `afecciones`, `estadoCliente`) VALUES
(3, '46807140', 'Perello Alybel', '2664345678', 19, 1, 0),
(5, '47345622', 'Miranda Matias', '2665378201', 18, 1, 1),
(6, '42901341', 'Saccone Cristian', '2664678903', 23, 1, 1),
(7, '46807142', 'Perello Aly', '2665015307', 19, 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diaspa`
--

CREATE TABLE `diaspa` (
  `codPack` int(11) NOT NULL,
  `FechayHora` datetime NOT NULL,
  `preferencias` varchar(100) NOT NULL,
  `codCli` int(11) NOT NULL,
  `monto` double NOT NULL,
  `estadoDia` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `diaspa`
--

INSERT INTO `diaspa` (`codPack`, `FechayHora`, `preferencias`, `codCli`, `monto`, `estadoDia`) VALUES
(1, '2025-12-01 13:30:00', 'Spa completo corporal y facial', 7, 45600, 1),
(2, '2025-11-14 13:30:00', 'Masajes faciales', 5, 52500, 0),
(4, '2025-11-21 16:00:00', 'Solo sauna y piedras calientes', 6, 34000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `instalacion`
--

CREATE TABLE `instalacion` (
  `codInstal` int(11) NOT NULL,
  `nombreInstalacion` varchar(20) NOT NULL,
  `detalleDeUso` varchar(100) NOT NULL,
  `precio30m` double NOT NULL,
  `estadoInstalacion` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `instalacion`
--

INSERT INTO `instalacion` (`codInstal`, `nombreInstalacion`, `detalleDeUso`, `precio30m`, `estadoInstalacion`) VALUES
(2, 'Sauna', 'Desintoxica y Relaja', 20000, 1),
(3, 'Jacuzzi', 'Relajacion con agua caliente', 24000, 1),
(4, 'Sala de masajes', 'Masajes corporales y faciales', 18000, 1),
(5, 'Centro de estetica', 'Diferentes tipos de tratamientos faciales que mejoran la estetica', 26000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `masaje`
--

CREATE TABLE `masaje` (
  `codTratamiento` int(11) NOT NULL,
  `nombreTratamiento` varchar(20) NOT NULL,
  `tipo` enum('facial','corporal','relajacion','estetico') NOT NULL,
  `detalleTratamiento` varchar(100) NOT NULL,
  `duracionTratamiento` int(11) NOT NULL,
  `costoTratamiento` double NOT NULL,
  `activo` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `masaje`
--

INSERT INTO `masaje` (`codTratamiento`, `nombreTratamiento`, `tipo`, `detalleTratamiento`, `duracionTratamiento`, `costoTratamiento`, `activo`) VALUES
(3, 'Pelling quimico', 'estetico', 'Exfolia la piel mejorando el tono y la textura', 20, 22500, 1),
(4, 'Mascarillas', 'relajacion', 'Aplicaciones para hidratar y nutrir la piel, mejora la apariencia', 15, 12500, 1),
(5, 'Microdermoabrasion', 'estetico', 'Exfolia la piel para eliminar celular muertas y mejorar la apariencia', 25, 24800, 1),
(6, 'Piedras calientes', 'corporal', 'Calma y relaja el cuerpo', 30, 28000, 1),
(7, 'Radiofrecuencia', 'corporal', 'Trata la flacidez y mejora la elasticidad de la piel', 15, 20400, 1),
(8, 'Masaje Shiatshu', 'facial', 'Mejora la circulacion, relaja y alivia dolores de cabeza o migrañas', 25, 19900, 1),
(9, 'Drenaje Linfatico', 'facial', 'Deshincha, descongestiona y desintoxica', 25, 20400, 1),
(10, 'Hidromasaje', 'relajacion', 'Relaja y calma el cuerpo con agua caliente', 30, 20000, 1),
(11, 'Masaje Thai', 'relajacion', 'Aplica presion en los puntos de energia del cuerpo', 15, 12000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `masajista`
--

CREATE TABLE `masajista` (
  `codMasajista` int(11) NOT NULL,
  `matricula` varchar(20) NOT NULL,
  `nombreMasajista` varchar(20) NOT NULL,
  `telefonoMasajista` varchar(15) NOT NULL,
  `especialidad` enum('facial','corporal','relajacion','estetico') NOT NULL,
  `estadoMasajista` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `masajista`
--

INSERT INTO `masajista` (`codMasajista`, `matricula`, `nombreMasajista`, `telefonoMasajista`, `especialidad`, `estadoMasajista`) VALUES
(1, '123738093', 'Saez Juan', '2664531262', 'estetico', 1),
(2, '678908626', 'Saez Cristina', '2664321287', 'facial', 1),
(3, '123456789', 'Saez JuanJo', '2665422452', 'relajacion', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesion`
--

CREATE TABLE `sesion` (
  `codSesion` int(11) NOT NULL,
  `fechaInicio` datetime NOT NULL,
  `fechaFin` datetime NOT NULL,
  `codTratamiento` int(11) NOT NULL,
  `codMasajista` int(11) NOT NULL,
  `codPack` int(11) NOT NULL,
  `codInstal` int(11) NOT NULL,
  `estadoInstalacion` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sesion`
--

INSERT INTO `sesion` (`codSesion`, `fechaInicio`, `fechaFin`, `codTratamiento`, `codMasajista`, `codPack`, `codInstal`, `estadoInstalacion`) VALUES
(2, '2025-11-13 10:00:00', '2025-11-13 10:20:00', 3, 1, 1, 4, 1),
(4, '2025-11-13 10:30:00', '2025-11-13 11:00:00', 5, 2, 1, 5, 1),
(7, '2025-11-14 17:30:00', '2025-11-14 17:45:00', 10, 3, 2, 3, 0),
(8, '2025-11-13 10:30:00', '2025-11-13 11:00:00', 3, 2, 1, 5, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codCli`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `diaspa`
--
ALTER TABLE `diaspa`
  ADD PRIMARY KEY (`codPack`),
  ADD UNIQUE KEY `idCliente` (`codCli`);

--
-- Indices de la tabla `instalacion`
--
ALTER TABLE `instalacion`
  ADD PRIMARY KEY (`codInstal`);

--
-- Indices de la tabla `masaje`
--
ALTER TABLE `masaje`
  ADD PRIMARY KEY (`codTratamiento`);

--
-- Indices de la tabla `masajista`
--
ALTER TABLE `masajista`
  ADD PRIMARY KEY (`codMasajista`),
  ADD UNIQUE KEY `matricula` (`matricula`);

--
-- Indices de la tabla `sesion`
--
ALTER TABLE `sesion`
  ADD PRIMARY KEY (`codSesion`),
  ADD KEY `idDiaSpa` (`codPack`) USING BTREE,
  ADD KEY `idMasajista` (`codMasajista`) USING BTREE,
  ADD KEY `codInstal` (`codInstal`) USING BTREE,
  ADD KEY `idTratamiento` (`codTratamiento`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codCli` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `diaspa`
--
ALTER TABLE `diaspa`
  MODIFY `codPack` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `instalacion`
--
ALTER TABLE `instalacion`
  MODIFY `codInstal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `masaje`
--
ALTER TABLE `masaje`
  MODIFY `codTratamiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `masajista`
--
ALTER TABLE `masajista`
  MODIFY `codMasajista` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `sesion`
--
ALTER TABLE `sesion`
  MODIFY `codSesion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `diaspa`
--
ALTER TABLE `diaspa`
  ADD CONSTRAINT `diaspa_ibfk_1` FOREIGN KEY (`codCli`) REFERENCES `cliente` (`codCli`);

--
-- Filtros para la tabla `sesion`
--
ALTER TABLE `sesion`
  ADD CONSTRAINT `sesion_ibfk_1` FOREIGN KEY (`codPack`) REFERENCES `diaspa` (`codPack`),
  ADD CONSTRAINT `sesion_ibfk_2` FOREIGN KEY (`codTratamiento`) REFERENCES `masaje` (`codTratamiento`),
  ADD CONSTRAINT `sesion_ibfk_3` FOREIGN KEY (`codMasajista`) REFERENCES `masajista` (`codMasajista`),
  ADD CONSTRAINT `sesion_ibfk_4` FOREIGN KEY (`codInstal`) REFERENCES `instalacion` (`codInstal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
