package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.PatentCountResponseMessage;

import java.util.List;

/**
 * 专利各种数量统计
 */
public class PatentCountApi extends AbstractApi<PatentCountResponseMessage> {

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
        return "/dataapi/v3/ic/getBusinessPatent";
    }
    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return PatentCountResponseMessage.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
