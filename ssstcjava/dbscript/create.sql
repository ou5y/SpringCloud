--收款凭证表
CREATE TABLE `ssstc_agency`.`make_collections_identify` (
  `id` BIGINT NOT NULL,
  `seller_id` BIGINT NULL COMMENT '商家id',
  `oder_id` BIGINT NULL COMMENT '订单id',
  `certificate` VARCHAR(45) NULL COMMENT '凭证（图片）',
  `create_time` DATETIME NULL,
  `liushuihao` VARCHAR(45) NULL COMMENT '流水号',
  PRIMARY KEY (`id`))
COMMENT = '收款凭证表';

--订单表
CREATE TABLE `order_form` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL COMMENT '商家id',
  `money` decimal(10,0) DEFAULT NULL COMMENT '消费金额',
  `state` int(11) DEFAULT NULL COMMENT '订单状态(0:待商家确认收款,1:商家确认收款,2:财务确认收款)',
  `create_time` datetime DEFAULT NULL,
  `caiwu_time` datetime DEFAULT NULL COMMENT '财务确认时间',
  `seller_time` datetime DEFAULT NULL COMMENT '商家确认时间',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

--店铺表
CREATE TABLE `ssstc_agency`.`store` (
  `id` BIGINT NOT NULL,
  `seller_id` BIGINT NULL,
  `master_pic` VARCHAR(45) NULL COMMENT '店铺主图片',
  `pic2` VARCHAR(45) NULL COMMENT '展示图片',
  `storecol` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `infomation` VARCHAR(45) NULL COMMENT '店铺介绍',
  `type_id` INT NULL COMMENT '店铺类型',
  `phone` VARCHAR(11) NULL,
  PRIMARY KEY (`id`))
COMMENT = '店铺表';

--行业表
CREATE TABLE `ssstc_agency`.`trade` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
COMMENT = '行业表';

--代理申请记录表
SELECT * FROM ssstc_agency.user;CREATE TABLE `agency_apply_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `linkman_name` varchar(45) DEFAULT NULL,
  `link_phone` varchar(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL COMMENT '代理区域id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理申请记录表';

