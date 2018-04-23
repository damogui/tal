package com.gongsibao.rest.web.common.util.wxpay;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.PublicAccount;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class WXPayConfig extends IWXPayConfig{

    private byte[] certData;
    private static WXPayConfig INSTANCE;
    private static PublicAccount account;
    private WXPayConfig() throws Exception{
    }
    private WXPayConfig(String oid) throws Exception{
        IPublicAccountService publicAccountService= ServiceFactory.create(IPublicAccountService.class);
        account=publicAccountService.byOriginalId(oid);
    }

    public static WXPayConfig getInstance(String oid) throws Exception{
        if (INSTANCE == null) {
            synchronized (WXPayConfig.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfig(oid);
                }
            }
        }
        return INSTANCE;
    }

    public String getAppID() {
        return account.getAppId();
    }

    public String getMchID() {
        return account.getMch_id();
    }

    public String getKey() {
        return account.getToken();
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "icompany.gongsibao.net/#/";
    }

    public String getAlternateDomain() {
        return "icompany.gongsibao.net/#/";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
