package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.trade.base.IContractService;

@Service
public class ContractService extends PersistableService<Contract> implements IContractService {

    public ContractService(){
        super();
        this.type=Contract.class;
    }
}