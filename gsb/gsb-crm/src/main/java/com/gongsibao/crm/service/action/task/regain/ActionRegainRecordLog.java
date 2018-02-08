package com.gongsibao.crm.service.action.task.regain;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw 收回：记录日志
 */
public class ActionRegainRecordLog implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		String content = ctx.getStatus().get("content").toString();
		Integer currentOwner = Integer.valueOf(ctx.getStatus().get("ownerId").toString());
		SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(currentOwner);

		//保存通知日志
		String getContact = NCustomerContact.handleContact(task.getCustomer());
		String copyWriter = String.format("【收回提醒】您好，【%s】收回【%s】1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，收回原因为【%s】，请知悉",
				organization.getEmployeeName(),organization.getSalessmanName(),task.getName(),task.getCustomer().getRealName(),getContact,content);
		
		//通知业务员
		notify(task,copyWriter,currentOwner);
		//通知直属领导
		if(organization.getDirectLeaderId() != null){
			notify(task,copyWriter,organization.getDirectLeaderId());
		}
		//通知服务商管理员
		if(organization.getAdminId() != null){
			notify(task,copyWriter,organization.getAdminId());
		}
	}
	/**
	 * 发送通知
	 * @param task 任务实体
	 * @param copyWriter 通知文案
	 * @param receivedId 接收人
	 */
	private void notify(NCustomerTask task,String copyWriter,Integer receivedId){
		INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);		
		NCustomerTaskNotify notify = new NCustomerTaskNotify();
		{
			notify.toNew();
			notify.setTaskId(task.getId());
			notify.setContent(copyWriter);
			notify.setType(NotifyType.WEIXIN);
			notify.setCustomerId(task.getCustomerId());
			notify.setSupplierId(task.getSupplierId());
			notify.setDepartmentId(task.getDepartmentId());
			notify.setReceivedId(receivedId);
			notifyService.save(notify);
		}
	}
}
