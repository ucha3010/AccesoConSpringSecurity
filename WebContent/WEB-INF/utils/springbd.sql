-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-12-2019 a las 23:52:45
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
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idCat`, `nombre`) VALUES
(1, 'Electrónica');

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
  `nacionalidad` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dni` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observaciones` text COLLATE utf8_spanish_ci,
  `datospersonales_idUsr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `datospersonales`
--

INSERT INTO `datospersonales` (`idDatosPers`, `nombre`, `apellido1`, `apellido2`, `sexo`, `fechaNacimiento`, `nacionalidad`, `dni`, `email`, `telefono`, `observaciones`, `datospersonales_idUsr`) VALUES
(1, 'José', 'Martínez', 'Suárez', 'Hombre', '1970-07-19', 'España', '123456789A', 'pepe@pepe.com', '666555444', 'El usuario principal con el cual hacer pruebas', 1),
(3, 'Roberto', 'Do Santos', '', 'Hombre', '1980-07-28', 'Portugal', '123456789D', 'roberto@roberto.com', '', '', 3),
(4, 'Damián', 'Usheff', '', 'Hombre', '1976-10-30', 'España', '123456789D', 'damian@damian.com', '666666666', '', 4),
(32, 'Damián', 'Usheff', 'Vellianitis', 'Hombre', '1976-10-30', 'Austria', '12345678A', 'damian@damian.com', '698765435', 'Observaciones para ser guardadas en base de datos', 44),
(34, 'María Antonieta', 'De las Nieves', 'Salgados', 'Mujer', '1973-03-16', 'empty', '123456789D', 'asdfasf@kjhicb.com', '638638632', '', 46),
(38, 'Lara', 'Fuentes', 'Galeano', 'Mujer', '2000-05-01', 'Andorra', '12345678S', 'asdfasf@kjhicb.com', '600000006', '', 55),
(41, 'Saúl', 'Rodríguez', 'Becerra', 'Hombre', '2002-02-02', 'Germany', '12345678A', 'saul@saul.com', '600000001', 'Alguna observación para ver el reseteo de la contraseña', 58),
(43, 'Alejandro', 'Vázquez', 'López', 'Hombre', '1984-02-25', 'España', '', 'asdfasf@kjhicb.com', '', '', 60),
(44, 'Bárbara', 'López', 'Aguirre', 'Mujer', NULL, 'empty', '', 'barbara@gmail.com', '', '', 61),
(45, 'Juan', 'Pérez', 'Arregui', 'Hombre', '1974-07-14', 'España', '12345678J', 'juan@juan.com', '699999999', '', 62),
(46, 'Walter', 'Tamanaha', 'Prado', 'Hombre', '1976-10-06', 'Turquía', '', 'walter@gmail.com', '', '', 63),
(47, 'Daniela', 'Solar', 'Duero', 'Mujer', NULL, 'España', '', 'daniela@daniela.com', '', '', 64),
(48, 'Tamara', 'Juárez', 'Aguayo', 'Mujer', '1985-04-15', 'España', 'Z2270588X', 'tamara@tamara.com', '688888888', '', 65),
(49, 'Rubén', 'Flores', 'Rosi', 'Hombre', '1984-08-06', 'España', '', 'ruben@ruben.com', '', '', 66),
(50, 'Isabel', 'Gutierrez', 'Nereo', 'Mujer', NULL, 'España', '', 'isabel@isabel.com', '', '', 67),
(51, 'Edu', 'Eduap', '', NULL, NULL, 'empty', '', 'edu@edu.com', '', '', 68),
(52, 'Dora', 'Núñez', 'Gala', 'Mujer', NULL, 'Grecia', '', 'dora@dora.com', '', '', 69),
(56, 'Servicio4aaa', 'Servicio', 'Cuatro', 'Hombre', '2003-02-01', 'empty', '', 'servicio4@gmail.com', '', '', 74),
(57, 'Servicio5', 'Servicio', 'Cinco', 'Mujer', '1972-09-30', 'San Marino', '12345678A', 'servicio5@gmail.com', '600000000', 'us us', 75),
(60, 'Servicio8', 'Servicio', 'Seis', 'Hombre', '2008-08-08', 'Dinamarca', '', 'servicio8@gmail.com', '', 'Observaciones8', 78),
(61, 'Servicio9', 'Servicio', 'Nueve', 'Mujer', '2009-09-09', 'empty', '', 'servicio9@gmail.com', '', '', 79),
(63, 'Felipe', 'Sexto', '', NULL, NULL, 'empty', '', 'sanlore@gmail.com', '', '', 81),
(64, 'Servicío11', 'Servício', 'Ónce', 'Mujer', '1984-08-25', 'Bélgica', '12345678A', 'servicio11@gmail.com', '600000011', 'Test con muchos acentos para análisis completo', 82);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `idDir` int(11) NOT NULL,
  `tipoVia` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreVia` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `resto` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cp` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provincia` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ciudad` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pais` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idDatosPers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `direccion`
--

INSERT INTO `direccion` (`idDir`, `tipoVia`, `nombreVia`, `numero`, `resto`, `cp`, `provincia`, `ciudad`, `pais`, `idDatosPers`) VALUES
(7, 'Calle', 'Menor', '2', NULL, '28005', 'Madrid', 'Madrid', 'España', 1),
(11, 'Calle', 'Ana de Austria', '50', NULL, '28050', 'Madrid', 'Madrid', 'España', 4),
(12, 'Avenida', 'Niceto Alcalá Zamora', '200', 'Urbanización El sol naciente', '28050', 'Madrid', 'Madrid', 'España', 4),
(13, 'Plaza', 'Hidalgo', '3', NULL, '99009', 'Vallladolid', 'Valladolid', 'España', 1),
(15, 'Calle', 'Martín', '23', NULL, '28050', 'Madrid', 'Sanchinaroo', 'España', 3),
(16, 'Calle', 'Américo Castro', '100', '2ºB', '28050', 'Madrid', 'Madrid', 'Spain', 32),
(18, 'Calle', 'Américo Castro 104 2ºB', '', '', '28050', 'Madrid', 'Madrid', 'España', 34),
(20, 'Plaza', 'Mayor', '1', '', '28001', 'Madrid', 'Madrid', 'Italy', 32),
(21, 'Avenida', 'Velazquez', '35', '', '28027', 'Madrid', 'Madrid', 'España', 45),
(22, 'Paseo', 'Extremadura', '30', '', '28024', '', 'Madrid', 'España', 47);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccionempresa`
--

CREATE TABLE `direccionempresa` (
  `idDirEmp` int(10) NOT NULL,
  `tipoVia` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreVia` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `resto` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cp` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provincia` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ciudad` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pais` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idEmp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `direccionempresa`
--

INSERT INTO `direccionempresa` (`idDirEmp`, `tipoVia`, `nombreVia`, `numero`, `resto`, `cp`, `provincia`, `ciudad`, `pais`, `idEmp`) VALUES
(1, 'Calle', 'Mayor', '6', 'local', '28001', 'Madrid', 'Madrid', 'España', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `idEmp` int(10) NOT NULL,
  `nombreComercial` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipoSociedad` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `actividad` text COLLATE utf8_spanish_ci,
  `cif` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `paginaWeb` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observaciones` text COLLATE utf8_spanish_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`idEmp`, `nombreComercial`, `tipoSociedad`, `actividad`, `cif`, `email`, `paginaWeb`, `telefono`, `fax`, `observaciones`) VALUES
(1, 'Empresa primera', 'SL', 'Venta de producto principal', 'D70055413', 'empresa.primera@gmail.com', 'www.empresaprimera.com', '912222222', '912222223', ''),
(2, 'Primer empresa desde front', 'SA', 'Fabrican diversión', 'V5115871E', 'diver@diver.com', 'www.desdefront.com', '910000005', '913333333', 'Agrego la observación correspondiente'),
(3, 'Tercera empresa dada de alta', 'Cooperativa', 'Hacen festivales', 'Q0263228I', 'festivales@festivales.com', 'www.hacenfestivales.com', '962222222', '', 'Festivales en la zona norte de España'),
(5, 'Dupuitrago', 'SA', '', 'H88268842', 'info@dupuitrago.com', '', '', '', ''),
(6, 'Qualisoftware', 'SL', 'Desarrollo de software', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `idEst` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `idFac` int(11) NOT NULL,
  `compra` tinyint(1) NOT NULL,
  `ivaTotal` double DEFAULT NULL,
  `descuentoTotal` double DEFAULT NULL,
  `fechaCompra` datetime NOT NULL,
  `fechaEntrega` datetime DEFAULT NULL,
  `idEst` int(11) NOT NULL,
  `direccionEntrega` varchar(300) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observaciones` varchar(300) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idFor` int(11) NOT NULL,
  `creadoPor` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formapago`
--

CREATE TABLE `formapago` (
  `idFor` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paises`
--

CREATE TABLE `paises` (
  `idPais` int(11) NOT NULL,
  `nombreES` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `nombreEN` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `paises`
--

INSERT INTO `paises` (`idPais`, `nombreES`, `nombreEN`) VALUES
(1, 'España', 'Spain'),
(2, 'Alemania', 'Germany'),
(3, 'Andorra', 'Andorra'),
(4, 'Austria', 'Austria'),
(5, 'Bélgica', 'Belgium'),
(6, 'Dinamarca', 'Denmark'),
(7, 'El Vaticano', 'The Vatican'),
(8, 'Francia', 'France'),
(9, 'Grecia', 'Greece'),
(10, 'Italia', 'Italy'),
(11, 'Noruega', 'Norway'),
(12, 'Países Bajos', 'Netherlands'),
(13, 'Portugal', 'Portugal'),
(14, 'Reino Unido', 'United Kingdom'),
(15, 'San Marino', 'San Marino'),
(16, 'Suecia', 'Sweden'),
(17, 'Suiza', 'Switzerland'),
(18, 'Turquía', 'Turkey');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idPro` int(11) NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL,
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
  `idSub` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idPro`, `descripcion`, `unidades`, `precioVenta`, `precioCompra`, `marca`, `modelo`, `serie`, `ubicacion`, `estado`, `partida`, `fechaCompra`, `enviar`, `vendible`, `mesesGarantia`, `peso`, `volumen`, `idSub`) VALUES
(1, 'TV Sanyo 15 pulgadas', 0, 150, 115.35, 'Sanyo', 'HR15', '12345678', 'Pasillo 3', 'ACTIVO', '', '2019-12-22', 1, 1, 12, 20, 0.016, 1),
(2, 'TV Sony 28 pulgadas', 0, 600, 421, 'Sony', 'RJ25', '', '', 'DESCATALOGADO', NULL, NULL, 0, 1, 6, 5, 3.2, 3);

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
  `observaciones` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL,
  `rol` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idRol`, `rol`) VALUES
(1, 'ROL_CLIENTE'),
(2, 'ROL_USUARIO'),
(3, 'ROL_ADMIN'),
(4, 'ROL_ROOT');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subcategoria`
--

CREATE TABLE `subcategoria` (
  `idSub` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `idCat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `subcategoria`
--

INSERT INTO `subcategoria` (`idSub`, `nombre`, `idCat`) VALUES
(1, 'Televisores', 1),
(2, 'USB 512GB', 1),
(3, 'Ordenador portátil Intel i7', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsr` int(11) NOT NULL,
  `usuario` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(256) COLLATE utf8_spanish_ci NOT NULL,
  `habilitado` tinyint(4) NOT NULL,
  `fechaCreacion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsr`, `usuario`, `clave`, `habilitado`, `fechaCreacion`) VALUES
(1, 'pepe', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2019-09-15 16:17:35'),
(3, 'roberto', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 0, '2019-09-15 16:17:09'),
(4, 'damian', 'f895c8e804268edd597848da7466e9a80648379b2258e1a7a59210af2f0c42a52dcf7efce6ab9347', 1, '2019-09-15 16:16:47'),
(44, 'dusheff', 'f49575f4e33033860f878aca63107c4a44f6f5871a09c8e8a99c592c70eb6d6d8aa7413683d63614', 1, '2019-09-15 16:15:00'),
(46, 'maria', '305c55eb1d869e20b3b72f5cfde19a626c54f04a848c24bf9ad7dfd3e506043d38b662301abda2e8', 1, '2019-09-19 12:02:56'),
(55, 'lara', '9b6a2d000fc7a133070e19a91bde28f9011f6017b2d92ee0d08120d68dfe371143bbd67cd590eabd', 1, '2019-09-30 21:15:39'),
(58, 'saul', '6a3121471f38805e88617b51876fbd30a4512687366dfa6e38eff311368b3e55a06c2414268c018a', 1, '2019-09-30 23:31:03'),
(60, 'alejandro', 'b43d75e6f04a5b896a14027c13b2885313eaa4dc02059027fc7efa28270a40bcc86138b6952b4acc', 1, '2019-10-09 22:46:50'),
(61, 'barbara', '9b4d05d41fc360472b1d48f0cb51c68bd782ec92298398d67c5159eda1e8a07bc13e68833dc27f33', 1, '2019-10-09 22:47:35'),
(62, 'juan', 'b2b3cc92be337bf743138ff1a841f9d8ae25e0fb95b6a1d9506191763063baed8002e36385c0cc57', 1, '2019-10-09 22:48:57'),
(63, 'walter', '5b664c1584d3d8ef4df19dac1967f70d4434abec39ffc4967c23a2920a9786e7589b10340f2201ff', 0, '2019-10-09 22:49:55'),
(64, 'daniela', 'bbd3d730524fecbfaf19296038cf3852d6eba7bf6343cc9be988b6b5ea01e189f331212f13ce0b8a', 1, '2019-10-22 00:01:15'),
(65, 'tamara', 'fc5c81966241e732b52b16017cbfd48d3c11b5f9158c16dd3cbc4c7990c755e3181a8e6716523e22', 1, '2019-10-22 23:46:22'),
(66, 'ruben', '7ae032287e49b9e5b68687f97d37e24434a47e19516046bfb9ce5efdd582f70705c0b14ba8d266df', 1, '2019-10-23 22:10:13'),
(67, 'isabel', '98fb8e4e20b766f3ef9e26d88858d811efc68847df974a7be81ba419f8712427be42ac0d39f6400b', 1, '2019-10-23 22:29:54'),
(68, 'edu', '8942e3612eb9b1c1f6c7e42ebde1cbb95167dd33da30a273e99cc4d7974b275aa86bc51b932c48cd', 1, '2019-10-24 23:23:10'),
(69, 'dora', 'bf919802908fca7678ec91658585ca0a47a04cfe122d36d550b4835a25475b1bf0fce7b744aae374', 1, '2019-11-03 17:14:36'),
(74, 'servicio4', 'd97d6dc0f3ccb515184fcd244e26e3c91547c2a4661bbcb7eacafc5ff93f6f26a8166c3126d48494', 1, '2019-12-01 18:55:16'),
(75, 'servicio5', '31a7ac310d6ab1c1d30e4565030ee39f958f393fe461402c9a0899b70ef65dde337031e205537c2c', 1, '2019-12-01 18:57:08'),
(78, 'servicio8', '7a49b2bd8000984ca974d275d95c9365d7d52da9f6b6e4f5bdc95f3f1cbccbb45df26a358885de89', 1, '2019-12-04 22:59:26'),
(79, 'servicio9', '120891246cdde24113ce295bcbbb9260eca9b7bd4d4d69b6b3aa8cc11c6bc8afd3178709f6a742f4', 1, '2019-12-04 23:06:53'),
(81, 'dausheff', 'a9c62f9bcd800150e96b1c2c2874a6863b1365f2cb1a519de75538553109a309e8043aaee0000e72', 1, '2019-12-06 23:49:13'),
(82, 'servicio11', '804097c51146ba177cdc406b06e21aef3f5bfafe91341cd1bb08bcd58517a488293c1f3b25c9fe0f', 1, '2019-12-09 13:42:37');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_empresa`
--

CREATE TABLE `usuario_empresa` (
  `idUsr` int(10) NOT NULL,
  `idEmp` int(10) NOT NULL,
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
(4, 5, '2019-12-02', 'pepe'),
(4, 6, '2019-11-03', 'dora'),
(44, 2, '2019-10-06', 'pepe'),
(46, 1, '2019-10-06', 'pepe'),
(46, 2, '2019-10-06', 'pepe'),
(55, 2, '2019-10-06', 'pepe'),
(58, 2, '2019-10-06', 'pepe'),
(58, 6, '2019-11-03', 'dora'),
(62, 2, '2019-10-09', 'pepe'),
(63, 3, '2019-10-09', 'pepe'),
(69, 6, '2019-11-03', 'dora');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_rol`
--

CREATE TABLE `usuario_rol` (
  `idUsr` int(10) NOT NULL,
  `idRol` int(10) NOT NULL,
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
(58, 2, '2019-10-09', 'pepe'),
(60, 1, '2019-10-09', 'pepe'),
(61, 1, '2019-10-09', 'pepe'),
(62, 2, '2019-10-09', 'pepe'),
(63, 1, '2019-10-09', 'pepe'),
(64, 1, '2019-10-22', 'pepe'),
(65, 1, '2019-10-22', 'OWN USER'),
(65, 2, '2019-12-04', 'pepe'),
(66, 1, '2019-10-23', 'OWN USER'),
(67, 1, '2019-10-23', 'OWN USER'),
(68, 1, '2019-10-24', 'OWN USER'),
(69, 2, '2019-11-03', 'pepe'),
(69, 3, '2019-11-03', 'pepe'),
(74, 1, '2019-12-01', 'maria'),
(75, 1, '2019-12-02', 'pepe'),
(75, 2, '2019-12-01', 'pepe'),
(78, 2, '2019-12-04', 'pepe'),
(79, 1, '2019-12-04', 'OWN USER'),
(81, 1, '2019-12-06', 'pepe'),
(82, 2, '2019-12-09', 'pepe');

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
-- Indices de la tabla `formapago`
--
ALTER TABLE `formapago`
  ADD PRIMARY KEY (`idFor`);

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
  ADD UNIQUE KEY `idPro` (`idPro`),
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
  MODIFY `idCat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  MODIFY `idDatosPers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;
--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `idDir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT de la tabla `direccionempresa`
--
ALTER TABLE `direccionempresa`
  MODIFY `idDirEmp` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmp` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `idEst` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `idFac` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT de la tabla `formapago`
--
ALTER TABLE `formapago`
  MODIFY `idFor` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT de la tabla `paises`
--
ALTER TABLE `paises`
  MODIFY `idPais` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idPro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `subcategoria`
--
ALTER TABLE `subcategoria`
  MODIFY `idSub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;
--
-- Restricciones para tablas volcadas
--

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
