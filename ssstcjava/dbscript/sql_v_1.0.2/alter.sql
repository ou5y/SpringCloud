ALTER TABLE `c_pay_info`
  ADD COLUMN `description` VARCHAR(200) NULL DEFAULT '' AFTER `order_fee`;

ALTER TABLE `c_pay_info`
  CHANGE COLUMN `create_time` `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ;

ALTER TABLE `order_form`
  CHANGE COLUMN `ranli_money` `ranli_money` DECIMAL(10,2) NULL DEFAULT NULL COMMENT '让利金额' ;


ALTER TABLE `business_support_info`
  MODIFY COLUMN `min_transfer_amt`  decimal(10,2) NOT NULL DEFAULT 0.01 COMMENT '最低打款额（元） 默认最小值:0.01' AFTER `settle_cycle`;


ALTER TABLE `business_support_info`
  ADD COLUMN `merchant_number`  varchar(32) NULL DEFAULT '' COMMENT '裕顺商户编号' AFTER `licence_no`;


ALTER TABLE `business_support_info`
  ADD COLUMN `merchant_status`  int(1) NULL DEFAULT NULL COMMENT '商户启用状态：0：已启用 1：已停用' AFTER `merchant_number`,
  ADD COLUMN `body`  varchar(400) NULL DEFAULT '' COMMENT '审核拒绝描述：描述审核拒绝原因。' AFTER `merchant_status`;

ALTER TABLE `business_support_info`
  MODIFY COLUMN `fee_value`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0.45' COMMENT '手续费费率值。按比例时单位为百分率；按笔数时单位为元' AFTER `fee_type`;









