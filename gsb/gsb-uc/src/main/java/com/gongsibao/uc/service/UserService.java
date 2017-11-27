package com.gongsibao.uc.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.User;
import com.gongsibao.uc.base.IUserService;

@Service
public class UserService extends PersistableService<User> implements IUserService {

    public UserService(){
        super();
        this.type=User.class;
    }
    


	@Override
	public User byMobilePhone(String mobilePhone) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("user.{id,mobilePhone}");
			oql.setFilter(" mobilePhone=? ");
			oql.getParameters().add("mobilePhone", mobilePhone, Types.VARCHAR);
		}
		
		User entity = this.pm.queryFirst(oql);
		return entity;
	}
}