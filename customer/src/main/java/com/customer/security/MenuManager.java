package com.customer.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/5/29.
 */
public  final class MenuManager {
    String[][] methods = {{"getMyAgencyBenefits","queryAgencyLineChart"},//服务商
                           {"open","openLog","myBusiness","getIncomeChangeList","getIncomeTotalList",
                                   "getIncomeUserList","getIncomeTotalList","addBusiness",
                                   "getBusinessList","getBusinessList","auditBusiness"},//推广员,高级推广员,总监
                          };
}
