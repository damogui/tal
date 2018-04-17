package com.gongsibao.crm.service.action.task.rollback;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw 回退：记录日志
 */
public class ActionRollbackRecordLog implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		
		
		Map<String, Object> getMap = ctx.getStatus();
		
		String content = getMap.get("content").toString();
		Integer currentOwner = Integer.valueOf(ctx.getStatus().get("ownerId").toString());
		SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(currentOwner);
		
		// 1.保存流转日志
		INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
		NCustomerOperationLog changeLog = new NCustomerOperationLog();
		{
			changeLog.toNew();// 标示下类型，有多种
			changeLog.setFormUserId(currentOwner);
			changeLog.setContent(content);
			changeLog.setChangeType(ChangeType.RELEASE);
			changeLog.setTaskId(task.getId());
			changeLog.setFormUserId((Integer) getMap.get("formUserId"));
			changeLog.setFormDepartmentId((Integer) getMap.get("formDepartmentId"));
			changeLog.setFormSupplierId((Integer) getMap.get("formSupplier"));
			changeLog.setSupplierId(organization.getSupplierId());
			changeLog.setDepartmentId(organization.getDepartmentId());
			changeLog.setCustomerId(task.getCustomerId());
			changeService.save(changeLog);
		}		
		
		//2.保存通知日志（通知部门领导和服务商管理员）
		String getContact = NCustomerContact.handleContact(task.getCustomer());
		String copyWriter = String.format("【退回提醒】您好，【%s】退回至公海1个商机，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，退回原因为【%s】，请及时处理",
				organization.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact,content);
		//通知直属领导
		if(organization.getDirectLeaderId() != null){
			notify(task,organization,copyWriter,organization.getDirectLeaderId());
		}
		//通知服务商管理员
		if(organization.getAdminId() != null){
			notify(task,organization,copyWriter,organization.getAdminId());
		}
	}
	/**
	 * 发送通知
	 * @param task 商机实体
	 * @param organization 业务员组织机构
	 * @param copyWriter 通知文案
	 * @param receivedId 接收人
	 */
	private void notify(NCustomerTask task,SalesmanOrganization organization,String copyWriter,Integer receivedId){
		INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);		
		NCustomerTaskNotify notify = new NCustomerTaskNotify();
		{
			notify.toNew();
			notify.setTaskId(task.getId());
			notify.setContent(copyWriter);
			notify.setCustomerId(task.getCustomerId());
			notify.setSupplierId(organization.getSupplierId());
			notify.setDepartmentId(organization.getDepartmentId());
			notify.setReceivedId(receivedId);
			notifyService.save(notify);
		}
	}
}
