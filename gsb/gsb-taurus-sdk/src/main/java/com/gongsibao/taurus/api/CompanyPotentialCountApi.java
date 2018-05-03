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
    private String name;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("name=" + this.getName());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getBusinessRecommendCount";
    }
    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return CompanyPotentialCountResponseMessage.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
