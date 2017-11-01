package com.azcx9d.system.annotation;

import com.azcx9d.common.entity.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class ValidatorAspect {

    //Controller层切点
    @Pointcut("@annotation(com.azcx9d.system.annotation.Before)")
    public void controllerAspect() {

    }

    /**
     * 前置通知
     *
     * @param joinPoint 切点
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        JsonResult errMsg = null;

        // 拦截的实体类
        Object target = joinPoint.getTarget();
        // 拦截的方法名称
        String methodName = joinPoint.getSignature().getName();

        // 拦截的放参数类型
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature())
                .getMethod().getParameterTypes();

        // 获得被拦截的方法
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        if (null != method) {

            // 判断是否包含自定义的注解
            if (method.isAnnotationPresent(Before.class)) {

                Class<?>[] clazzList= method.getAnnotation(Before.class).value();
                errMsg=handleErrorMessage(request,clazzList);
            }
        }

        return errMsg==null?joinPoint.proceed():errMsg;
    }

    /**
     * 错误信息处理
     * @param request
     * @param clazzList
     * @return
     */
    public JsonResult handleErrorMessage(HttpServletRequest request, Class<?>[] clazzList){
        boolean hasError=false;
//        Map<String,Object> errMsg=new HashMap<String,Object>();
        JsonResult errMsg = null;
        try {

            for(int k=0;k<clazzList.length;k++){

                Class<?> clazz=clazzList[k];
                Class<?> superClazz=clazz.getSuperclass();

                if(superClazz==Validator.class){

                    Object invoke = clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});

                    Method validateMethod = clazz.getDeclaredMethod("validate",HttpServletRequest.class);
                    validateMethod.setAccessible(true);
                    boolean b=(boolean) validateMethod.invoke(invoke,request);
                    //如果validate返回为false，则执行HandleError方法
                    if(!b){
                        Method errMethod = clazz.getDeclaredMethod("handleError");
                        errMethod.setAccessible(true);
                        errMsg=(JsonResult) errMethod.invoke(invoke);
                        hasError=true;
                        break;
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return hasError?errMsg:null;
    }

}