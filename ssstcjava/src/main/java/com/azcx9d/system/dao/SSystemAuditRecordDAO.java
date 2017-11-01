package com.azcx9d.system.dao;

import com.azcx9d.business.entity.ParaMap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/15 0015 18:00.
 */
public interface SSystemAuditRecordDAO {

    @Insert("insert into sys_audit_record (create_time,businessId,money,status) values(#{createTime},#{businessId},#{money},#{status})")
    void insertSystemAuditRecord(ParaMap paraMap);

    @Select("select * from sys_audit_record where date_format(create_time,'%Y-%m-%d')=#{queryDay}")
    List<Map> selectFaildList(ParaMap paraMap);
}
