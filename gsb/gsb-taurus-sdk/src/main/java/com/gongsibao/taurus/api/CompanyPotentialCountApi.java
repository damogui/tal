package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CompanyPotentialCountResponseMessage;

import java.util.List;

/**
 * 查询公司潜在机会
 */
public class CompanyPotentialCountApi extends AbstractApi<CompanyPotentialCountResponseMessage> {

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
        return "/dataapi/v3/ic/getBusinessRecommendCount";
    }

    @Override
    public Class<?> getResponseType() {
        return CompanyPotentialCountResponseMessage.class;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
