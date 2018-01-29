package com.gongsibao.crm.service.action.customer.member;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.crm.NCustomer;

public class ActionMemberVerify implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		NCustomer customer = (NCustomer) ctx.getItem();
		if (customer.getIsMember()) {

			throw new BusinessException("已经开通会员，不能重复开通！");
		}
		
		if (StringManager.isNullOrEmpty(customer.getMobile())) {

			throw new BusinessException("手机号码为空，不能开能会员！");
		}
	}

}
