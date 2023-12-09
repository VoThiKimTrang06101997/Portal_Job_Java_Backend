-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: developer_internship_new
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `CV` varchar(500) DEFAULT NULL,
  `desired_job` varchar(1000) DEFAULT NULL,
  `desiredWorkingProvince` varchar(255) DEFAULT NULL,
  `reference_letter` varchar(5000) DEFAULT NULL,
  `searchable` bit(1) DEFAULT NULL,
  `university_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  `desired_working_province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdofqp6qgoelgb7her3oe8po2o` (`university_id`),
  KEY `FKj9h7beyp5gsdtdb20km82b4fl` (`user_id`),
  CONSTRAINT `FKdofqp6qgoelgb7her3oe8po2o` FOREIGN KEY (`university_id`) REFERENCES `university` (`id`),
  CONSTRAINT `FKj9h7beyp5gsdtdb20km82b4fl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (1,NULL,'2023-05-10 04:57:54',NULL,'2023-05-10 04:57:54','CV001','BA, Tester, Back-end','TP.HCM',NULL,_binary '',1,1,NULL,NULL,NULL),(2,NULL,'2023-05-10 04:57:54',NULL,'2023-05-10 04:57:54','CV002','BA','Ha Noi',NULL,_binary '\0',2,2,NULL,NULL,NULL),(3,NULL,'2023-05-16 11:34:45',NULL,'2023-05-17 15:27:04','1684312023822_DeCuongThucTap_ThienThu.docx','HCM',NULL,'Java back-end dev',_binary '',1,21,NULL,24,'Hồ Chí Minh'),(4,NULL,'2023-05-16 13:16:12',NULL,'2023-05-16 13:16:12',NULL,NULL,NULL,NULL,_binary '',NULL,22,NULL,NULL,NULL),(5,NULL,'2023-05-16 15:05:10',NULL,'2023-05-16 15:05:10',NULL,NULL,NULL,NULL,_binary '',NULL,23,NULL,NULL,NULL),(6,NULL,'2023-05-17 15:14:39',NULL,'2023-05-18 10:22:49','1684380167401_DeCuongThucTap_ThienThu.docx','HCM',NULL,'Java back-end dev',_binary '',1,24,NULL,28,'Hồ Chí Minh'),(7,NULL,'2023-05-18 10:17:22',NULL,'2023-05-18 10:17:22',NULL,NULL,NULL,NULL,_binary '',NULL,25,NULL,NULL,NULL),(8,NULL,'2023-05-18 10:18:05',NULL,'2023-05-18 10:18:05',NULL,NULL,NULL,NULL,_binary '',NULL,26,NULL,NULL,NULL),(9,NULL,'2023-05-18 10:18:18',NULL,'2023-05-18 10:18:18',NULL,NULL,NULL,NULL,_binary '',NULL,27,NULL,NULL,NULL),(10,NULL,'2023-05-18 10:19:51',NULL,'2023-05-25 09:40:49','1684982448988_Java_Backend_NguyenMinhVy.pdf','HCM',NULL,'Java back-end dev',_binary '',1,28,NULL,28,'Hồ Chí Minh'),(11,NULL,'2023-05-25 09:49:51',NULL,'2023-05-25 09:49:51',NULL,NULL,NULL,NULL,_binary '',NULL,29,NULL,NULL,NULL),(12,NULL,'2023-05-25 09:51:21',NULL,'2023-05-25 10:43:47','1684983796953_Java_Backend_NguyenMinhVy.pdf','HCM',NULL,'Java back-end dev',_binary '',1,30,NULL,30,'Hồ Chí Minh'),(13,NULL,'2023-05-25 10:25:21',NULL,'2023-05-25 10:25:21',NULL,NULL,NULL,NULL,_binary '\0',NULL,31,NULL,NULL,NULL),(14,NULL,'2023-05-25 10:35:17',NULL,'2023-05-25 10:35:17',NULL,NULL,NULL,NULL,_binary '\0',NULL,32,NULL,NULL,NULL);
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_application`
--

DROP TABLE IF EXISTS `candidate_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate_application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `CV` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `reference_letter` varchar(5000) DEFAULT NULL,
  `candidate_id` bigint DEFAULT NULL,
  `job_id` bigint DEFAULT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqk1s00ya5abc7cih6ma0klwqq` (`candidate_id`),
  KEY `FKcdpyu77360933qap72c3ssujg` (`job_id`),
  CONSTRAINT `FKcdpyu77360933qap72c3ssujg` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `FKqk1s00ya5abc7cih6ma0klwqq` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_application`
--

LOCK TABLES `candidate_application` WRITE;
/*!40000 ALTER TABLE `candidate_application` DISABLE KEYS */;
INSERT INTO `candidate_application` VALUES (2,NULL,'2023-05-10 04:53:45',NULL,'2023-05-10 04:53:45','CV0002','user2@gmail.com','Nguyen B','9876543210',NULL,2,1,NULL,NULL),(45,NULL,'2023-05-25 14:18:53',NULL,'2023-05-25 14:18:53','1684999132154_1. Phieu danh gia TTDN.docx','vynguyen23200137@@gmail.com','Nguyen Minh Vy','0912345688',' thu gioi thieu',12,1,30,30),(46,NULL,'2023-05-25 14:27:34',NULL,'2023-05-25 14:27:34','1684999653946_1. Phieu danh gia TTDN.docx','vynguyen23200137@@gmail.com','Nguyen Minh Vy','0912345688',' thu gioi thieu',12,2,30,30),(47,NULL,'2023-05-25 14:47:20',NULL,'2023-05-25 14:47:20','1685000840008_1. Phieu danh gia TTDN.docx','vynguyen23200137@@gmail.com','Nguyen Minh Vy','0912345688',' thu gioi thieu',12,16,30,30),(48,NULL,'2023-05-25 14:48:08',NULL,'2023-05-25 14:48:08','1685000887864_1. Phieu danh gia TTDN.docx','vynguyen23200137@@gmail.com','Nguyen Minh Vy','0912345688',' thu gioi thieu',12,17,30,30),(49,NULL,'2023-05-25 14:48:22',NULL,'2023-05-25 14:48:22','1685000902386_1. Phieu danh gia TTDN.docx','vynguyen23200137@@gmail.com','Nguyen Minh Vy','0912345688',' thu gioi thieu',12,20,30,30),(50,NULL,'2023-05-25 14:59:44',NULL,'2023-05-25 14:59:44','1685001584175_1. Phieu danh gia TTDN.docx','vynguyen23200137@@gmail.com','Nguyen Minh Vy','0912345688',' thu gioi thieu',12,21,30,30);
/*!40000 ALTER TABLE `candidate_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_major`
--

DROP TABLE IF EXISTS `candidate_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate_major` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `candidate_id` bigint NOT NULL,
  `major_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKknai3r60abubbck50qviettqx` (`candidate_id`),
  KEY `FKaml36slyk7k8k3xuq1psiarm1` (`major_id`),
  CONSTRAINT `FKaml36slyk7k8k3xuq1psiarm1` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  CONSTRAINT `FKknai3r60abubbck50qviettqx` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_major`
--

LOCK TABLES `candidate_major` WRITE;
/*!40000 ALTER TABLE `candidate_major` DISABLE KEYS */;
INSERT INTO `candidate_major` VALUES (1,1,1),(2,1,3),(3,3,1),(4,6,1),(5,10,1),(6,12,1);
/*!40000 ALTER TABLE `candidate_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_position`
--

DROP TABLE IF EXISTS `candidate_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate_position` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `candidate_id` bigint DEFAULT NULL,
  `job_position_id` int DEFAULT NULL,
  `position_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhh7cq8ddtw11lg7etk6hf4skr` (`candidate_id`),
  KEY `FK8jcj9x9g16ws9y9gpsg1iy9sr` (`job_position_id`),
  KEY `FKb9vb4ls4ajju4mbtmh55lagx9` (`position_id`),
  CONSTRAINT `FK8jcj9x9g16ws9y9gpsg1iy9sr` FOREIGN KEY (`job_position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `FKb9vb4ls4ajju4mbtmh55lagx9` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `FKhh7cq8ddtw11lg7etk6hf4skr` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_position`
--

LOCK TABLES `candidate_position` WRITE;
/*!40000 ALTER TABLE `candidate_position` DISABLE KEYS */;
INSERT INTO `candidate_position` VALUES (1,NULL,NULL,NULL,NULL,3,NULL,3),(2,NULL,NULL,NULL,NULL,6,NULL,3),(3,NULL,NULL,NULL,NULL,10,NULL,3),(4,NULL,NULL,NULL,NULL,12,NULL,3);
/*!40000 ALTER TABLE `candidate_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_schedule`
--

DROP TABLE IF EXISTS `candidate_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate_schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `candidate_id` bigint DEFAULT NULL,
  `schedule_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjnemehle2pnv12s1njxe0qmbt` (`candidate_id`),
  KEY `FK6h3uk1xyictwdc0c0j6jrhqhn` (`schedule_id`),
  CONSTRAINT `FK6h3uk1xyictwdc0c0j6jrhqhn` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FKjnemehle2pnv12s1njxe0qmbt` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_schedule`
--

LOCK TABLES `candidate_schedule` WRITE;
/*!40000 ALTER TABLE `candidate_schedule` DISABLE KEYS */;
INSERT INTO `candidate_schedule` VALUES (1,1,1),(2,1,3),(3,3,2),(4,6,2),(5,10,2),(6,12,2);
/*!40000 ALTER TABLE `candidate_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `tax` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  `personnel_size` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_niu8sfil2gxywcru9ah3r4ec5` (`name`),
  KEY `FKqm7nvdm1ndyuybpx3uei4t4v1` (`status_id`),
  CONSTRAINT `FKqm7nvdm1ndyuybpx3uei4t4v1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,NULL,'2023-03-09 15:16:39',NULL,'2023-05-19 15:00:15','hàng đầu Việt Nam về công nghệ thông tin, cung cấp nhân lực toàn Việt Nam','ab@gmail.com','GO VAP','1684483214987_TinhHuyenXa2021.xlsx','Công ty R2S','08765432','1214','ab.edu.vn',1,NULL,8,1100),(2,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','công ty về IT ','intern@tma.com.vn','5',NULL,'TMA',' (028) 38912532','301425912','https://www.tma.vn/',1,NULL,NULL,10),(3,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','công ty về IT ','Recruitment@fsoft.com.vn','5',NULL,'FPT SOFTWARE','+84 24 73 044 888','101601092','career.fpt-software.com',1,NULL,NULL,10),(4,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','công ty về IT ','tuyendung@rakumo.vn	','2',NULL,'Rakumo','028 3812 0200','312288873','https://rakumo.vn/',1,NULL,NULL,10),(5,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','công ty về IT ','tuyendung@fujinet.net','2',NULL,'FUJINET SYSTEMS JSC','028 3847 7000','313424617','https://www.fujinet.net/vi',1,NULL,NULL,10),(6,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','công ty về IT ','info@axonactive.com','5',NULL,'AXON','+84 28 7109 1234','309699640','https://www.axonactive.com/',1,NULL,NULL,10),(7,NULL,NULL,NULL,NULL,'Công ty TNHH Đầu tư Phát triển và Chuyển giao công nghệ VINA (VINATTI) được thành lập bởi một nhóm các chuyên gia giàu kinh nghiệm trong lĩnh vực Công nghệ thông tin, Viễn thông và Tài chính. Với định hướng trở thành doanh nghiệp hàng đầu trong lĩnh vực Thương mại – Điện tử tại Việt Nam, VINATTI đã từng bước khẳng định mình qua các dự án cung cấp giải pháp và dịch vụ thanh toán trên nền tảng CNTT tiêu biểu như Hệ thống thanh toán đa dịch vụ tại quầy Bưu điện (Paypost), Hệ thống trung gian thanh toán (Ví điện tử A-PAY),....','admin@suga.sg','Số 1A Trương Quốc Dung, Phường 08, Quận Phú Nhuận, Thành phố Hồ Chí Minh',NULL,'SUGA',NULL,NULL,' http://thesugagroup.com/',1,NULL,NULL,499),(8,NULL,NULL,NULL,NULL,'TRẢI NGHIỆM CÙNG NHAU VƯƠN TỚI ĐỈNH CAO\nhttps://www.facebook.com/OneMountCareersOne Mount Group được thành lập với tham vọng kiến tạo hệ sinh thái công nghệ lớn nhất Việt Nam, cung cấp các giải pháp và dịch vụ xuyên suốt chuỗi giá trị, từ lĩnh vực dịch vụ tài chính, phân phối, bất động sản và bán lẻ. Bắt đầu với VinShop, ứng dụng bán lẻ chuyên dụng giúp các chủ tiệm tạp hoá độc lập phát triển cửa hàng của họ thông qua công nghệ hỗ trợ quản lý chuỗi cung ứng và hàng tồn kho. VinID, siêu ứng dụng và hệ thống quản lý khách hàng thân thiết lớn nhất Việt Nam tích hợp nhiều chức năng như thanh toán, quản lý nhà ở, mua hàng, dịch vụ tài chính. Và OneHousing, nền tảng toàn diện cho mọi nhu cầu về nhà ở, hỗ trợ mua bán, đầu tư và các dịch vụ khác liên quan đến bất động sản.\nTẦM NHÌN:\nOne Mount Group là đơn vị tiên phong, dẫn dắt và truyền cảm hứng để các doanh nghiệp cùng chung tay nâng cao hiệu quả của nền kinh tế Việt Nam, không ngừng cải thiện cuộc sống của mỗi người dân và giúp đánh thức mọi tiềm năng của xã hội.\nSỨ MỆNH:\nSứ mệnh của One Mount Group là tạo ra hệ sinh thái đáng tin cậy nhất Việt Nam, kết nối con người và doanh nghiệp tại nơi giao thoa giữa công nghệ và nhân văn.\n','press@onemount.com','•	Tower 2 Times City, 458 Minh Khai, Phường Vĩnh Tuy, Quận Hai Bà Trưng, Thành phố Hà Nội\n•	Tầng 9 tòa nhà Five Star Tower, 28 Bis Mạc Đĩnh Chi, Phường Đa Kao, Quận 1, Thành phố Hồ Chí Minh\n',NULL,'One Mount Group',NULL,NULL,' https://onemount.com/',1,NULL,NULL,1000),(9,NULL,NULL,NULL,NULL,'Work Like Tomorrow\nKofax is a leading provider of software to simplify and transform the First Mile™ of business. Success in the First Mile can dramatically improve the customer experience, greatly reduce operating costs and increase competitiveness, growth and profitability. Kofax software and solutions provide a rapid return on investment to more than 20,000 customers in financial services, insurance, government, healthcare, supply chain, business process outsourcing and other markets. Kofax delivers these through its direct sales and service organization, and a global network of more than 800 authorized resellers in more than 70 countries throughout the Americas, EMEA and Asia Pacific. For more information, visit kofax.com.\n\n','info@kofax.com','11 Fl., A tower, Handi Resco Bld., 521 Kim Mã str., Phường Ngọc Khánh, Quận Ba Đình, Thành phố Hà Nội',NULL,'KOFAX',NULL,NULL,'https://www.kofax.com/',1,NULL,NULL,100),(10,NULL,NULL,NULL,NULL,'DataStreams sẽ là đối tác tốt nhất để phát huy sức mạnh của dữ liệu của bạn\nNhiều công ty rơi vào tình trạng khó khăn vì không thể sử dụng dữ liệu mà họ đang có một cách hiệu quả cho dù khi họ sở hữu nhiều dữ liệu. Trong những trường hợp này, các công ty đó cần tìm một công ty chuyên môn về công nghệ thông tin mà có thể hỗ trợ giải pháp và dịch vụ tư vấn phù hợp. DataStreams đang xây dựng các giải pháp đẳng cấp thế giới có thể xử lý, quản lý và truyền đạt dữ liệu của người sử dụng một cách an toàn và hiệu quả trên bất kỳ nền tảng dữ liệu nào.\nTại sao phải là DataStreams - Expert for BigData, ETL, Data Governance, Data Management Solution\nDựa trên kinh nghiệm và bí quyết đa dạng được tích lũy trong lĩnh vực quản lý dữ liệu, DataStreams hiểu rõ nhu cầu của khách hàng, chẩn đoán trước dữ liệu của khách hàng và đề xuất các giải pháp phù hợp theo kết quả chẩn đoán.\nVì công ty chúng tôi có công nghệ sản phẩm hoàn chỉnh nên những khách hàng đang sử dụng sản phẩm của chúng tôi có thể nâng cao năng lực quản lý dữ liệu dần dần từng bước mà không chịu ảnh hưởng đặc biệt nào đến hệ điều hành. Đặc biệt, DataStreams đang cung cấp các giải pháp với mức giá hợp lý so với các giải pháp nước ngoài khác, đồng thời cung cấp các chức năng đa dạng, tính năng vượt trội và dịch vụ hỗ trợ kỹ thuật ổn định.\n','marketing@datastreams.co.kr','Tầng 25, Keangnam Landmark 72, Phạm Hùng, Phường Mễ Trì, Quận Nam Từ Liêm, Thành phố Hà Nội',NULL,'DataStreams Asia',NULL,NULL,'http://datastreams.co.kr/vt/',1,NULL,NULL,499),(11,NULL,NULL,NULL,NULL,'Mang lại giá trị đích thực\nTập Đoàn Công nghệ Quảng Ích\nTập Đoàn Công Nghệ Quảng Ích - QIG được thành lập trên cơ sở công ty cổ phần phần mềm Quảng Ích và các công ty con. Tập đoàn được hình thành với mục tiêu trở thành Tập đoàn công nghệ số 1 Việt Nam về các giải pháp công nghệ ứng dụng ở lĩnh vực giáo dục và các lĩnh vực có tính xã hội cao. Sau gần 20 năm hình thành và phát triển, công ty đã gặt hái được nhiều thành tựu và mang lại nhiều giá trị thiết thực cho các khách hàng, các sản phẩm và dịch vụ của công ty đã triển khai tới hàng triệu khách hàng trên toàn quốc.\nTầm Nhìn\n   Trở thành Tập đoàn công nghệ số 1 trong cung cấp các sản phẩm dịch vụ phần mềm cho giáo dục phổ thông tại Việt nam.\n   Xây dựng tổ chức với các giá trị tốt đẹp, văn minh và lan tỏa xã hội.\nSứ mệnh\n   Xây dựng hệ sinh thái phần mềm để chuyển đổi số toàn diện trong giáo dục phổ thông: Quản lý, Thư viện, Học tập và đánh giá, Kết nối nhà trường và gia đình.\n   Giúp ngành giáo dục (nhà quản lý, giáo viên, phụ huynh, học sinh) dễ dàng tiếp cận và ứng dụng công nghệ để nâng cao chất lượng quản lý, giảng dạy và học tập.\n','info@quangich.comquangich.','•	Z5-46 TTTM LePARC, KM 1,5 Pháp Vân, CV Yên Sở, Phường Yên Sở, Quận Hoàng Mai, Thành phố Hà Nội\n•	Tầng 09, Tòa nhà CIC TOWER tại ngõ 219 phố Trung Kính, Phường Yên Hoà, Quận Cầu Giấy, Thành phố Hà Nội\n\n',NULL,'Công ty Cổ Phần Công nghệ Quảng Ích',NULL,NULL,'https://qig.vn/',1,NULL,NULL,499),(12,NULL,NULL,NULL,NULL,'TMA Technology Group – Tập đoàn Công nghệ hàng đầu Việt Nam\nĐược thành lập năm 1997, TMA là tập đoàn công nghệ hàng đầu Việt Nam với hơn 3,800 kỹ sư. TMA có 6 văn phòng tại TP.HCM, Công viên Sáng tạo TMA tại Bình Định và 6 văn phòng tại nước ngoài (Canada, Mỹ, Đức, Úc, Nhật Bản và Singapore).\nVới sự phát triển vững mạnh 25 năm qua, TMA tự hào nhận được sự tin tưởng của khách hàng là những tập đoàn lớn đến từ 30 quốc gia trên thế giới. TMA nhiều năm liền vinh dự được bình chọn trong top doanh nghiệp CNTT Việt Nam, liên tục được vinh danh trong top 10 doanh nghiệp xuất khẩu phần mềm, Top 10 doanh nghiệp Fintech, Top 10 doanh nghiệp AI – IoT…\nĐặt yếu tố con người lên hàng đầu, TMA với môi trường làm việc chuyên nghiệp và thân thiện, luôn nỗ lực tạo ra đời sống văn hóa phong phú, sôi động, để nhân viên TMA luôn cảm thấy thoải mái, xem công ty không chỉ là nơi làm việc, mà còn là nơi có các hoạt động vui chơi, giải trí đầy thú vị, hấp dẫn. \nCùng với đó, TMA còn có rất nhiều phúc lợi hấp dẫn như:\n⮚ Lương thưởng cạnh tranh\n⮚ Nhiều cơ hội thăng tiến \n⮚ Bảo hiểm chăm sóc sức khỏe nhân viên toàn diện với mức bảo hiểm cao\n⮚ Hệ thống đào tạo nội bộ với hàng trăm khóa học mỗi năm \n⮚ Hàng trăm cơ hội công tác nước ngoài mỗi năm\nHãy gia nhập TMA ngay hôm nay!\n\n','recruit@tma.com.vn','•	Tòa nhà TMA, Công viên phần mềm Quang Trung, Phường Tân Chánh Hiệp, Quận 12, Thành phố Hồ Chí Minh\n•	111 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, Thành phố Hồ Chí Minh\n',NULL,'TMA Tech Group',NULL,NULL,'https://www.tma.vn/',1,NULL,NULL,1000),(13,NULL,NULL,NULL,NULL,'GIÁO DỤC ĐỂ PHÁT TRIỂN - ĐÀO TẠO ĐỂ LÀM VIỆC - TRẢI NGHIỆM ĐỂ TRƯỞNG THÀNH\nNỘI DUNG HOẠT ĐỘNG\n•	ĐÀO TẠO & GIÁO DỤC, phát triển kỹ năng hướng nghiệp \n•	PHÁI CỬ THỰC TẬP SINH, thực tập tại Nhật Bản \n•	ĐÀO TẠO & GIỚI THIỆU KỸ SƯ, làm việc tại Nhật Bản \n•	GIỚI THIỆU VIỆC LÀM TRONG NƯỚC, thay đổi ý thức – thăng tiến nghề nghiệp \n•	TƯ VẤN ĐẦU TƯ CHO CÁC DOANH NGHIỆP NHẬT BẢN, tận dụng cơ hội đầu tư tại Việt Nam \n•	TƯ VẤN THIẾT KẾ KỸ THUẬT CÔNG NGHỆ CAO, đào tạo phát triển kỹ sư trẻ Việt Nam  \n•	TƯ VẤN CHĂM SÓC SỨC KHỎE NGƯỜI CAO TUỔI TRONG NƯỚC, chất lượng tiêu chuẩn Nhật Bản \n','contact@esuhai.com','40/12 - 40/14 Ấp Bắc, Phường 13, Quận Tân Bình, Thành phố Hồ Chí Minh',NULL,'CÔNG TY TNHH ESUHAI',NULL,NULL,'https://esuhai.vn/',1,NULL,NULL,499),(14,NULL,NULL,NULL,NULL,'ESATECH - Services, Solutions, Electronics, Automations\nCông ty Cổ phần Công nghệ ESA Việt Nam - ESATECH là nhà cung cấp giải pháp một cửa cho sản xuất và vận chuyển thông minh.\nChúng tôi nghiên cứu, phát triển và tích hợp các giải pháp điều khiển cốt lõi cho xe có dẫn hướng tự động AGV, robot di động tự động - AMR, Xe nâng tự động - Automated Forklift.\nTầm nhìn:\n•	Đẩy mạnh mạng lưới nghiên cứu phát triển, ứng dụng công nghệ mới để tạo ra các sản phẩm ổn định, mạnh mẽ và thông minh hơn;\n•	Tăng cường hợp tác với các đối tác trong và ngoài nước để ứng dụng sản phẩm vào môi trường sản xuất thực tế, mở rộng lãnh thổ kinh doanh\n','info@esatech.vn','•	Số 265 đường Chiến Thắng, Phường Văn Quán, Quận Hà Đông, Thành phố Hà Nội\n•	Số 282 Đường Phan Trọng Tuệ, Xã Thanh Liệt, Huyện Thanh Trì, Thành phố Hà Nội\n',NULL,'Esa Việt Nam',NULL,NULL,'https://esa.vn/',1,NULL,NULL,99),(15,NULL,NULL,NULL,NULL,'LPBank - Liên Kết Phát Triển\nNgân hàng Thương mại Cổ phần Bưu điện Liên Việt (LPBank) tiền thân là Ngân hàng Thương mại Cổ phần Liên Việt (LienVietBank) được thành lập theo Giấy phép thành lập và hoạt động số 91/GP-NHNN ngày 28/03/2008 của Thống đốc Ngân hàng Nhà nước Việt Nam.\nNăm 2011, với việc Tổng Công ty Bưu chính Việt Nam góp vốn vào LienVietBank bằng giá trị Công ty Dịch vụ Tiết kiệm Bưu điện (VPSC) và bằng tiền mặt. Ngân hàng Liên Việt đã được Thủ tướng Chính phủ và Thống đốc Ngân hàng Nhà nước Việt Nam cho phép đổi tên thành Ngân hàng Thương mại Cổ phần Bưu điện Liên Việt. Cùng với việc đổi tên này, Tổng Công ty Bưu chính Việt Nam chính thức trở thành cổ đông lớn nhất của LPBank\nSau 15 năm thành lập và hoạt động, LPBank đã phát triển thành 1 trong top 10 NHTMCP lớn nhất Việt Nam với tổng tài sản trên 327.000 tỷ đồng, vốn điều lệ đạt 17.241 tỷ đồng. Hiện LPBank cũng là một trong những NH có mạng lưới rộng nhất trong hệ thống NH với 561 chi nhánh, phòng giao dịch và hơn 10.000 điểm cung cấp dịch vụ. Tổng số nhân sự của LPBank đã lên tới hơn 12.000 người.\n','tuyendung@lienvietpostbank.com.vn','•	Tòa nhà LPBank, số 210 Trần Quang Khải, Phường Tràng Tiền, Quận Hoàn Kiếm, Thành phố Hà Nội\n•	2A Nguyễn Thị Minh Khai, Phường Đa Kao, Quận 1, Thành phố Hồ Chí Minh\n•	140 Nguyễn Thị Minh Khai, Phường Hải Châu I, Quận Hải Châu, Thành phố Đà Nẵng\n',NULL,'NGÂN HÀNG THƯƠNG MẠI CỔ PHẦN BƯU ĐIỆN LIÊN VIỆT',NULL,NULL,'https://jobs.lienvietpostbank.com.vn/vi',1,NULL,NULL,19999),(16,NULL,NULL,NULL,NULL,'LET’S MAKE IT HAPPEN. Let’s create the future\nWE ARE FRACTAL - Leading Software Development Company in Vietnam. Established in July 2017, Fractal is a Software Solutions company , developing tailor made Sale/ Marketing solutions that help  Enterprises digitalizing operations and data procession.  We always try our best to stand out from the crowd of other software developments. Client satisfaction is our highest goal. Get inspired by client business, strike the best to help them pursuing success is our core mindset.  Be honored to serve most famous company in several industries: FMCG, Pharmacy, Transportation, Retails & Ecommerce.\n','thai.ngo@fractal.vn','Tầng 2, Tòa nhà Thái Sơn, 153 Ung Văn Khiêm, Phường 25, Quận Bình Thạnh, Thành phố Hồ Chí Minh',NULL,'CÔNG TY CỔ PHẦN CÔNG NGHỆ FRACTAL',NULL,NULL,'http://fractal.vn/',NULL,NULL,NULL,99);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_rate`
--

DROP TABLE IF EXISTS `company_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_rate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `score` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `status_id` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK87f02j6e9h79frw1vfcd1k3gm` (`company_id`),
  KEY `FKhk5pd94p899dvys4sjj82olkx` (`status_id`),
  KEY `FKitj0w2xjld2skj5pvul9xmkij` (`user_id`),
  CONSTRAINT `FK87f02j6e9h79frw1vfcd1k3gm` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKhk5pd94p899dvys4sjj82olkx` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKitj0w2xjld2skj5pvul9xmkij` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_rate`
--

LOCK TABLES `company_rate` WRITE;
/*!40000 ALTER TABLE `company_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `areaCode` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kt91asgk4ooq5248exnepyly8` (`areaCode`),
  UNIQUE KEY `UK_llidyp77h6xkeokpbmoy710d4` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (231,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','VNM','Vietnam');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr`
--

DROP TABLE IF EXISTS `hr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8yunn1u3pg1hw8uvc11qnfdw3` (`company_id`),
  KEY `FK2hw2m6g6qnwq2q9angvmo879g` (`user_id`),
  CONSTRAINT `FK2hw2m6g6qnwq2q9angvmo879g` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK8yunn1u3pg1hw8uvc11qnfdw3` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr`
--

LOCK TABLES `hr` WRITE;
/*!40000 ALTER TABLE `hr` DISABLE KEYS */;
INSERT INTO `hr` VALUES (1,NULL,'2023-03-10 16:11:31',NULL,'2023-03-10 16:11:33','HR',2,3,NULL,NULL),(2,NULL,'2023-03-10 16:11:31',NULL,'2023-05-19 14:56:15','HR Test',1,8,NULL,8);
/*!40000 ALTER TABLE `hr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_application`
--

DROP TABLE IF EXISTS `hr_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `hr_id` bigint DEFAULT NULL,
  `internship_programme_id` bigint DEFAULT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKekoosi21onfbrpxu5v6450eo3` (`hr_id`),
  KEY `FKep2uw6q9p2fbtlc8qco718yud` (`internship_programme_id`),
  CONSTRAINT `FKekoosi21onfbrpxu5v6450eo3` FOREIGN KEY (`hr_id`) REFERENCES `hr` (`id`),
  CONSTRAINT `FKep2uw6q9p2fbtlc8qco718yud` FOREIGN KEY (`internship_programme_id`) REFERENCES `internship_programme` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_application`
--

LOCK TABLES `hr_application` WRITE;
/*!40000 ALTER TABLE `hr_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `hr_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internship_major`
--

DROP TABLE IF EXISTS `internship_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internship_major` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `internship_programme_id` bigint DEFAULT NULL,
  `major_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh3q2ig706uqsvf605q7wans6b` (`internship_programme_id`),
  KEY `FKeie4dv0s6m4d7rrsyjug4ywy6` (`major_id`),
  CONSTRAINT `FKeie4dv0s6m4d7rrsyjug4ywy6` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  CONSTRAINT `FKh3q2ig706uqsvf605q7wans6b` FOREIGN KEY (`internship_programme_id`) REFERENCES `internship_programme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internship_major`
--

LOCK TABLES `internship_major` WRITE;
/*!40000 ALTER TABLE `internship_major` DISABLE KEYS */;
INSERT INTO `internship_major` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `internship_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internship_position`
--

DROP TABLE IF EXISTS `internship_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internship_position` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `internship_programme_id` bigint DEFAULT NULL,
  `position_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1vf1ad4myfevrhl2wfqag2ks5` (`internship_programme_id`),
  KEY `FK2lg0wcp6jmdfxt32ao5oalwvs` (`position_id`),
  CONSTRAINT `FK1vf1ad4myfevrhl2wfqag2ks5` FOREIGN KEY (`internship_programme_id`) REFERENCES `internship_programme` (`id`),
  CONSTRAINT `FK2lg0wcp6jmdfxt32ao5oalwvs` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internship_position`
--

LOCK TABLES `internship_position` WRITE;
/*!40000 ALTER TABLE `internship_position` DISABLE KEYS */;
INSERT INTO `internship_position` VALUES (1,1,1),(2,1,2),(3,2,1);
/*!40000 ALTER TABLE `internship_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internship_programme`
--

DROP TABLE IF EXISTS `internship_programme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internship_programme` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` bigint DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `other_info` varchar(1000) DEFAULT NULL,
  `requirement` varchar(1000) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `students` varchar(1000) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `university_id` bigint DEFAULT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkj2dab5m7b93jyxyxa6602mdo` (`status_id`),
  KEY `FKtcksg4b585248xsnjqe3m19vr` (`university_id`),
  CONSTRAINT `FKkj2dab5m7b93jyxyxa6602mdo` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKtcksg4b585248xsnjqe3m19vr` FOREIGN KEY (`university_id`) REFERENCES `university` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internship_programme`
--

LOCK TABLES `internship_programme` WRITE;
/*!40000 ALTER TABLE `internship_programme` DISABLE KEYS */;
INSERT INTO `internship_programme` VALUES (1,NULL,NULL,NULL,NULL,20,'Thực tập java đợt 1',NULL,'Thực tập java đợt 1','java',NULL,'list','Thực tập java đợt 1',1,1,1,NULL,'Hồ Chí Minh'),(2,NULL,NULL,NULL,NULL,30,'Thực tập BA',NULL,'Thực tập BA',NULL,NULL,'list','Thực tập BA',1,2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `internship_programme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internship_schedule`
--

DROP TABLE IF EXISTS `internship_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internship_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `internship_programme_id` bigint DEFAULT NULL,
  `schedule_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK45a4feqpvakr23t5fmogsyib1` (`internship_programme_id`),
  KEY `FK2bvgnslc4ntimqx5wdfj6b4c1` (`schedule_id`),
  CONSTRAINT `FK2bvgnslc4ntimqx5wdfj6b4c1` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FK45a4feqpvakr23t5fmogsyib1` FOREIGN KEY (`internship_programme_id`) REFERENCES `internship_programme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internship_schedule`
--

LOCK TABLES `internship_schedule` WRITE;
/*!40000 ALTER TABLE `internship_schedule` DISABLE KEYS */;
INSERT INTO `internship_schedule` VALUES (1,1,1);
/*!40000 ALTER TABLE `internship_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `other_info` varchar(5000) DEFAULT NULL,
  `requirement` varchar(5000) DEFAULT NULL,
  `salary_max` bigint DEFAULT NULL,
  `salary_min` bigint DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5q04favsasq8y70bsei7wv8fc` (`company_id`),
  KEY `FKtpro3k2axsnrs8ilpt8nmmf27` (`status_id`),
  CONSTRAINT `FK5q04favsasq8y70bsei7wv8fc` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKtpro3k2axsnrs8ilpt8nmmf27` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,12,'2023-05-10 04:43:16',NULL,'2023-05-12 10:06:38',10,'Cơ hội làm việc với một môi trường chuyên nghiệp.\nCó cơ hội được đào tạo các kỹ năng cần có của full-stack developer từ xây dựng yêu cầu đến release và vận hành hệ thống trên môi trường thực theo tiêu chuẩn Nhật Bản về qui trình và chất lượng.\nSau thời gian thực tập, có cơ hội trở thành nhân viên chính thức của công ty và được hưởng các chính sách phúc lợi hấp dẫn.','2023-05-31 09:43:16','1164 Phạm Văn Đồng, Quận Gò Vấp, Thành phố Hồ Chí Minh','Thực tập sinh Java','Trợ cấp thực tập: 3.000.000 - 5.000.000 đồng/tháng.\nLàm việc cùng team tech trẻ trung, đầy nhiệt huyết và năng động. \nCơ hội học hỏi và làm việc trực tiếp cùng các Leader đầy kinh nghiệm.','Sinh viên năm cuối chuyên ngành Công Nghệ Thông Tin.\nCó thể đi làm full từ 4-5 ngày/tuần.',1000000,5000000,'2023-05-18 09:43:16',3,1,8,NULL),(2,12,'2023-05-10 04:43:16',123,'2023-07-27 14:51:00',4,'Tham gia vào các dự án thiết kế, phát triển các sản phẩm mới theo yêu cầu của khách hàng hoặc từ Leader.\nTham gia bảo trì, nâng cấp chức năng, giao diện theo yêu cầu của khách hàng trên hệ thống website hiện có.\nTham gia phân tích yêu cầu, thiết kế, cài đặt và review code sử dụng một trong các framework của Javascript.\nNghiên cứu và ứng dụng các công nghệ mới vào phát triển sản phẩm.\nBáo cáo kết quả công việc cho PM.','2023-07-14 04:43:16','77 Thống Nhất, Quận Bình Thạnh, Thành phố Hồ Chí Minh','Thực tập BA','Trợ cấp thực tập: 3.000.000 - 5.000.000 đồng/tháng.\\r\\nLàm việc cùng team tech trẻ trung, đầy nhiệt huyết và năng động.\\r\\nCơ hội học hỏi và làm việc trực tiếp cùng các Leader đầy kinh nghiệm.','Yêu cầu ứng viên\\r\\nNắm vững kiến thức về HTML/CSS, JS/JQuery/AJAX/Bootstrap/Responsive.\\r\\nNắm vững ngôn ngữ lập trình JavaScript.\\r\\nHiểu biết về REST API.\\r\\nCó khả năng làm việc tốt với ReactJs, Next.Js\\r\\nCó khả năng tự học, và linh hoạt khi làm việc với các framework khác của Javascript. (NodeJs, ReactJs, Ghost, Strapi) để phát triển dự án.\\r\\n• Có tư duy logic tốt.\\r\\n• Có kỹ năng lên kế hoạch công việc và quản lý thời gian tốt.\\r\\n• Có kỹ năng làm việc nhóm tốt.\\r\\n• Chịu được áp lực và hoàn thành công việc đúng deadline.',1000000,5000000,'2023-05-10 04:43:16',6,1,8,NULL),(14,NULL,NULL,NULL,NULL,10,'•	Tham gia phát triển các dự án game của công ty\n•	Làm việc trực tiếp với Product Owner/Designer để làm UI cho mini/puzzle game và casino game, landing page và website\n•	Nhận task từ leader, tham gia phân tích thiết kế hệ thống hoặc framework, research  dựa trên require dự án \n•	Thời gian làm việc: Từ thứ 2 đến thứ 6 (8h/ngày). Riêng thứ 7 thì 2 lần trong tháng(chủ yếu đến công ty để giao lưu học hỏi giữa các team trong công ty)\n','2023-05-19 04:43:16','Số 1A Trương Quốc Dung, Phường 08, Quận Phú Nhuận, Thành phố Hồ Chí Minh','GAME BACKEND DEVELOPER','•	Mức lương thỏa thuận dựa trên năng lực ứng viên\n•	Trợ cấp ăn trưa(700.000- ăn tại công ty và không quy đổi sang tiền mặt)\n•	Trợ cấp chi phí gửi xe.\n•	Trợ cấp chi phí kết nối team mỗi 4 tháng/lần.\n•	Giảm ưu đãi các sản phẩm từ công ty như: Coffee HiPB, Hidental (nha khoa)...\n•	Được đề xuất, xét thưởng và xét tăng lương định kỳ 6 tháng 1 lần\n•	Full lương tháng 13, thưởng tết\n•	Được thưởng lợi nhuận dự án (10% profit của product sẽ share cho team)\n•	Full các chế độ như: BHXH, BHYT, Teambuilding,Khám sức khỏe\n•	Các chế độ khác như: nghỉ phép, lễ… theo chế độ mà bộ luật lao động Việt Nam quy định\n•	Được tiếp cận những thách thức để chinh phục, nhiều cơ hội thăng tiến và phát triển.\n•	Được làm việc trong môi trường năng động, trẻ trung\n•	Được đào tạo những kỹ năng mới phục vụ cho công việc\n•	Mức thu nhập tương xứng với năng lực và trình độ\n•	Được hướng dẫn, đào tạo kỹ năng cũng như xét tuyển thăng tiến các vị trí trong công việc.\n','•	Làm việc full-time \n•	Có khả năng giao tiếp tốt, làm việc trong môi trường teamwork hiệu quả.\n•	Có tinh thần học hỏi, cầu tiến. Có tinh thần trách nhiệm cao, sẵn sàng tiếp nhận thử thách trong công việc và đảm bảo hoàn thành được công việc đúng kế hoạch\n•	Kỹ năng quản lý thời gian tốt\n•	Tiếng anh tốt là một lợi thế \n•	Nắm vững cơ bản lập trình hướng đối tượng, kiến thức liên quan đến RESTful API, socket networking, web service\n•	Có kinh nghiệm làm việc với NodeJS, Spring Boot\n•	Có kiến thức về SQL, ElasticSearch, MongoDB\n•	Yêu thích đam mê làm game là một lợi thế\n\n\n',25000000,10000000,'2023-05-19 04:43:16',7,1,8,NULL),(15,NULL,NULL,NULL,'2023-05-25 14:47:26',4,'•	Writing clean & high-quality code\n•	Maintain & improve running- functionality as well as design and develop new system, new feature if needed\n•	Deliver end-to-end, including unit test, integration test and deployment to PROD\n•	Participate in code reviews & control coding quality standards\n','2023-05-16 04:43:16','Tầng 9 tòa nhà Five Star Tower, 28 Bis Mạc Đĩnh Chi, Phường Đa Kao, Quận 1, Thành phố Hồ Chí Minh','Back-End Engineer (Java)','•	Competitive income with lunch allowance, 13 fixed months salary, performance bonus and gift on special occasion.\n•	Annual leave: 15 - 20 days/year, birthday leave and other leaves/public holidays in accordance with the Labor Law of Vietnam\n•	Periodic medical checkup, Health/Social Insurances & PVI Insurance\n•	Be provided with high performance laptop and screen\n•	Modern and flexible Agile Office fully equipped with gaming, yoga, gym, lunch area, personal locker, open space... No dress code, just shine your way.\n','•	5+ years of handson development experience in Java and its frameworks (Spring Framework, Spring boot)\n•	Good knowledge of Data Structures and Algorithms\n•	Expertise in SQL / NoSQL Databases (MySQL, PostgreSQL, MongoDB)\n•	Experience with RESTful APIs, Microservices\n•	Familiarity with Redis, Kafka\n•	Experience with Docker/Kubernetes and Cloud Infrastructure is a plus\n•	Experience with Agile or Scrum software development methodologies\n',0,0,'2023-05-22 04:43:16',8,1,8,30),(16,NULL,NULL,NULL,NULL,2,'Analysts in the CDO team work with the business and product owner to create the backlog of epics and user stories for the team to build. They explore the data and processes to support this effort, create dashboards to help measure and communicate key metrics and test solutions built to ensure alignment with functional acceptance criteria.\n\n•	Lead the effort to communicate state of the business to stakeholders regularly - enable audiences to understand the reasons behind the trends - and provide insights to drive strategic decisions.\n•	Conduct exploratory analysis - go deep into the data to develop hypotheses and to answer complex business questions\n•	Develop tools and automated processes that project the work out to a broader audience. Strategize on democratizing data and insights to make analyses easily repeatable and generalizable by other team members in the future\n•	Ownership of conceptualizing, developing, and maintaining dashboards, visualizations\n•	Develop analytics frameworks and foundations to enable easy actionable insights and reliable measurement\n•	Become a data expert in your business domain and own data quality\n•	Empower the team to answer data questions quickly and easily by building high-quality ground truth data sets\n\n','2023-06-20 04:43:16','Tower 2 Times City, 458 Minh Khai, Phường Vĩnh Tuy, Quận Hai Bà Trưng, Thành phố Hà Nội','Data Analyst','13 months salary fixed','Key Skills\n•	Ability to translate business process and customer outcomes to user stories, requirements & specifications. \n•	Ability to work with business & technical stakeholders.\n•	Advanced SQL and data analysis skills. AGILE trained.\n•	2+ years experience in similar role.\nDomain Expertise\n•	Retail, Ecommerce, Supply Chain, Real Estate Services is plus\n•	Data modelling and data warehousing in general\nIndividual Skills\n•	Good communication skills - verbal and written\n•	Proactive problem solver, eye for detail, process driven\n•	AGILE trained, can elicit user stories, draw process diagrams\n•	Data modelling experience\n•	Good understanding of data management - data lineage, meta data, data quality, data governance\nAnalytics Experience\n•	Strong SQL and data analysis skill\n•	Visualisation with tools such as Power BI, Tableau etc.\n•	Data modelling & datamart experience\n•	Experience with an ETL framework like Airflow\n•	Nice to have - Python and R programming\n\n',0,0,'2023-05-22 04:43:16',8,1,8,NULL),(17,NULL,NULL,NULL,NULL,8,'•	Plan, develop, and execute test automation scripts for functional testing with scripting languages/frameworks such as Selenium, Robot, etc.\n•	Create and maintain test documentation such as defect reports and test result summaries.\n•	Interact with team members and other groups to ensure all requirements have been tested, defects have been documented, and quality issues identified during the testing process have been resolved.\n•	Communicate product and process information to other members of the team, other departments, and customers as necessary.\n','2023-06-20 04:43:16','11 Fl., A tower, Handi Resco Bld., 521 Kim Mã str., Phường Ngọc Khánh, Quận Ba Đình, Thành phố Hà Nội','Test Automation Engineer','•	Attractive salary (gross monthly salary of 18,000,000 - 25,000,000 VND)\n•	Stable and long-term job (Many employees of ours have been with us for longer than 10 years. We develop our own world-class products, not doing outsourcing).\n•	Annual salary review and adjustment.\n•	Professional working environment (Come and enjoy our powerful machines, licensed software, workspace with partitions, flexible working hours and no working overtime).\n•	Advanced health insurance for the whole family (Let the international hospitals take care of not only your health but also your loved ones\')\n•	Full salary contribution to social, health, and unemployment insurances (we will contribute an additional amount of 21.5% of your gross salary for your insurance benefits, including a good pension in future. Many other companies just offer an insurance package of minimum basic salary and so, the employees will have almost no income after retiring)\n•	Mon -Fri\n','•	Proficient in developing test automation scripts for functional testing with Selenium framework\n•	Knowledge of other software testing tools such as TestNG, RestAssured, Jmeter, Granafa, InfluxDB, SureFire, PyTest, Jacoco, SonarCloud, etc.\n•	Experience in installation, configuration, and troubleshooting of enterprise server and client applications in a virtual environment.\n•	Familiar with Agile and waterfall environments for fast-paced software development and testing release cycle.\n•	Good command of English.\n',25000000,18000000,'2023-05-13 04:43:16',9,1,8,NULL),(18,NULL,NULL,NULL,NULL,2,'•	Nghiên cứu, phân tích các tài liệu yêu cầu, thiết kế.\n•	Thiết kế và viết testcase, test script, checklist.\n•	Tham gia lập các bộ tài liệu test: Quy trình test, Testplan.\n•	Thực hiện kiểm thử, phát hiện lỗi, log lỗi và quản lý lỗi trên hệ thống Quản lý lỗi, phối hợp với developer để sửa lỗi.\n•	Test nghiệm thu sản phẩm, Xây dựng tài liệu hướng dẫn sử dụng, hướng dẫn cài đặt cho sản phẩm.\n•	Hướng dẫn, hỗ trợ khách hàng, hỗ trợ triển khai sản phẩm.\n•	Xây dựng báo cáo kết quả test. Báo cáo công việc với test lead.\n•	Đối với các ứng viên trên 3 năm kinh nghiệm được xem xét apply vào các vị trí test lead\n','2023-06-15 04:43:16','Tầng 25, Keangnam Landmark 72, Phạm Hùng, Phường Mễ Trì, Quận Nam Từ Liêm, Thành phố Hà Nội','[HN] Tester - Nhân Viên Kiểm Thử Phần Mềm','•	Performance Bonus\n•	Loyalty Bonus\n•	13th-month salary\n•	Technical Training in Korea\n•	Team building program\n•	Onsite Business trip\n•	Maternity Leave\n•	Annual Leave\n•	Insurance\n•	Happy meal\n•	Working hours: Monday to Friday from 08:30 to 17:30\n','•	Tốt nghiệp ĐH chuyên ngành IT hoặc các chuyên ngành liên quan. Hoặc ứng viên trái ngành đã có kinh nghiệm làm việc trong lĩnh vực kiểm thử.\n•	Nắm vững quy trình kiểm thử phần mềm.\n•	Có kỹ năng phân tích kết quả kiểm thử và báo cáo.\n•	Ưu tiên có các chứng chỉ về kiểm thử phần mềm: ISTQB\n•	Có kinh nghiệm xây dựng test case và thực hiện test.\n•	Có thể làm việc với cơ sở dữ liệu như MySQL, Oracle, SQL Server là một lợi thế....\n•	Đọc hiểu được tài liệu Tiếng Anh\n•	Vị trí test lead: nắm rõ qui trình test, qui trình phát triển sản phẩm, có kinh nghiệm quản lý dự án, xây dựng kế hoạch/báo cáo.\n•	Chấp nhận ứng viên chưa có kinh nghiệm (frehser) \n',10000000,5000000,'2023-05-23 04:43:16',10,1,8,NULL),(19,NULL,NULL,NULL,NULL,2,'•	Lập test plan, viết test case, chuẩn bị dữ liệu và môi trường test.\n•	Thực hiện kiểm thử, phát hiện lỗi và phối hợp với các developer để sửa lỗi.\n•	Log bugs, đánh giá mức độ quan trọng và mức độ khẩn cấp của bug.\n•	Phân tích và theo dõi kết quả test, báo cáo kết quả test.\n•	Tham gia nghiên cứu và đề xuất các cải tiến các chức năng, quy trình kiểm thử sản phẩm.\n•	Phân tích nghiệp vụ và lập test case.\n•	Đào tạo, chuyển giao nghiệp vụ cho team CSKH và Vận hành ứng dụng.\n','2023-06-10 04:43:16','•	Tầng 09, Tòa nhà CIC TOWER tại ngõ 219 phố Trung Kính, Phường Yên Hoà, Quận Cầu Giấy, Thành phố Hà Nội','[HN] Tester - Nhân Viên Kiểm Thử Phần Mềm','•	Mức lương theo kinh nghiệm ứng viên: từ 13-20tr triệu/tháng.\n•	Phụ cấp ngoài lương: Ăn trưa, gửi xe...\n•	Được đóng bảo hiểm xã hội, bảo hiểm thất nghiệp, bảo hiểm y tế và các quyền lợi khác theo quy định của pháp luật.\n•	Thưởng tháng: căn cứ vào chính sách của phòng ban.\n•	Thưởng cuối năm: phụ thuộc vào hiệu quả công việc cá nhân, bộ phận và tình hình kinh doanh của công ty.\n•	Thưởng đột xuất (thưởng nóng) nếu có hoàn thành tốt KPI.\n•	Môi trường làm việc: Trẻ trung, năng động, được tạo điều kiện để phát huy tối đa năng lực, nhiều cơ hội thăng tiến.\n•	Xét nâng lương định kỳ 1 lần/ năm và đột xuất nếu hoàn thành xuất sắc công việc.\n•	Được xem xét tham gia chương trình ESOP để gắn bó lâu dài với công ty.\n•	Được hưởng các quyền lợi và chế độ theo luật định (nghỉ lễ, BHXH, BHYT…), chế độ phúc lợi hàng năm của công ty.\n•	Chế độ lương làm thêm giờ (OT) theo đúng quy định của pháp luật.\n•	Nghỉ mát tại khách sạn 5 sao\n•	Lựa chọn làm việc tại 1 trong 2 địa điểm: Trung Kính (Yên Hòa - Cầu Giấy) hoặc Gamuda City (Yên Sở - Hoàng Mai)\n','•	Có từ 1 năm trở lên kinh nghiệm Tester.\n•	Nhanh nhẹn, chăm chỉ, khả năng học hỏi cao, và có trách nhiệm trong công việc.\n•	Nắm vững và hiểu biết sâu về quy trình Test, các kỹ thuật Testing.\n•	Có kinh nghiệm Test trên một trong các nền tảng các ứng dụng Web, Web Application, Mobile Application là một lợi thế.\n•	Có kỹ năng làm teamwork hoạt động đội nhóm tốt.\n•	Ưu tiên những ứng viên có kinh nghiệm làm việc với phần mềm có tính nghiệp vụ cao.\n•	Ưu tiên ứng viên có kỹ năng đào tạo.\n\n',20000000,13000000,'2023-05-19 04:43:16',11,1,8,NULL),(20,NULL,NULL,NULL,NULL,5,'•	Develop and enhance software through coding and testing.\n•	Deliver highest software quality by creating unit test.\n•	Adapt with the application and processes used in software development.\n•	Implements solutions as designed in technical documentation specification and coding standards.\n•	Reports to a technical lead.\n•	Bug fix and troubleshoot.\n','2023-06-19 04:43:16','Tòa nhà TMA, Công viên phần mềm Quang Trung, Phường Tân Chánh Hiệp, Quận 12, Thành phố Hồ Chí Minh','Full stack Developer (.NET/ReactJS)','•	13th salary and bonus based on business performance;\n•	Performance review each year and salary adjustment based on job performance\n•	Healthcare: Annual health check-up, Premium Health Insurance\n•	Comfortable working conditions and flexible working time\n•	Good career growth opportunities with interesting and challenging projects;\n•	English, technical, and soft skills training courses;\n•	Outdoor activities with company support: sports clubs, team building, happy hour parties, birthdays, trips, staff and family events, etc.\n•	Time work: Mon -Fri (8:30 - 12:00 - 1:30 - 6:00)\n','•	Good at programming language (C#) and framework (.NET or .NET Core)\r •	Good at Entity Framework\r •	Good at Object Oriented Programming (OOP), SOLID principles\r •	Has knowledge on design patterns (singleton, factory, bridge, building) programming specifically on C#\r •	Worked with RESTful APIs\r •	Has knowledge on SQL\r •	Knowledge on one of FE frameworks as Angular, React, Vue is a plus\r •	Knowledge on one of UI frameworks as Bootstrap, Material, Ant Design is a plus\r •	Fast learner, self-motivated, good problem-solving skills\r •	Highly accurate and detail-oriented.\r •	Good communication in English\r •	Familiar with tools: SVN/Git, Visual Studio, Visual Studio Code, SQL Server Management\r ',0,0,'2023-05-19 04:43:16',12,1,8,NULL),(21,NULL,NULL,NULL,NULL,2,'•	Lập trình phát triển các ứng dụng quản lý trong công ty trên nền tảng .NET .\n•	Lập trình FE dựa trên framework Angular, Ant design\n•	Phân tích yêu cầu, thiết kết giao diện theo yêu cầu để đáp ứng nhu cầu của người dùng.\n•	Tối ưu hoá code để tăng tốc độ, trải nghiệm cho người dùng.\n•	Bảo trì, nâng cấp các ứng dụng quản lý nội bộ\n•	Hỗ trợ các công việc dự án phát sinh theo chỉ đạo từ trưởng phòng ban\n','2023-05-31 04:43:16','40/12 - 40/14 Ấp Bắc, Phường 13, Quận Tân Bình, Thành phố Hồ Chí Minh','Nhân viên lập trình Front-end (HTML/CSS/JavaScript)','•	Lương theo năng lực thực tế; điều chỉnh định kỳ hằng năm, năm đầu chế độ điều chỉnh 2 lần\n•	 Thưởng hiệu quả công việc: 2 kỳ / năm & các kỳ thưởng lễ (30/04, 02/09, 20/11, 01/01)\n•	 Phụ cấp ngoài giờ, Phụ cấp di chuyển chi nhánh, Phụ cấp công tác.\n•	 Thăm bệnh, Khám sức khỏe định kỳ hằng năm, Tham gia BHXH-BHYT, Chế độ Bảo hiểm cao cấp đối với nhân viên thâm niên,…\n•	 Trợ cấp nhà xa, Cơm trưa, Môi trường làm việc, Thai sản,..\n•	 Phúc lợi Sinh nhật, Đám cưới, Sinh con\n•	 Tham gia các sự kiện: du lịch, teambuilding, …\n•	 Làm việc: Từ thứ Hai đến sáng Thứ 7 (nghỉ chiều thứ 7 và chủ nhật)\n•	Outdoor activities with company support: sports clubs, team building, happy hour parties, birthdays, trips, staff and family events, etc.\n•	Time work: Mon -Fri (8:30 - 12:00 - 1:30 - 6:00)\n','•	Có từ 2 năm kinh nghiệm trở lên\n•	Có kinh nghiệm và kiến thức vững về các công cụ lập trình giao diện: HTML/ CSS, Responsive, JavaScript, Bootstrap, Ant Design, Tailwind CSS, Material UI, ...\n•	Có kiến thức về lập trình Database, SQL server\n•	Có kiến thức về các framework JavaScript như Angular, React là điểm cộng\n•	Có kiến thức và kinh nghiệm lập trình Mobile là một lợi thế (React Native, Xamarin, Flutter,...)\n•	Có kiến thức về quy trình phát triển phần mềm theo mô hình Agile là lợi thế\n•	Khả năng giao tiếp và xử lý vấn đề tốt\n•	Có khả năng đọc hiểu tài liệu tiếng Anh\n•	Có thể làm việc trong môi trường áp lực cao\n',25000000,15000000,'2023-05-10 04:43:16',13,1,8,NULL),(22,NULL,NULL,NULL,NULL,10,'•	Chịu trách nhiệm thiết kế, duy trì và phát triển các hệ thống web theo yêu cầu.\n•	Cùng trao đổi team để phân tích, xây dựng, thiết kế hệ thống.\n•	Trao đổi với designer, BA về thiết kế UI/UX của sản phẩm.\n•	Đảm bảo đáp ứng các tiêu chuẩn về đồ họa trên giao diện.\n•	Thống nhất với Backend team về API và thực hiện tích hợp API.\n•	Tham gia vào review code front end.\n•	Ngoài ra chi tiết công việc sẽ được trao đổi khi phỏng vấn. \n','2023-05-31 04:43:16','Số 265 đường Chiến Thắng, Phường Văn Quán, Quận Hà Đông, Thành phố Hà Nội','Front-end Developer ( Angular/JS/HTML5/CSS3)','•	Mức lương: 14 – 35 triệu theo năng lực thực tế , tăng lương theo thâm niên công tác.\n•	Cấp máy tính, thiết bị làm việc.\n•	Cơ hội được đào tạo liên tục nâng cao chuyên môn và các kỹ năng làm viêc.\n•	Lộ trình thăng tiến, tăng lương rõ ràng.\n•	Đóng BHXH đẩy đủ và đúng luật.\n•	Có nhiều đợt thưởng trong năm: Thưởng lễ 30-4, thưởng ngày truyền thống, thưởng 6 tháng đầu năm, thưởng ngày thành lập công ty, thưởng Tết Dương, thưởng Tết âm (thưởng năm) và các giải thưởng cá nhân khác: Tiên tiến, xuất sắc, tiềm năng, có cố gắng….\n•	Hỗ trợ tiền điện thoại. Hỗ trợ tiền ăn trưa.\n•	Có chế độ chăm sóc y tế cho nhân viên, du lịch 1 năm/1 lần (trong nước hoặc nước ngoài) và tham gia các hoạt động vui chơi giải trí khác (sinh nhật công ty, sinh nhật các thành viên…)\n•	Có cơ hội được làm việc và công tác ở nước ngoài (Thái Lan, Malaysia, Singapore, Ấn Độ, Đức)\n•	Nghỉ 2 ngày thứ 7 trong tháng và các chủ nhật, chế độ phép đầy đủ theo quy định.\n','•	Yêu cầu có từ 1-3 năm kinh nghiệm lập trình FE thực tế.\n•	Có ít nhất 1-3 năm kinh nghiệm làm việc trong các dự thực tế.\n•	Hơn 4 năm kinh nghiệm xây dựng các sản phẩm dựa trên web với các Front-End hiện đại và tiên tiến, chẳng hạn như Bootstrap, Angular.\n•	Thành thạo HTML5, CSS3, JavaScript, TypeScript.\n•	Trải nghiệm với các công cụ xây dựng Front-End: Webpack, Yarn, Gulp.\n•	Kiến thức về DOM, Ajax, JSON, Dịch vụ web, API Web, API Restful, xác thực dựa trên mã thông báo.\n•	Quen thuộc với các công cụ phát triển và gỡ lỗi cho các vấn đề đa nền tảng, trình duyệt chéo.\n•	Có kinh nghiệm Web socket/ Socket.io/ JavaScript không đồng bộ.\n•	Hiểu biết và thành thạo các công cụ Devops như Jenkins, Git,... là một lợi thế.\n',0,0,'2023-05-10 04:43:16',14,1,8,NULL),(23,NULL,NULL,NULL,NULL,10,'•	Trực tiếp tham gia các dự án lập trình ứng dụng di động trong ngân hàng\n•	Thực hiện (lập trình) các chức năng theo thiết kế.\n•	Cải tiến và nâng cao chất lượng dự án, đề xuất ra các giải pháp kỹ thuật giải quyết các vấn đề phát sinh.\n•	Tham gia xây dựng, đóng góp nhằm cải thiện và tối ưu trải nghiệm người dùng.\n•	Làm việc với RESTful API để kết nối ứng dụng với server\n•	Các công việc khác thuộc chuyên môn theo sự phân công của cấp trên.\n','2023-05-31 04:43:16','Tòa nhà LPBank, số 210 Trần Quang Khải, Phường Tràng Tiền, Quận Hoàn Kiếm, Thành phố Hà Nội','Lập trình viên Mobile Android','•	Thưởng các Ngày lễ, Tết theo chính sách ngân hàng từng thời kỳ (Từ 16-18 tháng lương/ năm)\n•	Đầy đủ các chế độ, quyền lợi của cán bộ nhân viên Ngân hàng.\n•	Môi trường năng động, thân thiện, gần gũi. Có nhiều cơ hội học đào tạo, học hỏi và phát triển.\n•	Chính sách review lương hàng năm\n•	Cung cấp thiết bị làm việc\n•	Du lịch/team building hằng năm\n•	Tham gia đầy đủ bảo hiểm (BHXH/BHYT)\n•	12 ngày phép năm\n•	Làm việc từ 08h00 đến 17h00, từ Thứ 2 đến Thứ 6 hàng tuần. Từ 08h00 đến 12h00 sáng Thứ 7\n\n','•	Học vấn: Tốt nghiệp Cao đẳng/Đại học các ngành về Công nghệ thông tin hoặc các ngành liên quan khác.\n•	Kinh nghiệm: Có ít nhất 1 năm kinh nghiệm trong phát triển ứng dụng Android\n•	Nắm vững kiến thức về Java, Xml, Json, Android SDK\n•	Biết thêm Kotlin/Flutter là một lợi thế\n•	Hiểu và làm việc được với các mô hình OOP, MVP, MVVM\n•	Có kinh nghiệm làm việc với Firebase Message, socket\n•	Có kiến thức về hệ quản trị cơ sở dữ liệu SQL\n•	Kỹ năng mềm: Giao tiếp, làm việc độc lập/nhóm, tự học và nghiên cứu,...\n•	Thái độ, tinh thần, tư duy: Tiên phong, cầu tiến, chủ động, tuân thủ quy định, trung thực, thẳng thắn, biết lắng nghe, có tinh thần trách nhiệm cao, luôn hướng đến giải pháp,...\n•	Ngôn ngữ: Tiếng Anh khá trở lên, có khả năng đọc hiểu các tài liệu tiếng anh chuyên ngành.\n',35000000,14000000,'2023-05-22 04:43:16',15,1,8,NULL),(24,NULL,NULL,NULL,NULL,3,'•	Participate in analyzing, designing, and improving the functions of the company\'s products and services.\n•	Coordinate with the leader and team to build mobile apps by Flutter, Android, IOS native. \n•	Participate in operating and maintaining apps.\n•	Research new technologies suitable for investment projects of the company.\n•	Other jobs when assigned by superiors.\n','2023-05-30 04:43:16','Tầng 2, Tòa nhà Thái Sơn, 153 Ung Văn Khiêm, Phường 25, Quận Bình Thạnh, Thành phố Hồ Chí Minh','Mobile Developer (Flutter)','•	Good salary, bonus for 13 months and according to the project\n•	Have the opportunity to face many problems in programming and life that a big company you never have.\n•	Many opportunities to learn, demonstrate ability and advance at work.\n•	Improve English by working and daily meeting.\n•	Being trained if not experienced\n•	Working with young, healthy, ambitious and capable people.\n•	Open, dynamic, colorful environment.\n•	All regimes as prescribed by the State (health insurance, social insurance, ...)\n•	In case of emergency (COVID-19), our company will flexible support candidates for online interview\n•	Mon - Fri\n','•	Have 1 - 2 years of experience coding Flutter.\n•	Experience working with Bloc/RxDart.\n•	Know some design patterns (singleton, factory, data repository..).\n•	Enthusiasm for technology with a pulse on current trends and technologies in the area of mobile application development.\n•	Experience with Android (kotlin) or IOS (swift)  is a plus.\n•	Experience with and understanding of test-driven design and unit testing is a plus.\n•	Agile/Scrum experience is a plus.\n\n',0,0,'2023-05-22 04:43:16',16,1,8,NULL);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_care`
--

DROP TABLE IF EXISTS `job_care`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_care` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `candidate_id` bigint DEFAULT NULL,
  `job_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpmsbcidkolvoyp0bbwhjffn92` (`candidate_id`),
  KEY `FKe70kiyobn9spaeatmaojwctwf` (`job_id`),
  CONSTRAINT `FKe70kiyobn9spaeatmaojwctwf` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `FKpmsbcidkolvoyp0bbwhjffn92` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_care`
--

LOCK TABLES `job_care` WRITE;
/*!40000 ALTER TABLE `job_care` DISABLE KEYS */;
INSERT INTO `job_care` VALUES (27,10,1),(32,12,2),(33,12,1);
/*!40000 ALTER TABLE `job_care` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_major`
--

DROP TABLE IF EXISTS `job_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_major` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_id` bigint DEFAULT NULL,
  `major_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK895worun2beya55tb067vpsaa` (`job_id`),
  KEY `FK8jjtmopcpxh2knpkgmgusohr` (`major_id`),
  CONSTRAINT `FK895worun2beya55tb067vpsaa` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `FK8jjtmopcpxh2knpkgmgusohr` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_major`
--

LOCK TABLES `job_major` WRITE;
/*!40000 ALTER TABLE `job_major` DISABLE KEYS */;
INSERT INTO `job_major` VALUES (16,14,2),(17,15,1),(18,16,6),(19,17,2),(20,18,1),(21,19,2),(22,20,2),(23,21,2),(24,22,1),(25,23,2),(26,24,3),(27,1,1),(28,1,2),(29,1,3),(30,2,1),(31,2,2);
/*!40000 ALTER TABLE `job_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_position`
--

DROP TABLE IF EXISTS `job_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_position` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_id` bigint DEFAULT NULL,
  `position_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe3n7wgau21gol6ind1uou3nv6` (`job_id`),
  KEY `FKidhjuhgrpaedadid60xsbfolp` (`position_id`),
  CONSTRAINT `FKe3n7wgau21gol6ind1uou3nv6` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `FKidhjuhgrpaedadid60xsbfolp` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_position`
--

LOCK TABLES `job_position` WRITE;
/*!40000 ALTER TABLE `job_position` DISABLE KEYS */;
INSERT INTO `job_position` VALUES (18,14,1),(19,15,1),(20,16,6),(21,17,4),(22,18,4),(23,19,4),(24,20,2),(25,21,2),(26,22,2),(27,23,2),(28,24,2),(29,1,1),(30,1,2),(31,2,1);
/*!40000 ALTER TABLE `job_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_schedule`
--

DROP TABLE IF EXISTS `job_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_id` bigint DEFAULT NULL,
  `schedule_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpnk3sbu1la3t0rpqgqslstvyo` (`job_id`),
  KEY `FKb5pwufeak78wmvkml7jpbna4r` (`schedule_id`),
  CONSTRAINT `FKb5pwufeak78wmvkml7jpbna4r` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FKpnk3sbu1la3t0rpqgqslstvyo` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_schedule`
--

LOCK TABLES `job_schedule` WRITE;
/*!40000 ALTER TABLE `job_schedule` DISABLE KEYS */;
INSERT INTO `job_schedule` VALUES (16,14,1),(17,15,1),(18,16,1),(19,17,1),(20,18,1),(21,19,1),(22,20,1),(23,21,1),(24,22,1),(25,23,1),(26,24,1),(27,1,1),(28,1,2),(29,2,1),(30,2,3);
/*!40000 ALTER TABLE `job_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oi0ctjbjvktdcfxws9w2exiwb` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Khoa học máy tính',NULL,NULL),(2,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Công nghệ phần mềm',NULL,NULL),(3,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Kỹ thuật máy tính',NULL,NULL),(4,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Trí tuệ nhân tạo',NULL,NULL),(5,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Kỹ thuật mạng',NULL,NULL),(6,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Hệ thống quản lý thông tin',NULL,NULL);
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner`
--

DROP TABLE IF EXISTS `partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partner` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `position` varchar(255) NOT NULL,
  `university_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpeenpsvh7tju6qw0d3eu3jk4n` (`university_id`),
  KEY `FKp7w2lupjip7f9de1891xnl612` (`user_id`),
  CONSTRAINT `FKp7w2lupjip7f9de1891xnl612` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKpeenpsvh7tju6qw0d3eu3jk4n` FOREIGN KEY (`university_id`) REFERENCES `university` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner`
--

LOCK TABLES `partner` WRITE;
/*!40000 ALTER TABLE `partner` DISABLE KEYS */;
INSERT INTO `partner` VALUES (1,NULL,'2023-04-05 13:57:25',NULL,'2023-04-05 13:57:16','Trợ lý đào tạo',1,1,NULL,NULL),(2,NULL,'2023-04-07 09:28:15',NULL,'2023-04-07 09:28:16','Trợ lý đào tạo',1,2,NULL,NULL);
/*!40000 ALTER TABLE `partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qe48lxuex3swuovou3giy8qpk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Backend Developer',NULL,NULL),(2,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Frontend Developer',NULL,NULL),(3,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Business Analyst',NULL,NULL),(4,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Tester',NULL,NULL),(5,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','DevOps',NULL,NULL),(6,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Data Engineer',NULL,NULL),(7,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Data Scientist',NULL,NULL);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,'2023-05-10 02:15:49',NULL,'2023-05-10 02:15:49','Role_Admin',NULL,NULL),(2,NULL,'2023-05-10 02:15:49',NULL,'2023-05-10 02:15:49','Role_Candidate',NULL,NULL),(3,NULL,'2023-05-10 02:15:49',NULL,'2023-05-10 02:15:49','Role_HR',NULL,NULL),(4,NULL,'2023-05-10 02:15:49',NULL,'2023-05-10 02:15:49','Role_Partner',NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bs5r9n70832hqbegm4c0tpqos` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Full time',NULL,NULL),(2,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Part time',NULL,NULL),(3,NULL,'2023-03-09 15:16:39',NULL,'2023-03-09 15:16:39','Remote',NULL,NULL);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_reccgx9nr0a8dwv201t44l6pd` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,NULL,'2023-05-10 02:15:49',NULL,'2023-05-10 02:15:49','Active',NULL,NULL),(2,NULL,'2023-05-10 02:15:49',NULL,'2023-05-10 02:15:49','Not Active',NULL,NULL),(3,NULL,'2023-05-10 02:15:49',NULL,'2023-05-10 02:15:49','Lock',NULL,NULL),(4,NULL,'2023-05-10 02:15:49',NULL,'2023-05-10 02:15:49','Disable',NULL,NULL),(5,NULL,'2023-05-10 02:15:49',NULL,'2023-05-10 02:15:49','Delete',NULL,NULL);
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `university` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `description` varchar(5000) NOT NULL,
  `email` varchar(255) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `short_name` varchar(255) NOT NULL,
  `website` varchar(255) NOT NULL,
  `status_id` int NOT NULL,
  `university_type_id` int NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ru212k5vib3yvu360fuy3h1g5` (`name`),
  KEY `FK13b6987kku9ggha244kqhhn17` (`status_id`),
  KEY `FKrup8sayw7lvyhnw8gi8ibebpy` (`university_type_id`),
  CONSTRAINT `FK13b6987kku9ggha244kqhhn17` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKrup8sayw7lvyhnw8gi8ibebpy` FOREIGN KEY (`university_type_id`) REFERENCES `university_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
INSERT INTO `university` VALUES (1,NULL,'2023-04-05 13:50:53',NULL,'2023-04-05 13:50:56','null','đại học','daihocsaigon@sgu.edu.vn','2','Đại Học Sài Gòn','(84-28) 38.3544','SGU','',1,1,NULL,NULL),(2,NULL,'2023-04-07 09:19:14',NULL,'2023-04-07 09:19:16','null','đại học','tonducthanguniversity@tdtu.edu.vn','5','Đại Học Tôn Đức Thắng',' (028) 37 755 0','TDT','https://tdtu.edu.vn',1,1,NULL,NULL);
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university_type`
--

DROP TABLE IF EXISTS `university_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `university_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c7c48umo2p1yk7sitkv2q1190` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university_type`
--

LOCK TABLES `university_type` WRITE;
/*!40000 ALTER TABLE `university_type` DISABLE KEYS */;
INSERT INTO `university_type` VALUES (1,NULL,NULL,NULL,NULL,'Đại Học',NULL,NULL),(2,NULL,NULL,NULL,NULL,'Cao Đẳng',NULL,NULL),(3,NULL,NULL,NULL,NULL,'Trung Cấp',NULL,NULL);
/*!40000 ALTER TABLE `university_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `auth_provider` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `password_forgot_token` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `token_active` varchar(255) DEFAULT NULL,
  `role_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_by_user_id` bigint DEFAULT NULL,
  `last_modified_by_user_id` bigint DEFAULT NULL,
  `mail_receive` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  KEY `FKr62indkt0r2anb0m8hy5ldfpd` (`status_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKr62indkt0r2anb0m8hy5ldfpd` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'2023-03-10 11:19:06',NULL,'2023-03-10 11:19:06',NULL,'',NULL,'tranquoc@sgu.edu.vn','Quoc',_binary '\0','Tran','2','$2a$10$m57vW.QTV8e.4bFbz8/6EO40BD1/rz0vJ13R.4YyrrkyUTjufuC7.',NULL,'1234567890',NULL,4,1,NULL,NULL,_binary ''),(2,NULL,'2023-03-10 11:21:02',NULL,'2023-03-10 11:21:02',NULL,'',NULL,'tranglinh@tdt.edu.vn','Linh',_binary '\0','Nguyen Trang','2','$2a$10$m57vW.QTV8e.4bFbz8/6EO40BD1/rz0vJ13R.4YyrrkyUTjufuC7.',NULL,'1234567890',NULL,4,1,NULL,NULL,_binary ''),(3,NULL,'2023-03-10 11:31:40',NULL,'2023-03-10 11:31:40',NULL,'',NULL,'tranglinhhr@gmail.com','linh',_binary '','nguyen trang','2','$2a$10$m57vW.QTV8e.4bFbz8/6EO40BD1/rz0vJ13R.4YyrrkyUTjufuC7.',NULL,'1234567890',NULL,1,1,NULL,NULL,_binary ''),(4,NULL,'2023-03-10 11:32:35',NULL,'2023-03-10 11:32:35',NULL,'',NULL,'gsbcnbcccsbcabbbbrnyfbbszsssssknnmslzcs2gggsce3s@sssgmail.com','quoc',NULL,'tran','2','$2a$10$p9ezyBdVIffRyOJJyv2V3.NG9yzgmmhMykpjClNJzJZXsNu1k5F/y',NULL,'1234567890',NULL,3,2,NULL,NULL,_binary ''),(5,NULL,'2023-03-10 13:31:02',NULL,NULL,'GOOGLE','https://lh3.googleusercontent.com/a/AGNmyxZQPJ8fPpvO3JbZfN6yEYS1Ox8KJ_CurHJ41EUCkQ=s96-c',NULL,'Thiện',NULL,_binary '','$2a$10$FlBbZyv61hlYcYsK8YqnXu.m3DFDd3j/VqRl7I8lo23MMwp2t3BkW','2','',NULL,'269.tdd@gmail.com0706b601-c52a-40a8-b176-fd3b49410964',NULL,3,1,NULL,NULL,_binary ''),(6,NULL,'2023-03-10 13:29:26',NULL,'0000-00-00 00:00:00',NULL,'',NULL,'gsbcnbcccsbcabbbbrnyfbbszsssssknnmslzcss2gggsce3s@sssgmail.com','quoc',NULL,'tran','5','$2a$10$Dkzg9KzRyxQNvSEKYeAht.3BwLNYrp8xce9MQp8ru4ICeKdzuaQP6',NULL,NULL,NULL,3,2,NULL,NULL,_binary ''),(7,NULL,'2023-04-06 22:01:32',NULL,'2023-04-07 11:07:42','GOOGLE','https://lh3.googleusercontent.com/a/AGNmyxYGKqRu90QBFntTWyUWU1k324VNyM-91lONCUTW=s96-c','2023-04-17 00:00:00','tuenguyen553@gmail.com','Nguyen',_binary '\0','Tue','5','$2a$10$3qyrbt.4Tyoi0vNcw64RbeZ6BnV9Ax76JDzwQjP.6C72IDhLeWGgm','aec2825f-d481-447b-9dfb-ed797f83e287%2023-03-27%14:48:15','0332547834','',3,1,NULL,NULL,_binary ''),(8,NULL,'2023-03-13 18:31:46',NULL,'2023-05-19 14:56:15',NULL,'1684482973390_1. Phieu danh gia TTDN.docx','2000-11-13 07:00:00','nguyentranglinh1311@gmail.com','Trang Linh update 2',_binary '','Nguyen updateTest',' Gò Vấp Hồ Chí Minh Update','$2a$10$pGjAhdUzy/nt6VfF2hpZPeQfJxq1OTvuyL3wSdBZbWyks3ByheWJy',NULL,'1234567',NULL,3,1,NULL,8,_binary ''),(9,NULL,'2023-04-07 09:07:49',NULL,NULL,'GOOGLE','https://lh3.googleusercontent.com/a/AGNmyxb09pN5JlJmgBOCs7gKTL0oTHlaE3jStqiFfKnC=s96-c',NULL,'tue.nguyen2910@hcmut.edu.vn','Nguyễn Chí',NULL,'Tuệ','2','$2a$10$grZSKuYv9y3iozvXuxbLcuMKOXdHDVb06DgawvIhVEWwWObZkPOXS',NULL,NULL,NULL,3,1,NULL,NULL,_binary ''),(10,NULL,'2023-03-15 10:31:10',NULL,'2023-03-15 10:31:10',NULL,'',NULL,'tungngo256@gmail.com','Tùng',NULL,'Nguyễn Hữu Thanh','5','$2a$10$/ylCZxUGv/jP8HoTKhtTiekkftfmPTba0OP0L6.xPmjQ1yJoD0.Dy','c1945f2e-9e30-4cd0-bd6e-ec8233000036%2023-04-05%05:41:55','11111111','',3,1,NULL,NULL,_binary ''),(11,NULL,'2023-03-16 13:24:31',NULL,'2023-03-16 13:24:31',NULL,NULL,NULL,'tuenguyen554@gmail.com','Tue',NULL,'Nguyen chi','5','$2a$10$mULFSE0oVQqO8iYValnMt.7plPYgjn7I6plrYjmT5qp8qbmszaX.W','03e723db-275a-4321-ac44-0836fc3fc78e%2023-04-04%03:37:46','0332547834','',3,1,NULL,NULL,_binary ''),(12,NULL,'2023-03-17 13:25:11',NULL,'2023-03-17 13:25:11',NULL,NULL,NULL,'liemht@gmail.com','Liêm',NULL,'Hà Thanh','2','$2a$10$vO237BsvQ85Rk6aEBSv8HurJ2hSzg1g0yDiQvoi3u/m7oKeF9V8v6',NULL,'0332547834','',3,1,NULL,NULL,_binary ''),(13,NULL,'2023-03-27 09:33:31',NULL,'2023-03-27 09:33:31',NULL,NULL,NULL,'tue.nguyen2911@hcmut.edu.vn','tue',NULL,'nguyen chi','5','$2a$10$eMwHly6Yj.kn0Cdq28cmD.fr3/2UuLtpoVJ9Fu7ziQxngqmavIApW',NULL,'0332547834','91e08110-dde0-45d2-be76-e9bce4e2607f',3,2,NULL,NULL,_binary ''),(14,NULL,'2023-04-03 13:53:51',NULL,'2023-04-07 11:30:37',NULL,NULL,'1994-05-05 00:00:00','tranvanquocsgu@gmail.com','quoc',_binary '\0','tran','2','$2a$10$m57vW.QTV8e.4bFbz8/6EO40BD1/rz0vJ13R.4YyrrkyUTjufuC7.','954f34cc-8aea-4745-a3d0-5fa92083a2b9%2023-04-04%04:24:23',NULL,'',3,1,NULL,NULL,_binary ''),(15,NULL,'2023-04-04 02:55:01',NULL,'2023-04-06 03:23:13',NULL,NULL,'2023-03-26 00:00:00','tuthanhkhuong@gmail.com','Linh',_binary '\0','Mỹ','5','$2a$10$3qyrbt.4Tyoi0vNcw64RbeZ6BnV9Ax76JDzwQjP.6C72IDhLeWGgm','ad10bcff-3b4b-402c-ba0f-7364f6b50bb6%2023-04-06%04:01:39','0392364909','8b02dcd7-43c0-42c4-b07c-ba7e904f4116',3,1,NULL,NULL,_binary ''),(16,NULL,'2023-04-05 05:44:30',NULL,'2023-04-07 10:52:04',NULL,'https://firebasestorage.googleapis.com/v0/b/storageimg-36153.appspot.com/o/images%2F1ce55f3a-ba0d-4ddd-b7a6-d45960bb73e4.png?alt=media','1994-05-05 00:00:00','quoc','0',_binary '','$2a$10$z.N2Aq7y6skAV3JBz4XSZOze2O6YOGp9FEs0abg8oaMjprAkB7ue6','2','',NULL,'tungngo236@gmail.com','e04ab9b1-fec5-4000-9e21-fd115a080a65',3,1,NULL,NULL,_binary ''),(17,NULL,'2023-04-06 04:00:29',NULL,'2023-04-06 04:00:29',NULL,NULL,NULL,'tranglinh@gmail.com','Linh',NULL,'Trang','5','$2a$10$T.TNVFnge1dv6VS57Y.CbO7VapODUotwMDz11rC/7Zyw9oIez50Ey',NULL,'0392364909','9482429a-6dae-4018-a690-0ecc93fd0a51',3,1,NULL,NULL,_binary ''),(18,NULL,'2023-04-07 08:59:46',NULL,NULL,'GOOGLE','https://lh3.googleusercontent.com/a/AGNmyxbIAaZ3cxQLEVlGZDAdGW7Y4RZzp1xvoYDbW4ZF=s96-c',NULL,'trkien22@gmail.com','Trung',NULL,'Kiên','2','$2a$10$PvS35EMplXHtseWydGenqOwW0PYMjlc5S.U8VYPXsLZ8Nsfq6NTA6',NULL,NULL,NULL,3,1,NULL,NULL,_binary ''),(19,NULL,'2023-04-06 22:02:14',NULL,'2023-04-06 22:02:14',NULL,NULL,NULL,'vanhau27062001@gmail.com','Hau',NULL,'nv','5','$2a$10$jCBMcf325K7twOoVEtSX9OAg2n9cPGk3xoX0jIbQwt06jx0oL4Q2q',NULL,'0915568232','dc04cce8-b78e-43b1-829a-e41200bd89fc',3,2,NULL,NULL,_binary ''),(20,NULL,'2023-04-06 22:26:23',NULL,'2023-04-06 22:26:23',NULL,NULL,NULL,'tranuy@gmail.com','Uy',NULL,'Trần Đức','2','$2a$10$kPf9Y1.zhfTtZY28nR95leXY8kVrAlNF8lfExFBSGcYqxALfHrBlG',NULL,'0983269424','d10aae51-d5a7-4f0a-9d15-dd0fe104e7f4',3,1,NULL,NULL,_binary ''),(21,NULL,'2023-05-16 11:34:45',NULL,'2023-05-17 15:27:04',NULL,'1684312023789_DeCuongThucTap_ThienThu.docx','2001-04-18 07:00:00','candidateUpdateTest@gmail.com','Candidate update',_binary '\0','Nguyen Van','123, PVD','$2a$10$3O3Fq29W8mLu40SkcEOvKuCrWrSigrLHmihu.v8j0WH4qOVounpsG',NULL,'0912345688',NULL,2,2,NULL,24,_binary ''),(22,NULL,'2023-05-16 13:16:12',NULL,'2023-05-16 13:16:12',NULL,NULL,NULL,'candidate01@gmail.com','Candidate',NULL,'Nguyễn Văn',NULL,'$2a$10$NVLRFEmh/p2Zj4vo95I5SufbmCU3.4cZ1Yi/bnMfJNjKNyATXk5gu',NULL,'0912345688',NULL,2,2,NULL,NULL,_binary ''),(23,NULL,'2023-05-16 15:05:10',NULL,'2023-05-16 15:05:10',NULL,NULL,NULL,'candidate10@gmail.com','Candidate',NULL,'Nguyễn Văn',NULL,'$2a$10$kxkjOkc4.ExlmGE5ZuXHKunnbu1ewx37DlUzIrc.UsFWLhuAOX3wu',NULL,'0912345688',NULL,2,1,NULL,NULL,_binary ''),(24,NULL,'2023-05-17 15:14:39',NULL,'2023-05-18 10:22:49',NULL,'1684380167360_DeCuongThucTap_ThienThu.docx','2001-04-18 07:00:00','vynguyen23200137a@gmail.com','Vy',_binary '\0','Nguyen Minh','123, PVD','$2a$10$9tLXx9baF0D9Hio3UMU7xOfnBXDVZ2TZmkpeNMAaFt2VKyCAW3y4i',NULL,'0912345688',NULL,2,2,NULL,28,_binary ''),(25,NULL,'2023-05-18 10:17:21',NULL,'2023-05-18 10:17:21',NULL,NULL,NULL,'candidateDemo@gmail.com','Candidate',NULL,'Nguyễn Văn',NULL,'$2a$10$UzVJ2YsnxJ.QPmrXr1cBme7R/gLVOEVDR9DIcC1iYcJdUU1/b1wfi',NULL,'0912345688',NULL,2,2,NULL,NULL,_binary ''),(26,NULL,'2023-05-18 10:18:05',NULL,'2023-05-18 10:18:05',NULL,NULL,NULL,'thienthuphilieng2@gmail.com@gmail.com','Candidate',NULL,'Nguyễn Văn',NULL,'$2a$10$yHsmGOwm9stMMWospj.5JuG2Qp4eMVcbHdnvha10F8jd29f5A5L.S',NULL,'0912345688',NULL,2,2,NULL,NULL,_binary ''),(27,NULL,'2023-05-18 10:18:18',NULL,'2023-05-18 10:18:18',NULL,NULL,NULL,'thienthuphilieng2@gmail.com','Candidate',NULL,'Nguyễn Văn',NULL,'$2a$10$tGqta/AqyoRJHa333ofZBudbP.XKAchISRcK7Cf.aj9xGscJ.r9iS',NULL,'0912345688',NULL,2,2,NULL,NULL,_binary ''),(28,NULL,'2023-05-18 10:19:51',NULL,'2023-05-25 09:40:49',NULL,'1684982448973_NguyenThiThienThu_JavaDev.pdf','2001-04-18 07:00:00','vynguyen23200137aa@gmail.com','Vy',_binary '\0','Nguyen Minh','123, PVD','$2a$10$dkczIjBwxFHLcJs/0E8RP.15oF0VTq73gH6M2jChYlwjMCkh8a0e6',NULL,'0912345688',NULL,2,1,NULL,28,_binary ''),(29,NULL,'2023-05-25 09:49:51',NULL,'2023-05-25 09:49:51',NULL,NULL,'2001-04-18 07:00:00','thienthuphilieng2@@gmail.com','Candidate',_binary '','Nguyễn Văn','300 Phạm Văn Đồng,quận Gò Vấp, Thành phố Hồ Chí Minh','$2a$10$20p08fbPFsCL2yASc6g7yO/hPTC8pvCQNsL8qCbd8T1n8EwmUzoZ2',NULL,'0912345688',NULL,2,2,NULL,NULL,_binary ''),(30,NULL,'2023-05-25 09:51:21',NULL,'2023-05-25 10:19:35',NULL,'1684983796935_NguyenThiThienThu_JavaDev.pdf','2001-04-18 07:00:00','vynguyen23200137@@gmail.com','Vy',_binary '\0','Nguyen Minh','123, PVD','$2a$10$YlaP737RHFEjqHlO1e1dmO9yfVKwFlqlK0.mJN2H2FVPg7PJV9aOq',NULL,'0912345688',NULL,2,1,NULL,30,_binary ''),(31,NULL,'2023-05-25 10:25:21',NULL,'2023-05-25 10:32:47','GOOGLE','https://lh3.googleusercontent.com/a/AAcHTtdTZAL5ywu0ejQdMHaEmpJYHYJLIcYgPALpO5bF_gY=s96-c',NULL,'vynguyen23200137@gmail.com','Minh Vy',NULL,'Nguyễn','vi','$2a$10$MJYcjqoiut/ASFLaTWgv0.TYpGoMYuZvCKH6rrWQ7EqDMVZY4PNFm',NULL,NULL,NULL,2,1,NULL,NULL,_binary ''),(32,NULL,'2023-05-25 10:35:17',NULL,'2023-05-25 10:35:17','Google','https://lh3.googleusercontent.com/a/AAcHTtdunj8zYF1lVkvBr7fYGYn2kIytGjvWVeDTK3eK=s96-c',NULL,'thienthuphilieng@gmail.com','Nguyễn Thị Thiên',NULL,'Thu','vi','$2a$10$vUZQbmDKh1blyMLXHCXCJ.VdpcWW8ZvZZ99BlUb9VlcGObo0li0p6',NULL,NULL,NULL,2,1,NULL,NULL,_binary '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'developer_internship_new'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-29  9:01:56
