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
    private String name;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("name=" + this.getName());
        }
    }

    // TODO 接口地址错误，后续学强会给出
    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getTmNameListByCompanyName";
    }

    @Override
    public Class<?> getResponseType() {
        return TmAssembleResponseMessage.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
