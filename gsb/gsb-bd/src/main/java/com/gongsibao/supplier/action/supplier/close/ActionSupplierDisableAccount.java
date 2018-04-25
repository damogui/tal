package com.gongsibao.supplier.action.supplier.close;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.dict.SupplierStatus;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.service.SupplierService;

/**
 * @author hw 停用服务相关帐号
 */
public class ActionSupplierDisableAccount implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Supplier entity = (Supplier) ctx.getItem();
		List<Salesman> salesmanList = this.getSupplierSalesmanList(entity.getId());
		List<Integer> ss = new ArrayList<Integer>();
		for (Salesman salesman : salesmanList) {

			ss.add(salesman.getEmployeeId());
		}
		this.disableEmployee(ss);
		
		entity.setStatus(SupplierStatus.CLOSED);
		entity = getService().save(entity);
	}
	
	private IPersistableService<Supplier> getService(){

		Class<?> superType = SupplierService.class.getSuperclass();
		@SuppressWarnings("unchecked")
		IPersistableService<Supplier> service = (IPersistableService<Supplier>) ReflectManager.newInstance(superType);
		return service;
	}

	private void disableEmployee(List<Integer> idList) {

		String ids = StringManager.join(",", idList);
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("sys_permission_employee");
			updateSql.set("disabled", true);
			updateSql.where("id in (" + ids + ")");
		}
		String cmdText = updateSql.toSQL();
		
		IPersister<Salesman> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}

	private List<Salesman> getSupplierSalesmanList(Integer supplierId) {

		Oql oql = new Oql();
		{
			oql.setType(Salesman.class);
			oql.setSelects("id,employeeId");
			oql.setFilter("supplierId=" + supplierId);
		}
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		List<Salesman> salesmanList = salesmanService.queryList(oql);
		return salesmanList;
	}
}
