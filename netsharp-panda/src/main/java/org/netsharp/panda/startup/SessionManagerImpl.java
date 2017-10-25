package org.netsharp.panda.startup;

import java.util.List;

import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.session.ISessionManager;

public class SessionManagerImpl  implements ISessionManager {
	
	public Integer getUserId(){
		UserPermission up = UserPermissionManager.getUserPermissionWithoutException();
		if(up==null){
			return null;
		}
		
		Employee employee = up.getEmployee();
		if(employee == null){
			return null;
		}
		
		return employee.getId();
	}
	
	public String getUserName(){
		
		UserPermission up = UserPermissionManager.getUserPermissionWithoutException();
		if(up==null){
			return null;
		}
		
		Employee employee = up.getEmployee();
		if(employee == null){
			return null;
		}
		
		return employee.getName();
	}
	
	public String getDepartments() {
		
		UserPermission up = UserPermissionManager.getUserPermissionWithoutException();
		if(up==null){
			return null;
		}
		
		return up.getDepartments();
	}
	
	public List<String> getDepartmentPathCodes(){
		UserPermission up = UserPermissionManager.getUserPermissionWithoutException();
		if(up==null){
			return null;
		}
		
		return up.getDepartmentPathCodes();
	}

	@Override
	public Integer getCoperationId() {
		// TODO Auto-generated method stub
		UserPermission up = UserPermissionManager.getUserPermissionWithoutException();
		if(up==null){
			return null;
		}
		
		return up.getCorperation();
	}
}
