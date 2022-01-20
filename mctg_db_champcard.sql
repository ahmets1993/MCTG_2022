-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mctg_db
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `champcard`
--

DROP TABLE IF EXISTS `champcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `champcard` (
  `ChampCardID` int NOT NULL,
  `ChampName` varchar(45) NOT NULL,
  `ChampHP` int DEFAULT NULL,
  `CampDamage` int NOT NULL,
  `ChampDefence` int NOT NULL,
  `PackageID` int NOT NULL,
  `PackageName` varchar(45) DEFAULT NULL,
  `CardPosition` int DEFAULT NULL,
  PRIMARY KEY (`ChampCardID`),
  UNIQUE KEY `ChampCardID_UNIQUE` (`ChampCardID`),
  UNIQUE KEY `ChampName_UNIQUE` (`ChampName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `champcard`
--

LOCK TABLES `champcard` WRITE;
/*!40000 ALTER TABLE `champcard` DISABLE KEYS */;
INSERT INTO `champcard` VALUES (1,'Atrox',1200,120,60,1,'Light-Bringers',1),(2,'Ahri',900,145,40,5,'Light-Bringers',1),(3,'Akali',1000,158,30,18,'Light-Bringers',1),(4,'Akshan',1100,186,30,15,'Light-Bringers',1),(5,'Alistar',1600,70,90,8,'Light-Bringers',1),(6,'Amumu',1450,80,80,7,'Light-Bringers',1),(7,'Anivia',900,190,25,11,'Light-Bringers',1),(8,'Annie',950,150,50,13,'Light-Bringers',1),(9,'Aphelios',880,160,45,15,'Light-Bringers',2),(10,'Ashe',890,165,50,17,'Light-Bringers',1),(11,'Bard',1100,120,50,3,'Light-Bringers',1),(12,'Blitzcrank',1200,100,80,2,'Light-Bringers',1),(13,'Brand',880,160,30,4,'Light-Bringers',1),(14,'Braum',1570,60,1207,9,'Light-Bringers',1),(15,'Caitlyn',960,150,45,10,'Light-Bringers',1),(16,'Camille',1200,130,55,12,'Light-Bringers',1),(17,'Cassiopeia',1000,140,30,14,'Light-Bringers',1),(18,'Corki',950,135,48,16,'Light-Bringers',1),(19,'Darius',1300,140,50,19,'Light-Bringers',1),(20,'Diana',1100,150,45,20,'Light-Bringers',1),(21,'Ekko',1050,130,60,5,'Light-Bringers',2),(22,'Elise',1060,100,80,6,'Light-Bringers',1),(23,'Evelynn',1000,180,30,9,'Light-Bringers',2),(24,'Fiddlesticks',900,120,40,17,'Light-Bringers',2),(25,'Fiora',1100,140,50,1,'Light-Bringers',2),(26,'Fizz',1100,135,55,2,'Light-Bringers',2),(27,'Galio',1600,100,80,18,'Light-Bringers',2),(28,'Gangplank',1300,110,70,4,'Light-Bringers',2),(29,'Garen',1450,120,70,3,'Light-Bringers',2),(30,'Hecarim',1400,130,55,11,'Light-Bringers',2),(31,'Heimerdinger',1320,135,50,13,'Light-Bringers',2),(32,'Illaoi',1400,140,40,14,'Light-Bringers',2),(33,'Irelia',1300,100,70,12,'Light-Bringers',2),(34,'Ivern',1280,80,60,8,'Light-Bringers',2),(35,'Janna',1000,80,70,7,'Light-Bringers',2),(36,'Jarvan IV',1120,120,60,6,'Light-Bringers',2),(37,'Jax',1350,140,40,19,'Light-Bringers',2),(38,'Jayce',1280,120,45,10,'Light-Bringers',2),(39,'Jhin',925,150,30,16,'Light-Bringers',2),(40,'Jinx',900,160,35,20,'Light-Bringers',2),(41,'Kalista',900,140,50,1,'Light-Bringers',3),(42,'Karma',1100,100,50,20,'Light-Bringers',3),(43,'Karthus',1000,100,30,19,'Light-Bringers',3),(44,'Kassadin',880,130,50,2,'Light-Bringers',3),(45,'Katarina',845,150,40,7,'Light-Bringers',3),(46,'Kayle',1100,120,50,8,'Light-Bringers',3),(47,'Kayn',1200,130,40,16,'Light-Bringers',3),(48,'Kennen',1000,140,40,11,'Light-Bringers',3),(49,'Kindred',1100,120,50,17,'Light-Bringers',3),(50,'Kled',1080,135,35,4,'Light-Bringers',3),(51,'LeBlanc',920,150,20,3,'Light-Bringers',3),(52,'Lee Sin',1350,100,40,5,'Light-Bringers',3),(53,'Leona',1600,125,35,6,'Light-Bringers',3),(54,'Lillia',1240,120,40,18,'Light-Bringers',3),(55,'Lissandra',1230,140,20,12,'Light-Bringers',3),(56,'Lucian',950,150,20,13,'Light-Bringers',3),(57,'Lulu',1100,125,25,9,'Light-Bringers',3),(58,'Lux',1000,150,10,14,'Light-Bringers',3),(59,'Malphite',1580,130,20,10,'Light-Bringers',3),(60,'Malzahar',1020,145,15,15,'Light-Bringers',3),(61,'Maokai',1500,120,30,8,'Light-Bringers',4),(62,'Master Yi',1000,150,20,14,'Light-Bringers',4),(63,'Miss Fortune',950,130,40,11,'Light-Bringers',4),(64,'Mordekaiser',1350,125,35,1,'Light-Bringers',4),(65,'Morgana',900,145,15,7,'Light-Bringers',4),(66,'Nami',900,120,30,20,'Light-Bringers',4),(67,'Nasus',1320,110,50,16,'Light-Bringers',4),(68,'Nautilus',1450,145,15,6,'Light-Bringers',4),(69,'Neeko',1300,120,35,2,'Light-Bringers',4),(70,'Nidalee',1100,115,40,5,'Light-Bringers',4),(71,'Nocturne',1000,150,20,19,'Light-Bringers',4),(72,'Orianna',980,135,35,3,'Light-Bringers',4),(73,'Olaf',1220,130,25,4,'Light-Bringers',4),(74,'Ornn',1230,140,30,18,'Light-Bringers',4),(75,'Pantheon',1300,120,40,17,'Light-Bringers',4),(76,'Poppy',1380,125,35,15,'Light-Bringers',4),(77,'Pyke',1200,135,25,13,'Light-Bringers',4),(78,'Qiyana',1000,140,40,9,'Light-Bringers',4),(79,'Quinn',920,145,35,12,'Light-Bringers',4),(80,'Rakan',1280,150,20,10,'Light-Bringers',4),(81,'Rammus',1450,110,50,1,'Light-Bringers',5),(82,'Rell',1620,100,50,10,'Light-Bringers',5),(83,'Renekton',1400,150,20,20,'Light-Bringers',5),(84,'Rengar',1200,145,25,3,'Light-Bringers',5),(85,'Riven',1320,115,35,5,'Light-Bringers',5),(86,'Rumble',1000,110,40,7,'Light-Bringers',5),(87,'Senna',900,100,50,17,'Light-Bringers',5),(88,'Seraphine',1000,140,15,13,'Light-Bringers',5),(89,'Shaco',900,150,10,2,'Light-Bringers',5),(90,'Shen',1500,120,30,8,'Light-Bringers',5),(91,'Yasuo',1250,125,35,9,'Light-Bringers',5),(92,'Yuumi',1000,140,10,19,'Light-Bringers',5),(93,'Xin Zhao',1100,145,25,11,'Light-Bringers',5),(94,'Xayah',900,135,30,18,'Light-Bringers',5),(95,'Xerath',950,115,45,15,'Light-Bringers',5),(96,'Zac',1410,125,50,16,'Light-Bringers',5),(97,'Zed',1000,135,25,14,'Light-Bringers',5),(98,'Ziggs',1000,150,10,12,'Light-Bringers',5),(99,'Zoe',950,130,20,6,'Light-Bringers',5),(100,'Zyra',940,110,50,4,'Light-Bringers',5);
/*!40000 ALTER TABLE `champcard` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-20 18:23:49
