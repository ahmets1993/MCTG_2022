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
-- Table structure for table `gamelobby`
--

DROP TABLE IF EXISTS `gamelobby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gamelobby` (
  `GameLobbyID` int DEFAULT NULL,
  `UserName` varchar(45) DEFAULT NULL,
  `UserScore` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamelobby`
--

LOCK TABLES `gamelobby` WRITE;
/*!40000 ALTER TABLE `gamelobby` DISABLE KEYS */;
INSERT INTO `gamelobby` VALUES (123822,'user1','188'),(12312,'user1','188'),(12312,'user2','312'),(12312,'user3','22'),(12312,'user5','100'),(133333,'user1','196'),(133333,'user2','344'),(133333,'user3','38'),(133444,'user1','196'),(133444,'user2','344'),(133444,'user3','38'),(133444,'user4','120'),(133444,'user4','120'),(133444,'user4','120'),(133344142,'user1','196'),(133344142,'user2','344'),(133344142,'user3','38'),(133344142,'user4','120'),(144142,'user1','196'),(144142,'user2','344'),(144142,'user2','344'),(144142,'user2','344'),(144142,'user2','344'),(1441423232,'user3','38'),(1441423232,'user3','38'),(1441423232,'user3','38'),(1441423232,'user3','38'),(1441423232,'user3','38'),(1441423232,'user3','38'),(1441423232,'user3','38'),(1441423232,'user3','38'),(1441423232,'user3','38'),(5555,'user1','196'),(5555,'user1','196'),(5555,'user1','196'),(5555,'user2','344'),(5555,'user3','38'),(5555,'user4','120');
/*!40000 ALTER TABLE `gamelobby` ENABLE KEYS */;
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
