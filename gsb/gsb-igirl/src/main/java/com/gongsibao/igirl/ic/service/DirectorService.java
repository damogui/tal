package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.IcDirector;
import com.gongsibao.entity.igirl.ic.baseinfo.IcMember;
import com.gongsibao.igirl.ic.base.IDirectorService;
import org.netsharp.communication.Service;

@Service
public class DirectorService extends GsbPersistableService<IcDirector> implements IDirectorService {
    public DirectorService() {
        super();
        this.type = IcDirector.class;
    }
}
