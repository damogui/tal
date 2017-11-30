package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.ValidityScope;
import com.gongsibao.product.base.IValidityScopeService;

@Service
public class ValidityScopeService extends PersistableService<ValidityScope> implements IValidityScopeService {

    public ValidityScopeService(){
        super();
        this.type=ValidityScope.class;
    }
}