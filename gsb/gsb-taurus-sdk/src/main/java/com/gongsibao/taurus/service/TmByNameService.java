package com.gongsibao.taurus.service;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.TmByNameApi;
import com.gongsibao.taurus.entity.Tm;
import com.gongsibao.taurus.message.ResponseMessage;

import java.util.List;

/**
 * Created by wk on 2017/12/7.
 */
public class TmByNameService implements ITaurusApiService<Tm> {
    @Override
    public List<Tm> get(String name) {
        TmByNameApi api = ApiFactory.create(TmByNameApi.class);
        api.setTmName(name);
        ResponseMessage<Tm> response = api.getResponse();
        if (response == null) {
            return null;
        }
        return response.getList();
    }
}
