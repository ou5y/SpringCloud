package com.customer.dao;

import com.customer.entity.CCollect;
import java.util.List;
import java.util.Map;

public interface CCollectDao {

    /**
     * 查询收藏总数
     * @param params
     * @return int
     */
    int countCollect(Map<String,Object> params);

    /**
     * 我的收藏
     * @param params
     * @return List<Collect>
     */
    List<Map<String,Object>> queryMyCollect(Map<String,Object> params);

    /**
     * 添加收藏
     * @param params
     * @return
     */
    int addCollect(Map<String,Object> params);

    /**
     * 删除收藏
     * @param params
     * @return
     */
    int deleteCollect(Map<String,Object> params);

}