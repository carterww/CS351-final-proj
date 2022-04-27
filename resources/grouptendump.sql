-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: groupten
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CustomerNum` char(3) NOT NULL,
  `CustomerName` char(35) NOT NULL,
  `Street` char(20) DEFAULT NULL,
  `City` char(15) DEFAULT NULL,
  `State` char(2) DEFAULT NULL,
  `PostalCode` char(5) DEFAULT NULL,
  `Balance` decimal(8,2) DEFAULT NULL,
  `CreditLimit` decimal(8,2) DEFAULT NULL,
  `RepNum` char(2) DEFAULT NULL,
  PRIMARY KEY (`CustomerNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('126','Toys Galore','28 Laketon St.','Fullton','CA','90085',1210.25,52000.92,'15'),('260','Brookings Direct','452 Columbus Dr.','Grove','CA','90092',575.00,10000.00,'30'),('334','The Everything Shop','342 Magee St.','Congaree','CA','90097',2345.75,7500.00,'45'),('386','Johnson\'s Department Store','124 Main St.','Northfield','CA','90098',879.25,7500.00,'30'),('440','Grove Historical Museum Store','3456 Central Ave.','Fullton','CA','90085',345.00,5000.00,'45'),('502','Cards and More','167 Hale St.','Mesa','CA','90104',5025.75,5000.00,'15'),('586','Almondton General Store','3345 Devon Ave.','Almondton','CA','90125',3456.75,15000.00,'45'),('665','Cricket Gift Shop','372 Oxford St.','Grove','CA','90092',678.90,7500.00,'30'),('713','Cress Store','12 Rising Sun Ave.','Congaree','CA','90097',4234.60,10000.00,'15'),('796','Unique Gifts','786 Passmore St.','Northfield','CA','90098',124.75,7500.00,'45'),('824','Kline\'s','945 Gilham St.','Mesa','CA','90104',2475.99,15000.00,'30'),('893','All Season Gifts','382 Wildwood Ave.','Fullton','CA','90085',935.75,7500.00,'15');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `ItemNum` char(4) NOT NULL,
  `Description` char(30) DEFAULT NULL,
  `OnHand` decimal(4,0) DEFAULT NULL,
  `Category` char(3) DEFAULT NULL,
  `Storehouse` char(1) DEFAULT NULL,
  `Price` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`ItemNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('AH74','Patience',9,'GME','3',22.99),('BR23','Skittles',21,'GME','2',29.99),('CD33','Wood Block Set (48 piece)',36,'TOY','1',89.49),('DL51','Classic Railway Set',12,'TOY','3',107.95),('DR67','Giant Star Brain Teaser',24,'PZL','2',31.95),('DW23','Mancala',40,'GME','3',50.00),('FD11','Rocking Horse',8,'TOY','3',124.95),('FH24','Puzzle Gift Set',65,'PZL','1',38.95),('KA12','Cribbage Set',56,'GME','3',75.00),('KD34','Pentominoes Brain Teaser',60,'PZL','2',14.95),('KL78','Pick Up Sticks',110,'GME','1',10.95),('MT03','Zauberkasten Brain Teaser',45,'PZL','1',45.79),('NL89','Wood Block Set (62 piece)',32,'TOY','3',119.75),('TR40','Tic Tac Toe',75,'GME','2',13.99),('TW35','Fire Engine',30,'TOY','2',118.95);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderline` (
  `orderNum` char(5) NOT NULL,
  `ItemNum` char(4) NOT NULL,
  `NumOrdered` decimal(3,0) DEFAULT NULL,
  `QuotedPrice` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`orderNum`,`ItemNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
INSERT INTO `orderline` VALUES ('51608','CD33',5,86.99),('51610','KL78',25,10.95),('51610','TR40',10,13.99),('51613','DL51',5,104.95),('51614','FD11',1,124.95),('51617','NL89',4,115.99),('51617','TW35',3,116.95),('51619','FD11',2,121.95),('51623','DR67',5,29.95),('51623','FH24',12,36.95),('51623','KD34',10,13.10),('51625','MT03',8,45.79);
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderNum` char(5) NOT NULL,
  `OrderDate` date DEFAULT NULL,
  `CustomerNum` char(3) DEFAULT NULL,
  PRIMARY KEY (`OrderNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('51608','2015-10-12','126'),('51610','2015-10-12','334'),('51613','2015-10-13','386'),('51614','2015-10-13','260'),('51617','2015-10-15','586'),('51619','2015-10-15','126'),('51623','2015-10-15','586'),('51625','2015-10-16','796');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rep`
--

DROP TABLE IF EXISTS `rep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rep` (
  `RepNum` char(2) NOT NULL,
  `LastName` char(15) DEFAULT NULL,
  `FirstName` char(15) DEFAULT NULL,
  `Street` char(15) DEFAULT NULL,
  `City` char(15) DEFAULT NULL,
  `State` char(2) DEFAULT NULL,
  `PostalCode` char(5) DEFAULT NULL,
  `Commission` decimal(7,2) DEFAULT NULL,
  `Rate` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`RepNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rep`
--

LOCK TABLES `rep` WRITE;
/*!40000 ALTER TABLE `rep` DISABLE KEYS */;
INSERT INTO `rep` VALUES ('15','Campos','Rafael','724 Vinca Dr.','Grove','CA','90092',23457.50,0.06),('30','Gradey','Megan','632 Liatris St.','Fullton','CA','90085',41317.00,0.08),('45','Tian','Hui','1785 Tyler Ave.','Northfield','CA','90098',27789.25,0.06),('60','Sefton','Janet','267 Oakley St.','Congaree','CA','90097',0.00,0.06),('69','Williams','Carter','45 Stone Rd','Bowling Green','KY','42101',0.00,0.05),('85','Al-Raisi','Ali','542 Rock Rd','Bowling green','KY','42101',0.00,0.03);
/*!40000 ALTER TABLE `rep` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-27 14:27:26
