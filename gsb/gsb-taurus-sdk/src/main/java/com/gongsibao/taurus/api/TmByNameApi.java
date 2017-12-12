package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.TmByNameResponseMessage;

import java.util.List;

/**
 * Created by wk on 2017/12/7.
 * 通过名称查询商标
 */
public class TmByNameApi extends AbstractApi<TmByNameResponseMessage> {

    /**
     * 商标名称（查询参数）
     */
    private String tmName;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("name=" + this.getTmName());
        }
    }

    @Override
    protected String getUrl() {
        return "/bigdata/v0.1/tmList";
    }

    @Override
    public Class<?> getResponseType() {
        return TmByNameResponseMessage.class;
    }

    public String getTmName() {
        return tmName;
    }

    public void setTmName(String tmName) {
        this.tmName = tmName;
    }
}
