-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: backend_java
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `account_status` tinyint(1) NOT NULL,
  `position` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'meohuyff','123456',1,'staff',1),(2,'minhlq2911','123456',1,'admin',1);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bills` (
  `bill_id` int NOT NULL AUTO_INCREMENT,
  `staff_id` int NOT NULL,
  `date` date NOT NULL,
  `total_price` int NOT NULL,
  `address` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_id` int NOT NULL,
  `discount_code` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `customer_id_idx` (`customer_id`),
  KEY `discoutn_code_idx` (`discount_code`),
  KEY `fk_staff_id_bill` (`staff_id`),
  CONSTRAINT `fk_customer_id_bill` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_id_bill` FOREIGN KEY (`staff_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES (3,2,'2024-03-20',4100000,'1 Lê Duẩn',3,NULL),(4,2,'2024-03-11',2550000,'1 Trường Chinh',4,NULL),(5,2,'2024-02-14',1500000,'20 Lê Đại Hành',1,NULL),(6,1,'2024-04-14',2600000,'40 Lý Thái Tổ',2,NULL),(7,2,'2024-05-04',925000,'21 phan boi chau',5,'GIAMNUAGIA'),(8,2,'2024-05-04',925000,'21 le hong phong',6,'GIAMNUAGIA'),(9,2,'2024-05-04',842500,'220 brvt',7,'GIAMNUAGIA'),(10,2,'2024-05-04',550000,'204 nguyen trai',8,'GIAMNUAGIA'),(11,2,'2024-05-05',2150000,'',9,'SALE50K');
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills_details`
--

DROP TABLE IF EXISTS `bills_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bills_details` (
  `product_serial_id` int NOT NULL,
  `bill_id` int NOT NULL,
  `price_single` int NOT NULL,
  KEY `billdetail_product_id` (`product_serial_id`),
  KEY `billdetail_bill_id` (`bill_id`),
  CONSTRAINT `fk_billdetail_bill_id` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`bill_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_billdetail_product_id` FOREIGN KEY (`product_serial_id`) REFERENCES `product_details` (`product_serial_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills_details`
--

LOCK TABLES `bills_details` WRITE;
/*!40000 ALTER TABLE `bills_details` DISABLE KEYS */;
INSERT INTO `bills_details` VALUES (22,3,800000),(32,3,700000),(12,3,1100000),(42,3,1500000),(1,4,1850000),(33,4,700000),(2,7,1850000),(3,8,1850000),(7,9,1685000),(13,10,1100000),(39,11,700000),(43,11,1500000);
/*!40000 ALTER TABLE `bills_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `brand_id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'Adidas',1),(2,'Nike',1),(3,'Converse',1);
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Chạy bộ',1),(2,'Đá banh',1),(3,'Leo núi',1);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `phone_number` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'minhtri','909080823',1),(2,'anhthu','808090932',1),(3,'kimyen','123456256',1),(4,'vannam','341565224',1),(5,'huy nee','0346674072',1),(6,'khanh oke','0123123123',1),(7,'huy dz','0987789987',1),(8,'ngoc anh','0909090909',1),(9,'Nguyen The Ngoc','0377937824',1),(10,'Nguyen Van A','0123456789',1);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discounts`
--

DROP TABLE IF EXISTS `discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discounts` (
  `discount_code` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `discount_value` int NOT NULL,
  `type` varchar(2) COLLATE utf8mb4_general_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`discount_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
INSERT INTO `discounts` VALUES ('GIAMNUAGIA',50,'PR','2024-04-01','2025-04-30',1),('SALE50K',50000,'AR','2024-04-01','2025-04-30',1);
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goodsreceipt_details`
--

DROP TABLE IF EXISTS `goodsreceipt_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goodsreceipt_details` (
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `receipt_id` int NOT NULL,
  `input_price` int NOT NULL,
  KEY `FK_PRODUCT_ID_GD` (`product_id`),
  KEY `FK_RECEIPT_ID` (`receipt_id`),
  CONSTRAINT `FK_PRODUCT_ID_GD` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `FK_RECEIPT_ID` FOREIGN KEY (`receipt_id`) REFERENCES `goodsreceipts` (`receipt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goodsreceipt_details`
--

LOCK TABLES `goodsreceipt_details` WRITE;
/*!40000 ALTER TABLE `goodsreceipt_details` DISABLE KEYS */;
INSERT INTO `goodsreceipt_details` VALUES (1,3,1,1480000),(2,3,2,560000),(3,4,3,1600000),(7,10,4,500000),(9,10,4,500000),(5,10,4,1200000),(4,10,4,1000000);
/*!40000 ALTER TABLE `goodsreceipt_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goodsreceipts`
--

DROP TABLE IF EXISTS `goodsreceipts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goodsreceipts` (
  `receipt_id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `total_price` int NOT NULL,
  `supplier_id` int NOT NULL,
  `staff_id` int NOT NULL,
  `status` tinyint DEFAULT NULL,
  PRIMARY KEY (`receipt_id`),
  KEY `FK_Supplier` (`supplier_id`),
  KEY `FK_Staff` (`staff_id`),
  CONSTRAINT `FK_Staff` FOREIGN KEY (`staff_id`) REFERENCES `staffs` (`staff_id`),
  CONSTRAINT `FK_Supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goodsreceipts`
--

LOCK TABLES `goodsreceipts` WRITE;
/*!40000 ALTER TABLE `goodsreceipts` DISABLE KEYS */;
INSERT INTO `goodsreceipts` VALUES (1,'2024-01-01',5400000,1,1,1),(2,'2024-01-01',6000000,2,2,1),(3,'2024-02-01',4800000,3,1,1),(4,'2024-01-01',32000000,1,2,1);
/*!40000 ALTER TABLE `goodsreceipts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_details`
--

DROP TABLE IF EXISTS `product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_details` (
  `product_serial_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `sold` tinyint(1) NOT NULL,
  PRIMARY KEY (`product_serial_id`),
  KEY `FK_Product` (`product_id`),
  CONSTRAINT `FK_Product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_details`
--

LOCK TABLES `product_details` WRITE;
/*!40000 ALTER TABLE `product_details` DISABLE KEYS */;
INSERT INTO `product_details` VALUES (1,1,1),(2,1,1),(3,1,1),(4,2,0),(5,2,0),(6,2,0),(7,3,1),(8,3,1),(9,3,0),(10,3,1),(11,4,1),(12,4,1),(13,4,1),(14,4,1),(15,4,1),(16,4,1),(17,4,1),(18,4,1),(19,4,1),(20,4,0),(21,7,1),(22,7,1),(23,7,1),(24,7,0),(25,7,0),(26,7,0),(27,7,1),(28,7,0),(29,7,0),(30,7,0),(31,9,1),(32,9,1),(33,9,1),(34,9,0),(35,9,0),(36,9,0),(37,9,0),(38,9,0),(39,9,1),(40,9,0),(41,5,1),(42,5,1),(43,5,1),(44,5,0),(45,5,0),(46,5,0),(47,5,0),(48,5,0),(49,5,0),(50,5,0);
/*!40000 ALTER TABLE `product_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `brand_id` int NOT NULL,
  `product_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `output_price` int NOT NULL,
  `country` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `year_of_product` int NOT NULL,
  `image_path` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` int NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `category_id_idx` (`category_id`),
  KEY `brand_id_idx` (`brand_id`),
  CONSTRAINT `FK_BRAND_ID_PRODUCTS` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`brand_id`),
  CONSTRAINT `FK_CATEGORY_ID_PRODUCTS` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,1,1,'Air Jordan 1',1850000,'USA',1985,'/src/images/images/adidas-prophere.png',0,1),(2,1,1,'Air Jordan 4          ',2200000,'USA',1989,'/src/images/images/adidas-super-star-red.png',3,1),(3,1,2,'Nike Air Force 1      ',1685000,'VietNam',1982,'/src/images/images/nike-adapt-bb.png',2,1),(4,2,2,'Nike Mercurial        ',1100000,'VietNam',2022,'/src/images/images/nike-air-max-270-react.png',1,1),(5,2,3,'Adidas Predator       ',1500000,'Germany  ',1994,'/src/images/images/adidas-prophere-black-white.png',7,1),(6,2,3,'Adidas Ultra Boost    ',1800000,'ChiNa',2015,'/src/images/images/adidas-ultraboost-4.png',0,1),(7,3,1,'Converse Chuck Taylor ',800000,'USA',1917,'/src/images/images/converse-chuck-taylor.png',6,1),(8,3,1,'Converse Jack Purcell ',900000,'USA      ',1935,'/src/images/images/nike-air-max-97.png',0,1),(9,3,2,'Puma Suede            ',700000,'Germany  ',1968,'/src/images/images/nike-shox-tl.png',6,1),(10,3,3,'The North Face        ',3500000,'USA      ',1966,'/src/images/images/van-old-school.png',0,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `returns`
--

DROP TABLE IF EXISTS `returns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `returns` (
  `return_id` int NOT NULL AUTO_INCREMENT,
  `product_serial_id` int NOT NULL,
  `date_return` date NOT NULL,
  `reason` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `active` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`return_id`),
  KEY `FK_PRODUCT_SERIAL_ID` (`product_serial_id`),
  CONSTRAINT `FK_PRODUCT_SERIAL_ID` FOREIGN KEY (`product_serial_id`) REFERENCES `bills_details` (`product_serial_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `returns`
--

LOCK TABLES `returns` WRITE;
/*!40000 ALTER TABLE `returns` DISABLE KEYS */;
INSERT INTO `returns` VALUES (1,1,'2024-11-15','lỗi sản phẩm',1,'OK'),(2,22,'2024-11-18','pass sản phẩm',1,'OK'),(3,13,'2024-05-06','LỖI',1,'OK'),(4,2,'2024-05-06','lỗi tác vụ',1,'NO');
/*!40000 ALTER TABLE `returns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_details`
--

DROP TABLE IF EXISTS `role_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_details` (
  `role_id` int NOT NULL,
  `account_id` int NOT NULL,
  KEY `fk_account_id_role_detail` (`account_id`),
  KEY `fk_role_id_role_detail` (`role_id`),
  CONSTRAINT `FK_ACCOUNT_ID_RD` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`),
  CONSTRAINT `FK_ROLE_ID_RD` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_details`
--

LOCK TABLES `role_details` WRITE;
/*!40000 ALTER TABLE `role_details` DISABLE KEYS */;
INSERT INTO `role_details` VALUES (1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2);
/*!40000 ALTER TABLE `role_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `role_tab_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Quản lý sản phẩm','Sản phẩm',1),(2,'Quản lý nhân viên','Nhân viên',1),(3,'Quản lý nhà cung cấp','Nhà cung cấp',1),(4,'Quản lý tài khoản','Tài khoản',1),(5,'Quản lý nhập hàng','Nhập hàng',1),(6,'Quản lý bán hàng','Bán hàng',1),(7,'Quản lý phân quyền','Phân quyền',1),(8,'Quản lý thống kê','Thống kê',1),(9,'Quản lý khuyến mãi','Khuyến mãi',1),(10,'Quản lý khách hàng','Khách hàng',1),(11,'Quản lý phiếu nhập','Phiếu nhập',1),(12,'Quản lý đổi trả','Đổi trả',1),(13,'Quản lý bảo hành','Bảo hành',1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staffs`
--

DROP TABLE IF EXISTS `staffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staffs` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `fk_account_id_staff` (`account_id`),
  CONSTRAINT `FK_ACCOUNT_ID_STAFF` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staffs`
--

LOCK TABLES `staffs` WRITE;
/*!40000 ALTER TABLE `staffs` DISABLE KEYS */;
INSERT INTO `staffs` VALUES (1,'Huy Nguyen Anh','huyna@sgu.edu.vn','0909123123',0,1),(2,'Minh Lu Quang','minhlq2911@sgu.edu.vn','0931814480',1,2),(3,'Tri Tran Minh','tritm@sgu.edu.vn','0912312309',1,NULL),(4,'Ngoc The Tran','ngoctn@sgu.edu.vn','0912312309',1,NULL);
/*!40000 ALTER TABLE `staffs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `supplier_id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `supplier_address` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (1,'Nhà cung cấp 1','200 Nguyễn Trãi',1),(2,'Nhà cung cấp 2','256 Lê Lợi',1),(3,'Nhà cung cấp 3','269 Hồn Bàng',1),(6,'Nhà cung cấp 4','120 Phan Văn Trị',1),(7,'Nhà cung cấp 5','500 Lê Lợi',1),(8,'Nhà cung cấp 9','220 An Dương Vương',0),(9,'NCC125','123',1);
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty_details`
--

DROP TABLE IF EXISTS `warranty_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warranty_details` (
  `warranty_detail_id` int NOT NULL AUTO_INCREMENT,
  `product_serial_id` int NOT NULL,
  `warranty_date` date NOT NULL,
  `reason` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `status` int NOT NULL,
  `active` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`warranty_detail_id`),
  KEY `product_id_idx` (`product_serial_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_serial_id`) REFERENCES `product_details` (`product_serial_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty_details`
--

LOCK TABLES `warranty_details` WRITE;
/*!40000 ALTER TABLE `warranty_details` DISABLE KEYS */;
INSERT INTO `warranty_details` VALUES (1,1,'2024-04-08','lỗi sản phẩm',1,'OK'),(2,21,'2024-04-20','khách hàng không hài lòng',1,'OK'),(3,23,'2024-11-18','Lỗi sản phẩm',1,'OK'),(4,21,'2024-04-10','lỗi',1,'OK');
/*!40000 ALTER TABLE `warranty_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-05  1:46:38
