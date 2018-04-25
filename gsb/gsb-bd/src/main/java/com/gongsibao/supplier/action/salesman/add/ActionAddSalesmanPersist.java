package com.gongsibao.supplier.action.salesman.add;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.util.EncrypUtil;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.SalesmanRole;
import com.gongsibao.supplier.service.SalesmanService;

/**   
 * @ClassName:  ActionAddSalesmanPersist   
 * @Description:TODO 新增业务员保存
 * @author: 韩伟
 * @date:   2018年4月25日 上午11:17:26   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionAddSalesmanPersist implements IAction{

	@Override
	public void execute(ActionContext ctx) {

		Salesman entity = (Salesman) ctx.getItem();
		if (StringManager.isNullOrEmpty(entity.getNewPassword())) {

			throw new BusinessException("请设置密码");
		}

		String pwd = EncrypUtil.md5(entity.getNewPassword() + "user!@#123").substring(8, 24);
		IEmployeeService service = ServiceFactory.create(IEmployeeService.class);
		Employee employee = service.byPhone(entity.getMobile().trim());
		if (employee == null) {

			employee = new Employee();
			employee.toNew();
			employee.setName(entity.getName());
			employee.setMobile(entity.getMobile());
			employee.setLoginName(entity.getLoginName());
			employee.setEmail(entity.getEmail());
			employee.setEntryDate(entity.getEntryDate());
			employee.setQuitDate(entity.getQuitDate());
			employee.setDisabled(entity.getDisabled());
			employee.setPwd(pwd);
		} else {

			employee.setPwd(pwd);
		}

		RoleEmployee roleEmployee = null;
		List<RoleEmployee> reList = new ArrayList<RoleEmployee>();
		List<SalesmanRole> salesmanRoleList = entity.getRoles();
		for (SalesmanRole salesmanRole : salesmanRoleList) {

			roleEmployee = new RoleEmployee();
			roleEmployee.toNew();
			roleEmployee.setRoleId(salesmanRole.getRoleId());
			roleEmployee.setEmployeeId(employee.getId());
			reList.add(roleEmployee);
		}

		employee.setRoles(reList);
		service.save(employee);

		entity.setEmployeeId(employee.getId());
		
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
