-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 08, 2017 at 03:52 PM
-- Server version: 5.7.17-0ubuntu0.16.04.1
-- PHP Version: 7.0.15-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wmp`
--

-- --------------------------------------------------------

--
-- Table structure for table `action`
--

CREATE TABLE `action` (
  `Id_Act` int(11) NOT NULL,
  `IdPhoneInfo_Act` int(11) NOT NULL,
  `Sound_Act` tinyint(1) NOT NULL,
  `Logout_Act` tinyint(1) NOT NULL,
  `Broadcast_Act` tinyint(1) NOT NULL,
  `BroadcastSMS_Act` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `alarm`
--

CREATE TABLE `alarm` (
  `Id_Alarm` int(11) NOT NULL,
  `IdPhoneInfo_Alarm` int(11) NOT NULL,
  `IdLocation_Alarm` int(11) NOT NULL,
  `IdAlarmAccion_Alarm` int(11) NOT NULL,
  `SchedulePeriod_Alarm` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `alarmaccion`
--

CREATE TABLE `alarmaccion` (
  `Id_AlAcc` int(11) NOT NULL,
  `IdPlan_AlAcc` int(11) NOT NULL,
  `Desc_AlAcc` varchar(50) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `locationbookmark`
--

CREATE TABLE `locationbookmark` (
  `Id_Loc` int(11) NOT NULL,
  `IdPhoneInfo_Loc` int(11) NOT NULL,
  `Latitude_Loc` double NOT NULL,
  `Longitude_Loc` double NOT NULL,
  `Desc_Loc` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `Id_Men` int(11) NOT NULL,
  `IdPlan_Men` int(11) NOT NULL,
  `IdRole_Men` int(11) NOT NULL,
  `Name_Men` varchar(50) NOT NULL,
  `Url_Men` varchar(50) NOT NULL,
  `Class_Men` varchar(50) NOT NULL,
  `DisplayOrder_Men` int(11) NOT NULL,
  `IdParent_Men` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`Id_Men`, `IdPlan_Men`, `IdRole_Men`, `Name_Men`, `Url_Men`, `Class_Men`, `DisplayOrder_Men`, `IdParent_Men`) VALUES
(2, 1, 2, 'Realtime position Map', 'positioninfoadmin', 'fa fa-dashboard fa-fw', 1, 38),
(3, 1, 2, 'Position history Map', 'positionhist', 'fa fa-dashboard fa-fw', 2, 38),
(4, 4, 1, 'Dashboard', '', 'fa fa-dashboard fa-fw', 0, 0),
(5, 4, 1, 'Realtime position Map', 'positioninfoadmin', 'fa fa-dashboard fa-fw', 0, 24),
(6, 4, 1, 'Position history Map', 'positionhistAdmin', 'fa fa-dashboard fa-fw', 1, 24),
(7, 4, 1, 'Phone Info', 'phoneinfoadmin', 'fa fa-dashboard fa-fw', 0, 23),
(8, 4, 1, 'Users', '', 'fa fa-dashboard fa-fw', 4, 0),
(9, 4, 1, 'Phone Names', 'phonenamesadmin', 'fa fa-dashboard fa-fw', 1, 23),
(10, 4, 1, 'Reports', 'reports', 'fa fa-dashboard fa-fw', 7, 0),
(11, 1, 2, 'Dashboard', '', 'fa fa-dashboard fa-fw', 0, 0),
(12, 1, 1, 'Realtime position Map', 'positioninfoadmin', 'fa fa-dashboard fa-fw', 0, 28),
(13, 1, 1, 'Position history Map', 'positionhist', 'fa fa-dashboard fa-fw', 1, 28),
(14, 1, 1, 'Phone Info', 'phoneinfoadmin', 'fa fa-dashboard fa-fw', 0, 29),
(15, 4, 1, 'Action', 'actionadmin', 'fa fa-dashboard fa-fw', 3, 8),
(16, 4, 1, 'Notification', 'notificationadmin', 'fa fa-dashboard fa-fw', 4, 8),
(17, 4, 1, 'User Plan', 'userplanadmin', 'fa fa-dashboard fa-fw', 2, 8),
(18, 4, 1, 'Plan', 'planadmin', 'fa fa-dashboard fa-fw', 0, 26),
(19, 4, 1, 'Menu', 'menuadmin', 'fa fa-dashboard fa-fw', 0, 25),
(20, 4, 1, 'User Validations', 'uservalidationadmin', 'fa fa-dashboard fa-fw', 1, 8),
(21, 4, 1, 'Dashboard', 'dashboard', 'fa fa-dashboard fa-fw', 0, 4),
(22, 4, 1, 'User', 'usersadmin', 'fa fa-dashboard fa-fw', 0, 8),
(23, 4, 1, 'Phone', '', 'fa fa-dashboard fa-fw', 0, 0),
(24, 4, 1, 'Map', '', 'fa fa-dashboard fa-fw', 0, 0),
(25, 4, 1, 'Menu', '', 'fa fa-dashboard fa-fw', 0, 0),
(26, 4, 1, 'Plan', '', 'fa fa-dashboard fa-fw', 0, 0),
(27, 1, 2, 'Dashboard', 'dashboard', 'fa fa-dashboard fa-fw', 0, 11),
(28, 1, 1, 'Map', '', 'fa fa-dashboard fa-fw', 0, 0),
(29, 1, 1, 'Phone', '', 'fa fa-dashboard fa-fw', 0, 0),
(30, 4, 1, 'History Report', 'reporthistory', 'fa fa-dashboard fa-fw', 0, 10),
(31, 4, 1, 'Daily Report', 'reportday', 'fa fa-dashboard fa-fw', 1, 10),
(32, 4, 1, 'Location Bookmark', 'locationbookmark', 'fa fa-dashboard fa-fw', 0, 0),
(33, 4, 1, 'Alarm', 'locationalarm', 'fa fa-dashboard fa-fw', 1, 32),
(34, 4, 1, 'Locations', 'locations', 'fa fa-dashboard fa-fw', 1, 32),
(35, 4, 1, 'Facebook', '', 'fa fa-dashboard fa-fw', 0, 0),
(36, 4, 1, 'Facebook', 'facebookadmin', 'fa fa-dashboard fa-fw', 0, 35),
(37, 4, 1, 'Alarm Accion', 'alarmaccionadmin', 'fa fa-dashboard fa-fw', 2, 32),
(38, 1, 2, 'Map', '', 'fa fa-dashboard fa-fw', 0, 0),
(39, 4, 1, 'Gpx', '', 'fa fa-dashboard fa-fw', 0, 0),
(40, 4, 1, 'File Upload', 'gpxfileuploadadmin', 'fa fa-dashboard fa-fw', 0, 39),
(41, 1, 2, 'Gpx', '', 'fa fa-dashboard fa-fw', 0, 0),
(42, 1, 2, 'File Upload', 'gpxfileuploadadmin', 'fa fa-dashboard fa-fw', 0, 41),
(43, 1, 2, 'Phone', '', 'fa fa-dashboard fa-fw', 0, 0),
(44, 1, 2, 'Phone Names', 'phonenamesadmin', 'fa fa-dashboard fa-fw', 1, 43),
(45, 1, 2, 'Phone Info', 'phoneinfoadmin', 'fa fa-dashboard fa-fw', 0, 43);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `Id_Notif` int(11) NOT NULL,
  `IdUsuario_Notif` int(11) DEFAULT NULL,
  `Sms_Notif` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Read_Notif` tinyint(1) DEFAULT NULL,
  `Date_Notif` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Title_Notif` varchar(50) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `phoneinfo`
--

CREATE TABLE `phoneinfo` (
  `Id_PI` int(11) NOT NULL,
  `IdUsuario_PI` int(11) NOT NULL,
  `Imei_PI` varchar(15) NOT NULL,
  `Model_PI` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `phonenames`
--

CREATE TABLE `phonenames` (
  `Id_PhoN` int(11) NOT NULL,
  `IdPhoneInfo_PhoN` int(11) NOT NULL,
  `Desc_PhoN` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE `plan` (
  `Id_Pla` int(11) NOT NULL,
  `Cantuserlogged_Pla` int(11) NOT NULL,
  `Desc_Pla` varchar(50) NOT NULL,
  `HistoryReport_Pla` tinyint(1) NOT NULL,
  `RealTimeTraceReport_Pla` tinyint(1) NOT NULL,
  `Price_Pla` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plan`
--

INSERT INTO `plan` (`Id_Pla`, `Cantuserlogged_Pla`, `Desc_Pla`, `HistoryReport_Pla`, `RealTimeTraceReport_Pla`, `Price_Pla`) VALUES
(1, 1, 'Lifetime', 0, 0, 0),
(2, 5, 'Basic', 0, 0, 1),
(3, 10, 'Premium', 1, 1, 3),
(4, 0, 'Unlimited', 1, 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `positioninfo`
--

CREATE TABLE `positioninfo` (
  `Id_PosI` int(11) NOT NULL,
  `Latitude_PosI` double NOT NULL,
  `Longitude_PosI` double NOT NULL,
  `Date_PosI` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Accuracy_PosI` double NOT NULL,
  `IdPhoneInfo_PosI` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `positioninfo_hist`
--

CREATE TABLE `positioninfo_hist` (
  `Id_PosI` int(11) NOT NULL,
  `Latitude_PosI` double NOT NULL,
  `Longitude_PosI` double NOT NULL,
  `Date_PosI` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Accuracy_PosI` double NOT NULL,
  `IdPhoneInfo_PosI` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `Id_Ro` int(11) NOT NULL,
  `Desc_Ro` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`Id_Ro`, `Desc_Ro`) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- --------------------------------------------------------

--
-- Table structure for table `userplan`
--

CREATE TABLE `userplan` (
  `Id_UPla` int(11) NOT NULL,
  `IdUsuario_UPla` int(11) NOT NULL,
  `IdPlan_UPla` int(11) NOT NULL,
  `FechaVenc_UPla` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Id_Usu` int(11) NOT NULL,
  `Email_Usu` varchar(50) NOT NULL,
  `Password_Usu` varchar(50) NOT NULL,
  `Token_Usu` varchar(40) NOT NULL,
  `IdRole_Usu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `uservalidation`
--

CREATE TABLE `uservalidation` (
  `Id_Uv` int(11) NOT NULL,
  `IdPhoneInfo_Uv` int(11) NOT NULL,
  `Number_Uv` int(11) NOT NULL,
  `Validated_Uv` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `action`
--
ALTER TABLE `action`
  ADD PRIMARY KEY (`Id_Act`);

--
-- Indexes for table `alarm`
--
ALTER TABLE `alarm`
  ADD PRIMARY KEY (`Id_Alarm`);

--
-- Indexes for table `alarmaccion`
--
ALTER TABLE `alarmaccion`
  ADD PRIMARY KEY (`Id_AlAcc`);

--
-- Indexes for table `locationbookmark`
--
ALTER TABLE `locationbookmark`
  ADD PRIMARY KEY (`Id_Loc`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`Id_Men`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD UNIQUE KEY `Id_Notif` (`Id_Notif`);

--
-- Indexes for table `phoneinfo`
--
ALTER TABLE `phoneinfo`
  ADD PRIMARY KEY (`Id_PI`);

--
-- Indexes for table `phonenames`
--
ALTER TABLE `phonenames`
  ADD PRIMARY KEY (`Id_PhoN`);

--
-- Indexes for table `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`Id_Pla`);

--
-- Indexes for table `positioninfo`
--
ALTER TABLE `positioninfo`
  ADD PRIMARY KEY (`Id_PosI`);

--
-- Indexes for table `positioninfo_hist`
--
ALTER TABLE `positioninfo_hist`
  ADD PRIMARY KEY (`Id_PosI`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`Id_Ro`);

--
-- Indexes for table `userplan`
--
ALTER TABLE `userplan`
  ADD PRIMARY KEY (`Id_UPla`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id_Usu`),
  ADD UNIQUE KEY `Email_Usu` (`Email_Usu`);

--
-- Indexes for table `uservalidation`
--
ALTER TABLE `uservalidation`
  ADD PRIMARY KEY (`Id_Uv`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `action`
--
ALTER TABLE `action`
  MODIFY `Id_Act` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `alarm`
--
ALTER TABLE `alarm`
  MODIFY `Id_Alarm` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `alarmaccion`
--
ALTER TABLE `alarmaccion`
  MODIFY `Id_AlAcc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `locationbookmark`
--
ALTER TABLE `locationbookmark`
  MODIFY `Id_Loc` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `Id_Men` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `Id_Notif` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `phoneinfo`
--
ALTER TABLE `phoneinfo`
  MODIFY `Id_PI` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `phonenames`
--
ALTER TABLE `phonenames`
  MODIFY `Id_PhoN` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `plan`
--
ALTER TABLE `plan`
  MODIFY `Id_Pla` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `positioninfo`
--
ALTER TABLE `positioninfo`
  MODIFY `Id_PosI` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT for table `positioninfo_hist`
--
ALTER TABLE `positioninfo_hist`
  MODIFY `Id_PosI` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49154;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `Id_Ro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `userplan`
--
ALTER TABLE `userplan`
  MODIFY `Id_UPla` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `Id_Usu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;
--
-- AUTO_INCREMENT for table `uservalidation`
--
ALTER TABLE `uservalidation`
  MODIFY `Id_Uv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
