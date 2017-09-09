/*
Navicat MySQL Data Transfer

Source Server         : 121.42.45.40
Source Server Version : 50537
Source Host           : 121.42.45.40:3306
Source Database       : jinxiaocun

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2016-06-26 22:08:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_commodity`
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity`;
CREATE TABLE `t_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serail_number` varchar(16) DEFAULT NULL COMMENT '编号',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `unit` varchar(64) DEFAULT NULL COMMENT '单位',
  `specification` varchar(128) DEFAULT NULL COMMENT '规格',
  `retail_price` int(11) DEFAULT NULL COMMENT '零售价',
  `purchase_price` int(11) DEFAULT NULL COMMENT '进货价',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除状态0-正常，1-删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_serial_number` (`serail_number`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_commodity
-- ----------------------------
INSERT INTO `t_commodity` VALUES ('1', null, '1', '1', '1', '100', '1', '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('2', null, '1', '1', '1', '100', '1', '', '0', null, null, '0');
INSERT INTO `t_commodity` VALUES ('3', null, '1', '1', '1', '100', '1', '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('4', null, '1', '1', '1', '100', '1', '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('5', null, '1', '1', '', null, null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('6', null, '1', '1', '1', '100', null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('7', null, '1', '1', '1', '100', '1', '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('8', null, '1', '1', '1', '100', '1', '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('9', null, '1', '1', '11', '100', '1', '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('10', null, '1', '1', '', null, null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('11', null, '1', '1', '', null, null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('12', null, '1', '1', '', null, null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('13', null, '1', '1', '', null, null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('14', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `t_commodity` VALUES ('15', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `t_commodity` VALUES ('16', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `t_commodity` VALUES ('17', null, '1', '1', '', null, null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('18', null, '11', '1', '1', '100', null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('19', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `t_commodity` VALUES ('20', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `t_commodity` VALUES ('21', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `t_commodity` VALUES ('22', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `t_commodity` VALUES ('23', null, '123132', '1', '', null, null, '', '0', null, null, '0');
INSERT INTO `t_commodity` VALUES ('24', null, '123132', '1', '', null, null, '', '0', null, null, '0');
INSERT INTO `t_commodity` VALUES ('25', null, '1', '1', '1', '100', null, '', '0', null, null, '0');
INSERT INTO `t_commodity` VALUES ('26', null, '1', '1', '1', '100', null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('27', null, '1', '1', '', null, null, '', '0', null, null, '0');
INSERT INTO `t_commodity` VALUES ('28', null, '1', '1', '1', '100', null, '', '0', null, null, '0');
INSERT INTO `t_commodity` VALUES ('29', null, '1', '1', '', null, null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('30', null, '1', '1', '', null, null, '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('31', null, '1', '1', '1', '100', '1', '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('32', null, '12', '12', '1200', '1200', '1200', '', '0', null, null, '1');
INSERT INTO `t_commodity` VALUES ('33', null, '吃顿饭', '发达', '11100', '11100', null, '21421412', '0', null, null, '0');
INSERT INTO `t_commodity` VALUES ('34', 'SP2016062534', '五粮液', '瓶', '225ml/瓶', '12550', '9550', '', '0', null, null, '0');
INSERT INTO `t_commodity` VALUES ('35', 'SP2016062635', '茅台', '瓶', '500ml', '50000', '30000', '茅台', '0', null, null, '0');

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT NULL COMMENT '分公司',
  `serail_number` varchar(16) DEFAULT NULL COMMENT '编号',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `office_phone` varchar(128) DEFAULT NULL COMMENT '单位电话',
  `contact` varchar(128) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(128) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(128) DEFAULT NULL COMMENT '传真',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `postcode` varchar(128) DEFAULT NULL COMMENT '邮编',
  `qq` varchar(128) DEFAULT NULL COMMENT 'qq',
  `weixin` varchar(128) DEFAULT NULL COMMENT '微信',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `all_debt` int(11) DEFAULT NULL,
  `init_debt` int(11) DEFAULT '0' COMMENT '初始欠款',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除状态0-正常，1-删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_serial_number` (`serail_number`) USING BTREE,
  KEY `FK_Reference_5` (`store_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='客户表，关联门店表；为门店专有客户';

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', '1', 'CU00001', '诚苑', '82432611', 'tian', '18101330507', null, null, null, null, null, null, null, '0', '100000', null, null, null, '0');
INSERT INTO `t_customer` VALUES ('2', '1', 'CU00002', 'nanting', '12321', '312', '4214', null, null, null, null, null, null, null, '0', '0', null, null, null, '0');
INSERT INTO `t_customer` VALUES ('3', '2', 'CU00003', null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, '1');
INSERT INTO `t_customer` VALUES ('4', '2', 'KH20160626001', '客户1', '131342221', '客户2', '13131441', '', '', '213@123.com', '', null, '', '', '0', '0', null, null, null, '0');
INSERT INTO `t_customer` VALUES ('5', null, 'KH201606265', '起来了', '123', '123', '123', '123', '123@123.com', '123', '123', null, '', '', '0', null, null, null, null, null);
INSERT INTO `t_customer` VALUES ('6', null, 'KH201606266', '起来了', '123', '123', '123', '123', '123@123.com', '123', '123', null, '', '', '0', null, null, null, null, null);
INSERT INTO `t_customer` VALUES ('7', null, 'KH201606267', '客户', '11', '11', '11', '1', '123@123.com', '11', '11', null, '', '', '0', null, null, null, null, null);
INSERT INTO `t_customer` VALUES ('8', null, 'KH201606268', '客户', '11', '11', '11', '1', '123@123.com', '11', '11', null, '', '', '0', null, null, null, null, null);
INSERT INTO `t_customer` VALUES ('9', '1', 'KH201606269', '公司1客户1', '1111', '', '', '', '', '', '', null, '', '', '0', '0', null, null, null, '0');
INSERT INTO `t_customer` VALUES ('10', '1', 'KH2016062610', '公司1客户2', '12313', '', '', '', '', '', '', null, '', '', '0', '0', null, null, null, '1');
INSERT INTO `t_customer` VALUES ('11', '1', 'KH2016062611', '测试客户101', '82344567', '田宝超', '18101330507', '', '', '', '', null, '', '', null, '0', null, null, null, '0');

-- ----------------------------
-- Table structure for `t_employee`
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0' COMMENT '分公司-0默认为总公司员工',
  `serial_number` varchar(16) DEFAULT NULL COMMENT '编号',
  `position` varchar(16) DEFAULT NULL COMMENT '职位',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `phone` varchar(16) DEFAULT NULL COMMENT 'phone',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除状态,0-正常，1-删除',
  `privilige` int(11) DEFAULT NULL COMMENT '权限，0-提交分公司数据，1-查看分公司数据，2-总公司权限',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_serial_number` (`serial_number`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='员工表，业务中的经手人，制表人都首先需要是员工';

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES ('1', '1', 'EM00001', null, 'fdsa', null, null, null, null, '0', null);
INSERT INTO `t_employee` VALUES ('2', '1', 'EM00002', null, 'lyongang', null, null, null, null, '0', null);
INSERT INTO `t_employee` VALUES ('3', '2', 'EM00003', null, 'tian', null, null, null, null, '0', null);

-- ----------------------------
-- Table structure for `t_expense`
-- ----------------------------
DROP TABLE IF EXISTS `t_expense`;
CREATE TABLE `t_expense` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(16) DEFAULT NULL COMMENT '编号',
  `store_id` int(11) DEFAULT NULL COMMENT '门店',
  `business_date` datetime DEFAULT NULL COMMENT '业务日期',
  `type` int(11) DEFAULT NULL COMMENT '账目类型,1-销售费用，2-管理费用',
  `amount` int(11) DEFAULT NULL COMMENT '金额',
  `handler` int(11) DEFAULT NULL COMMENT '经手人',
  `handler_name` varchar(128) DEFAULT NULL COMMENT '经手人名称',
  `lister` int(11) DEFAULT NULL COMMENT '制表人',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除状态0-正常，1-删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_serial_number` (`serial_number`) USING BTREE,
  KEY `FK_Reference_10` (`store_id`),
  KEY `FK_Reference_8` (`handler`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='支出表，关联门店表，员工表';

-- ----------------------------
-- Records of t_expense
-- ----------------------------
INSERT INTO `t_expense` VALUES ('1', 'EX00001', '2', null, '1', '11100', null, null, null, '3213', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('2', 'EX00002', '1', '2016-06-23 16:07:00', '1', '2147483647', '2', null, null, '3213', null, '2016-06-26 17:07:52', '2016-06-26 17:07:52', '0');
INSERT INTO `t_expense` VALUES ('3', 'EX00003', '2', null, '1', '21100', null, null, null, '发达', null, null, null, '1');
INSERT INTO `t_expense` VALUES ('4', 'EX00004', '2', null, '1', '21100', null, null, null, '发达', null, null, null, '1');
INSERT INTO `t_expense` VALUES ('5', 'EX00005', '2', null, '1', '321300', null, null, null, '3123', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('6', 'EX00006', '2', null, '1', '31231200', null, null, null, '3213', null, null, null, '1');
INSERT INTO `t_expense` VALUES ('7', 'EX00007', '1', '2016-06-23 17:25:00', '1', '312421408', null, null, null, '41241', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('8', 'EX00008', '1', '2016-06-23 17:25:00', '1', '312421408', null, null, null, '41241', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('9', 'EX00009', '1', '2016-06-23 17:25:00', '1', '312421408', null, null, null, '41241', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('10', 'EX000010', '1', '2016-06-23 17:25:00', '1', '312421408', null, null, null, '41241', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('11', 'EX000011', '1', '2016-06-23 17:25:00', '2', '2147483647', '3', null, null, '41241', null, '2016-06-26 17:17:46', '2016-06-26 17:17:46', '0');
INSERT INTO `t_expense` VALUES ('12', null, '1', '2016-06-08 22:30:00', '1', '10000', null, null, null, 'remark', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('13', null, '1', '2016-06-08 22:30:00', '1', '10000', null, null, null, 'remark', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('14', null, '1', '2016-06-08 22:30:00', '1', '10000', null, null, null, 'remark', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('15', null, '1', '2016-06-01 02:10:00', '1', '100300', null, null, null, 'liyonggang', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('16', null, '1', '2016-06-01 02:10:00', '1', '100300', null, null, null, 'tian', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('17', null, '1', '2016-06-01 02:10:00', '1', '100300', '3', null, null, 'tian', null, null, null, '0');
INSERT INTO `t_expense` VALUES ('18', null, '1', '2016-06-23 17:25:00', null, '10000', '2', null, null, '发放大法', null, '2016-06-26 17:19:11', '2016-06-26 17:19:11', '0');
INSERT INTO `t_expense` VALUES ('19', null, '1', '2016-06-15 09:20:00', '2', '32132100', '3', null, null, '21421', null, '2016-06-26 17:21:20', '2016-06-26 17:21:20', '0');
INSERT INTO `t_expense` VALUES ('20', 'KH2016062620', '1', '2016-06-26 17:20:00', '2', '2000000', '3', null, null, 'tian；s项目', null, '2016-06-26 17:24:39', '2016-06-26 17:24:39', '0');
INSERT INTO `t_expense` VALUES ('21', 'KH2016062621', '1', '2016-06-21 13:25:00', '2', '321321', '1', null, null, '3213', null, '2016-06-26 18:29:13', '2016-06-26 18:29:13', '0');
INSERT INTO `t_expense` VALUES ('22', 'KH2016062622', '1', '2016-06-14 09:25:00', '2', '11234523', '3', null, null, '2141', null, '2016-06-26 18:31:26', '2016-06-26 18:31:26', '0');
INSERT INTO `t_expense` VALUES ('23', 'KH2016062623', '1', '2016-06-23 18:30:00', '1', '115432117', '3', null, null, '214124', null, '2016-06-26 18:33:34', '2016-06-26 18:33:34', '0');

-- ----------------------------
-- Table structure for `t_login`
-- ----------------------------
DROP TABLE IF EXISTS `t_login`;
CREATE TABLE `t_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) DEFAULT NULL COMMENT '雇员',
  `username` varchar(128) DEFAULT NULL COMMENT '登录名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_11` (`employee_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_login
-- ----------------------------
INSERT INTO `t_login` VALUES ('1', '1', 'admin', 'admin', '2016-06-24 16:14:00', '0', '2016-06-24 16:14:05', '2016-06-24 16:14:07', '0');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(16) DEFAULT NULL COMMENT '订单编号，特定前缀',
  `store_id` int(11) DEFAULT NULL COMMENT '门店',
  `store_name` varchar(128) DEFAULT NULL COMMENT '门店名称',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户',
  `customer_name` varchar(128) DEFAULT NULL COMMENT '客户名称',
  `handler` int(11) DEFAULT NULL COMMENT '经手人，销售',
  `handler_name` varchar(128) DEFAULT NULL COMMENT '经手人名称',
  `business_date` datetime DEFAULT NULL COMMENT '业务日期',
  `total_amount` int(11) DEFAULT '0' COMMENT '合计金额，分为单位',
  `discount_rate` int(11) DEFAULT '100' COMMENT '折扣率,',
  `discount_mount` int(11) DEFAULT '0' COMMENT '折扣额，分为单位',
  `final_amount` int(11) DEFAULT '0' COMMENT '最后金额，分为单位',
  `gross_profit` int(11) DEFAULT '0' COMMENT '毛利率',
  `actual_payment` int(11) DEFAULT '0' COMMENT '实际收款',
  `lister` int(11) DEFAULT NULL COMMENT '制表人',
  `lister_name` varchar(128) DEFAULT NULL COMMENT '制表人姓名',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT '0' COMMENT '状态-0正常，-1删除',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_serial_number` (`serial_number`) USING BTREE,
  KEY `FK_Reference_1` (`customer_id`),
  KEY `FK_Reference_2` (`store_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='订单表，关联门店，客户，商品，员工';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', 'OR00001', '1', '天下1', '1', '诚苑', '1', '经手人1', '2016-06-09 11:42:59', '10000', '100', '0', '90000', '10', '500', null, null, null, '0', null, null, '0');
INSERT INTO `t_order` VALUES ('2', 'OR201606262', '1', '天字一号店', '1', '诚苑', '1', '', '2016-06-26 20:38:26', '23650', '100', '0', '50000', '0', '0', '1', 'fdsa', null, '0', null, null, '0');
INSERT INTO `t_order` VALUES ('3', 'OR201606263', '1', '天字一号店', '1', '诚苑', '1', '', '2016-06-26 20:38:26', '23650', '100', '0', '50000', '0', '0', '1', 'fdsa', null, '0', null, null, '0');
INSERT INTO `t_order` VALUES ('4', 'OR201606264', '1', '天字一号店', '1', '诚苑', '1', '', '2016-06-26 22:06:56', '62550', '100', '0', '0', '0', '0', '1', 'fdsa', null, '0', null, null, '0');

-- ----------------------------
-- Table structure for `t_order_commodity`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_commodity`;
CREATE TABLE `t_order_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `commondity_name` varchar(64) DEFAULT NULL,
  `commodity_num` int(11) DEFAULT NULL COMMENT '商品数目',
  `commodity_price` int(11) DEFAULT NULL COMMENT '单价',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`order_id`),
  KEY `FK_Reference_4` (`commodity_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_order_commodity
-- ----------------------------
INSERT INTO `t_order_commodity` VALUES ('1', '1', '1', '商品1', '1', '500', '第一个商品卖了1个', null, null, null);
INSERT INTO `t_order_commodity` VALUES ('2', '1', '2', '商品2', '2', '100', '第二个商品卖了两个', null, null, null);
INSERT INTO `t_order_commodity` VALUES ('3', '2', '34', '34', '1', '12550', '', null, null, null);
INSERT INTO `t_order_commodity` VALUES ('4', '2', '33', '33', '1', '11100', '', null, null, null);
INSERT INTO `t_order_commodity` VALUES ('5', '3', '34', '34', '1', '12550', '', null, null, null);
INSERT INTO `t_order_commodity` VALUES ('6', '3', '33', '33', '1', '11100', '', null, null, null);
INSERT INTO `t_order_commodity` VALUES ('7', '4', '35', '35', '1', '50000', '', null, null, null);
INSERT INTO `t_order_commodity` VALUES ('8', '4', '34', '34', '1', '12550', '', null, null, null);

-- ----------------------------
-- Table structure for `t_receipt`
-- ----------------------------
DROP TABLE IF EXISTS `t_receipt`;
CREATE TABLE `t_receipt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(16) DEFAULT NULL COMMENT '编号',
  `store_id` int(11) DEFAULT NULL COMMENT '门店',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `business_date` datetime DEFAULT NULL COMMENT '业务日期',
  `debt` int(11) DEFAULT '0' COMMENT '应收金额',
  `amount` int(11) DEFAULT NULL COMMENT '收款金额',
  `handler` int(11) DEFAULT NULL COMMENT '经手人',
  `handler_name` varchar(128) DEFAULT NULL COMMENT '经手人名称',
  `lister` int(11) DEFAULT NULL COMMENT '制表人',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除状态0-正常，1-删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_serial_number` (`serial_number`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='收款表';

-- ----------------------------
-- Records of t_receipt
-- ----------------------------
INSERT INTO `t_receipt` VALUES ('1', null, '1', '0', '2016-06-07 05:20:00', '0', '10000', '1', null, null, '诚苑100', null, '2016-06-26 13:25:06', '2016-06-26 13:25:06', '0');
INSERT INTO `t_receipt` VALUES ('2', null, '1', '1', '2016-06-07 05:20:00', '0', '10000', '2', null, null, 'nanting1000', null, '2016-06-26 21:49:53', '2016-06-26 21:49:53', '0');
INSERT INTO `t_receipt` VALUES ('3', null, '1', '2', '2016-06-09 14:30:00', '0', '35100', '3', null, null, '公诉人', null, '2016-06-26 21:50:00', '2016-06-26 21:50:00', '0');
INSERT INTO `t_receipt` VALUES ('4', null, '1', '2', '2016-06-26 13:45:00', '0', '14511', '3', null, null, '321', null, '2016-06-26 21:50:09', '2016-06-26 21:50:09', '0');
INSERT INTO `t_receipt` VALUES ('5', null, '1', '2', null, '0', '12512', '3', null, null, '121312', null, '2016-06-26 21:50:16', '2016-06-26 21:50:16', '0');
INSERT INTO `t_receipt` VALUES ('6', 'RE201606266', '1', '2', null, '0', '12632', '3', null, null, '1111', null, '2016-06-26 21:50:30', '2016-06-26 21:50:30', '0');
INSERT INTO `t_receipt` VALUES ('7', null, '1', '2', null, '0', '12711', '3', null, null, '1111', null, '2016-06-26 21:50:37', '2016-06-26 21:50:37', '0');
INSERT INTO `t_receipt` VALUES ('8', 'RE201606268', '1', '1', '2016-06-28 16:15:00', '0', '1111', '2', null, null, '123', null, '2016-06-26 21:50:43', '2016-06-26 21:50:43', '0');

-- ----------------------------
-- Table structure for `t_store`
-- ----------------------------
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serail_number` varchar(16) DEFAULT NULL COMMENT '编号',
  `name` varchar(128) DEFAULT NULL COMMENT '门店名称',
  `contact` varchar(128) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(16) DEFAULT NULL COMMENT '电话',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `postcode` varchar(64) DEFAULT NULL COMMENT '邮编',
  `address` varchar(512) DEFAULT NULL COMMENT '地址',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `state` int(11) DEFAULT '0' COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除状态0-正常，1-删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_serial_number` (`serail_number`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='门店表';

-- ----------------------------
-- Records of t_store
-- ----------------------------
INSERT INTO `t_store` VALUES ('1', 'ST0001', '天字一号店', '111', '111', '11', '11', '1', '1', '0', null, null, '0');
INSERT INTO `t_store` VALUES ('2', 'ST0002', '天子二号店', '112', '112', '22', '22', '22', '22', '0', null, null, '0');
INSERT INTO `t_store` VALUES ('3', 'ST0003', '三号店', null, null, null, null, null, null, '0', null, null, '0');
INSERT INTO `t_store` VALUES ('4', null, null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `t_store` VALUES ('5', 'DM201606265', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_store` VALUES ('6', null, '我快睡着啦', '123', '123', '123@123.com', '123', '123', '123', null, null, null, '1');
INSERT INTO `t_store` VALUES ('7', 'DM201606267', '我快睡着啦', '123', '123', '123@123.com', '123', '123', '123', null, null, null, '1');
INSERT INTO `t_store` VALUES ('8', null, '123132', '111', '111', '123@123.com', '11', '11', '11', null, null, null, '0');
INSERT INTO `t_store` VALUES ('9', 'ST201606269', '123132', '111', '111', '123@123.com', '11', '11', '11', null, null, null, '0');
INSERT INTO `t_store` VALUES ('10', null, '测试门店1号', '田宝超', '18101330507', '418251346@qq.com', '100083', 'huaiang', 'cekfldjfdlafdasf', null, null, null, '1');
INSERT INTO `t_store` VALUES ('11', 'ST2016062611', '测试门店1号', '田宝超', '18101330507', '418251346@qq.com', '100083', 'huaiang', 'cekfldjfdlafdasf', null, null, null, '0');
INSERT INTO `t_store` VALUES ('12', null, '测试门店2号', '田宝超2', '18101330506', '', '', '', '', null, null, null, '1');
INSERT INTO `t_store` VALUES ('13', 'ST2016062613', '测试门店2号', '田宝超2', '18101330506', '', '', '', '', null, null, null, '0');
INSERT INTO `t_store` VALUES ('14', 'ST2016062614', '测试门店3号', '田宝超3', '18101330506', '', '', '', '', '0', null, null, '0');
INSERT INTO `t_store` VALUES ('15', null, '总门店', '总管理员', '12345678911', '123@123.com', '', '管理总部', '默认用来测试', null, null, null, null);
INSERT INTO `t_store` VALUES ('16', 'ST2016062616', '总门店', '总管理员', '12345678911', '123@123.com', '', '管理总部', '默认用来测试', null, null, null, null);
INSERT INTO `t_store` VALUES ('17', 'ST2016062617', '测试门店101', '田宝超', '18101330507', '418251346@qq.com', '100083', '诚苑', '放大放大', '0', null, null, '0');
