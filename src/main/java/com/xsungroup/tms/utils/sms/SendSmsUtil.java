package com.xsungroup.tms.utils.sms;

import com.alibaba.fastjson.JSON;

public class SendSmsUtil {

    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "N3130706";
    // 用户平台API密码(非登录密码)
    public static String password = "UGo7qNaEpsa6f8";



    /**
     * 参数是要发送的内容、手机号
     * @param
     */
    public static void sendMsg(String phone){

        String msg = "【欣雅】那个谁,您的信息已被上海京贸物流运输有限公司添加成功，快去注册吧！点击下面的链接可下载欣物盟APP， http://xsungroupapp.com";
        String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";

        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, phone, "true");
        String requestJson = JSON.toJSONString(smsSingleRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);

        System.out.println("response after request result is :" + response);

        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

        System.out.println("response  toString is :" + smsSingleResponse);

    }

}
