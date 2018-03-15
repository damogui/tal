package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.TmOccupiedResponseMessage;

import java.util.List;

public class TmOccupiedApi extends AbstractApi<TmOccupiedResponseMessage> {
    /**
     * 公司名称
     */
    private String name;

    @Override
    public String getUrl() {
        return "/dataapi/v3/ic/getTmCountByOccupied";
    }

    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return TmOccupiedResponseMessage.class;
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
