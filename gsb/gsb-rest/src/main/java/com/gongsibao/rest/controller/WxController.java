package com.gongsibao.rest.wechat.v1;

import com.gongsibao.entity.Result;
import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.web.WebResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/wx/{v}")
@Slf4j
@Api(1)
public class WxController {
    @Resource(name = "wxMpService")
    WxMpService wxMpService;
    /**
     * 获取公众号jsSignature
     *
     * @param url
     * @return
     */
    @RequestMapping("/jsSignature")
    @ResponseBody
    public WebResult getJsapiSignature(String url) {
        try {
            log.info("jsSignature url={}", url);

            WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(url);
            Map<String, String> map = new HashMap<>();
            map.put("url", jsapiSignature.getUrl());
            map.put("appId", jsapiSignature.getAppId());
            map.put("nonceStr", jsapiSignature.getNonceStr());
            map.put("signature", jsapiSignature.getSignature());
            map.put("timestamp", jsapiSignature.getTimestamp() + "");

            return WebResult.getSuccess(map);
        } catch (WxErrorException e) {
            log.error("jsSignature ex={}", e);
        }
        return WebResult.getError(1);
    }
}
