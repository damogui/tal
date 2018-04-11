package com.gongsibao.rest.common.util;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

public class WxRedisConfigStorage extends WxMpInMemoryConfigStorage {
    private final static String ACCESS_TOKEN_KEY = "wechat_access_token_";

    private final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket_";

    private RedisClient redisClient;

    @Override
    public String getAccessToken() {
        try {
            return redisClient.get(ACCESS_TOKEN_KEY.concat(appId));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isAccessTokenExpired() {
        return getAccessToken() == null ? true : false;
    }

    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        try {
            redisClient.set(ACCESS_TOKEN_KEY.concat(appId), accessToken, Long.valueOf(expiresInSeconds - 200));
        } catch (Exception e) {
        }
    }

    @Override
    public void expireAccessToken() {
        try {
            redisClient.expire(ACCESS_TOKEN_KEY.concat(appId), 0);
        } catch (Exception e) {
        }
    }

    @Override
    public String getJsapiTicket() {
        try {
            return redisClient.get(JSAPI_TICKET_KEY.concat(appId));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isJsapiTicketExpired() {
        return getJsapiTicket() == null ? true : false;
    }

    @Override
    public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
        try {
            redisClient.set(JSAPI_TICKET_KEY.concat(appId), jsapiTicket, Long.valueOf(expiresInSeconds - 200));
        } catch (Exception e) {
        }
    }

    @Override
    public void expireJsapiTicket() {
        try {
            redisClient.expire(JSAPI_TICKET_KEY.concat(appId), 0);
        } catch (Exception e) {
        }
    }
    public void setRedisClient(RedisClient redisClient){
        this.redisClient=redisClient;
    }
}
