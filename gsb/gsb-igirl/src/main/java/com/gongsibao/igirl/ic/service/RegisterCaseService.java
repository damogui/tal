package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.IcRegisterCase;
import com.gongsibao.igirl.ic.base.IRegisterCaseService;
import org.netsharp.communication.Service;

@Service
public class RegisterCaseService extends GsbPersistableService<IcRegisterCase> implements IRegisterCaseService {
    public RegisterCaseService() {
        super();
        this.type = IcRegisterCase.class;
    }
}
