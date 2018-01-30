package com.gongsibao.crm.service.action.customer.member;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.uc.Account;
import com.gongsibao.uc.base.IAccountService;

/**
 * @author hw
 * 开通会员：保存Account
 */
public class ActionMemberPersist implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		NCustomer customer = (NCustomer) ctx.getItem();
		Account account = new Account();
		{
			account.toNew();
			account.setName(customer.getRealName());
			account.setRealName(customer.getRealName());
			account.setEmail(customer.getEmail());
			account.setMobilePhone(customer.getMobile());
			account.setTelephone(customer.getTelephone());
			account.setHeadThumbFileId(0);
			account.setSourceClientId(0);
			account.setIdentityCard("");
			// 密码算法？
			account.setPasswd("");
			account.setTicket("");
			
			IAccountService accountService = ServiceFactory.create(IAccountService.class);
			account = accountService.save(account);
			
			//放到map中，回写时使用
			ctx.getStatus().put("account", account);
		}
	}

}
