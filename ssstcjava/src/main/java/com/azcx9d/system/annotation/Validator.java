package com.azcx9d.system.annotation;

import com.azcx9d.common.entity.JsonResult;

import javax.servlet.http.HttpServletRequest;


public abstract class Validator{

    protected abstract boolean validate(HttpServletRequest request);

    protected abstract JsonResult handleError();
}
