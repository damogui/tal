package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CompanyInfoListByKeyResponseMessage;
import com.gongsibao.taurus.message.response.CompanyInfoResponseMessage;

import java.util.List;

/**
 * 模糊查询公司信息
 */
public class CompanyInfoListByKeyApi extends AbstractApi<CompanyInfoListByKeyResponseMessage> {

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
        return "/dataapi/v3/ic/getListByKey";
    }
    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return CompanyInfoListByKeyResponseMessage.class;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
