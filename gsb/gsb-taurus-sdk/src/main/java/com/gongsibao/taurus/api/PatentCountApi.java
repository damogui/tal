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
    private String names;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("names=" + this.getNames());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getBusinessPatent";
    }

    @Override
    public Class<?> getResponseType() {
        return PatentCountResponseMessage.class;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
