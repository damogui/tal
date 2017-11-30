package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.ITopnavLinkService;
import com.gongsibao.entity.cms.TopnavLink;

@Service
public class TopnavLinkService extends PersistableService<TopnavLink> implements ITopnavLinkService {

    public TopnavLinkService(){
        super();
        this.type=TopnavLink.class;
    }
}