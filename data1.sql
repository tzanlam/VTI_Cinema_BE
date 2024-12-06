-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: vti_cinema
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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `gender` enum('FEMALE','MALE') DEFAULT NULL,
  `passport` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','MANAGER','USER') NOT NULL,
  `status` enum('ACTIVE','BLOCK','INACTIVE','PENDING') NOT NULL,
  `username` varchar(255) NOT NULL,
  `verification_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKq0uja26qgu1atulenwup9rxyr` (`email`),
  UNIQUE KEY `UKgex1lmaqpg0ir5g1f5eftyaa1` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,'hoanglee@gmail.com','hoanglee',NULL,NULL,'$2a$12$Hox62FNf5gqnYu5PYe/75OfBo/1xEiW8P5InyhVgV55u/GcyyylNy',NULL,'ADMIN','ACTIVE','hoanglee',NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeret8hxnypn4cdbiojqc5801e` (`movie_id`),
  CONSTRAINT `FKeret8hxnypn4cdbiojqc5801e` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1,NULL,NULL,'Exclusive Offer on Tickets!','https://example.com/banner1.jpg','INACTIVE','Blockbuster Movie 1',1),(2,NULL,NULL,'Special Discount on Popcorn!','https://example.com/banner2.jpg','INACTIVE','Blockbuster Movie 2',2),(3,NULL,NULL,'Enjoy a great time with family!','https://example.com/banner3.jpg','INACTIVE','Family Movie Night',3),(4,NULL,NULL,'Catch the biggest movies of the summer.','https://example.com/banner4.jpg','INACTIVE','Summer Blockbusters',4),(5,NULL,NULL,'An entire day of adrenaline-packed action!','https://example.com/banner5.jpg','INACTIVE','Action Movie Marathon',5),(6,NULL,NULL,'Laugh out loud with the best comedies.','https://example.com/banner6.jpg','INACTIVE','Comedy Night',6),(7,NULL,NULL,'A perfect night for lovers.','https://example.com/banner7.jpg','INACTIVE','Romantic Movies',7),(8,NULL,NULL,'Get ready for a spine-chilling experience!','https://example.com/banner8.jpg','INACTIVE','Horror Night',8),(9,NULL,NULL,'Fun for kids and the young at heart!','https://example.com/banner9.jpg','INACTIVE','Animated Movies',9),(10,NULL,NULL,'Discover unique and compelling indie films.','https://example.com/banner10.jpg','INACTIVE','Indie Films Showcase',10);
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `expiry_time` datetime(6) DEFAULT NULL,
  `total_price` double NOT NULL,
  `status` enum('CANCELLED','PENDING','SUCCESS') NOT NULL,
  `account_id` int DEFAULT NULL,
  `more_service_id` int DEFAULT NULL,
  `payment_id` int DEFAULT NULL,
  `voucher_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7hunottedmjhtdcvhv4sx6x4a` (`account_id`),
  KEY `FKnf22nvau3xj8uvpt2xcxm1nlh` (`more_service_id`),
  KEY `FK70t92vvx289ayx2hq2v4hdcjl` (`payment_id`),
  KEY `FKbs6twtq6v6sobgvl1gt6v1lan` (`voucher_id`),
  CONSTRAINT `FK70t92vvx289ayx2hq2v4hdcjl` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`),
  CONSTRAINT `FK7hunottedmjhtdcvhv4sx6x4a` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKbs6twtq6v6sobgvl1gt6v1lan` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`),
  CONSTRAINT `FKnf22nvau3xj8uvpt2xcxm1nlh` FOREIGN KEY (`more_service_id`) REFERENCES `more_service` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cinema`
--

DROP TABLE IF EXISTS `cinema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinema` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `location` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` enum('BUILDING','CLOSED','OPEN') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKoghcyu0s7am42jr8tbyux8csp` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinema`
--

LOCK TABLES `cinema` WRITE;
/*!40000 ALTER TABLE `cinema` DISABLE KEYS */;
INSERT INTO `cinema` VALUES (1,NULL,NULL,'Location A','Cinema A','OPEN'),(2,NULL,NULL,'Location B','Cinema B','CLOSED'),(3,NULL,NULL,'Location C','Cinema C','BUILDING'),(4,NULL,NULL,'Location D','Cinema D','OPEN'),(5,NULL,NULL,'Location E','Cinema E','CLOSED'),(6,NULL,NULL,'Location F','Cinema F','BUILDING'),(7,NULL,NULL,'Location G','Cinema G','OPEN'),(8,NULL,NULL,'Location H','Cinema H','CLOSED'),(9,NULL,NULL,'Location I','Cinema I','BUILDING'),(10,NULL,NULL,'Location J','Cinema J','OPEN');
/*!40000 ALTER TABLE `cinema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cinema_images`
--

DROP TABLE IF EXISTS `cinema_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinema_images` (
  `cinema_id` int NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  KEY `FKme2kgva34eerb0tv0bruv7lmo` (`cinema_id`),
  CONSTRAINT `FKme2kgva34eerb0tv0bruv7lmo` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinema_images`
--

LOCK TABLES `cinema_images` WRITE;
/*!40000 ALTER TABLE `cinema_images` DISABLE KEYS */;
INSERT INTO `cinema_images` VALUES (1,'image1.jpg'),(1,'image2.jpg'),(2,'image3.jpg'),(2,'image4.jpg'),(3,'image5.jpg'),(3,'image6.jpg'),(4,'image7.jpg'),(4,'image8.jpg'),(5,'image9.jpg'),(5,'image10.jpg'),(1,'image1.jpg'),(1,'image2.jpg'),(2,'image3.jpg'),(2,'image4.jpg'),(3,'image5.jpg'),(3,'image6.jpg'),(4,'image7.jpg'),(4,'image8.jpg'),(5,'image9.jpg'),(5,'image10.jpg');
/*!40000 ALTER TABLE `cinema_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_start_time`
--

DROP TABLE IF EXISTS `list_start_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `list_start_time` (
  `showtime_id` int NOT NULL,
  `start_time` time(6) NOT NULL,
  KEY `FKaenvtrs3pd72eyy10i28qghox` (`showtime_id`),
  CONSTRAINT `FKaenvtrs3pd72eyy10i28qghox` FOREIGN KEY (`showtime_id`) REFERENCES `show_time` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_start_time`
--

LOCK TABLES `list_start_time` WRITE;
/*!40000 ALTER TABLE `list_start_time` DISABLE KEYS */;
INSERT INTO `list_start_time` VALUES (1,'10:00:00.000000'),(1,'13:00:00.000000'),(1,'16:00:00.000000'),(2,'12:00:00.000000'),(2,'15:00:00.000000'),(2,'18:00:00.000000'),(3,'14:00:00.000000'),(3,'17:00:00.000000'),(3,'20:00:00.000000'),(4,'11:00:00.000000'),(4,'14:30:00.000000'),(4,'18:30:00.000000'),(5,'13:00:00.000000'),(5,'16:00:00.000000'),(5,'19:00:00.000000');
/*!40000 ALTER TABLE `list_start_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `more_service`
--

DROP TABLE IF EXISTS `more_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `more_service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `decription` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `status` enum('ACTIVE','AVAILABLE','INACTIVE','OUT_OF_STOCK') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `more_service`
--

LOCK TABLES `more_service` WRITE;
/*!40000 ALTER TABLE `more_service` DISABLE KEYS */;
INSERT INTO `more_service` VALUES (1,NULL,NULL,'Large size butter popcorn.','popcorn.jpg','Popcorn',5,'AVAILABLE'),(2,NULL,NULL,'Medium soda with choice of flavors.','soda.jpg','Soda',2.5,'OUT_OF_STOCK'),(3,NULL,NULL,'Cheese nachos with salsa.','nachos.jpg','Nachos',4,'ACTIVE'),(4,NULL,NULL,'Classic hot dog with mustard and ketchup.','hotdog.jpg','Hot Dog',3.5,'INACTIVE'),(5,NULL,NULL,'Chocolate and vanilla ice cream.','icecream.jpg','Ice Cream',3,'AVAILABLE');
/*!40000 ALTER TABLE `more_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `actor` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `duration` time(6) NOT NULL,
  `genre` enum('ACTION','COMEDY','DRAMA','HORROR','ROMANCE','SCI_FI') DEFAULT NULL,
  `image_movie` varchar(255) NOT NULL,
  `language` enum('ENGLISH','INDIA','JAPANESE','VIETNAMESE') DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `rating` float DEFAULT NULL,
  `start_date` date NOT NULL,
  `status` enum('CLOSE','COMING_SOON','SHOWING','SPECIAL') DEFAULT NULL,
  `trailer` varchar(255) NOT NULL,
  `viewing_age` enum('CHILD','G','PG','PG13','R','T15','T18') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'2024-12-05 21:24:30.814767','2024-12-05 21:24:30.814767','John Doe, Jane Smith','A high-speed action thriller.','Michael Bay','02:15:00.000000','ACTION','action_movie.jpg','ENGLISH','Fast Chase',8.5,'2024-12-15','CLOSE','trailer_fast_chase.mp4','PG13'),(2,NULL,NULL,'John Doe, Jane Smith','A high-speed action thriller.','Michael Bay','02:15:00.000000','ACTION','https://example.com/images/action_movie.jpg','ENGLISH','Fast Chase',8.5,'2024-12-15',NULL,'trailer_fast_chase.mp4','PG13'),(3,NULL,NULL,'Emma Stone, Ryan Gosling','A timeless love story.','Richard Curtis','01:50:00.000000','ROMANCE','https://example.com/images/romance_movie.jpg','VIETNAMESE','Love Beyond Time',7.8,'2024-11-10',NULL,'trailer_love_beyond_time.mp4','PG'),(4,NULL,NULL,'Unknown Cast','A haunting tale of survival.','James Wan','01:30:00.000000','HORROR','https://example.com/images/horror_movie.jpg','JAPANESE','Nightmare Alley',6.7,'2024-12-01',NULL,'trailer_nightmare_alley.mp4','R'),(5,NULL,NULL,'Kevin Hart, Tiffany Haddish','A hilarious journey of mishaps.','Todd Phillips','01:45:00.000000','COMEDY','https://example.com/images/comedy_movie.jpg','INDIA','Laugh Out Loud',8,'2024-12-20',NULL,'trailer_laugh_out_loud.mp4','PG13'),(6,NULL,NULL,'Meryl Streep, Denzel Washington','A gripping family drama.','Sam Mendes','02:30:00.000000','DRAMA','https://example.com/images/drama_movie.jpg','VIETNAMESE','The Long Goodbye',9,'2024-10-05',NULL,'trailer_the_long_goodbye.mp4','T15'),(7,NULL,NULL,'Chris Pratt, Zoe Saldana','An intergalactic adventure.','James Cameron','02:20:00.000000','SCI_FI','https://example.com/images/sci_fi_movie.jpg','ENGLISH','Galaxy Wars',7.5,'2024-12-25',NULL,'trailer_galaxy_wars.mp4','PG'),(8,NULL,NULL,'Animated Cast','Fun for the whole family.','Pixar Studios','01:20:00.000000','COMEDY','https://example.com/images/children_movie.jpg','JAPANESE','Cartoon Adventure',8.9,'2024-09-01',NULL,'trailer_cartoon_adventure.mp4','G'),(9,NULL,NULL,'Emily Blunt, John Krasinski','A silent yet deadly mystery.','Jordan Peele','02:10:00.000000','HORROR','https://example.com/images/thriller_movie.jpg','INDIA','Silent Shadows',7.2,'2024-12-08',NULL,'trailer_silent_shadows.mp4','T18'),(10,NULL,NULL,'Tom Cruise, Emily Blunt','A journey through time.','Christopher Nolan','02:40:00.000000','SCI_FI','https://example.com/images/sci_fi_thriller.jpg','ENGLISH','The Time Paradox',9.5,'2024-11-15',NULL,'trailer_time_paradox.mp4','PG13'),(11,NULL,NULL,'Anne Hathaway, Chris Hemsworth','Romantic hilarity ensues.','Nancy Meyers','01:55:00.000000','ROMANCE','https://example.com/images/romantic_comedy.jpg','VIETNAMESE','Love & Laughter',8.3,'2024-12-05',NULL,'trailer_love_laughter.mp4','PG'),(12,NULL,NULL,'John Doe, Jane Smith','A high-speed action thriller.','Michael Bay','02:15:00.000000','ACTION','https://example.com/images/action_movie.jpg','ENGLISH','Fast Chase',8.5,'2024-12-15',NULL,'trailer_fast_chase.mp4','PG13'),(13,NULL,NULL,'Emma Stone, Ryan Gosling','A timeless love story.','Richard Curtis','01:50:00.000000','ROMANCE','https://example.com/images/romance_movie.jpg','VIETNAMESE','Love Beyond Time',7.8,'2024-11-10',NULL,'trailer_love_beyond_time.mp4','PG'),(14,NULL,NULL,'Unknown Cast','A haunting tale of survival.','James Wan','01:30:00.000000','HORROR','https://example.com/images/horror_movie.jpg','JAPANESE','Nightmare Alley',6.7,'2024-12-01',NULL,'trailer_nightmare_alley.mp4','R'),(15,NULL,NULL,'Kevin Hart, Tiffany Haddish','A hilarious journey of mishaps.','Todd Phillips','01:45:00.000000','COMEDY','https://example.com/images/comedy_movie.jpg','INDIA','Laugh Out Loud',8,'2024-12-20',NULL,'trailer_laugh_out_loud.mp4','PG13'),(16,NULL,NULL,'Meryl Streep, Denzel Washington','A gripping family drama.','Sam Mendes','02:30:00.000000','DRAMA','https://example.com/images/drama_movie.jpg','VIETNAMESE','The Long Goodbye',9,'2024-10-05',NULL,'trailer_the_long_goodbye.mp4','T15'),(17,NULL,NULL,'Chris Pratt, Zoe Saldana','An intergalactic adventure.','James Cameron','02:20:00.000000','SCI_FI','https://example.com/images/sci_fi_movie.jpg','ENGLISH','Galaxy Wars',7.5,'2024-12-25',NULL,'trailer_galaxy_wars.mp4','PG'),(18,NULL,NULL,'Animated Cast','Fun for the whole family.','Pixar Studios','01:20:00.000000','COMEDY','https://example.com/images/children_movie.jpg','JAPANESE','Cartoon Adventure',8.9,'2024-09-01',NULL,'trailer_cartoon_adventure.mp4','G'),(19,NULL,NULL,'Emily Blunt, John Krasinski','A silent yet deadly mystery.','Jordan Peele','02:10:00.000000','HORROR','https://example.com/images/thriller_movie.jpg','INDIA','Silent Shadows',7.2,'2024-12-08',NULL,'trailer_silent_shadows.mp4','T18'),(20,NULL,NULL,'Tom Cruise, Emily Blunt','A journey through time.','Christopher Nolan','02:40:00.000000','SCI_FI','https://example.com/images/sci_fi_thriller.jpg','ENGLISH','The Time Paradox',9.5,'2024-11-15',NULL,'trailer_time_paradox.mp4','PG13'),(21,NULL,NULL,'Anne Hathaway, Chris Hemsworth','Romantic hilarity ensues.','Nancy Meyers','01:55:00.000000','ROMANCE','https://example.com/images/romantic_comedy.jpg','VIETNAMESE','Love & Laughter',8.3,'2024-12-05',NULL,'trailer_love_laughter.mp4','PG');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `address_tranfer` varchar(255) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `method` enum('BANKING','E_WALLET') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `amount` double NOT NULL,
  `reason` varchar(255) NOT NULL,
  `status` enum('PROCESSED','UNPROCESSED') DEFAULT NULL,
  `type` enum('INCOME','SPENDING') NOT NULL,
  `account_id` int NOT NULL,
  `booking_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7jbjwo4ybdl7qtjwkp4kitbh0` (`account_id`),
  KEY `FKb2gtyfso0hjq2laud991vt8fo` (`booking_id`),
  CONSTRAINT `FK7jbjwo4ybdl7qtjwkp4kitbh0` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKb2gtyfso0hjq2laud991vt8fo` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `screen_type` enum('IMAX','NORMAL') DEFAULT NULL,
  `status` enum('AVAILABLE','BOOKED','MAINTENANCE') DEFAULT NULL,
  `cinema_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK838jvntrkjvmbpm310wsdad1r` (`cinema_id`),
  CONSTRAINT `FK838jvntrkjvmbpm310wsdad1r` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,NULL,NULL,'Room A','IMAX','AVAILABLE',1),(2,NULL,NULL,'Room B','NORMAL','AVAILABLE',1),(3,NULL,NULL,'Room C','IMAX','AVAILABLE',2),(4,NULL,NULL,'Room D','NORMAL','AVAILABLE',2),(5,NULL,NULL,'Room E','IMAX','AVAILABLE',3),(6,NULL,NULL,'Room F','NORMAL','AVAILABLE',3),(7,NULL,NULL,'Room G','IMAX','AVAILABLE',4),(8,NULL,NULL,'Room H','NORMAL','AVAILABLE',4),(9,NULL,NULL,'Room I','IMAX','AVAILABLE',5),(10,NULL,NULL,'Room J','NORMAL','AVAILABLE',5),(11,NULL,NULL,'Room A','IMAX','AVAILABLE',1),(12,NULL,NULL,'Room B','NORMAL','AVAILABLE',1),(13,NULL,NULL,'Room C','IMAX','AVAILABLE',2),(14,NULL,NULL,'Room D','NORMAL','AVAILABLE',2),(15,NULL,NULL,'Room E','IMAX','AVAILABLE',3),(16,NULL,NULL,'Room F','NORMAL','AVAILABLE',3),(17,NULL,NULL,'Room G','IMAX','AVAILABLE',4),(18,NULL,NULL,'Room H','NORMAL','AVAILABLE',4),(19,NULL,NULL,'Room I','IMAX','AVAILABLE',5),(20,NULL,NULL,'Room J','NORMAL','AVAILABLE',5);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `status` enum('AVAILABLE','BOOKED','OCCUPIED') NOT NULL,
  `seat_type` enum('DOUBLE','SINGLE','VIP') NOT NULL,
  `room_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd7f42843rt05tt66t6vcb7s9u` (`room_id`),
  CONSTRAINT `FKd7f42843rt05tt66t6vcb7s9u` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_time`
--

DROP TABLE IF EXISTS `show_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show_time` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `show_date` date NOT NULL,
  `cinema_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `room_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnoeed8a588r214039fb9btfmf` (`cinema_id`),
  KEY `FK8e72rkmjwjag9nshwu5hvh6b4` (`movie_id`),
  KEY `FK9kvb60sp426s439jb9d6w4nm1` (`room_id`),
  CONSTRAINT `FK8e72rkmjwjag9nshwu5hvh6b4` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `FK9kvb60sp426s439jb9d6w4nm1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `FKnoeed8a588r214039fb9btfmf` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_time`
--

LOCK TABLES `show_time` WRITE;
/*!40000 ALTER TABLE `show_time` DISABLE KEYS */;
INSERT INTO `show_time` VALUES (1,NULL,NULL,'2024-12-05',1,1,1),(2,NULL,NULL,'2024-12-06',1,2,2),(3,NULL,NULL,'2024-12-07',2,3,3),(4,NULL,NULL,'2024-12-08',3,4,4),(5,NULL,NULL,'2024-12-09',4,5,5);
/*!40000 ALTER TABLE `show_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `price` double NOT NULL,
  `status` enum('CANCELLED','PAID','UNPAID') NOT NULL,
  `account_id` int NOT NULL,
  `booking_id` int DEFAULT NULL,
  `seat_id` int NOT NULL,
  `show_time_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdn3ugkxwosr8ksfxcjfow04b0` (`account_id`),
  KEY `FKrg7x158t96nucwslhq2bad6qm` (`booking_id`),
  KEY `FKqahao9a85drt47ikjp0b8syvh` (`seat_id`),
  KEY `FKsbhsari8771yntnvt3x9aws8b` (`show_time_id`),
  CONSTRAINT `FKdn3ugkxwosr8ksfxcjfow04b0` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKqahao9a85drt47ikjp0b8syvh` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`),
  CONSTRAINT `FKrg7x158t96nucwslhq2bad6qm` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`),
  CONSTRAINT `FKsbhsari8771yntnvt3x9aws8b` FOREIGN KEY (`show_time_id`) REFERENCES `show_time` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount` double NOT NULL,
  `expiry` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int DEFAULT NULL,
  `status` enum('EFFECTIVE','EXPIRED') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,NULL,NULL,'50% off on movie tickets',50,'2024-12-31','Discount50',100,'EFFECTIVE'),(2,NULL,NULL,'Free popcorn with movie ticket purchase',0,'2024-12-25','NewYearBonus',50,'EFFECTIVE'),(3,NULL,NULL,'20% off on all snacks',20,'2024-07-31','SummerSale',200,'EXPIRED');
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-05 23:24:54
