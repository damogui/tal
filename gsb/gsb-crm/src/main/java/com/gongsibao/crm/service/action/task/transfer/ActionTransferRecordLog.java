package com.gongsibao.crm.service.action.task.transfer;

import java.util.Map;

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
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw 转移：记录日志
 */
public class ActionTransferRecordLog implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		Map<String, Object> getMap = ctx.getStatus();
		// 1.保存流转日志
		INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
		NCustomerOperationLog changeLog = new NCustomerOperationLog();
		{

			changeLog.toNew();// 标示下类型，有多种
			changeLog.setTaskId(task.getId());
			changeLog.setChangeType(ChangeType.TRANSFER);
			changeLog.setCustomerId(task.getCustomerId());
			changeLog.setFormUserId((Integer) getMap.get("formUserId"));
			changeLog.setToUserId(task.getOwnerId());
			changeLog.setSupplierId(task.getSupplierId());
			changeLog.setDepartmentId(task.getDepartmentId());
			changeService.save(changeLog);
		}

		// 2.保存通知日志（通知接收人）
		
		if((boolean)ctx.getStatus().get("sameDepartment")){
			sameDepartment(task);
		}else {
			
		}
	}
	
	/**
	 * 部门内部转移
	 * @param task
	 */
	private void sameDepartment(NCustomerTask task){
		//转移的人员不能为空
		if(task.getOwnerId() != null){
			ActionContext ctx = new ActionContext();
			//业务员转移还是公海转移
			if(ctx.getStatus().get("formUserId") == null){
				sameDepartmentHighSeas(task,11111111); 
			}else{
				sameDepartmentSalesman(task,(Integer)ctx.getStatus().get("formUserId"));
			}
		}
	}
	/**
	 * 部门内部转移-业务员转移
	 * @param task
	 * @param formUserId
	 */
	private void sameDepartmentSalesman(NCustomerTask task,Integer formUserId){
		//1.业务员的转移
		SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(formUserId);
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
		String getContact = NCustomerContact.handleContact(task.getCustomer());
		String copyWriterForm = String.format("【转移提醒】您好，【%s】把您的1个任务转移给【%s】，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
				SessionManager.getUserId(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		
		String copyWriterTo = String.format("【转移提醒】您好，【%s】从【%s】转移给您1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
				SessionManager.getUserId(),orgaForm.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		sendNotify(task,copyWriterForm,formUserId);
		sendNotify(task,copyWriterTo,task.getOwnerId());
		//2.业务员的一、二级领导
		String leaderCopyWriter = String.format("【转移提醒】您好，【%s】从【%s】转移给【%s】1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
				SessionManager.getUserId(),orgaForm.getEmployeeName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		
		if(orgaForm.getDirectLeaderId() != null){
			sendNotify(task,leaderCopyWriter,orgaForm.getDirectLeaderId());
		}
		if(orgaForm.getSuperiorLeaderId() != null){
			sendNotify(task,leaderCopyWriter,orgaForm.getSuperiorLeaderId());
		}	
		if(orgaTo.getDirectLeaderId() != null){
			sendNotify(task,leaderCopyWriter,orgaTo.getDirectLeaderId());
		}
		if(orgaTo.getSuperiorLeaderId() != null){
			sendNotify(task,leaderCopyWriter,orgaTo.getSuperiorLeaderId());
		}	
	}
	/**
	 * 部门内部转移-公海转移
	 * @param task
	 * @param formUserId
	 */
	private void sameDepartmentHighSeas(NCustomerTask task,Integer formUserId){
		//1.业务员的转移
		SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(formUserId);
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
		String getContact = NCustomerContact.handleContact(task.getCustomer());
		String copyWriterForm = String.format("【转移提醒】您好，【%s】把您的1个任务转移给【%s】，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
				SessionManager.getUserId(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		
		String copyWriterTo = String.format("【转移提醒】您好，【%s】从【%s】公海转移给您1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
				SessionManager.getUserId(),orgaForm.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		sendNotify(task,copyWriterForm,formUserId);
		sendNotify(task,copyWriterTo,task.getOwnerId());
		//2.业务员的一、二级领导
		String leaderCopyWriter = String.format("【转移提醒】您好，【%s】从【%s】转移给【%s】1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
				SessionManager.getUserId(),orgaForm.getEmployeeName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		
		if(orgaForm.getDirectLeaderId() != null){
			sendNotify(task,leaderCopyWriter,orgaForm.getDirectLeaderId());
		}
		if(orgaForm.getSuperiorLeaderId() != null){
			sendNotify(task,leaderCopyWriter,orgaForm.getSuperiorLeaderId());
		}	
		if(orgaTo.getDirectLeaderId() != null){
			sendNotify(task,leaderCopyWriter,orgaTo.getDirectLeaderId());
		}
		if(orgaTo.getSuperiorLeaderId() != null){
			sendNotify(task,leaderCopyWriter,orgaTo.getSuperiorLeaderId());
		}	
	}
	/**
	 * 添加通知
	 * @param task
	 */
	private void sendNotify(NCustomerTask task,String copyWriter,Integer receivedId){
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
