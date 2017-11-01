package com.azcx9d.scheduled.service;

import com.azcx9d.user.dao.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by fangbaoyan on 2017/4/11.
 */
public class BusinessMaxAmountTest extends BaseJunit4Test {
    @Autowired
    private BusinessMaxAmount b;
    @Test
    public void doAdjust() throws Exception {
        b.doAdjust();
    }

}