-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-09-2019 a las 23:28:48
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
  `datospersonales_idUsr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `datospersonales`
--

INSERT INTO `datospersonales` (`idDatosPers`, `nombre`, `apellido1`, `apellido2`, `sexo`, `fechaNacimiento`, `nacionalidad`, `dni`, `email`, `telefono`, `observaciones`, `datospersonales_idUsr`) VALUES
(1, 'José', 'Martínez', 'Suárez', 'Masculino', '1967-07-11', 'España', '123456789A', 'pepe@pepe.com', '666555444', 'El usuario principal con el cual hacer pruebas', 1),
(2, 'Juan', 'Álvarez', NULL, 'masculino', '1984-10-24', 'España', '123456789B', 'juan@juan.com', NULL, NULL, 2),
(3, 'Roberto', 'Do Santos', NULL, 'masculino', '1980-07-28', 'Portugal', '123456789C', 'roberto@roberto.com', NULL, NULL, 3),
(4, 'Damián', 'Usheff', NULL, 'masculino', '1976-10-30', 'Argentina', '123456789D', 'damian@damian.com', NULL, NULL, 4),
(5, 'Carlos Alberto', 'Velazquez de la Tarragona', NULL, 'masculino', '1975-08-01', 'España', '123456789E', 'carlos.alberto.velazquez.delatarragona@telefonicaladron.com', NULL, NULL, 5),
(8, 'Prueba 15:15', 'Apellido 15:15', 'Segundo 23:43', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 18),
(9, 'Prueba 12:13', 'Apellido 12:13', 'Segundo 23:43', 'Mujer', NULL, NULL, NULL, NULL, NULL, NULL, 19),
(11, 'DamiÃ¡n', 'Usheff', 'Vellianitis', 'Hombre', NULL, NULL, NULL, NULL, NULL, NULL, 21),
(12, 'DamiÃ¡n', 'Apellido 18:04', 'Segundo 23:43', 'Hombre', NULL, NULL, NULL, NULL, NULL, NULL, 22),
(13, 'DamiÃ¡n', 'DamiÃ¡n', 'Segundo 23:43', 'Hombre', NULL, NULL, NULL, NULL, NULL, NULL, 23),
(14, 'Damián', 'Damián', 'Segundo 23:43', 'Hombre', NULL, NULL, NULL, NULL, NULL, NULL, 24),
(15, 'Prueba 18:21', 'Apellido 18:21', 'ergsgsdgf', 'Mujer', NULL, NULL, NULL, NULL, NULL, NULL, 25),
(16, 'Prueba 18:04', 'Núñez', 'Agüero', 'Hombre', NULL, NULL, NULL, NULL, NULL, NULL, 26),
(17, 'Prueba 23:43', 'Apellido 23:43', 'Segundo 23:43', 'Mujer', '2000-05-18', NULL, NULL, NULL, NULL, NULL, 27),
(18, 'Con', 'Validate', 'Núñezagüero', 'Hombre', '1986-06-29', NULL, NULL, NULL, NULL, NULL, 28),
(19, 'Prueba 18:21', 'Apellido 23:43', 'ergsgsdgf', 'Hombre', '1996-11-01', NULL, '', '', NULL, NULL, 29);

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
  `fechaCreacion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsr`, `usuario`, `clave`, `habilitado`, `fechaCreacion`) VALUES
(1, 'pepe', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-12 00:00:00'),
(2, 'juan', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-12 00:00:00'),
(3, 'roberto', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-13 18:35:04'),
(4, 'Damian', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-13 18:35:35'),
(5, 'Carlos', '44e31399fa4840954f1f7b8e14b96052e3622019c8ab4856a62f3d4926cbd645f8206d3884a3d173', 1, '2018-08-15 20:32:19'),
(18, 'prueba1515', '6fcd9959abd9fb8a8839cbf862241965041e55ca00a009c2583a0d8f2ac7a3c90019fea43903b152', 1, '2019-09-07 00:00:23'),
(19, 'prueba1213', 'f06e01d438c43efc0638047c5676ca50c21f2d16b87fcdd784350e4f1c80a65d8730b9a90930dea9', 1, '2019-09-07 12:14:12'),
(20, 'prueba1821', '517e2f3e475030e4775a5dff16d2956d23a93dd289adc598bf3f10603715cb0f3b12bf6520a50b1b', 1, '2019-09-07 13:16:34'),
(21, 'dusheff', '460b2585f2b9a8ffee004d3a58cddd718ee67103f96c16970990f42a58b1676646ba194d19c136c3', 1, '2019-09-07 16:00:08'),
(22, 'dusheff', '3e0d5c1e5abe1bc2eeae944f495cc8222d277acc9a4cd9659ab8d1ca94bedd7db0c870cc5352ede5', 1, '2019-09-07 16:17:42'),
(23, 'prueba1515', '5e2bcbf54357e98aeb360e75de42b82a6f410d9f34fc36bf884b6aebce0989cb643f989ac35f88b1', 1, '2019-09-07 16:52:38'),
(24, 'prueba1515', '531231bded7ccadc5992d5fe120eff0f20544246663b8f6572f7dea3111bbc4a8be7d5abd89e12cd', 1, '2019-09-07 16:57:44'),
(25, 'prueba1821', 'bd780eb9b528522dd001928201bc42b052544543400befa6498c94bf586319b9c1b97c2483af21f4', 1, '2019-09-07 17:00:51'),
(26, 'prueba1804', '8325676431201ccb538a21320665f268d052b38e5f7138dbbe19a2cfff0b7ba48a4c2e2058a99916', 1, '2019-09-07 17:01:51'),
(27, 'prueba2343', '5e53f92f99c245178ac256498b93b35f7be307a3b6edd0f6c156eb78441dd185126c469aab4dfb65', 1, '2019-09-07 17:11:10'),
(28, 'conValidate', '24aff816937b97005d3189cb202b7046f16b5ad70c771ad8e642a1e164f2c00127b1c6aa66934932', 1, '2019-09-07 17:31:35'),
(29, 'prueba1515', '00d195ef49ffe885befea39d8fd914af5ac44b1fd7f990cc268653b2e3cc864d784f876a6c3a612b', 1, '2019-09-07 23:43:57');

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
-- Estructura de tabla para la tabla `usuario_rol`
--

CREATE TABLE `usuario_rol` (
  `idUsr` int(10) NOT NULL,
  `idRol` int(10) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `creadoPor` varchar(10) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario_rol`
--

INSERT INTO `usuario_rol` (`idUsr`, `idRol`, `fechaCreacion`, `creadoPor`) VALUES
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
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

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
  MODIFY `idAd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  MODIFY `idDatosPers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `idDir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
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
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
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
  ADD CONSTRAINT `direccionEmpresa_ibfk_1` FOREIGN KEY (`idEmp`) REFERENCES `empresa` (`idEmp`) ON DELETE NO ACTION ON UPDATE CASCADE;

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
