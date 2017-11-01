package com.customer.web.pay;

import com.customer.entity.ParaMap;
import com.customer.web.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 支付异步回调控制器
 *
 * Created by HuangQing on 2017/5/20 0020 12:19.
 */
@RestController
@RequestMapping("/payCallBack")
@ApiIgnore
public class PayResultController extends BaseController {


    @RequestMapping("/async/nbpayCallBack")
    public Object async() {
        ParaMap paraMap = super.getParaMap();

        return "xxxxxxxxxxxxx";
    }
}
