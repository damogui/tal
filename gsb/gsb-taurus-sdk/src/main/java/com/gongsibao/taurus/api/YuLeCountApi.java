package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.ItemCountResponseMessage;

import java.util.List;

/**
 * 娱乐牌照机会数量
 */
public class YuLeCountApi extends AbstractApi<ItemCountResponseMessage> {

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
        return "/dataapi/v3/ic/getBusinessYuLe";
    }

    @Override
    public Class<?> getResponseType() {
        return ItemCountResponseMessage.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
