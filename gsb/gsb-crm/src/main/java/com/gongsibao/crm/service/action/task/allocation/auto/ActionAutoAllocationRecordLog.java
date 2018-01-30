package com.gongsibao.crm.service.action.task.allocation.auto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.supplier.Salesman;

/**
 * @author zhangchao 写入跟进记录
 */
public class ActionAutoAllocationRecordLog implements IAction {
	// 任务
	INCustomerTaskService nCustomerTaskService = ServiceFactory.create(INCustomerTaskService.class);
	// 流转日志
	INCustomerChangeService changeService = ServiceFactory.create(INCustomerChangeService.class);
	// 通知日志
	INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask entity = (NCustomerTask) ctx.getItem();
		Integer FormUserId = entity.getOwnerId() == null ? 0 : entity.getOwnerId();
		// 获取最新的任务
		entity = nCustomerTaskService.byId(entity.getId());
		// 添加日志
		addRecord(entity, FormUserId);
	}

	// 添加日志
	private void addRecord(NCustomerTask entity, Integer FormUserId) {
		// 当分配方式是：【手动分配】-提醒售前客服负责人进行手动分配
		if (entity.getAllocationType().equals(NAllocationType.AUTO) && entity.getOwnerId() != null && !entity.getOwnerId().equals(0)) {
			// 1.保存流转日志
			NCustomerChange changeEntity = new NCustomerChange();
			changeEntity.toNew();// 标示下类型，有多种
			changeEntity.setFormUserId(FormUserId);
			changeEntity.setToUserId(entity.getOwnerId());
			changeEntity.setChangeType(ChangeType.ALLOCATION);
			changeEntity.setTaskId(entity.getId());
			changeEntity.setSupplierId(entity.getSupplierId());
			changeEntity.setDepartmentId(entity.getDepartmentId());
			changeEntity.setCustomerId(entity.getCustomerId());
			// :TODO:等产品给文案模板
			changeEntity.setContent("【流转日志】任务分配给：【" + entity.getOwner().getName() + "】");
			changeService.save(changeEntity);
		}
		
		Integer receiveUserId = entity.getOwnerId();
		// 当是分配方式是【手动】时，则通知售前负责人
		if (entity.getAllocationType().equals(NAllocationType.MANUAL)) {
			// 售前负责人
			Salesman preSeller = getPreSeller();
			receiveUserId = preSeller.getEmployeeId();
		}
		// 当是分配方式是【半自动】时，则通知售前负责人该部门负责人
		if (entity.getAllocationType().equals(NAllocationType.SemiAutomatic)) {
			if (entity.getSupplierId() != null && !entity.getSupplierId().equals(0)) {
				// 当部门不为空时
				Integer departmentId = entity.getDepartmentId() == null ? 0 : entity.getDepartmentId();
				List<Salesman> tempList = getListBydepartmentId(entity.getSupplierId(), departmentId);
				if (CollectionUtils.isNotEmpty(tempList)) {
					receiveUserId = tempList.get(0).getEmployeeId();
				}
			}
		}

		// 2.保存通知日志
		NCustomerTaskNotify notifyEntity = new NCustomerTaskNotify();
		notifyEntity.toNew();
		notifyEntity.setCustomerId(entity.getCustomerId());
		notifyEntity.setTaskId(entity.getId());
		notifyEntity.setType(NotifyType.SYSTEM);
		notifyEntity.setSupplierId(entity.getSupplierId());
		notifyEntity.setDepartmentId(entity.getDepartmentId());
		notifyEntity.setReceivedId(receiveUserId);
		// :TODO:等产品给文案模板
		notifyEntity.setContent("【通知日志】任务分配给：【" + entity.getOwner().getName() + "】");
		notifyService.save(notifyEntity);
	}

	// 获取售前负责人
	private Salesman getPreSeller() {
		Oql oql = new Oql();
		{
			oql.setType(Salesman.class);
			oql.setSelects("*");
			oql.setFilter("name='刘立丹'");
		}
		IPersister<Salesman> salesrPm = PersisterFactory.create();
		return salesrPm.queryFirst(oql);
	}

	//根据服务商id和部门id获取业务员
	private List<Salesman> getListBydepartmentId(Integer supplierId, Integer departmentId) {

		List<String> whereStrList = new ArrayList<String>();
		
		whereStrList.add(" is_leader = 1 ");//是否主管：是
		
		if (supplierId != null && !supplierId.equals(0)) {
			whereStrList.add(" supplier_id = " + supplierId + " ");
		}
		if (departmentId != null && !departmentId.equals(0)) {
			whereStrList.add(" department_id = " + departmentId + " ");
		}
		String whereString = StringManager.join(" and ", whereStrList);
		Oql oql = new Oql();
		{
			oql.setType(Salesman.class);
			oql.setSelects("*");
			oql.setFilter(whereString);
		}
		IPersister<Salesman> salesrPm = PersisterFactory.create();
		return salesrPm.queryList(oql);
	}

}
