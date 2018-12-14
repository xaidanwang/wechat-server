/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.9.170
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 192.168.9.170:3306
 Source Schema         : mppr_face_model

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 14/12/2018 09:50:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_vss_face_dispatching_library_rl
-- ----------------------------
DROP TABLE IF EXISTS `t_vss_face_dispatching_library_rl`;
CREATE TABLE `t_vss_face_dispatching_library_rl`  (
  `keyid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `taskuuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '����UUID',
  `libraryuuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '��Ա��UUID',
  `createtime` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `updatetime` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '����ʱ��',
  PRIMARY KEY (`keyid`) USING BTREE,
  INDEX `Index_1`(`taskuuid`) USING BTREE,
  INDEX `Index_2`(`libraryuuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '�������غͱȶԿ���Ϣ��' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_vss_face_dispatching_library_rl
-- ----------------------------
INSERT INTO `t_vss_face_dispatching_library_rl` VALUES (9, '2662e3f694344982b84afff97c1cbe18', 'qwertyuiopdfghjkl', '2018-12-11 01:54:23', '2018-12-11 01:54:23');
INSERT INTO `t_vss_face_dispatching_library_rl` VALUES (10, '2662e3f694344982b84afff97c1cbe18', 'fasdfasodfjoiwep', '2018-12-11 01:54:23', '2018-12-11 01:54:23');
INSERT INTO `t_vss_face_dispatching_library_rl` VALUES (11, 'cd239345124744a6ae66c7ca2671abd0', 'qwertyuiopdfghjkl', '2018-12-11 01:58:43', '2018-12-11 01:58:43');
INSERT INTO `t_vss_face_dispatching_library_rl` VALUES (12, 'cd239345124744a6ae66c7ca2671abd0', 'fasdfasodfjoiwep', '2018-12-11 01:58:43', '2018-12-11 01:58:43');
INSERT INTO `t_vss_face_dispatching_library_rl` VALUES (13, 'a497f0c8cc044861bf87ead8031b1585', 'qwertyuiopdfghjkl', '2018-12-11 03:12:52', '2018-12-11 03:12:52');
INSERT INTO `t_vss_face_dispatching_library_rl` VALUES (14, 'a497f0c8cc044861bf87ead8031b1585', 'fasdfasodfjoiwep', '2018-12-11 03:12:52', '2018-12-11 03:12:52');
INSERT INTO `t_vss_face_dispatching_library_rl` VALUES (17, '5a107c33cd21480cb6e7940da8200c42', '1111111111111111111', '2018-12-13 02:35:22', '2018-12-13 02:35:22');
INSERT INTO `t_vss_face_dispatching_library_rl` VALUES (18, '5a107c33cd21480cb6e7940da8200c42', '22222222222222222', '2018-12-13 02:35:22', '2018-12-13 02:35:22');

SET FOREIGN_KEY_CHECKS = 1;
