package com.gongsibao.supplier.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.SalesmanRole;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;

@Service
public class SalesmanService extends SupplierPersistableService<Salesman> implements ISalesmanService {

	public SalesmanService() {
		super();
		this.type = Salesman.class;
	}

	@Override
	public Integer getSupplierId(Integer employeeId) {

		Salesman entity = byEmployeeId(employeeId);
		if (entity != null) {

			return entity.getSupplierId();
		}
		return null;
	}

	@Override
	public Integer getDepartmentId(Integer employeeId) {

		Salesman entity = byEmployeeId(employeeId);
		if (entity != null) {

			return entity.getDepartmentId();
		}
		return null;
	}

	@Override
	public List<Integer> getDepartmentIdList(Integer employeeId) {

		List<Integer> idList = new ArrayList<Integer>();
		Integer currentDepartmentId = this.getDepartmentId(employeeId);
		if (currentDepartmentId != null) {
			ISupplierDepartmentService departmentService = ServiceFactory.create(ISupplierDepartmentService.class);
			idList = departmentService.getSubDepartmentIdList(currentDepartmentId);
			// 包含当前部门Id
			idList.add(currentDepartmentId);
		}
		return idList;
	}

	@Override
	public Integer getEmployeeId(Integer salesmanId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id,employeeId");
			oql.setFilter("id=?");
			oql.getParameters().add("@id", salesmanId, Types.INTEGER);
		}

		Salesman entity = this.queryFirst(oql);
		if (entity != null) {

			return entity.getEmployeeId();
		}
		return null;
	}

	@Override
	public Salesman byId(Object id) {

		Salesman salesman = super.byId(id);
		Employee employee = salesman.getEmployee();
		if (employee != null) {
			salesman.setName(employee.getName());
			salesman.setMobile(employee.getMobile());
			salesman.setEmail(employee.getEmail());
			salesman.setLoginName(employee.getLoginName());
			salesman.setEntryDate(employee.getEntryDate());
			salesman.setQuitDate(employee.getQuitDate());
			salesman.setDisabled(employee.getDisabled());
		}

		return salesman;
	}

	@Override
	public Salesman byId(Salesman entity) {

		entity = this.byId(entity.getId());
		return entity;
	}

	@Override
	public Salesman byEmployeeId(Integer employeeId) {

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects("Salesman.*,Salesman.supplier.{id,name},Salesman.department.{id,name},Salesman.employee.{id,name}*");
			oql.setFilter("employeeId=?");
			oql.getParameters().add("@employeeId", employeeId, Types.INTEGER);
		}
		Salesman entity = this.queryFirst(oql);
		return entity;
	}

	@Override
	public boolean setDisabled(Integer salesmanId, boolean state) {

		// 停用的同时要停用Employee
		boolean isUpdate = false;
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("disabled", state);
			updateBuilder.where("id=?");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("salesmanId", salesmanId, Types.INTEGER);
		isUpdate = this.pm.executeNonQuery(updateBuilder.toSQL(), qps) > 0;

		// 停用Employee
		Integer employeeId = this.getEmployeeId(salesmanId);
		updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(Employee.class).getTableName());
			updateBuilder.set("disabled", state);
			updateBuilder.where("id=?");
		}

		qps = new QueryParameters();
		qps.add("employeeId", employeeId, Types.INTEGER);
		isUpdate = this.pm.executeNonQuery(updateBuilder.toSQL(), qps) > 0;

		return isUpdate;
	}

	@Override
	public boolean setReceiving(Integer salesmanId, boolean state) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("receiving", state);
			updateBuilder.where("id=?");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("salesmanId", salesmanId, Types.INTEGER);
		return this.pm.executeNonQuery(updateBuilder.toSQL(), qps) > 0;
	}

	@Override
	public Salesman save(Salesman entity) {

		EntityState state = entity.getEntityState();

		if (!state.equals(EntityState.Deleted)) {// 如果非删除的话取出来服务商的自营/平台属性赋值

			// 直接取部门的
			SupplierDepartmentService departmentService = new SupplierDepartmentService();
			SupplierDepartment department = departmentService.byId(entity.getDepartmentId());

			if (department == null) {
				throw new BusinessException("部门属性不正确");
			}
			entity.setType(department.getType());// 设置平台属性
			entity.setCustomerType(department.getCustomerType());// 设置分组属性

			if (state == EntityState.New) {

				this.createEmployee(entity);
			} else if (state == EntityState.Persist) {

				this.updateEmployee(entity);
			}
			entity = super.save(entity);

		} else {// 删除的时候走校验是不是存在任务，存在任务不能删除

			// entity.setEmployeeId(super.byId(entity.getId()).getEmployeeId());//需要用到员工id下面处理
			int taskCount = this.deleteVerify(entity);
			if (taskCount > 0) {// 校验能不能删除

				throw new BusinessException("该员工存在任务,不能删除");

			} else {// 为0的能删除

				entity = super.save(entity);
			}

		}

		return entity;
	}

	// /删除的时候校验员工下面是否存在任务
	private int deleteVerify(Salesman entity) {

		String sqlQ = "SELECT  employee_id  FROM sp_salesman  WHERE  id=?";
		QueryParameters qpsQ = new QueryParameters();
		qpsQ.add("@id", entity.getId(), Types.INTEGER);

		Object employeeIdO = this.pm.executeScalar(sqlQ, qpsQ);
		if (employeeIdO == null) {

			return 0;
		} else {

			String sql = "SELECT  COUNT(1)  FROM n_crm_customer_task  WHERE    owner_id=?";
			QueryParameters qps = new QueryParameters();
			qps.add("@owner_id", Integer.parseInt(employeeIdO.toString()), Types.INTEGER);
			int num = Integer.parseInt(this.pm.executeScalar(sql, qps).toString());
			return num;
		}

	}

	/*
	 * (non-Javadoc)根据服务商id获取员工集合
	 * 
	 * @see
	 * com.gongsibao.supplier.base.ISalesmanService#getBySupplierId(java.lang
	 * .Integer)
	 */
	@Override
	public List<Salesman> getBySupplierId(Integer supplierId) {
		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects("*");
			oql.setFilter("supplierId=? and disabled=0");// 没有停用的
			oql.getParameters().add("@supplierId", supplierId, Types.INTEGER);
		}
		List<Salesman> salesmanList = this.pm.queryList(oql);
		return salesmanList;
	}
	
	@Override
	public List<Salesman> getByDepartmentId(Integer departmentId) {
		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects("*");
			oql.setFilter("departmentId=? and disabled=0");// 没有停用的
			oql.getParameters().add("@departmentId", departmentId, Types.INTEGER);
		}
		List<Salesman> salesmanList = this.pm.queryList(oql);
		return salesmanList;
	}
	/**
	 * @throws
	 * @Title: createEmployee
	 * @Description: TODO(创建Employee)
	 * @param: @param entity
	 * @return: void
	 */
	private void createEmployee(Salesman entity) {

		IEmployeeService service = ServiceFactory.create(IEmployeeService.class);
		
		Employee employee = service.byPhone(entity.getMobile());
		if(employee == null){

			employee = new Employee();
			employee.toNew();
			employee.setName(entity.getName());
			employee.setMobile(entity.getMobile());
			employee.setLoginName(entity.getLoginName());
			employee.setEmail(entity.getEmail());
			employee.setEntryDate(entity.getEntryDate());
			employee.setQuitDate(entity.getQuitDate());
			employee.setDisabled(entity.getDisabled());
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
	}

	/**
	 * @throws
	 * @Title: updateEmployee
	 * @Description: TODO(更新Employee)
	 * @param: @param entity
	 * @return: void
	 */
	private void updateEmployee(Salesman entity) {

		IEmployeeService service = ServiceFactory.create(IEmployeeService.class);
		Employee employee = service.byId(entity.getEmployeeId());
		employee.setName(entity.getName());
		employee.setMobile(entity.getMobile());
		employee.setLoginName(entity.getLoginName());
		employee.setEmail(entity.getEmail());
		employee.setEntryDate(entity.getEntryDate());
		employee.setQuitDate(entity.getQuitDate());
		employee.setDisabled(entity.getDisabled());

		List<RoleEmployee> roleEmployeeList = employee.getRoles();
		for (RoleEmployee roleEmployee : roleEmployeeList) {
			roleEmployee.toDeleted();
		}

		RoleEmployee roleEmployee = null;
		List<SalesmanRole> salesmanRoleList = entity.getRoles();
		for (SalesmanRole salesmanRole : salesmanRoleList) {

			roleEmployee = new RoleEmployee();
			roleEmployee.toNew();
			roleEmployee.setEmployeeId(employee.getId());
			roleEmployee.setRoleId(salesmanRole.getRoleId());
			roleEmployeeList.add(roleEmployee);
		}

		employee.setRoles(roleEmployeeList);
		service.save(employee);
	}

	@Override
	public Boolean hasEmployeeId(Integer employeeId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setFilter("employeeId=?");
			oql.getParameters().add("@employeeId", employeeId, Types.INTEGER);
		}
		return this.queryCount(oql) > 0;
	}

	@Override
	public Salesman getDirectLeader(Integer salesmanId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("is_leader = 1  AND disabled = 0 AND department_id IN ( SELECT department_id FROM sp_salesman WHERE id = ?)");
			oql.getParameters().add("@id", salesmanId, Types.INTEGER);
		}

		return this.queryFirst(oql);
	}

	@Override
	public Salesman getSuperiorLeader(Integer salesmanId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("is_leader = 1  AND disabled = 0 AND department_id IN (SELECT parent_id FROM sp_department WHERE id IN (SELECT department_id FROM sp_salesman WHERE id = ?))");
			oql.getParameters().add("@id", salesmanId, Types.INTEGER);
		}

		return this.queryFirst(oql);
	}

	@Override
	public Integer getLeaderId(Integer supplierId, Integer departmentId) {
		Salesman manEntity = new Salesman();
		Integer leaderId = null;
		if(supplierId != null && departmentId == null){
			//服务商管理员
			Oql oql = new Oql();
			{
				oql.setType(this.type);
				oql.setSelects("*,supplier.*");
				oql.setFilter(" disabled =0 and supplier_id = ?");
				oql.getParameters().add("@supplier_id", supplierId, Types.INTEGER);
				manEntity = this.queryFirst(oql);
			}
			if(manEntity != null){
				leaderId = manEntity.getSupplier().getAdminId();
			}
		}else if(departmentId != null){
			//部门领导
			Oql oql = new Oql();
			{
				oql.setType(this.type);
				oql.setSelects("*");
				oql.setFilter("is_leader = 1 and disabled =0 and department_id = ?");
				oql.getParameters().add("@department_id", departmentId, Types.INTEGER);
				manEntity = this.queryFirst(oql);
			}
			if(manEntity != null){
				leaderId = manEntity.getEmployeeId();
			}
		}
		return leaderId;
	}

	
}