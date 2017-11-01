CREATE TABLE `b_second_kill_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `business_id` int(11) NOT NULL COMMENT '店铺id',
  `phone` varchar(13) NOT NULL COMMENT '联系电话',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0  待审核  1  审核通过  2  拒绝',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='我要做秒杀';

CREATE TABLE `business_support_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `b_id` int(11) NOT NULL COMMENT '店铺id(business_id)',
  `company_name` varchar(100) NOT NULL DEFAULT '' COMMENT '商户工商注册名称',
  `bank_code` varchar(45) NOT NULL COMMENT '银行代码',
  `bank_name` varchar(64) NOT NULL COMMENT '开户银行',
  `bank_branch` varchar(45) NOT NULL COMMENT '开户银行支行名称',
  `bank_account_no` varchar(45) NOT NULL DEFAULT '' COMMENT '公司对公账户',
  `bank_account_name` varchar(45) NOT NULL DEFAULT '' COMMENT '公司对公账户名称',
  `location` int(11) NOT NULL DEFAULT '0' COMMENT '法定代表人归属地；0：中国大陆；1：港澳；2：台湾；3：外籍',
  `cert_name` varchar(45) NOT NULL COMMENT '法人姓名',
  `cert_type` varchar(45) NOT NULL DEFAULT '01' COMMENT '证件类型。01：居民身份证02: 临时居民身份证;03: 户口簿;04军人身份证;05: 港澳居民往来内地通行证;06: 台湾居民来往大陆通行证;07: 护照;08: 其他证件;',
  `cert_phone` varchar(11) NOT NULL COMMENT '法人_手机号码',
  `cert_id` varchar(45) NOT NULL COMMENT '法人代表证件编号',
  `biz_link_man` varchar(45) NOT NULL COMMENT '业务联系人',
  `biz_phone` varchar(11) NOT NULL COMMENT '业务联系电话',
  `start_val` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '最小交易金额(元) 默认最小值:0',
  `end_val` decimal(10,2) NOT NULL DEFAULT '9999999.99' COMMENT '最大交易金额（元）默认最大值:999999999.99',
  `fee_type` varchar(45) NOT NULL DEFAULT '01' COMMENT '收费方式  01：按比例 ；02：按笔数',
  `fee_value` varchar(45) NOT NULL DEFAULT '0' COMMENT '手续费费率值。按比例时单位为百分率；按笔数时单位为元',
  `min_value` varchar(45) NOT NULL DEFAULT '0' COMMENT '起收手续费 默认最小值:0',
  `max_value` varchar(45) NOT NULL DEFAULT '0' COMMENT '封顶手续费 默认最大值:999999999.99',
  `refund_feeRule_flag` int(11) NOT NULL DEFAULT '0' COMMENT '退款手续费处理规则   -1：退款退手续费0：退款不退手续费 1：退款收手续费',
  `settle_tdflag` varchar(45) NOT NULL DEFAULT 'T' COMMENT '结算账期   默认值：T',
  `settle_cycle` int(11) NOT NULL DEFAULT '1' COMMENT '结算周期(天)  默认值：1',
  `min_transfer_amt` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '最低打款额（元） 默认最小值:0.01',
  `pay_type` varchar(45) NOT NULL DEFAULT '01' COMMENT '付款方式   01：对公账户  02：资金账户',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '0：待审核 1:全团审核失败 2:全团初审通过 3：裕顺审核中 4：裕顺审核失败  4:裕顺审核成功',
  `failureCause` varchar(255) DEFAULT NULL COMMENT '审核失败原因',
  `merch_div` varchar(6) NOT NULL DEFAULT '' COMMENT '商户地区编码',
  `merchant_name` varchar(64) NOT NULL DEFAULT '' COMMENT '商户对外经营名称',
  `licence_no` varchar(64) NOT NULL DEFAULT '' COMMENT '营业执照编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='店铺辅助信息';

# CREATE TABLE `c_banner` (
#   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
#   `name` varchar(255) DEFAULT '' COMMENT '图片名称',
#   `url` varchar(255) DEFAULT '' COMMENT 'url地址',
#   `pic` varchar(255) DEFAULT '' COMMENT '图片路径',
#   `type` int(2) DEFAULT '1' COMMENT '轮播图类型1:首页 2:今日数据 3:逛一逛',
#   `display` int(2) DEFAULT '1' COMMENT '显示: 1,显示  0,不显示',
#   `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间(时间倒序)',
#   `sort` int(11) DEFAULT '0' COMMENT '顺序',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='轮播图表';


CREATE TABLE `c_collect` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `name` varchar(255) NOT NULL COMMENT '商品/店铺名称',
  `type` tinyint(1) NOT NULL COMMENT '类型：0：商品    1：店铺',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `img_url` varchar(255) NOT NULL COMMENT '图片地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gb_id` int(11) NOT NULL COMMENT '商品/店铺id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='商品/店铺收藏';

# CREATE TABLE `c_common_problem` (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `name` varchar(255) DEFAULT '' COMMENT '名称',
#   `url` varchar(255) DEFAULT '' COMMENT '链接地址',
#   `type` int(11) DEFAULT '0' COMMENT '类型(1:关于全团了, 2:操作类问题, 3:商户类问题, 4:身份类问题, 5:银行卡类问题)',
#   `app_type` int(11) DEFAULT '1' COMMENT 'app类型(1:消费者端(默认), 2:商户端)',
#   `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='常见问题表';


# CREATE TABLE `c_goods` (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `name` varchar(50) NOT NULL COMMENT '(商品名称)+规格名称',
#   `spu_id` int(11) DEFAULT '0' COMMENT '商品公共id',
#   `business_id` int(11) NOT NULL DEFAULT '0' COMMENT '商家(店铺)id',
#   `business_name` varchar(50) NOT NULL DEFAULT '' COMMENT '店铺名称',
#   `category_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品分类id',
#   `brand_id` int(11) DEFAULT '0' COMMENT '品牌id（预留字段）',
#   `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格(原价)',
#   `shopping_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '市场价（促销价）',
#   `hit_num` int(11) DEFAULT '0' COMMENT '点击数量',
#   `sales_num` int(11) DEFAULT '0' COMMENT '销售数量',
#   `collection_num` int(11) DEFAULT '0' COMMENT '收藏数量',
#   `inventory_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品库存',
#   `first_pic` varchar(255) NOT NULL DEFAULT '' COMMENT '商品主图',
#   `state` int(11) NOT NULL DEFAULT '1' COMMENT '商品状态(-1:删除,0:下架,1:上架)',
#   `is_rec` int(11) DEFAULT '0' COMMENT '是否推荐',
#   `detail` text COMMENT '详情(商品描述)',
#   `synopsis` text COMMENT '简介',
#   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
#   `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
#   `is_hot` int(11) DEFAULT '0' COMMENT '是否热门（0 否， 1是）',
#   `is_new` int(11) DEFAULT '0' COMMENT '是否新品（0 否， 1是）',
#   `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
#   `params` text COMMENT '参数',
#   `loop_pics` varchar(500) NOT NULL DEFAULT '' COMMENT '轮播图片(3张)',
#   `show_pics` varchar(1000) NOT NULL DEFAULT '' COMMENT '展示图(9张)',
#   `article_number` varchar(100) DEFAULT '' COMMENT '商品货号',
#   `rangli` decimal(10,2) NOT NULL DEFAULT '0.20' COMMENT '商品让利',
#   `express_cost` decimal(10,2) DEFAULT '0.00' COMMENT '快递费用',
#   `sort` int(11) NOT NULL DEFAULT '0' COMMENT '商品展示顺序：从大到小',
#   `show_where` int(1) NOT NULL DEFAULT '0' COMMENT '展示位置 0:不展示 1:首页 2:逛一逛',
#   `show_where_state` int(1) NOT NULL DEFAULT '0' COMMENT '展示位置审核状态 -1:审核失败 0:未审核 1:审核通过',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB AUTO_INCREMENT=924 DEFAULT CHARSET=utf8 COMMENT='商品表';


# CREATE TABLE `c_goods_state` (
#   `id` int(11) NOT NULL,
#   `name` varchar(20) NOT NULL COMMENT '状态名',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `c_gs_att_goods_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
  `att_option_id` int(11) DEFAULT NULL COMMENT '属性选项id',
  `att_name` varchar(45) DEFAULT NULL COMMENT '属性名称',
  `att_option` varchar(45) DEFAULT NULL COMMENT '属性选项',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品分类属性关系表';


CREATE TABLE `c_gs_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL COMMENT '分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `c_gs_att_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `att_id` int(11) DEFAULT NULL COMMENT '属性id',
  `att_value` varchar(45) DEFAULT NULL COMMENT '属性选项',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商品属性值表';


CREATE TABLE `c_gs_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT '',
  `parent_id` int(11) DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  `icon_url` varchar(255) DEFAULT '' COMMENT '图标地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='商品分类';

CREATE TABLE `c_gs_spe_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
  `spe_option_id` int(11) DEFAULT NULL COMMENT '规格选项id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货品规格表';


CREATE TABLE `clearance_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_card_no` varchar(20) NOT NULL COMMENT '银行卡号',
  `bank_name` varchar(32) NOT NULL COMMENT '开户行名称',
  `business_id` int(11) NOT NULL COMMENT '店铺id',
  `identity_card` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `card_name` varchar(32) NOT NULL COMMENT '卡名称',
  `card_type` varchar(10) NOT NULL COMMENT '卡类型',
  `bank_id` varchar(32) NOT NULL COMMENT '银行id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '绑定时间',
  `bank_logo` varchar(255) DEFAULT NULL COMMENT '银行logo地址',
  `card_holder` varchar(20) NOT NULL COMMENT '持卡人姓名',
  `identity_id` int(11) DEFAULT NULL,
  `branch_name` varchar(64) DEFAULT NULL COMMENT '开户行支行名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='结算账户银行信息';


CREATE TABLE `c_level_menu_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level_id` int(11) DEFAULT NULL,
  `menu_id` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `c_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


CREATE TABLE `c_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `content` text COMMENT '内容',
  `display` int(2) DEFAULT '1' COMMENT '是否显示(1:显示, 2:隐藏)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='首页公告';


CREATE TABLE `c_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
  `user_id` int(11) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(500) DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`user_id`,`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

CREATE TABLE `c_pay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台:1-裕顺,2-微信,3-支付宝',
  `platform_number` varchar(200) DEFAULT NULL COMMENT '裕顺支付流水号',
  `platform_status` varchar(20) DEFAULT NULL COMMENT '裕顺支付状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='支付信息表';

CREATE TABLE `c_platform_status` (
  `identity_id` int(11) NOT NULL,
  `level_id` int(11) NOT NULL COMMENT 'agency_level_value',
  `level_name` varchar(45) DEFAULT '',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_pass` char(1) DEFAULT 'Y' COMMENT '是否已开通 (否N   是Y)',
  PRIMARY KEY (`identity_id`,`level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地位;身份;状态';


CREATE TABLE `c_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '(商品名称)',
  `category_id` int(11) DEFAULT '0' COMMENT '商品分类id',
  `brand_id` int(11) DEFAULT '0' COMMENT '品牌id（预留字段）',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格(原价)',
  `shopping_price` decimal(10,2) DEFAULT '0.00' COMMENT '市场价（促销价）',
  `hit_num` int(11) DEFAULT '0' COMMENT '点击数量',
  `sales_num` int(11) DEFAULT '0' COMMENT '销售数量',
  `collection_num` int(11) DEFAULT '0' COMMENT '收藏数量',
  `inventory_num` int(11) DEFAULT '0' COMMENT '商品库存',
  `first_pic` varchar(45) DEFAULT '' COMMENT '商品主图',
  `state` int(11) DEFAULT '1' COMMENT '商品状态(-1:删除,0:下架,1:上架)',
  `is_rec` int(11) DEFAULT '0' COMMENT '是否推荐',
  `detail` text COMMENT '详情',
  `synopsis` varchar(500) DEFAULT '' COMMENT '简介',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_hot` int(11) DEFAULT '0' COMMENT '是否热门（0 否， 1是）',
  `is_new` int(11) DEFAULT '0' COMMENT '是否新品（0 否， 1是）',
  `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品表';

CREATE TABLE `c_product_spec_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `spec_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货品规格关系表';

CREATE TABLE `c_p_specification` (
  `id` int(11) NOT NULL COMMENT '货品规格表',
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货品规格表';


CREATE TABLE `c_recommend_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `recommend_phone` varchar(11) NOT NULL COMMENT '推荐人手机号码',
  `recommended_phone` varchar(11) NOT NULL COMMENT '被推荐人手机号码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态： 0：待审核  1：审核通过  2：审核失败',
  `level_id` int(2) DEFAULT NULL COMMENT '等级id',
  `level_name` varchar(32) DEFAULT NULL COMMENT '等级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='开通记录';


CREATE TABLE `c_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';


CREATE TABLE `c_second_kill` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `goods_name` varchar(255) NOT NULL,
  `goods_img` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL COMMENT '优惠后的价格',
  `original_price` decimal(10,2) NOT NULL COMMENT '原价',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `sale_out` int(11) NOT NULL DEFAULT '0' COMMENT '售出数量',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `kill_desc` varchar(255) DEFAULT NULL COMMENT '秒杀活动描述',
  `concern_number` int(11) NOT NULL DEFAULT '0' COMMENT '关注数',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '活动状态，0：未开始    1：活动进行中   2：活动结束',
  `business_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='秒杀';

CREATE TABLE `c_spe_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spec_id` int(11) DEFAULT NULL COMMENT '规格id',
  `value` varchar(45) DEFAULT NULL COMMENT '规格选项',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格值表';


CREATE TABLE `c_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT '' COMMENT '标签描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='标签表';


CREATE TABLE `c_user_role_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `shandian` decimal(10,2) DEFAULT '0.00' COMMENT '主动善点(1-对于消费者:消费后获得的善点,2-对于商家:让利后获得的善点)',
  `integral` bigint(20) DEFAULT '0' COMMENT '积分',
  `love` float DEFAULT '0' COMMENT '小额善心',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `recommend_shandian` decimal(10,2) DEFAULT '0.00' COMMENT '推荐善点-被动善点 (推荐的人消费后获得的收益)',
  `reuse_point` decimal(10,2) DEFAULT '0.00',
  `c_user_role_attributecol` varchar(45) DEFAULT NULL,
  `love_large` float DEFAULT '0' COMMENT '大额善心',
  `love_middle` float DEFAULT '0' COMMENT '中额善心',
  `open_limit` int(11) NOT NULL DEFAULT '100' COMMENT '每日开通数量上限',
  `love_small` float DEFAULT '0' COMMENT '小额善心',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45164 DEFAULT CHARSET=utf8;


CREATE TABLE `c_votes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `business_id` int(11) DEFAULT NULL COMMENT '商铺id',
  `vote` int(4) DEFAULT NULL COMMENT '评分(1:1星,,,5:5星)',
  `content` varchar(500) DEFAULT '' COMMENT '评价内容',
  `pics` varchar(255) DEFAULT '' COMMENT '图片(中间以","隔开)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `p_id` int(11) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='评分表';

CREATE TABLE `financial_audit_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `business_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `make_collections_identify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_id` int(11) DEFAULT NULL COMMENT '商家id',
  `oder_id` int(11) DEFAULT NULL COMMENT '订单id',
  `certificate` varchar(45) DEFAULT NULL COMMENT '凭证（图片）',
  `create_time` datetime DEFAULT NULL,
  `liushuihao` varchar(45) DEFAULT NULL COMMENT '流水号',
  `rangli_money` double(11,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=616 DEFAULT CHARSET=utf8 COMMENT='收款凭证表';


# CREATE TABLE `notice` (
#   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
#   `is_open` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开启,0:false    1:true',
#   `message` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '消息内容',
#   `type` int(1) NOT NULL DEFAULT '0' COMMENT '消息类型：0：系统公告    1：安全公告',
#   `app_type` int(1) DEFAULT '1' COMMENT 'APP类型：1：Android商户端 2 :Android代理端3：Android用户端 4：IOS商户端 5：iOS代理端 6：IOS用户端',
#   `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '收货人姓名',
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '收货人手机',
  `province` int(11) DEFAULT NULL COMMENT '省',
  `city` int(11) DEFAULT NULL COMMENT '市',
  `area` int(11) DEFAULT NULL COMMENT '区',
  `full_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '详细地址(街道,楼牌号等)',
  `is_default` int(4) DEFAULT '0' COMMENT '默认地址(0:不是默认地址, 1:默认地址)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='收货地址表';
