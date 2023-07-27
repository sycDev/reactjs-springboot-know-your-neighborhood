CREATE DATABASE IF NOT EXISTS `db_kyn`;
USE `db_kyn`;

--
-- Table structure for table `store`
--
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `store_id` bigint NOT NULL AUTO_INCREMENT,
  `store_name` varchar(50) NOT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  `opening_hours` varchar(255) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT NOW(),
  `updated_at` datetime NOT NULL DEFAULT NOW(),
  `created_by` bigint NOT NULL,
  `updated_by` bigint NOT NULL,
  PRIMARY KEY (`store_id`)
);

--
-- Table structure for table `role`
--
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
	`role_id` bigint NOT NULL AUTO_INCREMENT,
    `role_name` varchar(50) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `uk_role_name` (`role_name`)
);

--
-- Default roles for table `role`
--
INSERT INTO db_kyn.`role`(`role_id`, `role_name`) VALUES(1, "USER"), (2, "ADMIN");

--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`user_id` bigint NOT NULL AUTO_INCREMENT,
    `username` varchar(30) NOT NULL,
    `email` varchar(255) NOT NULL,
    `password` varchar(64),
    `name` varchar(255),
    `img_url` varchar(255),
    `provider` varchar(20),
    `provider_id` varchar(255),
    `created_at` datetime NOT NULL DEFAULT NOW(),
    `updated_at` datetime NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uk_email` (`email`),
	UNIQUE KEY `uk_username` (`username`)
);

--
-- Table structure for table `user_role`
--
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
	`user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    PRIMARY KEY (`user_id`,`role_id`),
    KEY `role_fk_idx` (`role_id`),
    CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
    CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);
