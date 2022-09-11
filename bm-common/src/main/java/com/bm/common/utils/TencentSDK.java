package com.bm.common.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

// 导入对应SMS模块的client
import com.tencentcloudapi.sms.v20210111.SmsClient;

// 导入要请求接口对应的request response类
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;

public class TencentSDK {

    private static final String SECRET_ID = "AKIDxkLjuseCWcV9ZnuoVzGKAmBMK4aqrc7M";

    private static final String SECRET_KEY = "5xZx5xbz7zc081f5HEwObcPxTzQuJZgd";


    private static ClientProfile init() {

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        httpProfile.setConnTimeout(60);
        httpProfile.setEndpoint("sms.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);
        return clientProfile;
    }

    /**
     *
     * @param templateId 腾讯云定义模板ID
     * @param value1 模板参数1 - 验证码 or 书籍名称
     * @param value2 模板参数2 - 超时时间 or 借阅归还时间
     * @param mobile 发送人手机号
     * @return SendSmsResponse
     */
    public static SendSmsResponse sendSms(String templateId, String value1, String value2, String mobile) {
        ClientProfile clientProfile = init();
        Credential cred = new Credential(SECRET_ID, SECRET_KEY);
        SendSmsResponse res = null;
        try {
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppId("1400735154");
            req.setSignName("放学后茶局公众号");
            /* 模板 ID: 必须填写已审核通过的模板 ID */
            req.setTemplateId(templateId);

            /* 模板参数: 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空 */
            String[] templateParamSet = {value1, value2};
            req.setTemplateParamSet(templateParamSet);

            /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]*/
            String[] phoneNumberSet = {"+86" + mobile};
            req.setPhoneNumberSet(phoneNumberSet);
            res = client.SendSms(req);

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return res;
    }
    /**
     * Tencent Cloud Sms Sendsms
     */
    public static void main(String[] args) {
        sendSms("123", "123", "123", "!23");
    }
}

