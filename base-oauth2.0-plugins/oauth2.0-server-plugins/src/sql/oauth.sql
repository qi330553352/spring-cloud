/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.20-log : Database - oauth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oauth` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oauth`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(512) DEFAULT NULL,
  `role_string` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`id`,`user_name`,`email`,`password`,`role_string`) values (1,'user','user@sample.com','123','ROLE_USER');

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `authorities` */

insert  into `authorities`(`username`,`authority`) values ('admin','ADMIN'),('user','USER');

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT 'MD5åŠ å¯†çš„access_tokençš„å€¼',
  `token` blob COMMENT 'OAuth2AccessToken.javaå¯¹è±¡åºåˆ—åŒ–åçš„äºŒè¿›åˆ¶æ•°æ®',
  `authentication_id` varchar(256) DEFAULT NULL COMMENT 'MD5åŠ å¯†è¿‡çš„username,client_id,scope',
  `user_name` varchar(256) DEFAULT NULL COMMENT 'ç™»å½•çš„ç”¨æˆ·å',
  `client_id` varchar(256) DEFAULT NULL COMMENT 'å®¢æˆ·ç«¯ID',
  `authentication` blob COMMENT 'OAuth2Authentication.javaå¯¹è±¡åºåˆ—åŒ–åçš„äºŒè¿›åˆ¶æ•°æ®',
  `refresh_token` varchar(256) DEFAULT NULL COMMENT 'MD5åŠ å¯†æœçš„refresh_tokençš„å€¼'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='è®¿é—®ä»¤ç‰Œ';

/*Data for the table `oauth_access_token` */

insert  into `oauth_access_token`(`token_id`,`token`,`authentication_id`,`user_name`,`client_id`,`authentication`,`refresh_token`) values ('20c7c731e8edb07d7141ff3159e7673d','¬í\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken²6$úÎ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6…ZÜçĞ\0\0xpsr\0java.util.DatehjKYt\0\0xpw\0\0d%Ò:xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ßGcĞÉ·\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokensá\ncTÔ^\0L\0valueq\0~\0xpt\0$a4e4a3f3-2cf2-4bf0-9827-952a85973a3csq\0~\0	w\0\0d*Üìúxsr\0%java.util.Collections$UnmodifiableSet€’Ñ›€U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0€Ë^÷\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSetØl×Z•İ*\0\0xr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxt\0bearert\0$9c25f4c5-b81a-4d15-8e35-ff1a57ff7785','af7b541c269407f312624c83295c2e2d','admin','oauth_client_ren','¬í\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication½@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenÓª(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableListü%1µì\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0€Ë^÷\0L\0cq\0~\0xpsr\0java.util.ArrayListxÒ™Ça\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0¤\0L\0rolet\0Ljava/lang/String;xpt\0ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qi½\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0oauth_client_rensr\0%java.util.Collections$UnmodifiableMapñ¥¨ştõB\0L\0mq\0~\0xpsr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0codet\0a8r61wt\0\ngrant_typet\0authorization_codet\0scopet\0readt\0\rresponse_typet\0codet\0redirect_urit\0http://localhost:8080/callbackt\0statet\0secret-rensanningt\0\rclient_secrett\0oauth_client_secret_rent\0	client_idt\0oauth_client_renxsr\0%java.util.Collections$UnmodifiableSet€’Ñ›€U\0\0xq\0~\0	sr\0java.util.LinkedHashSetØl×Z•İ*\0\0xr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxsq\0~\0/w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0http://localhost:8080/callbackpsq\0~\0/w\0\0\0?@\0\0\0\0\0\0xsq\0~\0/w\0\0\0?@\0\0\0\0\0q\0~\0#xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0¤\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0<sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0¤\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0 721CBA7EF250362881A63FC061C5F121psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0¤\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0,sr\0java.util.TreeSetİ˜P“•í‡[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0¤\0\0xpw\0\0\0q\0~\0xpt\0admin','6b134471fda9af7267f5631ca5366636'),('99dda055eec50c757aba21db349a1aca','¬í\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken²6$úÎ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6…ZÜçĞ\0\0xpsr\0java.util.DatehjKYt\0\0xpw\0\0d&9Ÿ¸xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ßGcĞÉ·\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokensá\ncTÔ^\0L\0valueq\0~\0xpt\0$0ee69b59-9d13-40c7-a5cb-2619292ea2b8sq\0~\0	w\0\0d*Öü9xsr\0%java.util.Collections$UnmodifiableSet€’Ñ›€U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0€Ë^÷\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSetØl×Z•İ*\0\0xr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxt\0bearert\0$4eabffb8-a064-4668-833c-bcb520e26bf9','db7cfacbbe39224f7ddedf64ff1afd91','user','oauth_client_ren','¬í\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication½@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenÓª(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableListü%1µì\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0€Ë^÷\0L\0cq\0~\0xpsr\0java.util.ArrayListxÒ™Ça\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0¤\0L\0rolet\0Ljava/lang/String;xpt\0USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qi½\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0oauth_client_rensr\0%java.util.Collections$UnmodifiableMapñ¥¨ştõB\0L\0mq\0~\0xpsr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0codet\0stzPHTt\0\ngrant_typet\0authorization_codet\0scopet\0readt\0\rresponse_typet\0codet\0redirect_urit\0http://localhost:8080/callbackt\0statet\0secret-rensanningt\0\rclient_secrett\0oauth_client_secret_rent\0	client_idt\0oauth_client_renxsr\0%java.util.Collections$UnmodifiableSet€’Ñ›€U\0\0xq\0~\0	sr\0java.util.LinkedHashSetØl×Z•İ*\0\0xr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxsq\0~\0/w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0http://localhost:8080/callbackpsq\0~\0/w\0\0\0?@\0\0\0\0\0\0xsq\0~\0/w\0\0\0?@\0\0\0\0\0q\0~\0#xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0¤\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0<sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0¤\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0 F86E8608AAD4B113C540B5A5E447A5F6psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0¤\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0,sr\0java.util.TreeSetİ˜P“•í‡[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0¤\0\0xpw\0\0\0q\0~\0xpt\0user','dc3bb1beb3e44d613bda57a7b88eea11');

/*Table structure for table `oauth_approvals` */

DROP TABLE IF EXISTS `oauth_approvals`;

CREATE TABLE `oauth_approvals` (
  `userid` varchar(256) DEFAULT NULL COMMENT 'ç™»å½•çš„ç”¨æˆ·å',
  `clientid` varchar(256) DEFAULT NULL COMMENT 'å®¢æˆ·ç«¯ID',
  `scope` varchar(256) DEFAULT NULL COMMENT 'ç”³è¯·çš„æƒé™',
  `status` varchar(10) DEFAULT NULL COMMENT 'çŠ¶æ€ï¼ˆApproveæˆ–Denyï¼‰',
  `expiresat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'è¿‡æœŸæ—¶é—´',
  `lastmodifiedat` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'æœ€ç»ˆä¿®æ”¹æ—¶é—´'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='æˆæƒè®°å½•';

/*Data for the table `oauth_approvals` */

insert  into `oauth_approvals`(`userid`,`clientid`,`scope`,`status`,`expiresat`,`lastmodifiedat`) values ('user','oauth_client_ren','read','APPROVED','2018-07-22 12:11:29','2018-06-22 12:11:29'),('admin','oauth_client_ren','read','APPROVED','2018-07-22 12:17:59','2018-06-22 12:17:59');

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL COMMENT 'å®¢æˆ·ç«¯ID',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT 'èµ„æºIDé›†åˆ,å¤šä¸ªèµ„æºæ—¶ç”¨é€—å·(,)åˆ†éš”',
  `client_secret` varchar(256) DEFAULT NULL COMMENT 'å®¢æˆ·ç«¯å¯†åŒ™',
  `scope` varchar(256) DEFAULT NULL COMMENT 'å®¢æˆ·ç«¯ç”³è¯·çš„æƒé™èŒƒå›´',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT 'å®¢æˆ·ç«¯æ”¯æŒçš„grant_type',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT 'é‡å®šå‘URI',
  `authorities` varchar(256) DEFAULT NULL COMMENT 'å®¢æˆ·ç«¯æ‰€æ‹¥æœ‰çš„Spring Securityçš„æƒé™å€¼ï¼Œå¤šä¸ªç”¨é€—å·(,)åˆ†éš”',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'è®¿é—®ä»¤ç‰Œæœ‰æ•ˆæ—¶é—´å€¼(å•ä½:ç§’)',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'æ›´æ–°ä»¤ç‰Œæœ‰æ•ˆæ—¶é—´å€¼(å•ä½:ç§’)',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT 'é¢„ç•™å­—æ®µ',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT 'ç”¨æˆ·æ˜¯å¦è‡ªåŠ¨Approvalæ“ä½œ',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='å®¢æˆ·ç«¯ä¿¡æ¯';

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('oauth_client',NULL,'oauth_client_secret','read,write','authorization_code,refresh_token','http://default-oauth-callback.com','ROLE_USER',1800,86400,NULL,'0'),('oauth_client_ren',NULL,'oauth_client_secret_ren','read','authorization_code,refresh_token','http://localhost:8080/callback','ROLE_USER',1800,86400,NULL,'0');

/*Table structure for table `oauth_client_token` */

DROP TABLE IF EXISTS `oauth_client_token`;

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT 'MD5åŠ å¯†çš„access_tokenå€¼',
  `token` blob COMMENT 'OAuth2AccessToken.javaå¯¹è±¡åºåˆ—åŒ–åçš„äºŒè¿›åˆ¶æ•°æ®',
  `authentication_id` varchar(256) DEFAULT NULL COMMENT 'MD5åŠ å¯†è¿‡çš„username,client_id,scope',
  `user_name` varchar(256) DEFAULT NULL COMMENT 'ç™»å½•çš„ç”¨æˆ·å',
  `client_id` varchar(256) DEFAULT NULL COMMENT 'å®¢æˆ·ç«¯ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_token` */

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL COMMENT 'æˆæƒç (æœªåŠ å¯†)',
  `authentication` blob COMMENT 'AuthorizationRequestHolder.javaå¯¹è±¡åºåˆ—åŒ–åçš„äºŒè¿›åˆ¶æ•°æ®'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='æˆæƒç ';

/*Data for the table `oauth_code` */

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT 'MD5åŠ å¯†è¿‡çš„refresh_tokençš„å€¼',
  `token` blob COMMENT 'OAuth2RefreshToken.javaå¯¹è±¡åºåˆ—åŒ–åçš„äºŒè¿›åˆ¶æ•°æ®',
  `authentication` blob COMMENT 'OAuth2Authentication.javaå¯¹è±¡åºåˆ—åŒ–åçš„äºŒè¿›åˆ¶æ•°æ®'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='æ›´æ–°ä»¤ç‰Œ';

/*Data for the table `oauth_refresh_token` */

insert  into `oauth_refresh_token`(`token_id`,`token`,`authentication`) values ('6b134471fda9af7267f5631ca5366636','¬í\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ßGcĞÉ·\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokensá\ncTÔ^\0L\0valuet\0Ljava/lang/String;xpt\0$a4e4a3f3-2cf2-4bf0-9827-952a85973a3csr\0java.util.DatehjKYt\0\0xpw\0\0d*Üìúx','¬í\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication½@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenÓª(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableListü%1µì\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0€Ë^÷\0L\0cq\0~\0xpsr\0java.util.ArrayListxÒ™Ça\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0¤\0L\0rolet\0Ljava/lang/String;xpt\0ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qi½\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0oauth_client_rensr\0%java.util.Collections$UnmodifiableMapñ¥¨ştõB\0L\0mq\0~\0xpsr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0codet\0a8r61wt\0\ngrant_typet\0authorization_codet\0scopet\0readt\0\rresponse_typet\0codet\0redirect_urit\0http://localhost:8080/callbackt\0statet\0secret-rensanningt\0\rclient_secrett\0oauth_client_secret_rent\0	client_idt\0oauth_client_renxsr\0%java.util.Collections$UnmodifiableSet€’Ñ›€U\0\0xq\0~\0	sr\0java.util.LinkedHashSetØl×Z•İ*\0\0xr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxsq\0~\0/w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0http://localhost:8080/callbackpsq\0~\0/w\0\0\0?@\0\0\0\0\0\0xsq\0~\0/w\0\0\0?@\0\0\0\0\0q\0~\0#xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0¤\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0<sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0¤\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0 721CBA7EF250362881A63FC061C5F121psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0¤\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0,sr\0java.util.TreeSetİ˜P“•í‡[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0¤\0\0xpw\0\0\0q\0~\0xpt\0admin'),('dc3bb1beb3e44d613bda57a7b88eea11','¬í\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ßGcĞÉ·\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokensá\ncTÔ^\0L\0valuet\0Ljava/lang/String;xpt\0$0ee69b59-9d13-40c7-a5cb-2619292ea2b8sr\0java.util.DatehjKYt\0\0xpw\0\0d*Öü9x','¬í\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication½@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenÓª(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableListü%1µì\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0€Ë^÷\0L\0cq\0~\0xpsr\0java.util.ArrayListxÒ™Ça\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0¤\0L\0rolet\0Ljava/lang/String;xpt\0USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qi½\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0oauth_client_rensr\0%java.util.Collections$UnmodifiableMapñ¥¨ştõB\0L\0mq\0~\0xpsr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0codet\00gkrcdt\0\ngrant_typet\0authorization_codet\0scopet\0readt\0\rresponse_typet\0codet\0redirect_urit\0http://localhost:8080/callbackt\0statet\0secret-rensanningt\0\rclient_secrett\0oauth_client_secret_rent\0	client_idt\0oauth_client_renxsr\0%java.util.Collections$UnmodifiableSet€’Ñ›€U\0\0xq\0~\0	sr\0java.util.LinkedHashSetØl×Z•İ*\0\0xr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxsq\0~\0/w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0http://localhost:8080/callbackpsq\0~\0/w\0\0\0?@\0\0\0\0\0\0xsq\0~\0/w\0\0\0?@\0\0\0\0\0q\0~\0#xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0¤\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0<sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0¤\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0 630F4FA4449BA826227C4198D3B0617Dpsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0¤\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0,sr\0java.util.TreeSetİ˜P“•í‡[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0¤\0\0xpw\0\0\0q\0~\0xpt\0user');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values ('admin','admin',1),('user','123',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
