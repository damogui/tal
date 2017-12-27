package com.gongsibao.sms.base;

import java.util.Collection;
import java.util.Map;

import com.gongsibao.sms.dto.SmsRequest;
import com.gongsibao.sms.dto.SmsResponse;
import com.gongsibao.sms.dto.SmsTemplateResponse;

public interface ISmsService {
    /**发送短信*/
    public SmsResponse send(Integer appId, String mobilePhone, String content);
    public void send(Integer appId, Collection<String> mobilePhone, String content);

    /**通过模板发送短信*/
    public SmsResponse send(Integer appId, String mobilPhone, Integer templateId, Map<String, String> paras);

    /**
     * 发送短信（支持模板发送和非模板发送）
     */
    public SmsResponse send(SmsRequest smsRequest);

    /**
     * 获取短信模板
     */
    public SmsTemplateResponse getTemplateBy(Integer templateId, Map<String, String> smsRequestParas);

    public String sendSmsValidCode(String keyPrev, Integer appId, String mobilePhone, String content);
    public String sendSmsValidCode(String keyPrev, Integer appId, String mobilePhone, String content, int time);

    public String sendSmsValidCode(Integer appId, String mobilePhone, String content);

    public String sendSmsValidCode(String keyPrev, String mobilePhone, String content);

    public String sendSmsValidCode(String mobilePhone, String content);

    public String sendSmsValidCode(String mobilePhone);

    public boolean validMobileCode(String key, String code, Boolean isClean);
}
