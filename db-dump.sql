DROP DATABASE IF EXISTS distance_calculator;
CREATE DATABASE distance_calculator;
USE distance_calculator;
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (`id` INT NOT NULL AUTO_INCREMENT, `latitude` DOUBLE precision NOT NULL, `longitude` DOUBLE precision NOT NULL, `name` VARCHAR(255) NOT NULL,  PRIMARY KEY(`id`));
DROP TABLE IF EXISTS `distance`;
CREATE TABLE `distance` (`id` INT NOT NULL AUTO_INCREMENT, `distance` INT,  `from_city` INT NOT NULL, `to_city` INT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`from_city`) REFERENCES city(id), FOREIGN KEY(`to_city`) REFERENCES city(id));