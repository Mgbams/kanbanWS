-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: kanban_ws
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `ville_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj4fguxo4r3dkyx4fqj6mb1451` (`ville_id`),
  CONSTRAINT `FKj4fguxo4r3dkyx4fqj6mb1451` FOREIGN KEY (`ville_id`) REFERENCES `ville` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Client 1',NULL),(2,'Client 2',NULL),(3,'Client 3',NULL),(4,'Client 4',NULL),(5,'Client 5',NULL),(6,'Client 6',NULL),(7,'Client 7',NULL),(8,'Client 8',NULL),(9,'Client 9',NULL),(10,'Client 10',NULL),(11,'Client 11',NULL),(12,'Client 12',NULL),(13,'Client 13',NULL),(14,'Client 14',NULL),(15,'Client 15',NULL),(16,'Client 16',NULL),(17,'Client 17',NULL),(18,'Client 18',NULL),(19,'Client 19',NULL),(20,'Client 20',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colonne`
--

DROP TABLE IF EXISTS `colonne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colonne` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colonne`
--

LOCK TABLES `colonne` WRITE;
/*!40000 ALTER TABLE `colonne` DISABLE KEYS */;
INSERT INTO `colonne` VALUES (1,'A faire'),(2,'En cours'),(3,'A tester'),(4,'Terminé');
/*!40000 ALTER TABLE `colonne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `developpeur`
--

DROP TABLE IF EXISTS `developpeur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `developpeur` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateNaissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developpeur`
--

LOCK TABLES `developpeur` WRITE;
/*!40000 ALTER TABLE `developpeur` DISABLE KEYS */;
INSERT INTO `developpeur` VALUES (1,NULL,NULL,NULL,NULL,'Kierann'),(2,NULL,NULL,NULL,NULL,'Kingsley'),(3,NULL,NULL,NULL,NULL,'Roman'),(4,NULL,NULL,NULL,NULL,'Jon Richard Wissem'),(5,NULL,NULL,NULL,NULL,'Alaric'),(6,NULL,NULL,NULL,NULL,'Oleg'),(7,NULL,NULL,NULL,NULL,'Ahmed'),(8,NULL,NULL,NULL,NULL,'Moulaye'),(9,NULL,NULL,NULL,NULL,'Hugo');
/*!40000 ALTER TABLE `developpeur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projet`
--

DROP TABLE IF EXISTS `projet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(3) DEFAULT NULL,
  `dateHeureCreation` datetime(6) DEFAULT NULL,
  `dateHeureLivraison` datetime(6) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `client_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t6r7lyfljkhpo6evrq2rjgix7` (`code`),
  KEY `FK3gd83ei69y5fxi09t9mk3by6c` (`client_id`),
  CONSTRAINT `FK3gd83ei69y5fxi09t9mk3by6c` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projet`
--

LOCK TABLES `projet` WRITE;
/*!40000 ALTER TABLE `projet` DISABLE KEYS */;
INSERT INTO `projet` VALUES (1,NULL,NULL,NULL,'Projet 1',1);
/*!40000 ALTER TABLE `projet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projet_developpeurs`
--

DROP TABLE IF EXISTS `projet_developpeurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projet_developpeurs` (
  `projets_id` bigint NOT NULL,
  `developpeurs_id` bigint NOT NULL,
  KEY `FKdhayodsu0q7lxyrf41dmv9l6d` (`developpeurs_id`),
  KEY `FKof2you0fy0mjs81sthhhkn8re` (`projets_id`),
  CONSTRAINT `FKdhayodsu0q7lxyrf41dmv9l6d` FOREIGN KEY (`developpeurs_id`) REFERENCES `developpeur` (`id`),
  CONSTRAINT `FKof2you0fy0mjs81sthhhkn8re` FOREIGN KEY (`projets_id`) REFERENCES `projet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projet_developpeurs`
--

LOCK TABLES `projet_developpeurs` WRITE;
/*!40000 ALTER TABLE `projet_developpeurs` DISABLE KEYS */;
/*!40000 ALTER TABLE `projet_developpeurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tache`
--

DROP TABLE IF EXISTS `tache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tache` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateCreation` datetime(6) DEFAULT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `nbHeuresEffectives` int NOT NULL,
  `nbHeuresPrevues` int NOT NULL,
  `colonneActuelle_id` bigint DEFAULT NULL,
  `developpeur_id` bigint DEFAULT NULL,
  `projet_id` bigint DEFAULT NULL,
  `typeTache_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrbp002ruccenk83s3t3dxvdrb` (`colonneActuelle_id`),
  KEY `FKc6mvx6cmimeteqb4oxxnbvpaf` (`developpeur_id`),
  KEY `FKcb0x7p6kcs8h6ijwif67qdj9b` (`projet_id`),
  KEY `FKhsdeq736col15iis3gioklxl7` (`typeTache_id`),
  CONSTRAINT `FKc6mvx6cmimeteqb4oxxnbvpaf` FOREIGN KEY (`developpeur_id`) REFERENCES `developpeur` (`id`),
  CONSTRAINT `FKcb0x7p6kcs8h6ijwif67qdj9b` FOREIGN KEY (`projet_id`) REFERENCES `projet` (`id`),
  CONSTRAINT `FKhsdeq736col15iis3gioklxl7` FOREIGN KEY (`typeTache_id`) REFERENCES `typetache` (`id`),
  CONSTRAINT `FKrbp002ruccenk83s3t3dxvdrb` FOREIGN KEY (`colonneActuelle_id`) REFERENCES `colonne` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tache`
--

LOCK TABLES `tache` WRITE;
/*!40000 ALTER TABLE `tache` DISABLE KEYS */;
INSERT INTO `tache` VALUES (1,'2022-01-11 16:39:01.136179','Inscription',0,2,1,1,NULL,2),(2,'2022-01-11 16:39:01.148662','Connexion/Déconnexion',0,2,1,1,NULL,2),(3,'2022-01-11 16:39:01.153443','Liste des courses',0,2,1,1,NULL,2),(4,'2022-01-11 16:39:01.159350','Ajout d\'une course',0,2,1,1,NULL,2),(5,'2022-01-11 16:39:01.162569','Modification d\'une course',0,2,1,1,NULL,2),(6,'2022-01-11 16:39:01.168402','Téléversement de l\'avatar',0,2,1,1,NULL,2),(7,'2022-01-11 16:39:01.173141','Inscription',0,2,1,2,NULL,2),(8,'2022-01-11 16:39:01.179004','Connexion/Déconnexion',0,2,1,2,NULL,2),(9,'2022-01-11 16:39:01.185986','Liste des courses',0,2,1,2,NULL,2),(10,'2022-01-11 16:39:01.191834','Ajout d\'une course',0,2,1,2,NULL,2),(11,'2022-01-11 16:39:01.198741','Modification d\'une course',0,2,1,2,NULL,2),(12,'2022-01-11 16:39:01.203548','Téléversement de l\'avatar',0,2,1,2,NULL,2),(13,'2022-01-11 16:39:01.207403','Inscription',0,2,1,3,NULL,2),(14,'2022-01-11 16:39:01.212204','Connexion/Déconnexion',0,2,1,3,NULL,2),(15,'2022-01-11 16:39:01.219282','Liste des courses',0,2,1,3,NULL,2),(16,'2022-01-11 16:39:01.222993','Ajout d\'une course',0,2,1,3,NULL,2),(17,'2022-01-11 16:39:01.227774','Modification d\'une course',0,2,1,3,NULL,2),(18,'2022-01-11 16:39:01.231968','Téléversement de l\'avatar',0,2,1,3,NULL,2),(19,'2022-01-11 16:39:01.236725','Inscription',0,2,1,4,NULL,2),(20,'2022-01-11 16:39:01.241449','Connexion/Déconnexion',0,2,1,4,NULL,2),(21,'2022-01-11 16:39:01.245702','Liste des courses',0,2,1,4,NULL,2),(22,'2022-01-11 16:39:01.250506','Ajout d\'une course',0,2,1,4,NULL,2),(23,'2022-01-11 16:39:01.254245','Modification d\'une course',0,2,1,4,NULL,2),(24,'2022-01-11 16:39:01.259034','Téléversement de l\'avatar',0,2,1,4,NULL,2),(25,'2022-01-11 16:39:01.263229','Inscription',0,2,1,5,NULL,2),(26,'2022-01-11 16:39:01.267993','Connexion/Déconnexion',0,2,1,5,NULL,2),(27,'2022-01-11 16:39:01.272910','Liste des courses',0,2,1,5,NULL,2),(28,'2022-01-11 16:39:01.277670','Ajout d\'une course',0,2,1,5,NULL,2),(29,'2022-01-11 16:39:01.281394','Modification d\'une course',0,2,1,5,NULL,2),(30,'2022-01-11 16:39:01.285607','Téléversement de l\'avatar',0,2,1,5,NULL,2),(31,'2022-01-11 16:39:01.290322','Inscription',0,2,1,6,NULL,2),(32,'2022-01-11 16:39:01.295102','Connexion/Déconnexion',0,2,1,6,NULL,2),(33,'2022-01-11 16:39:01.299276','Liste des courses',0,2,1,6,NULL,2),(34,'2022-01-11 16:39:01.304205','Ajout d\'une course',0,2,1,6,NULL,2),(35,'2022-01-11 16:39:01.307968','Modification d\'une course',0,2,1,6,NULL,2),(36,'2022-01-11 16:39:01.312765','Téléversement de l\'avatar',0,2,1,6,NULL,2),(37,'2022-01-11 16:39:01.317689','Inscription',0,2,1,7,NULL,2),(38,'2022-01-11 16:39:01.322452','Connexion/Déconnexion',0,2,1,7,NULL,2),(39,'2022-01-11 16:39:01.327279','Liste des courses',0,2,1,7,NULL,2),(40,'2022-01-11 16:39:01.331540','Ajout d\'une course',0,2,1,7,NULL,2),(41,'2022-01-11 16:39:01.335306','Modification d\'une course',0,2,1,7,NULL,2),(42,'2022-01-11 16:39:01.340021','Téléversement de l\'avatar',0,2,1,7,NULL,2),(43,'2022-01-11 16:39:01.344873','Inscription',0,2,1,8,NULL,2),(44,'2022-01-11 16:39:01.349183','Connexion/Déconnexion',0,2,1,8,NULL,2),(45,'2022-01-11 16:39:01.353809','Liste des courses',0,2,1,8,NULL,2),(46,'2022-01-11 16:39:01.358612','Ajout d\'une course',0,2,1,8,NULL,2),(47,'2022-01-11 16:39:01.361830','Modification d\'une course',0,2,1,8,NULL,2),(48,'2022-01-11 16:39:01.367616','Téléversement de l\'avatar',0,2,1,8,NULL,2),(49,'2022-01-11 16:39:01.371401','Inscription',0,2,1,9,NULL,2),(50,'2022-01-11 16:39:01.375167','Connexion/Déconnexion',0,2,1,9,NULL,2),(51,'2022-01-11 16:39:01.379394','Liste des courses',0,2,1,9,NULL,2),(52,'2022-01-11 16:39:01.384191','Ajout d\'une course',0,2,1,9,NULL,2),(53,'2022-01-11 16:39:01.387873','Modification d\'une course',0,2,1,9,NULL,2),(54,'2022-01-11 16:39:01.391007','Téléversement de l\'avatar',0,2,1,9,NULL,2),(55,NULL,'problem',0,5,1,9,1,4),(56,NULL,'test hello',0,95,1,6,1,1),(57,NULL,'testing',0,42,1,7,1,4);
/*!40000 ALTER TABLE `tache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typetache`
--

DROP TABLE IF EXISTS `typetache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typetache` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `couleur` varchar(255) DEFAULT NULL,
  `description` longtext,
  `nom` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typetache`
--

LOCK TABLES `typetache` WRITE;
/*!40000 ALTER TABLE `typetache` DISABLE KEYS */;
INSERT INTO `typetache` VALUES (1,'ffac2d',NULL,'Bug'),(2,'009ad7',NULL,'Fonctionnalité'),(3,'68af27',NULL,'Amélioration'),(4,'67319a',NULL,'Spike');
/*!40000 ALTER TABLE `typetache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ville`
--

DROP TABLE IF EXISTS `ville`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ville` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codeInsee` varchar(255) DEFAULT NULL,
  `codePostal` varchar(255) DEFAULT NULL,
  `complement` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ville`
--

LOCK TABLES `ville` WRITE;
/*!40000 ALTER TABLE `ville` DISABLE KEYS */;
/*!40000 ALTER TABLE `ville` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-12  3:16:42
