package com.gongsibao.supplier.service.action.open;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IRoleService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Role;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.supplier.dict.SupplierStatus;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.supplier.service.SupplierService;

/**
 * @author hw 创建服务商管理员
 */
public class ActionSupplierCreateAdmin implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Supplier entity = (Supplier) ctx.getItem();

		// 如果已经开过户，直接设置状态,只启用管理员帐号
		if (entity.getAdminId() != null && entity.getAdminId() != 0) {

			this.enableEmployee(entity.getAdminId());
			entity.setStatus(SupplierStatus.OPEND);
			entity = getService().save(entity);

		} else {

			// 1.创建服务商部门：SupplierDepartment
			SupplierDepartment department = this.createDepartment(entity);

			// 2.创建平台帐号
			Employee employee = this.createEmployee(entity);

			// 3.创建Salesman，并关联部门
			this.createSalesman(department, employee);

			// 4.设置服务商的AdminId
			entity = persist(entity, employee);
		}

		ctx.setItem(entity);
	}

	private void enableEmployee(Integer employeeId) {

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("sys_permission_employee");
			updateSql.set("disabled", false);
			updateSql.where("id =" + employeeId);
		}
		String cmdText = updateSql.toSQL();

		IPersister<Salesman> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}

	private IPersistableService<Supplier> getService() {

		Class<?> superType = SupplierService.class.getSuperclass();
		@SuppressWarnings("unchecked")
		IPersistableService<Supplier> service = (IPersistableService<Supplier>) ReflectManager.newInstance(superType);
		return service;
	}

	private Supplier persist(Supplier entity, Employee employee) {

		entity.setStatus(SupplierStatus.OPEND);
		entity.setAdminId(employee.getId());
		entity = getService().save(entity);
		return entity;
	}

	private Salesman createSalesman(SupplierDepartment department, Employee employee) {

		Salesman salesman = new Salesman();
		{
			salesman.toNew();
			salesman.setDepartmentId(department.getId());
			salesman.setSupplierId(department.getSupplierId());
			salesman.setEmployeeId(employee.getId());
		}
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		salesmanService.save(salesman);
		return salesman;
	}

	private Employee createEmployee(Supplier supplier) {

		String mobile = supplier.getMobilePhone();
		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		Employee employee = employeeService.byPhone(mobile);

		if (employee == null) {

			employee = new Employee();
			{
				employee.toNew();
				employee.setName(supplier.getContact());
				employee.setMobile(supplier.getMobilePhone());
				employee.setLoginName(supplier.getMobilePhone());
			}
		} else {

			employee.setDisabled(false);
		}

		Role role = getAdminRole();
		List<RoleEmployee> roles = new ArrayList<RoleEmployee>();
		RoleEmployee reEmployee = new RoleEmployee();
		{
			reEmployee.toNew();
			reEmployee.setRoleId(role.getId());
			roles.add(reEmployee);
		}
		employee.setRoles(roles);
		employee = employeeService.save(employee);
		return employee;
	}

	private SupplierDepartment createDepartment(Supplier supplier) {

		SupplierDepartment department = new SupplierDepartment();
		{
			department.toNew();
			department.setName(supplier.getName());
			department.setSupplierId(supplier.getId());
		}
		ISupplierDepartmentService departmentService = ServiceFactory.create(ISupplierDepartmentService.class);
		department = departmentService.save(department);
		return department;
	}

	private Role getAdminRole() {

		IRoleService roleService = ServiceFactory.create(IRoleService.class);
		Oql oql = new Oql();
		{
			oql.setType(Role.class);
			oql.setSelects("id,name");
			oql.setFilter("code='Supplier_Admin'");
		}

		Role role = roleService.queryFirst(oql);
		if (role == null) {

			throw new BusinessException("请设置编码为'Supplier_Admin'的角色！");
		}
		return role;
	}
}
