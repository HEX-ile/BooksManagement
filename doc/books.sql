/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : books

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 11/09/2022 21:55:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bm_book
-- ----------------------------
DROP TABLE IF EXISTS `bm_book`;
CREATE TABLE `bm_book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书名',
  `book_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍编码',
  `publishing_house` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `book_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `bookshelf_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书架号',
  `over` int NULL DEFAULT NULL COMMENT '库存',
  `status` int NULL DEFAULT NULL COMMENT '书本状态0=有效 1=已借完 2=删除',
  `creater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_book
-- ----------------------------
INSERT INTO `bm_book` VALUES (1, '高数', 'ICSL1234', '大学出版', '数学', 'dolor', '2-222', 2, 0, 'sys', '2022-09-11 15:50:49', NULL, NULL);
INSERT INTO `bm_book` VALUES (2, '开发', 'ICSL1235', '清华出版', '技术', 'enim', '2-234', 2, 0, 'sys', '2022-09-11 15:50:49', '2', '2022-09-11 16:37:38');

-- ----------------------------
-- Table structure for bm_borrow
-- ----------------------------
DROP TABLE IF EXISTS `bm_borrow`;
CREATE TABLE `bm_borrow`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `book_id` int NULL DEFAULT NULL COMMENT '书籍id',
  `borrow_time` datetime NULL DEFAULT NULL COMMENT '借书日期',
  `return_time` datetime NULL DEFAULT NULL COMMENT '还书日期',
  `status` int NULL DEFAULT NULL COMMENT '是否归还 0=未归还 1=已归还 2=已超期',
  `creater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_borrow
-- ----------------------------

-- ----------------------------
-- Table structure for bm_permission
-- ----------------------------
DROP TABLE IF EXISTS `bm_permission`;
CREATE TABLE `bm_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int NULL DEFAULT NULL COMMENT '上级权限ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `creater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_permission
-- ----------------------------
INSERT INTO `bm_permission` VALUES (1, 0, 'user', '用户角色', 'system', '2022-09-11 11:29:55', NULL, NULL, NULL);
INSERT INTO `bm_permission` VALUES (2, 0, 'manage', '管理员', 'system', '2022-09-11 11:29:55', NULL, NULL, NULL);
INSERT INTO `bm_permission` VALUES (3, 0, 'admin', '系统管理员', 'system', '2022-09-11 11:29:55', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for bm_role
-- ----------------------------
DROP TABLE IF EXISTS `bm_role`;
CREATE TABLE `bm_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `creater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_role
-- ----------------------------
INSERT INTO `bm_role` VALUES (1, 'user', '用户角色', 'system', '2022-09-11 11:29:55', NULL, NULL, NULL);
INSERT INTO `bm_role` VALUES (2, 'manage', '管理员', 'system', '2022-09-11 11:29:55', NULL, NULL, NULL);
INSERT INTO `bm_role` VALUES (3, 'admin', '系统管理员', 'system', '2022-09-11 11:29:55', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for bm_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `bm_role_permission`;
CREATE TABLE `bm_role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` int NULL DEFAULT NULL COMMENT '权限ID',
  `creater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_role_permission
-- ----------------------------
INSERT INTO `bm_role_permission` VALUES (1, 1, 1, 'system', '2022-09-11 11:29:55', NULL, NULL, NULL);
INSERT INTO `bm_role_permission` VALUES (2, 2, 2, 'system', '2022-09-11 11:29:55', NULL, NULL, NULL);
INSERT INTO `bm_role_permission` VALUES (3, 3, 3, 'system', '2022-09-11 11:29:55', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for bm_user
-- ----------------------------
DROP TABLE IF EXISTS `bm_user`;
CREATE TABLE `bm_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `status` int NULL DEFAULT NULL COMMENT '状态 0=正常 1=失效 2=删除',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` int NULL DEFAULT NULL COMMENT '性别 0=女 1=男',
  `creater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_user
-- ----------------------------
INSERT INTO `bm_user` VALUES (2, 'hex', '72cb35da1dfe14bb40907f99fe369543388bad70dd2ec7644794cfce95b63067', 0, '15289963211', 0, 'system', '2022-09-11 11:42:05', NULL, NULL, NULL);
INSERT INTO `bm_user` VALUES (3, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 0, '18944792311', 0, 'system', '2022-09-11 21:53:08', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for bm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `bm_user_role`;
CREATE TABLE `bm_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户表ID',
  `role_id` int NULL DEFAULT NULL COMMENT '角色表ID',
  `status` int NULL DEFAULT NULL COMMENT '用户角色状态 0=有效 1=失效',
  `creater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bm_user_role
-- ----------------------------
INSERT INTO `bm_user_role` VALUES (1, 2, 1, 0, '2', '2022-09-11 11:42:06', NULL, NULL, NULL);
INSERT INTO `bm_user_role` VALUES (4, 3, 1, 0, '3', '2022-09-11 21:53:09', NULL, NULL, NULL);
INSERT INTO `bm_user_role` VALUES (5, 3, 2, 0, '3', '2022-09-11 11:42:06', NULL, NULL, NULL);
INSERT INTO `bm_user_role` VALUES (6, 3, 3, 0, '3', '2022-09-11 11:42:06', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
