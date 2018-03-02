package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.TmInfoResponseMessage;

import java.util.List;

/**
 * Created by wk on 2018/2/2.
 */
public class TmRenewalListApi extends AbstractApi<TmInfoResponseMessage> {

    /**
     * 公司名称
     */
    private String name;

    /**
     * 商标续展截止日期
     */
    private String endDate;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("name=" + this.getName());
            parameters.add("endDate=" + this.getEndDate());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getTmRenewalListByCompanyName";
    }
    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return TmInfoResponseMessage.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
