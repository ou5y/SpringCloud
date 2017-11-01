package com.customer.util;


import com.customer.entity.YunpianReaultEntity;
import com.customer.service.PhoneCodeService;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author fby
 *
 */


public class MobileServerUtils {

    private static final String apiKey ="c17d217536c5fcf1f6c5d8315aa86480";

    private static final String serviceURL="https://sms.yunpian.com/v2/sms/single_send.json";

    public static Map<String,YunpianReaultEntity> map = new HashMap<String,YunpianReaultEntity>();
/**
 *
 * @param text
 * @param mobile
 * @return
 * @throws ParseException
 * @throws IOException
 */
public static YunpianReaultEntity singleSend(String text, String mobile,int randNum) throws ParseException, IOException{

    Map<String, String> params = new HashMap<String, String>();
    params.put("apikey", apiKey);
    params.put("text", text);
    params.put("mobile", mobile);
    return sendPost(serviceURL, params,randNum);
}


/**
 *
 * @param url
 * @param params
 * @return
 * @throws ParseException
 * @throws IOException
 */
public static YunpianReaultEntity sendPost(String url, Map<String, String> params,int randNum) throws ParseException, IOException{
    String text="";
    //
    List<NameValuePair> list = new ArrayList<NameValuePair>();
    Iterator iterator = params.entrySet().iterator();
    while (iterator.hasNext()) {
        Entry<String, String> elem = (Entry<String, String>) iterator.next();
        list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
    }
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //
        HttpPost post = new HttpPost(url);
        HttpEntity rqEntity = new UrlEncodedFormEntity(list, "UTF-8");
        post.setEntity(rqEntity);

        //
        CloseableHttpResponse  httprs = null;
        httprs = client.execute(post);
        HttpEntity rsEntity = httprs.getEntity();
        text = EntityUtils.toString(rsEntity);
        JSONObject json = new JSONObject().fromObject(text);
        YunpianReaultEntity yre = new YunpianReaultEntity();
        yre = (YunpianReaultEntity) JSONObject.toBean(json,YunpianReaultEntity.class);

        yre.setRandNum(randNum);
        yre.setTime(System.currentTimeMillis());
        map.put(params.get("mobile"), yre);

        if(client!=null)
            client.close();

    return yre;
}


//
///**
// * 验证码校验有效性
// * @param phone
// * @param randNum
// * @return
// * @return
// */
//public static  boolean checkCode(String phone,String randNum)
//{
////return true;
//    YunpianReaultEntity entity =map.get(phone);
//    if (entity==null) {
//        return false;
//    }
//    Long inTime=entity.getTime();
//    Long nowTime = System.currentTimeMillis();
//    Long interval = (nowTime-inTime)/1000;
//
//
//    if (randNum.equals(String.valueOf(entity.getRandNum())) && interval<180)
////    if (randNum.equals(String.valueOf(entity.getRandNum())) && interval<180*60*60)
//        return true;
//    else
//        return false;
//}
//
//    /**
//     * 根据手机号码获取验证码
//     * @param phone 手机号码
//     * @return randNum 验证码
//     */
//    public static String getCode(String phone){
//        YunpianReaultEntity entity =map.get(phone);
//        if (entity!=null) {
//            return entity.getRandNum()+"";
//        }else {
//            return null;
//        }
//    }



//    /**
//     * 验证码校验有效性
//     * @param phone
//     * @param randNum
//     * @return
//     * @return
//     */
//    public static  boolean checkCode(String phone,String randNum){
//        return phoneCodeService.checkPhoneCode(phone,randNum);
//    }

    /**
     * 根据手机号码获取验证码
     * @param phone 手机号码
     * @return randNum 验证码
     */
    public static String getCode(String phone){
        YunpianReaultEntity entity =map.get(phone);
        if (entity!=null) {
            return entity.getRandNum()+"";
        }else {
            return null;
        }
    }

}
