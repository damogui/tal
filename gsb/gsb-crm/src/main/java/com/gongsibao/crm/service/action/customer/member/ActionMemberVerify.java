package com.gongsibao.crm.service.action.customer.member;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.crm.NCustomer;

public class ActionMemberVerify implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomer customer = (NCustomer) ctx.getItem();
		if ((customer.getIsMember() != null && customer.getIsMember()) || (customer.getAccountId() != null && customer.getAccountId() > 0)) {

			throw new BusinessException("已经开通会员，不能重复开通！");
		}

		if (customer.getAccountId() != null && customer.getAccountId() > 0) {
			
			if (customer.getIsMember() == null || !customer.getIsMember()) {

				// 更新IsMember为true
				updateIsMember(customer.getId());
			}
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
	
	private void updateIsMember(Integer customerId){
		
		StringBuilder builer = new StringBuilder();
		{
			builer.append("UPDATE crm_customer ");
			builer.append("SET is_member = 1 ");
			builer.append(" where pkid=?");
		}
		QueryParameters qps = new QueryParameters();
		qps.add("@customerId", customerId, Types.INTEGER);
		IPersister<NCustomer> pm = PersisterFactory.create();
		pm.executeNonQuery(builer.toString(), qps);
	}

}
