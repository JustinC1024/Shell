/*
Navicat MySQL Data Transfer

Source Server         : qwe
Source Server Version : 50727
Source Host           : 192.168.1.8:3306
Source Database       : chm

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-04-30 16:39:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cmp_bill
-- ----------------------------
DROP TABLE IF EXISTS `cmp_bill`;
CREATE TABLE `cmp_bill` (
  `pk_bill` varchar(20) NOT NULL,
  `pk_support` varchar(20) DEFAULT NULL,
  `pk_customer` varchar(20) DEFAULT NULL,
  `pk_sell_room` varchar(20) DEFAULT NULL,
  `pk_lease_room` varchar(20) DEFAULT NULL,
  `pk_clerk` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `result` varchar(500) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_bill`),
  KEY `pk_customer` (`pk_customer`),
  KEY `pk_clerk` (`pk_clerk`),
  KEY `pk_support` (`pk_support`),
  KEY `ts` (`ts`),
  KEY `pk_sell_room` (`pk_sell_room`),
  KEY `pk_lease_room` (`pk_lease_room`),
  CONSTRAINT `bill_clerk` FOREIGN KEY (`pk_clerk`) REFERENCES `db_clerk` (`pk_clerk`),
  CONSTRAINT `bill_customer` FOREIGN KEY (`pk_customer`) REFERENCES `db_customer` (`pk_customer`),
  CONSTRAINT `bill_lease` FOREIGN KEY (`pk_lease_room`) REFERENCES `db_lease_room` (`pk_lease_room`),
  CONSTRAINT `bill_sell` FOREIGN KEY (`pk_sell_room`) REFERENCES `db_sell_room` (`pk_sell_room`),
  CONSTRAINT `bill_support` FOREIGN KEY (`pk_support`) REFERENCES `db_support` (`pk_support`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cmp_follow
-- ----------------------------
DROP TABLE IF EXISTS `cmp_follow`;
CREATE TABLE `cmp_follow` (
  `pk_follow` varchar(20) NOT NULL,
  `pk_customer` varchar(20) DEFAULT NULL,
  `pk_sell_room` varchar(20) DEFAULT NULL,
  `pk_lease_room` varchar(20) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_follow`),
  KEY `pk_customer` (`pk_customer`),
  KEY `ts` (`ts`),
  KEY `pk_sell_room` (`pk_sell_room`),
  KEY `pk_lease_room` (`pk_lease_room`),
  CONSTRAINT `follow_customer` FOREIGN KEY (`pk_customer`) REFERENCES `db_customer` (`pk_customer`),
  CONSTRAINT `follow_lease` FOREIGN KEY (`pk_lease_room`) REFERENCES `db_lease_room` (`pk_lease_room`),
  CONSTRAINT `follow_sell` FOREIGN KEY (`pk_sell_room`) REFERENCES `db_sell_room` (`pk_sell_room`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cmp_identity
-- ----------------------------
DROP TABLE IF EXISTS `cmp_identity`;
CREATE TABLE `cmp_identity` (
  `pk_identity` varchar(20) NOT NULL,
  `pk_customer` varchar(20) DEFAULT NULL,
  `pk_clerk` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_identity`),
  UNIQUE KEY `pk_customer` (`pk_customer`),
  KEY `pk_clerk` (`pk_clerk`),
  KEY `ts` (`ts`),
  CONSTRAINT `identity_clerk` FOREIGN KEY (`pk_clerk`) REFERENCES `db_clerk` (`pk_clerk`),
  CONSTRAINT `identity_customer` FOREIGN KEY (`pk_customer`) REFERENCES `db_customer` (`pk_customer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cmp_owner
-- ----------------------------
DROP TABLE IF EXISTS `cmp_owner`;
CREATE TABLE `cmp_owner` (
  `pk_owner` varchar(20) NOT NULL,
  `pk_customer` varchar(20) DEFAULT NULL,
  `pk_clerk` varchar(20) DEFAULT NULL,
  `pk_sell_room` varchar(20) DEFAULT NULL,
  `pk_lease_room` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_owner`),
  UNIQUE KEY `pk_lease_room` (`pk_lease_room`),
  UNIQUE KEY `pk_sell_room` (`pk_sell_room`),
  KEY `pk_clerk` (`pk_clerk`),
  KEY `pk_customer` (`pk_customer`),
  KEY `ts` (`ts`),
  CONSTRAINT `owner_clerk` FOREIGN KEY (`pk_clerk`) REFERENCES `db_clerk` (`pk_clerk`),
  CONSTRAINT `owner_customer` FOREIGN KEY (`pk_customer`) REFERENCES `db_customer` (`pk_customer`),
  CONSTRAINT `owner_lease_room` FOREIGN KEY (`pk_lease_room`) REFERENCES `db_lease_room` (`pk_lease_room`),
  CONSTRAINT `owner_sell_room` FOREIGN KEY (`pk_sell_room`) REFERENCES `db_sell_room` (`pk_sell_room`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cmp_temp_img
-- ----------------------------
DROP TABLE IF EXISTS `cmp_temp_img`;
CREATE TABLE `cmp_temp_img` (
  `pk_temp_img` varchar(20) NOT NULL,
  `pk_foreign` varchar(25) DEFAULT NULL,
  `img` longblob,
  `dr` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`pk_temp_img`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_clerk
-- ----------------------------
DROP TABLE IF EXISTS `db_clerk`;
CREATE TABLE `db_clerk` (
  `pk_clerk` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `introduction` varchar(500) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `pk_jurisdiction` varchar(20) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `img` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pk_clerk`),
  UNIQUE KEY `account` (`account`),
  UNIQUE KEY `phone` (`phone`),
  KEY `parent` (`parent`),
  KEY `jurisdiction` (`pk_jurisdiction`),
  KEY `name` (`name`),
  KEY `ts` (`ts`),
  CONSTRAINT `clerk_jurisdiction` FOREIGN KEY (`pk_jurisdiction`) REFERENCES `db_jurisdiction` (`pk_jurisdiction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_customer
-- ----------------------------
DROP TABLE IF EXISTS `db_customer`;
CREATE TABLE `db_customer` (
  `pk_customer` varchar(20) NOT NULL,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `portrait` longblob,
  `name` varchar(20) DEFAULT NULL,
  `identity` varchar(50) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `birth` varchar(10) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_customer`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `account` (`account`),
  KEY `name` (`name`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_jurisdiction
-- ----------------------------
DROP TABLE IF EXISTS `db_jurisdiction`;
CREATE TABLE `db_jurisdiction` (
  `pk_jurisdiction` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_jurisdiction`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_lease_price
-- ----------------------------
DROP TABLE IF EXISTS `db_lease_price`;
CREATE TABLE `db_lease_price` (
  `pk_lease_price` varchar(20) NOT NULL,
  `payment` varchar(10) DEFAULT NULL,
  `rent` int(11) DEFAULT NULL,
  `deposit` int(11) DEFAULT NULL,
  `service` int(11) DEFAULT NULL,
  `agency` varchar(10) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_lease_price`),
  KEY `rent` (`rent`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_lease_room
-- ----------------------------
DROP TABLE IF EXISTS `db_lease_room`;
CREATE TABLE `db_lease_room` (
  `pk_lease_room` varchar(20) NOT NULL,
  `title` varchar(60) DEFAULT NULL,
  `county` varchar(6) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `apartment` varchar(30) DEFAULT NULL,
  `mode` int(11) DEFAULT NULL,
  `public_ts` varchar(19) DEFAULT NULL,
  `update_ts` varchar(19) DEFAULT NULL,
  `pk_lease_price` varchar(20) DEFAULT NULL,
  `pk_lease_room_basic` varchar(20) DEFAULT NULL,
  `pk_lease_room_mating` varchar(20) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_lease_room`),
  UNIQUE KEY `pk_lease_price` (`pk_lease_price`),
  UNIQUE KEY `pk_lease_room_basic` (`pk_lease_room_basic`),
  KEY `apartment` (`apartment`),
  KEY `county` (`county`),
  KEY `update_ts` (`update_ts`),
  KEY `title` (`title`),
  KEY `ts` (`ts`),
  KEY `lease_matting` (`pk_lease_room_mating`),
  CONSTRAINT `lease_basic` FOREIGN KEY (`pk_lease_room_basic`) REFERENCES `db_lease_room_basic` (`pk_lease_room_basic`),
  CONSTRAINT `lease_matting` FOREIGN KEY (`pk_lease_room_mating`) REFERENCES `db_lease_room_mating` (`pk_lease_room_mating`),
  CONSTRAINT `lease_price` FOREIGN KEY (`pk_lease_price`) REFERENCES `db_lease_price` (`pk_lease_price`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_lease_room_basic
-- ----------------------------
DROP TABLE IF EXISTS `db_lease_room_basic`;
CREATE TABLE `db_lease_room_basic` (
  `pk_lease_room_basic` varchar(20) NOT NULL,
  `area` int(11) DEFAULT NULL,
  `orientation` varchar(6) DEFAULT NULL,
  `check_in` varchar(10) DEFAULT NULL,
  `maintain` varchar(19) DEFAULT NULL,
  `floor` varchar(10) DEFAULT NULL,
  `floor_num` int(11) DEFAULT NULL,
  `ladder` varchar(10) DEFAULT NULL,
  `parking` varchar(10) DEFAULT NULL,
  `water` varchar(10) DEFAULT NULL,
  `electricity` varchar(10) DEFAULT NULL,
  `gas` varchar(10) DEFAULT NULL,
  `term` varchar(10) DEFAULT NULL,
  `heating` varchar(10) DEFAULT NULL,
  `inspection` varchar(10) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_lease_room_basic`),
  KEY `orientation` (`orientation`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_lease_room_mating
-- ----------------------------
DROP TABLE IF EXISTS `db_lease_room_mating`;
CREATE TABLE `db_lease_room_mating` (
  `pk_lease_room_mating` varchar(20) NOT NULL,
  `television` int(11) DEFAULT NULL,
  `fridge` int(11) DEFAULT NULL,
  `washing` int(11) DEFAULT NULL,
  `air` int(11) DEFAULT NULL,
  `heater` int(11) DEFAULT NULL,
  `bed` int(11) DEFAULT NULL,
  `heating` int(11) DEFAULT NULL,
  `broadband` int(11) DEFAULT NULL,
  `wardrobe` int(11) DEFAULT NULL,
  `gas` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_lease_room_mating`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_reptile_url
-- ----------------------------
DROP TABLE IF EXISTS `db_reptile_url`;
CREATE TABLE `db_reptile_url` (
  `pk_reptile_url` varchar(20) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `url` varchar(600) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`pk_reptile_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_room_img
-- ----------------------------
DROP TABLE IF EXISTS `db_room_img`;
CREATE TABLE `db_room_img` (
  `pk_room_img` varchar(20) NOT NULL,
  `title` varchar(60) DEFAULT NULL,
  `img` longblob,
  `ind` int(11) DEFAULT NULL,
  `pk_room` varchar(20) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_room_img`),
  KEY `pk_room` (`pk_room`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_sell_compartment_info
-- ----------------------------
DROP TABLE IF EXISTS `db_sell_compartment_info`;
CREATE TABLE `db_sell_compartment_info` (
  `pk_sell_compartment_info` varchar(20) NOT NULL,
  `area` float(6,2) DEFAULT NULL,
  `direction` varchar(20) DEFAULT NULL,
  `windows` varchar(10) DEFAULT NULL,
  `pk_sell_compartment_type` varchar(20) DEFAULT NULL,
  `pk_sell_room_compartment` varchar(20) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_sell_compartment_info`),
  KEY `info_type` (`pk_sell_compartment_type`),
  KEY `compartment` (`pk_sell_room_compartment`),
  KEY `pk_sell_room_compartment` (`pk_sell_room_compartment`),
  KEY `ts` (`ts`),
  CONSTRAINT `info_compartment` FOREIGN KEY (`pk_sell_room_compartment`) REFERENCES `db_sell_room_compartment` (`pk_sell_room_compartment`),
  CONSTRAINT `info_type` FOREIGN KEY (`pk_sell_compartment_type`) REFERENCES `db_sell_compartment_type` (`pk_sell_compartment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_sell_compartment_type
-- ----------------------------
DROP TABLE IF EXISTS `db_sell_compartment_type`;
CREATE TABLE `db_sell_compartment_type` (
  `pk_sell_compartment_type` varchar(20) NOT NULL,
  `title` varchar(10) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_sell_compartment_type`),
  UNIQUE KEY `pk` (`pk_sell_compartment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_sell_price
-- ----------------------------
DROP TABLE IF EXISTS `db_sell_price`;
CREATE TABLE `db_sell_price` (
  `pk_sell_price` varchar(20) NOT NULL,
  `total_price` int(11) DEFAULT NULL,
  `unit_price` float(9,1) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_sell_price`),
  KEY `total_price` (`total_price`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_sell_room
-- ----------------------------
DROP TABLE IF EXISTS `db_sell_room`;
CREATE TABLE `db_sell_room` (
  `pk_sell_room` varchar(20) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `county` varchar(6) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `public_ts` varchar(19) DEFAULT NULL,
  `update_ts` varchar(19) DEFAULT NULL,
  `pk_sell_price` varchar(20) DEFAULT NULL,
  `pk_sell_room_transaction` varchar(20) DEFAULT NULL,
  `pk_sell_room_basic` varchar(20) DEFAULT NULL,
  `pk_sell_room_characteristic` varchar(20) DEFAULT NULL,
  `pk_sell_room_compartment` varchar(20) DEFAULT NULL,
  `pk_village` varchar(20) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_sell_room`),
  UNIQUE KEY `pk_sell_price` (`pk_sell_price`),
  UNIQUE KEY `pk_sell_room_basic` (`pk_sell_room_basic`),
  KEY `sell_characteristic` (`pk_sell_room_characteristic`),
  KEY `sell_compartment` (`pk_sell_room_compartment`),
  KEY `sell_transaction` (`pk_sell_room_transaction`),
  KEY `price` (`pk_sell_price`),
  KEY `village` (`pk_village`),
  KEY `public_ts` (`public_ts`),
  KEY `title` (`title`),
  KEY `county` (`county`),
  KEY `pk_village` (`pk_village`),
  KEY `ts` (`ts`),
  CONSTRAINT `sell_basic` FOREIGN KEY (`pk_sell_room_basic`) REFERENCES `db_sell_room_basic` (`pk_sell_room_basic`),
  CONSTRAINT `sell_characteristic` FOREIGN KEY (`pk_sell_room_characteristic`) REFERENCES `db_sell_room_characteristic` (`pk_sell_room_characteristic`),
  CONSTRAINT `sell_compartment` FOREIGN KEY (`pk_sell_room_compartment`) REFERENCES `db_sell_room_compartment` (`pk_sell_room_compartment`),
  CONSTRAINT `sell_price` FOREIGN KEY (`pk_sell_price`) REFERENCES `db_sell_price` (`pk_sell_price`),
  CONSTRAINT `sell_transaction` FOREIGN KEY (`pk_sell_room_transaction`) REFERENCES `db_sell_room_transaction` (`pk_sell_room_transaction`),
  CONSTRAINT `sell_village` FOREIGN KEY (`pk_village`) REFERENCES `db_village` (`pk_village`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_sell_room_basic
-- ----------------------------
DROP TABLE IF EXISTS `db_sell_room_basic`;
CREATE TABLE `db_sell_room_basic` (
  `pk_sell_room_basic` varchar(20) NOT NULL,
  `apartment` varchar(20) DEFAULT NULL,
  `floor` varchar(10) DEFAULT NULL,
  `floor_num` int(11) DEFAULT NULL,
  `built_up_area` varchar(10) DEFAULT NULL,
  `area` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `orientation` varchar(10) DEFAULT NULL,
  `building` varchar(10) DEFAULT NULL,
  `decoration` varchar(6) DEFAULT NULL,
  `structure` varchar(6) DEFAULT NULL,
  `ladder` varchar(10) DEFAULT NULL,
  `property_years` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_sell_room_basic`),
  KEY `apartment` (`apartment`),
  KEY `built_up_area` (`built_up_area`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_sell_room_characteristic
-- ----------------------------
DROP TABLE IF EXISTS `db_sell_room_characteristic`;
CREATE TABLE `db_sell_room_characteristic` (
  `pk_sell_room_characteristic` varchar(20) NOT NULL,
  `core` varchar(600) DEFAULT NULL,
  `apartment` varchar(600) DEFAULT NULL,
  `village` varchar(600) DEFAULT NULL,
  `crowd` varchar(600) DEFAULT NULL,
  `situation` varchar(600) DEFAULT NULL,
  `traffic` varchar(600) DEFAULT NULL,
  `surround` varchar(600) DEFAULT NULL,
  `taxation` varchar(600) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_sell_room_characteristic`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_sell_room_compartment
-- ----------------------------
DROP TABLE IF EXISTS `db_sell_room_compartment`;
CREATE TABLE `db_sell_room_compartment` (
  `pk_sell_room_compartment` varchar(20) NOT NULL,
  `img` longblob,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_sell_room_compartment`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_sell_room_transaction
-- ----------------------------
DROP TABLE IF EXISTS `db_sell_room_transaction`;
CREATE TABLE `db_sell_room_transaction` (
  `pk_sell_room_transaction` varchar(20) NOT NULL,
  `listing_ts` varchar(20) DEFAULT NULL,
  `ownership` varchar(10) DEFAULT NULL,
  `last_ts` varchar(20) DEFAULT NULL,
  `purpose` varchar(10) DEFAULT NULL,
  `years` varchar(10) DEFAULT NULL,
  `property` varchar(10) DEFAULT NULL,
  `mortgage` varchar(60) DEFAULT NULL,
  `permit` varchar(20) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_sell_room_transaction`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_support
-- ----------------------------
DROP TABLE IF EXISTS `db_support`;
CREATE TABLE `db_support` (
  `pk_support` varchar(20) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `introduction` varchar(500) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_support`),
  KEY `title` (`title`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_village
-- ----------------------------
DROP TABLE IF EXISTS `db_village`;
CREATE TABLE `db_village` (
  `pk_village` varchar(20) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `introduction` varchar(50) DEFAULT NULL,
  `county` varchar(6) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `consult` int(11) DEFAULT NULL,
  `building_type` varchar(30) DEFAULT NULL,
  `property` varchar(60) DEFAULT NULL,
  `company` varchar(60) DEFAULT NULL,
  `developer` varchar(60) DEFAULT NULL,
  `building_num` int(11) DEFAULT NULL,
  `room_num` int(11) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `update_ts` varchar(19) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_village`),
  KEY `county` (`county`),
  KEY `consult` (`consult`),
  KEY `title` (`title`),
  KEY `update_ts` (`update_ts`),
  KEY `ts` (`ts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for db_village_img
-- ----------------------------
DROP TABLE IF EXISTS `db_village_img`;
CREATE TABLE `db_village_img` (
  `pk_village_img` varchar(20) NOT NULL,
  `img` longblob,
  `pk_village` varchar(20) DEFAULT NULL,
  `ts` varchar(19) DEFAULT NULL,
  `spare1` varchar(50) DEFAULT NULL,
  `spare2` varchar(50) DEFAULT NULL,
  `spare3` varchar(50) DEFAULT NULL,
  `ind` int(11) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_village_img`),
  KEY `village` (`pk_village`),
  KEY `pk_village` (`pk_village`),
  KEY `ts` (`ts`),
  CONSTRAINT `img_village` FOREIGN KEY (`pk_village`) REFERENCES `db_village` (`pk_village`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
