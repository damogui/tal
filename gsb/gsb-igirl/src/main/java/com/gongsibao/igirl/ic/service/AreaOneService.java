package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.AreaOne;
import com.gongsibao.igirl.ic.base.IAreaOneService;
import org.netsharp.communication.Service;

@Service
public class AreaOneService extends GsbPersistableService<AreaOne> implements IAreaOneService {
    public AreaOneService() {
        super();
        this.type = AreaOne.class;
    }
}
