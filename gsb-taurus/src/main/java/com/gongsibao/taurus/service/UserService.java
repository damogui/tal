package com.gongsibao.taurus.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.User;
import com.gongsibao.taurus.base.IUserService;

@Service
public class UserService extends PersistableService< User> implements IUserService {

    public UserService(){
        super();
        this.type= User.class;
    }

	@Override
	public User byMobile(String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("mobile=?");
			oql.getParameters().add("mobile", mobile, Types.VARCHAR);
		}
		return this.queryFirst(oql);
	}
}
