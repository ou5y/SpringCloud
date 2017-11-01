package com.azcx9d.business.dao;

import com.azcx9d.business.dto.CommonProblemDto;
import com.azcx9d.business.entity.ParaMap;

import java.util.List;

/**
 * Created by chenxl on 2017/5/23 0023.
 */
public interface BSetupDao {

    int updateAvatar(ParaMap paraMap);

    int updateUserName(ParaMap paraMap);

    int saveUserSuggest(ParaMap paraMap);

    List<CommonProblemDto> getCommonProblem(ParaMap paraMap);
}
