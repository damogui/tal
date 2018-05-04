package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.IcUnPerson;
import com.gongsibao.igirl.ic.base.IUnPersonService;
import org.netsharp.communication.Service;

@Service
public class UnPersonoService extends GsbPersistableService<IcUnPerson> implements IUnPersonService {
    public UnPersonoService() {
        super();
        this.type = IcUnPerson.class;
    }
}
