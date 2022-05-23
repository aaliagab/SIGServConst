-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2022 a las 03:04:49
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `servicomultiples`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acesso`
--

CREATE TABLE `acesso` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `acesso`
--

INSERT INTO `acesso` (`id`, `nome`) VALUES
(1, 'admin'),
(2, 'gerente'),
(3, 'vendedor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bairro`
--

CREATE TABLE `bairro` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `id_municipio` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `bairro`
--

INSERT INTO `bairro` (`id`, `nome`, `id_municipio`) VALUES
(1, 'Cantinflas', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bilhete`
--

CREATE TABLE `bilhete` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `data_criacao` date NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `valor` double(10,2) NOT NULL,
  `id_iva` int(11) DEFAULT NULL,
  `quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `bilhete`
--

INSERT INTO `bilhete` (`id`, `nome`, `data_criacao`, `id_tipo`, `valor`, `id_iva`, `quantidade`) VALUES
(4, 'BIE - LUANDA', '2022-05-22', 1, 5000.00, 1, 38),
(5, 'BIE-LUANDA', '2022-05-22', 2, 3000.00, 1, 58);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargo`
--

CREATE TABLE `cargo` (
  `id` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cargo`
--

INSERT INTO `cargo` (`id`, `nome`) VALUES
(1, 'Chefe de venda'),
(2, 'Vendedor'),
(3, 'Gerente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `num` varchar(50) NOT NULL,
  `empresa` tinyint(1) NOT NULL,
  `niif` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `num`, `empresa`, `niif`) VALUES
(9, 'CL00000009', 1, ''),
(10, 'CL00000010', 0, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `endereco`
--

CREATE TABLE `endereco` (
  `id` int(11) NOT NULL,
  `id_bairro` int(11) NOT NULL,
  `rua` varchar(60) NOT NULL,
  `num_casa` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `endereco`
--

INSERT INTO `endereco` (`id`, `id_bairro`, `rua`, `num_casa`) VALUES
(2, 1, 'Fang', '12'),
(3, 1, 'Host', '23'),
(6, 1, '23', ''),
(7, 1, 'Central', '34'),
(8, 1, 'Principal', '123'),
(9, 1, 'Velchord', '123'),
(10, 1, 'Fangui', ''),
(11, 1, 'Soler', '12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_solicitude`
--

CREATE TABLE `estado_solicitude` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estado_solicitude`
--

INSERT INTO `estado_solicitude` (`id`, `nome`) VALUES
(1, 'Novo'),
(2, 'Em processo'),
(3, 'Realizado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funcionario`
--

CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL,
  `id_cargo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `funcionario`
--

INSERT INTO `funcionario` (`id`, `id_cargo`) VALUES
(7, 1),
(2, 2),
(8, 2),
(1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `id` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`id`, `nome`) VALUES
(1, 'Masculino'),
(2, 'Feminino');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `iva`
--

CREATE TABLE `iva` (
  `id` int(11) NOT NULL,
  `porcetagem` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `iva`
--

INSERT INTO `iva` (`id`, `porcetagem`) VALUES
(1, 14.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_pagamento`
--

CREATE TABLE `metodo_pagamento` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `metodo_pagamento`
--

INSERT INTO `metodo_pagamento` (`id`, `nome`) VALUES
(1, 'Cash'),
(2, 'Tranferência'),
(3, 'Cartão');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipio`
--

CREATE TABLE `municipio` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) COLLATE latin1_bin NOT NULL,
  `id_provincia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Volcado de datos para la tabla `municipio`
--

INSERT INTO `municipio` (`id`, `nome`, `id_provincia`) VALUES
(1, 'Cuito', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pessoa`
--

CREATE TABLE `pessoa` (
  `id` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `sobrenome` varchar(60) DEFAULT NULL,
  `id_genero` int(11) NOT NULL,
  `data_ingreso` date NOT NULL,
  `email` varchar(120) DEFAULT NULL,
  `telefone` varchar(60) NOT NULL,
  `id_endereco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pessoa`
--

INSERT INTO `pessoa` (`id`, `nome`, `sobrenome`, `id_genero`, `data_ingreso`, `email`, `telefone`, `id_endereco`) VALUES
(1, 'Artur', 'Ochaenda', 1, '2022-05-05', 'a@a.aa', '(+244)927-834-842', 2),
(2, 'Fernanda', 'Alba', 2, '2022-05-05', 'f@f.ss', '(+244)965-236-236', 3),
(5, 'Josua', 'Bernolio', 1, '2022-05-05', 'jo@jo.oo', '(+244)923-737-673', 6),
(6, 'Construccôes & Filhos, S.A.', NULL, 1, '2022-05-05', 'as@ss.aa', '(+244)923-938-389', 7),
(7, 'Paulo', 'Somenba', 1, '2022-05-12', 'a@aaa.aaa', '(+244)983-475-784', 8),
(8, 'Josue', 'Jacobo', 1, '2022-05-22', 'a@a.aa', '(+244)923-523-611', 9),
(9, 'Loja Marcos & Filhos', 'SA', 1, '2022-05-22', 'a@a.aa', '(+244)937-232-737', 10),
(10, 'Bernaldinha', 'Malanga', 2, '2022-05-22', 'b@b.bb', '(+244)922-272-722', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

CREATE TABLE `provincia` (
  `id` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `provincia`
--

INSERT INTO `provincia` (`id`, `nome`) VALUES
(1, 'Luanda'),
(2, 'Bié');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitude`
--

CREATE TABLE `solicitude` (
  `id` int(11) NOT NULL,
  `num` varchar(45) DEFAULT NULL,
  `data` date NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_estado_solicitude` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `solicitude`
--

INSERT INTO `solicitude` (`id`, `num`, `data`, `id_cliente`, `id_estado_solicitude`) VALUES
(11, 'PED-BILH00000011', '2022-05-22', 9, 3),
(12, 'PED-BILH00000012', '2022-05-22', 10, 3),
(13, 'PED-BILH00000013', '2022-05-22', 10, 3),
(14, 'PED-BILH00000014', '2022-05-22', 9, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitude_bilhete`
--

CREATE TABLE `solicitude_bilhete` (
  `id` int(11) NOT NULL,
  `id_solicitude` int(11) NOT NULL,
  `id_servico` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `solicitude_bilhete`
--

INSERT INTO `solicitude_bilhete` (`id`, `id_solicitude`, `id_servico`, `quantidade`) VALUES
(18, 11, 5, 1),
(19, 11, 4, 2),
(20, 12, 5, 4),
(21, 13, 5, 2),
(22, 14, 5, 2),
(23, 14, 4, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo`
--

CREATE TABLE `tipo` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo`
--

INSERT INTO `tipo` (`id`, `nome`) VALUES
(1, 'Primeira Classe'),
(2, 'Classe economica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `id_acesso` int(11) NOT NULL,
  `id_funcionario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `senha`, `id_acesso`, `id_funcionario`) VALUES
(1, 'admin', '2c978d261a17e63ffc5b87f58b2e237fbe88a94ef1113cd76caa9453d4123c7310cde5fb2048dd5c88ca36cb18c520060b7b58027dd8177499860b32d7f54705', 1, NULL),
(2, 'gerente', 'a095afed29d50ee7980cbdb886fbd83bdba1cd7c003abec2b0f9f50a4ff285642e46710a1d6b3ad4ced5b6513ba508dbfaac5c82b11a58fcc0618b5efdff7838', 2, 1),
(3, 'vendedor', '44ee616eeac3e3852a1b8a9d0a9a0c580e18a533b3ce04aaa4d8e69729cd56eebe06d640b2e4660c59d4832ba754b43c95c99b0a63bbd3da9594c1daaf823164', 3, 2),
(4, 'pauloso', 'd5723702f30458e60c74372c0b5505436e9e7e7715f68d54695807943227dd7885c299389e79511e3addaf7fe5a0ba8aa15ca32533f008a6016ce871cf7a25a1', 3, 7),
(5, 'jacobo', 'b363225b18199687c8fed8f8c80c4c6f0dc1e6c7b2a6e478222a0b4734951ce5d9163c7069a1b8d0327b469a9cc1d22f1d8e6fc05dd36e77ecf44d6ed3eb89d8', 3, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venda`
--

CREATE TABLE `venda` (
  `id` int(11) NOT NULL,
  `id_solicitude` int(11) NOT NULL,
  `id_funcionario` int(11) NOT NULL,
  `id_metodo_pagamento` int(11) NOT NULL,
  `total` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Volcado de datos para la tabla `venda`
--

INSERT INTO `venda` (`id`, `id_solicitude`, `id_funcionario`, `id_metodo_pagamento`, `total`) VALUES
(6, 12, 1, 2, 12000),
(7, 11, 1, 1, 13000),
(8, 12, 1, 1, 12000),
(9, 11, 1, 1, 13000),
(10, 13, 1, 1, 6000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `acesso`
--
ALTER TABLE `acesso`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `bairro`
--
ALTER TABLE `bairro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_municipio` (`id_municipio`);

--
-- Indices de la tabla `bilhete`
--
ALTER TABLE `bilhete`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_iva` (`id_iva`),
  ADD KEY `id_tipo` (`id_tipo`);

--
-- Indices de la tabla `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_bairro` (`id_bairro`);

--
-- Indices de la tabla `estado_solicitude`
--
ALTER TABLE `estado_solicitude`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cargo` (`id_cargo`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `iva`
--
ALTER TABLE `iva`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `metodo_pagamento`
--
ALTER TABLE `metodo_pagamento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `municipio`
--
ALTER TABLE `municipio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_provincia` (`id_provincia`);

--
-- Indices de la tabla `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_endereco` (`id_endereco`),
  ADD KEY `id_genero` (`id_genero`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `solicitude`
--
ALTER TABLE `solicitude`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_estado_solicitude` (`id_estado_solicitude`);

--
-- Indices de la tabla `solicitude_bilhete`
--
ALTER TABLE `solicitude_bilhete`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_servico` (`id_servico`),
  ADD KEY `id_solicitude` (`id_solicitude`);

--
-- Indices de la tabla `tipo`
--
ALTER TABLE `tipo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_acesso` (`id_acesso`),
  ADD KEY `id_funcionario` (`id_funcionario`);

--
-- Indices de la tabla `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_funcionario` (`id_funcionario`),
  ADD KEY `id_metodo_pagamento` (`id_metodo_pagamento`),
  ADD KEY `id_solicitude` (`id_solicitude`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `acesso`
--
ALTER TABLE `acesso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `bairro`
--
ALTER TABLE `bairro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `bilhete`
--
ALTER TABLE `bilhete`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `cargo`
--
ALTER TABLE `cargo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `estado_solicitude`
--
ALTER TABLE `estado_solicitude`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `iva`
--
ALTER TABLE `iva`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `metodo_pagamento`
--
ALTER TABLE `metodo_pagamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `municipio`
--
ALTER TABLE `municipio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `provincia`
--
ALTER TABLE `provincia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `solicitude`
--
ALTER TABLE `solicitude`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `solicitude_bilhete`
--
ALTER TABLE `solicitude_bilhete`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `tipo`
--
ALTER TABLE `tipo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `venda`
--
ALTER TABLE `venda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bairro`
--
ALTER TABLE `bairro`
  ADD CONSTRAINT `bairro_ibfk_1` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `bilhete`
--
ALTER TABLE `bilhete`
  ADD CONSTRAINT `bilhete_ibfk_1` FOREIGN KEY (`id_iva`) REFERENCES `iva` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bilhete_ibfk_2` FOREIGN KEY (`id_tipo`) REFERENCES `tipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `endereco`
--
ALTER TABLE `endereco`
  ADD CONSTRAINT `endereco_ibfk_1` FOREIGN KEY (`id_bairro`) REFERENCES `bairro` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `funcionario_ibfk_2` FOREIGN KEY (`id_cargo`) REFERENCES `cargo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `municipio`
--
ALTER TABLE `municipio`
  ADD CONSTRAINT `municipio_ibfk_1` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pessoa`
--
ALTER TABLE `pessoa`
  ADD CONSTRAINT `pessoa_ibfk_1` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pessoa_ibfk_2` FOREIGN KEY (`id_genero`) REFERENCES `genero` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `solicitude`
--
ALTER TABLE `solicitude`
  ADD CONSTRAINT `solicitude_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `solicitude_ibfk_2` FOREIGN KEY (`id_estado_solicitude`) REFERENCES `estado_solicitude` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `solicitude_bilhete`
--
ALTER TABLE `solicitude_bilhete`
  ADD CONSTRAINT `solicitude_bilhete_ibfk_1` FOREIGN KEY (`id_servico`) REFERENCES `bilhete` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `solicitude_bilhete_ibfk_2` FOREIGN KEY (`id_solicitude`) REFERENCES `solicitude` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_acesso`) REFERENCES `acesso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`id_metodo_pagamento`) REFERENCES `metodo_pagamento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`id_solicitude`) REFERENCES `solicitude` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
