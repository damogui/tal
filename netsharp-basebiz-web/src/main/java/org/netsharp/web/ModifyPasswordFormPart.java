package org.netsharp.web;

import org.netsharp.authorization.LoginManager;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.commerce.FormPart;

public class ModifyPasswordFormPart extends FormPart{

	/**
	 * 修改密码
	 * @param originalPassword
	 * @param newPassword
	 * @param confirmPassword
	 * @return
	 */
	public boolean save(String originalPassword,String newPassword,String confirmPassword){
		
		UserPermission up = UserPermissionManager.getUserPermission();
		Employee employee = up.getEmployee();
		if(!employee.getPwd().equals(originalPassword)){
			
			throw new BusinessException("原始密码输入错误！");
		}
		
		if(!newPassword.equals(confirmPassword)){
			
			throw new BusinessException("两次输入不一致！");
		}
		
		if(originalPassword.equals(confirmPassword)){
			
			throw new BusinessException("不能和原始密码一样");
		}
		
		employee.setPwd(confirmPassword);
		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		employee = employeeService.changPassWord(employee);
		
		//修改密码后，需要重新登录
		LoginManager manager = new LoginManager();
		manager.logout();
		return true;
	}
}
