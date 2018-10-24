-- MySQL Workbench Synchronization
-- Generated: 2018-10-24 08:08
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: student

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `registracia_konferencia`.`participant` 
DROP COLUMN `participantcol`,
ADD COLUMN `tshirt` ENUM('S', 'M', 'L', 'XL', 'XXL') NULL DEFAULT NULL AFTER `cash`,
ADD COLUMN `workshop_id` INT(11) NOT NULL AFTER `tshirt`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`, `workshop_id`),
ADD INDEX `fk_participant_workshop1_idx` (`workshop_id` ASC);
;

CREATE TABLE IF NOT EXISTS `registracia_konferencia`.`companion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `start` DATETIME NULL DEFAULT NULL,
  `end` DATETIME NULL DEFAULT NULL,
  `category` ENUM('CHILD_UNDER_6', 'CHILD_UNDER_12', 'ADULT') NULL DEFAULT NULL,
  `tshirt` ENUM('S', 'M', 'L', 'XL', 'XXL') NULL DEFAULT NULL,
  `participant_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `participant_id`),
  INDEX `fk_companion_participant_idx` (`participant_id` ASC),
  CONSTRAINT `fk_companion_participant`
    FOREIGN KEY (`participant_id`)
    REFERENCES `registracia_konferencia`.`participant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `registracia_konferencia`.`workshop` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `start` DATE NULL DEFAULT NULL,
  `end` DATE NULL DEFAULT NULL,
  `price_full` DECIMAL(6,2) NULL DEFAULT NULL,
  `price_student` DECIMAL(6,2) NULL DEFAULT NULL,
  `price_full_late` DECIMAL(6,2) NULL DEFAULT NULL,
  `price_student_late` DECIMAL(6,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `registracia_konferencia`.`participant` 
ADD CONSTRAINT `fk_participant_workshop1`
  FOREIGN KEY (`workshop_id`)
  REFERENCES `registracia_konferencia`.`workshop` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
