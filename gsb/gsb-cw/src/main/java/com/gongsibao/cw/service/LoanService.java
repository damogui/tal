package com.gongsibao.cw.service;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.entity.cw.Loan;

@Service
public class LoanService extends PersistableService<Loan> implements ILoanService{
	public LoanService() {
		super();
		this.type = Loan.class;
	}

	@Override
	public Boolean saveLoan(Loan loan) {
		ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/cw/loan/form");
            ctx.setItem(loan);
            ctx.setState(loan.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
	}
}
