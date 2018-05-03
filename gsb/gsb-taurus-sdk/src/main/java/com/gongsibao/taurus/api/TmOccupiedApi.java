package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.TmAssembleResponseMessage;

import java.util.List;

public class TmOccupiedApi extends AbstractApi<TmAssembleResponseMessage> {
    /**
     * 公司名称
     */
    private String name;

    @Override
    public String getUrl() {
        return "/dataapi/v3/ic/getTmListByOccupied";
    }

    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return TmAssembleResponseMessage.class;
    }

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("name=" + this.getName());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
