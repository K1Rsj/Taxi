-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: taxi
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `id_cars` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(45) NOT NULL,
  `number` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL DEFAULT 'FREE',
  `driver` varchar(45) NOT NULL,
  `car_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id_cars`,`car_type_id`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `fk_cars_car_type1_idx` (`car_type_id`),
  CONSTRAINT `fk_cars_car_type1` FOREIGN KEY (`car_type_id`) REFERENCES `car_type` (`id_car_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'Peugeot 308','АА5421НМ','BUSY','Василий Грабар',1),(2,'Volkswagen Polo','АА8712НМ','BUSY','Иван Коваль',1),(3,'Daewoo Lanos','АА1251НМ','FREE','Денис Гончар',1),(4,'Kia Optima','АА9843НМ','FREE','Максим Мороз',2),(5,'Ford Mondeo','АА7765НМ','FREE','Михаил Клименко',2),(6,'Chevrolet Malibu','АА1113НМ','BUSY','Василий Михайленко',2),(7,'Ford Transit','АА0098НМ','BUSY','Олег Кобзаренко',3),(8,'Mercedes-Benz Sprinter','АА6501НМ','BUSY','Сергей Шкоропад',3),(9,'Toyota Coaster','АА7800НМ','BUSY','Богдан Сова',3),(10,'Audi A4','АА8878НМ','FREE','Евгений Яремчук',4),(11,'Mazda 6','АА1161НМ','BUSY','Иван Скороход',4),(12,'Lexus	IS','АА3322НМ','FREE','Олег Кобец',4),(13,'Subaru Impreza','АА4444АА','BUSY','Максим Короб',1),(15,'Daewoo Lanos','АА5641АА','FREE','Василий Щорс',1),(16,'Peugeot 208','АА7686АА','FREE','Desmond Cena',1),(17,'Range Rover Evoque','AA5643AA','FREE','Nasir Jones',4);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-20 17:16:56
