package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.ItemCountResponseMessage;

import java.util.List;

/**
 * 税收筹划机会数量
 */
public class ShuiShouApi extends AbstractApi<ItemCountResponseMessage> {

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
        return "/dataapi/v3/ic/getBusinessShuiShou";
    }

    @Override
    public Class<?> getResponseType() {
        return ItemCountResponseMessage.class;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
