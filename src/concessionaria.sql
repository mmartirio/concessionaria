CREATE DATABASE  IF NOT EXISTS `api_concessionaria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `api_concessionaria`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: api_concessionaria
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `tb_cars`
--

DROP TABLE IF EXISTS `tb_cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cars` (
  `id_vehicle` binary(16) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `doors` int DEFAULT NULL,
  `fuel` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_vehicle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cars`
--

LOCK TABLES `tb_cars` WRITE;
/*!40000 ALTER TABLE `tb_cars` DISABLE KEYS */;
INSERT INTO `tb_cars` VALUES (_binary ']Y\»\ÏLƒë±ø}\ _ñ',NULL,5,'gasolina','Renault','Duster',86550.00,'2022'),(_binary 'ô8\Ì\ÔCíµ∞¿~(∑\\',NULL,4,'gasolina','Fiat','Uno',27000.00,'2018'),(_binary '\Ô2€éNMÆΩ)8`•∑\Î',NULL,4,'Gasolina','Toyota','Corolla',100000.00,'2022');
/*!40000 ALTER TABLE `tb_cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_motorcycles`
--

DROP TABLE IF EXISTS `tb_motorcycles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_motorcycles` (
  `id_vehicle` binary(16) NOT NULL,
  `cylinder` decimal(38,2) DEFAULT NULL,
  `fuel` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_vehicle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_motorcycles`
--

LOCK TABLES `tb_motorcycles` WRITE;
/*!40000 ALTER TABLE `tb_motorcycles` DISABLE KEYS */;
INSERT INTO `tb_motorcycles` VALUES (_binary '\‡\·‹ßF\\êM\r∞\‰\⁄\€\·',160.00,'flex','Honda','Start',11800.00,'2020'),(_binary 'e)ß\÷OΩCCØæ≥h\Ó\‚',300.00,'gasolina','Honda','CB 300',16000.00,'2018'),(_binary '~ú±\ÎGæLâõpµˇ\r\≈)r',160.00,'gasolina','Honda','Start',11800.00,'2020'),(_binary '\…w]Å+\ H6Ç^∏Û¸R¡_',250.00,'gasolina','Yamaha','Factor',15790.00,'2020'),(_binary '\œ\Ó^§ìNÉ\‹ûÛ\”',150.00,'Gasolina','Honda','CG Fan',9500.00,'2013');
/*!40000 ALTER TABLE `tb_motorcycles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_model`
--

DROP TABLE IF EXISTS `vehicle_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_model` (
  `id_vehicle` binary(16) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `vehicle_type` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_vehicle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_model`
--

LOCK TABLES `vehicle_model` WRITE;
/*!40000 ALTER TABLE `vehicle_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle_model` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-27 15:19:04
