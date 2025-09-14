CREATE DATABASE IF NOT EXISTS `proj2_dev` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `proj2_dev`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: proj2_dev
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `activity_type`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `activity_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_type`
--

LOCK TABLES `activity_type` WRITE;
/*!40000 ALTER TABLE `activity_type` DISABLE KEYS */;
INSERT IGNORE INTO `activity_type` VALUES (1,'FITNESS desc','FITNESS',NULL),(2,'CAR desc','CAR',NULL),(3,'HOUSE desc','HOUSE',NULL),(4,'','HEALTH',NULL),(5,'','VACATION',NULL);
/*!40000 ALTER TABLE `activity_type` ENABLE KEYS */;
UNLOCK TABLES;

UPDATE activity_type SET user = 'user' WHERE id IN (1,2,3,4,5) AND user IS NULL;

--
-- Table structure for table `activity`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_sub_type` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `activitytype_id` bigint DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh1rlxay4sp8hnk87xvfeosqx0` (`activitytype_id`),
  CONSTRAINT `FKh1rlxay4sp8hnk87xvfeosqx0` FOREIGN KEY (`activitytype_id`) REFERENCES `activity_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;

-- FITNESS (id = 1)
INSERT IGNORE INTO `activity` (`id`, `activity_sub_type`, `title`, `activitytype_id`, `user`) VALUES
  (22, 'CARDIO', 'Cycling', 1, 'user'),
  (23, 'CARDIO', 'Swimming', 1, 'user'),
  (24, 'TEAM', 'Basketball', 1, 'user'),
  (25, 'TEAM', 'Volleyball', 1, 'user'),
  (26, 'STRENGTH', 'Bodyweight Training', 1, 'user'),
  (27, 'STRENGTH', 'Deadlifts', 1, 'user'),
  (28, 'HIIT', 'Tabata Workout', 1, 'user'),
  (29, 'HIIT', 'Circuit Training', 1, 'user'),

-- CAR (id = 2)
  (30, 'MAINTENANCE', 'Tire Rotation', 2, 'user'),
  (31, 'MAINTENANCE', 'Replace Air Filter', 2, 'user'),
  (32, 'REPAIR', 'Windshield Crack Fix', 2, 'user'),
  (33, 'REPAIR', 'AC Compressor Replacement', 2, 'user'),
  (34, 'CLEANING', 'Interior Detailing', 2, 'user'),
  (35, 'CLEANING', 'Engine Bay Wash', 2, 'user'),
  (36, 'INSPECTION', 'Emissions Test', 2, 'user'),
  (37, 'INSPECTION', 'Brake Inspection', 2, 'user'),

-- HOUSE (id = 3)
  (38, 'CLEANING', 'Mop Kitchen Floor', 3, 'user'),
  (39, 'CLEANING', 'Dust Shelves', 3, 'user'),
  (40, 'REPAIR', 'Fix Door Hinge', 3, 'user'),
  (41, 'REPAIR', 'Patch Wall Hole', 3, 'user'),
  (42, 'MAINTENANCE', 'Lawn Mowing', 3, 'user'),
  (43, 'MAINTENANCE', 'Gutter Cleaning', 3, 'user'),
  (44, 'DECORATION', 'Paint Bedroom Wall', 3, 'user'),
  (45, 'DECORATION', 'Install Wall Art', 3, 'user'),

-- HEALTH (id = 4)
  (46, 'CHECKUP', 'Vision Test', 4, 'user'),
  (47, 'CHECKUP', 'Cholesterol Screening', 4, 'user'),
  (48, 'WELLNESS', 'Yoga Session', 4, 'user'),
  (49, 'WELLNESS', 'Breathing Exercises', 4, 'user'),
  (50, 'NUTRITION', 'Meal Prep', 4, 'user'),
  (51, 'NUTRITION', 'Calorie Tracking', 4, 'user'),
  (52, 'APPOINTMENT', 'Physiotherapy Visit', 4, 'user'),
  (53, 'APPOINTMENT', 'ENT Consultation', 4, 'user'),

-- VACATION (id = 5)
  (54, 'TRAVEL', 'Train Reservation', 5, 'user'),
  (55, 'TRAVEL', 'Rent a Car', 5, 'user'),
  (56, 'PLANNING', 'Book Hotel', 5, 'user'),
  (57, 'PLANNING', 'Make Packing List', 5, 'user'),
  (58, 'ACTIVITY', 'City Walking Tour', 5, 'user'),
  (59, 'ACTIVITY', 'Boat Ride', 5, 'user'),
  (60, 'RELAXATION', 'Spa Day', 5, 'user'),
  (61, 'RELAXATION', 'Mountain View Picnic', 5, 'user');

 /*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `day_activity`
--

DROP TABLE IF EXISTS `day_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `day_activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_factor` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `logged_reps` varchar(255) DEFAULT NULL,
  `todo_kg` varchar(255) DEFAULT NULL,
  `todo_reps` varchar(255) DEFAULT NULL,
  `todo_sets` varchar(255) DEFAULT NULL,
  `activity_id` bigint DEFAULT NULL,
  `activitytype_id` bigint DEFAULT NULL,
  `is_logged` bit(1) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaq07a4510mjpb7qx0y94s93sh` (`activity_id`),
  KEY `FKemkox7dxhdv7vu5c8d1rwuc44` (`activitytype_id`),
  CONSTRAINT `FKaq07a4510mjpb7qx0y94s93sh` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKemkox7dxhdv7vu5c8d1rwuc44` FOREIGN KEY (`activitytype_id`) REFERENCES `activity_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5012 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



--
-- Dumping data for table `day activity`
--


INSERT IGNORE INTO `day_activity`
(`amount_factor`, `date`, `logged_reps`, `todo_kg`, `todo_reps`, `todo_sets`, `activity_id`, `activitytype_id`, `is_logged`, `user`)
VALUES
(150, '2025-06-01', '12', '40', '10', '4', 26, 1, b'1', 'user'),  -- FITNESS + STRENGTH
(920, '2025-06-01', '10', '70', '8', '3', 27, 1, b'1', 'user'),   -- FITNESS + STRENGTH
(240, '2025-06-01', '9', NULL, NULL, NULL, 23, 1, b'1', 'user'),  -- FITNESS + CARDIO
(305, '2025-06-01', '5', NULL, NULL, NULL, 30, 2, b'1', 'user'),  -- CAR
(410, '2025-06-01', '6', NULL, NULL, NULL, 39, 3, b'1', 'user'),  -- HOUSE
(122, '2025-06-01', NULL, NULL, NULL, NULL, 54, 5, b'0', 'user'), -- VACATION
(700, '2025-06-01', '15', '50', '12', '3', 26, 1, b'1', 'user'),  -- FITNESS + STRENGTH
(310, '2025-06-01', '8', NULL, NULL, NULL, 35, 2, b'1', 'user'),  -- CAR
(501, '2025-06-01', '7', NULL, NULL, NULL, 50, 4, b'1', 'user'),  -- HEALTH
(880, '2025-06-01', NULL, NULL, NULL, NULL, 61, 5, b'0', 'user'); -- VACATION


--
-- Table structure for table `databasechangelog`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `databasechangelog` (
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT IGNORE INTO `databasechangelog` VALUES ('1675895134152-1','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',1,'EXECUTED','8:a0d7751c49aa7b060a12731f443ffd4c','createTable tableName=activity','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-2','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',2,'EXECUTED','8:762ce844739b14998daffae5871c0bfd','createTable tableName=activity_type','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-3','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',3,'EXECUTED','8:d6f26f1ed05aeccb593b889b99870615','createTable tableName=country','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-4','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',4,'EXECUTED','8:55f19bf596b98dd7db8b7031104173fa','createTable tableName=day_activity','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-5','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',5,'EXECUTED','8:645d377511098f8bac950050e3779a77','createTable tableName=department','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-6','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',6,'EXECUTED','8:475b6c70cef560294b290393028f203f','createTable tableName=employee','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-7','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',7,'EXECUTED','8:852d7a02a38ac463c780febdca7c49f1','createTable tableName=global_flag','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-8','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',8,'EXECUTED','8:adc84be73e0eb357193e791048988807','createTable tableName=jhi_authority','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-9','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',9,'EXECUTED','8:9182bf3f10006efe10610f9956c2629d','createTable tableName=jhi_persistent_audit_event','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-10','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',10,'EXECUTED','8:f560bd9fbcb575483eb530bcc6c26421','createTable tableName=jhi_persistent_audit_evt_data','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-11','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',11,'EXECUTED','8:e0d9435f975600207dfd263addfdf769','createTable tableName=jhi_user','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-12','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',12,'EXECUTED','8:e9ede1e0194e29ab4d8d987a26f654ae','createTable tableName=jhi_user_authority','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-13','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',13,'EXECUTED','8:00d8fb6ada2db7d654dd3bbfa54f0457','createTable tableName=job','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-14','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',14,'EXECUTED','8:c38cba5555cfe50f11e5846268d98b04','createTable tableName=job_history','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-15','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',15,'EXECUTED','8:e4fc59c24a61914a98b95d81ce000af2','createTable tableName=job_task','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-16','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',16,'EXECUTED','8:628eab83b1991aed9e44ab999e80b6b4','createTable tableName=location','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-17','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',17,'EXECUTED','8:b4845c2063084c7a83b4e02585190204','createTable tableName=region','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-18','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',18,'EXECUTED','8:5329569bd54f893cc729211c691fb7ac','createTable tableName=session_flag_dates','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-19','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',19,'EXECUTED','8:3678ab391624759249f3002b28a046ad','createTable tableName=task','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-20','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',20,'EXECUTED','8:2a50d66e7626229d8be5b75b15a6bf10','addPrimaryKey constraintName=PRIMARY, tableName=jhi_authority','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-21','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',21,'EXECUTED','8:fafbdb27d8d22f4844574c6dd3ca815e','addPrimaryKey constraintName=PRIMARY, tableName=jhi_persistent_audit_evt_data','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-22','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',22,'EXECUTED','8:a99ddfb332a4181e31365f79eb29dd3a','addPrimaryKey constraintName=PRIMARY, tableName=jhi_user_authority','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-23','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',23,'EXECUTED','8:dbcaf8ef6f0ad7a3fa2396e5c77ffac7','addPrimaryKey constraintName=PRIMARY, tableName=job_task','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-24','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',24,'EXECUTED','8:0704e8937a3077d9591873655c474959','addUniqueConstraint constraintName=UK_2benpqe455foqkfcgi0jjh63e, tableName=job_history','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-25','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',25,'EXECUTED','8:1ade3957e1c2e70b3b829e0102efe4a5','addUniqueConstraint constraintName=UK_9y0frpqnmqe7y6mk109vw3246, tableName=jhi_user','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-26','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',26,'EXECUTED','8:056e3815316ccf676b2ef31d603b5e27','addUniqueConstraint constraintName=UK_buw2l0rwspbnlx3wi4womfo55, tableName=job_history','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-27','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',27,'EXECUTED','8:56e7aa35f38f9b570d48272e76281185','addUniqueConstraint constraintName=UK_bycanyquvi09q7fh5pgxrqnku, tableName=jhi_user','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-28','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',28,'EXECUTED','8:5fe7de15cd8d795a1f591b712d7cab6a','addUniqueConstraint constraintName=UK_e19n7n78ppli0pc6nstem49ya, tableName=department','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-29','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',29,'EXECUTED','8:61b6237cff524df614992316b2f03207','addUniqueConstraint constraintName=UK_e1poa94nbfdncxt7kvt0p340v, tableName=job_history','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-30','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',30,'EXECUTED','8:1f98b4f30e267ba62b38f06713f806e8','addUniqueConstraint constraintName=UK_hc9mjste783w3n9f93e0lf7i4, tableName=country','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-31','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',31,'EXECUTED','8:c029ac96f2448ad200586b9afe48fc6a','addUniqueConstraint constraintName=UK_oij09nrgw3jac87nxirlho8cj, tableName=location','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-32','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',32,'EXECUTED','8:2fabd8dc893e9c3ccee0680a6fe4dd89','createIndex indexName=FK4psxl0jtx6nr7rhqbynr6itoc, tableName=jhi_user_authority','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-33','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',33,'EXECUTED','8:ccb6530f5737a7f0a428dd3b1d5f7e3b','createIndex indexName=FKaq07a4510mjpb7qx0y94s93sh, tableName=day_activity','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-34','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',34,'EXECUTED','8:94280881c81e7fcedcb179a2140035fa','createIndex indexName=FKaqjpcb20qnu30mklmulk7xydt, tableName=job_task','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-35','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:32',35,'EXECUTED','8:fa4cdf6ba56c56cb8f5bfba0db96bf17','createIndex indexName=FKbejtwvg9bxus2mffsm3swj3u9, tableName=employee','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-36','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',36,'EXECUTED','8:07851957ca112f8f501c8bfa747be986','createIndex indexName=FKemkox7dxhdv7vu5c8d1rwuc44, tableName=day_activity','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-37','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',37,'EXECUTED','8:c11a39176a3d5c4a7b9433a2d8eb41d4','createIndex indexName=FKf1r7yjyd8j729jpk3f1dckt68, tableName=session_flag_dates','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-38','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',38,'EXECUTED','8:9a468efff3544dc6c5c402cda2666305','createIndex indexName=FKh1rlxay4sp8hnk87xvfeosqx0, tableName=activity','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-39','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',39,'EXECUTED','8:5645e35738d6c31da61b1f4203aee4ee','createIndex indexName=FKkbfwqadwjxuh6vqgis77q7c7f, tableName=job','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-40','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',40,'EXECUTED','8:6c34529a8e6e61de6ef2a7ce8c12c5ec','createIndex indexName=FKou6wbxug1d0qf9mabut3xqblo, tableName=employee','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-41','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',41,'EXECUTED','8:ac7cc37bd11e3c97da46d326ae31f41b','addForeignKeyConstraint baseTableName=job_history, constraintName=FK1qikydvi1h4879h5mkvpddnu5, referencedTableName=job','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-42','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',42,'EXECUTED','8:feddd62ce57d028b18bf38872042491b','addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=FK290okww5jujghp4el5i7mgwu0, referencedTableName=jhi_user','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-43','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',43,'EXECUTED','8:4686d1b52cc358dbf31eb082fcb74aa3','addForeignKeyConstraint baseTableName=jhi_persistent_audit_evt_data, constraintName=FK2ehnyx2si4tjd2nt4q7y40v8m, referencedTableName=jhi_persistent_audit_event','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-44','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',44,'EXECUTED','8:0f06873801a71befa64bc26792dcfc85','addForeignKeyConstraint baseTableName=job_history, constraintName=FK3j9mpn82on45fsk7ah4tps67o, referencedTableName=employee','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-45','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',45,'EXECUTED','8:48a693748c26e38bf4b0aa6b9fcc1db8','addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=FK4psxl0jtx6nr7rhqbynr6itoc, referencedTableName=jhi_authority','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-46','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',46,'EXECUTED','8:36430f65b2e740f04cfb37b16ed425a3','addForeignKeyConstraint baseTableName=day_activity, constraintName=FKaq07a4510mjpb7qx0y94s93sh, referencedTableName=activity','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-47','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',47,'EXECUTED','8:4a6080b93567f71e006ddfdd6187d86f','addForeignKeyConstraint baseTableName=job_task, constraintName=FKaqjpcb20qnu30mklmulk7xydt, referencedTableName=task','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-48','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',48,'EXECUTED','8:fa046cc3c560f050994dc123d710e0c7','addForeignKeyConstraint baseTableName=employee, constraintName=FKbejtwvg9bxus2mffsm3swj3u9, referencedTableName=department','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-49','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',49,'EXECUTED','8:b431a78d931a6e18fddc18be9834bb4f','addForeignKeyConstraint baseTableName=day_activity, constraintName=FKemkox7dxhdv7vu5c8d1rwuc44, referencedTableName=activity_type','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-50','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',50,'EXECUTED','8:d14c4ec14aefb00b30548476b87b1a1a','addForeignKeyConstraint baseTableName=session_flag_dates, constraintName=FKf1r7yjyd8j729jpk3f1dckt68, referencedTableName=global_flag','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-51','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',51,'EXECUTED','8:80a84d06bd69a149ed9f2893a5f5a758','addForeignKeyConstraint baseTableName=activity, constraintName=FKh1rlxay4sp8hnk87xvfeosqx0, referencedTableName=activity_type','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-52','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',52,'EXECUTED','8:e29b0ae0d8906d1da67532a6035adbd9','addForeignKeyConstraint baseTableName=job_history, constraintName=FKjo9yhubhvo4qefb2t36e619s7, referencedTableName=department','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-53','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',53,'EXECUTED','8:9583398361abb9f4176b5bcaa642d25f','addForeignKeyConstraint baseTableName=job, constraintName=FKkbfwqadwjxuh6vqgis77q7c7f, referencedTableName=employee','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-54','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',54,'EXECUTED','8:3809f0905443dc0f2481c03ad747b829','addForeignKeyConstraint baseTableName=location, constraintName=FKn5m6ve3ryy2r25qvisdrg0aos, referencedTableName=country','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-55','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',55,'EXECUTED','8:0b15fc9b130b2a9d21de43c468fbad0d','addForeignKeyConstraint baseTableName=employee, constraintName=FKou6wbxug1d0qf9mabut3xqblo, referencedTableName=employee','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-56','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',56,'EXECUTED','8:a5fbde5a90a936a9ed5f8c01044d439a','addForeignKeyConstraint baseTableName=job_task, constraintName=FKpdjx6xa2gmqbjxiju8voj0yyi, referencedTableName=job','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-57','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',57,'EXECUTED','8:66c4bb3a41b12daef9a29889cd26d66f','addForeignKeyConstraint baseTableName=department, constraintName=FKrf9pmd4xvkiuh46soainevapk, referencedTableName=location','',NULL,'3.8.9',NULL,NULL,'6065772245'),('1675895134152-58','tioan (generated)','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-02-10 23:49:33',58,'EXECUTED','8:b38f2da09d700b2fb6daa9513a63b838','addForeignKeyConstraint baseTableName=country, constraintName=FKs3bda8801uhqtttuaur9r6eic, referencedTableName=region','',NULL,'3.8.9',NULL,NULL,'6065772245'),('20230102000000-1','tioan','config/liquibase/changelog/20230102000000_added_user_column_to_dayactivity.xml','2023-02-10 23:49:33',59,'EXECUTED','8:1f2ebb8df25d258f3f1327764aa4ffe7','addColumn tableName=day_activity','',NULL,'3.8.9',NULL,NULL,'6065772245'),('20231102000000-1','tioan','config/liquibase/changelog/20231102000000_added_testcolumn_to_region.xml','2023-02-11 00:03:46',60,'EXECUTED','8:8bbedfaec590ced861e15fee767b36e5','addColumn tableName=region','',NULL,'4.3.1',NULL,NULL,'6066626568'),('20232002000000-1','tioan','config/liquibase/changelog/20232002000000_added_user_column_to_activity.xml','2023-02-20 23:50:50',61,'EXECUTED','8:a37814957bea35bcddb00959fff1c495','addColumn tableName=activity','',NULL,'4.3.1',NULL,NULL,'6929850030'),('20232102000000-1','tioan','config/liquibase/changelog/20232102000000_added_user_column_to_global_flags.xml','2023-02-21 23:04:19',62,'EXECUTED','8:3a3ce092f6b83ef7b6253f0245a033b0','addColumn tableName=global_flag','',NULL,'4.3.1',NULL,NULL,'7013459116'),('20230404000000-1','tioan','config/liquibase/changelog/20230404000000_added_timezone_column_to_jhi_user.xml','2023-04-04 22:57:41',63,'EXECUTED','8:b635a95ae2e23dce3aebeeb16972cea5','addColumn tableName=jhi_user','',NULL,'4.3.1',NULL,NULL,'0638261787'),('20231705000000-1','tioan','config/liquibase/changelog/20231705000000_added_user_column_to_activity_type.xml','2023-05-17 22:49:58',64,'EXECUTED','8:8aa9d0306ed0c613649b76f22ae65997','addColumn tableName=activity_type','',NULL,'4.3.1',NULL,NULL,'4352998186'),('20231606000000-1','tioan','config/liquibase/changelog/20231606000000_added_more_free_categories.xml','2023-06-16 20:21:32',65,'EXECUTED','8:a2e57cde2670b2ab06f30c172f0f64bd','insert tableName=activity_type; insert tableName=activity_type','',NULL,'4.3.1',NULL,NULL,'6936092404');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `databasechangeloglock` (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT IGNORE INTO `databasechangeloglock` VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day_activity`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `day_activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_factor` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `logged_reps` varchar(255) DEFAULT NULL,
  `todo_kg` varchar(255) DEFAULT NULL,
  `todo_reps` varchar(255) DEFAULT NULL,
  `todo_sets` varchar(255) DEFAULT NULL,
  `activity_id` bigint DEFAULT NULL,
  `activitytype_id` bigint DEFAULT NULL,
  `is_logged` bit(1) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaq07a4510mjpb7qx0y94s93sh` (`activity_id`),
  KEY `FKemkox7dxhdv7vu5c8d1rwuc44` (`activitytype_id`),
  CONSTRAINT `FKaq07a4510mjpb7qx0y94s93sh` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKemkox7dxhdv7vu5c8d1rwuc44` FOREIGN KEY (`activitytype_id`) REFERENCES `activity_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=1667 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `global_flag`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `global_flag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jhi_authority`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT IGNORE INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_PAID'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `jhi_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `lang_key` varchar(10) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `reset_date` datetime(6) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `timezone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9y0frpqnmqe7y6mk109vw3246` (`login`),
  UNIQUE KEY `UK_bycanyquvi09q7fh5pgxrqnku` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

-- I have removed user data and have a backup in Deploy

--
-- Table structure for table `jhi_user_authority`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `jhi_user_authority` (
  `user_id` bigint NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `FK4psxl0jtx6nr7rhqbynr6itoc` (`authority_name`),
  CONSTRAINT `FK290okww5jujghp4el5i7mgwu0` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK4psxl0jtx6nr7rhqbynr6itoc` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT IGNORE INTO `jhi_user_authority` VALUES (25,'ROLE_PAID'),(21,'ROLE_USER'),(50,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;






--
-- Table structure for table `session_flag_dates`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `session_flag_dates` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `global_flag_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf1r7yjyd8j729jpk3f1dckt68` (`global_flag_id`),
  CONSTRAINT `FKf1r7yjyd8j729jpk3f1dckt68` FOREIGN KEY (`global_flag_id`) REFERENCES `global_flag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_flag_dates`
--

-- LOCK TABLES `session_flag_dates` WRITE;
-- /*!40000 ALTER TABLE `session_flag_dates` DISABLE KEYS */;
-- INSERT INTO `session_flag_dates` VALUES (1,'2020-11-14','2020-03-14',1),(2,'2022-12-11','2021-01-01',1),(3,NULL,'2021-03-16',1),(4,NULL,'2022-12-04',1),(5,NULL,'2022-12-04',1),(6,NULL,'2022-12-04',1),(7,NULL,'2022-12-04',1),(8,NULL,'2022-12-04',1),(9,NULL,'2022-11-30',1),(10,NULL,'2022-12-13',1),(11,'2022-04-05','2022-04-10',3),(15,'2023-02-14','2023-02-07',3),(17,'2023-02-23','2023-02-16',3),(18,'2023-04-10','2023-03-21',3),(19,'2023-04-22','2023-04-12',3),(25,'2023-06-09','2023-06-05',3),(26,'2023-06-08','2023-06-07',24),(27,'2023-06-12','2023-06-10',24),(28,'2023-07-08','2023-06-14',24),(29,'2023-08-09','2023-08-09',3),(30,'2023-08-11','2023-08-04',24),(31,NULL,'2023-08-11',3);
-- /*!40000 ALTER TABLE `session_flag_dates` ENABLE KEYS */;
-- UNLOCK TABLES;

INSERT INTO activity_type (description, title, user) VALUES
                                                         ('FITNESS desc','FITNESS','tasos'),
                                                         ('CAR desc','CAR','tasos'),
                                                         ('HOUSE desc','HOUSE','anmol'),
                                                         ('HEALTH desc','HEALTH','joe'),
                                                         ('VACATION desc','VACATION','bill');


INSERT INTO activity (activity_sub_type, title, activitytype_id, user) VALUES
                                                                           ('CARDIO','Cycling',(SELECT id FROM activity_type WHERE title='FITNESS' AND user='tasos' LIMIT 1),'tasos'),
('CARDIO','Swimming',(SELECT id FROM activity_type WHERE title='FITNESS' AND user='tasos' LIMIT 1),'tasos'),
('MAINTENANCE','Tire Rotation',(SELECT id FROM activity_type WHERE title='CAR' AND user='tasos' LIMIT 1),'tasos'),
('INSPECTION','Emissions Test',(SELECT id FROM activity_type WHERE title='CAR' AND user='tasos' LIMIT 1),'tasos'),
('CLEANING','Mop Kitchen Floor',(SELECT id FROM activity_type WHERE title='HOUSE' AND user='anmol' LIMIT 1),'anmol'),
('REPAIR','Fix Door Hinge',(SELECT id FROM activity_type WHERE title='HOUSE' AND user='anmol' LIMIT 1),'anmol'),
('CHECKUP','Vision Test',(SELECT id FROM activity_type WHERE title='HEALTH' AND user='joe' LIMIT 1),'joe'),
('WELLNESS','Yoga Session',(SELECT id FROM activity_type WHERE title='HEALTH' AND user='joe' LIMIT 1),'joe'),
('TRAVEL','Train Reservation',(SELECT id FROM activity_type WHERE title='VACATION' AND user='bill' LIMIT 1),'bill'),
('RELAXATION','Spa Day',(SELECT id FROM activity_type WHERE title='VACATION' AND user='bill' LIMIT 1),'bill');


