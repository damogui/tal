package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CompanyBusinessCountResponseMessage;

import java.util.List;

/**
 * 公司业务数量统计
 */
public class CompanyBusinessCountApi extends AbstractApi<CompanyBusinessCountResponseMessage> {

    /**
     * 公司名称
     */
    private String names;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("names=" + this.getNames());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getBusinessHasCount";
    }

    @Override
    public Class<?> getResponseType() {
        return CompanyBusinessCountResponseMessage.class;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
