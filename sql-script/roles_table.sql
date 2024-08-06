CREATE DATABASE  IF NOT EXISTS `java_thymeleaf_blog`;
USE `java_thymeleaf_blog`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
 `id` int NOT NULL AUTO_INCREMENT,
 `name` varchar(100) DEFAULT NULL,
 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `updated_at` timestamp NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



INSERT INTO `roles` (id, name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');
