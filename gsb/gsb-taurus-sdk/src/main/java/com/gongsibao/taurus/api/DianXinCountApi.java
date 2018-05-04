package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.ItemCountResponseMessage;

import java.util.List;

/**
 * 增值电信机会数量
 */
public class DianXinCountApi extends AbstractApi<ItemCountResponseMessage> {

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
        return "/dataapi/v3/ic/getBusinessDianXin";
    }
    @Override
    public int getInterfaceType() {
        return 1;
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
