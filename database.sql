SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `CourierDB` ;
CREATE SCHEMA IF NOT EXISTS `CourierDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `CourierDB` ;

-- -----------------------------------------------------
-- Table `CourierDB`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CourierDB`.`User` ;

CREATE TABLE IF NOT EXISTS `CourierDB`.`User` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `Login` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(32) NOT NULL,
  `Email` VARCHAR(75) NOT NULL,
  `Type` ENUM('Kierownik','Kierowca') NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE INDEX `UzytkownikId_UNIQUE` (`UserId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CourierDB`.`Car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CourierDB`.`Car` ;

CREATE TABLE IF NOT EXISTS `CourierDB`.`Car` (
  `CarId` INT NOT NULL AUTO_INCREMENT,
  `Type` ENUM('TruckA','TruckB') NULL,
  PRIMARY KEY (`CarId`),
  UNIQUE INDEX `CarId_UNIQUE` (`CarId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CourierDB`.`User_Car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CourierDB`.`User_Car` ;

CREATE TABLE IF NOT EXISTS `CourierDB`.`User_Car` (
  `UserId` INT NOT NULL,
  `CarId` INT NOT NULL,
  PRIMARY KEY (`UserId`, `CarId`),
  INDEX `fk_User_has_Car_Car1_idx` (`CarId` ASC),
  INDEX `fk_User_has_Car_User_idx` (`UserId` ASC),
  CONSTRAINT `fk_User_has_Car_User`
    FOREIGN KEY (`UserId`)
    REFERENCES `CourierDB`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Car_Car1`
    FOREIGN KEY (`CarId`)
    REFERENCES `CourierDB`.`Car` (`CarId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CourierDB`.`Route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CourierDB`.`Route` ;

CREATE TABLE IF NOT EXISTS `CourierDB`.`Route` (
  `RouteId` INT NOT NULL AUTO_INCREMENT,
  `From` VARCHAR(255) NULL,
  `To` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`RouteId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CourierDB`.`Log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CourierDB`.`Log` ;

CREATE TABLE IF NOT EXISTS `CourierDB`.`Log` (
  `LogId` INT NOT NULL AUTO_INCREMENT,
  `Event` VARCHAR(255) NOT NULL,
  `Time` TIMESTAMP NOT NULL,
  `Device` VARCHAR(75) NULL,
  PRIMARY KEY (`LogId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CourierDB`.`RouteInformation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CourierDB`.`RouteInformation` ;

CREATE TABLE IF NOT EXISTS `CourierDB`.`RouteInformation` (
  `RouteInformationId` INT NOT NULL AUTO_INCREMENT,
  `Distance` DOUBLE NOT NULL,
  `Fuel` FLOAT NOT NULL,
  `StartTime` DATETIME NOT NULL,
  `EndTime` DATETIME NOT NULL,
  `RouteId` INT NOT NULL,
  `CarId` INT NOT NULL,
  `UserId` INT NOT NULL,
  PRIMARY KEY (`RouteInformationId`),
  INDEX `fk_RouteInformation_Route1_idx` (`RouteId` ASC),
  INDEX `fk_RouteInformation_Car1_idx` (`CarId` ASC),
  INDEX `fk_RouteInformation_User1_idx` (`UserId` ASC),
  CONSTRAINT `fk_RouteInformation_Route1`
    FOREIGN KEY (`RouteId`)
    REFERENCES `CourierDB`.`Route` (`RouteId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RouteInformation_Car1`
    FOREIGN KEY (`CarId`)
    REFERENCES `CourierDB`.`Car` (`CarId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RouteInformation_User1`
    FOREIGN KEY (`UserId`)
    REFERENCES `CourierDB`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
