package com.gongsibao.sms.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gongsibao.sms.base.ISmsService;
import com.gongsibao.sms.base.cache.ICacheService;
import com.gongsibao.sms.dto.SmsRequest;
import com.gongsibao.sms.dto.SmsResponse;
import com.gongsibao.sms.dto.SmsTemplateResponse;
import com.gongsibao.sms.utils.HttpClientUtil;
import com.gongsibao.sms.utils.JsonUtils;
import com.gongsibao.sms.utils.PropertiesReader;
import com.gongsibao.sms.utils.constant.ConstantCache;
import com.gongsibao.sms.utils.security.NumberUtils;
import com.gongsibao.sms.utils.security.StringUtils;


@Service("smsService")
public class SmsServiceImpl implements ISmsService, InitializingBean {

    Log log = LogFactory.getLog(ISmsService.class);

    @Autowired
    private ICacheService cacheService;

	// Read from config(sms_api_url)
    private String smsApiUrl = "http://192.168.16.52:2102/api";
    
    public SmsResponse send(Integer appId, String mobilePhone, String content) {
        SmsRequest request = new SmsRequest();
        request.setAppId(appId);
        request.setMobilePhone(mobilePhone);
        request.setContent(content);
        return send(request);
    }
    public void send(final Integer appId, final Collection<String> mobilePhone, final String content) {
        new Thread() {
            @Override
            public void run() {
                if (CollectionUtils.isEmpty(mobilePhone)) {
                    return;
                }
                for (String mobile : mobilePhone) {
                    send(appId, mobile, content);
                }
            }
        }.start();
    }

    public SmsResponse send(Integer appId, String mobilPhone, Integer templateId, Map<String, String> paras) {
        SmsRequest request = new SmsRequest();
        request.setAppId(appId);
        request.setMobilePhone(mobilPhone);
        request.setTemplateId(templateId);
        request.setTemplateParas(paras);
        return send(request);
    }

    public SmsResponse send(SmsRequest smsRequest) {

        SmsResponse response = new SmsResponse();
        if (StringUtils.isBlank(smsRequest.getMobilePhone())) {
            response.setIsSuccessful(false);
            response.setStatusMessage("手机号不能为空。");
            return response;
        }

        if (smsRequest.getAppId() < 1) {
            response.setIsSuccessful(false);
            response.setStatusMessage("应用程序Id必须指定。");
            return response;
        }


        String contentJson = JsonUtils.objectToJson(smsRequest);
        SmsResponse smsResponse = new SmsResponse();
        String result = null;
        try {
            Map<String, String> map = getCommonHearsMap();
            String sendUrl = smsApiUrl + "/Send";
            //log.info("\n============================sendUrl[" + sendUrl + "]==============================================================");
            result = HttpClientUtil.httpPost(sendUrl, map, contentJson, "utf-8");
            //log.info("\n============================result[" + result + "]==============================================================");
            if (StringUtils.isNotBlank(result)) {
                smsResponse = JsonUtils.jsonToObject(result, SmsResponse.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
            smsResponse.setIsSuccessful(false);
            smsResponse.setSmsId(-1);
            smsResponse.setStatusCode(-1);
            smsResponse.setStatusMessage(e.getMessage());
        }

        return smsResponse;
    }

    public SmsTemplateResponse getTemplateBy(Integer templateId, Map<String, String> smsRequestParas) {
        SmsTemplateResponse response = null;
        String result = null;
        try {
            Map<String, String> map = getCommonHearsMap();
            String contentJson = JsonUtils.objectToJson(smsRequestParas);

            String sendUrl = smsApiUrl + "/SmsTemplates/?templateId=" + templateId + "&paras=" + URLEncoder.encode(contentJson);
            result = HttpClientUtil.httpGet(sendUrl, "utf-8", map);

            if (StringUtils.isNotBlank(result)) {
                response = JsonUtils.jsonToObject(result, SmsTemplateResponse.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String sendSmsValidCode(String keyPrev, Integer appId, String mobilePhone, String content) {
        return sendSmsValidCode(keyPrev, appId, mobilePhone, content, 0);
    }

    public String sendSmsValidCode(String keyPrev, Integer appId, String mobilePhone, String content, int time) {
        if (time == 0) {
            time = ConstantCache.TIME_HALF_HOUR;
        }
        String validCode = StringUtils.trimToEmpty(keyPrev) + mobilePhone;
        String code = StringUtils.trimToEmpty(cacheService.get(validCode));
        if (StringUtils.isBlank(code)) {
            code = RandomStringUtils.randomNumeric(6);
            boolean put = cacheService.put(validCode, code, time);
            System.out.println(put);
        } else {
            cacheService.put(validCode, code, time);
        }

        String timesKey = StringUtils.trimToEmpty(keyPrev) + mobilePhone + "_time";
        int isSend = NumberUtils.toInt(cacheService.get(timesKey, Integer.class));

        if (StringUtils.isBlank(content)) {
            content = "【公司宝】本次操作的验证码为：{code}，30分钟内有效。如非您本人操作，请忽略该短信。如需帮助，请拨打客服电话：4006-798-999。";
        }

        if (null == appId || appId == 0) {
            appId = 2;
        }

        final int smsAppId = appId;
        final String smsContent = content.replace("{code}", code);
        if (isSend == 0 && BooleanUtils.toBoolean(PropertiesReader.getValue("project", "is_send"))) {
            new Thread() {
                @Override
                public void run() {
                    send(smsAppId, mobilePhone, smsContent);
                }
            }.start();
            cacheService.put(timesKey, 1, ConstantCache.TIME_ONE_MINUTE);
        }
        //log.info("code=" + code + "| mobilePhone : " + mobilePhone);
        return code;
    }

    public String sendSmsValidCode(Integer appId, String mobilePhone, String content) {
        return sendSmsValidCode(null, appId, mobilePhone, content);
    }

    public String sendSmsValidCode(String keyPrev, String mobilePhone, String content) {
        return sendSmsValidCode(keyPrev, 0, mobilePhone, content);
    }

    public String sendSmsValidCode(String mobilePhone, String content) {
        return sendSmsValidCode(null, 0, mobilePhone, content);
    }

    public String sendSmsValidCode(String mobilePhone) {
        return sendSmsValidCode(null, 0, mobilePhone, null);
    }

    public boolean validMobileCode(String key, String code, Boolean isClean) {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
            return false;
        }
        String cacheCode = StringUtils.trimToEmpty(cacheService.get(key));
        if (isClean) {
            cacheService.delete(key);
        }
        if (StringUtils.isBlank(cacheCode)) {
            return false;
        }

        if (!cacheCode.equals(code)) {
            return false;
        }
        return true;
    }

    /**
     * 生成公用的
     */
    private Map<String, String> getCommonHearsMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("accept", "application/json");
        map.put("content-type", " application/json");
        return map;
    }

    public void afterPropertiesSet() throws Exception {
        smsApiUrl = PropertiesReader.getValue("project", "sms_api_url");
    }
}
