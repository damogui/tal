package com.gongsibao.uc.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

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

	@Override
	public Boolean hasMobile(Integer id, String mobile) {
		
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
		}

		List<String> ss = new ArrayList<String>();
		ss.add("mobilePhone =?");
		oql.getParameters().add("mobile", mobile, Types.VARCHAR);
		if (id != null) {
			ss.add("id<>?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		String filter = StringManager.join(" AND ", ss);
		oql.setFilter(filter);

		return this.queryCount(oql)>0;
	}
}