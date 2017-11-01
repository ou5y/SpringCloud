-- 数据迁移

-- set sql_safe_updates=0;

-- 消费者和推广员
insert into c_user_role_attribute(user_id,role_id,shandian,integral,love,love_middle,love_large,love_small,create_time,last_update_time)
  select u.id,1,u.shandian,u.jifen,u.shanxin,u.shanxin3,u.shanxin4,u.shanxin2,u.create_time,u.update_time from user u where (u.user_type=2 OR u.user_type=0);

-- 商家
insert into c_user_role_attribute(user_id,role_id,shandian,integral,love,love_middle,love_large,love_small,create_time,last_update_time)
  select u.id,2,u.shandian,u.jifen,u.shanxin,u.shanxin3,u.shanxin4,u.shanxin2,u.create_time,u.update_time from user u where u.user_type=1;

-- 同步代理的状态
insert into c_platform_status(identity_id,level_id,level_name)
  select ura.id,(select ag.param_value from agency_level ag where ag.param_value=u.level) level_id,
                (select ag.param_name from agency_level ag where ag.param_value=u.level)level_name
  from c_user_role_attribute ura,user u
  where ura.user_id = u.id
        and ura.role_id=1
        and u.user_type=2
;

-- 同步服务商身份
update user_agency ua
  inner join c_user_role_attribute a on ua.user_id = a.user_id
set ua.user_id = a.id
where a.role_id=1;

insert into c_platform_status(identity_id,level_id,level_name)
  select  distinct (ua.user_id) id,
                   9 as level_id,
                   (select ag.param_name from agency_level ag where ag.param_value=9)level_name
  from user_agency ua
  where not exists (select * from c_platform_status a where ua.user_id =a.identity_id and a.level_id=9)
;

-- 同步商家user_id
-- select * from business a,c_user_role_attribute b where a.user_id = b.id and b.role_id=2;
update business a inner join c_user_role_attribute b
    on a.user_id=b.user_id
set a.user_id = b.id
where b.role_id=2;

-- 同步business upload_user
update business a inner join c_user_role_attribute b
    on a.upload_user=b.user_id
set a.upload_user = b.id
where b.role_id=1;

-- 同步 passive_shandian

update  passive_shandian ps
  inner join c_user_role_attribute ura
    on ps.from_id = ura.user_id
set ps.from_id = ura.id
where ura.role_id=1;

-- 同步 xfz_dzb
update xfz_dzb x inner join c_user_role_attribute a on x.user_id= a.user_id
set x.user_id = a.id;

-- 同步 order_form

UPDATE order_form of
  INNER JOIN
  c_user_role_attribute ura
    ON of.user_id = ura.user_id
SET
  of.user_id = ura.id
where ura.role_id=1;

UPDATE order_form of
  INNER JOIN
  c_user_role_attribute ura
    ON of.seller_id = ura.user_id
SET
  of.seller_id = ura.id
where ura.role_id=2;


-- 同步 bank_card

update bank_card  a inner join c_user_role_attribute  b on a.user_id = b.user_id
set a.user_id = b.id;



update convertibility_record a inner join c_user_role_attribute b
    on a.user_id =b.user_id
set a.user_id = b.id;


insert into c_user_role_attribute(user_id,role_id,shandian,integral,love,love_middle,love_large,love_small,create_time,last_update_time)
    select a.user_id,1,0,0,0,0,0,0,now(),now() from c_user_role_attribute a where role_id =2;


















