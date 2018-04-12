package com.gongsibao.account.service;

import com.gongsibao.account.base.IAccountCompanyService;
import com.gongsibao.account.base.IAccountService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountCompany;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;
import java.util.List;

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
			oql.setFilter("account_id= " + accountId + "  and inUse=" + inUse);
		}
		return this.queryCount(oql);
	}

	@Override
	public List<AccountCompany> findByAccount(int accountId, int inUse) {
		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setFilter("accountId= " + accountId + "  and inUse=" + inUse);
		}
		return this.queryList(oql);
	}
}