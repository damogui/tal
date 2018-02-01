package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.TmAssembleResponseMessage;

import java.util.List;

/**
 * 查询商标聚合
 */
public class TmAssembleApi extends AbstractApi<TmAssembleResponseMessage> {

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
        return "/dataapi/v3/ic/getTmListAndClass";
    }

    @Override
    public Class<?> getResponseType() {
        return TmAssembleResponseMessage.class;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
