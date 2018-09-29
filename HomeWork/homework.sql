/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : homework

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-09-29 15:32:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `flag` varchar(20) DEFAULT NULL,
  `username` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1', '2', '2018-09-28 15:10:33', '2018-09-28 15:10:37', '1', '123');
INSERT INTO `t_log` VALUES ('2', '2', '2018-09-29 13:05:31', '2018-09-29 13:51:49', '1', '123');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zy', '123', '1234325', '2018-08-13 18:00:31');
INSERT INTO `t_user` VALUES ('2', 'zy1', '1234', '1234325325', '2018-08-13 20:32:19');
INSERT INTO `t_user` VALUES ('3', '吃盖浇饭', '123', '18738161475', null);
INSERT INTO `t_user` VALUES ('4', 'zy333', '123', '18738161475', null);
INSERT INTO `t_user` VALUES ('6', 'sdfs', '123', '324324', '2018-09-06 20:39:28');
INSERT INTO `t_user` VALUES ('7', '123', '123', '18738161476', '2018-09-29 00:00:00');
INSERT INTO `t_user` VALUES ('8', '1234', '21321', '18738161475', '2018-09-29 00:00:00');
INSERT INTO `t_user` VALUES ('9', '12345', '21321', '18738161475', '2018-09-29 00:00:00');
INSERT INTO `t_user` VALUES ('10', '1231', '21321', '18738161475', '2018-09-29 00:00:00');
