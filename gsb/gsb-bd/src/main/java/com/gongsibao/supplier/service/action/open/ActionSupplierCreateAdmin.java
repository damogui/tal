package com.gongsibao.supplier.service.action.open;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IRoleEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.supplier.DepartmentProduct;
import com.gongsibao.entity.supplier.FunctionModuleRole;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.supplier.SupplierFunctionModule;
import com.gongsibao.entity.supplier.SupplierProduct;
import com.gongsibao.entity.supplier.dict.SupplierStatus;
import com.gongsibao.supplier.base.IFunctionModuleRoleService;
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

	private void createSalesman(SupplierDepartment department, Employee employee) {

		Salesman salesman = new Salesman();
		{
			salesman.toNew();
			salesman.setName(employee.getName());
			salesman.setMobile(employee.getMobile());
			salesman.setEmail(employee.getEmail());
			salesman.setLoginName(employee.getLoginName());
			salesman.setEntryDate(employee.getEntryDate());
			salesman.setQuitDate(employee.getQuitDate());
			salesman.setDepartmentId(department.getId());
			salesman.setSupplierId(department.getSupplierId());
			salesman.setEmployeeId(employee.getId());
		}

		IPersister<Salesman> pm = PersisterFactory.create();// 因为重写了，所以调用父类
		pm.save(salesman);
	}

	private Employee createEmployee(Supplier supplier) {

		String mobile = supplier.getMobilePhone();
		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		Employee employee = employeeService.byPhone(mobile);

		List<RoleEmployee> roles = new ArrayList<RoleEmployee>();
		List<FunctionModuleRole> fmrList = getAdminRole(supplier);
		for (FunctionModuleRole fmr : fmrList) {

			RoleEmployee reEmployee = new RoleEmployee();
			{
				reEmployee.toNew();
				reEmployee.setRoleId(fmr.getRoleId());
				roles.add(reEmployee);
			}
		}

		if (employee == null) {

			employee = new Employee();
			{
				employee.toNew();
				employee.setName(supplier.getContact());
				employee.setMobile(supplier.getMobilePhone());
				employee.setLoginName(supplier.getMobilePhone());
			}
			employee.setRoles(roles);
		} else {

			employee.setDisabled(false);

			// 如果没有管理员角色，则添加
			boolean isHasAdminRole = this.hasAdminRole(employee.getId());
			if (!isHasAdminRole) {

				employee.setRoles(roles);
				//reEmployee.setEmployeeId(employee.getId());
			}
		}

		employee = employeeService.save(employee);
		return employee;
	}

	private boolean hasAdminRole(Integer employeeId) {

		IRoleEmployeeService roleEmployeeService = ServiceFactory.create(IRoleEmployeeService.class);
		Oql oql = new Oql();
		{
			oql.setType(RoleEmployee.class);
			oql.setFilter("employeeId=? and role.code='Supplier_Admin'");
			oql.getParameters().add("employeeId", employeeId, Types.INTEGER);
		}
		return roleEmployeeService.queryCount(oql) > 0;
	}

	private SupplierDepartment createDepartment(Supplier supplier) {

		SupplierDepartment department = new SupplierDepartment();
		{
			department.toNew();
			department.setName(supplier.getName());
			department.setSupplierId(supplier.getId());
			department.setType(supplier.getType());

			// 带入服务范围
			DepartmentProduct departmentProduct = null;

			List<DepartmentProduct> departmentProductList = new ArrayList<DepartmentProduct>();
			List<SupplierProduct> supplierProductList = supplier.getServiceProducts();
			for (SupplierProduct supplierProduct : supplierProductList) {

				departmentProduct = new DepartmentProduct();
				{
					departmentProduct.toNew();
					departmentProduct.setProductCategoryId1(supplierProduct.getProductCategoryId1());
					departmentProduct.setProductCategoryId2(supplierProduct.getProductCategoryId2());
					departmentProduct.setProductId(supplierProduct.getProductId());
					departmentProduct.setProvinceId(supplierProduct.getnProvinceId());
					departmentProduct.setCityId(supplierProduct.getnCityId());
					departmentProduct.setCountyId(supplierProduct.getnCountyId());
					departmentProductList.add(departmentProduct);
				}
			}

			department.setServiceProducts(departmentProductList);

		}
		ISupplierDepartmentService departmentService = ServiceFactory.create(ISupplierDepartmentService.class);
		department = departmentService.save(department);
		return department;
	}

	private List<FunctionModuleRole> getAdminRole(Supplier supplier) {

		List<Integer> moduleIdList = new ArrayList<Integer>();
		List<SupplierFunctionModule> moduleList = supplier.getModules();
		for (SupplierFunctionModule sfm : moduleList) {

			moduleIdList.add(sfm.getFunctionModuleId());
		}

		IFunctionModuleRoleService fmrService = ServiceFactory.create(IFunctionModuleRoleService.class);
		List<FunctionModuleRole> fmrList = fmrService.queryList(moduleIdList);
		return fmrList;
	}
}
