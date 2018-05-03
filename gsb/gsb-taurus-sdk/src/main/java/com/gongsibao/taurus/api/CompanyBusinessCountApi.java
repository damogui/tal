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
    private String name;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("name=" + this.getName());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getBusinessHasCount";
    }

    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return CompanyBusinessCountResponseMessage.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
