package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.ITitleDescService;
import com.gongsibao.entity.cms.TitleDesc;

@Service
public class TitleDescService extends PersistableService<TitleDesc> implements ITitleDescService {

    public TitleDescService(){
        super();
        this.type=TitleDesc.class;
    }
}