package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICompanyPlayerService;
import com.gongsibao.entity.crm.CompanyPlayer;

@Service
public class CompanyPlayerService extends PersistableService<CompanyPlayer> implements ICompanyPlayerService {

    public CompanyPlayerService(){
        super();
        this.type=CompanyPlayer.class;
    }
}