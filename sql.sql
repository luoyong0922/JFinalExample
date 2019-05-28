/*
SQLyog Job Agent v12.2.6 (64 bit) Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.6.35-log : Database - cms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `cms`;

/*Table structure for table `cms_article` */

DROP TABLE IF EXISTS `cms_article`;

CREATE TABLE `cms_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) DEFAULT NULL,
  `alias_title` varchar(255) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `type` int(2) DEFAULT NULL COMMENT '1：自创；2：转载',
  `classify_id` bigint(20) DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `source_url` varchar(255) DEFAULT NULL,
  `summary` text,
  `keywords` varchar(255) DEFAULT NULL,
  `content` longtext,
  `is_hot` int(2) DEFAULT '0',
  `pic` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='文章';

/*Data for the table `cms_article` */

insert  into `cms_article`(`id`,`title`,`alias_title`,`author`,`type`,`classify_id`,`source_name`,`source_url`,`summary`,`keywords`,`content`,`is_hot`,`pic`,`create_time`,`update_time`) values 
(1,'文章1',NULL,'user1',NULL,3,NULL,NULL,NULL,'科技',NULL,0,NULL,'2019-05-28 08:42:00',NULL),
(3,'文章3',NULL,'user3',NULL,4,NULL,NULL,NULL,'军事','123456',0,NULL,'2019-05-28 08:45:13','2019-05-28 17:28:29'),
(4,'文章4',NULL,'user4',NULL,3,NULL,NULL,NULL,'财经','文章，4user4，财经',0,NULL,'2019-05-28 16:56:30',NULL),
(5,'文章2',NULL,'user2',NULL,6,NULL,NULL,NULL,'金融','金融指货币的发行、流通和回笼，贷款的发放和收回，存款的存入和提取，汇兑的往来等经济活动。金融（FINANCE或FINAUNCE）就是对现有资源进行重新整合之后，实现价值和利润的等效流通。',0,NULL,'2019-05-28 17:27:19',NULL);

/*Table structure for table `cms_classify` */

DROP TABLE IF EXISTS `cms_classify`;

CREATE TABLE `cms_classify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `alias_name` varchar(45) DEFAULT NULL,
  `seq` int(10) DEFAULT NULL,
  `pid` bigint(20) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='文章分类';

/*Data for the table `cms_classify` */

insert  into `cms_classify`(`id`,`name`,`alias_name`,`seq`,`pid`,`create_time`,`update_time`) values 
(1,'标题1',NULL,NULL,0,'2019-05-28 08:39:39',NULL),
(2,'标题1-1',NULL,NULL,1,'2019-05-28 08:40:17',NULL),
(3,'标题1-1-1',NULL,NULL,2,'2019-05-28 08:40:37',NULL),
(4,'标题1-1-3',NULL,NULL,2,'2019-05-28 08:40:51',NULL),
(5,'标题2',NULL,NULL,0,'2019-05-28 09:57:07',NULL),
(6,'标题1-1-2',NULL,NULL,2,'2019-05-28 17:24:13',NULL),
(7,'标题2-1',NULL,NULL,5,'2019-05-28 17:24:36',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
