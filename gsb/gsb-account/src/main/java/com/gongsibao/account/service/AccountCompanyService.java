package com.gongsibao.account.service;

import com.gongsibao.account.base.IAccountCompanyService;
import com.gongsibao.account.base.IAccountService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountCompany;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;

@Service
public class AccountCompanyService extends PersistableService<AccountCompany> implements IAccountCompanyService {

	public AccountCompanyService() {
		super();
		this.type = AccountCompany.class;
	}

	@Override
	public int countByAccount(int accountId, int inUse) {
		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setFilter("accountId=?");
			oql.setFilter("inUse=?");
			oql.getParameters().add("accountId", accountId, Types.INTEGER);
			oql.getParameters().add("inUse", inUse, Types.INTEGER);
		}
		return this.queryCount(oql);
	}


}