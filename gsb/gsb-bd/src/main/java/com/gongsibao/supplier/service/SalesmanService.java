package com.gongsibao.supplier.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.EntityState;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.entity.Employee;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.supplier.base.ISupplierService;

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
	public boolean getReceiving(Integer employeeId) {

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects("{id,receiving}");
			oql.setFilter("employeeId=?");
			oql.getParameters().add("@employeeId", employeeId, Types.INTEGER);
		}
		Salesman entity = this.queryFirst(oql);
		if (entity == null) {

			return false;
		}
		return entity.getReceiving();
	}

	@Override
	public Salesman save(Salesman entity) {

		EntityState state = entity.getEntityState();
		if (state == EntityState.New) {

			return this.add(entity);

		} else if (state == EntityState.Persist) {

			return this.update(entity);

		} else if (state == EntityState.Deleted) {

			return this.delete(entity);
		}
		
		return entity;
	}

	/**
	 * @Title: add
	 * @Description: TODO(创建业务员)
	 * @param: @param entity
	 * @param: @return
	 * @return: Salesman
	 * @throws
	 */
	private Salesman add(Salesman entity) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/supplier/salesman/add");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);

		entity = (Salesman) ctx.getItem();

		return entity;
	}

	/**
	 * @Title: update
	 * @Description: TODO(更新一个业务员)
	 * @param: @param entity
	 * @param: @return
	 * @return: Salesman
	 * @throws
	 */
	private Salesman update(Salesman entity) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/supplier/salesman/update");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);

		entity = (Salesman) ctx.getItem();

		return entity;
	}

	/**
	 * @Title: delete
	 * @Description: TODO(删除一个业务员)
	 * @param: @param entity
	 * @param: @return
	 * @return: Salesman
	 * @throws
	 */
	private Salesman delete(Salesman entity) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/supplier/salesman/delete");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);

		entity = (Salesman) ctx.getItem();

		return entity;
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
		if (supplierId != null && departmentId == null) {
			// 服务商管理员
			Oql oql = new Oql();
			{
				oql.setType(this.type);
				oql.setSelects("*,supplier.*");
				oql.setFilter(" disabled =0 and supplier_id = ?");
				oql.getParameters().add("@supplier_id", supplierId, Types.INTEGER);
				manEntity = this.queryFirst(oql);
			}
			if (manEntity != null) {
				leaderId = manEntity.getSupplier().getAdminId();
			}
		} else if (departmentId != null) {
			// 部门领导
			Oql oql = new Oql();
			{
				oql.setType(this.type);
				oql.setSelects("*");
				oql.setFilter("is_leader = 1 and disabled =0 and department_id = ?");
				oql.getParameters().add("@department_id", departmentId, Types.INTEGER);
				manEntity = this.queryFirst(oql);
			}
			if (manEntity != null) {
				leaderId = manEntity.getEmployeeId();
			}
		}
		return leaderId;
	}

	// 根据角色Code获取该角色下所有的employeeId集合
	@Override
	public List<Integer> getEmployeeIdListByRoleCodes(List<String> roleCodes) {

		List<Integer> employeeIdList = new ArrayList<Integer>();
		StringBuffer sql = new StringBuffer();

		if (CollectionUtils.isEmpty(roleCodes)) {
			return new ArrayList<Integer>();
		}

		String roleCodeStrs = new String();

		for (String roleCode : roleCodes) {
			roleCodeStrs += "'" + roleCode + "',";
		}

		roleCodeStrs = roleCodeStrs.substring(0, roleCodeStrs.length() - 1);
		sql.append("SELECT employee_id FROM sys_permission_role_employee ");
		sql.append("WHERE employee_id in(SELECT id FROM sys_permission_employee WHERE disabled = 0) ");
		sql.append("AND role_id IN (SELECT id FROM sys_permission_role WHERE CODE in(" + roleCodeStrs + ") )");
		DataTable dataTable = this.pm.executeTable(sql.toString(), null);

		for (IRow row : dataTable) {
			Integer employeeId = row.getInteger("employee_id");
			if (!employeeIdList.contains(employeeId)) {
				employeeIdList.add(employeeId);
			}
		}

		return employeeIdList;
	}

	@Override
	public NotifyType getNotifyType(Integer employeeId) {

		Integer supplierId = getSupplierId(employeeId);
		ISupplierService ISupplierService = ServiceFactory.create(ISupplierService.class);
		return ISupplierService.getNotifyType(supplierId);
	}

}