package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CopyrightCountResponseMessage;

import java.util.List;

/**
 * 著作权数量统计
 */
public class CopyrightCountApi extends AbstractApi<CopyrightCountResponseMessage> {
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
        return "/dataapi/v3/ic/getBusinessCopyRight";
    }

    @Override
    public Class<?> getResponseType() {
        return CopyrightCountResponseMessage.class;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
