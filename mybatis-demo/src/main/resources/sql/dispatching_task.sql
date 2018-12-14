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

 Date: 14/12/2018 09:44:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_vss_face_dispatching_task
-- ----------------------------
DROP TABLE IF EXISTS `t_vss_face_dispatching_task`;
CREATE TABLE `t_vss_face_dispatching_task`  (
  `keyid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `taskuuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '����UUID',
  `taskname` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '��������',
  `useruuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '�˺�UUID',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '����������',
  `reason` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `threshold` int(11) DEFAULT NULL,
  `isglobal` tinyint(1) DEFAULT 0 COMMENT '�Ƿ�ȫ��',
  `enabled` tinyint(1) DEFAULT 1 COMMENT '�Ƿ�����',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '�Ƿ�ɾ��',
  `ispopup` tinyint(1) DEFAULT 0 COMMENT '�Ƿ?�',
  `alarmed` tinyint(1) DEFAULT NULL COMMENT '�Ƿ?�',
  `quality` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '��������',
  `alarmlevel` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '�����ȼ�',
  `createtime` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '����ʱ��',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `platformuuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'ƽ̨UUID',
  `precode` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '���ǰ��ʶ',
  `prename` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '���ǰ����',
  PRIMARY KEY (`keyid`) USING BTREE,
  UNIQUE INDEX `Index_1`(`taskuuid`) USING BTREE,
  INDEX `Index_2`(`useruuid`) USING BTREE,
  INDEX `Index_4`(`deleted`) USING BTREE,
  INDEX `Index_6`(`platformuuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '�������������' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_vss_face_dispatching_task
-- ----------------------------
INSERT INTO `t_vss_face_dispatching_task` VALUES (8, 'a497f0c8cc044861bf87ead8031b1585', 'asdfasdf', 'asdfasdfasd', 'asdfasdf', 'asdf', 80, 0, 1, 0, 0, 1, '{\"blur\":0.0,\"completeness\":0.0,\"occlusion\":0.0,\"pitch\":0.0,\"roll\":0.0,\"yaw\":0.0}', NULL, '2018-12-11 03:12:52', '2018-12-13 00:43:54', NULL, '', '', '');
INSERT INTO `t_vss_face_dispatching_task` VALUES (15, '5a107c33cd21480cb6e7940da8200c42', '1111', '11111', NULL, NULL, 80, 0, 1, 0, 0, 1, '{\"blur\":0.0,\"completeness\":0.0,\"mode\":\"1234545\",\"occlusion\":0.0,\"pitch\":0.0,\"roll\":0.0,\"yaw\":0.0}', NULL, '2018-12-13 02:35:22', '2018-12-13 02:35:29', NULL, '', '', '');

SET FOREIGN_KEY_CHECKS = 1;
