package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CompanyInfoResponseMessage;

import java.util.List;

/**
 * 查询公司详情api
 */
public class CompanyInfoApi extends AbstractApi<CompanyInfoResponseMessage> {

    /**
     * 公司名称
     */
    private String companyName;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("name=" + this.getCompanyName());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getByName";
    }
    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return CompanyInfoResponseMessage.class;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
