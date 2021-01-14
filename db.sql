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
  `description` VARCHAR(255) NOT NULL,
  `producer` VARCHAR(45) NOT NULL,
  `type` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `musicshop`.`guitar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `productId` INT NOT NULL,
  `guitarType` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`productId`) REFERENCES `musicshop`.`product`(`id`));
  
CREATE TABLE `musicshop`.`strings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `productId` INT NOT NULL,
  `gauge` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`productId`) REFERENCES `musicshop`.`product`(`id`));
  
CREATE TABLE `musicshop`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `addressId` INT NOT NULL,
  `status` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`userId`) REFERENCES `musicshop`.`account`(`id`),
  FOREIGN KEY(`addressId`) REFERENCES `musicshop`.`address`(`id`)
);

CREATE TABLE `musicshop`.`order_product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `orderId` INT NOT NULL,
  `productId` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`orderId`) REFERENCES `musicshop`.`order`(`id`),
  FOREIGN KEY(`productId`) REFERENCES `musicshop`.`product`(`id`)
);

INSERT INTO product VALUES (null, 
"60th Anniversary 1960 Les Paul", 
6499,
"This limited-edition 60th Anniversary model celebrates the iconic 1960 Les Paul Standard, which has shaped music across genres for six decades.",
"GIBSON",
"GUITAR",
20);

INSERT INTO guitar VALUES (null,
1,
"ELECTRIC");
