package com.azcx9d.business.service;

import com.azcx9d.business.dto.CommonProblemDto;
import com.azcx9d.business.entity.ParaMap;

import java.util.List;

/**
 * Created by chenxl on 2017/5/23 0023.
 */
public interface BSetupService {

    int updateAvatar(ParaMap paraMap) throws Exception;

    int updateUserName(ParaMap paraMap) throws Exception;

    int saveUserSuggest(ParaMap paraMap) throws Exception;

    List<CommonProblemDto> getCommonProblem(ParaMap paraMap) throws Exception;
}
