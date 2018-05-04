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
    private String name;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("name=" + this.getName());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getBusinessCopyRight";
    }
    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return CopyrightCountResponseMessage.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
