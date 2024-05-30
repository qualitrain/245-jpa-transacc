-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: localhost    Database: bdejmtransacc
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `art_articulo`
--

DROP TABLE IF EXISTS `art_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_articulo` (
  `ART_CVE` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `ART_COSTO_PROV_1` float DEFAULT NULL,
  `ART_DESCRIPCION` varchar(40) COLLATE latin1_spanish_ci NOT NULL,
  `ART_EXISTENCIA` int(11) NOT NULL,
  `ART_PRECIO_LISTA` float DEFAULT NULL,
  PRIMARY KEY (`ART_CVE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `art_articulo`
--

LOCK TABLES `art_articulo` WRITE;
/*!40000 ALTER TABLE `art_articulo` DISABLE KEYS */;
INSERT INTO `art_articulo` VALUES ('CCS-MC-001',133.23,'Camisa caballero Sport Talla M',70,457.9),('CCS-MC-002',133.23,'Camisa caballero Sport Talla CH',11,457.9),('CCS-ML-233',160.1,'Camisa caballero Sport Talla M',12,632.25),('CHX-WW-002',679.25,'Chaleco lana merino Talla G',14,1999.99),('CHX-WW-003',679.25,'Chaleco lana merino Talla M',22,1999.99),('PCS-00134',345,'Pantalón caballero Sport Talla 34',14,985.5),('PCS-00136',347.1,'Pantalón caballero Sport Talla 36',12,985.5),('PCS-JN-00130',255.89,'Pantalón caballero Mezclilla Talla 30',11,877.45);
/*!40000 ALTER TABLE `art_articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cte_cliente`
--

DROP TABLE IF EXISTS `cte_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cte_cliente` (
  `CTE_NUM` bigint(20) NOT NULL AUTO_INCREMENT,
  `CTE_DIRECCION` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CTE_NOMBRE` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CTE_SALDO` double DEFAULT NULL,
  PRIMARY KEY (`CTE_NUM`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cte_cliente`
--

LOCK TABLES `cte_cliente` WRITE;
/*!40000 ALTER TABLE `cte_cliente` DISABLE KEYS */;
INSERT INTO `cte_cliente` VALUES (1,'Av. Insurgentes Sur 1555,  despacho 910','Jorge Antunes Barranco',15509.739990234375),(2,'Av. Independencia 1500','Ismael Zambrano Cantú',49356.70965576172),(3,'Calle del Sapo 276','Mario Mena Basurto',5207.649993896484),(4,'Simón Bolívar 4500-A','Miriam Pacheco Reyes',48396.28076171875),(5,'Av. San Martín 2390','Jimena Torres Cancino',16321.500030517578);
/*!40000 ALTER TABLE `cte_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dev_detalle_vta`
--

DROP TABLE IF EXISTS `dev_detalle_vta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dev_detalle_vta` (
  `DEV_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DEV_CANTIDAD` int(11) NOT NULL,
  `DEV_NUM_DETALLE` int(11) DEFAULT NULL,
  `DEV_PRECIO_UNITARIO` float NOT NULL,
  `DEV_ART_CVE` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DEV_NUM_VTA` int(11) NOT NULL,
  PRIMARY KEY (`DEV_ID`),
  UNIQUE KEY `UKi8xkkterm0vfspd71g3q5gie6` (`DEV_NUM_VTA`,`DEV_NUM_DETALLE`),
  KEY `FK87mwpssrrmtn77r5y22c1qud9` (`DEV_ART_CVE`),
  CONSTRAINT `FK87mwpssrrmtn77r5y22c1qud9` FOREIGN KEY (`DEV_ART_CVE`) REFERENCES `art_articulo` (`ART_CVE`),
  CONSTRAINT `FKf9kfrq0pgqxxmn0xqg1x60kwj` FOREIGN KEY (`DEV_NUM_VTA`) REFERENCES `vta_venta` (`VTA_NUM_VENTA`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dev_detalle_vta`
--

LOCK TABLES `dev_detalle_vta` WRITE;
/*!40000 ALTER TABLE `dev_detalle_vta` DISABLE KEYS */;
INSERT INTO `dev_detalle_vta` VALUES (2,5,1,457.9,'CCS-MC-001',2),(3,1,1,457.9,'CCS-MC-001',3),(4,1,2,1999.99,'CHX-WW-002',3),(5,1,3,985.5,'PCS-00134',3),(6,1,1,457.9,'CCS-MC-001',4),(7,1,2,1999.99,'CHX-WW-002',4),(8,1,3,985.5,'PCS-00134',4),(9,2,1,1999.99,'CHX-WW-002',5),(10,1,2,1999.99,'CHX-WW-003',5),(11,2,3,877.45,'PCS-JN-00130',5),(12,2,1,1999.99,'CHX-WW-002',6),(13,1,2,1999.99,'CHX-WW-003',6),(14,2,3,877.45,'PCS-JN-00130',6),(15,2,1,985.5,'PCS-00136',7),(16,1,2,877.45,'PCS-JN-00130',7),(17,2,3,457.9,'CCS-MC-001',7),(18,1,1,457.9,'CCS-MC-002',8),(19,2,2,1999.99,'CHX-WW-002',8),(20,1,3,1999.99,'CHX-WW-003',8),(21,4,1,457.9,'CCS-MC-002',9),(22,6,2,457.9,'CCS-MC-001',9),(24,1,1,457.9,'CCS-MC-002',10),(26,1,2,1999.99,'CHX-WW-002',10),(28,1,3,457.9,'CCS-MC-002',10),(29,1,1,985.5,'PCS-00136',12),(30,5,2,457.9,'CCS-MC-001',12),(34,5,1,1999.99,'CHX-WW-002',14),(36,5,2,1999.99,'CHX-WW-003',14),(37,1,1,457.9,'CCS-MC-002',16),(38,5,1,1999.99,'CHX-WW-002',17),(39,5,2,1999.99,'CHX-WW-003',17),(40,5,1,1999.99,'CHX-WW-002',18),(41,5,2,1999.99,'CHX-WW-003',18),(42,1,1,457.9,'CCS-MC-002',19),(43,1,1,457.9,'CCS-MC-002',20),(44,1,1,877.45,'PCS-JN-00130',21),(45,1,2,985.5,'PCS-00134',21),(46,1,3,457.9,'CCS-MC-002',21),(47,1,1,877.45,'PCS-JN-00130',22),(48,1,2,985.5,'PCS-00134',22),(49,1,3,985.5,'PCS-00136',22),(50,1,1,457.9,'CCS-MC-002',23),(51,1,2,457.9,'CCS-MC-001',23),(52,1,3,632.25,'CCS-ML-233',23),(53,1,1,457.9,'CCS-MC-002',24),(54,1,2,457.9,'CCS-MC-001',24),(55,1,3,632.25,'CCS-ML-233',24),(56,1,1,457.9,'CCS-MC-002',25),(57,1,2,457.9,'CCS-MC-001',25),(58,1,3,632.25,'CCS-ML-233',25),(59,1,4,1999.99,'CHX-WW-003',25),(60,1,1,457.9,'CCS-MC-002',26),(61,1,2,457.9,'CCS-MC-001',26),(62,1,3,632.25,'CCS-ML-233',26),(63,1,4,1999.99,'CHX-WW-003',26),(64,1,1,457.9,'CCS-MC-002',27),(65,1,2,457.9,'CCS-MC-001',27),(66,1,3,632.25,'CCS-ML-233',27),(67,1,4,1999.99,'CHX-WW-002',27),(68,1,5,1999.99,'CHX-WW-003',27),(69,1,6,877.45,'PCS-JN-00130',27),(70,1,7,985.5,'PCS-00134',27),(73,1,8,985.5,'PCS-00136',27),(80,1,1,457.9,'CCS-MC-002',29),(81,1,2,632.25,'CCS-ML-233',29),(82,1,3,877.45,'PCS-JN-00130',29),(83,1,4,985.5,'PCS-00134',29),(84,1,5,985.5,'PCS-00136',29),(85,1,1,457.9,'CCS-MC-002',30),(86,1,2,632.25,'CCS-ML-233',30),(87,1,3,877.45,'PCS-JN-00130',30),(88,1,4,985.5,'PCS-00134',30),(89,1,5,985.5,'PCS-00136',30),(90,1,1,457.9,'CCS-MC-002',31),(91,1,2,632.25,'CCS-ML-233',31),(92,1,3,877.45,'PCS-JN-00130',31),(93,1,4,985.5,'PCS-00134',31),(94,1,5,985.5,'PCS-00136',31),(95,1,1,457.9,'CCS-MC-002',32),(96,1,2,632.25,'CCS-ML-233',32),(97,1,3,877.45,'PCS-JN-00130',32),(98,1,4,985.5,'PCS-00134',32),(99,1,5,985.5,'PCS-00136',32),(100,1,1,457.9,'CCS-MC-002',33),(101,1,2,457.9,'CCS-MC-001',33),(102,1,3,632.25,'CCS-ML-233',33),(103,1,4,1999.99,'CHX-WW-002',33),(104,1,5,1999.99,'CHX-WW-003',33),(105,1,6,877.45,'PCS-JN-00130',33),(106,1,7,985.5,'PCS-00134',33),(107,1,8,985.5,'PCS-00136',33),(108,1,1,457.9,'CCS-MC-002',34),(109,1,2,457.9,'CCS-MC-001',34),(110,1,3,632.25,'CCS-ML-233',34),(111,1,4,1999.99,'CHX-WW-002',34),(112,1,5,1999.99,'CHX-WW-003',34),(113,1,6,877.45,'PCS-JN-00130',34),(114,1,7,985.5,'PCS-00134',34),(115,1,8,985.5,'PCS-00136',34),(116,1,1,985.5,'PCS-00136',35),(117,1,1,457.9,'CCS-MC-002',36),(118,1,1,457.9,'CCS-MC-002',37),(119,1,1,457.9,'CCS-MC-002',38),(120,1,2,457.9,'CCS-MC-002',38),(121,1,3,457.9,'CCS-MC-002',38),(122,1,4,457.9,'CCS-MC-002',38),(123,1,1,457.9,'CCS-MC-002',39),(124,1,2,457.9,'CCS-MC-002',39),(125,1,3,457.9,'CCS-MC-002',39),(126,1,4,457.9,'CCS-MC-002',39),(127,1,1,457.9,'CCS-MC-002',40),(128,1,2,457.9,'CCS-MC-002',40),(129,1,3,457.9,'CCS-MC-002',40),(130,1,4,457.9,'CCS-MC-002',40);
/*!40000 ALTER TABLE `dev_detalle_vta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ven_vendedor`
--

DROP TABLE IF EXISTS `ven_vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ven_vendedor` (
  `VEN_NUM` bigint(20) NOT NULL AUTO_INCREMENT,
  `VEN_NVTAS` bigint(20) DEFAULT NULL,
  `VEN_NOMBRE` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`VEN_NUM`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ven_vendedor`
--

LOCK TABLES `ven_vendedor` WRITE;
/*!40000 ALTER TABLE `ven_vendedor` DISABLE KEYS */;
INSERT INTO `ven_vendedor` VALUES (1,22,'Jorge Juan Del Valle Kamakura'),(2,4,'Miguel De Anda Martínez'),(3,6,'María del Pilar García García');
/*!40000 ALTER TABLE `ven_vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vta_venta`
--

DROP TABLE IF EXISTS `vta_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vta_venta` (
  `VTA_NUM_VENTA` int(11) NOT NULL AUTO_INCREMENT,
  `VTA_FECHA` date NOT NULL,
  `VTA_CTE_NUM` bigint(20) DEFAULT NULL,
  `VTA_VEN_NUM` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`VTA_NUM_VENTA`),
  KEY `FK9rsvi8y553wx3oaovhjdn2pos` (`VTA_CTE_NUM`),
  KEY `FK3glgj0p6i5lhv3rphg4eo2abn` (`VTA_VEN_NUM`),
  CONSTRAINT `FK3glgj0p6i5lhv3rphg4eo2abn` FOREIGN KEY (`VTA_VEN_NUM`) REFERENCES `ven_vendedor` (`VEN_NUM`),
  CONSTRAINT `FK9rsvi8y553wx3oaovhjdn2pos` FOREIGN KEY (`VTA_CTE_NUM`) REFERENCES `cte_cliente` (`CTE_NUM`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vta_venta`
--

LOCK TABLES `vta_venta` WRITE;
/*!40000 ALTER TABLE `vta_venta` DISABLE KEYS */;
INSERT INTO `vta_venta` VALUES (2,'2018-08-07',2,1),(3,'2018-08-07',2,1),(4,'2018-08-07',2,3),(5,'2018-08-07',1,1),(6,'2018-08-08',1,1),(7,'2018-08-09',3,3),(8,'2018-08-09',2,1),(9,'2018-08-09',2,1),(10,'2018-08-10',2,1),(12,'2018-08-10',5,3),(14,'2018-08-10',4,2),(16,'2018-08-10',2,1),(17,'2018-08-10',4,2),(18,'2018-08-10',4,2),(19,'2018-08-10',2,1),(20,'2018-08-10',2,1),(21,'2018-08-10',5,3),(22,'2018-08-10',5,3),(23,'2018-08-10',2,1),(24,'2018-08-10',2,1),(25,'2018-08-10',2,1),(26,'2018-08-10',2,1),(27,'2018-08-10',4,3),(29,'2018-08-10',5,1),(30,'2018-08-10',5,1),(31,'2018-08-10',2,1),(32,'2018-08-10',2,1),(33,'2018-08-10',2,1),(34,'2018-08-10',2,1),(35,'2018-08-10',3,2),(36,'2018-08-10',3,2),(37,'2018-08-10',2,1),(38,'2018-08-10',2,1),(39,'2018-08-10',2,1),(40,'2018-08-10',2,1);
/*!40000 ALTER TABLE `vta_venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-10 20:49:20
