-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2017 at 08:23 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `attendance`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance_info`
--

CREATE TABLE `attendance_info` (
  `id` int(11) NOT NULL,
  `total_present` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ip_address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `attendance_user`
--

CREATE TABLE `attendance_user` (
  `attendance_id` int(11) NOT NULL,
  `uhf_tag_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` datetime DEFAULT NULL,
  `attendance_count` int(11) NOT NULL,
  `ip_address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance_user`
--

INSERT INTO `attendance_user` (`attendance_id`, `uhf_tag_id`, `date`, `modify_date`, `attendance_count`, `ip_address`) VALUES
(1, 'E20050004513025517906034', '2017-01-12 18:33:59', '2017-01-10 00:00:00', 1, 'sdsfsdfs'),
(2, 'E20050004513018122602BC5', '2017-01-12 18:57:25', NULL, 3, 'sdsd');

-- --------------------------------------------------------

--
-- Table structure for table `card_holders`
--

CREATE TABLE `card_holders` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uhf_tag_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(6) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `bloodgroup` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dateofbirth` date NOT NULL,
  `fathername` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `mothername` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cellphone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `presentaddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `parmanentaddress` varchar(100) NOT NULL,
  `enrollment_date` date NOT NULL,
  `expire_date` date DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `card_holders`
--

INSERT INTO `card_holders` (`id`, `uhf_tag_id`, `name`, `gender`, `bloodgroup`, `dateofbirth`, `fathername`, `mothername`, `email`, `cellphone`, `photo`, `presentaddress`, `parmanentaddress`, `enrollment_date`, `expire_date`, `type`) VALUES
('151-14-5061', 'E20050004513005719304D4F', 'pramit gunguly', 'male', 'asdf', '2017-01-10', 'sfs', 'sdf', 'sdf', 'sfs', 'img/profile.jpg', 'asf', 'sf', '2017-01-24', '2017-01-25', 'fsf'),
('151-15-4739', 'E20050004513018122602BC5', 'Ibrahim Khan', 'male', 'fas', '2017-01-03', 'fsd', 'sdf', 'sf', 'sfs', 'img/profile.jpg', 'sdf', 'sf', '2017-01-25', '2017-01-26', 'dfs'),
('151-15-5165', 'E20050004513025517906034', 'Soikat Hasan Ahmed', 'male', 'a+', '2017-01-09', 'sdasd', 'fasfasf', 'sfdas', 'asfas', 'img/profile.jpg', 'asf', 'sdf', '2017-01-24', '2017-01-25', 'sdfsaf');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance_info`
--
ALTER TABLE `attendance_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `attendance_info2` (`ip_address`);

--
-- Indexes for table `attendance_user`
--
ALTER TABLE `attendance_user`
  ADD PRIMARY KEY (`attendance_id`),
  ADD KEY `attendance_user2` (`ip_address`),
  ADD KEY `attendance_user3` (`uhf_tag_id`);

--
-- Indexes for table `card_holders`
--
ALTER TABLE `card_holders`
  ADD PRIMARY KEY (`uhf_tag_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance_info`
--
ALTER TABLE `attendance_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `attendance_user`
--
ALTER TABLE `attendance_user`
  MODIFY `attendance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2122;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance_user`
--
ALTER TABLE `attendance_user`
  ADD CONSTRAINT `attendance_user_ibfk_1` FOREIGN KEY (`uhf_tag_id`) REFERENCES `card_holders` (`uhf_tag_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
