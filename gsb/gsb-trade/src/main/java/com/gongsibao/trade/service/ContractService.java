package com.gongsibao.trade.service;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.trade.base.IContractService;

@Service
public class ContractService extends PersistableService<Contract> implements IContractService {

    public ContractService() {
        super();
        this.type = Contract.class;
    }

    @Override
    public Contract saveContract(Contract contract) {
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/contract/save");
            ctx.setItem(contract);
            ctx.setState(contract.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);

        contract = (Contract) ctx.getItem();
        return contract;
    }

}