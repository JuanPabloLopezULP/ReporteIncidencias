CREATE DATABASE  IF NOT EXISTS `sistema_reporte_incidencias` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sistema_reporte_incidencias`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: sistema_reporte_incidencias
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sis_rep_inc_cliente`
--

DROP TABLE IF EXISTS `sis_rep_inc_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sis_rep_inc_cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cuit` bigint NOT NULL,
  `razonSocial` varchar(150) DEFAULT NULL,
  `iddatoscontacto` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5nhgv863g91lj3qfxuw49okcc` (`cuit`),
  KEY `FK_b90yq8eaywbcvf55yxs16707f` (`iddatoscontacto`),
  CONSTRAINT `FK_b90yq8eaywbcvf55yxs16707f` FOREIGN KEY (`iddatoscontacto`) REFERENCES `sis_rep_inc_datos_contacto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sis_rep_inc_cliente_servicio`
--

DROP TABLE IF EXISTS `sis_rep_inc_cliente_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sis_rep_inc_cliente_servicio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `idcliente` bigint DEFAULT NULL,
  `idservicio` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rxq9qkpnjy64fvvnendbpnl11` (`idcliente`),
  KEY `FK_k88jqiogwr9h21q8ooxlumtrc` (`idservicio`),
  CONSTRAINT `FK_k88jqiogwr9h21q8ooxlumtrc` FOREIGN KEY (`idservicio`) REFERENCES `sis_rep_inc_servicio` (`id`),
  CONSTRAINT `FK_rxq9qkpnjy64fvvnendbpnl11` FOREIGN KEY (`idcliente`) REFERENCES `sis_rep_inc_cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sis_rep_inc_datos_contacto`
--

DROP TABLE IF EXISTS `sis_rep_inc_datos_contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sis_rep_inc_datos_contacto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `celular` bigint NOT NULL,
  `email` varchar(75) DEFAULT NULL,
  `telefono` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sis_rep_inc_especialidad`
--

DROP TABLE IF EXISTS `sis_rep_inc_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sis_rep_inc_especialidad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(175) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sis_rep_inc_operador_mesa_ayuda`
--

DROP TABLE IF EXISTS `sis_rep_inc_operador_mesa_ayuda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sis_rep_inc_operador_mesa_ayuda` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `legajo` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `iddatoscontacto` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_omu6yqbpx4jdatxs6owvhfeav` (`legajo`),
  KEY `FK_bfykpebd47oyqia34t5o0wi18` (`iddatoscontacto`),
  CONSTRAINT `FK_bfykpebd47oyqia34t5o0wi18` FOREIGN KEY (`iddatoscontacto`) REFERENCES `sis_rep_inc_datos_contacto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sis_rep_inc_reporte_incidencia`
--

DROP TABLE IF EXISTS `sis_rep_inc_reporte_incidencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sis_rep_inc_reporte_incidencia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigoReporte` varchar(255) NOT NULL,
  `descripcionProblema` varchar(255) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fechaAlta` date NOT NULL,
  `fechaPosibleResolucion` date DEFAULT NULL,
  `observacionesTecnico` varchar(255) DEFAULT NULL,
  `tiempoEstimadoResolucion` int NOT NULL,
  `tipoProblema` varchar(255) NOT NULL,
  `idcliente` bigint NOT NULL,
  `idespecialidad` bigint NOT NULL,
  `idoperador` bigint DEFAULT NULL,
  `idservicio` bigint DEFAULT NULL,
  `idtecnico` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bm01ijn3hol0c48h2wtuqhncf` (`codigoReporte`),
  KEY `FK_lqs1d2h0ctbysfychcg6huc1h` (`idcliente`),
  KEY `FK_xq0qdh8kp48xnxdklajixtrd` (`idespecialidad`),
  KEY `FK_2v7ghp8j5bf34ibr42gkpkxk4` (`idoperador`),
  KEY `FK_emf1fh14n9rpqr4cbtwor1pnb` (`idservicio`),
  KEY `FK_43phtdtmgjicmasgmqia2apt9` (`idtecnico`),
  CONSTRAINT `FK_2v7ghp8j5bf34ibr42gkpkxk4` FOREIGN KEY (`idoperador`) REFERENCES `sis_rep_inc_operador_mesa_ayuda` (`id`),
  CONSTRAINT `FK_43phtdtmgjicmasgmqia2apt9` FOREIGN KEY (`idtecnico`) REFERENCES `sis_rep_inc_tecnico` (`id`),
  CONSTRAINT `FK_emf1fh14n9rpqr4cbtwor1pnb` FOREIGN KEY (`idservicio`) REFERENCES `sis_rep_inc_servicio` (`id`),
  CONSTRAINT `FK_lqs1d2h0ctbysfychcg6huc1h` FOREIGN KEY (`idcliente`) REFERENCES `sis_rep_inc_cliente` (`id`),
  CONSTRAINT `FK_xq0qdh8kp48xnxdklajixtrd` FOREIGN KEY (`idespecialidad`) REFERENCES `sis_rep_inc_especialidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sis_rep_inc_servicio`
--

DROP TABLE IF EXISTS `sis_rep_inc_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sis_rep_inc_servicio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sis_rep_inc_tecnico`
--

DROP TABLE IF EXISTS `sis_rep_inc_tecnico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sis_rep_inc_tecnico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `legajo` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `iddatoscontacto` bigint DEFAULT NULL,
  `metodoContacto` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9arv5ufda08axv1jn1tndbf7q` (`legajo`),
  KEY `FK_tj0rhvtbmyjveo36f7rfvuodu` (`iddatoscontacto`),
  CONSTRAINT `FK_tj0rhvtbmyjveo36f7rfvuodu` FOREIGN KEY (`iddatoscontacto`) REFERENCES `sis_rep_inc_datos_contacto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sis_rep_inc_tecnico_especialidad`
--

DROP TABLE IF EXISTS `sis_rep_inc_tecnico_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sis_rep_inc_tecnico_especialidad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `idespecialidad` bigint DEFAULT NULL,
  `idtecnico` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hnbj5d6uw613ghlvhml8slpk4` (`idespecialidad`),
  KEY `FK_r9v15ymchxshlflra1fqukc0d` (`idtecnico`),
  CONSTRAINT `FK_hnbj5d6uw613ghlvhml8slpk4` FOREIGN KEY (`idespecialidad`) REFERENCES `sis_rep_inc_especialidad` (`id`),
  CONSTRAINT `FK_r9v15ymchxshlflra1fqukc0d` FOREIGN KEY (`idtecnico`) REFERENCES `sis_rep_inc_tecnico` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-01  8:46:18
