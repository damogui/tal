package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.Copyright;
import com.gongsibao.yj.base.ICopyrightService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CopyrightService extends PersistableService<Copyright> implements ICopyrightService {

    public CopyrightService(){
        super();
        this.type=Copyright.class;
    }
}