package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IHotKeywrodService;
import com.gongsibao.entity.cms.HotKeywrod;

@Service
public class HotKeywrodService extends PersistableService<HotKeywrod> implements IHotKeywrodService {

    public HotKeywrodService(){
        super();
        this.type=HotKeywrod.class;
    }
}