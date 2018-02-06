package com.gongsibao.crm.service.action.task.rollback;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw 回退：记录日志
 */
public class ActionRollbackRecordLog implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		String content = ctx.getStatus().get("content").toString();

		// 保存流转日志
		INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
		NCustomerOperationLog changeLog = new NCustomerOperationLog();
		{

			changeLog.toNew();// 标示下类型，有多种
			changeLog.setFormUserId(task.getOwnerId());
			changeLog.setContent(content);
			changeLog.setChangeType(ChangeType.RELEASE);
			changeLog.setTaskId(task.getId());
			changeLog.setSupplierId(task.getSupplierId());
			changeLog.setDepartmentId(task.getDepartmentId());
			changeLog.setCustomerId(task.getCustomerId());
			changeService.save(changeLog);
		}

		// 2.保存通知日志（通知级别：部门级别、平台级别）
		String copyWriter;
		if(task.getOwnerId() !=null){
			SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
			ISupplierDepartmentService departmentService = ServiceFactory.create(ISupplierDepartmentService.class);
			Integer currentDepartmentSupId = departmentService.getSupDepartmentId(organization.getDepartmentId());
			//当前部门的上级为空，进入平台公海。否则进入上级部门公海
			if(currentDepartmentSupId == null){
				copyWriter = String.format("【退回提醒】您好，【当前登录人】退回至公海1个任务，任务名称【任务名称】，客户名称【客户名称】，客户联系方式【优先取客户手机号，星号隐藏中间四位，如无手机号，按照座机号、微信号、QQ号、其他联系方式的顺序取值，显示前两位及最后两位，中间用星号代替】，退回原因为【】，请及时处理");
			}else {
				copyWriter ="部门公海";
			}
		}
		
		INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
		NCustomerTaskNotify notify = new NCustomerTaskNotify();
		{
			content = String.format("任务ID：%s,被业务员回退，请悉知。", task.getId());
			notify.toNew();
			notify.setTaskId(task.getId());
			notify.setContent(content);
			notify.setType(NotifyType.WEIXIN);
			notify.setCustomerId(task.getCustomerId());
			notify.setSupplierId(task.getSupplierId());
			notify.setDepartmentId(task.getDepartmentId());
			notify.setReceivedId(task.getOwnerId());
			notifyService.save(notify);
		}
	}
}
