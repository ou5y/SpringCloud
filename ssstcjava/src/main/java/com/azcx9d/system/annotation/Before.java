package com.azcx9d.system.annotation;

import java.lang.annotation.*;

/**
 *自定义注解 拦截Controller
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Before {
    Class<?>[] value();
}