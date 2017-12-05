package com.gongsibao.uc.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.uc.base.IUserService;

public class UserFormPart extends FormPart{


	IUserService userService = ServiceFactory.create(IUserService.class);
	public Boolean hasMobile(Integer id,String mobile){
	
		return userService.hasMobile(id,mobile);
	}
}
