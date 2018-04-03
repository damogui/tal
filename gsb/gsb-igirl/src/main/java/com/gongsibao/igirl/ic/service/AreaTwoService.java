package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.AreaTwo;
import com.gongsibao.igirl.ic.base.IAreaTwoService;
import org.netsharp.communication.Service;

@Service
public class AreaTwoService extends GsbPersistableService<AreaTwo> implements IAreaTwoService {
    public AreaTwoService() {
        super();
        this.type = AreaTwo.class;
    }
}
