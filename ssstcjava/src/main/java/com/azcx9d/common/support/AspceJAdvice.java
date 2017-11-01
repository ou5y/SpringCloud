package com.azcx9d.common.support;

import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.agency.entity.Agency;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试after,before,around,throwing,returning Advice. 
 * @author fby
 *
 */

//@Component
//@Aspect
public class AspceJAdvice {

    @Autowired
    private HttpServletRequest request;

    private Logger logger = LoggerFactory.getLogger(AspceJAdvice.class);
    /**
     * Pointcut
     * 定义Pointcut，Pointcut的名称为login()，此方法没有返回值和参数
     * 该方法就是一个标识，不进行调用
     */
    @Pointcut("execution(* com.azcx9d.user.service.UserApiService.login(..))")
    private void login() {}


    /**
     * Before
     * 在核心业务执行前执行，不能阻止核心业务的调用。
     *
     * @param joinPoint
     */
    @Before("login()")
    public void beforeAdvice(JoinPoint joinPoint) {

    }

    /**
     * After
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
     *
     * @param joinPoint
     */
    @After(value = "login()")
    public void afterAdvice(JoinPoint joinPoint) {

    }

    /**
     * Around
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     * <p>
     * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice
     * 执行完AfterAdvice，再转到ThrowingAdvice
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "login()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
       try{

           String Agent = request.getHeader("User-Agent");

           logger.info("设备类型:"+CheckAgent(Agent));

           //调用核心逻辑
           LoginExecution<Agency> execution = (LoginExecution<Agency>)pjp.proceed();

           logger.info("用户:"+execution.getData().getId()+"登陆成功");

           return execution;
       }catch (Throwable e)
       {
           return null;
       }

    }

    /**
     * 判断设备型号
      * @return
     */
    public static String CheckAgent(String agent)
    {
        String flag = "other";


        String[] keywords = { "Android", "iPhone", "iPod", "iPad"};

        for (int i=0;i<keywords.length;i++)
        {
            if (agent.contains(keywords[i]))
                flag=keywords[i];
            else
                flag=agent;
        }

        return flag;
    }



}