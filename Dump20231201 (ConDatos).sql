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
-- Dumping data for table `sis_rep_inc_cliente`
--

LOCK TABLES `sis_rep_inc_cliente` WRITE;
/*!40000 ALTER TABLE `sis_rep_inc_cliente` DISABLE KEYS */;
INSERT INTO `sis_rep_inc_cliente` VALUES (1,35623588,'Juan Pablo Lopez',1),(2,38082942,'Antonella Cocco',2),(3,39423094,'Valentin Lopez',5);
/*!40000 ALTER TABLE `sis_rep_inc_cliente` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sis_rep_inc_cliente_servicio`
--

LOCK TABLES `sis_rep_inc_cliente_servicio` WRITE;
/*!40000 ALTER TABLE `sis_rep_inc_cliente_servicio` DISABLE KEYS */;
INSERT INTO `sis_rep_inc_cliente_servicio` VALUES (1,3,1),(2,3,3),(3,1,1),(4,2,3);
/*!40000 ALTER TABLE `sis_rep_inc_cliente_servicio` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sis_rep_inc_datos_contacto`
--

LOCK TABLES `sis_rep_inc_datos_contacto` WRITE;
/*!40000 ALTER TABLE `sis_rep_inc_datos_contacto` DISABLE KEYS */;
INSERT INTO `sis_rep_inc_datos_contacto` VALUES (1,2625406156,'juan@gmail.com',2625406156),(2,12341234,'anto@gmail.com',134123),(3,1234123,'pepet@gmail.com',141234),(4,2561452,'marcemillo@gmail.com',1334123),(5,12341234,'vale@gmail.com',14213412),(6,241623,'josesalas@gmail.com',5477828),(7,3634521,'pedrog@gmail.com',52345),(8,63451234,'ramoncito@gmail.com',15234512),(9,51341341,'elsicario@gmail.com',54353421);
/*!40000 ALTER TABLE `sis_rep_inc_datos_contacto` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sis_rep_inc_especialidad`
--

LOCK TABLES `sis_rep_inc_especialidad` WRITE;
/*!40000 ALTER TABLE `sis_rep_inc_especialidad` DISABLE KEYS */;
INSERT INTO `sis_rep_inc_especialidad` VALUES (1,'Windows'),(2,'Linux'),(3,'MacOs'),(4,'Office'),(5,'Tango'),(6,'Reparacion de PC');
/*!40000 ALTER TABLE `sis_rep_inc_especialidad` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sis_rep_inc_operador_mesa_ayuda`
--

LOCK TABLES `sis_rep_inc_operador_mesa_ayuda` WRITE;
/*!40000 ALTER TABLE `sis_rep_inc_operador_mesa_ayuda` DISABLE KEYS */;
INSERT INTO `sis_rep_inc_operador_mesa_ayuda` VALUES (1,'Salas',2001,'Jose',6);
/*!40000 ALTER TABLE `sis_rep_inc_operador_mesa_ayuda` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sis_rep_inc_reporte_incidencia`
--

LOCK TABLES `sis_rep_inc_reporte_incidencia` WRITE;
/*!40000 ALTER TABLE `sis_rep_inc_reporte_incidencia` DISABLE KEYS */;
INSERT INTO `sis_rep_inc_reporte_incidencia` VALUES (1,'F1410','Pantalla no enciende','RESUELTO','2023-11-30','2023-12-03',NULL,90,'BASICO',3,1,1,1,4),(2,'B1312','Monitor no enciende','RESUELTO','2023-11-30','2023-12-03',NULL,90,'BASICO',1,6,1,1,2),(3,'C1516','PC no se actualiza','RESUELTO','2023-11-30','2023-12-03',NULL,120,'INTERMEDIO',2,1,1,3,4),(4,'B1031','Pc se reinicia','RESUELTO','2023-12-01','2023-12-05',NULL,120,'INTERMEDIO',2,1,1,3,2),(5,'A1748','Problema con drivers de sonido','PENDIENTE','2023-12-01','2023-12-05',NULL,150,'COMPLEJO',1,1,1,1,1),(6,'G1157','Problema con actualizaciones','RESUELTO','2023-12-01','2023-12-06',NULL,120,'INTERMEDIO',2,1,1,3,4),(7,'G1556','Problema con drivers de video','RESUELTO','2023-12-01','2023-12-08',NULL,120,'INTERMEDIO',3,4,NULL,3,4),(8,'D1527','Office se desinstalo','EN PROCESO','2023-12-01','2023-12-06',NULL,120,'INTERMEDIO',2,4,1,3,4);
/*!40000 ALTER TABLE `sis_rep_inc_reporte_incidencia` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sis_rep_inc_servicio`
--

LOCK TABLES `sis_rep_inc_servicio` WRITE;
/*!40000 ALTER TABLE `sis_rep_inc_servicio` DISABLE KEYS */;
INSERT INTO `sis_rep_inc_servicio` VALUES (1,'Hardware'),(2,'Software'),(3,'Sistema Operativo');
/*!40000 ALTER TABLE `sis_rep_inc_servicio` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sis_rep_inc_tecnico`
--

LOCK TABLES `sis_rep_inc_tecnico` WRITE;
/*!40000 ALTER TABLE `sis_rep_inc_tecnico` DISABLE KEYS */;
INSERT INTO `sis_rep_inc_tecnico` VALUES (1,'Perez',1001,'Roberto',3,'WHATSAPP'),(2,'Garcia',1002,'Pedro',7,'WHATSAPP'),(3,'Rodriguez',1003,'Ramon',8,'WHATSAPP'),(4,'Rojas',1004,'Robert',9,'EMAIL');
/*!40000 ALTER TABLE `sis_rep_inc_tecnico` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `sis_rep_inc_tecnico_especialidad`
--

LOCK TABLES `sis_rep_inc_tecnico_especialidad` WRITE;
/*!40000 ALTER TABLE `sis_rep_inc_tecnico_especialidad` DISABLE KEYS */;
INSERT INTO `sis_rep_inc_tecnico_especialidad` VALUES (1,1,1),(2,2,1),(3,1,2),(4,1,3),(5,4,3),(6,1,4),(7,4,4),(8,5,4),(9,6,2);
/*!40000 ALTER TABLE `sis_rep_inc_tecnico_especialidad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-01  8:45:19
