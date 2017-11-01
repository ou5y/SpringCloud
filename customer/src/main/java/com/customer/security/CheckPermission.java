package com.customer.security;

import java.lang.annotation.*;

/**
 * Created by fangbaoyan on 2017/5/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPermission {
}
