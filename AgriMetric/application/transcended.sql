-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 03, 2016 at 12:39 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `transcended`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill_details`
--

CREATE TABLE `bill_details` (
  `bill_no.` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `delivery_date` int(11) NOT NULL,
  `current_date` int(11) NOT NULL,
  `reffer_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `advance` int(11) NOT NULL,
  `measutement_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bill_item_details`
--

CREATE TABLE `bill_item_details` (
  `bill_item_id` int(11) NOT NULL,
  `bill_no` int(11) NOT NULL,
  `item_code_no` int(11) NOT NULL,
  `item_catagory_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `catagory_details`
--

CREATE TABLE `catagory_details` (
  `catagory_id` int(11) NOT NULL,
  `catagory_name` varchar(50) NOT NULL,
  `stiching_charge` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `company_details`
--

CREATE TABLE `company_details` (
  `company_id` int(11) NOT NULL,
  `company_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_details`
--

CREATE TABLE `customer_details` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(50) NOT NULL,
  `phone_no.` int(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `measurement_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_transaction`
--

CREATE TABLE `customer_transaction` (
  `transaction_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `bill_no` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `item_catagory`
--

CREATE TABLE `item_catagory` (
  `item_code_no` int(11) NOT NULL,
  `catagory_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `item_deduction`
--

CREATE TABLE `item_deduction` (
  `deduction_id` int(11) NOT NULL,
  `code_no` int(11) NOT NULL,
  `deducted_quantity` int(11) NOT NULL,
  `deducted_date` int(11) NOT NULL,
  `bill_no` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `item_details`
--

CREATE TABLE `item_details` (
  `item_code_no` int(11) NOT NULL,
  `company_id` int(11) NOT NULL,
  `catagory_id` int(11) NOT NULL,
  `current_quantity` int(11) NOT NULL,
  `added_date` int(11) NOT NULL,
  `selling_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `production_details`
--

CREATE TABLE `production_details` (
  `production_id` int(11) NOT NULL,
  `bill_no` int(11) NOT NULL,
  `remarks` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `production_item_details`
--

CREATE TABLE `production_item_details` (
  `production_id` int(11) NOT NULL,
  `bill_item_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `assigned_date` int(11) NOT NULL,
  `completed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reffer_details`
--

CREATE TABLE `reffer_details` (
  `reffer_id` int(11) NOT NULL,
  `reffer_by` int(11) NOT NULL,
  `reffer_to` int(11) NOT NULL,
  `royalty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `f_name` varchar(50) NOT NULL,
  `l_name` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(15) NOT NULL,
  `user_type_id` int(11) NOT NULL,
  `last_logged_in` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_type_details`
--

CREATE TABLE `user_type_details` (
  `user_type_id` int(11) NOT NULL,
  `user_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill_details`
--
ALTER TABLE `bill_details`
  ADD PRIMARY KEY (`bill_no.`);

--
-- Indexes for table `bill_item_details`
--
ALTER TABLE `bill_item_details`
  ADD PRIMARY KEY (`bill_item_id`);

--
-- Indexes for table `catagory_details`
--
ALTER TABLE `catagory_details`
  ADD PRIMARY KEY (`catagory_id`);

--
-- Indexes for table `company_details`
--
ALTER TABLE `company_details`
  ADD PRIMARY KEY (`company_id`);

--
-- Indexes for table `customer_transaction`
--
ALTER TABLE `customer_transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `item_catagory`
--
ALTER TABLE `item_catagory`
  ADD PRIMARY KEY (`item_code_no`,`catagory_id`);

--
-- Indexes for table `item_deduction`
--
ALTER TABLE `item_deduction`
  ADD PRIMARY KEY (`deduction_id`);

--
-- Indexes for table `item_details`
--
ALTER TABLE `item_details`
  ADD PRIMARY KEY (`item_code_no`);

--
-- Indexes for table `production_details`
--
ALTER TABLE `production_details`
  ADD PRIMARY KEY (`production_id`);

--
-- Indexes for table `reffer_details`
--
ALTER TABLE `reffer_details`
  ADD PRIMARY KEY (`reffer_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_type_details`
--
ALTER TABLE `user_type_details`
  ADD PRIMARY KEY (`user_type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill_details`
--
ALTER TABLE `bill_details`
  MODIFY `bill_no.` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `bill_item_details`
--
ALTER TABLE `bill_item_details`
  MODIFY `bill_item_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `catagory_details`
--
ALTER TABLE `catagory_details`
  MODIFY `catagory_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `company_details`
--
ALTER TABLE `company_details`
  MODIFY `company_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customer_transaction`
--
ALTER TABLE `customer_transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `item_deduction`
--
ALTER TABLE `item_deduction`
  MODIFY `deduction_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `item_details`
--
ALTER TABLE `item_details`
  MODIFY `item_code_no` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `production_details`
--
ALTER TABLE `production_details`
  MODIFY `production_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `reffer_details`
--
ALTER TABLE `reffer_details`
  MODIFY `reffer_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_type_details`
--
ALTER TABLE `user_type_details`
  MODIFY `user_type_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
