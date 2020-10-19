-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: khoabanking
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `goinho`
--

DROP TABLE IF EXISTS `goinho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goinho` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bietdanhgoinho` varchar(255) DEFAULT NULL,
  `chuoimanguoigoinho` varchar(255) DEFAULT NULL,
  `hotennguoigoinho` varchar(255) DEFAULT NULL,
  `manganhang` int NOT NULL,
  `mataikhoancannho` int NOT NULL,
  `mataikhoangoinho` bigint NOT NULL,
  `trangthai` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmdql4gkwymd9h13e7lqlmxyht` (`manganhang`),
  KEY `FK340blvrs72mcrt06dpjcpu8j6` (`mataikhoancannho`),
  KEY `FKk9techsmm81y3y9gkldgbvvgj` (`mataikhoangoinho`),
  CONSTRAINT `FK340blvrs72mcrt06dpjcpu8j6` FOREIGN KEY (`mataikhoancannho`) REFERENCES `taikhoandangnhap` (`mataikhoan`),
  CONSTRAINT `FKk9techsmm81y3y9gkldgbvvgj` FOREIGN KEY (`mataikhoangoinho`) REFERENCES `taikhoanthanhtoan` (`mataikhoanthanhtoan`),
  CONSTRAINT `FKmdql4gkwymd9h13e7lqlmxyht` FOREIGN KEY (`manganhang`) REFERENCES `nganhang` (`manganhang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goinho`
--

LOCK TABLES `goinho` WRITE;
/*!40000 ALTER TABLE `goinho` DISABLE KEYS */;
/*!40000 ALTER TABLE `goinho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lichsugiaodich`
--

DROP TABLE IF EXISTS `lichsugiaodich`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lichsugiaodich` (
  `id` int NOT NULL AUTO_INCREMENT,
  `maloaigiaodich` int NOT NULL,
  `manganhanggui` int NOT NULL,
  `manganhangnhan` int NOT NULL,
  `manguoigui` int NOT NULL,
  `manguoinhan` int NOT NULL,
  `mataikhoannguoigui` bigint NOT NULL,
  `mataikhoannguoinhan` bigint NOT NULL,
  `ngaygiaodich` datetime DEFAULT NULL,
  `noidungchuyenkhoan` varchar(255) DEFAULT NULL,
  `signature_nguoi_gui` longtext,
  `signature_nguoi_nhan` longtext,
  `sotiengiaodich` varchar(255) DEFAULT NULL,
  `tennguoigui` varchar(255) DEFAULT NULL,
  `tennguoinhan` varchar(255) DEFAULT NULL,
  `trangthai` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK8i5d0vfxyk1nrdyvvhinhsqex` (`maloaigiaodich`),
  KEY `FKbbwu5jll5814mwey15t1222n` (`manganhanggui`),
  KEY `FK5i4xrbvma9y7jr97f2vky2rn8` (`manganhangnhan`),
  KEY `FKla35c4jsjdfr4ya5dwfiefmjp` (`manguoigui`),
  KEY `FKmundjqyenh7o7wjuq1ceifkb` (`manguoinhan`),
  CONSTRAINT `FK5i4xrbvma9y7jr97f2vky2rn8` FOREIGN KEY (`manganhangnhan`) REFERENCES `nganhang` (`manganhang`),
  CONSTRAINT `FK8i5d0vfxyk1nrdyvvhinhsqex` FOREIGN KEY (`maloaigiaodich`) REFERENCES `loaigiaodich` (`id`),
  CONSTRAINT `FKbbwu5jll5814mwey15t1222n` FOREIGN KEY (`manganhanggui`) REFERENCES `nganhang` (`manganhang`),
  CONSTRAINT `FKla35c4jsjdfr4ya5dwfiefmjp` FOREIGN KEY (`manguoigui`) REFERENCES `taikhoandangnhap` (`mataikhoan`),
  CONSTRAINT `FKmundjqyenh7o7wjuq1ceifkb` FOREIGN KEY (`manguoinhan`) REFERENCES `taikhoandangnhap` (`mataikhoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lichsugiaodich`
--

LOCK TABLES `lichsugiaodich` WRITE;
/*!40000 ALTER TABLE `lichsugiaodich` DISABLE KEYS */;
/*!40000 ALTER TABLE `lichsugiaodich` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaigiaodich`
--

DROP TABLE IF EXISTS `loaigiaodich`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaigiaodich` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenloaigiaodich` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaigiaodich`
--

LOCK TABLES `loaigiaodich` WRITE;
/*!40000 ALTER TABLE `loaigiaodich` DISABLE KEYS */;
INSERT INTO `loaigiaodich` VALUES (1,'Chuyển khoản nội bộ'),(2,'Chuyển khoản liên ngân hàng'),(3,'Thanh toán nợ');
/*!40000 ALTER TABLE `loaigiaodich` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loainguoidung`
--

DROP TABLE IF EXISTS `loainguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loainguoidung` (
  `maloainguoidung` int NOT NULL AUTO_INCREMENT,
  `mota` varchar(255) DEFAULT NULL,
  `tenloainguoidung` varchar(255) DEFAULT NULL,
  `trangthai` int DEFAULT '1',
  PRIMARY KEY (`maloainguoidung`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loainguoidung`
--

LOCK TABLES `loainguoidung` WRITE;
/*!40000 ALTER TABLE `loainguoidung` DISABLE KEYS */;
INSERT INTO `loainguoidung` VALUES (1,'Người có quyền cao nhất','ROLE_ADMIN',1),(2,'Nhân viên của ngân hàng','ROLE_EMPLOYEE',1),(3,'Người dùng','ROLE_USER',1);
/*!40000 ALTER TABLE `loainguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loainhacno`
--

DROP TABLE IF EXISTS `loainhacno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loainhacno` (
  `maloaino` int NOT NULL AUTO_INCREMENT,
  `tenloaino` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maloaino`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loainhacno`
--

LOCK TABLES `loainhacno` WRITE;
/*!40000 ALTER TABLE `loainhacno` DISABLE KEYS */;
INSERT INTO `loainhacno` VALUES (1,'Chưa thanh toán'),(2,'Đã thanh toán'),(3,'Từ chối thanh toán'),(4,'Hủy thanh toán');
/*!40000 ALTER TABLE `loainhacno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nganhang`
--

DROP TABLE IF EXISTS `nganhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nganhang` (
  `manganhang` int NOT NULL AUTO_INCREMENT,
  `partnercode` varchar(255) DEFAULT NULL,
  `privatekey` varchar(255) DEFAULT NULL,
  `publickey` varchar(255) DEFAULT NULL,
  `tennganhang` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`manganhang`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nganhang`
--

LOCK TABLES `nganhang` WRITE;
/*!40000 ALTER TABLE `nganhang` DISABLE KEYS */;
INSERT INTO `nganhang` VALUES (1,'local',NULL,NULL,'Nội bộ'),(2,'rsa',NULL,NULL,'RSA'),(3,'pgp',NULL,NULL,'PGP');
/*!40000 ALTER TABLE `nganhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacno`
--

DROP TABLE IF EXISTS `nhacno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacno` (
  `id` int NOT NULL AUTO_INCREMENT,
  `maloainhacno` int NOT NULL,
  `manguoibino` int NOT NULL,
  `manguoinhacno` int NOT NULL,
  `mataikhoanduocnhacno` bigint NOT NULL,
  `mataikhoannhacno` bigint NOT NULL,
  `noidungnhacno` varchar(255) DEFAULT NULL,
  `sotienno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7dbii0wu4rp23ll0s7gk9tdaq` (`maloainhacno`),
  KEY `FKjulr5ref1dq0cxoajyv88p2hb` (`mataikhoanduocnhacno`),
  KEY `FKq0tj9ptc1p2v1gnigqlj9s8ym` (`mataikhoannhacno`),
  KEY `FK59r1ldcigivj8d1mw5wpebl6k` (`manguoibino`),
  KEY `FKaw90bt375d5iv2ieor6w8ytrw` (`manguoinhacno`),
  CONSTRAINT `FK59r1ldcigivj8d1mw5wpebl6k` FOREIGN KEY (`manguoibino`) REFERENCES `taikhoandangnhap` (`mataikhoan`),
  CONSTRAINT `FK7dbii0wu4rp23ll0s7gk9tdaq` FOREIGN KEY (`maloainhacno`) REFERENCES `loainhacno` (`maloaino`),
  CONSTRAINT `FKaw90bt375d5iv2ieor6w8ytrw` FOREIGN KEY (`manguoinhacno`) REFERENCES `taikhoandangnhap` (`mataikhoan`),
  CONSTRAINT `FKjulr5ref1dq0cxoajyv88p2hb` FOREIGN KEY (`mataikhoanduocnhacno`) REFERENCES `taikhoanthanhtoan` (`mataikhoanthanhtoan`),
  CONSTRAINT `FKq0tj9ptc1p2v1gnigqlj9s8ym` FOREIGN KEY (`mataikhoannhacno`) REFERENCES `taikhoanthanhtoan` (`mataikhoanthanhtoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacno`
--

LOCK TABLES `nhacno` WRITE;
/*!40000 ALTER TABLE `nhacno` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhacno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `maotp` int NOT NULL,
  `thoigianluu` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoandangnhap`
--

DROP TABLE IF EXISTS `taikhoandangnhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoandangnhap` (
  `mataikhoan` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `hoten` varchar(255) DEFAULT NULL,
  `maloainguoidung` int NOT NULL,
  `matkhau` varchar(255) DEFAULT NULL,
  `refeshtoken` varchar(255) DEFAULT NULL,
  `sodienthoai` varchar(255) DEFAULT NULL,
  `tinhtrangno` int DEFAULT '0',
  `trangthai` int DEFAULT '1',
  PRIMARY KEY (`mataikhoan`),
  KEY `FK67c8badcur4pmit35h8w69q88` (`maloainguoidung`),
  CONSTRAINT `FK67c8badcur4pmit35h8w69q88` FOREIGN KEY (`maloainguoidung`) REFERENCES `loainguoidung` (`maloainguoidung`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoandangnhap`
--

LOCK TABLES `taikhoandangnhap` WRITE;
/*!40000 ALTER TABLE `taikhoandangnhap` DISABLE KEYS */;
INSERT INTO `taikhoandangnhap` VALUES (1,'admin@gmail.com','Admin',1,'$2a$12$tN6lNWoXbOa5NT2p5QZWeuwK1OGmS.59sq9EI/Ykh4Kk8lnKQztWy',NULL,'0123123123',0,1),(2,'employee@gmail.com','Employee',2,'$2a$12$tN6lNWoXbOa5NT2p5QZWeuwK1OGmS.59sq9EI/Ykh4Kk8lnKQztWy',NULL,'0456456456',0,1),(3,'billythekid15810@gmail.com','User',3,'$2a$12$tN6lNWoXbOa5NT2p5QZWeuwK1OGmS.59sq9EI/Ykh4Kk8lnKQztWy',NULL,'0789789789',0,1),(4,'user2@gmail.com','User2',3,'$2a$12$tN6lNWoXbOa5NT2p5QZWeuwK1OGmS.59sq9EI/Ykh4Kk8lnKQztWy',NULL,'0147147147',0,1),(5,'rsa@gmail.com','Thành viên bank RSA',3,'$2a$12$tN6lNWoXbOa5NT2p5QZWeuwK1OGmS.59sq9EI/Ykh4Kk8lnKQztWy',NULL,'0147147147',0,1),(6,'pgp@gmail.com','Thành viên bank PGP',3,'$2a$12$tN6lNWoXbOa5NT2p5QZWeuwK1OGmS.59sq9EI/Ykh4Kk8lnKQztWy',NULL,'0147147147',0,1);
/*!40000 ALTER TABLE `taikhoandangnhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoanliennganhang`
--

DROP TABLE IF EXISTS `taikhoanliennganhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoanliennganhang` (
  `mataikhoan` bigint NOT NULL,
  `hoten` varchar(255) DEFAULT NULL,
  `manganhang` int NOT NULL,
  `sodu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mataikhoan`),
  KEY `FKqjjft8gsnabhhm49748vwwqp4` (`manganhang`),
  CONSTRAINT `FKqjjft8gsnabhhm49748vwwqp4` FOREIGN KEY (`manganhang`) REFERENCES `nganhang` (`manganhang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoanliennganhang`
--

LOCK TABLES `taikhoanliennganhang` WRITE;
/*!40000 ALTER TABLE `taikhoanliennganhang` DISABLE KEYS */;
INSERT INTO `taikhoanliennganhang` VALUES (200001,'Tài khoản RSA',2,'500000'),(300001,'Tài khoản PGP',3,'500000');
/*!40000 ALTER TABLE `taikhoanliennganhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoanthanhtoan`
--

DROP TABLE IF EXISTS `taikhoanthanhtoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoanthanhtoan` (
  `mataikhoanthanhtoan` bigint NOT NULL,
  `mataikhoandangnhap` int NOT NULL,
  `sodu` varchar(255) DEFAULT NULL,
  `trangthai` int DEFAULT '1',
  PRIMARY KEY (`mataikhoanthanhtoan`),
  KEY `FK8qof15pqr2reig9g4ow416qt1` (`mataikhoandangnhap`),
  CONSTRAINT `FK8qof15pqr2reig9g4ow416qt1` FOREIGN KEY (`mataikhoandangnhap`) REFERENCES `taikhoandangnhap` (`mataikhoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoanthanhtoan`
--

LOCK TABLES `taikhoanthanhtoan` WRITE;
/*!40000 ALTER TABLE `taikhoanthanhtoan` DISABLE KEYS */;
INSERT INTO `taikhoanthanhtoan` VALUES (100001,3,'500000',1),(100002,3,'500000',1),(100003,4,'500000',1),(100004,4,'500000',1),(200001,5,'500000',1),(300001,6,'500000',1);
/*!40000 ALTER TABLE `taikhoanthanhtoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-17 14:28:05
