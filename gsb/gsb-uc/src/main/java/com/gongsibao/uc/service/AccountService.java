package com.gongsibao.uc.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.Account;
import com.gongsibao.uc.base.IAccountService;

@Service
public class AccountService extends PersistableService<Account> implements IAccountService {

	public AccountService() {
		super();
		this.type = Account.class;
	}

	@Override
	public Boolean hasMobile(String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setFilter("mobilePhone=?");
			oql.getParameters().add("@mobile", mobile, Types.VARCHAR);
		}
		return this.queryCount(oql) > 0;
	}

	@Override
	public Account byMobile(String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects("*");
			oql.setFilter("mobilePhone=?");
			oql.getParameters().add("@mobile", mobile, Types.VARCHAR);
		}
		return this.queryFirst(oql);
	}
}