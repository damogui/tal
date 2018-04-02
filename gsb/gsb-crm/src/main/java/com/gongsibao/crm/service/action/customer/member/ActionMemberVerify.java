package com.gongsibao.crm.service.action.customer.member;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.gardian.base.IAccountService;

public class ActionMemberVerify implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomer customer = (NCustomer) ctx.getItem();
		if (customer.getIsMember() || (customer.getAccountId() != null && customer.getAccountId() > 0)) {

			throw new BusinessException("已经开通会员，不能重复开通！");
		}

		if (StringManager.isNullOrEmpty(customer.getMobile())) {

			throw new BusinessException("手机号码为空，不能开能会员！");
		}

		IAccountService accountService = ServiceFactory.create(IAccountService.class);
		Account account = accountService.byMobile(customer.getMobile());
		if (account != null) {

			throw new BusinessException("已经开通会员，不能重复开通！");
		}
	}

}
