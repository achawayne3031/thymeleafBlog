CREATE DATABASE  IF NOT EXISTS `java_thymeleaf_blog`;
USE `java_thymeleaf_blog`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` TEXT DEFAULT NULL,
  `user_id` int NOT NULL,
  `enabled` tinyint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


