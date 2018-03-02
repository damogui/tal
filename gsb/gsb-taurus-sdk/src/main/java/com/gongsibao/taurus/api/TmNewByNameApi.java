package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.TmNewResponseMessage;

import java.util.List;

/**
 * Created by wk on 2018/3/1.
 */
public class TmNewByNameApi extends AbstractApi<TmNewResponseMessage> {


    @Override
    public String getUrl() {
        return "/data/api/trademark/tmname";
    }
    @Override
    public int getInterfaceType() {
        return 3;
    }

    @Override
    public Class<?> getResponseType() {
        return TmNewResponseMessage.class;
    }
}
