-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-07-2020 a las 23:48:22
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
(9, 'Ropa', 'Clothe', NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2020-05-24 18:26:28');

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
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
(36, 2, 0, 0, 0, 0, 0, '', '2020-05-23 23:59:03');

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
  `observaciones` text COLLATE utf8_spanish_ci,
  `datospersonales_idUsr` int(11) NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
(47, 'Daniela', 'Solar Gaudio', 'Duero', 'Mujer', NULL, 1, '', 'daniela@daniela.com', '', '', 64, '', '2020-05-23 23:59:35'),
(52, 'Dora', 'Núñez', 'Gala', 'Mujer', NULL, 0, '', 'dora@dora.com', '659659658', '', 69, 'admin', '2020-05-31 22:17:07'),
(56, 'Servicio4aaa', 'Servicio', 'Cuatro', 'Hombre', '2003-02-01', 0, '', 'servicio4@gmail.com', '', '', 74, '', '2020-05-23 23:59:35'),
(57, 'Servicio5', 'Servicio', 'Cinco', 'Mujer', '1972-09-30', 0, '12345678A', 'servicio5@gmail.com', '600000000', 'us us', 75, '', '2020-05-23 23:59:35'),
(60, 'Servicio8', 'Servicio', 'Seis', 'Hombre', '2008-08-08', 0, '', 'servicio8@gmail.com', '', 'Observaciones8', 78, '', '2020-05-23 23:59:35'),
(63, 'Felipe', 'Sexto', '', 'Mujer', NULL, 0, '', 'sanlore@gmail.com', '678678678', '', 81, 'pepe', '2020-07-02 21:38:44'),
(64, 'Servicío11', 'Servício', 'Ónce', 'Mujer', '1984-08-25', 0, '12345678A', 'servicio11@gmail.com', '600000011', 'Test con muchos acentos para análisis completo', 82, '', '2020-05-23 23:59:35'),
(65, 'Usuario', 'Con el Rol', 'De Usuario', NULL, '1979-07-04', 2, '', 'usuario@gmail.com', '658658658', '', 83, 'pepe', '2020-05-29 15:30:22'),
(66, 'Cliente', 'Con el Rol', 'De Cliente', 'Mujer', '1980-06-10', 3, '', 'cliente@gmail.com', '661661661', '', 84, '', '2020-05-23 23:59:35'),
(67, 'Administrador', 'Con Rol', 'Admin', 'Mujer', '1980-10-10', 6, '01234567A', 'admin@gmail.com', '600111226', 'Administrador para test', 85, 'admin', '2020-06-03 20:49:06'),
(71, 'Capo Servicio', 'Numero', 'Diez', NULL, '1974-06-12', 0, '', 'elservicio@servicio.com', '', '', 89, '', '2020-05-23 23:59:35'),
(72, 'Virus', 'Chino', '', 'Hombre', '2019-12-20', 1, '', 'corona@vir.us', '', '', 90, '', '2020-05-23 23:59:35'),
(73, 'Graciela', 'Rodríguez', 'Sánchez', 'Mujer', '1974-06-21', 1, '01234567A', 'graciela@graciela.com', '654987987', 'La observación de Graciela', 91, '', '2020-05-23 23:59:35'),
(74, 'Alejandro', 'Magno', '', NULL, NULL, 0, '', 'alejandro@alejandro.com', '', '', 92, '', '2020-05-23 23:59:35'),
(75, 'Sabrina', 'Pérez', 'Aguado', 'Mujer', '1980-07-18', 4, '18868446M', 'sabrina@sabrina.com', '612612612', 'La observación de Sabrina', 93, '', '2020-05-23 23:59:35'),
(76, 'Sergio', 'Fernández', '', NULL, NULL, 0, '', 'sergio@gmail.com.es', '', '', 94, '', '2020-05-23 23:59:35'),
(77, 'Yolanda', 'Artunez', '', NULL, NULL, 0, '', 'yolanda@gmail.com', '', '', 95, '', '2020-05-23 23:59:35'),
(78, 'Florencia', 'Peña', '', NULL, NULL, 0, '', 'florencia@gmail.com', '', '', 96, '', '2020-05-23 23:59:35'),
(79, 'Claudio', 'Paul', 'Caniggia', NULL, NULL, 0, '', 'claudio@gmail.com', '', '', 97, 'admin', '2020-05-24 18:28:21'),
(80, 'Sergio', 'Fernández', 'López', 'Hombre', '1980-06-19', 2, '01234567R', 'sergiof@gmail.com', '652652652', 'adfasdf', 105, 'sergiof', '2020-07-02 21:41:08');

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
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
(7, 'road.Paseo', 'Colón', '10', '', '28004', '', 'Madrid', 0, 12, 'pepe', '2020-07-07 20:36:51');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `idEmp` int(11) NOT NULL,
  `nombreComercial` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipoSociedad` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `actividad` text COLLATE utf8_spanish_ci,
  `cif` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `paginaWeb` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observaciones` text COLLATE utf8_spanish_ci,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
(14, 'Sinesio Peluqueros', 'SL', 'Peluquería', '', '', '', '', '', '', 'admin', '2020-05-24 18:40:33');

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
  `facturacion` tinyint(4) NOT NULL,
  `idDirEmp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

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
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
  `ivaTotal` double NOT NULL,
  `ivaImporteTotal` double NOT NULL,
  `descuentoTotal` double NOT NULL,
  `descuentoImporteTotal` double NOT NULL,
  `importeEnvioSinIva` double NOT NULL,
  `importeTotal` double NOT NULL,
  `fechaCompra` datetime NOT NULL,
  `fechaEntrega` datetime DEFAULT NULL,
  `idEst` int(11) NOT NULL,
  `observaciones` varchar(300) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idFor` int(11) NOT NULL,
  `creadoPor` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `idCuo` int(11) NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`idFac`, `compra`, `ivaTotal`, `ivaImporteTotal`, `descuentoTotal`, `descuentoImporteTotal`, `importeEnvioSinIva`, `importeTotal`, `fechaCompra`, `fechaEntrega`, `idEst`, `observaciones`, `idFor`, `creadoPor`, `idCuo`, `modificadoPor`, `fechaModificacion`) VALUES
(7, 1, 10, 49.5, 0, 0, 0, 495, '2020-01-17 20:51:06', NULL, 6, '', 4, 'admin', 0, '', '2020-05-24 00:02:04'),
(8, 0, 21, 50.82, 0, 0, 0, 242, '2020-01-18 00:00:00', NULL, 7, 'Primera prueba QUITAR unidades. TV Sanyo 15 pulgadas. Saco 1 unidad. Importe 242€. IVA 21%', 4, 'admin', 0, '', '2020-05-24 00:02:04'),
(9, 0, 21, 42, 0, 0, 0, 242, '2020-03-28 00:00:00', NULL, 1, '', 4, 'admin', 0, '', '2020-05-24 00:02:04'),
(64, 1, 21, 118.93, 0, 0, 0, 685.27, '2020-03-01 00:00:00', NULL, 6, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(66, 1, 21, 0, 0, 0, 0, 0, '2020-05-01 00:00:00', NULL, 1, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(67, 1, 21, 0, 0, 0, 0, 0, '2020-06-01 00:00:00', NULL, 8, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(68, 1, 21, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 8, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(69, 1, 21, 0, 0, 0, 0, 0, '2020-08-01 00:00:00', NULL, 8, '', 4, 'pepe', 24, '', '2020-05-24 00:02:04'),
(72, 1, 0, 0, 0, 0, 0, 7, '2020-03-22 00:00:00', NULL, 3, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(74, 1, 0, 0, 0, 0, 0, 8, '2020-02-15 00:00:00', NULL, 8, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(75, 0, 0, 0, 0, 0, 0, 0, '2020-02-15 11:40:35', NULL, 7, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(85, 1, 0, 0, 0, 0, 0, 12, '2020-06-01 00:00:00', NULL, 6, '', 4, 'pepe', 26, '', '2020-05-24 00:02:04'),
(86, 1, 0, 0, 0, 0, 0, 9, '2020-02-15 14:38:39', NULL, 6, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(87, 0, 0, 0, 0, 0, 0, 20, '2020-02-15 14:38:53', NULL, 7, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(88, 1, 21, 26.03, 0, 0, 0, 150, '2020-03-16 23:18:40', NULL, 6, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(90, 1, 0, 0, 0, 0, 0, 20, '2020-05-01 00:00:00', NULL, 6, '', 4, 'pepe', 27, '', '2020-05-24 00:02:04'),
(91, 1, 0, 0, 0, 0, 0, 0, '2020-06-01 00:00:00', NULL, 6, '', 4, 'pepe', 27, '', '2020-05-24 00:02:04'),
(92, 1, 0, 0, 0, 0, 0, 15, '2020-05-01 00:00:00', NULL, 6, '', 4, 'pepe', 28, '', '2020-05-24 00:02:04'),
(94, 1, 0, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 6, '', 4, 'pepe', 28, '', '2020-05-24 00:02:04'),
(95, 1, 0, 0, 0, 0, 0, 0, '2020-08-01 00:00:00', NULL, 6, '', 4, 'pepe', 28, '', '2020-05-24 00:02:04'),
(96, 1, 0, 0, 0, 0, 0, 0, '2020-09-01 00:00:00', NULL, 6, '', 4, 'pepe', 28, '', '2020-05-24 00:02:04'),
(100, 1, 6, 3.96, 0, 0, 0, 70, '2020-05-01 00:00:00', NULL, 1, 'Con la pantalla minimizada', 4, 'pepe', 30, '', '2020-05-24 00:02:04'),
(101, 1, 6, 0, 0, 0, 0, 0, '2020-06-01 00:00:00', NULL, 6, 'Con la pantalla minimizada', 4, 'pepe', 30, '', '2020-05-24 00:02:04'),
(102, 1, 6, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 6, 'Con la pantalla minimizada', 4, 'pepe', 30, '', '2020-05-24 00:02:04'),
(103, 1, 5, 2.85, 0, 0, 0, 60, '2020-05-01 00:00:00', NULL, 6, 'Observación de agregado castañas', 4, 'pepe', 31, '', '2020-05-24 00:02:04'),
(104, 1, 5, 0, 0, 0, 0, 0, '2020-06-01 00:00:00', NULL, 6, 'Observación de agregado castañas', 4, 'pepe', 31, '', '2020-05-24 00:02:04'),
(105, 1, 5, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 6, 'Observación de agregado castañas', 4, 'pepe', 31, '', '2020-05-24 00:02:04'),
(106, 1, 0, 0, 0, 0, 0, 1, '2020-05-03 00:00:00', NULL, 6, '', 4, 'pepe', 32, '', '2020-05-24 00:02:04'),
(107, 1, 0, 0, 0, 0, 0, 2, '2020-05-03 00:00:00', NULL, 8, '', 4, 'pepe', 33, '', '2020-05-24 00:02:04'),
(108, 1, 21, 2.6, 0, 0, 0, 15, '2020-06-01 00:00:00', NULL, 6, '', 4, 'pepe', 34, '', '2020-05-24 00:02:04'),
(109, 1, 21, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 1, '', 4, 'pepe', 34, '', '2020-05-24 00:02:04'),
(110, 1, 21, 0, 0, 0, 0, 0, '2020-08-01 00:00:00', NULL, 6, '', 4, 'pepe', 34, '', '2020-05-24 00:02:04'),
(111, 1, 0, 0, 0, 0, 0, 0, '2020-06-01 00:00:00', NULL, 6, '', 4, 'pepe', 35, '', '2020-05-24 00:02:04'),
(112, 1, 0, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 6, '', 4, 'pepe', 35, '', '2020-05-24 00:02:04'),
(113, 1, 0, 0, 0, 0, 0, 0, '2020-05-03 14:02:22', NULL, 6, '', 4, 'pepe', 0, '', '2020-05-24 00:02:04'),
(114, 1, 0, 0, 0, 0, 0, 20, '2020-06-01 00:00:00', NULL, 3, '', 4, 'pepe', 36, 'pepe', '2020-07-07 21:04:29'),
(115, 1, 0, 0, 0, 0, 0, 0, '2020-07-01 00:00:00', NULL, 6, '', 4, 'pepe', 36, '', '2020-05-24 00:02:04'),
(116, 1, 0, 0, 0, 0, 0, 10, '2020-05-24 20:42:23', NULL, 6, '', 4, 'pepe', 0, 'pepe', '2020-05-24 18:42:23');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturaenviarfacturar`
--

CREATE TABLE `facturaenviarfacturar` (
  `idEnFa` int(11) NOT NULL,
  `nombre` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `cp` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `ciudad` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `provincia` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `pais` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `facturar` tinyint(4) NOT NULL,
  `enviar` tinyint(4) NOT NULL,
  `idFac` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

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
  `observaciones` text COLLATE utf8_spanish_ci
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
(147, 114, 3, '2020-07-07 23:04:29', 'pepe', 'se comprueba funcionamiento de cambio de estado en una factura');

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
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
  `descripcion` text COLLATE utf8_spanish_ci,
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
(68, 0, 5, 0, 0, 0, 0, 0, 0, 0, 'IMG_0311.JPG', '\\resources\\imgs\\productos\\5', '', 1219528, 1, NULL, '2020-07-07 22:39:45', 'pepe', '2020-07-07 22:39:45', 'pepe');

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
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idPro` int(11) NOT NULL,
  `nombreES` text COLLATE utf8_spanish_ci NOT NULL,
  `nombreEN` text COLLATE utf8_spanish_ci NOT NULL,
  `nombrePT` text COLLATE utf8_spanish_ci,
  `nombreFR` text COLLATE utf8_spanish_ci,
  `nombreIT` text COLLATE utf8_spanish_ci,
  `nombreGE` text COLLATE utf8_spanish_ci,
  `nombreCA` text COLLATE utf8_spanish_ci,
  `nombreEU` text COLLATE utf8_spanish_ci,
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
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
(6, 'Estantería de madera color roble', 'Oak wood shelving', NULL, NULL, NULL, NULL, NULL, NULL, 7, 45, 32, 'Ikea', '', '', '', 'ACTIVE', NULL, NULL, 0, 1, 6, 0, 0, 17, '', '2020-05-24 00:03:08'),
(7, 'Taza Star Wars negra', 'Star Wars Mug Black', NULL, NULL, NULL, NULL, NULL, NULL, 9, 2.5, 0.7, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 1, 0, 0, 0, 15, 'pepe', '2020-07-07 21:06:45'),
(8, 'Reloj temperatura', 'Temperature clock', NULL, NULL, NULL, NULL, NULL, NULL, 0, 12.5, 7, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 15, 'admin', '2020-05-28 21:20:04'),
(9, 'Cable USB', 'USB cable', NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 1, 'IBM', 'X26', '12358', 'Pasillo 3', 'INACTIVE', NULL, NULL, 0, 1, 3, 0.2, 0.6, 2, '', '2020-05-24 00:03:08'),
(10, 'Jamón ibérico', 'Jam', NULL, NULL, NULL, NULL, NULL, NULL, 0, 12, 0, '4J', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 0, 0, 18, 'pepe', '2020-07-07 21:08:13'),
(11, 'Mesa redonda', 'Circle table', NULL, NULL, NULL, NULL, NULL, NULL, 0, 30, 0, '', '', '', '', 'ACTIVE', NULL, NULL, 0, 0, 0, 7, 0, 5, '', '2020-05-24 00:03:08'),
(12, 'Queso Casanto', 'Casanto Chees', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1.2, 0.84, 'Casanto', '', '', '', 'ACTIVE', NULL, NULL, 0, 1, 0, 0.12, 0, 19, 'pepe', '2020-05-24 18:45:09');

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
  `ivaProducto` double DEFAULT NULL,
  `porcentajeDescuento` double DEFAULT NULL,
  `precioUnitSinIva` double NOT NULL,
  `precioUnitConIva` double NOT NULL,
  `precioFinal` double NOT NULL,
  `observaciones` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `producto_factura`
--

INSERT INTO `producto_factura` (`idPro`, `idFac`, `cantidad`, `ivaProducto`, `porcentajeDescuento`, `precioUnitSinIva`, `precioUnitConIva`, `precioFinal`, `observaciones`, `modificadoPor`, `fechaModificacion`) VALUES
(1, 7, 5, 10, 0, 90, 99, 495, NULL, '', '2020-05-24 00:03:38'),
(1, 8, 1, 21, 0, 200, 242, 242, NULL, '', '2020-05-24 00:03:38'),
(1, 9, 1, 21, 0, 200, 242, 242, NULL, '', '2020-05-24 00:03:38'),
(3, 72, 5, 0, 0, 1.4, 1.4, 7, NULL, '', '2020-05-24 00:03:38'),
(3, 74, 5, 0, 0, 1.6, 1.6, 8, NULL, '', '2020-05-24 00:03:38'),
(3, 75, 7, 0, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 100, 2, 6, 0, 33.01, 35, 70, NULL, '', '2020-05-24 00:03:38'),
(3, 101, 0, 6, 0, 33.01, 35, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 102, 0, 6, 0, 33.01, 35, 0, NULL, '', '2020-05-24 00:03:38'),
(3, 106, 1, 0, 0, 1, 1, 1, NULL, '', '2020-05-24 00:03:38'),
(4, 92, 2, 0, 0, 7.5, 7.5, 15, NULL, '', '2020-05-24 00:03:38'),
(4, 94, 0, 0, 0, 7.5, 7.5, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 95, 0, 0, 0, 7.5, 7.5, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 96, 0, 0, 0, 7.5, 7.5, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 103, 3, 5, 0, 19.04, 20, 60, NULL, '', '2020-05-24 00:03:38'),
(4, 104, 0, 5, 0, 19.04, 20, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 105, 0, 5, 0, 19.04, 20, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 108, 2, 21, 0, 6.19, 7.5, 15, NULL, '', '2020-05-24 00:03:38'),
(4, 109, 0, 21, 0, 6.19, 7.5, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 110, 0, 21, 0, 6.19, 7.5, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 111, 0, 0, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 112, 0, 0, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 113, 0, 0, 0, 0, 0, 0, NULL, '', '2020-05-24 00:03:38'),
(4, 114, 1, 0, 0, 20, 20, 20, NULL, '', '2020-05-24 00:03:38'),
(4, 115, 0, 0, 0, 20, 20, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 64, 1, 21, 0, 566.33, 685.27, 685.27, NULL, '', '2020-05-24 00:03:38'),
(6, 66, 0, 21, 0, 566.33, 685.27, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 67, 0, 21, 0, 566.33, 685.27, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 68, 0, 21, 0, 566.33, 685.27, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 69, 0, 21, 0, 566.33, 685.27, 0, NULL, '', '2020-05-24 00:03:38'),
(6, 88, 2, 21, 0, 61.98, 75, 150, NULL, '', '2020-05-24 00:03:38'),
(7, 85, 4, 0, 0, 3, 3, 0, NULL, '', '2020-05-24 00:03:38'),
(7, 86, 3, 0, 0, 3, 3, 9, NULL, '', '2020-05-24 00:03:38'),
(7, 87, 2, 0, 0, 10, 10, 20, NULL, '', '2020-05-24 00:03:38'),
(7, 90, 2, 0, 0, 10, 10, 20, NULL, '', '2020-05-24 00:03:38'),
(7, 91, 0, 0, 0, 10, 10, 0, NULL, '', '2020-05-24 00:03:38'),
(7, 107, 1, 0, 0, 2, 2, 2, NULL, '', '2020-05-24 00:03:38'),
(7, 116, 1, 0, 0, 10, 10, 10, NULL, 'pepe', '2020-05-24 18:42:23');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL,
  `rol` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `modificadoPor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
(20, 'Camisas', 'Shirts', NULL, NULL, NULL, NULL, NULL, NULL, 9, 'admin', '2020-06-03 20:52:12');

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
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
(82, 'servicio11', '804097c51146ba177cdc406b06e21aef3f5bfafe91341cd1bb08bcd58517a488293c1f3b25c9fe0f', 1, '2019-12-09 13:42:37', '', '2020-05-24 00:05:06'),
(83, 'usuario', 'b83a56592aaa0e1655049827bef098f1a37028f693e6d04b9fde3a362bb3981bd5292b70a1ff1bc5', 1, '2020-01-10 17:52:03', '', '2020-05-24 00:05:06'),
(84, 'cliente', '246a26ea1d6494b3adeefb95c9b9746d5810c4514646a3c08d181b57e3b757c25456a21ff8399595', 1, '2020-01-12 23:08:26', '', '2020-05-24 00:05:06'),
(85, 'admin', '156cbf1d8b26f4f8da18e962a73709404099f687cb3c77632e81ac7e976e3284cf1d2f59c0fa28ec', 1, '2020-01-13 19:54:04', '', '2020-05-24 00:05:06'),
(89, 'servicio10', '560387f8a3b50960752ab727a17c11076f1d6da7f88592871606a71e295286563233b20000f325f1', 1, '2020-03-22 00:27:24', '', '2020-05-24 00:05:06'),
(90, 'corona', '9e18e24a8dcf8ec974312f9a9d551ead3c9f6e85c3f1edf12a94b2d5a356f9485b1b699d6524750f', 1, '2020-03-25 22:02:40', '', '2020-05-24 00:05:06'),
(91, 'graciela', 'fa9995483f96e2bd303f826e9e15931b46ad3b4b4485f1baada7ae1ef34ff5ce117c29daf7f5ba8f', 1, '2020-04-22 23:47:18', '', '2020-05-24 00:05:06'),
(92, 'mirorol1', 'b3ad838aca284016747db3f1dfc57ca3c44c4222c38c53e0c5a966fa6e50dc5a2b714153a57442a4', 1, '2020-04-23 22:11:09', '', '2020-05-24 00:05:06'),
(93, 'Sabrina', 'e23a800406232f251561548b8e243fdc4ef2be4eb8ff50044ab8c7380c4cdfb8bbcba4e4a4eefe84', 1, '2020-04-25 20:23:42', '', '2020-05-24 00:05:06'),
(94, 'sergio', '9855f87ea1975f4cc9154a0f444c92c7ad08fd9e95a5704d9a0ac6465907a4b8a5c886424124732d', 1, '2020-05-03 12:31:31', '', '2020-05-24 00:05:06'),
(95, 'yolanda', 'd2d2208ce58d047b17e0e86712f7d271734fdda3c4ca3a791547b5f89e67b3d14e6783262d6ab899', 1, '2020-05-03 12:43:33', '', '2020-05-24 00:05:06'),
(96, 'florencia', 'cbfdf6de6357b293a53726b77fda713c3797db4f14d49f38f80155231acacd09e81aedef69755e04', 1, '2020-05-03 12:46:57', '', '2020-05-24 00:05:06'),
(97, 'otrocliente', '67f569905aced8fcae9162fa6663035cdfd4c7c13422afeaa3fae3b9822f143c5be8cf083bdce700', 1, '2020-05-24 20:28:06', 'admin', '2020-05-24 18:28:06'),
(105, 'sergiof', '95ea14e3b81c6652dd54cf23ee92f211ac491619c70aae6056a734bbbe5935ad68128f908275d496', 1, '2020-07-02 23:31:37', 'OWN USER', '2020-07-02 21:31:37');

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
(1, 1, 'factura', 'ivaTotal', 'DESC'),
(2, 1, 'producto', 'nombreES', 'ASC'),
(3, 1, 'empresa', 'nombreComercial', 'ASC'),
(4, 83, 'empresa', 'email', 'ASC'),
(5, 83, 'producto', 'nombreES', 'ASC'),
(6, 85, 'producto', 'nombreEN', 'ASC'),
(7, 85, 'empresa', 'nombreComercial', 'ASC'),
(8, 83, 'factura', 'fechaCompra', 'DESC'),
(9, 85, 'factura', 'importeFront', 'DESC'),
(11, 85, 'usuario', 'usuario', 'ASC'),
(12, 83, 'usuario', 'datosPersonales.nombre', 'ASC'),
(13, 1, 'usuario', 'usuario.usuario', 'ASC'),
(14, 69, 'usuario', 'usuario', 'ASC'),
(15, 81, 'usuario', 'usuario', 'ASC');

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
(90, 1, '2020-07-07', 'pepe'),
(92, 1, '2020-05-10', 'pepe');

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
(82, 2, '2019-12-09', 'pepe'),
(83, 2, '2020-05-29', 'pepe'),
(84, 1, '2020-01-12', 'OWN USER'),
(85, 3, '2020-06-01', 'pepe'),
(89, 1, '2020-03-22', 'pepe'),
(90, 1, '2020-03-25', 'OWN USER'),
(91, 1, '2020-04-22', 'pepe'),
(92, 1, '2020-04-23', 'pepe'),
(93, 1, '2020-04-25', 'OWN USER'),
(94, 1, '2020-05-03', 'OWN USER'),
(95, 1, '2020-05-03', 'OWN USER'),
(96, 1, '2020-05-03', 'OWN USER'),
(97, 1, '2020-05-24', 'admin'),
(105, 1, '2020-07-02', 'OWN USER');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idAd`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCat`);

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
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idCat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `cuota`
--
ALTER TABLE `cuota`
  MODIFY `idCuo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT de la tabla `cuotadetalle`
--
ALTER TABLE `cuotadetalle`
  MODIFY `idCuDe` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  MODIFY `idDatosPers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;
--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `idDir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT de la tabla `direccionempresa`
--
ALTER TABLE `direccionempresa`
  MODIFY `idDirEmp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `empresapropia`
--
ALTER TABLE `empresapropia`
  MODIFY `idPropia` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `idEst` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `idFac` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;
--
-- AUTO_INCREMENT de la tabla `facturaenviarfacturar`
--
ALTER TABLE `facturaenviarfacturar`
  MODIFY `idEnFa` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `factura_estado`
--
ALTER TABLE `factura_estado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=148;
--
-- AUTO_INCREMENT de la tabla `formapago`
--
ALTER TABLE `formapago`
  MODIFY `idFor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `foto`
--
ALTER TABLE `foto`
  MODIFY `idFot` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT de la tabla `paises`
--
ALTER TABLE `paises`
  MODIFY `idPais` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idPro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `subcategoria`
--
ALTER TABLE `subcategoria`
  MODIFY `idSub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;
--
-- AUTO_INCREMENT de la tabla `usuarioorden`
--
ALTER TABLE `usuarioorden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
