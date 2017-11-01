package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BBusinessMemberDAO;
import com.azcx9d.business.dto.BusinessMemberDto;
import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.business.entity.BUser;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BBusinessMemberService;
import com.azcx9d.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/2 0002 17:24.
 */
@Service("businessBusinessMemberService")
public class BBusinessMemberServiceImpl implements BBusinessMemberService {

    @Autowired
    private BBusinessMemberDAO memberDAO;

    @Override
    public Page selectMyMembersByUserId(Page page, ParaMap paraMap) throws Exception {
        int total = memberDAO.countTotal(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<BusinessMemberDto> list = memberDAO.selectMyMembersByUserId(paraMap);
        page.setPageList(list);
        page.init();
        return page;
    }
}
