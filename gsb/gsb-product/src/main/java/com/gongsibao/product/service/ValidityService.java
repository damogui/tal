package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Validity;
import com.gongsibao.product.base.IValidityService;

@Service
public class ValidityService extends PersistableService<Validity> implements IValidityService {

    public ValidityService(){
        super();
        this.type=Validity.class;
    }
}