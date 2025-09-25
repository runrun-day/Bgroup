CREATE DATABASE  IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shop`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_detail_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `num` int NOT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,1,1,2),(2,1,3,1),(3,2,2,1),(4,2,4,3),(5,3,5,2),(6,4,6,1),(7,4,1,1),(8,5,3,4),(9,5,5,1),(10,6,2,2),(11,6,6,3),(12,7,1,1),(13,8,3,2),(14,8,5,2),(15,9,4,1),(16,9,2,3),(17,10,6,2),(18,10,5,1),(19,11,1,2),(20,11,3,3),(21,12,4,2),(22,12,6,1),(23,13,2,1),(24,13,5,1),(25,14,1,3),(26,14,3,1),(27,15,6,1),(28,15,5,2);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_date` datetime NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'2025-05-05 10:30:00'),(2,2,'2025-05-12 14:20:00'),(3,3,'2025-05-15 09:10:00'),(4,4,'2025-05-22 18:00:00'),(5,5,'2025-06-01 11:15:00'),(6,6,'2025-06-05 16:45:00'),(7,1,'2025-06-12 10:00:00'),(8,2,'2025-06-25 15:30:00'),(9,3,'2025-07-03 17:50:00'),(10,4,'2025-07-15 08:25:00'),(11,5,'2025-07-22 12:40:00'),(12,6,'2025-08-02 13:55:00'),(13,1,'2025-08-10 09:30:00'),(14,3,'2025-08-20 16:20:00'),(15,4,'2025-08-28 19:10:00');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `price` int NOT NULL,
  `image_rename` varchar(255) DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'黒ボールペン 油性 5本セット',300,'dfbe6676-cbd5-4389-81be-e8f99359a560.png',NULL),(2,'赤ボールペン 油性 5本セット',300,'ec602b7f-5bfb-444f-8eb8-1fb34ed0a565.png',NULL),(3,'コピー用紙 A4 1冊(500枚)',500,'24e2bf2c-0688-4a7d-803d-61d0830f93c0.png',NULL),(4,'コピー用紙 B5 1冊(500枚)',450,'f885c803-a135-44bc-bb98-920789fe96c0.png',NULL),(5,'修正液ペン 油性・水性両用1本(7mL)',200,'c9b1c5c1-6f91-427d-83a8-ea1a83c86e89.png',NULL),(6,'スティックのり 1本(5g)',100,'51ee468e-c1a9-4dee-a217-8e82c0b7ddfe.png',NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regular_service`
--

DROP TABLE IF EXISTS `regular_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regular_service` (
  `regular_service_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `span` int NOT NULL,
  `start_date` datetime NOT NULL,
  PRIMARY KEY (`regular_service_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `regular_service_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `regular_service_chk_1` CHECK ((`span` between 1 and 3))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regular_service`
--

LOCK TABLES `regular_service` WRITE;
/*!40000 ALTER TABLE `regular_service` DISABLE KEYS */;
INSERT INTO `regular_service` VALUES (1,1,1,'2025-05-05 10:30:00'),(2,2,2,'2025-05-12 14:20:00'),(3,3,3,'2025-05-15 09:10:00'),(4,4,1,'2025-06-01 11:15:00'),(5,5,2,'2025-06-25 15:30:00'),(6,6,3,'2025-07-03 17:50:00');
/*!40000 ALTER TABLE `regular_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regular_service_detail`
--

DROP TABLE IF EXISTS `regular_service_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regular_service_detail` (
  `regular_service_detail_id` int NOT NULL AUTO_INCREMENT,
  `regular_service_id` int NOT NULL,
  `product_id` int NOT NULL,
  `num` int NOT NULL,
  PRIMARY KEY (`regular_service_detail_id`),
  KEY `regular_service_id` (`regular_service_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `regular_service_detail_ibfk_1` FOREIGN KEY (`regular_service_id`) REFERENCES `regular_service` (`regular_service_id`) ON DELETE CASCADE,
  CONSTRAINT `regular_service_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regular_service_detail`
--

LOCK TABLES `regular_service_detail` WRITE;
/*!40000 ALTER TABLE `regular_service_detail` DISABLE KEYS */;
INSERT INTO `regular_service_detail` VALUES (1,1,1,2),(2,1,3,1),(3,2,2,1),(4,3,5,2),(5,3,4,1),(6,4,6,1),(7,5,3,3),(8,5,5,1),(9,6,2,2),(10,6,6,3);
/*!40000 ALTER TABLE `regular_service_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `postcode` varchar(7) NOT NULL,
  `address` varchar(255) NOT NULL,
  `tel` varchar(11) NOT NULL,
  `password` varchar(1000) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'佐藤 太郎','taro@example.com','1234567','東京都千代田区1-1-1','09012345678','0M0F4MmdThnZoURrhzDkMFZKAzWZd504gGFtgqzrSsM='),(2,'鈴木 花子','hanako@example.com','2345678','東京都新宿区2-2-2','08023456789','ZL/DLe4dGJfDgkRf9YNyW0REhMbbO8AG/gv8sWctxHo='),(3,'高橋 健','ken@example.com','3456789','東京都品川区3-3-3','07034567890','FnNoeMxtE8vGel+KC6OqlVnJJWBGUIfmTLlnuILNcag=='),(4,'田中 美咲','misaki@example.com','4567890','東京都渋谷区4-4-4','09045678901','+9CtNiqvnCMTN0XcIxxwFK4XhyZ+X9eAIr1HH3DszoU='),(5,'渡辺 一郎','ichiro@example.com','5678901','東京都港区5-5-5','08056789012','SYTgo5pAdQKAYghAGK92rMs4hEQuFVRm99aUciivxv0='),(6,'山本 愛','ai@example.com','6789012','東京都豊島区6-6-6','07067890123','L756XsbxamCTlMurcVh56d6bwGA3Fiqf9/T1xnbZvPI=');
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

-- Dump completed on 2025-09-22 10:19:29
