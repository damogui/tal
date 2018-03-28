package com.gongsibao.igirl.tm.dto.TradeMark;

//首页选项
public class Step1 {
    //申请人类型,汉字：法人或其它组织、自然人
    private String appTypeId;

    //书式类型，汉字：中国大陆、国外、中国台湾、中国香港、中国澳门
    private String appGjdq;

    public String getAppTypeId() {
        return appTypeId;
    }

    public void setAppTypeId(String appTypeId) {
        this.appTypeId = appTypeId;
    }

    public String getAppGjdq() {
        return appGjdq;
    }

    public void setAppGjdq(String appGjdq) {
        this.appGjdq = appGjdq;
    }
}
