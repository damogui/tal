package com.gongsibao.crm.service.action.customer.member;

import java.util.Random;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.EncrypUtil;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.crm.NCustomer;

/**
 * @author hw 开通会员：保存Account
 */
public class ActionMemberPersist implements IAction {

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
			account.setSourceClientId(1035);
			account.setIdentityCard("");
			int randomPwd = Math.abs(new Random(7).nextInt());
			String pwd = EncrypUtil.md5(randomPwd + "user!@#123").substring(8, 24);
			account.setPasswd(pwd);
			account.setTicket("");

			IAccountService accountService = ServiceFactory.create(IAccountService.class);
			account = accountService.save(account);

			// 放到map中，回写时使用
			ctx.getStatus().put("account", account);
			ctx.getStatus().put("pwd", String.valueOf(randomPwd));
		}
	}

}
