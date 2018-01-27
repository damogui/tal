package com.gongsibao.crm.service.action.autoAllot;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationDispositon;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;

/**
 * @author zhangchao 分配逻辑
 */
public class ActionCustomerTaskAllot implements IAction {

	// 任务服务对象
	INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);
	ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask entity = (NCustomerTask) ctx.getItem();

		Integer ownerId = 0;

		List<String> salesmanSqlWhereList = new ArrayList<String>();
		salesmanSqlWhereList.add(" disabled=0 ");// 没有停用的
		// 当有跟进【服务商时】，将分配组织机构范围缩小至跟进部门
		if (!entity.getSupplierId().equals(0)) {
			salesmanSqlWhereList.add(" supplier_id=" + entity.getSupplierId() + " ");
		}
		// 新客户时(【新客户】或【全部】标签的组织机构)
		if (entity.getTaskType().equals(TaskCustomerType.NEW)) {
			salesmanSqlWhereList.add(" (customer_type=" + TaskCustomerType.NEW.getValue() + " or customer_type=" + TaskCustomerType.All.getValue() + ") ");
		}
		// 老客户时(【老客户】或【全部】标签的组织机构)
		if (entity.getTaskType().equals(TaskCustomerType.OLD)) {
			salesmanSqlWhereList.add(" (customer_type=" + TaskCustomerType.OLD.getValue() + " or customer_type=" + TaskCustomerType.All.getValue() + ") ");
		}
		// 平台：自营时
		if (entity.getAllocationDispositon().equals(AllocationDispositon.DIRECT)) {
			salesmanSqlWhereList.add(" (allocation_dispositon=" + AllocationDispositon.DIRECT.getValue() + " or allocation_dispositon=" + AllocationDispositon.UNLIMITED.getValue() + ") ");
		}
		// 平台：平台时
		if (entity.getAllocationDispositon().equals(AllocationDispositon.PLATFORM)) {
			salesmanSqlWhereList.add(" (allocation_dispositon=" + AllocationDispositon.PLATFORM.getValue() + " or allocation_dispositon=" + AllocationDispositon.UNLIMITED.getValue() + ") ");
		}
		

		// 查询业务员的条件的字符串
		String whereString = StringManager.join(" and ", salesmanSqlWhereList);
		
		Oql salesmanOql = new Oql();
		{
			salesmanOql.setType(Salesman.class);
			salesmanOql.setSelects("*");
			salesmanOql.setFilter(whereString);
		}
		
		//分配方式:半自动分配时（分配到跟进服务商即可）
		if(entity.getAllocationType().equals(NAllocationType.SemiAutomatic)){
		
			updateTaskSupplierId(entity.getId(),1);
		}
		
		//分配方式:自动分配时
		if(entity.getAllocationType().equals(NAllocationType.AUTO)){
			List<Salesman> salesmanList = salesmanService.queryList(salesmanOql);
			ownerId = salesmanList.get(0).getEmployeeId();
			// 跟新业务员
			updateTaskOwnerId(entity.getId(), ownerId);
		}
		
	}

	private void updateTaskOwnerId(Integer taskId, Integer ownerId) {
		// 跟新业务员
		IPersister<NCustomerTask> taskPm = PersisterFactory.create();

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("owner_id", ownerId);
			updateSql.where("id=?");
		}
		String cmdText = updateSql.toSQL();

		QueryParameters qps = new QueryParameters();
		qps.add("id", taskId, Types.INTEGER);

		taskPm.executeNonQuery(cmdText, qps);
	}
	
	private void updateTaskSupplierId(Integer taskId, Integer supplierId) {
		// 跟新业务员
		IPersister<NCustomerTask> taskPm = PersisterFactory.create();

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("owner_id", supplierId);
			updateSql.where("id=?");
		}
		String cmdText = updateSql.toSQL();

		QueryParameters qps = new QueryParameters();
		qps.add("id", taskId, Types.INTEGER);

		taskPm.executeNonQuery(cmdText, qps);
	}
}
