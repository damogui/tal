package com.gongsibao.cw.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.IU8BankService;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.entity.u8.dic.U8BankType;

@Service
public class U8BankService  extends PersistableService<U8Bank> implements IU8BankService {

	public U8BankService() {
		super();
		this.type = U8Bank.class;
	}

	@Override
	public List<U8Bank> getU8BankList(Integer setOfBooksId) {
		Oql oql = new Oql();
		oql.setType(U8Bank.class);
		oql.setSelects("u8Bank.*");
		oql.setFilter("setOfBooksId=? and type = ?");
		oql.getParameters().add("setOfBooksId", setOfBooksId, Types.INTEGER);
		oql.getParameters().add("type", U8BankType.Zhifu.getValue(), Types.INTEGER);
		return this.queryList(oql);
	}

}
