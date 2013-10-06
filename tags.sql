-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 06, 2013 at 11:34 AM
-- Server version: 5.1.36-community-log
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tags`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `tagsDim` varchar(1000) NOT NULL,
  `assotext` varchar(1000) NOT NULL,
  `tagsName` varchar(1000) NOT NULL,
  `imageURL` varchar(500) NOT NULL,
  `passDims` varchar(1000) NOT NULL,
  `passText` varchar(1000) NOT NULL,
  `passTagsIndex` varchar(1000) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `tagsDim`, `assotext`, `tagsName`, `imageURL`, `passDims`, `passText`, `passTagsIndex`) VALUES
('asd', 'p2p4p3', '46.0,37.0,126.0,130.0,325.0,38.0,433.0,136.0,56.0,255.0,166.0,375.0,330.0,266.0,452.0,390.0,', 'p1,p2,p3,p4,', 't1,t2,t3,t4,', 'C:\\Users\\Mahesh\\Documents\\android_by_theederv.jpg', '325.0,433.0,136.0,330.0,452.0,390.0,56.0,166.0,375.0,', 'p2p4p3', '1,3,2,'),
('7', 'maheshnaidu', '89.0,53.0,413.0,170.0,88.0,225.0,414.0,356.0,', 'pass1,pass2,', 'tag1,tag2,', 'C:\\Users\\Mahesh\\Documents\\android_by_theederv.jpg', '99.0,323.0,224.0,402.0,606.0,369.0,', 'maheshnaidu', '0,1,'),
('hello', 'pas1pas2pas3', '48.0,38.0,171.0,140.0,370.0,40.0,558.0,189.0,671.0,52.0,796.0,164.0,', 'pas1,pas2,pas3,', 'tag1,tag2,tag3,', 'C:\\Users\\Mahesh\\Documents\\android_by_theederv.jpg', '48.0,171.0,140.0,370.0,558.0,189.0,671.0,796.0,164.0,', 'pas1pas2pas3', '0,1,2,'),
('mohit', '12', '126.0,75.0,317.0,270.0,573.0,90.0,767.0,267.0,', '1,2,', '1,2,', 'C:\\Users\\Mahesh\\Documents\\android_by_theederv.jpg', '126.0,317.0,270.0,573.0,767.0,267.0,', '12', '0,1,'),
('7', 'maheshnaidu', '99.0,69.0,323.0,224.0,402.0,166.0,606.0,369.0,', 'mahesh,naidu,', 'mahesh,naidu,', 'C:\\Users\\Mahesh\\Pictures\\1_puzzle.jpg', '99.0,323.0,224.0,402.0,606.0,369.0,', 'maheshnaidu', '0,1,'),
('mahesh', 'onefour', '42.0,52.0,168.0,162.0,291.0,49.0,425.0,163.0,41.0,235.0,172.0,343.0,284.0,234.0,421.0,345.0,', 'one,two,three,four,', 'one,two,three,four,', 'C:\\Users\\Mahesh\\Desktop\\Business Man(2012)[UandiStar.org]\\the-business-man-photo.jpg', '42.0,168.0,162.0,284.0,421.0,345.0,', 'onefour', '0,3,'),
('prsaanna', '132', '81.0,51.0,219.0,181.0,187.0,363.0,352.0,515.0,563.0,310.0,712.0,463.0,837.0,500.0,967.0,641.0,', '1,2,3,4,', '1,2,3,4,', 'C:\\Users\\Public\\Pictures\\Sample Pictures\\Jellyfish.jpg', '81.0,219.0,181.0,563.0,712.0,463.0,187.0,352.0,515.0,', '132', '0,2,1,'),
('123456', '1topcenterleft', '', '', '', 'C:\\Users\\Mahesh\\Desktop\\Important\\IMG_0003.jpg', '26.0,110.0,140.0,298.0,392.0,81.0,265.0,415.0,242.0,59.0,193.0,367.0,', '1topcenterleft', '6,4,0,1,'),
('123456', '1topcenterleft', '265.0,105.0,415.0,242.0,59.0,235.0,193.0,367.0,475.0,281.0,623.0,405.0,274.0,316.0,412.0,460.0,298.0,16.0,392.0,81.0,546.0,56.0,677.0,152.0,26.0,53.0,110.0,140.0,', 'center,left,right,middle,top,to,1,', 'center,left,right,middle,top,to,1,', 'C:\\Users\\Mahesh\\Desktop\\Important\\IMG_0001.jpg', '26.0,110.0,140.0,298.0,392.0,81.0,265.0,415.0,242.0,59.0,193.0,367.0,', '1topcenterleft', '6,4,0,1,'),
('chanti', '123', '131.0,27.0,299.0,144.0,344.0,198.0,527.0,345.0,584.0,385.0,747.0,505.0,', '1,2,3,', '1,2,3,', 'C:\\Users\\Mahesh\\Desktop\\Important\\Capture.PNG', '131.0,299.0,144.0,344.0,527.0,345.0,584.0,747.0,505.0,', '123', '0,1,2,'),
('rnath', '', '345.0,203.0,519.0,328.0,566.0,90.0,664.0,155.0,73.0,100.0,257.0,202.0,', '1,2,3,', '1,2,3,', 'C:\\Users\\Mahesh\\Pictures\\1294450L452c0-333Z.jpg', '', '', ''),
('ravi', '123', '221.0,241.0,251.0,314.0,484.0,234.0,521.0,330.0,387.0,194.0,452.0,242.0,343.0,255.0,368.0,311.0,', '1,2,3,4,', '1,2,3,4,', 'C:\\Users\\Mahesh\\Pictures\\vignan\\DSC_0220.JPG', '221.0,251.0,314.0,484.0,521.0,330.0,387.0,452.0,242.0,', '123', '0,1,2,'),
('ravi', '', '221.0,241.0,251.0,314.0,484.0,234.0,521.0,330.0,387.0,194.0,452.0,242.0,343.0,255.0,368.0,311.0,', '1,2,3,4,', '1,2,3,4,', 'C:\\\\Users\\\\Mahesh\\\\Pictures\\\\vignan\\\\DSC_0220.JPG', '', '', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
