create database examDicuAlinLeon;

CREATE TABLE `examdicualinleon`.`bikes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `brand` VARCHAR(200) NOT NULL,
  `model` VARCHAR(200) NOT NULL,
  `price` DOUBLE NOT NULL,
  `color` VARCHAR(50) NOT NULL,
  `type` INT NOT NULL,
  PRIMARY KEY (`id`));
  
SELECT * FROM bikes;