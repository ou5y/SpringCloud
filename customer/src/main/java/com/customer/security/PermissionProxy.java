package com.customer.security;

import com.customer.config.RedisManager;
import com.customer.dao.UserDao;
import com.customer.dto.RoleListDto;
import com.customer.exception.CustomerException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by fangbaoyan on 2017/5/28.
 */
@Configuration
@Aspect
public class PermissionProxy {
    @Autowired
    private RedisManager redisManager;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HttpServletRequest request;

    public static final String[][] methods = {{"getMyAgencyBenefits","queryAgencyLineChart","getIncomeTotalList","getIncomeChangeList","getIncomeUserList","getIncomeDetailList" },//服务商
            {"open","openLog","myBusiness","getIncomeChangeList",
                    "getIncomeUserList","getIncomeTotalList","addBusiness","addBusinessSimple",
                    "getBusinessList","getBusinessType","auditBusiness","getIncomeDetailList"},//推广员,高级推广员,总监
    };


    @Around("@annotation(com.customer.security.CheckPermission)")
    public Object check(ProceedingJoinPoint point) throws Throwable
    {
        MethodSignature signature = (MethodSignature) point.getSignature();

        //获取被代理方法的类名
        String className=signature.getDeclaringTypeName();
        //获取被代理的方法名
        Method method = signature.getMethod();
        String methodName=method.getName();

        for (Object arg : point.getArgs()) {
//            if (!(arg instanceof HttpServletRequest)) {
//                continue;
//            }
//            HttpServletRequest request = (HttpServletRequest) arg;
            List<String> menus=new ArrayList<String>();
            int id = (int)request.getAttribute("userId");
           List<RoleListDto> roleListDto;
// =redisManager.getList(id+"");
////            List list=redisManager.getList(id+"");
//            if (list!=null && list.size()>0)
//            {
//                roleListDto = list;
//            }
//            else
//            {
                roleListDto=userDao.findUserRole(id);
//                redisManager.setList(id+"",roleListDto);
//            }
            if (roleListDto!=null && roleListDto.size()>0)
            {
                for (int i=0;i<roleListDto.size();i++)
                {
                    int flag=roleListDto.get(i).getLevelId();
                    switch (flag){
                        case 9:
//                           menus.addAll(Arrays.asList(methods[0]));
                            Collections.addAll(menus,methods[0]);
                           break;
                        case 1:
                        case 2:
                        case 6:
                        case 7:
//                            menus.addAll(Arrays.asList(methods[1]));
                            Collections.addAll(menus,methods[1]);
                            break;
                        default:
                            Collections.addAll(menus,methods[0]);
                            Collections.addAll(menus,methods[1]);
                    }


                }

            }


//            if(menus.contains(className+"_"+methodName))
                if(menus.contains(methodName))
                return point.proceed();


             throw new CustomerException(2,"您没有权限");

            }


            return null;
    }
}
