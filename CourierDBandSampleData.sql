-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 16, 2014 at 05:28 PM
-- Server version: 5.5.34-0ubuntu0.13.10.1
-- PHP Version: 5.5.3-1ubuntu2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `CourierDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `Car`
--

CREATE TABLE IF NOT EXISTS `Car` (
  `CarId` int(11) NOT NULL AUTO_INCREMENT,
  `Type` enum('TruckA','TruckB') DEFAULT NULL,
  PRIMARY KEY (`CarId`),
  UNIQUE KEY `CarId_UNIQUE` (`CarId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `Car`
--

INSERT INTO `Car` (`CarId`, `Type`) VALUES
(1, 'TruckA'),
(2, 'TruckB');

-- --------------------------------------------------------

--
-- Table structure for table `Log`
--

CREATE TABLE IF NOT EXISTS `Log` (
  `LogId` int(11) NOT NULL AUTO_INCREMENT,
  `Event` varchar(255) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Device` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`LogId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Route`
--

CREATE TABLE IF NOT EXISTS `Route` (
  `RouteId` int(11) NOT NULL AUTO_INCREMENT,
  `StartPoint` varchar(255) NOT NULL,
  `EndPoint` varchar(255) NOT NULL,
  PRIMARY KEY (`RouteId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Route`
--

INSERT INTO `Route` (`RouteId`, `StartPoint`, `EndPoint`) VALUES
(1, 'Wroclaw', 'Warszawa');

-- --------------------------------------------------------

--
-- Table structure for table `RouteInformation`
--

CREATE TABLE IF NOT EXISTS `RouteInformation` (
  `RouteInformationId` int(11) NOT NULL AUTO_INCREMENT,
  `Distance` double NOT NULL,
  `Fuel` float NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  `RouteId` int(11) NOT NULL,
  `CarId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  PRIMARY KEY (`RouteInformationId`),
  KEY `fk_RouteInformation_Route1_idx` (`RouteId`),
  KEY `fk_RouteInformation_Car1_idx` (`CarId`),
  KEY `fk_RouteInformation_User1_idx` (`UserId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `RouteInformation`
--

INSERT INTO `RouteInformation` (`RouteInformationId`, `Distance`, `Fuel`, `StartTime`, `EndTime`, `RouteId`, `CarId`, `UserId`) VALUES
(1, 100, 9, '2014-01-16 00:00:00', '2014-01-17 00:00:00', 1, 1, 2),
(2, 20, 4, '2014-01-01 00:00:00', '2014-01-06 00:00:00', 1, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(45) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Email` varchar(75) NOT NULL,
  `Type` enum('Kierownik','Kierowca') DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UzytkownikId_UNIQUE` (`UserId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UserId`, `Login`, `Password`, `Email`, `Type`) VALUES
(1, 'test', '202cb962ac59075b964b07152d234b70', 'jacek@wp.pl', 'Kierownik'),
(2, 'driverA', '202cb962ac59075b964b07152d234b70', 'driver@wp.pl', 'Kierowca'),
(3, 'driverB', '202cb962ac59075b964b07152d234b70', 'driver@wp.pl', 'Kierowca'),
(4, 'test22', '123', 'test@', 'Kierowca');

-- --------------------------------------------------------

--
-- Table structure for table `User_Car`
--

CREATE TABLE IF NOT EXISTS `User_Car` (
  `UserId` int(11) NOT NULL,
  `CarId` int(11) NOT NULL,
  PRIMARY KEY (`UserId`,`CarId`),
  KEY `fk_User_has_Car_Car1_idx` (`CarId`),
  KEY `fk_User_has_Car_User_idx` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `RouteInformation`
--
ALTER TABLE `RouteInformation`
  ADD CONSTRAINT `fk_RouteInformation_Car1` FOREIGN KEY (`CarId`) REFERENCES `Car` (`CarId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_RouteInformation_Route1` FOREIGN KEY (`RouteId`) REFERENCES `Route` (`RouteId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_RouteInformation_User1` FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `User_Car`
--
ALTER TABLE `User_Car`
  ADD CONSTRAINT `fk_User_has_Car_Car1` FOREIGN KEY (`CarId`) REFERENCES `Car` (`CarId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_User_has_Car_User` FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
