-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2018 at 02:37 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `customerinfo`
--

-- --------------------------------------------------------

--
-- Table structure for table `auctionstore`
--

CREATE TABLE `auctionstore` (
  `auction store` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cust_info`
--

CREATE TABLE `cust_info` (
  `customer id` varchar(200) NOT NULL,
  `customer name` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `postal code` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `country` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `object number` varchar(200) NOT NULL,
  `auctionstore` varchar(200) NOT NULL,
  `ordernumber` varchar(200) NOT NULL,
  `pfwa` varchar(200) NOT NULL,
  `startdate` varchar(200) NOT NULL,
  `enddate` varchar(200) NOT NULL,
  `dateoforder` varchar(200) NOT NULL,
  `retailprice` int(200) NOT NULL,
  `retailpriceshipping` int(200) NOT NULL,
  `sellingprice` int(200) NOT NULL,
  `sellingpriceshipping` int(200) NOT NULL,
  `earnings` int(200) NOT NULL,
  `methododpayment` varchar(200) NOT NULL,
  `pickdate` varchar(200) NOT NULL,
  `dosManufacuturer` varchar(200) NOT NULL,
  `dosCompany` varchar(200) NOT NULL,
  `status` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `paymentmethods`
--

CREATE TABLE `paymentmethods` (
  `payment Methods` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pickfromwhichaccount`
--

CREATE TABLE `pickfromwhichaccount` (
  `pfwa` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cust_info`
--
ALTER TABLE `cust_info`
  ADD UNIQUE KEY `customer id` (`customer id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
