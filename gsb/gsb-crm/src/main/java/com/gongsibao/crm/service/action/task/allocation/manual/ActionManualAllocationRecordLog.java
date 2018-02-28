package com.gongsibao.crm.service.action.task.allocation.manual;

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
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw 分配：保存日志
 */
public class ActionManualAllocationRecordLog implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();
		Map<String, Object> getMap = ctx.getStatus();

		// 1.保存流转日志
		INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
		
		NCustomerOperationLog changeLog = new NCustomerOperationLog();
		{
			changeLog.toNew();
			changeLog.setTaskId(task.getId());
			changeLog.setChangeType(ChangeType.ALLOCATION);
			changeLog.setCustomerId(task.getCustomerId());
			changeLog.setFormDepartmentId((Integer) getMap.get("formDepartmentId"));
			changeLog.setFormSupplierId((Integer) getMap.get("formSupplier"));
			changeLog.setToUserId(task.getOwnerId());
			changeLog.setToDepartmentId(task.getDepartmentId());
			changeLog.setToSupplierId(task.getSupplierId());
			changeLog.setSupplierId(task.getSupplierId());
			changeLog.setDepartmentId(task.getDepartmentId());
			changeService.save(changeLog);
		}

		// 2.通知日志
		int alloCount = Integer.valueOf(getMap.get("alloCount").toString());
		if(alloCount >1){
			if(!(boolean)getMap.get("isNotify")){
				batchAllocation(task,alloCount);
			}
		}else{
			
			allocation(task);
		}
	}
	/**
	 * 单任务分配
	 * @param task
	 */
	private void allocation(NCustomerTask task){
		String getContact = NCustomerContact.handleContact(task.getCustomer());
		String copyWriter = null;
		
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		//业务员为空，通知服务商管理员或部门主管
		if(task.getOwnerId() == null){
			String.format("【分配提醒】您好，1个新任务待您分配，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时分配跟进",
					task.getName(),task.getCustomer().getRealName(),getContact);
			
			Integer leaderId = salesmanService.getLeaderId(task.getSupplierId(), task.getDepartmentId());
			sendNotify(task,copyWriter,leaderId);
		}else{
			
			//通知业务员文案
			copyWriter = String.format("【分配提醒】您好，1个新任务分配给您，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
					task.getName(),task.getCustomer().getRealName(),getContact);
			sendNotify(task,copyWriter,task.getOwnerId());
			
			//通知业务员的一二级领导
			SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
			String leaderCopyWriter = String.format("【分配提醒】您好，1个新任务分配给【%s】，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时安排跟进",
					organization.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
			
			if(organization.getDirectLeaderId() !=null){
				sendNotify(task,leaderCopyWriter,organization.getDirectLeaderId());
			}
			if(organization.getSuperiorLeaderId() !=null){
				sendNotify(task,leaderCopyWriter,organization.getSuperiorLeaderId());
			}
		}
	}
	/**
	 * 批量分配
	 * @param task
	 */
	private void batchAllocation(NCustomerTask task , int alloCount){
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		//业务员为空，通知服务商管理员或部门主管
		if(task.getOwnerId() == null){
			String copyWriter = String.format("【批量分配提醒】您好，%s个新任务待您分配，请及时分配跟进",alloCount);
			Integer leaderId = salesmanService.getLeaderId(task.getSupplierId(), task.getDepartmentId());
			sendNotify(task,copyWriter,leaderId);
		}else{
			SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
			
			//通知业务员文案
			String copyWriter = String.format("【批量分配提醒】您好，【%s】分配%s个任务给您，请及时跟进",
					organization.getDirectLeaderName(),alloCount);
			sendNotify(task,copyWriter,task.getOwnerId());
			//通知业务员的一二级领导
			
			String leaderCopyWriter = String.format("【批量分配提醒】您好，【%s】分配%s个任务给【%s】，请及时安排跟进",
					organization.getDirectLeaderName(),alloCount,organization.getEmployeeName());
			
			if(organization.getDirectLeaderId() !=null){
				sendNotify(task,leaderCopyWriter,organization.getDirectLeaderId());
			}
			if(organization.getSuperiorLeaderId() !=null){
				sendNotify(task,leaderCopyWriter,organization.getSuperiorLeaderId());
			}
		}
		
		
	}
	/**
	 * 发送通知
	 * @param task 任务实体	 
	 * @param copyWriter 通知文案
	 * @param receivedId 接收人
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
