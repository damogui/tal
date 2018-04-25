package com.gongsibao.supplier.action.salesman.update;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.service.SupplierDepartmentService;

public class ActionUpdateSalesmanVerify implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		Salesman entity = (Salesman) ctx.getItem();

		// 直接取部门的
		SupplierDepartmentService departmentService = new SupplierDepartmentService();
		SupplierDepartment department = departmentService.byId(entity.getDepartmentId());
		if (department == null) {
			throw new BusinessException("部门属性不正确");
		}

		Integer supplierId = department.getSupplierId();
		boolean isHas = hasMobile(supplierId, entity.getMobile(),entity.getId());
		if (isHas) {

			throw new BusinessException(String.format("服务商下面已存在手机号:%s的业务员", entity.getMobile()));
		}
		

		entity.setType(department.getType());// 设置平台属性
		entity.setCustomerType(department.getCustomerType());// 设置分组属性
		entity.setSupplierId(department.getSupplierId());
	}


	private boolean hasMobile(Integer supplierId, String mobile,Integer salesmanId) {

		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("id,name,loginName");
			oql.setFilter("login_name =?");
			oql.getParameters().add("@login_name", mobile, Types.VARCHAR);
		}
		Employee employee = employeeService.queryFirst(oql);
		if (employee == null) {

			return false;

		} else {

			String sql = "SELECT COUNT(1) FROM  sp_salesman WHERE supplier_id=? AND employee_id=? and id<> ?";
			QueryParameters qps = new QueryParameters();
			{
				qps.add("@supplier_id", supplierId, Types.INTEGER);
				qps.add("@employee_id", employee.getId(), Types.INTEGER);
				qps.add("@id", salesmanId, Types.INTEGER);
			}
			IPersister<Salesman> pm = PersisterFactory.create();
			return pm.executeInt(sql, qps) > 0;
		}
	}
}
