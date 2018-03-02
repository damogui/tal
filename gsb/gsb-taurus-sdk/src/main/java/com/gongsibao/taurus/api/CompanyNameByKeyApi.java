package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CompanyNameByKeyResponseMessage;

import java.util.List;

/**
 * 公司名称suggest
 */
public class CompanyNameByKeyApi extends AbstractApi<CompanyNameByKeyResponseMessage> {

    /**
     * key
     */
    private String key;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("key=" + this.getKey());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getNameListByKey";
    }
    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return CompanyNameByKeyResponseMessage.class;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
