package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyPlayer;
import com.gongsibao.yj.base.ICompanyPlayerService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyPlayerService extends PersistableService<CompanyPlayer> implements ICompanyPlayerService {

    public CompanyPlayerService(){
        super();
        this.type=CompanyPlayer.class;
    }
}