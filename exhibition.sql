-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema exhibitions
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `exhibition`;

-- -----------------------------------------------------
-- Schema exhibitions
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `exhibition` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `exhibition`;

-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `first_name` VARCHAR(32) NOT NULL,
  `last_name` VARCHAR(32) NOT NULL,
  `role` ENUM('admin', 'user') NOT NULL DEFAULT 'user',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `booking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `total` DOUBLE UNSIGNED NOT NULL,
  `account_id` INT NOT NULL,
  `paid` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `account_id_idx` (`account_id` ASC),
  CONSTRAINT `account_id`
    FOREIGN KEY (`account_id`)
    REFERENCES `account` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exposition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exposition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL,
  `category` ENUM('science', 'art', 'entertainment', 'technologies', 'history', 'other') NOT NULL DEFAULT 'other',
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `price` DOUBLE UNSIGNED NOT NULL,
  `locale` ENUM('en', 'ua') NOT NULL DEFAULT 'en',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `showroom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `showroom` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL,
  `booked_by_exposition_id` INT DEFAULT NULL,
  `locale` ENUM('en', 'ua') NOT NULL DEFAULT 'en',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `exposition_id_idx` (`booked_by_exposition_id` ASC),
  CONSTRAINT `booked_by_exposition_id`
    FOREIGN KEY (`booked_by_exposition_id`)
    REFERENCES `exposition` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket` (
  `booking_id` INT NOT NULL,
  `exposition_id` INT NOT NULL,
  `quantity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`booking_id`, `exposition_id`),
  INDEX `exposition_id_idx` (`exposition_id` ASC),
  INDEX `booking_id_idx` (`booking_id` ASC),
  CONSTRAINT `exposition_id`
    FOREIGN KEY (`exposition_id`)
    REFERENCES `exposition` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `booking_id`
    FOREIGN KEY (`booking_id`)
    REFERENCES `booking` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO exposition VALUES (1, 'Art of Modern Ukraine', 'art', '2020-10-01', '2020-10-08', 9.99, 'en');
INSERT INTO exposition VALUES (2, 'ComicCon', 'entertainment', '2020-10-09', '2020-10-16', 99.99, 'en');
INSERT INTO exposition VALUES (3, 'Mobile World Congress', 'technologies', '2020-10-17', '2020-10-24', 99.99, 'en');
INSERT INTO exposition VALUES (4, 'Ukrainian Science', 'science', '2020-10-25', '2020-11-01', 29.99, 'en');
INSERT INTO exposition VALUES (5, 'Ancient Rome and Greece', 'history', '2020-11-02', '2020-11-09', 14.99, 'en');
INSERT INTO exposition VALUES (6, 'AgroExpo', 'other', '2020-11-10', '2020-11-17', 24.99, 'en');
INSERT INTO exposition VALUES (7, 'Мистецтво сучасної України', 'art', '2020-10-01', '2020-10-08', 9.99, 'ua');
INSERT INTO exposition VALUES (8, 'КомікКон', 'entertainment', '2020-10-09', '2020-10-16', 99.99, 'ua');
INSERT INTO exposition VALUES (9, 'Всесвітній мобільний конгрес', 'technologies', '2020-10-17', '2020-10-24', 99.99, 'ua');
INSERT INTO exposition VALUES (10, 'Українська наука', 'science', '2020-10-25', '2020-11-01', 29.99, 'ua');
INSERT INTO exposition VALUES (11, 'Стародавні Рим та Греція', 'history', '2020-11-02', '2020-11-09', 14.99, 'ua');
INSERT INTO exposition VALUES (12, 'АгроЕкспо', 'other', '2020-11-10', '2020-11-17', 24.99, 'ua');
INSERT INTO account VALUES (1, 'Kirill', 'Kirill12', 'Kirill', 'Karpenko', 'admin');
INSERT INTO showroom VALUES(1, 'Showroom №1', '1', 'en');
INSERT INTO showroom VALUES(2, 'Showroom №2', '2', 'en');
INSERT INTO showroom VALUES(3, 'Шоурум №1', '7', 'ua');
INSERT INTO showroom VALUES(4, 'Шоурум №2', '8', 'ua');