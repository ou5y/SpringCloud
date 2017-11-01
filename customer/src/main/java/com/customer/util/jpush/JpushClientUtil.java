package com.customer.util.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.*;
import com.customer.util.CalendarUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/3 0003.
 */
public class JpushClientUtil {
    private final static String appKey = "2fdcfd52e6c7a4883530609e";

    private final static String masterSecret = "23167a11923ed1337e085d76";

//     代理端
//     Appkey:2fdcfd52e6c7a4883530609e Secret:23167a11923ed1337e085d76
//    商户端
//    appKey :6cd33dd42b2f362f84fc50dc  Master Secret:f6191861f02e5caf1d4c1504

//    private static JPushClient jPushClient = new JPushClient(masterSecret, appKey);
      private static JPushClient jPushClient;

      // 创建jPushClient对象
      public static void init(String appType){
          if(appType.equals("1") || appType.equals("4")){
              jPushClient = new JPushClient("f6191861f02e5caf1d4c1504", "6cd33dd42b2f362f84fc50dc");
          }else if(appType.equals("2") || appType.equals("5")){
              jPushClient = new JPushClient("23167a11923ed1337e085d76", "2fdcfd52e6c7a4883530609e");
          }else if(appType.equals("3") || appType.equals("6")){
              jPushClient = new JPushClient("5d25b345a453c399c22c92af", "5b6740390bccf63df8a22275");
          }
      }

    /**
     * 推送给设备标识参数的用户
     *
     * @param registrationId     设备标识
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendToRegistrationId(String registrationId, String notification_title, String msg_title, String msg_content, String extrasparam) {
        int result = 0;
        try {
            PushPayload pushPayload = JpushClientUtil.buildPushObject_all_registrationId_alertWithTitle(registrationId, notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 推送给设备标识参数的用户
     *
     * @param alias     设备别名
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendToAlias(String alias, String notification_title, String msg_title, String msg_content, String extrasparam) {
        int result = 0;
        try {
            PushPayload pushPayload = JpushClientUtil.buildPushObject_all_alias_alertWithTitle(alias, notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送给所有安卓用户
     *
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendToAllAndroid(String notification_title, String msg_title, String msg_content, String extrasparam) {
        int result = 0;
        try {
            PushPayload pushPayload = JpushClientUtil.buildPushObject_android_all_alertWithTitle(notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    /**
     * 发送给所有IOS用户
     *
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendToAllIos(String notification_title, String msg_title, String msg_content, String extrasparam) {
        int result = 0;
        try {
            PushPayload pushPayload = JpushClientUtil.buildPushObject_ios_all_alertWithTitle(notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    /**
     * 发送给所有用户
     *
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendToAll(String notification_title, String msg_title, String msg_content, String extrasparam) {
        int result = 0;
        try {
            PushPayload pushPayload = JpushClientUtil.buildPushObject_android_and_ios(notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }


    public static PushPayload buildPushObject_android_and_ios(String notification_title, String msg_title, String msg_content, String extrasparam) {

        Map<String,String> extras = new HashMap<String,String>(0);
        if(extrasparam!=null && !"".equals(extrasparam)) {
            String [] paramArrs = extrasparam.split(",");
            for(int i=0;i<paramArrs.length;i++){
                String exparam = paramArrs[i];
                int index = exparam.indexOf("-");
                String key = exparam.substring(0,index);
                String value = exparam.substring(index+1,exparam.length());
                if(key.contains("Time")){
                    Date date = CalendarUtil.formatByTime(value);
                    value = date.getTime()+"";
                }

                extras.put(key,value);
            }
        }

        IosAlert iosAlert =  IosAlert.newBuilder().setTitleAndBody(notification_title,null, msg_content).build();
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert(notification_title)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(msg_content)
                                //自定义消息标题
//                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extras)
                                .build()
                        )
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(msg_content)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extras)
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)

                                .build()
                        )
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtras(extras)
                        .build())

                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build()
                )
                .build();
    }

    private static PushPayload buildPushObject_all_registrationId_alertWithTitle(String registrationId, String notification_title, String msg_title, String msg_content, String extrasparam) {

        System.out.println("----------buildPushObject_all_all_alert");
        //创建一个IosAlert对象，可指定APNs的alert、title等字段
        //IosAlert iosAlert =  IosAlert.newBuilder().setTitleAndBody("title", "alert body").build();

        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
//                .setAudience(Audience.registrationId(registrationId))
                .setAudience(Audience.alias(registrationId))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(msg_content)
                                //自定义消息标题
//                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
//                               .addExtras(extras)

                                .build())
                        //指定当前推送的iOS通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(msg_content)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
//                                .addExtras(extras)

                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                //取消此注释，消息推送时ios将无法在锁屏情况接收
                                // .setContentAvailable(true)
                                .build())


                        .build())
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()

                        .setMsgContent(msg_content)

                        .setTitle(msg_title)

//                        .addExtras(extras)

//                        .addExtra("message extras key", extrasparam)

                        .build())
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；
                        .setTimeToLive(86400)

                        .build())

                .build();

    }

    private static PushPayload buildPushObject_all_alias_alertWithTitle(String alias, String notification_title, String msg_title, String msg_content, String extrasparam) {

        Map<String,String> extras = new HashMap<String,String>(0);
        if(extrasparam!=null && !"".equals(extrasparam)) {
            String [] paramArrs = extrasparam.split(",");
            for(int i=0;i<paramArrs.length;i++){
                String exparam = paramArrs[i];
                int index = exparam.indexOf("-");
                String key = exparam.substring(0,index);
                String value = exparam.substring(index+1,exparam.length());
                if(key.contains("Time")){
                    Date date = CalendarUtil.formatByTime(value);
                    value = date.getTime()+"";
                }

                extras.put(key,value);
            }
        }

        //创建一个IosAlert对象，可指定APNs的alert、title等字段
        IosAlert iosAlert =  IosAlert.newBuilder().setTitleAndBody(notification_title,null, msg_content).build();
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
//                .setAudience(Audience.registrationId(registrationId))
                .setAudience(Audience.alias(alias))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(msg_content)
                                //自定义消息标题
//                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）

                                .addExtras(extras)


                                .build())
                        //指定当前推送的iOS通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(iosAlert)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extras)
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                //取消此注释，消息推送时ios将无法在锁屏情况接收
                                // .setContentAvailable(true)
                                .build())


                        .build())
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtras(extras)
                        .build())
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；
                        .setTimeToLive(86400)

                        .build())

                .build();

    }

    private static PushPayload buildPushObject_android_all_alertWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam) {
        Map<String,String> extras = new HashMap<String,String>(0);
        if(extrasparam!=null && !"".equals(extrasparam)) {
            String [] paramArrs = extrasparam.split(",");
            for(int i=0;i<paramArrs.length;i++){
                String exparam = paramArrs[i];
                int index = exparam.indexOf("-");
                String key = exparam.substring(0,index);
                String value = exparam.substring(index+1,exparam.length());
                if(key.contains("Time")){
                    Date date = CalendarUtil.formatByTime(value);
                    value = date.getTime()+"";
                }

                extras.put(key,value);
            }
        }

        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.all())
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(msg_content)
                                //自定义消息标题
//                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extras)
                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
//                        .setTitle(msg_title)
                        .addExtras(extras)
                        .build())

                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    private static PushPayload buildPushObject_ios_all_alertWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam) {
        Map<String,String> extras = new HashMap<String,String>(0);
        if(extrasparam!=null && !"".equals(extrasparam)) {
            String [] paramArrs = extrasparam.split(",");
            for(int i=0;i<paramArrs.length;i++){
                String exparam = paramArrs[i];
                int index = exparam.indexOf("-");
                String key = exparam.substring(0,index);
                String value = exparam.substring(index+1,exparam.length());
                if(key.contains("Time")){
                    Date date = CalendarUtil.formatByTime(value);
                    value = date.getTime()+"";
                }

                extras.put(key,value);
            }
        }

        return PushPayload.newBuilder()
            //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
            .setPlatform(Platform.ios())
            //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
            .setAudience(Audience.all())
            //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
            .setNotification(Notification.newBuilder()
                    //指定当前推送的android通知
                    .addPlatformNotification(IosNotification.newBuilder()
                            //传一个IosAlert对象，指定apns title、title、subtitle等
                            .setAlert(msg_content)
                            //直接传alert
                            //此项是指定此推送的badge自动加1
                            .incrBadge(1)
                            //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                            // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                            .setSound("sound.caf")
                            //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                            .addExtras(extras)
                            //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                            // .setContentAvailable(true)

                            .build())
                    .build()
            )
            //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
            // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
            // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
            .setMessage(Message.newBuilder()
                    .setMsgContent(msg_content)
                    .setTitle(msg_title)
                    .addExtras(extras)
                    .build())

            .setOptions(Options.newBuilder()
                    //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                    .setApnsProduction(false)
                    //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                    .setSendno(1)
                    //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                    .setTimeToLive(86400)
                    .build())
            .build();
    }

//    public static void main(String[] args){
//
////976a5cb1247c42d6a5c26ec20255025b      IOS                   3410e9c626054dfd8fff45e42179f322    Android
//        String registrationId = "976a5cb1247c42d6a5c26ec20255025b";
//        String notificationTitle = "全团了服务商端";
//        String msgTitle = "消息标题";
//        String msgContent = "消息内容没有标题Iosaaaaaaaaa";
//        String extrasParam = "扩展参数";
////
////        JPushClient jPushClient = getJPushClient("1");
////        if(JpushClientUtil.sendToRegistrationId(registrationId,notificationTitle,msgTitle,msgContent,extrasParam)==1){
////            System.out.println("推送成功");
////        }
//
////        if(JpushClientUtil.sendToAlias(registrationId,notificationTitle,msgTitle,msgContent,extrasParam)==1){
////            System.out.println("推送成功");
////        }
//
//
//        if(JpushClientUtil.sendToAll(notificationTitle,msgTitle,msgContent,extrasParam)==1){
//            System.out.println("推送成功,哈哈哈哈哈哈哈哈哈哈哈哈");
//        }
//
//    }
}
