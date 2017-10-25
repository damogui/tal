package org.netsharp.organization.controller.authorization;

import org.netsharp.organization.entity.Employee;

public class LoginManager {
	
	public void login(String userName,String pwd){
		
		// 执行登录验证
		Employee employee = null;
		
		//验证成功后添加Session
		UserPermissionManager.addSession(employee);
	}
	
	public void logout(){
		UserPermissionManager.removeSession();
	}
}
