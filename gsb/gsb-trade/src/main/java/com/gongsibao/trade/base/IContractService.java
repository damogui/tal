package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.Contract;
import org.netsharp.core.annotations.Transaction;

public interface IContractService extends IPersistableService<Contract> {

    @Transaction
    Contract saveContract(Contract contract);
}