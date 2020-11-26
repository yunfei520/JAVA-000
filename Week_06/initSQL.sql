
-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_code` int(32) NOT NULL COMMENT '订单编码',
  `order_status` int(11) NOT NULL COMMENT '订单状态：0未付款,1已付款,2已发货,3已签收,-1退货申请,-2退货中,-3已退货,-4取消交易',
  `order_num` int(11) DEFAULT NULL COMMENT '订单数量',
  `order_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `address` varchar(100) NOT NULL COMMENT '收货地址',
  `order_settlement_time` datetime DEFAULT NULL COMMENT '订单结算时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `userId` int(11) DEFAULT NULL COMMENT '用户id',
  `payType` int(11) NOT NULL COMMENT '付款方式：0：货到付款 1:在线支付',
  `isPay` int(11) DEFAULT NULL COMMENT '是否支付：0：未支付 1：已支付',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_order_product
-- ----------------------------
DROP TABLE IF EXISTS `t_order_product`;
CREATE TABLE `t_order_product` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `product_id` int(11) NOT NULL COMMENT '商品ID',
  `product_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `product_price` decimal(10,0) DEFAULT NULL COMMENT '商品价格',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_product
-- ----------------------------

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_name` varchar(32) NOT NULL COMMENT '商品名称',
  `product_category` varchar(32) DEFAULT NULL COMMENT '商品类别',
  `product_code` varchar(32) NOT NULL COMMENT '商品编码',
  `product_price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `status` int(11) NOT NULL COMMENT '是否上架 1：是 0：否',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(32) NOT NULL COMMENT '账号',
  `user_password` varchar(32) NOT NULL COMMENT '密码',
  `user_nickname` varchar(50) NOT NULL COMMENT '昵称',
  `user_realname` varchar(50) NOT NULL COMMENT '姓名',
  `status` int(2) NOT NULL COMMENT '状态：0禁用，1正常可用',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `user_gender` char(2) DEFAULT NULL COMMENT '性别',
  `mobile_phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
