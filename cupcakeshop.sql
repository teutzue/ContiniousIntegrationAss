CREATE DATABASE  IF NOT EXISTS `cupcakeshop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cupcakeshop`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: cupcakeshop
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `cupcake`
--

DROP TABLE IF EXISTS `cupcake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cupcake` (
  `idCupcake` int(11) NOT NULL AUTO_INCREMENT,
  `cupcakeName` varchar(45) DEFAULT NULL,
  `idTopping` int(11) DEFAULT NULL,
  `idBottom` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCupcake`),
  KEY `fk_topping_idx` (`idTopping`),
  KEY `fk_bottom_idx` (`idBottom`),
  CONSTRAINT `fk_idBottom` FOREIGN KEY (`idBottom`) REFERENCES `cupcakebottom` (`idBottom`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idTopping` FOREIGN KEY (`idTopping`) REFERENCES `cupcaketopping` (`idTopping`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcake`
--

LOCK TABLES `cupcake` WRITE;
/*!40000 ALTER TABLE `cupcake` DISABLE KEYS */;
INSERT INTO `cupcake` VALUES (1,'ChoccoNut',3,1),(2,'Chocolate with Chocolate',1,1),(3,'Blueberry with Vanilla',2,2),(4,'Chocolate with Vanilla',2,1),(5,'Crispy with Pistacio',4,4),(6,'Raspberry with Chocolate',1,3),(7,'Chocolate with Vanilla',2,1),(8,'Raspberry with Nutmeg',3,3),(9,'ChoccoNut',3,1);
/*!40000 ALTER TABLE `cupcake` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupcakebottom`
--

DROP TABLE IF EXISTS `cupcakebottom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cupcakebottom` (
  `idBottom` int(11) NOT NULL AUTO_INCREMENT,
  `cupcakeBottomName` varchar(45) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`idBottom`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcakebottom`
--

LOCK TABLES `cupcakebottom` WRITE;
/*!40000 ALTER TABLE `cupcakebottom` DISABLE KEYS */;
INSERT INTO `cupcakebottom` VALUES (1,'Chocolate',5),(2,'Blueberry',5),(3,'Raspberry',5),(4,'Crispy',6),(5,'Strawberry',6),(6,'Rum/Raisin',7),(7,'Orange',8),(8,'Lemon',8),(9,'Blue cheese',9);
/*!40000 ALTER TABLE `cupcakebottom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupcaketopping`
--

DROP TABLE IF EXISTS `cupcaketopping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cupcaketopping` (
  `idTopping` int(11) NOT NULL AUTO_INCREMENT,
  `cupcakeToppingName` varchar(45) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`idTopping`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcaketopping`
--

LOCK TABLES `cupcaketopping` WRITE;
/*!40000 ALTER TABLE `cupcaketopping` DISABLE KEYS */;
INSERT INTO `cupcaketopping` VALUES (1,'Chocolate',5),(2,'Vanilla',5),(3,'Nutmeg',5),(4,'Pistacio',6),(5,'Almond',7);
/*!40000 ALTER TABLE `cupcaketopping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `idInvoice` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paid` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idInvoice`),
  KEY `fk_idUser_idx` (`idUser`),
  CONSTRAINT `fk_idUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,1,'2017-02-27 12:18:18',0),(2,1,'2017-02-27 12:25:48',0),(3,1,'2017-02-27 12:44:49',0),(4,1,'2017-03-01 19:15:53',0);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoicedetails`
--

DROP TABLE IF EXISTS `invoicedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoicedetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idInvoice` int(11) NOT NULL,
  `idCupcake` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoicedetails_IdCupCake_idx` (`idCupcake`),
  KEY `fk_invoicedetails_IdInvoice` (`idInvoice`),
  CONSTRAINT `fk_invoicedetails_IdCupcake` FOREIGN KEY (`idCupcake`) REFERENCES `cupcake` (`idCupcake`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoicedetails_IdInvoice` FOREIGN KEY (`idInvoice`) REFERENCES `invoice` (`idInvoice`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoicedetails`
--

LOCK TABLES `invoicedetails` WRITE;
/*!40000 ALTER TABLE `invoicedetails` DISABLE KEYS */;
INSERT INTO `invoicedetails` VALUES (1,1,1,3),(2,2,2,20),(3,2,3,30),(4,3,4,20),(5,3,5,100),(6,4,6,20),(7,4,7,20),(8,4,8,100);
/*!40000 ALTER TABLE `invoicedetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Christian','Olsen',1000),(2,'Bjørn','Kristiansen',1001);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-26  4:21:06
