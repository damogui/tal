package com.gongsibao.franchisee.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.franchisee.FranchiseeBusinessScope;
import com.gongsibao.franchisee.base.IFranchiseeBusinessScopeService;

@Service
public class FranchiseeBusinessScopeService extends PersistableService<FranchiseeBusinessScope> implements IFranchiseeBusinessScopeService {

    public FranchiseeBusinessScopeService(){
        super();
        this.type=FranchiseeBusinessScope.class;
    }
}