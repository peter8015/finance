/*
SQLyog Enterprise - MySQL GUI v6.55 RC2
MySQL - 5.0.20a-nt : Database - dyd_credit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`dyd_credit` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `dyd_credit`;

/*Table structure for table `production` */

DROP TABLE IF EXISTS `production`;

CREATE TABLE `production` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(512) collate utf8_bin default NULL,
  `name` varchar(255) collate utf8_bin NOT NULL,
  `version` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `production` */

insert  into `production`(`id`,`description`,`name`,`version`) values (1,'production description','production',1),(7,'打发打发','大幅度',0),(10,'苟富贵','打发打发',0);

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_bin NOT NULL,
  `url` varchar(255) collate utf8_bin NOT NULL,
  `version` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_resource` */

insert  into `t_resource`(`id`,`name`,`url`,`version`) values (1,'用户列表','/user',NULL),(2,'添加用户','/user?form',NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_bin NOT NULL,
  `sort_number` int(11) default NULL,
  `version` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`sort_number`,`version`) values (1,'ROLE_ADMIN',1,1),(2,'ROLE_USER',1,1);

/*Table structure for table `t_role_resources` */

DROP TABLE IF EXISTS `t_role_resources`;

CREATE TABLE `t_role_resources` (
  `t_role` bigint(20) NOT NULL,
  `resources` bigint(20) NOT NULL,
  PRIMARY KEY  (`t_role`,`resources`),
  KEY `FK_10tkxl2ptuy0oy5ea5tumktng` (`resources`),
  KEY `FK_22ce2g7lk0250kkfryqnu90cd` (`t_role`),
  CONSTRAINT `FK_22ce2g7lk0250kkfryqnu90cd` FOREIGN KEY (`t_role`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_10tkxl2ptuy0oy5ea5tumktng` FOREIGN KEY (`resources`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_role_resources` */

insert  into `t_role_resources`(`t_role`,`resources`) values (1,1),(2,1),(1,2);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `city` varchar(45) collate utf8_bin NOT NULL,
  `email` varchar(45) collate utf8_bin NOT NULL,
  `fund_account` varchar(255) collate utf8_bin NOT NULL,
  `login_name` varchar(45) collate utf8_bin NOT NULL,
  `password` varchar(255) collate utf8_bin NOT NULL,
  `user_name` varchar(45) collate utf8_bin NOT NULL,
  `version` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`city`,`email`,`fund_account`,`login_name`,`password`,`user_name`,`version`) values (1,'北京','dfdfd@dfdf.com','aaaaaaaaaa','admin','admin','admin',0),(2,'北京','dfd@dd.com','bbbbbbbbbb','user','user','user',0);

/*Table structure for table `t_user_roles` */

DROP TABLE IF EXISTS `t_user_roles`;

CREATE TABLE `t_user_roles` (
  `t_user` bigint(20) NOT NULL,
  `roles` bigint(20) NOT NULL,
  PRIMARY KEY  (`t_user`,`roles`),
  KEY `FK_mj6mptufgio4vf30y1lo09sx7` (`roles`),
  KEY `FK_iwia4as4nttptg7yj0hd339rq` (`t_user`),
  CONSTRAINT `FK_iwia4as4nttptg7yj0hd339rq` FOREIGN KEY (`t_user`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_mj6mptufgio4vf30y1lo09sx7` FOREIGN KEY (`roles`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_user_roles` */

insert  into `t_user_roles`(`t_user`,`roles`) values (1,1),(1,2),(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
