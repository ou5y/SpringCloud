ALTER TABLE `ssstc`.`user`
  CHANGE COLUMN `name` `name` VARCHAR(30) NULL DEFAULT '' COMMENT '姓名' ;


ALTER TABLE `ssstc`.`c_user_role_attribute`
  ADD INDEX `user_id_index` (`user_id` DESC);


ALTER TABLE `business`
  ADD COLUMN `tags`  varchar(255) NULL DEFAULT '' COMMENT '商铺标签' ;
ALTER TABLE `business`
  ADD COLUMN `tags_name`  varchar(500) NULL DEFAULT '' COMMENT '商铺标签描述' ;
ALTER TABLE `business`
  ADD COLUMN `per_average`  double(11,2) NULL DEFAULT 0 COMMENT '人均消费(元)' ;
ALTER TABLE `business`
  add COLUMN `xingming` varchar(10) DEFAULT NULL COMMENT '业务联系人';

ALTER TABLE `business`
  add COLUMN `business_licence_no` varchar(32) DEFAULT NULL COMMENT '营业执照号码';

  ALTER TABLE `business`
add COLUMN    `latitude1` varchar(50) DEFAULT NULL;


ALTER TABLE `business`
  add COLUMN  `longitude1` varchar(50) DEFAULT NULL;

  ALTER TABLE `business`
add COLUMN  `register_name` varchar(64) DEFAULT NULL COMMENT '商户注册名称';

ALTER TABLE `business`
  add COLUMN  `identity_card_up` varchar(255) DEFAULT NULL COMMENT '法人身份证正面照';

  ALTER TABLE `business`
add COLUMN  `identity_card_down` varchar(255) DEFAULT NULL COMMENT '法人身份证反面照';

ALTER TABLE `business`
  add COLUMN  `clearance_bank_card_up` varchar(255) DEFAULT NULL COMMENT '分账银行卡正面照';


alter table system_message add column title varchar(100);
alter table system_message add column app_type int(1) comment '1:用户端  2：商户端';

ALTER TABLE business ADD INDEX index_upload_user (upload_user); -- 索引

ALTER TABLE `ssstc`.`order_form`
  CHANGE COLUMN `create_time` `create_time` DATETIME NULL DEFAULT NOW() ;
