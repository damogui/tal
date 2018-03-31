package com.gongsibao.cw.service;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.entity.cw.Expense;

@Service
public class ExpenseService extends PersistableService<Expense> implements IExpenseService{

	public ExpenseService() {
		super();
		this.type = Expense.class;
	}

	@Override
	public Boolean saveExpense(Expense entity) {
		ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/cw/expense/form");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
	}
}
