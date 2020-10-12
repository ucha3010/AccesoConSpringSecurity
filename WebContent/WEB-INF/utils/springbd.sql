-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-09-2020 a las 18:07:54
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `springbd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `idAd` int(11) NOT NULL,
  `nombre` varchar(85) COLLATE utf8_spanish_ci NOT NULL,
  `cargo` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `fechaCreacion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`idAd`, `nombre`, `cargo`, `fechaCreacion`) VALUES
(1, 'Juan', 'Gerente', '2018-02-19 22:05:48'),
(8, 'Juan Martín', 'Gerente', '2018-03-04 22:20:58'),
(21, 'Damián', 'Dueño', '2018-08-08 19:54:12'),
(22, 'Daniel', 'Relaciones Públicas', '2018-08-08 20:00:09'),
(23, 'Alberto Federico', 'Director Técnico', '2018-08-21 18:01:23'),
(25, 'Claudio', 'Químico', '2018-08-21 18:13:02'),
(26, 'Pedro', 'Jefe de ingeniería', '2019-02-18 22:08:25'),
(28, 'María Victoria', 'Representante legal', '2019-02-18 22:08:25'),
(30, 'Andrés', 'Mensajero', '2019-02-21 22:21:59'),
(31, 'María Eugenia', 'Representante legal', '2019-02-21 22:23:28'),
(34, 'Fabián', 'Jardinero', '2019-02-24 18:18:27'),
(35, 'Hugo', 'Barrendero', '2019-11-13 21:51:07'),
(36, 'Analía', 'Maquilladora', '2019-11-13 21:52:27'),
(37, 'Diana', 'Gerente', '2019-11-13 21:52:41'),
(38, 'Sofía', 'Director de Marketing', '2019-11-13 21:53:08');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `botonfavorito`
--

CREATE TABLE `botonfavorito` (
  `idBot` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `botonfavorito`
--

INSERT INTO `botonfavorito` (`idBot`, `nombre`) VALUES
(1, 'default'),
(2, 'cat'),
(3, 'rabbit');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `idCat` int(11) NOT NULL,
  `nombreES` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEN` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombrePT` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreFR` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreIT` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreGE` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreCA` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEU` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idCat`, `nombreES`, `nombreEN`, `nombrePT`, `nombreFR`, `nombreIT`, `nombreGE`, `nombreCA`, `nombreEU`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'Aparatos electrónicos', 'Electronic devices', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-23 23:57:37'),
(2, 'Hogar', 'Home', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-23 23:57:37'),
(6, 'otra', 'Other', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-23 23:57:37'),
(7, 'Coches', 'Cars', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-23 23:57:37'),
(8, '0 Otros', '0 Others', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-23 23:57:37'),
(9, 'Ropa', 'Clothe', NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2020-05-24 18:26:28'),
(10, 'Juegos', 'Games', NULL, NULL, NULL, NULL, NULL, NULL, 'pepe', '2020-08-13 15:18:16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `constantes`
--

CREATE TABLE `constantes` (
  `clave` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `valorDouble` double NOT NULL,
  `valorString100` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `valorText` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `constantes`
--

INSERT INTO `constantes` (`clave`, `valorDouble`, `valorString100`, `valorText`, `modificadoPor`, `fechaModificacion`) VALUES
('ivaEnvio', 21, NULL, NULL, 'pepe', '2020-09-26 15:48:13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuota`
--

CREATE TABLE `cuota` (
  `idCuo` int(11) NOT NULL,
  `cantidadCuotas` int(11) NOT NULL,
  `comisionAperturaPor` double NOT NULL,
  `comisionAperturaImp` double NOT NULL,
  `interesPor` double NOT NULL,
  `interesImp` double NOT NULL,
  `totalCompletoAPagar` double NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cuota`
--

INSERT INTO `cuota` (`idCuo`, `cantidadCuotas`, `comisionAperturaPor`, `comisionAperturaImp`, `interesPor`, `interesImp`, `totalCompletoAPagar`, `modificadoPor`, `fechaModificacion`) VALUES
(24, 6, 0.3, 2.06, 1.7, 3.4, 0, '', '2020-05-23 23:59:03'),
(26, 4, 0, 0, 1, 0.03, 0, '', '2020-05-23 23:59:03'),
(27, 2, 0, 0, 0, 0, 0, '', '2020-05-23 23:59:03'),
(28, 5, 0, 0, 0, 0, 0, '', '2020-05-23 23:59:03'),
(30, 3, 2, 1.4, 5, 0.58, 0, '', '2020-05-23 23:59:03'),
(31, 3, 5, 3, 2, 0.2, 0, '', '2020-05-23 23:59:03'),
(32, 1, 0, 0, 0, 0, 0, '', '2020-05-23 23:59:03'),
(33, 1, 0, 0, 0, 0, 0, '', '2020-05-23 23:59:03'),
(34, 3, 0, 0, 2, 0.05, 0, '', '2020-05-23 23:59:03'),
(35, 2, 0, 0, 0, 0, 0, '', '2020-05-23 23:59:03'),
(36, 2, 0, 0, 0, 0, 0, '', '2020-05-23 23:59:03'),
(37, 3, 0, 0, 2, 0.6, 180.6, 'pepe', '2020-07-20 11:35:03'),
(38, 3, 0, 0, 0, 0, 30, 'pepe', '2020-07-21 19:40:56'),
(39, 6, 0, 0, 1.5, 0.53, 120.53, 'pepe', '2020-07-22 15:39:45'),
(40, 6, 0, 0, 1.5, 0.61, 140.61, 'pepe', '2020-07-22 15:54:50'),
(41, 4, 1.3, 1.3, 2.25, 0.47, 101.77, 'pepe', '2020-07-22 18:06:45'),
(42, 4, 1.3, 1.3, 2.25, 0.47, 101.77, 'pepe', '2020-07-22 18:30:09'),
(43, 4, 1.3, 2.14, 2.25, 0.77, 167.19, 'pepe', '2020-07-22 18:32:10'),
(44, 4, 1.3, 0.83, 2.25, 0.3, 64.6, 'pepe', '2020-07-22 18:35:14'),
(45, 4, 1.3, 1.3, 2.25, 0.47, 101.77, 'pepe', '2020-07-22 18:41:39'),
(46, 4, 1.3, 0.83, 2.25, 0.3, 64.6, 'pepe', '2020-07-22 18:42:45'),
(47, 4, 0, 0, 0, 0, 110, 'pepe', '2020-07-22 18:44:50'),
(48, 4, 0, 0, 0, 0, 100.01, 'pepe', '2020-07-22 19:02:40'),
(49, 4, 0, 0, 0, 0, 101, 'pepe', '2020-07-22 19:03:40'),
(50, 3, 0, 0, 1, 0.1, 90.1, 'pepe', '2020-07-26 12:26:00'),
(51, 3, 0, 0, 0, 0, 90, 'pepe', '2020-07-26 19:46:09'),
(52, 3, 0, 0, 0, 0, 90, 'pepe', '2020-07-26 19:50:36'),
(53, 3, 0, 0, 0, 0, 90, 'pepe', '2020-07-26 20:10:54'),
(54, 4, 1, 1.6, 2.5, 0.83, 162.43, 'pepe', '2020-07-26 21:29:13'),
(55, 10, 1, 1.8, 2.5, 2.07, 183.87, 'pepe', '2020-07-26 21:30:11'),
(56, 4, 0, 0, 1, 5.42, 205.42, 'pepe', '2020-07-26 22:00:44'),
(57, 4, 0, 0, 1, 0.42, 200.42, 'pepe', '2020-07-26 22:01:37'),
(58, 12, 25.5, 51, 25.5, 28.69, 279.69, 'pepe', '2020-08-04 22:04:11'),
(59, 8, 0, 0, 0, 0, 20, 'pepe', '2020-08-16 21:24:51'),
(60, 4, 0, 0, 3, 0.13, 20.13, 'pepe', '2020-08-16 21:27:09'),
(61, 2, 2, 0.4, 0, 0, 20.4, 'pepe', '2020-08-16 21:27:46'),
(62, 4, 0.7, 0.17, 0.3, 0.02, 24.39, 'admin', '2020-08-30 23:21:36'),
(63, 12, 2, 46000.02, 3, 37546.1, 2383547.07, 'pepe', '2020-08-31 21:56:53');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuotadetalle`
--

CREATE TABLE `cuotadetalle` (
  `idCuDe` int(11) NOT NULL,
  `idCuo` int(11) NOT NULL,
  `importeSinInteres` double NOT NULL,
  `importeInteres` double NOT NULL,
  `importeCuota` double NOT NULL,
  `fecha` date NOT NULL,
  `capitalPendienteAntes` double NOT NULL,
  `capitalPendienteDespues` double NOT NULL,
  `numeroCuota` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cuotadetalle`
--

INSERT INTO `cuotadetalle` (`idCuDe`, `idCuo`, `importeSinInteres`, `importeInteres`, `importeCuota`, `fecha`, `capitalPendienteAntes`, `capitalPendienteDespues`, `numeroCuota`) VALUES
(1, 40, 23.33, 0.1, 23.43, '2020-08-01', 140, 116.67, 1),
(2, 40, 23.33, 0.1, 23.43, '2020-09-01', 116.67, 93.34, 2),
(3, 40, 23.33, 0.1, 23.43, '2020-10-01', 93.34, 70.01, 3),
(4, 40, 23.33, 0.1, 23.43, '2020-11-01', 70.01, 46.68, 4),
(5, 40, 23.33, 0.1, 23.43, '2020-12-01', 46.68, 23.35, 5),
(6, 40, 23.35, 0.11, 23.46, '2021-01-01', 23.35, 0, 6),
(7, 41, 26.3, 0.11, 26.41, '2020-08-01', 100, 73.7, 1),
(8, 41, 25, 0.11, 25.11, '2020-09-01', 73.7, 48.7, 2),
(9, 41, 25, 0.11, 25.11, '2020-10-01', 48.7, 23.7, 3),
(10, 41, 25, 0.14, 25.14, '2020-11-01', 23.7, -1.3, 4),
(11, 42, 25, 0.11, 26.41, '2020-08-01', 100, 75, 1),
(12, 42, 25, 0.11, 25.11, '2020-09-01', 75, 50, 2),
(13, 42, 25, 0.11, 25.11, '2020-10-01', 50, 25, 3),
(14, 42, 25, 0.14, 25.14, '2020-11-01', 25, 0, 4),
(15, 43, 41.07, 0.19, 43.4, '2020-08-01', 164.28, 123.21, 1),
(16, 43, 41.07, 0.19, 41.26, '2020-09-01', 123.21, 82.14, 2),
(17, 43, 41.07, 0.19, 41.26, '2020-10-01', 82.14, 41.07, 3),
(18, 43, 41.07, 0.2, 41.27, '2020-11-01', 41.07, 0, 4),
(19, 44, 15.86, 0.08, 16.77, '2020-08-01', 63.47, 47.61, 1),
(20, 44, 15.86, 0.08, 15.94, '2020-09-01', 47.61, 31.75, 2),
(21, 44, 15.86, 0.08, 15.94, '2020-10-01', 31.75, 15.89, 3),
(22, 44, 15.86, 0.06, 15.95, '2020-11-01', 15.89, 0.03, 4),
(23, 45, 25, 0.11, 26.41, '2020-08-01', 100, 75, 1),
(24, 45, 25, 0.11, 25.11, '2020-09-01', 75, 50, 2),
(25, 45, 25, 0.11, 25.11, '2020-10-01', 50, 25, 3),
(26, 45, 25, 0.14, 25.14, '2020-11-01', 25, 0, 4),
(27, 46, 15.86, 0.08, 16.77, '2020-08-01', 63.47, 47.61, 1),
(28, 46, 15.86, 0.08, 15.94, '2020-09-01', 47.61, 31.75, 2),
(29, 46, 15.86, 0.08, 15.94, '2020-10-01', 31.75, 15.89, 3),
(30, 46, 15.89, 0.06, 15.95, '2020-11-01', 15.89, 0, 4),
(31, 47, 25, 0, 25, '2020-08-01', 100, 75, 1),
(32, 47, 25, 10, 35, '2020-09-01', 75, 50, 2),
(33, 47, 25, 0, 25, '2020-10-01', 50, 25, 3),
(34, 47, 25, 0, 25, '2020-11-01', 25, 0, 4),
(35, 48, 25, 0, 25, '2020-08-01', 100, 75, 1),
(36, 48, 25, 0.01, 25.01, '2020-09-01', 75, 50, 2),
(37, 48, 25, 0, 25, '2020-10-01', 50, 25, 3),
(38, 48, 25, 0, 25, '2020-11-01', 25, 0, 4),
(39, 49, 25, 0, 25, '2020-08-01', 100, 75, 1),
(40, 49, 25, -10, 15, '2020-09-01', 75, 50, 2),
(41, 49, 25, 11, 36, '2020-10-01', 50, 25, 3),
(42, 49, 25, 0, 25, '2020-11-01', 25, 0, 4),
(43, 50, 30, 0.05, 30.05, '2020-08-01', 90, 60, 1),
(44, 50, 30, 0, 30, '2020-09-01', 60, 30, 2),
(45, 50, 30, 0.05, 30.05, '2020-10-01', 30, 0, 3),
(46, 51, 30, 0, 30, '2020-08-01', 90, 60, 1),
(47, 51, 30, 0, 30, '2020-09-01', 60, 30, 2),
(48, 51, 30, 0, 30, '2020-10-01', 30, 0, 3),
(49, 52, 30, 0, 30, '2020-08-01', 90, 60, 1),
(50, 52, 30, 0, 30, '2020-09-01', 60, 30, 2),
(51, 52, 30, 0, 30, '2020-10-01', 30, 0, 3),
(52, 53, 30, 0, 30, '2020-08-01', 90, 60, 1),
(53, 53, 30, 0, 30, '2020-09-01', 60, 30, 2),
(54, 53, 30, 0, 30, '2020-10-01', 30, 0, 3),
(55, 54, 40, 0.2, 41.8, '2020-08-01', 160, 120, 1),
(56, 54, 40, 0.2, 40.2, '2020-09-01', 120, 80, 2),
(57, 54, 40, 0.2, 40.2, '2020-10-01', 80, 40, 3),
(58, 54, 40, 0.23, 40.23, '2020-11-01', 40, 0, 4),
(59, 55, 18, 0.2, 20, '2020-08-01', 180, 162, 1),
(60, 55, 18, 0.2, 18.2, '2020-09-01', 162, 144, 2),
(61, 55, 18, 0.2, 18.2, '2020-10-01', 144, 126, 3),
(62, 55, 18, 0.2, 18.2, '2020-11-01', 126, 108, 4),
(63, 55, 18, 0.2, 18.2, '2020-12-01', 108, 90, 5),
(64, 55, 18, 0.2, 18.2, '2021-01-01', 90, 72, 6),
(65, 55, 18, 0.2, 18.2, '2021-02-01', 72, 54, 7),
(66, 55, 18, 0.2, 18.2, '2021-03-01', 54, 36, 8),
(67, 55, 18, 0.2, 18.2, '2021-04-01', 36, 18, 9),
(68, 55, 18, 0.27, 18.27, '2021-05-01', 18, 0, 10),
(69, 56, 50, 0.1, 50.1, '2020-08-01', 200, 150, 1),
(70, 56, 50, 0.1, 50.1, '2020-09-01', 150, 100, 2),
(71, 56, 50, 5.1, 55.1, '2020-10-01', 100, 50, 3),
(72, 56, 50, 0.12, 50.12, '2020-11-01', 50, 0, 4),
(73, 57, 50, 0.1, 50.1, '2020-08-01', 200, 150, 1),
(74, 57, 50, 0.1, 50.1, '2020-09-01', 150, 100, 2),
(75, 57, 50, 0.1, 50.1, '2020-10-01', 100, 50, 3),
(76, 57, 50, 0.12, 50.12, '2020-11-01', 50, 0, 4),
(77, 58, 16.66, 2.39, 70.05, '2020-09-01', 200, 183.34, 1),
(78, 58, 16.66, 2.39, 19.05, '2020-10-01', 183.34, 166.68, 2),
(79, 58, 16.66, 2.39, 19.05, '2020-11-01', 166.68, 150.02, 3),
(80, 58, 16.66, 2.39, 19.05, '2020-12-01', 150.02, 133.36, 4),
(81, 58, 16.66, 2.39, 19.05, '2021-01-01', 133.36, 116.7, 5),
(82, 58, 16.66, 2.39, 19.05, '2021-02-01', 116.7, 100.04, 6),
(83, 58, 16.66, 2.39, 19.05, '2021-03-01', 100.04, 83.38, 7),
(84, 58, 16.66, 2.39, 19.05, '2021-04-01', 83.38, 66.72, 8),
(85, 58, 16.66, 2.39, 19.05, '2021-05-01', 66.72, 50.06, 9),
(86, 58, 16.66, 2.39, 19.05, '2021-06-01', 50.06, 33.4, 10),
(87, 58, 16.66, 2.39, 19.05, '2021-07-01', 33.4, 16.74, 11),
(88, 58, 16.74, 2.4, 19.14, '2021-08-01', 16.74, 0, 12),
(89, 59, 2.5, 0, 2.5, '2020-09-01', 20, 17.5, 1),
(90, 59, 2.5, 0, 2.5, '2020-10-01', 17.5, 15, 2),
(91, 59, 2.5, 0, 2.5, '2020-11-01', 15, 12.5, 3),
(92, 59, 2.5, 0, 2.5, '2020-12-01', 12.5, 10, 4),
(93, 59, 2.5, 0, 2.5, '2021-01-01', 10, 7.5, 5),
(94, 59, 2.5, 0, 2.5, '2021-02-01', 7.5, 5, 6),
(95, 59, 2.5, 0, 2.5, '2021-03-01', 5, 2.5, 7),
(96, 59, 2.5, 0, 2.5, '2021-04-01', 2.5, 0, 8),
(97, 60, 5, 0.03, 5.03, '2020-09-01', 20, 15, 1),
(98, 60, 5, 0.03, 5.03, '2020-10-01', 15, 10, 2),
(99, 60, 5, 0.03, 5.03, '2020-11-01', 10, 5, 3),
(100, 60, 5, 0.04, 5.04, '2020-12-01', 5, 0, 4),
(101, 61, 10, 0, 10.4, '2020-09-01', 20, 10, 1),
(102, 61, 10, 0, 10, '2020-10-01', 10, 0, 2),
(103, 62, 6.05, 0, 6.22, '2020-09-01', 24.2, 18.15, 1),
(104, 62, 6.05, 0, 6.05, '2020-10-01', 18.15, 12.1, 2),
(105, 62, 6.05, 0, 6.05, '2020-11-01', 12.1, 6.05, 3),
(106, 62, 6.05, 0.02, 6.07, '2020-12-01', 6.05, 0, 4),
(107, 63, 191666.74, 3128.84, 240795.6, '2020-09-01', 2300000.95, 2108334.21, 1),
(108, 63, 191666.74, 3128.84, 194795.58, '2020-10-01', 2108334.21, 1916667.47, 2),
(109, 63, 191666.74, 3128.84, 194795.58, '2020-11-01', 1916667.47, 1725000.73, 3),
(110, 63, 191666.74, 3128.84, 194795.58, '2020-12-01', 1725000.73, 1533333.99, 4),
(111, 63, 191666.74, 3128.84, 194795.58, '2021-01-01', 1533333.99, 1341667.25, 5),
(112, 63, 191666.74, 3128.84, 194795.58, '2021-02-01', 1341667.25, 1150000.51, 6),
(113, 63, 191666.74, 3128.84, 194795.58, '2021-03-01', 1150000.51, 958333.77, 7),
(114, 63, 191666.74, 3128.84, 194795.58, '2021-04-01', 958333.77, 766667.03, 8),
(115, 63, 191666.74, 3128.84, 194795.58, '2021-05-01', 766667.03, 575000.29, 9),
(116, 63, 191666.74, 3128.84, 194795.58, '2021-06-01', 575000.29, 383333.55, 10),
(117, 63, 191666.74, 3128.84, 194795.58, '2021-07-01', 383333.55, 191666.81, 11),
(118, 63, 191666.81, 3128.86, 194795.67, '2021-08-01', 191666.81, 0, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datospersonales`
--

CREATE TABLE `datospersonales` (
  `idDatosPers` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido1` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido2` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `sexo` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `nacionalidad_idPais` int(11) NOT NULL,
  `dni` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observaciones` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `datospersonales_idUsr` int(11) NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `datospersonales`
--

INSERT INTO `datospersonales` (`idDatosPers`, `nombre`, `apellido1`, `apellido2`, `sexo`, `fechaNacimiento`, `nacionalidad_idPais`, `dni`, `email`, `telefono`, `observaciones`, `datospersonales_idUsr`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'José', 'Martínez', 'Suárez', 'Hombre', '1970-07-19', 1, '123456789A', 'pepe@pepe.com', '666555444', 'El usuario principal con el cual hacer pruebas', 1, '', '2020-05-23 23:59:35'),
(3, 'Roberto', 'Do Santos', '', 'Hombre', '1980-07-28', 0, '123456789D', 'roberto@roberto.com', '', '', 3, '', '2020-05-23 23:59:35'),
(4, 'Damián', 'Usheff', '', 'Hombre', '1976-10-30', 0, '123456789D', 'damian@damian.com', '666666666', '', 4, '', '2020-05-23 23:59:35'),
(32, 'Damián', 'Usheff', 'Vellianitis', 'Hombre', '1976-10-30', 0, '12345678A', 'damian@damian.com', '698765435', 'Observaciones para ser guardadas en base de datos', 44, '', '2020-05-23 23:59:35'),
(34, 'María Antonieta', 'De las Nieves', 'Salgados', 'Mujer', '1973-03-16', 0, '123456789D', 'asdfasf@kjhicb.com', '638638638', '', 46, 'dora', '2020-05-31 21:43:10'),
(38, 'Lara', 'Fuentes', 'Galeano', 'Mujer', '2000-05-01', 0, '12345678S', 'asdfasf@kjhicb.com', '600000006', '', 55, '', '2020-05-23 23:59:35'),
(41, 'Saúl', 'Rodríguez', 'Becerra', NULL, '2002-02-02', 0, '12345678A', 'saul@saul.com', '600000002', 'Alguna observación para ver el reseteo de la contraseña', 58, 'dora', '2020-05-31 21:47:51'),
(43, 'Alejandro', 'Vázquez', 'López', 'Hombre', '1984-02-25', 0, '', 'asdfasf@kjhicb.com', '', '', 60, '', '2020-05-23 23:59:35'),
(44, 'Bárbara', 'López', 'Aguirre Juarez', 'Mujer', NULL, 0, '', 'barbara@gmail.com', '', '', 61, '', '2020-05-23 23:59:35'),
(45, 'Juan', 'Pérez', 'Arregui', 'Hombre', '1974-07-14', 0, '12345678J', 'juan@juan.com', '699999999', '', 62, '', '2020-05-23 23:59:35'),
(46, 'Walter', 'Tamanaha', 'Prado', 'Hombre', '1976-10-06', 0, '', 'walter@gmail.com', '', '', 63, '', '2020-05-23 23:59:35'),
(47, 'Daniela', 'Solar Gaudio', 'Duero', 'Mujer', NULL, 1, '', 'daniela@daniela.com', '', '', 64, 'pepe', '2020-07-16 12:20:47'),
(52, 'Dora', 'Núñez', 'Gala', 'Mujer', '1979-05-14', 0, '', 'dora@dora.com', '659659658', '', 69, 'dora', '2020-07-16 12:22:40'),
(56, 'Servicio4aaa', 'Servicio', 'Cuatro', 'Hombre', '2003-02-01', 5, '', 'servicio4@gmail.com', '', '', 74, 'admin', '2020-09-24 19:53:13'),
(57, 'Servicio5', 'Servicio', 'Cinco', 'Mujer', '1972-09-30', 0, '12345678A', 'servicio5@gmail.com', '600000000', 'us us', 75, '', '2020-05-23 23:59:35'),
(60, 'Servicio8', 'Servicio', 'Seis', 'Hombre', '2008-08-08', 0, '', 'servicio8@gmail.com', '', 'Observaciones8', 78, '', '2020-05-23 23:59:35'),
(63, 'Felipe', 'Sexto', '', 'Mujer', NULL, 0, '', 'sanlore@gmail.com', '678678678', '', 81, 'pepe', '2020-07-02 21:38:44'),
(65, 'Usuario', 'Con el Rol', 'De Usuario', 'Hombre', '1979-07-04', 1, '', 'usuario@gmail.com', '658658659', '', 83, 'usuario', '2020-09-19 12:17:06'),
(66, 'Cliente', 'Con el Rol', 'De Cliente', 'Mujer', '1980-06-10', 3, '', 'cliente@gmail.com', '661661661', '', 84, '', '2020-05-23 23:59:35'),
(67, 'Administrador', 'Con Rol', 'Admin', 'Mujer', '1980-10-10', 6, '01234567A', 'admin@gmail.com', '600111227', 'Administrador para test', 85, 'admin', '2020-09-19 12:18:13'),
(72, 'Virus', 'Chino', '', 'Hombre', '2019-12-20', 1, '', 'corona@vir.us', '', '', 90, '', '2020-05-23 23:59:35'),
(73, 'Graciela', 'Rodríguez', 'Sánchez', 'Mujer', '1974-06-21', 1, '01234567A', 'graciela@graciela.com', '654987987', 'La observación de Graciela', 91, '', '2020-05-23 23:59:35'),
(75, 'Sabrina', 'Pérez', 'Aguado', 'Mujer', '1980-07-18', 4, '18868446M', 'sabrina@sabrina.com', '612612612', 'La observación de Sabrina', 93, '', '2020-05-23 23:59:35'),
(76, 'Sergio', 'Fernández', '', NULL, NULL, 0, '', 'sergio@gmail.com.es', '', '', 94, '', '2020-05-23 23:59:35'),
(77, 'Yolanda', 'Artunez', '', NULL, NULL, 0, '', 'yolanda@gmail.com', '', '', 95, '', '2020-05-23 23:59:35'),
(78, 'Florencia', 'Peña', '', NULL, NULL, 0, '', 'florencia@gmail.com', '', '', 96, '', '2020-05-23 23:59:35'),
(79, 'Claudio', 'Paul', 'Caniggia', NULL, NULL, 0, '', 'claudio@gmail.com', '', '', 97, 'admin', '2020-05-24 18:28:21'),
(80, 'Sergio', 'Fernández', 'López', 'Hombre', '1980-06-19', 2, '01234567R', 'sergiof@gmail.com', '652652652', 'adfasdf', 105, 'sergiof', '2020-07-02 21:41:08'),
(81, 'Jacinta', 'Pichimahuida', '', 'Mujer', '1960-06-15', 17, '', 'jacinta@gmail.com', '', '', 106, 'jacinta', '2020-09-19 10:46:32'),
(82, 'Pascualino', 'Serrano', '', 'Mujer', NULL, 0, '', 'pascualino@gmail.com', '', '', 107, 'OWN USER', '2020-09-19 12:12:54'),
(83, 'Evangelina', 'Julie', '', 'Mujer', NULL, 0, '', 'evangelina@gmail.com', '', '', 108, 'OWN USER', '2020-09-19 12:37:52'),
(84, 'Salomé', 'Arístides', '', NULL, NULL, 0, '', 'salome@gmail.com', '', '', 109, 'admin', '2020-09-19 12:38:46');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `idDir` int(11) NOT NULL,
  `tipoVia` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreVia` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `resto` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cp` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provincia` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ciudad` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pais_idPais` int(30) NOT NULL,
  `idDatosPers` int(11) NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `direccion`
--

INSERT INTO `direccion` (`idDir`, `tipoVia`, `nombreVia`, `numero`, `resto`, `cp`, `provincia`, `ciudad`, `pais_idPais`, `idDatosPers`, `modificadoPor`, `fechaModificacion`) VALUES
(7, 'road.Street', 'Menor', '2', NULL, '28005', 'Madrid', 'Madrid', 0, 1, '', '2020-05-24 00:00:06'),
(11, 'road.Street', 'Ana de Austria', '50', NULL, '28050', 'Madrid', 'Madrid', 2, 4, '', '2020-05-24 00:00:06'),
(12, 'road.Avenue', 'Niceto Alcalá Zamora', '200', 'Urbanización El sol naciente', '28050', 'Madrid', 'Madrid', 1, 4, '', '2020-05-24 00:00:06'),
(13, 'road.Square', 'Hidalgo', '3', NULL, '99009', 'Vallladolid', 'Valladolid', 1, 1, '', '2020-05-24 00:00:06'),
(15, 'road.Street', 'Martín', '23', NULL, '28050', 'Madrid', 'Sanchinaroo', 0, 3, '', '2020-05-24 00:00:06'),
(16, 'road.Street', 'Américo Castro', '100', '2ºB', '28050', 'Madrid', 'Madrid', 0, 32, '', '2020-05-24 00:00:06'),
(18, 'road.Street', 'Américo Castro 104 2ºB', '', '', '28050', 'Madrid', 'Madrid', 0, 34, '', '2020-05-24 00:00:06'),
(20, 'road.Square', 'Mayor', '1', '', '28001', 'Madrid', 'Madrid', 0, 32, '', '2020-05-24 00:00:06'),
(21, 'road.Avenue', 'Velazquez', '35', '', '28027', 'Madrid', 'Madrid', 0, 45, '', '2020-05-24 00:00:06'),
(22, 'road.Paseo', 'Extremadura', '30', '', '28024', '', 'Madrid', 0, 47, '', '2020-05-24 00:00:06'),
(23, 'road.Paseo', 'Castellana', '124 6ºA', 'Portal C', '28006', 'Madrid', 'Madrid', 1, 72, '', '2020-05-24 00:00:06'),
(24, 'road.Boulevard', 'Primero', '2', '', '12345', '', 'Amsterdam', 12, 4, '', '2020-05-24 00:00:06'),
(25, 'road.Paseo', 'Menor', '2', '', '28050', 'MADRID', 'MADRID', 1, 79, 'admin', '2020-05-24 18:38:14'),
(26, 'road.Road', 'Vicálvaro', '', 'Altura km 25', '28061', 'Madrid', 'Madrid', 1, 38, 'pepe', '2020-07-07 20:33:54');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccionempresa`
--

CREATE TABLE `direccionempresa` (
  `idDirEmp` int(11) NOT NULL,
  `tipoVia` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreVia` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `resto` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cp` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provincia` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ciudad` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pais_idPais` int(30) NOT NULL,
  `idEmp` int(10) NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `direccionempresa`
--

INSERT INTO `direccionempresa` (`idDirEmp`, `tipoVia`, `nombreVia`, `numero`, `resto`, `cp`, `provincia`, `ciudad`, `pais_idPais`, `idEmp`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'road.Route', 'Mayor', '6', 'local', '28001', 'Madrid', 'Madrid', 16, 1, '', '2020-05-24 00:00:36'),
(2, 'road.Avenue', 'Manuel Becerra', 'Bajo 3', '', '47024', 'León', 'Cardales', 1, 2, '', '2020-05-24 00:00:36'),
(3, 'road.Square', 'Serrano', '63', 'Pasillo 3', '1856', '', 'CABA', 4, 1, '', '2020-05-24 00:00:36'),
(4, 'road.Road', 'San Jerónimo', '2', '', '28006', 'Madrid', 'Madrid', 1, 1, '', '2020-05-24 00:00:36'),
(5, 'road.Square', 'Manuel Becerra', '5', '', '47024', 'León', 'Cardales', 1, 8, 'admin', '2020-05-24 18:39:35'),
(6, 'road.Paseo', 'San Jerónimo', '4', '', '28007', 'Madrid', 'Madrid', 1, 9, 'pepe', '2020-07-07 20:36:16'),
(7, 'road.Paseo', 'Colón', '10', '', '28004', '', 'Madrid', 0, 12, 'pepe', '2020-07-07 20:36:51'),
(8, 'road.Street', 'San Jerónimo', '10', '', '28006', 'Madrid', 'Madrid', 1, 16, 'pepe', '2020-08-18 21:37:21');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccionempresapropia`
--

CREATE TABLE `direccionempresapropia` (
  `idDirPropia` int(11) NOT NULL,
  `tipoVia` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreVia` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `resto` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cp` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provincia` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ciudad` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pais_idPais` int(11) NOT NULL,
  `idPropia` int(11) NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `direccionempresapropia`
--

INSERT INTO `direccionempresapropia` (`idDirPropia`, `tipoVia`, `nombreVia`, `numero`, `resto`, `cp`, `provincia`, `ciudad`, `pais_idPais`, `idPropia`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'road.Paseo', 'Castellana', '110', '', '28006', 'Madrid', 'Madrid', 1, 1, 'pepe', '2020-08-19 22:20:36'),
(2, 'road.Street', 'Sarmiento', '21', '', '24004', 'Valladolid', 'Valladolid', 1, 2, 'pepe', '2020-08-19 22:20:43'),
(3, 'road.Paseo', 'San Ildefonso', '2', '', '34057', 'Lugo', 'Lugo', 1, 3, 'pepe', '2020-08-20 17:04:14'),
(4, 'road.Street', 'Castellana', '2', 'asdfasdfasdf', '28050', 'Madrid', 'MADRID', 1, 4, 'pepe', '2020-08-20 17:05:56'),
(5, 'road.Route', '27', '21', 'dsafsadf', '28050', 'MADRID', 'MADRID', 1, 5, 'pepe', '2020-08-20 17:06:37'),
(6, 'road.Paseo', 'Castellana', '72', 'planta 4', '28006', 'Madrid', 'Madrid', 1, 6, 'pepe', '2020-08-20 17:24:28'),
(7, 'road.Avenue', 'Sarmiento', '2', '', '28050', 'Madrid', 'Madrid', 1, 7, 'pepe', '2020-09-26 16:00:28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `idEmp` int(11) NOT NULL,
  `nombreComercial` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipoSociedad` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `actividad` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `cif` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `paginaWeb` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observaciones` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`idEmp`, `nombreComercial`, `tipoSociedad`, `actividad`, `cif`, `email`, `paginaWeb`, `telefono`, `fax`, `observaciones`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'Empresa primera', 'SL', 'Venta de producto principal', 'D70055413', 'empresa.primera@gmail.com', 'www.empresaprimera.com', '912222222', '912222223', '', '', '2020-05-24 00:01:07'),
(2, 'Primer empresa desde front', 'SA', 'Fabrican diversión', 'V5115871E', 'diver@diver.com', 'www.desdefront.com', '910000005', '913333333', 'Agrego la observación correspondiente', '', '2020-05-24 00:01:07'),
(3, 'Tercera empresa dada de alta', 'Cooperativa', 'Hacen festivales', 'Q0263228I', 'festivales@festivales.com', 'www.hacenfestivales.com', '962222222', '', 'Festivales en la zona norte de España', '', '2020-05-24 00:01:07'),
(5, 'Dupuitrago', 'SA', '', 'H88268842', 'info@dupuitrago.com', '', '', '', '', '', '2020-05-24 00:01:07'),
(6, 'Qualisoftware', 'SL', 'Desarrollo de software', '', 'quali@software.com', '', '', '', '', '', '2020-05-24 00:01:07'),
(8, 'Empresa borrar', 'SL', '', '', '', '', '', '', '', '', '2020-05-24 00:01:07'),
(9, 'La prueba de la organización', 'SA', 'Ver si van todos los datos', 'E56898976', 'prueba@organizacion.com', 'www.prueba.organizacion.es', '915235236', '915235237', 'Observación de la empresa Prueba de la organización.', '', '2020-05-24 00:01:07'),
(11, 'Muebles Eladio', 'SL', '', '', '', '', '', '', '', '', '2020-05-24 00:01:07'),
(12, 'Peluquería Bernarda', 'SL', '', '', '', '', '', '', '', '', '2020-05-24 00:01:07'),
(14, 'Sinesio Peluqueros', 'SL', 'Peluquería', '', '', '', '', '', '', 'admin', '2020-05-24 18:40:33'),
(15, 'Embotelladora Serinada', 'SA', '', 'R9710960G', 'serinada@serinada.es', 'www.serinada.es', '918528520', '', '', 'admin', '2020-07-27 16:43:10'),
(16, 'Cristales Beirut', 'SL', 'Cristalería', 'C48473946', 'beirut@gmail.com', 'www.cristaleriabeirut.com', '915554444', '915554443', 'Empresa dedicada a la instalación de cristales de todo tipo', 'pepe', '2020-08-18 20:06:56'),
(17, 'Inmobiliaria Careti', 'SL', '', '', '', '', '', '', '', 'pepe', '2020-08-18 20:11:54'),
(18, 'Soldadores Urriaga', 'SL', '', '', '', '', '', '', '', 'pepe', '2020-08-18 20:14:16'),
(19, 'Vivero Almareque', 'SL', '', '', '', '', '', '', '', 'pepe', '2020-08-18 20:16:13'),
(20, 'Cerraduras Kenedy', 'SL', '', '', '', '', '', '', '', 'pepe', '2020-08-18 20:17:48');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresapropia`
--

CREATE TABLE `empresapropia` (
  `idPropia` int(11) NOT NULL,
  `razonSocial` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `cif` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `fax` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `facturacion` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `empresapropia`
--

INSERT INTO `empresapropia` (`idPropia`, `razonSocial`, `cif`, `telefono`, `fax`, `email`, `facturacion`) VALUES
(6, 'Company', 'F25564477', '914564567', '914564568', 'company@company.es', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `idEst` int(11) NOT NULL,
  `nombreES` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEN` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombrePT` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreFR` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreIT` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreGE` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreCA` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEU` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`idEst`, `nombreES`, `nombreEN`, `nombrePT`, `nombreFR`, `nombreIT`, `nombreGE`, `nombreCA`, `nombreEU`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'Enviado', 'Sent', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:01:35'),
(3, 'Pendiente de envío', 'Pending to send', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:01:35'),
(5, 'Cancelado', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:01:35'),
(6, 'Agregar stock', 'Add stock', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:01:35'),
(7, 'Quitar stock', 'Remove stock', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:01:35'),
(8, 'En tránsito', 'In transit', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:01:35'),
(9, 'Tramitado', 'Tramitado', NULL, NULL, NULL, NULL, NULL, NULL, 'pepe', '2020-05-24 18:41:21');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `idFac` int(11) NOT NULL,
  `compra` tinyint(1) NOT NULL,
  `totalSinIvaEnvDescfac` double NOT NULL,
  `descuentoTotal` double NOT NULL,
  `descuentoImporteProductos` double NOT NULL,
  `descuentoImporteFactura` double NOT NULL,
  `descuentoImporteTotal` double NOT NULL,
  `importeEnvioSinIva` double NOT NULL,
  `envioIvaPor` double NOT NULL,
  `envioIvaImp` double NOT NULL,
  `productosIvaImp` double NOT NULL,
  `totalSinIvaConDescfac` double NOT NULL,
  `ivaTotal` double NOT NULL,
  `ivaImporteTotal` double NOT NULL,
  `importeTotal` double NOT NULL,
  `fechaCompra` datetime NOT NULL,
  `fechaEntrega` datetime DEFAULT NULL,
  `idEst` int(11) NOT NULL,
  `observaciones` varchar(300) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idFor` int(11) NOT NULL,
  `creadoPor` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `idCuo` int(11) NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`idFac`, `compra`, `totalSinIvaEnvDescfac`, `descuentoTotal`, `descuentoImporteProductos`, `descuentoImporteFactura`, `descuentoImporteTotal`, `importeEnvioSinIva`, `envioIvaPor`, `envioIvaImp`, `productosIvaImp`, `totalSinIvaConDescfac`, `ivaTotal`, `ivaImporteTotal`, `importeTotal`, `fechaCompra`, `fechaEntrega`, `idEst`, `observaciones`, `idFor`, `creadoPor`, `idCuo`, `modificadoPor`, `fechaModificacion`) VALUES
(7, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 49.5, 495, '2020-01-17 20:51:06', NULL, 6, '', 4, 'admin', 0, '', '2020-05-24 00:02:04'),
(8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 50.82, 242, '2020-01-18 00:00:00', NULL, 7, 'Primera prueba QUITAR unidades. TV Sanyo 15 pulgadas. Saco 1 unidad. Importe 242€. IVA 21%', 4, 'admin', 0, '', '2020-05-24 00:02:04'),
(9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 42, 242, '2020-03-28 00:00:00', NULL, 1, '', 4, 'admin', 0, '', '2020-05-24 00:02:04'),
(64, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 118.93, 685.27, '2020-03-01 00:00:00', NULL, 6, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(66, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, '2020-05-01 00:00:00', NULL, 1, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(67, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, '2020-06-01 00:00:00', NULL, 8, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(68, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, '2020-07-01 00:00:00', NULL, 8, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(69, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, '2020-08-01 00:00:00', NULL, 8, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(72, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, '2020-03-22 00:00:00', NULL, 3, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(74, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, '2020-02-15 00:00:00', NULL, 5, '', 4, 'pepe', 0, 'admin', '2020-09-24 19:52:20'),
(75, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-02-15 11:40:35', NULL, 7, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(85, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, '2020-06-01 00:00:00', NULL, 6, '', 4, 'pepe', 26, '', '2020-05-24 00:02:04'),
(86, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, '2020-02-15 14:38:39', NULL, 6, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(87, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, '2020-02-15 14:38:53', NULL, 7, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(88, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 26.03, 150, '2020-03-16 23:18:40', NULL, 6, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(90, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, '2020-05-01 00:00:00', NULL, 6, '', 4, 'pepe', 27, '', '2020-05-24 00:02:04'),
(91, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-06-01 00:00:00', NULL, 6, '', 4, 'pepe', 27, '', '2020-05-24 00:02:04'),
(92, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, '2020-05-01 00:00:00', NULL, 6, '', 4, 'pepe', 28, '', '2020-05-24 00:02:04'),
(94, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 6, '', 4, 'pepe', 28, '', '2020-05-24 00:02:04'),
(95, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-08-01 00:00:00', NULL, 6, '', 4, 'pepe', 28, '', '2020-05-24 00:02:04'),
(96, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-09-01 00:00:00', NULL, 6, '', 4, 'pepe', 28, '', '2020-05-24 00:02:04'),
(100, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 3.96, 70, '2020-05-01 00:00:00', NULL, 1, 'Con la pantalla minimizada', 4, 'pepe', 30, '', '2020-05-24 00:02:04'),
(101, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, '2020-06-01 00:00:00', NULL, 6, 'Con la pantalla minimizada', 4, 'pepe', 30, '', '2020-05-24 00:02:04'),
(102, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, '2020-07-01 00:00:00', NULL, 6, 'Con la pantalla minimizada', 4, 'pepe', 30, '', '2020-05-24 00:02:04'),
(103, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 2.85, 60, '2020-05-01 00:00:00', NULL, 6, 'Observación de agregado castañas', 4, 'pepe', 31, '', '2020-05-24 00:02:04'),
(104, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, '2020-06-01 00:00:00', NULL, 6, 'Observación de agregado castañas', 4, 'pepe', 31, '', '2020-05-24 00:02:04'),
(105, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, '2020-07-01 00:00:00', NULL, 6, 'Observación de agregado castañas', 4, 'pepe', 31, '', '2020-05-24 00:02:04'),
(106, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, '2020-05-03 00:00:00', NULL, 6, '', 4, 'pepe', 32, '', '2020-05-24 00:02:04'),
(107, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, '2020-05-03 00:00:00', NULL, 8, '', 4, 'pepe', 33, '', '2020-05-24 00:02:04'),
(108, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 2.6, 15, '2020-06-01 00:00:00', NULL, 6, '', 4, 'pepe', 34, '', '2020-05-24 00:02:04'),
(109, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, '2020-07-01 00:00:00', NULL, 1, '', 4, 'pepe', 34, '', '2020-05-24 00:02:04'),
(110, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, '2020-08-01 00:00:00', NULL, 6, '', 4, 'pepe', 34, '', '2020-05-24 00:02:04'),
(111, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-06-01 00:00:00', NULL, 6, '', 4, 'pepe', 35, '', '2020-05-24 00:02:04'),
(112, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 6, '', 4, 'pepe', 35, '', '2020-05-24 00:02:04'),
(113, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-05-03 14:02:22', NULL, 6, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(114, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, '2020-06-01 00:00:00', NULL, 3, '', 4, 'pepe', 36, 'pepe', '2020-07-07 21:04:29'),
(115, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 6, '', 4, 'pepe', 36, '', '2020-05-24 00:02:04'),
(116, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, '2020-05-24 20:42:23', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-05-24 18:42:23'),
(117, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 10.41, 60, '2020-07-18 00:22:45', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-07-17 22:22:45'),
(118, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 23.42, 135, '2020-07-18 21:02:02', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-07-18 19:02:02'),
(119, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 31.23, 180, '2020-07-20 13:35:03', NULL, 6, '', 4, 'pepe', 37, 'pepe', '2020-07-20 11:35:03'),
(120, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 26.03, 150, '2020-07-21 20:53:42', NULL, 6, 'Agrego 3 unidades por 150 euros con un IVA del 21%', 4, 'pepe', 0, 'pepe', '2020-07-21 18:54:34'),
(121, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 36.44, 210, '2020-07-21 21:14:53', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-07-21 19:14:53'),
(122, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, '2020-07-21 21:19:13', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-07-21 19:19:14'),
(123, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, '2020-07-21 21:22:40', NULL, 7, '', 4, 'pepe', 0, 'pepe', '2020-07-21 19:22:40'),
(124, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 5.2, 30, '2020-07-21 21:31:06', NULL, 6, 'Se agrega 1 por 30 euros y 21% de IVA en 3 cuotas sin interés ni comisión de apertura', 4, 'pepe', 38, 'pepe', '2020-07-21 19:41:07'),
(125, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 20.82, 120, '2020-07-22 17:39:45', NULL, 6, '', 4, 'pepe', 39, 'pepe', '2020-07-22 15:39:52'),
(126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-07-22 17:54:20', NULL, 7, '', 4, 'pepe', 0, 'pepe', '2020-07-22 15:54:20'),
(127, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 24.29, 140, '2020-07-22 17:54:50', NULL, 6, '', 4, 'pepe', 40, 'pepe', '2020-07-22 15:54:50'),
(128, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 17.35, 100, '2020-07-22 20:06:45', NULL, 6, '', 4, 'pepe', 41, 'pepe', '2020-07-22 18:06:45'),
(129, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 17.35, 100, '2020-07-22 20:30:09', NULL, 6, '', 4, 'pepe', 42, 'pepe', '2020-07-22 18:30:09'),
(130, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 28.51, 164.28, '2020-07-22 00:00:00', NULL, 5, '', 4, 'pepe', 43, 'admin', '2020-09-19 17:19:55'),
(131, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 11.01, 63.47, '2020-07-22 20:35:14', NULL, 6, '', 4, 'pepe', 44, 'pepe', '2020-07-22 18:35:14'),
(132, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 17.35, 100, '2020-07-22 20:41:39', NULL, 6, '', 4, 'pepe', 45, 'pepe', '2020-07-22 18:41:39'),
(133, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 11.01, 63.47, '2020-07-22 20:42:44', NULL, 6, '', 4, 'pepe', 46, 'pepe', '2020-07-22 18:42:45'),
(134, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 17.35, 100, '2020-07-22 20:44:50', NULL, 6, '', 4, 'pepe', 47, 'pepe', '2020-07-22 18:44:50'),
(135, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 17.35, 100, '2020-07-22 21:02:40', NULL, 6, '', 4, 'pepe', 48, 'pepe', '2020-07-22 19:02:40'),
(136, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 17.35, 100, '2020-07-22 21:03:40', NULL, 6, '', 4, 'pepe', 49, 'pepe', '2020-07-22 19:03:40'),
(137, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, '2020-07-26 14:26:00', NULL, 6, '', 4, 'pepe', 50, 'pepe', '2020-07-26 12:26:00'),
(138, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, '2020-07-26 16:26:18', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-07-26 14:26:18'),
(139, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, '2020-07-26 16:27:10', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-07-26 14:27:10'),
(140, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, '2020-07-26 16:28:50', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-07-26 14:28:50'),
(141, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, '2020-07-26 21:43:59', NULL, 6, '', 4, 'pepe', 51, 'pepe', '2020-07-26 19:46:09'),
(142, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, '2020-07-26 21:49:20', NULL, 6, '', 4, 'pepe', 52, 'pepe', '2020-07-26 19:50:36'),
(143, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, '2020-07-26 22:10:14', NULL, 6, '', 4, 'pepe', 53, 'pepe', '2020-07-26 20:10:54'),
(144, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 26.03, 150, '2020-07-26 23:17:12', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-07-26 21:17:12'),
(145, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 150, '2020-07-26 23:18:08', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-07-26 21:18:08'),
(146, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 27.76, 160, '2020-07-26 23:29:00', NULL, 6, '', 4, 'pepe', 54, 'pepe', '2020-07-26 21:29:13'),
(147, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 31.23, 180, '2020-07-26 23:30:11', NULL, 6, '', 4, 'pepe', 55, 'pepe', '2020-07-26 21:30:11'),
(148, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 34.71, 200, '2020-07-27 00:00:44', NULL, 6, '', 4, 'pepe', 56, 'pepe', '2020-07-26 22:00:44'),
(149, 1, 0, 22.5, 0, 0, 88.65, 0, 0, 0, 0, 0, 21, 34.71, 200, '2020-07-27 00:01:37', NULL, 6, 'Voy a poner unas observaciones para intentar que se pase del ancho y que salte a la segunda línea', 4, 'pepe', 57, 'pepe', '2020-08-04 17:43:29'),
(150, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 19.09, 110, '2020-08-01 13:52:29', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-08-01 11:52:29'),
(153, 1, 0, 5, 0, 3.5, 3.5, 4, 0, 0, 0, 0, 10, 6.36, 70, '2020-08-02 23:41:37', NULL, 6, 'Sin cuotas, con envío y con descuento', 4, 'pepe', 0, 'pepe', '2020-09-06 11:37:12'),
(159, 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 67, '2020-08-03 00:03:55', NULL, 6, 'Sin cuotas, con envío, sin descuento', 4, 'pepe', 0, 'pepe', '2020-08-16 21:21:32'),
(160, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 451.23, 2600, '2020-08-03 18:01:28', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-08-03 16:01:28'),
(161, 1, 0, 10, 0, 1000, 1000, 0, 0, 0, 0, 0, 21, 43469.65, 250468, '2020-08-03 18:19:04', NULL, 6, 'Sin cuotas, sin envío, con descuento', 4, 'pepe', 0, 'pepe', '2020-09-06 11:35:02'),
(162, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 34.71, 200, '2020-08-05 00:04:11', NULL, 6, 'Con 12 cuotas', 4, 'pepe', 58, 'pepe', '2020-08-16 21:20:32'),
(163, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 3.45, 38, '2020-08-11 15:37:31', NULL, 6, 'Sin cuotas, sin envío y sin descuento', 4, 'pepe', 0, 'pepe', '2020-08-16 21:20:13'),
(164, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 1.81, 20, '2020-08-16 23:24:51', NULL, 6, 'Con 8 cuotas', 4, 'pepe', 59, 'pepe', '2020-08-16 21:24:51'),
(165, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 3.47, 20, '2020-08-16 23:27:09', NULL, 6, 'Con 4 cuotas', 4, 'pepe', 60, 'pepe', '2020-08-16 21:27:09'),
(166, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 3.47, 20, '2020-08-16 23:27:46', NULL, 6, 'Con 2 cuotas', 4, 'pepe', 61, 'pepe', '2020-08-16 21:27:46'),
(167, 1, 20, 0, 0, 0, 0, 0, 0, 0, 0, 20, 21, 4.2, 24.2, '2020-08-31 00:27:56', NULL, 6, 'Probando factura modificada. Agrego 2 unidades de 10€ sin iva. El iva es 21%. Total 24,20€. Sin cuotas.', 4, 'admin', 0, 'admin', '2020-08-30 22:27:56'),
(168, 0, 53.71, 0, 0, 0, 0, 0, 0, 0, 0, 53.71, 21, 11.28, 65, '2020-08-31 00:33:11', NULL, 7, 'Quito 2 unidades ingresando 65€ con un iva del 21%. Tendría que ser 53,72@ de producto y 11,28€ de iva. El valor del producto individual sin iva sería 26,86€', 4, 'admin', 0, 'admin', '2020-08-30 22:33:11'),
(169, 1, 68.6, 0, 0, 0, 0, 0, 0, 0, 0, 68.6, 21, 14.4, 83, '2020-08-31 00:43:46', NULL, 6, '', 4, 'admin', 0, 'admin', '2020-08-30 22:43:46'),
(170, 0, 53.72, 0, 0, 0, 0, 0, 0, 0, 0, 53.72, 21, 11.28, 65, '2020-08-31 00:45:11', NULL, 7, 'Quito 2 unidades ingresando 65€ con un iva del 21%. Tendría que ser 53,72€ de producto y 11,28€ de iva. El valor del producto individual sin iva sería 26,86€', 4, 'admin', 0, 'admin', '2020-08-30 22:45:11'),
(171, 1, 20, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 20, '2020-08-31 00:54:11', NULL, 6, '', 4, 'admin', 0, 'admin', '2020-08-30 22:54:11'),
(172, 1, 20, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 20, '2020-08-31 01:13:44', NULL, 6, '', 4, 'admin', 0, 'admin', '2020-08-30 23:13:44'),
(173, 1, 20, 0, 0, 0, 0, 0, 0, 0, 0, 20, 21, 4.2, 24.2, '2020-08-31 00:00:00', NULL, 8, '', 4, 'admin', 0, 'admin', '2020-09-19 17:14:03'),
(174, 0, 53.72, 0, 0, 0, 0, 0, 0, 0, 0, 53.72, 21, 11.28, 65, '2020-08-31 01:15:02', NULL, 7, 'Quito 2 unidades ingresando 65€ con un iva del 21%. Tendría que ser 53,72@ de producto y 11,28€ de iva. El valor del producto individual sin iva sería 26,86€', 4, 'admin', 0, 'admin', '2020-08-30 23:15:02'),
(175, 1, 20, 0, 0, 0, 0, 0, 0, 0, 0, 20, 21, 4.2, 24.2, '2020-08-31 01:21:36', NULL, 6, '', 4, 'admin', 62, 'admin', '2020-08-30 23:21:36'),
(176, 1, 2277228.66, 0, 0, 0, 0, 0, 0, 0, 0, 2277228.66, 1, 22772.29, 2300000.95, '2020-08-31 23:56:53', NULL, 6, 'Para acomodar columnas con números grandes', 4, 'pepe', 63, 'pepe', '2020-08-31 21:56:53'),
(177, 1, 7.23, 0, 0, 0, 0, 0, 0, 0, 0, 7.23, 21, 1.52, 8.75, '2020-09-01 23:55:40', NULL, 6, '', 4, 'admin', 0, 'admin', '2020-09-01 21:55:40'),
(178, 1, 15, 20, 0, 3, 3, 2, 21, 0.42, 1.2, 14, 0, 1.62, 15.62, '2020-09-06 14:40:40', NULL, 6, '', 4, 'admin', 0, 'admin', '2020-09-06 21:25:31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturaenviarfacturar`
--

CREATE TABLE `facturaenviarfacturar` (
  `idEnFa` int(11) NOT NULL,
  `nombre` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  `direccion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cp` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ciudad` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provincia` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pais` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `facturar` tinyint(4) NOT NULL,
  `enviar` tinyint(4) NOT NULL,
  `idFac` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `facturaenviarfacturar`
--

INSERT INTO `facturaenviarfacturar` (`idEnFa`, `nombre`, `direccion`, `cp`, `ciudad`, `provincia`, `pais`, `telefono`, `facturar`, `enviar`, `idFac`) VALUES
(1, 'Miguel Angel De Los Montes Rodriguez-Santillana', 'Avenida de la princesa del país de las maravillas que queda junto a la casa del enano, 1285, entrando por el pasillo principal a la derecha la tercera puerta', '28004', 'San Sebastián de los Reyes', 'Madrid', 'España', '+571 586 1234567890', 1, 1, 149),
(4, 'Empresa propia', 'Avenida Manuel Becerra Bajo 3 ', '47024', 'Cardales', 'León', 'España', '+34 916666666', 1, 1, 159),
(5, 'Empresa propia', 'Avenida Manuel Becerra Bajo 3 ', '47024', 'Cardales', 'León', 'España', '+34 916666666', 1, 1, 160),
(6, 'Empresa propia', 'Avenida Manuel Becerra Bajo 3 ', '47024', 'Cardales', 'León', 'España', '+34 916666666', 1, 1, 161),
(7, 'Empresa propia', 'Avenida Manuel Becerra Bajo 3 ', '47024', 'Cardales', 'León', 'España', '+34 916666666', 1, 1, 162),
(8, 'Empresa propia', 'Avenida Manuel Becerra Bajo 3 ', '47024', 'Cardales', 'León', 'España', '+34 916666666', 1, 1, 163),
(9, 'Empresa propia', 'Avenida Manuel Becerra Bajo 3 ', '47024', 'Cardales', 'León', 'España', '+34 916666666', 1, 1, 164),
(10, 'Empresa propia', 'Avenida Manuel Becerra Bajo 3 ', '47024', 'Cardales', 'León', 'España', '+34 916666666', 1, 1, 165),
(11, 'Empresa propia', 'Avenida Manuel Becerra Bajo 3 ', '47024', 'Cardales', 'León', 'España', '+34 916666666', 1, 1, 166),
(12, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 167),
(13, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 168),
(14, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 169),
(15, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 170),
(16, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 171),
(17, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 172),
(18, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 173),
(19, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 174),
(20, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 175),
(21, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 176),
(22, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 177),
(23, 'Company', 'Paseo Castellana 72 planta 4', '28006', 'Madrid', 'Madrid', 'España', '914564567', 1, 1, 178);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_estado`
--

CREATE TABLE `factura_estado` (
  `id` int(11) NOT NULL,
  `idFac` int(11) NOT NULL,
  `idEst` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `creadoPor` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `observaciones` text COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `factura_estado`
--

INSERT INTO `factura_estado` (`id`, `idFac`, `idEst`, `fecha`, `creadoPor`, `observaciones`) VALUES
(1, 2, 6, '2020-01-17 19:21:39', 'pepe', NULL),
(2, 3, 6, '2020-01-17 19:26:19', 'pepe', NULL),
(3, 4, 6, '2020-01-17 19:31:41', 'pepe', NULL),
(4, 5, 6, '2020-01-17 19:38:47', 'pepe', NULL),
(5, 6, 6, '2020-01-17 20:05:28', 'admin', NULL),
(6, 7, 6, '2020-01-17 20:51:06', 'admin', NULL),
(7, 8, 7, '2020-01-18 19:21:35', 'admin', 'Mensaje en textarea con unas cuantas palabras para ver como funciona este tema del resizing. Ya'),
(8, 9, 7, '2020-01-18 12:05:49', 'admin', NULL),
(10, 8, 3, '2020-01-19 21:26:49', 'admin', 'Ahora sí que voy a poner unas cuantas palabras.'),
(11, 8, 5, '2020-01-19 11:48:30', 'admin', 'mañana'),
(12, 8, 7, '2020-01-19 23:15:07', 'admin', 'Pongo otra cosa'),
(13, 8, 3, '2020-01-22 00:01:21', 'admin', NULL),
(14, 8, 7, '2020-01-22 00:01:54', 'pepe', 'Vuelvo al estado correcto.'),
(15, 8, 1, '2020-01-24 20:41:14', 'pepe', NULL),
(16, 8, 7, '2020-01-24 20:41:27', 'pepe', NULL),
(70, 64, 6, '2020-02-12 23:43:08', 'pepe', 'Voy a poner una observación la cual va a ocupar unos cuantos renglones y se verá bien.'),
(72, 66, 6, '2020-02-12 23:43:08', 'pepe', NULL),
(73, 67, 6, '2020-02-12 23:43:08', 'pepe', NULL),
(74, 68, 6, '2020-02-12 23:43:08', 'pepe', NULL),
(75, 69, 6, '2020-02-12 23:43:08', 'pepe', NULL),
(78, 72, 6, '2020-02-15 11:15:10', 'pepe', NULL),
(80, 74, 6, '2020-02-15 11:15:34', 'pepe', 'otra obser'),
(81, 75, 7, '2020-02-15 11:40:35', 'pepe', NULL),
(91, 85, 6, '2020-02-15 14:38:22', 'pepe', 'una obs'),
(92, 86, 6, '2020-02-15 14:38:39', 'pepe', NULL),
(93, 87, 7, '2020-02-15 14:38:53', 'pepe', NULL),
(94, 67, 3, '2020-02-15 23:59:02', 'pepe', NULL),
(95, 67, 6, '2020-02-15 23:59:08', 'pepe', NULL),
(96, 74, 8, '2020-02-19 22:48:00', 'pepe', 'una observ'),
(97, 72, 3, '2020-02-19 22:48:16', 'pepe', NULL),
(98, 64, 8, '2020-02-19 23:12:58', 'pepe', NULL),
(99, 64, 3, '2020-02-19 23:13:04', 'pepe', NULL),
(100, 64, 6, '2020-02-19 23:13:06', 'pepe', NULL),
(101, 66, 8, '2020-02-19 23:17:37', 'pepe', 'Está de camino'),
(102, 88, 6, '2020-03-16 23:18:40', 'pepe', NULL),
(103, 66, 1, '2020-03-17 21:54:28', 'pepe', NULL),
(104, 67, 1, '2020-03-22 22:02:39', 'pepe', NULL),
(105, 67, 6, '2020-03-22 22:02:57', 'pepe', NULL),
(106, 67, 8, '2020-03-22 22:03:16', 'pepe', NULL),
(107, 67, 6, '2020-03-22 22:03:22', 'pepe', NULL),
(108, 67, 8, '2020-03-22 22:03:39', 'pepe', NULL),
(109, 67, 6, '2020-03-22 22:04:03', 'pepe', NULL),
(110, 67, 8, '2020-03-22 22:04:15', 'pepe', NULL),
(112, 90, 6, '2020-04-26 23:29:31', 'pepe', NULL),
(113, 91, 6, '2020-04-26 23:29:31', 'pepe', NULL),
(114, 92, 6, '2020-04-27 22:40:20', 'pepe', NULL),
(116, 94, 6, '2020-04-27 22:40:20', 'pepe', NULL),
(117, 95, 6, '2020-04-27 22:40:20', 'pepe', NULL),
(118, 96, 6, '2020-04-27 22:40:20', 'pepe', NULL),
(122, 100, 6, '2020-04-27 22:50:14', 'pepe', NULL),
(123, 101, 6, '2020-04-27 22:50:14', 'pepe', NULL),
(124, 102, 6, '2020-04-27 22:50:14', 'pepe', NULL),
(125, 103, 6, '2020-04-27 22:56:24', 'pepe', NULL),
(126, 104, 6, '2020-04-27 22:56:24', 'pepe', NULL),
(127, 105, 6, '2020-04-27 22:56:24', 'pepe', NULL),
(128, 106, 6, '2020-05-01 09:56:50', 'pepe', NULL),
(129, 107, 6, '2020-05-01 09:57:26', 'pepe', NULL),
(130, 108, 6, '2020-05-01 11:42:28', 'pepe', NULL),
(131, 109, 6, '2020-05-01 11:42:28', 'pepe', NULL),
(132, 110, 6, '2020-05-01 11:42:28', 'pepe', NULL),
(133, 111, 6, '2020-05-03 14:01:57', 'pepe', NULL),
(134, 112, 6, '2020-05-03 14:01:57', 'pepe', NULL),
(135, 113, 6, '2020-05-03 14:02:22', 'pepe', NULL),
(136, 114, 6, '2020-05-03 14:04:33', 'pepe', NULL),
(137, 115, 6, '2020-05-03 14:04:33', 'pepe', NULL),
(138, 100, 1, '2020-05-09 17:58:58', 'admin', NULL),
(139, 68, 8, '2020-05-09 18:07:22', 'admin', NULL),
(140, 69, 8, '2020-05-09 18:08:34', 'admin', NULL),
(141, 9, 1, '2020-05-09 20:06:14', 'pepe', NULL),
(142, 109, 1, '2020-05-09 20:06:27', 'pepe', NULL),
(143, 107, 8, '2020-05-09 20:09:05', 'pepe', NULL),
(144, 100, 8, '2020-05-09 21:44:12', 'pepe', NULL),
(145, 100, 1, '2020-05-09 21:45:15', 'pepe', NULL),
(146, 116, 6, '2020-05-24 20:42:23', 'pepe', NULL),
(147, 114, 3, '2020-07-07 23:04:29', 'pepe', 'se comprueba funcionamiento de cambio de estado en una factura'),
(148, 117, 6, '2020-07-18 00:22:45', 'pepe', NULL),
(149, 118, 6, '2020-07-18 21:02:02', 'pepe', NULL),
(150, 119, 6, '2020-07-20 13:35:03', 'pepe', NULL),
(151, 119, 6, '2020-07-20 13:35:03', 'pepe', NULL),
(152, 120, 6, '2020-07-21 20:54:34', 'pepe', NULL),
(153, 121, 6, '2020-07-21 21:14:53', 'pepe', NULL),
(154, 122, 6, '2020-07-21 21:19:14', 'pepe', NULL),
(155, 123, 7, '2020-07-21 21:22:40', 'pepe', NULL),
(156, 124, 6, '2020-07-21 21:31:08', 'pepe', NULL),
(157, 124, 6, '2020-07-21 21:41:07', 'pepe', NULL),
(158, 125, 6, '2020-07-22 17:39:45', 'pepe', NULL),
(159, 125, 6, '2020-07-22 17:39:52', 'pepe', NULL),
(160, 126, 7, '2020-07-22 17:54:20', 'pepe', NULL),
(161, 127, 6, '2020-07-22 17:54:50', 'pepe', NULL),
(162, 127, 6, '2020-07-22 17:54:50', 'pepe', NULL),
(163, 128, 6, '2020-07-22 20:06:45', 'pepe', NULL),
(164, 128, 6, '2020-07-22 20:06:45', 'pepe', NULL),
(165, 129, 6, '2020-07-22 20:30:09', 'pepe', NULL),
(166, 129, 6, '2020-07-22 20:30:09', 'pepe', NULL),
(167, 130, 6, '2020-07-22 20:32:10', 'pepe', NULL),
(168, 130, 6, '2020-07-22 20:32:10', 'pepe', NULL),
(169, 131, 6, '2020-07-22 20:35:14', 'pepe', NULL),
(170, 131, 6, '2020-07-22 20:35:14', 'pepe', NULL),
(171, 132, 6, '2020-07-22 20:41:39', 'pepe', NULL),
(172, 132, 6, '2020-07-22 20:41:39', 'pepe', NULL),
(173, 133, 6, '2020-07-22 20:42:44', 'pepe', NULL),
(174, 133, 6, '2020-07-22 20:42:45', 'pepe', NULL),
(175, 134, 6, '2020-07-22 20:44:50', 'pepe', NULL),
(176, 134, 6, '2020-07-22 20:44:50', 'pepe', NULL),
(177, 135, 6, '2020-07-22 21:02:40', 'pepe', NULL),
(178, 135, 6, '2020-07-22 21:02:40', 'pepe', NULL),
(179, 136, 6, '2020-07-22 21:03:40', 'pepe', NULL),
(180, 136, 6, '2020-07-22 21:03:40', 'pepe', NULL),
(181, 137, 6, '2020-07-26 14:26:00', 'pepe', NULL),
(182, 137, 6, '2020-07-26 14:26:00', 'pepe', NULL),
(183, 138, 6, '2020-07-26 16:26:32', 'pepe', NULL),
(184, 139, 6, '2020-07-26 16:27:10', 'pepe', NULL),
(185, 140, 6, '2020-07-26 16:28:50', 'pepe', NULL),
(186, 141, 6, '2020-07-26 21:45:01', 'pepe', NULL),
(187, 141, 6, '2020-07-26 21:46:09', 'pepe', NULL),
(188, 142, 6, '2020-07-26 21:49:20', 'pepe', NULL),
(189, 142, 6, '2020-07-26 21:50:36', 'pepe', NULL),
(190, 143, 6, '2020-07-26 22:10:43', 'pepe', NULL),
(191, 143, 6, '2020-07-26 22:10:54', 'pepe', NULL),
(192, 144, 6, '2020-07-26 23:17:12', 'pepe', NULL),
(193, 145, 6, '2020-07-26 23:18:08', 'pepe', NULL),
(194, 146, 6, '2020-07-26 23:29:00', 'pepe', NULL),
(195, 146, 6, '2020-07-26 23:29:13', 'pepe', NULL),
(196, 147, 6, '2020-07-26 23:30:11', 'pepe', NULL),
(197, 147, 6, '2020-07-26 23:30:11', 'pepe', NULL),
(198, 148, 6, '2020-07-27 00:00:44', 'pepe', NULL),
(199, 148, 6, '2020-07-27 00:00:44', 'pepe', NULL),
(200, 149, 6, '2020-07-27 00:01:37', 'pepe', NULL),
(201, 149, 6, '2020-07-27 00:01:37', 'pepe', NULL),
(202, 150, 6, '2020-08-01 13:52:29', 'pepe', NULL),
(205, 153, 6, '2020-08-02 23:41:37', 'pepe', NULL),
(211, 159, 6, '2020-08-03 00:03:55', 'pepe', NULL),
(212, 160, 6, '2020-08-03 18:01:28', 'pepe', NULL),
(213, 161, 6, '2020-08-03 18:19:04', 'pepe', NULL),
(214, 162, 6, '2020-08-05 00:04:11', 'pepe', NULL),
(215, 162, 6, '2020-08-05 00:04:11', 'pepe', NULL),
(216, 163, 6, '2020-08-11 15:37:31', 'pepe', NULL),
(217, 164, 6, '2020-08-16 23:24:51', 'pepe', NULL),
(218, 164, 6, '2020-08-16 23:24:51', 'pepe', NULL),
(219, 165, 6, '2020-08-16 23:27:09', 'pepe', NULL),
(220, 165, 6, '2020-08-16 23:27:09', 'pepe', NULL),
(221, 166, 6, '2020-08-16 23:27:46', 'pepe', NULL),
(222, 166, 6, '2020-08-16 23:27:46', 'pepe', NULL),
(223, 167, 6, '2020-08-31 00:27:56', 'admin', NULL),
(224, 168, 7, '2020-08-31 00:33:11', 'admin', NULL),
(225, 169, 6, '2020-08-31 00:43:46', 'admin', NULL),
(226, 170, 7, '2020-08-31 00:45:11', 'admin', NULL),
(227, 171, 6, '2020-08-31 00:54:11', 'admin', NULL),
(228, 172, 6, '2020-08-31 01:13:44', 'admin', NULL),
(229, 173, 6, '2020-08-31 01:14:13', 'admin', NULL),
(230, 174, 7, '2020-08-31 01:15:02', 'admin', NULL),
(231, 175, 6, '2020-08-31 01:21:36', 'admin', NULL),
(232, 175, 6, '2020-08-31 01:21:36', 'admin', NULL),
(233, 176, 6, '2020-08-31 23:56:53', 'pepe', NULL),
(234, 176, 6, '2020-08-31 23:56:53', 'pepe', NULL),
(235, 177, 6, '2020-09-01 23:55:40', 'admin', NULL),
(236, 178, 6, '2020-09-06 14:40:40', 'admin', NULL),
(237, 173, 8, '2020-09-19 19:14:03', 'admin', NULL),
(238, 130, 5, '2020-09-19 19:19:55', 'admin', NULL),
(239, 74, 5, '2020-09-24 21:52:20', 'admin', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formapago`
--

CREATE TABLE `formapago` (
  `idFor` int(11) NOT NULL,
  `nombreES` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEN` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombrePT` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreFR` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreIT` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreGE` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreCA` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEU` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `formapago`
--

INSERT INTO `formapago` (`idFor`, `nombreES`, `nombreEN`, `nombrePT`, `nombreFR`, `nombreIT`, `nombreGE`, `nombreCA`, `nombreEU`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'Efectivo', 'Cash', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:02:36'),
(2, 'Tarjeta de débito', 'Debit card', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:02:36'),
(3, 'Cheque', 'Check', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:02:36'),
(4, 'Movimiento stock', 'Stock change', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:02:36'),
(5, 'Tarjeta de  crédito', 'Credit card', NULL, NULL, NULL, NULL, NULL, NULL, '', '2020-05-24 00:02:36'),
(6, 'Pay pal', 'Pay pal', NULL, NULL, NULL, NULL, NULL, NULL, 'pepe', '2020-05-24 18:43:35');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `foto`
--

CREATE TABLE `foto` (
  `idFot` int(11) NOT NULL,
  `idUsr` int(11) NOT NULL,
  `idPro` int(11) NOT NULL,
  `idEmp` int(11) NOT NULL,
  `idCat` int(11) NOT NULL,
  `idSub` int(11) NOT NULL,
  `idPais` int(11) NOT NULL,
  `idFor` int(11) NOT NULL,
  `idEst` int(11) NOT NULL,
  `idRol` int(11) NOT NULL,
  `nombre` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `ruta` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `peso` bigint(20) NOT NULL,
  `principal` tinyint(4) NOT NULL,
  `extension` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fechaCreacion` datetime NOT NULL,
  `creadoPor` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `foto`
--

INSERT INTO `foto` (`idFot`, `idUsr`, `idPro`, `idEmp`, `idCat`, `idSub`, `idPais`, `idFor`, `idEst`, `idRol`, `nombre`, `ruta`, `descripcion`, `peso`, `principal`, `extension`, `fechaCreacion`, `creadoPor`, `fechaModificacion`, `modificadoPor`) VALUES
(34, 1, 0, 0, 0, 0, 0, 0, 0, 0, 'IMG-20200222-WA0053.jpeg', '', '', 628963, 1, NULL, '2020-05-19 20:11:35', 'pepe', NULL, NULL),
(43, 0, 8, 0, 0, 0, 0, 0, 0, 0, 'reloj temperatura 1.jpg', '\\resources\\imgs\\productos\\8', '', 12256, 0, NULL, '2020-05-20 22:31:16', 'usuario', NULL, NULL),
(45, 0, 8, 0, 0, 0, 0, 0, 0, 0, 'reloj temperatura 3.jpg', '\\resources\\imgs\\productos\\8', 'La tercera foto', 27246, 1, NULL, '2020-05-20 22:42:13', 'usuario', '2020-05-20 23:33:06', 'pepe'),
(46, 0, 8, 0, 0, 0, 0, 0, 0, 0, 'reloj temperatura 4.jpg', '\\resources\\imgs\\productos\\8', '', 16784, 0, NULL, '2020-05-20 23:05:20', 'usuario', '2020-05-20 23:33:06', 'pepe'),
(50, 84, 0, 0, 0, 0, 0, 0, 0, 0, 'IMG-20200226-WA0085.jpg', '\\resources\\imgs\\usuarios\\84', '', 81178, 1, NULL, '2020-05-21 20:30:20', 'cliente', '2020-05-21 22:03:37', 'cliente'),
(59, 84, 0, 0, 0, 0, 0, 0, 0, 0, 'IMG-20200225-WA0012.jpg', '\\resources\\imgs\\usuarios\\84', '', 137174, 0, NULL, '2020-05-21 21:47:01', 'cliente', '2020-05-21 22:02:33', 'cliente'),
(64, 84, 0, 0, 0, 0, 0, 0, 0, 0, 'IMG-20200227-WA0040.jpg', '\\resources\\imgs\\usuarios\\84', '', 103871, 0, NULL, '2020-05-21 22:02:48', 'cliente', NULL, NULL),
(65, 84, 0, 0, 0, 0, 0, 0, 0, 0, 'IMG-20200225-WA0068.jpg', '\\resources\\imgs\\usuarios\\84', '', 121523, 0, NULL, '2020-05-21 22:03:18', 'cliente', '2020-05-21 22:03:37', 'cliente'),
(66, 85, 0, 0, 0, 0, 0, 0, 0, 0, 'IMG-20200221-WA0013.jpg', '\\resources\\imgs\\usuarios\\85', '', 56296, 1, NULL, '2020-05-28 23:18:26', 'admin', '2020-05-28 23:18:26', 'admin'),
(67, 105, 0, 0, 0, 0, 0, 0, 0, 0, 'IMG_0019.JPG', '\\resources\\imgs\\usuarios\\105', '', 1378681, 1, NULL, '2020-07-02 23:33:06', 'sergiof', '2020-07-02 23:33:06', 'sergiof'),
(68, 0, 5, 0, 0, 0, 0, 0, 0, 0, 'IMG_0311.JPG', '\\resources\\imgs\\productos\\5', '', 1219528, 1, NULL, '2020-07-07 22:39:45', 'pepe', '2020-07-07 22:39:45', 'pepe'),
(69, 0, 5, 0, 0, 0, 0, 0, 0, 0, 'IMG_0312.JPG', '\\resources\\imgs\\productos\\5', '', 1242816, 0, NULL, '2020-07-18 13:27:00', 'pepe', '2020-07-18 13:27:00', 'pepe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paises`
--

CREATE TABLE `paises` (
  `idPais` int(11) NOT NULL,
  `nombreES` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `nombreEN` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `nombrePT` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreFR` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreIT` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreGE` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreCA` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEU` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `paises`
--

INSERT INTO `paises` (`idPais`, `nombreES`, `nombreEN`, `nombrePT`, `nombreFR`, `nombreIT`, `nombreGE`, `nombreCA`, `nombreEU`) VALUES
(0, 'No informado', 'Not selected', NULL, NULL, NULL, NULL, NULL, NULL),
(1, 'España', 'Spain', NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'Alemania', 'Germany', NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'Andorra', 'Andorra', NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'Austria', 'Austria', NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'Bélgica', 'Belgium', NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'Dinamarca', 'Denmark', NULL, NULL, NULL, NULL, NULL, NULL),
(7, 'El Vaticano', 'The Vatican', NULL, NULL, NULL, NULL, NULL, NULL),
(8, 'Francia', 'France', NULL, NULL, NULL, NULL, NULL, NULL),
(9, 'Grecia', 'Greece', NULL, NULL, NULL, NULL, NULL, NULL),
(10, 'Italia', 'Italy', NULL, NULL, NULL, NULL, NULL, NULL),
(11, 'Noruega', 'Norway', NULL, NULL, NULL, NULL, NULL, NULL),
(12, 'Países Bajos', 'Netherlands', NULL, NULL, NULL, NULL, NULL, NULL),
(13, 'Portugal', 'Portugal', NULL, NULL, NULL, NULL, NULL, NULL),
(14, 'Reino Unido', 'United Kingdom', NULL, NULL, NULL, NULL, NULL, NULL),
(15, 'San Marino', 'San Marino', NULL, NULL, NULL, NULL, NULL, NULL),
(16, 'Suecia', 'Sweden', NULL, NULL, NULL, NULL, NULL, NULL),
(17, 'Suiza', 'Switzerland', NULL, NULL, NULL, NULL, NULL, NULL),
(18, 'Turquía', 'Turkey', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preferenciausuario`
--

CREATE TABLE `preferenciausuario` (
  `idPrefUsr` int(11) NOT NULL,
  `tema` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `botonFavorito` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `recibirPublicidad` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `preferenciausuario`
--

INSERT INTO `preferenciausuario` (`idPrefUsr`, `tema`, `botonFavorito`, `recibirPublicidad`) VALUES
(1, 'default', 'default', 0),
(83, 'default', 'default', 1),
(84, 'tema03', 'default', 1),
(85, 'default', 'cat', 1),
(106, 'tema03', 'corazon', 1),
(107, 'default', 'default', 0),
(108, 'default', 'default', 1),
(109, 'default', 'default', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idPro` int(11) NOT NULL,
  `nombreES` text COLLATE utf8_spanish_ci NOT NULL,
  `nombreEN` text COLLATE utf8_spanish_ci NOT NULL,
  `nombrePT` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreFR` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreIT` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreGE` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreCA` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEU` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `unidades` int(11) NOT NULL,
  `precioVenta` double NOT NULL,
  `precioCompra` double NOT NULL,
  `marca` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `modelo` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `serie` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ubicacion` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `partida` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fechaCompra` date DEFAULT NULL,
  `enviar` tinyint(1) NOT NULL,
  `vendible` tinyint(1) NOT NULL,
  `mesesGarantia` double NOT NULL,
  `peso` double NOT NULL,
  `volumen` double NOT NULL,
  `idSub` int(11) NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idPro`, `nombreES`, `nombreEN`, `nombrePT`, `nombreFR`, `nombreIT`, `nombreGE`, `nombreCA`, `nombreEU`, `unidades`, `precioVenta`, `precioCompra`, `marca`, `modelo`, `serie`, `ubicacion`, `estado`, `partida`, `fechaCompra`, `enviar`, `vendible`, `mesesGarantia`, `peso`, `volumen`, `idSub`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'TV Sanyo 15 pulgadas', 'TV Sanyo 15 inches', NULL, NULL, NULL, NULL, NULL, NULL, 7, 150, 115.35, 'Sanyo', 'HR15', '12345678', 'Pasillo 3', 'ACTIVE', NULL, '2019-12-22', 1, 1, 12, 20, 0.016, 1, '', '2020-05-24 00:03:08'),
(2, 'TV Sony 28 pulgadas', 'TV Sony 28 inches', NULL, NULL, NULL, NULL, NULL, NULL, 0, 600, 421, 'Sony', 'RJ25', '', '', 'DISCONTINUED', NULL, NULL, 0, 1, 6, 5, 3.2, 1, '', '2020-05-24 00:03:08'),
(3, 'Papel higiénico Higienol', 'Toilet paper Higienol', NULL, NULL, NULL, NULL, NULL, NULL, 6, 1.5, 0.95, 'Higienol', '', '', '', 'ACTIVE', NULL, NULL, 1, 1, 2.5, 0.3, 0.29, 9, '', '2020-05-24 00:03:08'),
(4, 'Castañas Gallegas', 'Chestnuts from Galicia', NULL, NULL, NULL, NULL, NULL, NULL, 8, 2, 0.4, 'La castañera', 'Bolsa L', '', '', 'INACTIVE', NULL, NULL, 0, 1, 0, 2, 0, 18, '', '2020-05-24 00:03:08'),
(5, 'Monitor Samsung 27 pulgadas', 'Monitor Samsung 27 inches', NULL, NULL, NULL, NULL, NULL, NULL, 0, 250, 180, 'Samsung', 'Energy', '158159126', '', 'ACTIVE', NULL, NULL, 1, 1, 12, 2, 1, 16, '', '2020-05-24 00:03:08'),
(6, 'Estantería de madera color roble', 'Oak wood shelving', NULL, NULL, NULL, NULL, NULL, NULL, 411, 45, 32, 'Ikea', '', '', '', 'ACTIVE', NULL, NULL, 0, 1, 6, 0, 0, 17, 'pepe', '2020-08-02 22:03:40'),
(7, 'Taza Star Wars negra', 'Star Wars Mug Black', NULL, NULL, NULL, NULL, NULL, NULL, 9, 2.5, 0.7, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 1, 0, 0, 0, 15, 'pepe', '2020-07-07 21:06:45'),
(8, 'Reloj temperatura', 'Temperature clock', NULL, NULL, NULL, NULL, NULL, NULL, 0, 12.5, 7, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 15, 'admin', '2020-05-28 21:20:04'),
(9, 'Cable USB', 'USB cable', NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 1, 'IBM', 'X26', '12358', 'Pasillo 3', 'INACTIVE', NULL, NULL, 0, 1, 3, 0.2, 0.6, 2, '', '2020-05-24 00:03:08'),
(10, 'Jamón ibérico', 'Jam', NULL, NULL, NULL, NULL, NULL, NULL, 12, 12, 0, '4J', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 18, 'pepe', '2020-07-07 21:08:13'),
(11, 'Mesa redonda', 'Circle table', NULL, NULL, NULL, NULL, NULL, NULL, 1, 30, 0, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 7, 0, 5, '', '2020-05-24 00:03:08'),
(12, 'Queso Casanto', 'Casanto Chees', NULL, NULL, NULL, NULL, NULL, NULL, 5, 1.2, 0.84, 'Casanto', '', '', '', 'ACTIVE', NULL, NULL, 0, 1, 0, 0.12, 0, 19, 'pepe', '2020-05-24 18:45:09'),
(13, 'Juego Lo sabe no lo sabe', 'Juego Lo sabe no lo sabe', NULL, NULL, NULL, NULL, NULL, NULL, 0, 10, 0, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 21, 'pepe', '2020-08-13 15:18:55'),
(14, 'Ajedrés', 'Chess', NULL, NULL, NULL, NULL, NULL, NULL, 2, 4, 0, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 21, 'pepe', '2020-08-13 15:19:25'),
(15, 'Impresora Canon DU20', 'Printer Canon DU20', NULL, NULL, NULL, NULL, NULL, NULL, 8900, 80, 0, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 22, 'pepe', '2020-08-13 15:21:53'),
(16, 'Dados', 'Dados', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1.5, 0, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 21, 'pepe', '2020-08-13 15:20:26'),
(17, 'Botella 1,5L transparente', 'Botella 1,5L transparente', NULL, NULL, NULL, NULL, NULL, NULL, 20, 1.2, 0, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 18, 'pepe', '2020-08-13 15:28:42'),
(18, 'Queso de untar finas hiervas', 'Queso de untar finas hiervas', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0.9, 0, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 18, 'pepe', '2020-08-13 15:29:18'),
(19, 'Cola cao 500g', 'Cola cao 500g', NULL, NULL, NULL, NULL, NULL, NULL, 0, 2.3, 0, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 18, 'pepe', '2020-08-13 15:29:50');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_empresa`
--

CREATE TABLE `producto_empresa` (
  `idPro` int(11) NOT NULL,
  `idEmp` int(11) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `creadoPor` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `producto_empresa`
--

INSERT INTO `producto_empresa` (`idPro`, `idEmp`, `fechaCreacion`, `creadoPor`) VALUES
(2, 3, '2020-01-10', 'DAMIAN'),
(3, 1, '2020-05-10', 'pepe'),
(4, 1, '2020-09-25', 'pepe'),
(4, 2, '2020-01-10', 'DAMIAN'),
(4, 3, '2020-01-10', 'DAMIAN'),
(7, 9, '2020-05-11', 'pepe'),
(8, 5, '2020-05-11', 'pepe'),
(9, 3, '2020-05-10', 'pepe'),
(10, 9, '2020-07-07', 'pepe'),
(11, 11, '2020-05-10', 'pepe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_factura`
--

CREATE TABLE `producto_factura` (
  `idPro` int(11) NOT NULL,
  `idFac` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `ivaProducto` double NOT NULL,
  `ivaImporteTotal` double NOT NULL,
  `descuentoPor` double NOT NULL,
  `descuentoImporteTotal` double NOT NULL,
  `precioUnitSinIva` double NOT NULL,
  `precioUnitSinIvaConDesc` double NOT NULL,
  `precioUnitario` double NOT NULL,
  `precioFinalSinIva` double NOT NULL,
  `precioFinalRecibidoPagado` double NOT NULL,
  `observaciones` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `producto_factura`
--

INSERT INTO `producto_factura` (`idPro`, `idFac`, `cantidad`, `ivaProducto`, `ivaImporteTotal`, `descuentoPor`, `descuentoImporteTotal`, `precioUnitSinIva`, `precioUnitSinIvaConDesc`, `precioUnitario`, `precioFinalSinIva`, `precioFinalRecibidoPagado`, `observaciones`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 7, 5, 10, 0, 0, 0, 90, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(1, 8, 1, 21, 0, 0, 0, 200, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(1, 9, 1, 21, 0, 0, 0, 200, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(1, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:50:25'),
(1, 160, 1, 10, 0, 0, 0, 170, 0, 0, 0, 177, NULL, 'pepe', '2020-08-11 16:30:18'),
(2, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:51:29'),
(2, 160, 1, 0, 0, 0, 0, 150, 0, 0, 0, 181.5, NULL, 'pepe', '2020-08-12 22:02:02'),
(3, 72, 5, 0, 0, 0, 0, 1.4, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 74, 5, 0, 0, 0, 0, 1.6, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 75, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 100, 2, 6, 0, 0, 0, 33.01, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 101, 0, 6, 0, 0, 0, 33.01, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 102, 0, 6, 0, 0, 0, 33.01, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 106, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:51:43'),
(3, 160, 12, 21, 0, 0, 0, 6, 0, 0, 0, 7.26, NULL, 'pepe', '2020-08-11 16:30:32'),
(4, 92, 2, 0, 0, 0, 0, 7.5, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 94, 0, 0, 0, 0, 0, 7.5, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 95, 0, 0, 0, 0, 0, 7.5, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 96, 0, 0, 0, 0, 0, 7.5, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 103, 3, 5, 0, 0, 0, 19.04, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 104, 0, 5, 0, 0, 0, 19.04, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 105, 0, 5, 0, 0, 0, 19.04, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 108, 2, 21, 0, 0, 0, 6.19, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 109, 0, 21, 0, 0, 0, 6.19, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 110, 0, 21, 0, 0, 0, 6.19, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 112, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 113, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 114, 1, 0, 0, 0, 0, 20, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 115, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:51:50'),
(4, 160, 1, 0, 0, 0, 0, 6, 0, 0, 0, 6, NULL, 'pepe', '2020-08-12 22:02:34'),
(5, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:51:58'),
(6, 64, 1, 21, 0, 0, 0, 566.33, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 66, 0, 21, 0, 0, 0, 566.33, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 67, 0, 21, 0, 0, 0, 566.33, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 68, 0, 21, 0, 0, 0, 566.33, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 69, 0, 21, 0, 0, 0, 566.33, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 88, 2, 21, 0, 0, 0, 61.98, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 117, 2, 21, 0, 0, 0, 24.79, 0, 0, 0, 60, NULL, 'pepe', '2020-07-18 18:37:44'),
(6, 118, 3, 21, 0, 0, 0, 37.19, 0, 0, 0, 135, NULL, 'pepe', '2020-07-18 19:02:02'),
(6, 128, 2, 21, 0, 0, 0, 41.32, 0, 0, 0, 100, NULL, 'pepe', '2020-07-22 18:06:45'),
(6, 129, 2, 21, 0, 0, 0, 41.32, 0, 0, 0, 100, NULL, 'pepe', '2020-07-22 18:30:09'),
(6, 130, 7, 21, 0, 0, 0, 19.39, 0, 0, 0, 164.28, NULL, 'pepe', '2020-07-22 18:32:10'),
(6, 131, 2, 21, 0, 0, 0, 26.22, 0, 0, 0, 63.47, NULL, 'pepe', '2020-07-22 18:35:14'),
(6, 132, 2, 21, 0, 0, 0, 41.32, 0, 0, 0, 100, NULL, 'pepe', '2020-07-22 18:41:39'),
(6, 133, 2, 21, 0, 0, 0, 26.22, 0, 0, 0, 63.47, NULL, 'pepe', '2020-07-22 18:42:44'),
(6, 134, 1, 21, 0, 0, 0, 82.64, 0, 0, 0, 100, NULL, 'pepe', '2020-07-22 18:44:50'),
(6, 135, 1, 21, 0, 0, 0, 82.64, 0, 0, 0, 100, NULL, 'pepe', '2020-07-22 19:02:40'),
(6, 136, 1, 21, 0, 0, 0, 82.64, 0, 0, 0, 100, NULL, 'pepe', '2020-07-22 19:03:40'),
(6, 137, 1, 0, 0, 0, 0, 90, 0, 0, 0, 90, NULL, 'pepe', '2020-07-26 12:26:00'),
(6, 141, 1, 0, 0, 0, 0, 90, 0, 0, 0, 90, NULL, 'pepe', '2020-07-26 19:46:08'),
(6, 142, 1, 0, 0, 0, 0, 90, 0, 0, 0, 90, NULL, 'pepe', '2020-07-26 19:50:35'),
(6, 143, 1, 0, 0, 0, 0, 90, 0, 0, 0, 90, NULL, 'pepe', '2020-07-26 20:10:49'),
(6, 146, 5, 21, 0, 0, 0, 26.44, 0, 0, 0, 160, NULL, 'pepe', '2020-07-26 21:29:00'),
(6, 147, 9, 21, 0, 0, 0, 16.52, 0, 0, 0, 180, NULL, 'pepe', '2020-07-26 21:30:11'),
(6, 148, 5, 21, 0, 0, 0, 33.05, 0, 0, 0, 200, NULL, 'pepe', '2020-07-26 22:00:44'),
(6, 149, 5, 21, 0, 0, 0, 33.05, 0, 0, 0, 200, NULL, 'pepe', '2020-07-26 22:01:37'),
(6, 150, 2, 21, 0, 0, 0, 45.45, 0, 0, 0, 110, NULL, 'pepe', '2020-08-01 11:52:29'),
(6, 153, 2, 10, 0, 0, 0, 31.81, 0, 0, 0, 70, NULL, 'pepe', '2020-08-02 21:41:37'),
(6, 159, 1, 0, 0, 0, 0, 67, 0, 0, 0, 67, NULL, 'pepe', '2020-08-02 22:03:55'),
(6, 160, 10, 21, 0, 0, 0, 214.87, 0, 0, 0, 2600, 'Estas son las observaciones puestas para el otro producto', 'pepe', '2020-08-03 16:32:47'),
(6, 161, 300, 21, 0, 0, 0, 689.99, 0, 0, 0, 250468, NULL, 'pepe', '2020-08-03 16:19:04'),
(6, 162, 2, 21, 0, 0, 0, 82.64, 0, 0, 0, 200, NULL, 'pepe', '2020-08-04 22:04:11'),
(6, 163, 2, 10, 0, 0, 0, 17.27, 0, 0, 0, 38, NULL, 'pepe', '2020-08-11 13:37:31'),
(6, 164, 1, 10, 0, 0, 0, 18.18, 0, 0, 0, 20, NULL, 'pepe', '2020-08-16 21:24:51'),
(6, 165, 1, 21, 0, 0, 0, 16.52, 0, 0, 0, 20, NULL, 'pepe', '2020-08-16 21:27:09'),
(6, 166, 1, 21, 0, 0, 0, 16.52, 0, 0, 0, 20, NULL, 'pepe', '2020-08-16 21:27:46'),
(7, 85, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(7, 86, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(7, 87, 2, 0, 0, 0, 0, 10, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(7, 90, 2, 0, 0, 0, 0, 10, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(7, 91, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(7, 107, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(7, 116, 1, 0, 0, 0, 0, 10, 0, 0, 0, 0, NULL, 'pepe', '2020-05-24 18:42:23'),
(7, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:52:05'),
(8, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:52:41'),
(8, 160, 3, 10, 0, 50, 0, 60, 0, 0, 0, 99, 'Estas son las observaciones puestas para uno de los productos', 'admin', '2020-08-03 16:32:22'),
(9, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:52:48'),
(10, 119, 2, 21, 0, 0, 0, 74.38, 0, 0, 0, 180, NULL, 'pepe', '2020-07-20 11:35:03'),
(10, 121, 3, 21, 0, 0, 0, 57.85, 0, 0, 0, 210, NULL, 'pepe', '2020-07-21 19:15:01'),
(10, 122, 4, 0, 0, 0, 0, 10, 0, 0, 0, 40, NULL, 'pepe', '2020-07-21 19:19:21'),
(10, 123, 4, 0, 0, 0, 0, 10, 0, 0, 0, 40, NULL, 'pepe', '2020-07-21 19:22:40'),
(10, 125, 4, 21, 0, 0, 0, 24.79, 0, 0, 0, 120, NULL, 'pepe', '2020-07-22 15:39:45'),
(10, 126, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 'pepe', '2020-07-22 15:54:20'),
(10, 127, 4, 21, 0, 0, 0, 28.92, 0, 0, 0, 140, NULL, 'pepe', '2020-07-22 15:54:50'),
(10, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:52:55'),
(10, 161, 60, 10, 0, 0, 0, 6, 0, 0, 0, 396, NULL, 'admin', '2020-08-03 16:38:31'),
(11, 124, 1, 21, 0, 0, 0, 24.79, 0, 0, 0, 30, NULL, 'pepe', '2020-07-21 19:34:59'),
(11, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:58:59'),
(12, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 20:59:50'),
(12, 177, 5, 21, 1.52, 0, 0, 1.45, 1.45, 1.75, 7.23, 8.75, '', 'admin', '2020-09-01 21:55:40'),
(13, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 21:00:01'),
(14, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 21:00:10'),
(14, 167, 2, 21, 4.2, 0, 0, 10, 10, 12.1, 20, 24.2, '', 'admin', '2020-08-30 22:27:56'),
(14, 168, 2, 21, 11.28, 0, 0, 26.85, 26.85, 32.5, 53.71, 65, '', 'admin', '2020-08-30 22:33:11'),
(14, 169, 2, 21, 14.4, 0, 0, 34.29, 34.29, 41.5, 68.6, 83, '', 'admin', '2020-08-30 22:43:46'),
(14, 170, 2, 21, 11.28, 0, 0, 26.85, 26.85, 32.5, 53.72, 65, '', 'admin', '2020-08-30 22:45:11'),
(14, 171, 0, 0, 0, 0, 0, 20, 20, 20, 20, 20, '', 'admin', '2020-08-30 22:54:11'),
(14, 172, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20, '', 'admin', '2020-08-30 23:13:44'),
(14, 173, 2, 21, 4.2, 0, 0, 10, 10, 12.1, 20, 24.2, '', 'admin', '2020-08-30 23:14:13'),
(14, 174, 2, 21, 11.28, 0, 0, 26.86, 26.86, 32.5, 53.72, 65, '', 'admin', '2020-08-30 23:15:02'),
(14, 175, 2, 21, 4.2, 0, 0, 10, 10, 12.1, 20, 24.2, '', 'admin', '2020-08-30 23:21:36'),
(15, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 21:00:30'),
(15, 176, 8900, 1, 22772.29, 0, 0, 255.87, 255.87, 258.43, 2277228.66, 2300000.95, '', 'pepe', '2020-08-31 21:56:53'),
(16, 153, 1, 0, 0, 0, 0, 10, 0, 0, 0, 10, NULL, 'pepe', '2020-08-17 21:00:37'),
(17, 178, 20, 10, 1.5, 0, 0, 0.75, 0.75, 0.83, 15, 16.5, '', 'admin', '2020-09-06 12:40:40');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL,
  `rol` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idRol`, `rol`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'ROL_CLIENTE', '', '2020-05-24 00:04:03'),
(2, 'ROL_USUARIO', '', '2020-05-24 00:04:03'),
(3, 'ROL_ADMIN', '', '2020-05-24 00:04:03'),
(4, 'ROL_ROOT', '', '2020-05-24 00:04:03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subcategoria`
--

CREATE TABLE `subcategoria` (
  `idSub` int(11) NOT NULL,
  `nombreES` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEN` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombrePT` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreFR` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreIT` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreGE` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreCA` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreEU` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idCat` int(11) NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `subcategoria`
--

INSERT INTO `subcategoria` (`idSub`, `nombreES`, `nombreEN`, `nombrePT`, `nombreFR`, `nombreIT`, `nombreGE`, `nombreCA`, `nombreEU`, `idCat`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'Televisores', 'TV', NULL, NULL, NULL, NULL, NULL, NULL, 1, '', '2020-05-24 00:04:37'),
(2, 'USB 512GB', 'USB 512GB', NULL, NULL, NULL, NULL, NULL, NULL, 1, '', '2020-05-24 00:04:37'),
(3, 'Ordenador portátil Intel i7', 'Laptop Intel i7', NULL, NULL, NULL, NULL, NULL, NULL, 1, '', '2020-05-24 00:04:37'),
(4, 'Sillas', 'Chairs', NULL, NULL, NULL, NULL, NULL, NULL, 2, '', '2020-05-24 00:04:37'),
(5, 'Mesas', 'Tables', NULL, NULL, NULL, NULL, NULL, NULL, 2, '', '2020-05-24 00:04:37'),
(9, 'Unidad 3', 'Unit 3', NULL, NULL, NULL, NULL, NULL, NULL, 6, '', '2020-05-24 00:04:37'),
(10, 'Cargadores de móvil', 'Mobile chargers', NULL, NULL, NULL, NULL, NULL, NULL, 1, '', '2020-05-24 00:04:37'),
(12, 'Espejos', 'Mirror', NULL, NULL, NULL, NULL, NULL, NULL, 7, 'pepe', '2020-07-07 21:09:19'),
(13, 'Puertas', 'Doors', NULL, NULL, NULL, NULL, NULL, NULL, 7, '', '2020-05-24 00:04:37'),
(14, 'Ambientadores', 'Air fresheners', NULL, NULL, NULL, NULL, NULL, NULL, 2, '', '2020-05-24 00:04:37'),
(15, '0 Otros', '0 Others', NULL, NULL, NULL, NULL, NULL, NULL, 8, '', '2020-05-24 00:04:37'),
(16, 'Pantallas de ordenador', 'Computer screens', NULL, NULL, NULL, NULL, NULL, NULL, 1, '', '2020-05-24 00:04:37'),
(17, 'Estanterías', 'Shelves', NULL, NULL, NULL, NULL, NULL, NULL, 2, '', '2020-05-24 00:04:37'),
(18, 'Alimento', 'Food', NULL, NULL, NULL, NULL, NULL, NULL, 2, '', '2020-05-24 00:04:37'),
(19, 'Pantalones', 'Throusers', NULL, NULL, NULL, NULL, NULL, NULL, 9, 'admin', '2020-05-24 18:26:18'),
(20, 'Camisas', 'Shirts', NULL, NULL, NULL, NULL, NULL, NULL, 9, 'admin', '2020-06-03 20:52:12'),
(21, 'Juegos de mesa', 'Table games', NULL, NULL, NULL, NULL, NULL, NULL, 10, 'pepe', '2020-08-13 15:18:39'),
(22, 'Impresoras', 'Printers', NULL, NULL, NULL, NULL, NULL, NULL, 1, 'pepe', '2020-08-13 15:21:39');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tema`
--

CREATE TABLE `tema` (
  `idTem` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tema`
--

INSERT INTO `tema` (`idTem`, `nombre`) VALUES
(1, 'default');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsr` int(11) NOT NULL,
  `usuario` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(256) COLLATE utf8_spanish_ci NOT NULL,
  `habilitado` tinyint(4) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsr`, `usuario`, `clave`, `habilitado`, `fechaCreacion`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 'pepe', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2019-09-15 16:17:35', '', '2020-05-24 00:05:06'),
(3, 'roberto', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 0, '2019-09-15 16:17:09', '', '2020-05-24 00:05:06'),
(4, 'damian', 'f895c8e804268edd597848da7466e9a80648379b2258e1a7a59210af2f0c42a52dcf7efce6ab9347', 1, '2019-09-15 16:16:47', '', '2020-05-24 00:05:06'),
(44, 'dusheff', 'f49575f4e33033860f878aca63107c4a44f6f5871a09c8e8a99c592c70eb6d6d8aa7413683d63614', 1, '2019-09-15 16:15:00', '', '2020-05-24 00:05:06'),
(46, 'maria', '6528de74a3a997555a963d5e0fdf49daefe22ae73b1478e0ab9d4076f73b4c369d8524aa84afaea8', 1, '2019-09-19 12:02:56', '', '2020-05-24 00:05:06'),
(55, 'lara', '476520558b62a164fa6703002dc8a18c1358db557da022709b0366dac3d58a47c8771358874ae922', 1, '2019-09-30 21:15:39', '', '2020-05-24 00:05:06'),
(58, 'saul', '6a3121471f38805e88617b51876fbd30a4512687366dfa6e38eff311368b3e55a06c2414268c018a', 1, '2019-09-30 23:31:03', '', '2020-05-24 00:05:06'),
(60, 'alejandro', 'e802fb1dbb24708e796f6635894ba4e706c304061c83579d1392b6af6eef8d93047d49e0c0a00faf', 1, '2019-10-09 22:46:50', '', '2020-05-24 00:05:06'),
(61, 'barbara', '09995b0ba36f4e70c8ec38828bf00fe3a65845e2854065861f02a607b71afeeb3e37fe99f4515fd6', 1, '2019-10-09 22:47:35', '', '2020-05-24 00:05:06'),
(62, 'juan', 'b2b3cc92be337bf743138ff1a841f9d8ae25e0fb95b6a1d9506191763063baed8002e36385c0cc57', 1, '2019-10-09 22:48:57', '', '2020-05-24 00:05:06'),
(63, 'walter', '5b664c1584d3d8ef4df19dac1967f70d4434abec39ffc4967c23a2920a9786e7589b10340f2201ff', 0, '2019-10-09 22:49:55', '', '2020-05-24 00:05:06'),
(64, 'daniela', '0630ba7dfed86b40255f1d5e94638d1e105efbc8de306f4c913863fc0511ff10efb9f212a2e1ba4d', 1, '2019-10-22 00:01:15', '', '2020-05-24 00:05:06'),
(69, 'dora', '8769023e870d932f2e0af83914ea8432fe62853af2379348aa58955bcfd602d88d9ae5aad65cd2af', 1, '2019-11-03 17:14:36', '', '2020-05-24 00:05:06'),
(74, 'servicio4', 'f221a89c046184fd068377974e56061bcb79961ef2e456506092715b11dbfa45a4ba832a9517739c', 1, '2019-12-01 18:55:16', '', '2020-05-24 00:05:06'),
(75, 'servicio5', '31a7ac310d6ab1c1d30e4565030ee39f958f393fe461402c9a0899b70ef65dde337031e205537c2c', 1, '2019-12-01 18:57:08', '', '2020-05-24 00:05:06'),
(78, 'servicio8', '7a49b2bd8000984ca974d275d95c9365d7d52da9f6b6e4f5bdc95f3f1cbccbb45df26a358885de89', 1, '2019-12-04 22:59:26', '', '2020-05-24 00:05:06'),
(81, 'dausheff', '4d949ec854f080c355c86f9a297cfedcb572d17a1257807571a4410e3d1dcc4e32e2ca4915bf90fa', 1, '2019-12-06 23:49:13', '', '2020-05-24 00:05:06'),
(83, 'usuario', 'b83a56592aaa0e1655049827bef098f1a37028f693e6d04b9fde3a362bb3981bd5292b70a1ff1bc5', 1, '2020-01-10 17:52:03', '', '2020-05-24 00:05:06'),
(84, 'cliente', '246a26ea1d6494b3adeefb95c9b9746d5810c4514646a3c08d181b57e3b757c25456a21ff8399595', 1, '2020-01-12 23:08:26', '', '2020-05-24 00:05:06'),
(85, 'admin', '156cbf1d8b26f4f8da18e962a73709404099f687cb3c77632e81ac7e976e3284cf1d2f59c0fa28ec', 1, '2020-01-13 19:54:04', '', '2020-05-24 00:05:06'),
(90, 'corona', '9e18e24a8dcf8ec974312f9a9d551ead3c9f6e85c3f1edf12a94b2d5a356f9485b1b699d6524750f', 1, '2020-03-25 22:02:40', '', '2020-05-24 00:05:06'),
(91, 'graciela', 'fa9995483f96e2bd303f826e9e15931b46ad3b4b4485f1baada7ae1ef34ff5ce117c29daf7f5ba8f', 1, '2020-04-22 23:47:18', '', '2020-05-24 00:05:06'),
(93, 'Sabrina', 'e23a800406232f251561548b8e243fdc4ef2be4eb8ff50044ab8c7380c4cdfb8bbcba4e4a4eefe84', 1, '2020-04-25 20:23:42', '', '2020-05-24 00:05:06'),
(94, 'sergio', '9855f87ea1975f4cc9154a0f444c92c7ad08fd9e95a5704d9a0ac6465907a4b8a5c886424124732d', 1, '2020-05-03 12:31:31', '', '2020-05-24 00:05:06'),
(95, 'yolanda', 'd2d2208ce58d047b17e0e86712f7d271734fdda3c4ca3a791547b5f89e67b3d14e6783262d6ab899', 1, '2020-05-03 12:43:33', '', '2020-05-24 00:05:06'),
(96, 'florencia', 'cbfdf6de6357b293a53726b77fda713c3797db4f14d49f38f80155231acacd09e81aedef69755e04', 1, '2020-05-03 12:46:57', '', '2020-05-24 00:05:06'),
(97, 'otrocliente', '67f569905aced8fcae9162fa6663035cdfd4c7c13422afeaa3fae3b9822f143c5be8cf083bdce700', 1, '2020-05-24 20:28:06', 'admin', '2020-05-24 18:28:06'),
(105, 'sergiof', '95ea14e3b81c6652dd54cf23ee92f211ac491619c70aae6056a734bbbe5935ad68128f908275d496', 1, '2020-07-02 23:31:37', 'OWN USER', '2020-07-02 21:31:37'),
(106, 'jacinta', '9cd01588f9f420a45ed97c6938c6668302d4cd7b9c96ae0660f53ee0d3147c8d83bf14c97ce0b699', 1, '2020-09-19 12:35:15', 'OWN USER', '2020-09-19 10:35:15'),
(107, 'pascualino', 'c12a8b273c5beb58ae93d468bcf4730f74aef4295dfcc5f85d8059f464fea908e1ddace8db167b5a', 1, '2020-09-19 14:12:54', 'OWN USER', '2020-09-19 12:12:54'),
(108, 'evangelina', 'fcce62337f629a4655de1aab0da8f575878bf7565567b4f5ee4ff669e99af7d60e0e710192131e40', 1, '2020-09-19 14:37:52', 'OWN USER', '2020-09-19 12:37:52'),
(109, 'salome', '2e26ba388b157d35ac3cc1bb205509c14f38191d87b4e0bf083cdf371c039acfd5ef7c004537854f', 1, '2020-09-19 14:38:46', 'admin', '2020-09-19 12:39:45');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarioorden`
--

CREATE TABLE `usuarioorden` (
  `id` int(11) NOT NULL,
  `idUsr` int(11) NOT NULL,
  `tabla` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `columna` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `orden` varchar(4) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarioorden`
--

INSERT INTO `usuarioorden` (`id`, `idUsr`, `tabla`, `columna`, `orden`) VALUES
(1, 1, 'factura', 'idFac', 'DESC'),
(2, 1, 'producto', 'nombreES', 'ASC'),
(3, 1, 'empresa', 'nombreComercial', 'ASC'),
(4, 83, 'empresa', 'email', 'ASC'),
(5, 83, 'producto', 'nombreES', 'ASC'),
(6, 85, 'producto', 'nombreES', 'ASC'),
(7, 85, 'empresa', 'nombreComercial', 'ASC'),
(8, 83, 'factura', 'fechaCompra', 'DESC'),
(11, 85, 'usuario', 'datosPersonales.nombre', 'ASC'),
(12, 83, 'usuario', 'datosPersonales.nombre', 'ASC'),
(13, 1, 'usuario', 'datosPersonales.nombre', 'ASC'),
(14, 69, 'usuario', 'usuario', 'ASC'),
(15, 81, 'usuario', 'usuario', 'ASC'),
(16, 85, 'factura', 'idFac', 'ASC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_empresa`
--

CREATE TABLE `usuario_empresa` (
  `idUsr` int(11) NOT NULL,
  `idEmp` int(11) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `creadoPor` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario_empresa`
--

INSERT INTO `usuario_empresa` (`idUsr`, `idEmp`, `fechaCreacion`, `creadoPor`) VALUES
(1, 1, '2019-08-31', 'DAMIAN'),
(3, 3, '2019-10-06', 'pepe'),
(4, 1, '2019-10-09', 'juan'),
(4, 2, '2020-01-26', 'admin'),
(4, 6, '2019-11-03', 'dora'),
(44, 2, '2019-10-06', 'pepe'),
(46, 1, '2019-10-06', 'pepe'),
(55, 2, '2019-10-06', 'pepe'),
(58, 2, '2019-10-06', 'pepe'),
(58, 6, '2019-11-03', 'dora'),
(61, 2, '2020-01-26', 'pepe'),
(62, 1, '2020-04-19', 'pepe'),
(62, 2, '2019-10-09', 'pepe'),
(63, 3, '2019-10-09', 'pepe'),
(69, 6, '2019-11-03', 'dora'),
(84, 2, '2020-04-25', 'pepe'),
(90, 1, '2020-07-07', 'pepe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_rol`
--

CREATE TABLE `usuario_rol` (
  `idUsr` int(11) NOT NULL,
  `idRol` int(11) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `creadoPor` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario_rol`
--

INSERT INTO `usuario_rol` (`idUsr`, `idRol`, `fechaCreacion`, `creadoPor`) VALUES
(1, 3, '2019-10-11', 'pepe'),
(1, 4, '2019-10-11', 'pepe'),
(3, 3, '2019-09-30', 'pepe'),
(4, 1, '2019-10-11', 'damian'),
(44, 1, '2019-09-14', 'DAMIAN'),
(46, 1, '2019-10-09', 'pepe'),
(46, 3, '2019-10-09', 'pepe'),
(55, 1, '2019-10-22', 'pepe'),
(58, 1, '2019-10-09', 'pepe'),
(58, 3, '2020-05-31', 'dora'),
(60, 1, '2019-10-09', 'pepe'),
(61, 1, '2019-10-09', 'pepe'),
(62, 2, '2019-10-09', 'pepe'),
(63, 1, '2019-10-09', 'pepe'),
(64, 1, '2019-10-22', 'pepe'),
(69, 2, '2019-11-03', 'pepe'),
(69, 3, '2019-11-03', 'pepe'),
(74, 1, '2019-12-01', 'maria'),
(75, 1, '2019-12-02', 'pepe'),
(75, 2, '2019-12-01', 'pepe'),
(78, 2, '2019-12-04', 'pepe'),
(81, 3, '2020-05-29', 'pepe'),
(83, 2, '2020-05-29', 'pepe'),
(84, 1, '2020-01-12', 'OWN USER'),
(85, 3, '2020-06-01', 'pepe'),
(90, 1, '2020-03-25', 'OWN USER'),
(91, 1, '2020-04-22', 'pepe'),
(93, 1, '2020-04-25', 'OWN USER'),
(94, 1, '2020-05-03', 'OWN USER'),
(95, 1, '2020-05-03', 'OWN USER'),
(96, 1, '2020-05-03', 'OWN USER'),
(97, 1, '2020-05-24', 'admin'),
(105, 1, '2020-07-02', 'OWN USER'),
(106, 1, '2020-09-19', 'OWN USER'),
(107, 1, '2020-09-19', 'OWN USER'),
(108, 1, '2020-09-19', 'OWN USER'),
(109, 1, '2020-09-19', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idAd`);

--
-- Indices de la tabla `botonfavorito`
--
ALTER TABLE `botonfavorito`
  ADD PRIMARY KEY (`idBot`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCat`);

--
-- Indices de la tabla `constantes`
--
ALTER TABLE `constantes`
  ADD PRIMARY KEY (`clave`);

--
-- Indices de la tabla `cuota`
--
ALTER TABLE `cuota`
  ADD PRIMARY KEY (`idCuo`);

--
-- Indices de la tabla `cuotadetalle`
--
ALTER TABLE `cuotadetalle`
  ADD PRIMARY KEY (`idCuDe`),
  ADD KEY `idCuo` (`idCuo`);

--
-- Indices de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  ADD PRIMARY KEY (`idDatosPers`),
  ADD UNIQUE KEY `usuario_idUsr` (`datospersonales_idUsr`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`idDir`),
  ADD KEY `FK_idDatosPers` (`idDatosPers`) USING BTREE;

--
-- Indices de la tabla `direccionempresa`
--
ALTER TABLE `direccionempresa`
  ADD PRIMARY KEY (`idDirEmp`),
  ADD KEY `FK_idEmp` (`idEmp`) USING BTREE;

--
-- Indices de la tabla `direccionempresapropia`
--
ALTER TABLE `direccionempresapropia`
  ADD PRIMARY KEY (`idDirPropia`),
  ADD KEY `idPropia` (`idPropia`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`idEmp`);

--
-- Indices de la tabla `empresapropia`
--
ALTER TABLE `empresapropia`
  ADD PRIMARY KEY (`idPropia`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`idEst`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`idFac`),
  ADD KEY `idFor` (`idFor`),
  ADD KEY `idEst` (`idEst`);

--
-- Indices de la tabla `facturaenviarfacturar`
--
ALTER TABLE `facturaenviarfacturar`
  ADD PRIMARY KEY (`idEnFa`),
  ADD KEY `idFac` (`idFac`);

--
-- Indices de la tabla `factura_estado`
--
ALTER TABLE `factura_estado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `formapago`
--
ALTER TABLE `formapago`
  ADD PRIMARY KEY (`idFor`);

--
-- Indices de la tabla `foto`
--
ALTER TABLE `foto`
  ADD PRIMARY KEY (`idFot`);

--
-- Indices de la tabla `paises`
--
ALTER TABLE `paises`
  ADD PRIMARY KEY (`idPais`);

--
-- Indices de la tabla `preferenciausuario`
--
ALTER TABLE `preferenciausuario`
  ADD PRIMARY KEY (`idPrefUsr`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idPro`),
  ADD KEY `idSub` (`idSub`);

--
-- Indices de la tabla `producto_empresa`
--
ALTER TABLE `producto_empresa`
  ADD PRIMARY KEY (`idPro`,`idEmp`),
  ADD KEY `idEmp` (`idEmp`);

--
-- Indices de la tabla `producto_factura`
--
ALTER TABLE `producto_factura`
  ADD PRIMARY KEY (`idPro`,`idFac`),
  ADD KEY `idFac` (`idFac`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `subcategoria`
--
ALTER TABLE `subcategoria`
  ADD PRIMARY KEY (`idSub`),
  ADD KEY `idCat` (`idCat`);

--
-- Indices de la tabla `tema`
--
ALTER TABLE `tema`
  ADD PRIMARY KEY (`idTem`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsr`);

--
-- Indices de la tabla `usuarioorden`
--
ALTER TABLE `usuarioorden`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario_empresa`
--
ALTER TABLE `usuario_empresa`
  ADD PRIMARY KEY (`idUsr`,`idEmp`),
  ADD KEY `FK_idEmp` (`idEmp`);

--
-- Indices de la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD PRIMARY KEY (`idUsr`,`idRol`),
  ADD KEY `FK_idRoles` (`idRol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admin`
--
ALTER TABLE `admin`
  MODIFY `idAd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de la tabla `botonfavorito`
--
ALTER TABLE `botonfavorito`
  MODIFY `idBot` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idCat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `cuota`
--
ALTER TABLE `cuota`
  MODIFY `idCuo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `cuotadetalle`
--
ALTER TABLE `cuotadetalle`
  MODIFY `idCuDe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=119;

--
-- AUTO_INCREMENT de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  MODIFY `idDatosPers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `idDir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `direccionempresa`
--
ALTER TABLE `direccionempresa`
  MODIFY `idDirEmp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `direccionempresapropia`
--
ALTER TABLE `direccionempresapropia`
  MODIFY `idDirPropia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `empresapropia`
--
ALTER TABLE `empresapropia`
  MODIFY `idPropia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `idEst` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `idFac` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=179;

--
-- AUTO_INCREMENT de la tabla `facturaenviarfacturar`
--
ALTER TABLE `facturaenviarfacturar`
  MODIFY `idEnFa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `factura_estado`
--
ALTER TABLE `factura_estado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=240;

--
-- AUTO_INCREMENT de la tabla `formapago`
--
ALTER TABLE `formapago`
  MODIFY `idFor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `foto`
--
ALTER TABLE `foto`
  MODIFY `idFot` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT de la tabla `paises`
--
ALTER TABLE `paises`
  MODIFY `idPais` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idPro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `subcategoria`
--
ALTER TABLE `subcategoria`
  MODIFY `idSub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `tema`
--
ALTER TABLE `tema`
  MODIFY `idTem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT de la tabla `usuarioorden`
--
ALTER TABLE `usuarioorden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuotadetalle`
--
ALTER TABLE `cuotadetalle`
  ADD CONSTRAINT `cuotadetalle_ibfk_1` FOREIGN KEY (`idCuo`) REFERENCES `cuota` (`idCuo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  ADD CONSTRAINT `fk_datospersonales_usuario` FOREIGN KEY (`datospersonales_idUsr`) REFERENCES `usuario` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`idDatosPers`) REFERENCES `datospersonales` (`idDatosPers`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `direccionempresa`
--
ALTER TABLE `direccionempresa`
  ADD CONSTRAINT `direccionEmpresa_ibfk_1` FOREIGN KEY (`idEmp`) REFERENCES `empresa` (`idEmp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`idEst`) REFERENCES `estado` (`idEst`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`idFor`) REFERENCES `formapago` (`idFor`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `facturaenviarfacturar`
--
ALTER TABLE `facturaenviarfacturar`
  ADD CONSTRAINT `facturaenviarfacturar_ibfk_1` FOREIGN KEY (`idFac`) REFERENCES `factura` (`idFac`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idSub`) REFERENCES `subcategoria` (`idSub`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto_empresa`
--
ALTER TABLE `producto_empresa`
  ADD CONSTRAINT `producto_empresa_ibfk_1` FOREIGN KEY (`idEmp`) REFERENCES `empresa` (`idEmp`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `producto_empresa_ibfk_2` FOREIGN KEY (`idPro`) REFERENCES `producto` (`idPro`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto_factura`
--
ALTER TABLE `producto_factura`
  ADD CONSTRAINT `producto_factura_ibfk_1` FOREIGN KEY (`idFac`) REFERENCES `factura` (`idFac`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `producto_factura_ibfk_2` FOREIGN KEY (`idPro`) REFERENCES `producto` (`idPro`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `subcategoria`
--
ALTER TABLE `subcategoria`
  ADD CONSTRAINT `subcategoria_ibfk_1` FOREIGN KEY (`idCat`) REFERENCES `categoria` (`idCat`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario_empresa`
--
ALTER TABLE `usuario_empresa`
  ADD CONSTRAINT `FK_idEmp` FOREIGN KEY (`idEmp`) REFERENCES `empresa` (`idEmp`),
  ADD CONSTRAINT `FK_idUsrEmp` FOREIGN KEY (`idUsr`) REFERENCES `usuario` (`idUsr`);

--
-- Filtros para la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD CONSTRAINT `FK_idRol` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`),
  ADD CONSTRAINT `FK_idUsr` FOREIGN KEY (`idUsr`) REFERENCES `usuario` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
