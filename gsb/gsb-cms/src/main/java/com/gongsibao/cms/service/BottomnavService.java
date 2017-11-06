package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IBottomnavService;
import com.gongsibao.entity.cms.Bottomnav;

@Service
public class BottomnavService extends PersistableService<Bottomnav> implements IBottomnavService {

    public BottomnavService(){
        super();
        this.type=Bottomnav.class;
    }
}