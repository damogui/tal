package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;

@Service
public class DictService extends PersistableService<Dict> implements IDictService {

    public DictService(){
        super();
        this.type=Dict.class;
    }
}