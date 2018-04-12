package com.gongsibao.rest;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.gongsibao.rest.common.util.RedisClient;
import com.gongsibao.rest.common.util.WxRedisConfigStorage;
import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="wechat")
@ComponentScan("com.gongsibao.rest")
public class WeixinConfig {
    private String url;
    private String appid;
    private String appsecret;
    private String token;
    private String partnerId;
    private String partnerKey;
    private String keyPath;
    private String payNotifyUrl;

    @Autowired
    private RedisClient redisClient;

    @Bean(name = "wxMpService")
    public WxMpService wxMpService() {
        WxRedisConfigStorage config = new WxRedisConfigStorage();
        config.setAppId(appid);
        config.setSecret(appsecret);
        config.setToken(token);
        config.setRedisClient(redisClient);

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);
        return wxService;
    }

    @Bean(name = "wxPayService")
    public WxPayService wxPayService() {
        WxPayService wxPayService = new WxPayServiceImpl();
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(appid);
        wxPayConfig.setMchId(partnerId);
        wxPayConfig.setMchKey(partnerKey);
        wxPayConfig.setKeyPath(keyPath);
        wxPayService.setConfig(wxPayConfig);
        return wxPayService;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerKey() {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
}
