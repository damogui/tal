package com.gongsibao.uc.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.uc.User;

public interface IUserService extends IPersistableService<User> {
	
	User byMobilePhone(String mobilePhone);
	
	Boolean hasMobile(Integer id,String mobile);
}