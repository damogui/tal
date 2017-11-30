package com.gongsibao.taurus.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.taurus.User;

public interface IUserService  extends IPersistableService<User>{

	User byMobile(String mobile);
}
