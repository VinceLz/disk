# disk
网盘系统



使用java做的一个网盘系统，实现上传，下载，删除，分享等功能。
数据库:
/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.6.21 : Database - drive
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`drive` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `drive`;

/*Table structure for table `catalog` */

DROP TABLE IF EXISTS `catalog`;

CREATE TABLE `catalog` (
  `cId` varchar(50) NOT NULL,
  `pId` varchar(50) DEFAULT NULL,
  `cName` varchar(50) DEFAULT NULL,
  `cDate` varchar(50) DEFAULT NULL,
  `cF` varchar(50) DEFAULT NULL,
  `isShare` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `catalog_file` */

DROP TABLE IF EXISTS `catalog_file`;

CREATE TABLE `catalog_file` (
  `cf` varchar(50) NOT NULL,
  `fid` varchar(50) DEFAULT NULL,
  KEY `cf` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `file` */

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
  `fId` varchar(50) NOT NULL,
  `fPath` text,
  `fSize` int(50) DEFAULT NULL,
  `fType` varchar(50) DEFAULT NULL,
  `fName` varchar(50) DEFAULT NULL,
  `fHash` varchar(50) DEFAULT NULL,
  `fDowncount` int(11) DEFAULT NULL,
  `fDesc` varchar(50) DEFAULT NULL,
  `fUploadtime` date DEFAULT NULL,
  `isShare` bigint(2) DEFAULT NULL,
  `cId` varchar(50) DEFAULT NULL,
  `fDiskName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `info` */

DROP TABLE IF EXISTS `info`;

CREATE TABLE `info` (
  `iId` varchar(50) NOT NULL,
  `iTitle` varchar(100) DEFAULT NULL,
  `iContent` text,
  `iTime` varchar(50) DEFAULT NULL,
  `iImage` varchar(500) DEFAULT NULL,
  `isImage` int(11) DEFAULT NULL,
  `iLocation` int(11) DEFAULT NULL,
  `iStart` int(11) DEFAULT NULL,
  PRIMARY KEY (`iId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uId` varchar(55) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `uPassword` varchar(50) DEFAULT NULL,
  `cId` varchar(50) DEFAULT NULL,
  `uTime` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `fileSize` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
