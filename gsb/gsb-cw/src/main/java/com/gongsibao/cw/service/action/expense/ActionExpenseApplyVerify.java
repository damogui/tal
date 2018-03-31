package com.gongsibao.cw.service.action.expense;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.cw.base.ILoanService;

public class ActionExpenseApplyVerify implements IAction {

	ILoanService loanService = ServiceFactory.create(ILoanService.class);

    @Override
    public void execute(ActionContext ctx) {
       

    }

}
