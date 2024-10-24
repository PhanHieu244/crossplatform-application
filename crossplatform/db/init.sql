-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cross_platform_db
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `absence_request`
--

DROP TABLE IF EXISTS `absence_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `absence_request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_detail_id` int NOT NULL,
  `status` varchar(25) NOT NULL,
  `absence_date` datetime NOT NULL,
  `reason` text,
  PRIMARY KEY (`id`),
  KEY `class_detail_id` (`class_detail_id`),
  CONSTRAINT `absence_request_ibfk_1` FOREIGN KEY (`class_detail_id`) REFERENCES `class_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absence_request`
--

LOCK TABLES `absence_request` WRITE;
/*!40000 ALTER TABLE `absence_request` DISABLE KEYS */;
INSERT INTO `absence_request` VALUES (11,12,'ACCEPTED','2024-11-11 00:00:00','Ban di giai cuu the gioi'),(12,12,'PENDING','2024-11-12 00:00:00','Ban di giai cuu the gioi'),(13,12,'PENDING','2024-11-16 00:00:00','Ban di giai cuu the gioi');
/*!40000 ALTER TABLE `absence_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` varchar(25) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `session` varchar(255) DEFAULT NULL,
  `status` enum('Kích hoạt','Bị khóa') DEFAULT 'Kích hoạt',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'hieu@gmail.com','123456','hieu@gmail.com','Giảng viên',NULL,NULL,'Bị khóa'),(2,'hieu1@gmail.com','123456','hieu1@gmail.com','Sinh viên',NULL,NULL,'Bị khóa'),(3,'hieu@sis.hust.edu.vn','123456','hieu@sis.hust.edu.vn','Giảng viên',NULL,NULL,'Bị khóa'),(4,'hieu@hust.edu.vn','123456','hieu@hust.edu.vn','Giảng viên','bOmlL1','1','Kích hoạt'),(5,'gv@hust.edu.vn','123456','gv@hust.edu.vn','Giảng viên','ChBCEr','1','Kích hoạt'),(6,'gv1@hust.edu.vn','123456','gv1@hust.edu.vn','lecturer','BdEJs6','1','Kích hoạt'),(7,'gv2@hust.edu.vn','123456','gv2@hust.edu.vn','LECTURER','h76N0F','1','Kích hoạt'),(8,'gv3@hust.edu.vn','123456','gv3@hust.edu.vn','LECTURER','mRJbCm','1','Kích hoạt'),(9,'sv1@hust.edu.vn','123456','sv1@hust.edu.vn','STUDENT','jjv419','9990','Kích hoạt'),(10,'sv2@hust.edu.vn','123456','sv2@hust.edu.vn','STUDENT',NULL,NULL,'Kích hoạt'),(11,'sv3@hust.edu.vn','123456','sv3@hust.edu.vn','STUDENT',NULL,NULL,'Bị khóa'),(12,'sv4@hust.edu.vn','123456','sv4@hust.edu.vn','STUDENT',NULL,NULL,'Kích hoạt'),(13,'sv11@hust.edu.vn','123456','sv11@hust.edu.vn','STUDENT','17VtUu','9999','Kích hoạt'),(14,'sv12@hust.edu.vn','123456','sv12@hust.edu.vn','STUDENT',NULL,NULL,'Kích hoạt'),(15,'sv13@hust.edu.vn','123456','sv13@hust.edu.vn','STUDENT',NULL,NULL,'Bị khóa'),(16,'sv14@hust.edu.vn','123456','sv14@hust.edu.vn','STUDENT',NULL,NULL,'Kích hoạt'),(17,'sv15@hust.edu.vn','123456','sv15@hust.edu.vn','STUDENT',NULL,NULL,'Bị khóa'),(18,'sv16@hust.edu.vn','123456','sv16@hust.edu.vn','STUDENT',NULL,NULL,'Kích hoạt'),(19,'sv17@hust.edu.vn','123456','sv17@hust.edu.vn','STUDENT',NULL,NULL,'Kích hoạt'),(20,'sv18@hust.edu.vn','123456','sv18@hust.edu.vn','STUDENT','f7RuZC','9990','Kích hoạt'),(21,'sv19@hust.edu.vn','123456','sv19@hust.edu.vn','STUDENT','j15ciC','9999','Kích hoạt'),(22,'sv20@hust.edu.vn','123456','sv20@hust.edu.vn','STUDENT',NULL,NULL,'Bị khóa'),(23,'sv001@hust.edu.vn','123456','sv001@hust.edu.vn','STUDENT',NULL,NULL,'Kích hoạt');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_logs`
--

DROP TABLE IF EXISTS `activity_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_logs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `activity_description` text,
  `activity_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `activity_logs_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_logs`
--

LOCK TABLES `activity_logs` WRITE;
/*!40000 ALTER TABLE `activity_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment_submissions`
--

DROP TABLE IF EXISTS `assignment_submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment_submissions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `assignment_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `submission_time` datetime DEFAULT NULL,
  `grade` float DEFAULT NULL,
  `file_url` varchar(300) DEFAULT NULL,
  `text_response` text,
  PRIMARY KEY (`id`),
  KEY `assignment_id` (`assignment_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `assignment_submissions_ibfk_1` FOREIGN KEY (`assignment_id`) REFERENCES `assignments` (`id`),
  CONSTRAINT `assignment_submissions_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_submissions`
--

LOCK TABLES `assignment_submissions` WRITE;
/*!40000 ALTER TABLE `assignment_submissions` DISABLE KEYS */;
INSERT INTO `assignment_submissions` VALUES (3,8,6,'2024-10-10 21:21:33',8,'ea1ea24f-3290-411e-88b4-2cbac1fae687_3.jpg','giai bai tap giai tich');
/*!40000 ALTER TABLE `assignment_submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` text,
  `lecturer_id` int DEFAULT NULL,
  `class_id` int DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `file_url` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lecturer_id` (`lecturer_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `assignments_ibfk_1` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`id`),
  CONSTRAINT `assignments_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (8,'Bai tap GT3','noi chung la kho lam',2,34,'2024-12-11 14:30:00','085ff213-0c5b-4979-b838-bc424ae0139d_440928461_1697910494073624_6837773432617050045_n.png'),(10,'Bai tap GT3','noi chung la kho lam',2,34,'2024-12-11 14:30:00','9f72e8cd-5ea2-4900-8437-6f0070eafa52_3.jpg');
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `attendance_status` varchar(25) NOT NULL,
  `attendance_time` datetime DEFAULT NULL,
  `class_detail_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `class_detail_id` (`class_detail_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`class_detail_id`) REFERENCES `class_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,'EXCUSED_ABSENCE','2024-11-12 00:00:00',7),(2,'PRESENT','2024-11-12 00:00:00',8),(3,'UNEXCUSED_ABSENCE','2024-11-12 00:00:00',9),(4,'PRESENT','2024-11-12 00:00:00',10),(5,'PRESENT','2024-11-12 00:00:00',11),(6,'UNEXCUSED_ABSENCE','2024-11-13 00:00:00',7),(7,'PRESENT','2024-11-13 00:00:00',8),(8,'UNEXCUSED_ABSENCE','2024-11-13 00:00:00',9),(9,'PRESENT','2024-11-13 00:00:00',10),(10,'PRESENT','2024-11-13 00:00:00',11),(11,'UNEXCUSED_ABSENCE','2024-11-13 00:00:00',7),(12,'PRESENT','2024-11-13 00:00:00',8),(13,'UNEXCUSED_ABSENCE','2024-11-13 00:00:00',9),(14,'PRESENT','2024-11-13 00:00:00',10),(15,'PRESENT','2024-11-13 00:00:00',11),(16,'UNEXCUSED_ABSENCE','2024-11-13 00:00:00',7),(17,'PRESENT','2024-11-13 00:00:00',8),(18,'UNEXCUSED_ABSENCE','2024-11-13 00:00:00',9),(19,'PRESENT','2024-11-13 00:00:00',10),(20,'PRESENT','2024-11-13 00:00:00',11);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_details`
--

DROP TABLE IF EXISTS `class_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `class_details_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `class_details_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_details`
--

LOCK TABLES `class_details` WRITE;
/*!40000 ALTER TABLE `class_details` DISABLE KEYS */;
INSERT INTO `class_details` VALUES (1,22,1),(2,22,2),(3,22,3),(4,22,5),(5,33,1),(6,33,2),(7,34,1),(8,34,2),(9,34,3),(10,34,5),(11,34,8),(12,34,6),(13,34,8);
/*!40000 ALTER TABLE `class_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(100) NOT NULL,
  `description` text,
  `lecturer_id` int DEFAULT NULL,
  `schedule` varchar(255) DEFAULT NULL,
  `start_date` timestamp NOT NULL,
  `end_date` timestamp NOT NULL,
  `max_student_amount` int NOT NULL,
  `code` varchar(6) NOT NULL,
  `attached_code` varchar(6) DEFAULT NULL,
  `class_type` varchar(20) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `lecturer_id` (`lecturer_id`),
  KEY `code_index` (`code`),
  CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'Introduction to Programming',NULL,NULL,NULL,'2024-09-30 17:00:00','2024-11-30 17:00:00',30,'799123',NULL,'LT_BT','ACTIVE'),(4,'Introduction to Programming',NULL,NULL,NULL,'2024-09-30 17:00:00','2024-11-30 17:00:00',30,'699123',NULL,'LT_BT','ACTIVE'),(6,'Introduction to Programming',NULL,NULL,NULL,'2024-09-30 17:00:00','2024-11-30 17:00:00',30,'699124',NULL,'LT_BT','ACTIVE'),(7,'Introduction to Programming',NULL,NULL,NULL,'2024-09-30 17:00:00','2024-11-30 17:00:00',30,'699125',NULL,'LT_BT','ACTIVE'),(9,'Introduction to Programming',NULL,NULL,NULL,'2024-09-30 17:00:00','2024-11-30 17:00:00',30,'699126',NULL,'LT_BT','ACTIVE'),(11,'Introduction to Programming',NULL,NULL,NULL,'2024-09-30 17:00:00','2024-11-30 17:00:00',30,'699326',NULL,'LT_BT','ACTIVE'),(13,'Introduction to Programming',NULL,NULL,NULL,'2024-09-30 17:00:00','2024-11-30 17:00:00',30,'699316',NULL,'LT_BT','ACTIVE'),(16,'Introduction to Programming',NULL,NULL,NULL,'2024-09-30 17:00:00','2024-11-30 17:00:00',30,'699116',NULL,'LT_BT','ACTIVE'),(17,'Advanced to Programming',NULL,1,NULL,'2024-10-31 17:00:00','2024-11-30 17:00:00',30,'699216',NULL,'LT_BT','COMPLETED'),(22,'WEL to Programming',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',30,'783226',NULL,'LT_BT','ACTIVE'),(24,'WEL to Programming',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',30,'781226',NULL,'LT_BT','ACTIVE'),(26,'WEL to Programming',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',30,'783211',NULL,'LT_BT','ACTIVE'),(27,'WEL to Programming',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',30,'783212',NULL,'LT_BT','ACTIVE'),(28,'WEL to Programming',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',30,'783213',NULL,'LT_BT','ACTIVE'),(29,'WEL to Programming',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',30,'783214',NULL,'LT_BT','ACTIVE'),(30,'WEL to Programming',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',30,'783234',NULL,'LT_BT','ACTIVE'),(31,'WEL to Programming',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',30,'11111',NULL,'LT_BT','ACTIVE'),(32,'WEL to Programming',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',30,'111111',NULL,'LT_BT','ACTIVE'),(33,'Test Name',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',2,'000001',NULL,'LT_BT','ACTIVE'),(34,'Test Attendance',NULL,2,NULL,'2024-10-10 17:00:00','2024-11-30 17:00:00',20,'000002',NULL,'LT','ACTIVE');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation_partners`
--

DROP TABLE IF EXISTS `conversation_partners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation_partners` (
  `id` int NOT NULL AUTO_INCREMENT,
  `conversation_id` int NOT NULL,
  `partner_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conversation_id` (`conversation_id`),
  KEY `partner_id` (`partner_id`),
  CONSTRAINT `conversation_partners_ibfk_1` FOREIGN KEY (`conversation_id`) REFERENCES `conversations` (`id`),
  CONSTRAINT `conversation_partners_ibfk_2` FOREIGN KEY (`partner_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation_partners`
--

LOCK TABLES `conversation_partners` WRITE;
/*!40000 ALTER TABLE `conversation_partners` DISABLE KEYS */;
INSERT INTO `conversation_partners` VALUES (11,13,21),(12,13,7),(13,14,7),(14,14,19),(15,15,7),(16,15,1),(17,16,20),(18,16,1),(19,17,20),(20,17,21),(21,18,9),(22,18,20);
/*!40000 ALTER TABLE `conversation_partners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversations`
--

DROP TABLE IF EXISTS `conversations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `current_message_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `current_message_id` (`current_message_id`),
  CONSTRAINT `conversations_ibfk_1` FOREIGN KEY (`current_message_id`) REFERENCES `messages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversations`
--

LOCK TABLES `conversations` WRITE;
/*!40000 ALTER TABLE `conversations` DISABLE KEYS */;
INSERT INTO `conversations` VALUES (13,11,'2024-10-23 16:36:43','2024-10-23 16:36:48'),(14,NULL,'2024-10-23 16:37:19','2024-10-23 16:47:23'),(15,16,'2024-10-23 17:00:52','2024-10-23 17:00:54'),(16,29,'2024-10-23 17:39:54','2024-10-23 17:49:57'),(17,22,'2024-10-23 17:45:41','2024-10-23 17:45:46'),(18,32,'2024-10-23 17:46:16','2024-10-23 17:50:30');
/*!40000 ALTER TABLE `conversations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00003','quan','db/changelog/db.changelog-master.xml','2024-09-21 14:50:52',1,'EXECUTED','9:ac9b806a8e072d2fe59fbac2cb3a82b5','sqlFile path=../../db/mysql/00003_quan_create.up.sql','',NULL,'4.27.0',NULL,NULL,'6905051722'),('00004.1','phanhieu244','db/changelog/db.changelog-master.xml','2024-09-22 13:00:11',2,'EXECUTED','9:25ea785a675d7bd83daff9529d28aa44','sqlFile path=../../db/mysql/00004_update_class.up.sql','',NULL,'4.27.0',NULL,NULL,'6984811481');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int NOT NULL,
  `LOCKED` tinyint NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,0,NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_requests`
--

DROP TABLE IF EXISTS `leave_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave_requests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `class_id` int DEFAULT NULL,
  `reason` text,
  `status` varchar(25) NOT NULL,
  `request_time` datetime DEFAULT NULL,
  `response_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `leave_requests_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `leave_requests_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_requests`
--

LOCK TABLES `leave_requests` WRITE;
/*!40000 ALTER TABLE `leave_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `leave_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturers`
--

DROP TABLE IF EXISTS `lecturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `lecturers_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturers`
--

LOCK TABLES `lecturers` WRITE;
/*!40000 ALTER TABLE `lecturers` DISABLE KEYS */;
INSERT INTO `lecturers` VALUES (1,'gv1@hust.edu.vn','gv1@hust.edu.vn',6),(2,'gv2@hust.edu.vn','gv2@hust.edu.vn',7),(3,'gv3@hust.edu.vn','gv3@hust.edu.vn',8);
/*!40000 ALTER TABLE `lecturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_id` int DEFAULT NULL,
  `material_name` varchar(255) NOT NULL,
  `description` text,
  `material_link` varchar(255) DEFAULT NULL,
  `material_type` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `materials_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `conversation_id` int NOT NULL,
  `sender` int NOT NULL,
  `content` text,
  `status` varchar(25) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `conversation_id` (`conversation_id`),
  KEY `sender` (`sender`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`conversation_id`) REFERENCES `conversations` (`id`),
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`sender`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (10,13,21,'fasdfa','UNREAD','2024-10-23 16:36:43','2024-10-23 16:36:43'),(11,13,21,'fasdfasdfasdfasdfasd','UNREAD','2024-10-23 16:36:48','2024-10-23 16:36:48'),(12,14,7,'fasdfasdfasd','READ','2024-10-23 16:37:19','2024-10-23 16:44:39'),(13,14,7,'fasdfasdfasdf','READ','2024-10-23 16:37:22','2024-10-23 16:44:32'),(15,15,7,'fasdfasd','UNREAD','2024-10-23 17:00:52','2024-10-23 17:00:52'),(16,15,7,'fasdfasd','UNREAD','2024-10-23 17:00:54','2024-10-23 17:00:54'),(17,16,20,'fasdf','UNREAD','2024-10-23 17:39:54','2024-10-23 17:39:54'),(18,16,20,'fasdfasd','UNREAD','2024-10-23 17:40:16','2024-10-23 17:40:16'),(19,16,20,'fassdf','UNREAD','2024-10-23 17:40:33','2024-10-23 17:40:33'),(20,16,20,'fadsfasd','UNREAD','2024-10-23 17:40:36','2024-10-23 17:40:36'),(21,17,20,'d','UNREAD','2024-10-23 17:45:41','2024-10-23 17:45:41'),(22,17,20,'fasdfasd','UNREAD','2024-10-23 17:45:46','2024-10-23 17:45:46'),(23,16,20,'fasdfasdf','UNREAD','2024-10-23 17:46:09','2024-10-23 17:46:09'),(24,18,9,'fasdfasdf','UNREAD','2024-10-23 17:46:16','2024-10-23 17:46:16'),(25,18,20,'fasdfasdfasdf','UNREAD','2024-10-23 17:46:20','2024-10-23 17:46:20'),(26,18,9,'fsadfasdfasd','UNREAD','2024-10-23 17:46:24','2024-10-23 17:46:24'),(27,18,9,'fd','UNREAD','2024-10-23 17:46:27','2024-10-23 17:46:27'),(28,16,20,'fasdfas','UNREAD','2024-10-23 17:49:16','2024-10-23 17:49:16'),(29,16,20,'fasdfas','UNREAD','2024-10-23 17:49:57','2024-10-23 17:49:57'),(30,18,9,'fsdfasdf','UNREAD','2024-10-23 17:50:19','2024-10-23 17:50:19'),(31,18,20,'ok ban','UNREAD','2024-10-23 17:50:25','2024-10-23 17:50:25'),(32,18,9,'chuyen gi the\n','UNREAD','2024-10-23 17:50:30','2024-10-23 17:50:30');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipient_id` int DEFAULT NULL,
  `message` text NOT NULL,
  `sent_time` datetime NOT NULL,
  `status` varchar(25) NOT NULL,
  `type` varchar(50) NOT NULL,
  `from_user` int DEFAULT NULL,
  `to_user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `recipient_id` (`recipient_id`),
  KEY `from_user` (`from_user`),
  KEY `to_user` (`to_user`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`from_user`) REFERENCES `accounts` (`id`),
  CONSTRAINT `notifications_ibfk_2` FOREIGN KEY (`from_user`) REFERENCES `accounts` (`id`),
  CONSTRAINT `notifications_ibfk_3` FOREIGN KEY (`to_user`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,NULL,'Hello, you have a new notification!','2024-10-11 18:56:15','UNREAD','ABSENCE',7,12),(2,NULL,'Hello, you have a new notification!','2024-10-11 19:04:24','READ','ABSENCE',7,21),(3,NULL,'Hello, you have a new notification!','2024-10-11 19:23:53','READ','ABSENCE',7,21),(4,NULL,'Hello, you have a new notification!','2024-10-11 19:23:54','UNREAD','ABSENCE',7,21),(5,NULL,'Hello, you have a new notification!','2024-10-11 19:23:55','UNREAD','ABSENCE',7,21);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'sv11@hust.edu.vn','sv11@hust.edu.vn',13),(2,'sv12@hust.edu.vn','sv12@hust.edu.vn',14),(3,'sv14@hust.edu.vn','sv14@hust.edu.vn',16),(4,'sv16@hust.edu.vn','sv16@hust.edu.vn',18),(5,'sv17@hust.edu.vn','sv17@hust.edu.vn',19),(6,'sv19@hust.edu.vn','sv19@hust.edu.vn',21),(7,'sv18@hust.edu.vn','sv18@hust.edu.vn',20),(8,'sv001@hust.edu.vn','sv001@hust.edu.vn',23);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-24  4:15:04
