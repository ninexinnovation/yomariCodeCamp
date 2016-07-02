-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 24, 2016 at 05:52 PM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agrimetrix`
--

-- --------------------------------------------------------

--
-- Table structure for table `humidity`
--

DROP TABLE IF EXISTS `humidity`;
CREATE TABLE IF NOT EXISTS `humidity` (
  `humidity_id` int(11) NOT NULL AUTO_INCREMENT,
  `system_id` int(11) NOT NULL,
  `data` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`humidity_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `moisture`
--

DROP TABLE IF EXISTS `moisture`;
CREATE TABLE IF NOT EXISTS `moisture` (
  `moisture_id` int(11) NOT NULL AUTO_INCREMENT,
  `system_id` int(11) NOT NULL,
  `data` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`moisture_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `moisture`
--

INSERT INTO `moisture` (`moisture_id`, `system_id`, `data`, `time`) VALUES
(1, 1, 122, 1466738663),
(2, 1, 122, 1466738680),
(3, 1, 122, 1466738681),
(4, 1, 122, 1466738909);

-- --------------------------------------------------------

--
-- Table structure for table `motor_status`
--

DROP TABLE IF EXISTS `motor_status`;
CREATE TABLE IF NOT EXISTS `motor_status` (
  `motor_id` int(11) NOT NULL AUTO_INCREMENT,
  `system_id` int(11) NOT NULL,
  `motor_status` tinyint(1) NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`motor_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `motor_status`
--

INSERT INTO `motor_status` (`motor_id`, `system_id`, `motor_status`, `time`) VALUES
(1, 1, 0, 1466738049);

-- --------------------------------------------------------

--
-- Table structure for table `system_status`
--

DROP TABLE IF EXISTS `system_status`;
CREATE TABLE IF NOT EXISTS `system_status` (
  `system_id` int(11) NOT NULL AUTO_INCREMENT,
  `system_status` tinyint(1) NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`system_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `system_status`
--

INSERT INTO `system_status` (`system_id`, `system_status`, `time`) VALUES
(1, 0, 1466738050);

-- --------------------------------------------------------

--
-- Table structure for table `temperature`
--

DROP TABLE IF EXISTS `temperature`;
CREATE TABLE IF NOT EXISTS `temperature` (
  `temp_id` int(11) NOT NULL AUTO_INCREMENT,
  `system_id` int(11) NOT NULL,
  `data` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`temp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
