package com.gongsibao.account.base;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountCompany;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface IAccountCompanyService extends IPersistableService<AccountCompany> {
	/**
	 * @Description:TODO
	 * @param
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/11 17:33
	 */
	int countByAccount(int accountId, int inUse);

	List<AccountCompany> findByAccount(int accountId, int inUse);
}