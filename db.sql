create database musicshop;

CREATE TABLE `musicshop`.`account` (
  id int not null auto_increment primary key,
  username VARCHAR(30) NOT NULL,
  password VARCHAR(30) NOT NULL,
  email VARCHAR(100) NOT NULL);
  
CREATE TABLE `musicshop`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `musicshop`.`account_address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account_id` INT NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`account_id`) REFERENCES `musicshop`.`account`(`id`),
  FOREIGN KEY(`address_id`) REFERENCES `musicshop`.`address`(`id`));
  
CREATE TABLE `musicshop`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  `producer` VARCHAR(45) NOT NULL,
  `type` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`));
