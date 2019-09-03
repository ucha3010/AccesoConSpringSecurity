-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-09-2019 a las 00:00:59
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
(13, 'María José', 'Representante legal', '2018-03-04 22:37:25'),
(21, 'Damián', 'Dueño', '2018-08-08 19:54:12'),
(22, 'Daniel', 'Relaciones Públicas', '2018-08-08 20:00:09'),
(23, 'Alberto Federico', 'Director Técnico', '2018-08-21 18:01:23'),
(25, 'Claudio', 'Químico', '2018-08-21 18:13:02'),
(26, 'Pedro', 'Jefe de ingeniería', '2019-02-18 22:08:25'),
(28, 'María Victoria', 'Representante legal', '2019-02-18 22:08:25'),
(30, 'Andrés', 'Mensajero', '2019-02-21 22:21:59'),
(31, 'María Eugenia', 'Representante legal', '2019-02-21 22:23:28'),
(33, 'Pancho', 'Aguatero', '2019-02-24 18:16:49'),
(34, 'Fabián', 'Jardinero', '2019-02-24 18:18:27');

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
  `usuario_idUsr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `datospersonales`
--

INSERT INTO `datospersonales` (`idDatosPers`, `nombre`, `apellido1`, `apellido2`, `sexo`, `fechaNacimiento`, `nacionalidad`, `dni`, `email`, `telefono`, `observaciones`, `usuario_idUsr`) VALUES
(1, 'José', 'Martínez', 'Suárez', 'Masculino', '1967-07-11', 'España', '123456789A', 'pepe@pepe.com', '666555444', 'El usuario principal con el cual hacer pruebas', 1),
(2, 'Juan', 'Álvarez', NULL, 'masculino', '1984-10-24', 'España', '123456789B', 'juan@juan.com', NULL, NULL, 2),
(3, 'Roberto', 'Do Santos', NULL, 'masculino', '1980-07-28', 'Portugal', '123456789C', 'roberto@roberto.com', NULL, NULL, 3),
(4, 'Damián', 'Usheff', NULL, 'masculino', '1976-10-30', 'Argentina', '123456789D', 'damian@damian.com', NULL, NULL, 4),
(5, 'Carlos Alberto', 'Velazquez de la Tarragona', NULL, 'masculino', '1975-08-01', 'España', '123456789E', 'carlos.alberto.velazquez.delatarragona@telefonicaladron.com', NULL, NULL, 5);

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
(14, 'Pasaje', 'Silvano', 'sin número', NULL, '49251', 'Córdoba', 'El barrial', 'España', 2),
(15, 'Calle', 'Martín', '23', NULL, '28050', 'Madrid', 'Sanchinaroo', 'España', 3);

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
(1, 'Empresa primera', 'SL', 'Venta de producto principal', 'B12345678', 'empresa.primera@gmail.com', 'www.empresaprimera.com', '912222222', '912222223', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `idRoles` int(11) NOT NULL,
  `rol` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idRoles`, `rol`) VALUES
(1, 'ROL_ADMIN'),
(2, 'ROL_USUARIO'),
(3, 'ROL_CLIENTE'),
(4, 'ROL_ROOT');

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
  `datospersonales_idDatosPers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsr`, `usuario`, `clave`, `habilitado`, `fechaCreacion`, `datospersonales_idDatosPers`) VALUES
(1, 'pepe', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-12 00:00:00', 1),
(2, 'juan', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-12 00:00:00', 2),
(3, 'roberto', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-13 18:35:04', 3),
(4, 'Damian', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-13 18:35:35', 4),
(5, 'Carlos', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-15 20:32:19', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_empresa`
--

CREATE TABLE `usuario_empresa` (
  `idUsr` int(10) NOT NULL,
  `idEmp` int(10) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `creadoPor` varchar(10) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario_empresa`
--

INSERT INTO `usuario_empresa` (`idUsr`, `idEmp`, `fechaCreacion`, `creadoPor`) VALUES
(1, 1, '2019-08-31', 'DAMIAN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_roles`
--

CREATE TABLE `usuario_roles` (
  `idUsr` int(10) NOT NULL,
  `idRoles` int(10) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `creadoPor` varchar(10) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario_roles`
--

INSERT INTO `usuario_roles` (`idUsr`, `idRoles`, `fechaCreacion`, `creadoPor`) VALUES
(1, 1, '2019-08-16', 'DAMIAN'),
(1, 2, '2019-08-16', 'DAMIAN'),
(1, 4, '2019-08-27', 'DAMIAN'),
(2, 2, '2019-08-16', 'DAMIAN'),
(3, 3, '2019-08-27', 'DAMIAN'),
(4, 3, '2019-08-27', 'DAMIAN'),
(5, 3, '2019-08-27', 'DAMIAN');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idAd`);

--
-- Indices de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  ADD PRIMARY KEY (`idDatosPers`),
  ADD UNIQUE KEY `usuario_idUsr` (`usuario_idUsr`);

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
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idRoles`);

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
-- Indices de la tabla `usuario_roles`
--
ALTER TABLE `usuario_roles`
  ADD PRIMARY KEY (`idUsr`,`idRoles`),
  ADD KEY `FK_idRoles` (`idRoles`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admin`
--
ALTER TABLE `admin`
  MODIFY `idAd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  MODIFY `idDatosPers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `idDir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT de la tabla `direccionempresa`
--
ALTER TABLE `direccionempresa`
  MODIFY `idDirEmp` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmp` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `idRoles` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  ADD CONSTRAINT `fk_datospersonales_usuario` FOREIGN KEY (`usuario_idUsr`) REFERENCES `usuario` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`idDatosPers`) REFERENCES `datospersonales` (`idDatosPers`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `direccionempresa`
--
ALTER TABLE `direccionempresa`
  ADD CONSTRAINT `direccionEmpresa_ibfk_1` FOREIGN KEY (`idEmp`) REFERENCES `empresa` (`idEmp`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario_empresa`
--
ALTER TABLE `usuario_empresa`
  ADD CONSTRAINT `FK_idEmp` FOREIGN KEY (`idEmp`) REFERENCES `empresa` (`idEmp`),
  ADD CONSTRAINT `FK_idUsrEmp` FOREIGN KEY (`idUsr`) REFERENCES `usuario` (`idUsr`);

--
-- Filtros para la tabla `usuario_roles`
--
ALTER TABLE `usuario_roles`
  ADD CONSTRAINT `FK_idRoles` FOREIGN KEY (`idRoles`) REFERENCES `roles` (`idRoles`),
  ADD CONSTRAINT `FK_idUsr` FOREIGN KEY (`idUsr`) REFERENCES `usuario` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
