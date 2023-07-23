-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: his_registrationsystem
-- ------------------------------------------------------
-- Server version	8.0.30

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

drop database if exists his_registrationsystem;
create database his_registrationsystem;
use his_registrationsystem;

--
-- Table structure for table `case_history`
--

DROP TABLE IF EXISTS `case_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `case_history` (
  `patientNumber` int NOT NULL,
  `time` datetime DEFAULT NULL,
  `patientWord` varchar(100) DEFAULT NULL,
  `dealAdvice` varchar(100) DEFAULT NULL,
  `medicalResult` varchar(100) DEFAULT NULL,
  `doctorId` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`patientNumber`),
  KEY `doctorId_idx` (`doctorId`),
  CONSTRAINT `doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case_history`
--

LOCK TABLES `case_history` WRITE;
/*!40000 ALTER TABLE `case_history` DISABLE KEYS */;
INSERT INTO `case_history` VALUES (3,'2023-07-24 11:00:00','眼睛好痛','多喝热水','红眼病','522123197901010004');
/*!40000 ALTER TABLE `case_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `name` varchar(20) NOT NULL,
  `introduction` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES 
('儿科','儿科（或称小儿科）是现代医学其中一个分支,研究儿童的发育、护理和疾病,专门医疗患病的婴儿、儿童及青少年。最大的年龄限制介乎十四至二十一岁之间，而每个国家都有不同的界定。一个受到这方面知识专门训练的医生也叫做儿科医生。'),
('内科','内科学是临床医学的一个专科，几乎是所有其他临床医学的基础，亦有医学之母之称。'),
('外科','外科是研究外科疾病的发生、发展规律及其临床表现，诊断、预防和治疗的科学，是以手术切除、修补为主要治病手段的专业科室。'),
('妇产科','妇产科是临床医学四大主要学科之一，主要研究女性生殖器官疾病的病因、病理、诊断及防治，妊娠、分娩的生理和病理变化，高危妊娠及难产的预防和诊治，女性生殖内分泌，计划生育及妇女保健等。'),
('泌尿科','泌尿科是专门研究男女泌尿道与男性生殖系统的一门医学，它主要是从外科细分而来。男性的泌尿与生殖系统密不可分，而女性的泌尿道则开口于外阴部。'),
('皮肤科','皮肤科属于外科，主要治疗各种皮肤病，常见皮肤病有牛皮癣 、 疱疹 、酒渣鼻 、脓疱疮 、化脓菌感染 、疤痕 、癣 、鱼鳞病 、腋臭 、青春痘 、毛囊炎 、斑秃脱发 、男科炎症 、婴儿尿布疹 、鸡眼 、雀斑 、汗疱疹 、口腔部护理 、脱毛 、黄褐斑等。'),
('眼科','眼科的全称是“眼病专科”，是研究发生在视觉系统，包括眼球及与其相关联的组织有关疾病的学科。眼科一般研究玻璃体、视网膜疾病，眼视光学，青光眼和视神经病变，白内障等多种眼科疾病。'),
('骨科','骨科是各大医院最常见的科室之一，主要研究骨骼肌肉系统的解剖、生理与病理，运用药物、手术及物理方法保持和发展这一系统的正常形态与功能。'),
('麻醉科','麻醉学科是一个综合性的学科，它包含多学科的知识。范围很广，不单单是满足手术的要求，还参入各科室的抢救工作，妇科的无痛分娩，无痛流产等等。');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `id` varchar(18) NOT NULL,
  `password` varchar(16) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `dept` varchar(20) DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `fee` decimal(10,2) NOT NULL,
  `introduction` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dept` (`dept`),
  CONSTRAINT `dept` FOREIGN KEY (`dept`) REFERENCES `department` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES ('522123197901010001','50000001','王老大',42,'妇产科',0,'12366660001',90.00,'悬壶济世，妙手回春'),('522123197901010002','50000002','王老二',41,'儿科',1,'12366660002',85.00,'悬壶济世，妙手回春'),('522123197901010003','50000003','王老三',40,'骨科',1,'12366660003',80.00,'悬壶济世，妙手回春'),('522123197901010004','50000004','王老四',39,'眼科',0,'12366660004',75.00,'悬壶济世，妙手回春'),('522123197901010005','50000005','王老五',38,'皮肤科',1,'12366660005',70.00,'悬壶济世，妙手回春'),('522123197901010006','50000006','王老六',37,'泌尿科',0,'12366660006',65.00,'悬壶济世，妙手回春'),('522123197901010007','50000007','王老七',36,'外科',1,'12366660007',60.00,'悬壶济世，妙手回春'),('522123197901010008','50000008','王老八',35,'内科',1,'12366660008',55.00,'悬壶济世，妙手回春'),('522123197901010009','50000009','王老九',34,'麻醉科',0,'12366660009',50.00,'悬壶济世，妙手回春');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_record`
--

DROP TABLE IF EXISTS `medical_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record` (
  `patientId` varchar(18) NOT NULL,
  `doctorId` varchar(18) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`patientId`,`doctorId`,`time`),
  KEY `Rec_doctorId_idx` (`doctorId`),
  CONSTRAINT `Rec_doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`),
  CONSTRAINT `Rec_Patient_ID` FOREIGN KEY (`patientId`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record`
--

LOCK TABLES `medical_record` WRITE;
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
INSERT INTO `medical_record` VALUES ('522123199901010002','522123197901010004','2023-07-24 10:00:00');
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` varchar(18) NOT NULL,
  `password` varchar(16) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES ('522123199901010001','00000001','张三',20,1,'12300000001'),('522123199901010002','00000002','李四',19,0,'12300000002'),('522123199901010003','00000003','王麻子',18,1,'12300000003');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
  `patientNumber` int NOT NULL,
  `time` datetime DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `feeStatus` tinyint(1) DEFAULT NULL,
  `doctorId` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`patientNumber`),
  KEY `doctorId_idx` (`doctorId`),
  CONSTRAINT `P_doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (3,'2023-07-24 11:10:00','莎普爱思滴眼液',0,'522123197901010004');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `patientNumber` int NOT NULL AUTO_INCREMENT,
  `registTime` datetime NOT NULL,
  `reserveTime` date NOT NULL,
  `timeRange` tinyint(1) NOT NULL,
  `medicalStatus` int DEFAULT NULL,
  `registDept` varchar(20) DEFAULT NULL,
  `doctorId` varchar(18) NOT NULL,
  `patientId` varchar(18) NOT NULL,
  PRIMARY KEY (`patientNumber`),
  KEY `Reg_Dept_idx` (`registDept`),
  KEY `Reg_doctorId` (`doctorId`),
  KEY `Reg_Patient_ID` (`patientId`),
  CONSTRAINT `Reg_doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`),
  CONSTRAINT `Reg_Patient_ID` FOREIGN KEY (`patientId`) REFERENCES `patients` (`id`),
  CONSTRAINT `registDept` FOREIGN KEY (`registDept`) REFERENCES `department` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (1,'2023-07-22 11:55:00','2023-07-24',0,0,'妇产科','522123197901010001','522123199901010001'),(3,'2023-07-22 11:55:00','2023-07-24',0,1,'眼科','522123197901010004','522123199901010002'),(4,'2023-07-22 11:55:00','2023-07-24',0,0,'泌尿科','522123197901010006','522123199901010003');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-23 15:17:19
