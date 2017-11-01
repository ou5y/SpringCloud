-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 121.196.206.212    Database: ssstc
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agency_level`
--

DROP TABLE IF EXISTS `agency_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agency_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `param_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '参数名称',
  `param_value` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '参数值',
  `role_id` int(11) DEFAULT NULL COMMENT '所属模块',
  `percent` float(10,2) DEFAULT NULL COMMENT '比例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_level`
--

LOCK TABLES `agency_level` WRITE;
/*!40000 ALTER TABLE `agency_level` DISABLE KEYS */;
INSERT INTO `agency_level` VALUES (1,'高级推广员','1',NULL,NULL),(2,'推广员','2',NULL,NULL),(3,'总监','6',NULL,NULL),(4,'副总','7',NULL,NULL),(6,'服务商','9',NULL,NULL);
/*!40000 ALTER TABLE `agency_level` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-09 11:55:06







-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 121.196.206.212    Database: ssstc
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `c_gs_category`
--

DROP TABLE IF EXISTS `c_gs_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_gs_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT '',
  `parent_id` int(11) DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  `icon_url` varchar(255) DEFAULT '' COMMENT '图标地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_gs_category`
--

LOCK TABLES `c_gs_category` WRITE;
/*!40000 ALTER TABLE `c_gs_category` DISABLE KEYS */;
INSERT INTO `c_gs_category` VALUES (1,'家用电器',0,NULL,''),(2,'手机数码',0,NULL,''),(3,'电脑办公',0,NULL,''),(4,'家装建材',0,NULL,''),(5,'服装服饰',0,NULL,''),(6,'鞋帽箱包',0,NULL,''),(7,'美容化妆',0,NULL,''),(8,'运动户外',0,NULL,''),(9,'汽车服务',0,NULL,''),(10,'母婴玩具',0,NULL,''),(11,'糖酒生鲜',0,NULL,''),(12,'社会服务',0,NULL,''),(13,'机票酒店',0,NULL,''),(14,'理财咨询',0,NULL,''),(15,'建筑设计',0,NULL,''),(16,'农副用品',0,NULL,''),(17,'电视',1,NULL,''),(18,'空调',1,NULL,''),(19,'洗衣机',1,NULL,''),(20,'冰箱',1,NULL,''),(21,'厨卫大电',1,NULL,''),(22,'厨卫小电',1,NULL,''),(23,'生活电器',1,NULL,''),(24,'个户健康',1,NULL,''),(25,'家庭影院',1,NULL,''),(26,'手机通讯',2,NULL,''),(27,'手机配件',2,NULL,''),(28,'摄影摄像',2,NULL,''),(29,'数码配件',2,NULL,''),(30,'影音娱乐',2,NULL,''),(31,'智能设备',2,NULL,''),(32,'电子教育',2,NULL,''),(33,'电脑整机',3,NULL,''),(34,'电脑配件',3,NULL,''),(35,'外观产品',3,NULL,''),(36,'游戏设备',3,NULL,''),(37,'网络产品',3,NULL,''),(38,'办公设备',3,NULL,''),(39,'文具耗材',3,NULL,''),(40,'服务产品',3,NULL,''),(41,'厨具',4,NULL,''),(42,'家纺',4,NULL,''),(43,'生活日用',4,NULL,''),(44,'家装软饰',4,NULL,''),(45,'灯具',4,NULL,''),(46,'家具',4,NULL,''),(47,'建材',4,NULL,''),(48,'厨房卫浴',4,NULL,''),(49,'五金机电',4,NULL,''),(50,'装修定制',4,NULL,''),(51,'女装',5,NULL,''),(52,'男装',5,NULL,''),(53,'内衣',5,NULL,''),(54,'配饰',5,NULL,''),(55,'童装童鞋',5,NULL,''),(56,'时尚女鞋',6,NULL,''),(57,'流行男鞋',6,NULL,''),(58,'潮流女包',6,NULL,''),(59,'精品男包',6,NULL,''),(60,'功能箱包',6,NULL,''),(61,'精品饰品',6,NULL,''),(62,'礼品',6,NULL,''),(63,'钟表',6,NULL,''),(64,'珠宝首饰',6,NULL,''),(65,'美容美发',7,NULL,''),(66,'化妆护发',7,NULL,''),(67,'身体护理',7,NULL,''),(68,'口腔护理',7,NULL,''),(69,'女性护理',7,NULL,''),(70,'香水彩妆',7,NULL,''),(71,'清洁用品',7,NULL,''),(72,'宠物生活',7,NULL,''),(73,'运动鞋包',8,NULL,''),(74,'运动服饰',8,NULL,''),(75,'健身训练',8,NULL,''),(76,'骑行运动',8,NULL,''),(77,'体育用品',8,NULL,''),(78,'户外鞋服',8,NULL,''),(79,'户外装备',8,NULL,''),(80,'垂钓用品',8,NULL,''),(81,'游泳用品',8,NULL,''),(82,'汽车销售',9,NULL,''),(83,'维修保养',9,NULL,''),(84,'汽车装饰',9,NULL,''),(85,'车载电器',9,NULL,''),(86,'美容清洗',9,NULL,''),(87,'安全自驾',9,NULL,''),(88,'赛事改装',9,NULL,''),(89,'汽车服务',9,NULL,''),(90,'租车',9,NULL,''),(91,'代驾',9,NULL,''),(92,'奶粉',10,NULL,''),(93,'营养辅食',10,NULL,''),(94,'尿裤湿巾',10,NULL,''),(95,'喂养用品',10,NULL,''),(96,'洗护用品',10,NULL,''),(97,'寝居服饰',10,NULL,''),(98,'妈妈用品',10,NULL,''),(99,'童车童床',10,NULL,''),(100,'玩具',10,NULL,''),(101,'乐器',10,NULL,''),(102,'新鲜水果',11,NULL,''),(103,'蔬菜蛋品',11,NULL,''),(104,'肉类',11,NULL,''),(105,'海鲜水产',11,NULL,''),(106,'冷饮冻食',11,NULL,''),(107,'酒类',11,NULL,''),(108,'糖果',11,NULL,''),(109,'进口食品',11,NULL,''),(110,'休闲食品',11,NULL,''),(111,'茗茶',11,NULL,''),(112,'饮料冲调',11,NULL,''),(113,'粮油调味',11,NULL,''),(114,'社会服务',12,NULL,''),(115,'出行机票',13,NULL,''),(116,'酒店预订',13,NULL,''),(117,'婚庆服务',13,NULL,''),(118,'旅游度假',13,NULL,''),(119,'演出票务',13,NULL,''),(120,'娱乐餐饮',13,NULL,''),(121,'彩票',13,NULL,''),(122,'游戏',13,NULL,''),(123,'海外生活',13,NULL,''),(124,'经纪代理',14,NULL,''),(125,'咨询服务',14,NULL,''),(126,'理财',14,NULL,''),(127,'保险',14,NULL,''),(128,'建筑建造',15,NULL,''),(129,'机械制造',15,NULL,''),(130,'设计服务',15,NULL,''),(131,'广告活动',15,NULL,''),(132,'影视媒体',15,NULL,''),(133,'房产服务',15,NULL,''),(134,'种子',16,NULL,''),(135,'农药',16,NULL,''),(136,'化肥',16,NULL,''),(137,'饲料兽药',16,NULL,'');
/*!40000 ALTER TABLE `c_gs_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-09 11:55:08


-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 121.196.206.212    Database: ssstc
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `c_menu`
--

DROP TABLE IF EXISTS `c_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_menu`
--

LOCK TABLES `c_menu` WRITE;
/*!40000 ALTER TABLE `c_menu` DISABLE KEYS */;
INSERT INTO `c_menu` VALUES (1,'上传商家',NULL,'2017-05-05 13:45:34','2017-05-05 13:45:34'),(2,'开通',NULL,'2017-05-05 13:45:34','2017-05-05 13:45:34'),(3,'推广业务',NULL,'2017-05-15 14:50:08','2017-05-15 14:50:08'),(4,'服务商业务',NULL,'2017-05-15 14:50:24','2017-05-15 14:50:24');
/*!40000 ALTER TABLE `c_menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-09 11:55:07


-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 121.196.206.212    Database: ssstc
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `c_role`
--

DROP TABLE IF EXISTS `c_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_role`
--

LOCK TABLES `c_role` WRITE;
/*!40000 ALTER TABLE `c_role` DISABLE KEYS */;
INSERT INTO `c_role` VALUES (1,'消费者',NULL,'2017-05-04 14:14:38','2017-05-04 14:14:38',NULL),(2,'商家',NULL,'2017-05-04 14:14:38','2017-05-04 14:14:38',NULL);
/*!40000 ALTER TABLE `c_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-09 11:55:09
