-- 该脚本用于记录对数据更新的回滚恢复，已方便回滚和更新脚本的重复执行
-- 执行完该脚本后，此文件夹内所有其他脚本做出的改动都应该被取消：
--   新建的表、trigger、procedure等要drop掉
--   新加的记录要delete掉
--   update的记录要update成原先的状态
--   delete掉的记录要insert回来

-- 删除新脚本给现有表添加的字段
call up_drop_column('ssstc', 'business', 'tags');
call up_drop_column('ssstc', 'business', 'tags_name');
call up_drop_column('ssstc', 'business', 'per_average');
call up_drop_column('ssstc', 'business', 'xingming');
call up_drop_column('ssstc', 'business', 'business_licence_no');
call up_drop_column('ssstc', 'business', 'latitude1');
call up_drop_column('ssstc', 'business', 'longitude1');
call up_drop_column('ssstc', 'business', 'register_name');
call up_drop_column('ssstc', 'business', 'identity_card_up');
call up_drop_column('ssstc', 'business', 'identity_card_down');
call up_drop_column('ssstc', 'business', 'clearance_bank_card_up');
call up_drop_column('ssstc', 'system_message', 'title');
call up_drop_column('ssstc', 'system_message', 'app_type');

--
drop table if exists `b_second_kill_plan`;
drop table if exists `business_support_info`;
-- drop table if exists `c_banner`;
drop table if exists `c_collect`;
-- drop table if exists `c_common_problem`;
-- drop table if exists `c_goods`;
-- drop table if exists `c_goods_state`;
drop table if exists `c_gs_att_goods_relation`;
drop table if exists `c_gs_attribute`;
drop table if exists `c_gs_att_value`;
drop table if exists `c_gs_category`;
drop table if exists `c_gs_spe_option`;
drop table if exists `clearance_account`;
drop table if exists `c_level_menu_relation`;
drop table if exists `c_menu`;
drop table if exists `c_notice`;
drop table if exists `c_order_item`;
drop table if exists `c_pay_info`;
drop table if exists `c_platform_status`;
drop table if exists `c_product`;
drop table if exists `c_product_spec_relation`;
drop table if exists `c_p_specification`;
drop table if exists `c_recommend_log`;
drop table if exists `c_role`;
drop table if exists `c_second_kill`;
drop table if exists `c_spe_value`;
drop table if exists `c_tags`;
drop table if exists `c_user_role_attribute`;
drop table if exists `c_votes`;
drop table if exists `financial_audit_record`;
drop table if exists `make_collections_identify`;
-- drop table if exists `notice`;
drop table if exists `user_address`;


-- 恢复服务商身份
update user_agency a INNER  join c_user_role_attribute b on a.user_id=b.id
    set a.user_id =  b.user_id;


-- 恢复商家
update business a inner join c_user_role_attribute b
    on a.user_id=b.id
set a.user_id = b.user_id
;

update business a inner join c_user_role_attribute b
    on a.upload_user=b.id
set a.upload_user = b.user_id
;


-- 恢复 passive_shandian
update  passive_shandian a
  inner join c_user_role_attribute b
    on a.from_id = b.id
set a.from_id = b.user_id
;

-- 恢复 xfz_dzb
update xfz_dzb a inner join c_user_role_attribute b on a.user_id= b.id
set a.user_id = b.user_id;


update bank_card  a inner join c_user_role_attribute  b on a.user_id = b.id
set a.user_id = b.user_id;


ALTER TABLE business DROP INDEX index_upload_user;