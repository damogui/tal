package com.gongsibao.trade.base;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.Contract;
import org.netsharp.core.annotations.Transaction;

public interface IContractService extends IPersistableService<Contract> {

    @Transaction
    Contract saveContract(Contract contract);

    //修改合同状态
    void updateStatus(Integer id, AuditStatusType auditStatusType);

    Contract getById(Integer id);

}