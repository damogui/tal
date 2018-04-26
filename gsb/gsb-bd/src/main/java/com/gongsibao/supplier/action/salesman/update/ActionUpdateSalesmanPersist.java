package com.gongsibao.supplier.action.salesman.update;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.util.EncrypUtil;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.SalesmanRole;
import com.gongsibao.supplier.service.SalesmanService;

public class ActionUpdateSalesmanPersist implements IAction{

	@Override
	public void execute(ActionContext ctx) {

		Salesman entity = (Salesman) ctx.getItem();
		IEmployeeService service = ServiceFactory.create(IEmployeeService.class);
		Employee employee = service.byId(entity.getEmployeeId());

		if (!StringManager.isNullOrEmpty(entity.getNewPassword())) {

			String pwd = EncrypUtil.md5(entity.getNewPassword() + "user!@#123").substring(8, 24);
			employee.setPwd(pwd);
		}

		//为了能够修改登录帐号，应该专门有一个功能是修改登录帐号的。
		entity.setLoginName(entity.getMobile());
		
		employee.setName(entity.getName());
		employee.setMobile(entity.getMobile());
		employee.setLoginName(entity.getLoginName());
		employee.setEmail(entity.getEmail());
		employee.setEntryDate(entity.getEntryDate());
		employee.setQuitDate(entity.getQuitDate());
		employee.setDisabled(entity.getDisabled());

		List<RoleEmployee> roleEmployeeList = employee.getRoles();
		
		//将旧的全部删除
		for (RoleEmployee roleEmployee : roleEmployeeList) {
			roleEmployee.toDeleted();
		}

		//创建新的
		RoleEmployee roleEmployee = null;
		List<SalesmanRole> salesmanRoleList = entity.getRoles();
		for (SalesmanRole salesmanRole : salesmanRoleList) {

			if(salesmanRole.getEntityState() != EntityState.Deleted){

				roleEmployee = new RoleEmployee();
				roleEmployee.toNew();
				roleEmployee.setEmployeeId(employee.getId());
				roleEmployee.setRoleId(salesmanRole.getRoleId());
				roleEmployeeList.add(roleEmployee);
			}
		}

		employee.setRoles(roleEmployeeList);
		service.save(employee);
		
		entity = this.getService().save(entity);
		ctx.setItem(entity);
	}
	

	private IPersistableService<Salesman> getService() {

		Class<?> superType = SalesmanService.class.getSuperclass();
		@SuppressWarnings("unchecked")
		IPersistableService<Salesman> service = (IPersistableService<Salesman>) ReflectManager.newInstance(superType);
		return service;
	}

}
