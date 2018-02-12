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
import com.gongsibao.supplier.base.ISalesmanService;
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

		// 2.转移通知
		boolean isNotify = (boolean) getMap.get("isNotify");
		if(!isNotify){
			Map<ProcessNoticeEnum,Map<Integer, Integer>> formSupplierId = (Map<ProcessNoticeEnum, Map<Integer, Integer>>) getMap.get("noticeMap");
			for (Map.Entry entry : formSupplierId.entrySet()) {  
				ProcessNoticeEnum noticeEnum = (ProcessNoticeEnum) entry.getKey();
				Map<Integer, Integer> typeCountMap = (Map<Integer, Integer>) entry.getValue();
				switch (noticeEnum.getValue()) {
				case 1:
					salesmanTosalesman(typeCountMap,task);
					break;
				case 2:
					salesmanToseas(typeCountMap,task);
					break;
				case 3:
					seasTosalesman(typeCountMap,task);
					break;
				case 4:
					seasToseas(typeCountMap,task);
					break;
				}
			}
		}	   
	}
	/**
	 * 业务员任务转移给业务员
	 * @param typeCountMap
	 * @param task
	 */
	private void salesmanTosalesman(Map<Integer, Integer> typeCountMap,NCustomerTask task){
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
		int countTo = 0;
		//被转移业务员（N个）
		for (Map.Entry entry : typeCountMap.entrySet()) {
			int alloCount = (Integer) entry.getValue();
			countTo += alloCount;
			
			Integer formUserId =  (Integer) entry.getKey();
			SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(formUserId);
			
			String copyWriterForm = String.format("【转移提醒】您好，【%s】把您的%s个任务转移给【%s】，请知悉",
					SessionManager.getUserName(),alloCount,orgaTo.getEmployeeName());
			String leaderCopyWriterForm = String.format("【转移提醒】您好，【%s】从【%s】转移给【%s】%s个任务，请知悉",
					SessionManager.getUserName(),orgaForm.getEmployeeName(),orgaTo.getEmployeeName(),alloCount);
			
			sendNotify(task,copyWriterForm,formUserId);
			if(orgaForm.getDirectLeaderId() != null){
				sendNotify(task,leaderCopyWriterForm,orgaForm.getDirectLeaderId());
			}
			if(orgaForm.getSuperiorLeaderId() != null){
				sendNotify(task,leaderCopyWriterForm,orgaForm.getSuperiorLeaderId());
			}
		}
		//接收业务员		
		String copyWriterTo = String.format("【转移提醒】您好，【%s】转移给您%s个任务，请及时跟进",
				SessionManager.getUserName(),countTo);
		String leaderCopyWriterTo = String.format("【转移提醒】您好，【%s】转移给【%s】%s个任务，请知悉",
				SessionManager.getUserName(),orgaTo.getEmployeeName(),countTo);
		
		sendNotify(task,copyWriterTo,task.getOwnerId());
		
		if(orgaTo.getDirectLeaderId() != null){
			sendNotify(task,leaderCopyWriterTo,orgaTo.getDirectLeaderId());
		}
		if(orgaTo.getSuperiorLeaderId() != null){
			sendNotify(task,leaderCopyWriterTo,orgaTo.getSuperiorLeaderId());
		}			
	}
	/**
	 * 公海任务转移给业务员
	 * @param typeCountMap
	 * @param task
	 */
	private void seasTosalesman(Map<Integer, Integer> typeCountMap,NCustomerTask task){
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
		int countTo = 0;
		//被转移部门负责人（多个）
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		for (Map.Entry entry : typeCountMap.entrySet()) {
			int alloCount = (Integer) entry.getValue();
			countTo += alloCount;
			
			Integer formDepartId =  (Integer) entry.getKey();
			Integer leaderId = salesmanService.getLeaderId(null, formDepartId);
			SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(leaderId);
			
			String copyWriterForm = String.format("【转移提醒】您好，【%s】把您部门公海的%s个任务转移给【%s】，请知悉",
					SessionManager.getUserName(),alloCount,orgaTo.getEmployeeName());
			
			String leaderCopyWriterForm = String.format("【转移提醒】您好，【%s】从【%s】的公海转移给【%s】的公海%s个任务，请知悉",
					SessionManager.getUserName(),orgaForm.getDepartmentName(),orgaTo.getEmployeeName(),alloCount);
			
			sendNotify(task,copyWriterForm,leaderId);			
			
			if(orgaForm.getDirectLeaderId() != null){
				sendNotify(task,leaderCopyWriterForm,orgaForm.getDirectLeaderId());
			}
			if(orgaForm.getSuperiorLeaderId() != null){
				sendNotify(task,leaderCopyWriterForm,orgaForm.getSuperiorLeaderId());
			}			
		}
		//接收业务员		
		String copyWriterTo = String.format("【转移提醒】您好，【%s】转移给您%s个任务，请及时跟进",
				SessionManager.getUserName(),countTo);
		String leaderCopyWriterTo = String.format("【转移提醒】您好，【%s】转移给【%s】%s个任务，请知悉",
				 SessionManager.getUserName(),orgaTo.getEmployeeName(),countTo);
		
		sendNotify(task,copyWriterTo,task.getOwnerId());
		
		if(orgaTo.getDirectLeaderId() != null){
			sendNotify(task,leaderCopyWriterTo,orgaTo.getDirectLeaderId());
		}
		if(orgaTo.getSuperiorLeaderId() != null){
			sendNotify(task,leaderCopyWriterTo,orgaTo.getSuperiorLeaderId());
		}	
	}
	
	/**
	 * 业务员任务转移给公海
	 * @param typeCountMap
	 * @param task
	 */
	private void salesmanToseas(Map<Integer, Integer> typeCountMap,NCustomerTask task){
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer leaderId = salesmanService.getLeaderId(task.getSupplierId(), task.getDepartmentId());
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(leaderId);
		int countTo = 0;
		//被转移业务员（N个）
		for (Map.Entry entry : typeCountMap.entrySet()) {
			int alloCount = (Integer) entry.getValue();
			countTo += alloCount;
			
			Integer formUserId =  (Integer) entry.getKey();
			SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(formUserId);
			
			String copyWriterForm = String.format("【转移提醒】您好，【%s】把您的%s个任务转移给【%s】的公海，请知悉",
					SessionManager.getUserName(),alloCount,orgaTo.getDepartmentName());
			String leaderCopyWriterForm = String.format("【转移提醒】您好，【%s】从【%s】转移给【%s】的公海%s个任务，请知悉",
					SessionManager.getUserName(),orgaForm.getEmployeeName(),orgaTo.getDepartmentName(),alloCount);
			
			sendNotify(task,copyWriterForm,formUserId);
			if(orgaForm.getDirectLeaderId() != null){
				sendNotify(task,leaderCopyWriterForm,orgaForm.getDirectLeaderId());
			}
			if(orgaForm.getSuperiorLeaderId() != null){
				sendNotify(task,leaderCopyWriterForm,orgaForm.getSuperiorLeaderId());
			}
		}
		//接收部门负责人		
		String copyWriterTo = String.format("【转移提醒】您好，【%s】转移给您部门公海%s个任务，请及时跟进",
				SessionManager.getUserName(),countTo);
		String leaderCopyWriterTo = String.format("【转移提醒】您好，【操作人】转移给【%s】的公海%s个任务，请知悉",
				SessionManager.getUserName(),orgaTo.getDepartmentName(),countTo);
		
		sendNotify(task,copyWriterTo,leaderId);
		
		if(orgaTo.getDirectLeaderId() != null){
			sendNotify(task,leaderCopyWriterTo,orgaTo.getDirectLeaderId());
		}
		if(orgaTo.getSuperiorLeaderId() != null){
			sendNotify(task,leaderCopyWriterTo,orgaTo.getSuperiorLeaderId());
		}			
	}
	/**
	 * 公海任务转移给公海
	 * @param typeCountMap
	 * @param task
	 */
	private void seasToseas(Map<Integer, Integer> typeCountMap,NCustomerTask task){
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer leaderIdTo = salesmanService.getLeaderId(task.getSupplierId(), task.getDepartmentId());
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(leaderIdTo);
		int countTo = 0;
		//被转移部门负责人（多个）
		for (Map.Entry entry : typeCountMap.entrySet()) {
			int alloCount = (Integer) entry.getValue();
			countTo += alloCount;
			
			Integer formDepartId =  (Integer) entry.getKey();
			Integer leaderIdFrom = salesmanService.getLeaderId(null, formDepartId);
			SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(leaderIdFrom);
			
			String copyWriterForm = String.format("【转移提醒】您好，【%s】把您部门公海的%s个任务转移给【%s】的公海，请知悉",
					SessionManager.getUserName(),alloCount,orgaTo.getDepartmentName());
			
			String leaderCopyWriterForm = String.format("【转移提醒】您好，【%s】从【%s】的公海转移给【%s】的公海N个任务，请知悉",
					SessionManager.getUserName(),orgaForm.getDepartmentName(),orgaTo.getDepartmentName(),alloCount);
			
			sendNotify(task,copyWriterForm,leaderIdFrom);			
			
			if(orgaForm.getDirectLeaderId() != null){
				sendNotify(task,leaderCopyWriterForm,orgaForm.getDirectLeaderId());
			}
			if(orgaForm.getSuperiorLeaderId() != null){
				sendNotify(task,leaderCopyWriterForm,orgaForm.getSuperiorLeaderId());
			}			
		}
		//接收部门的负责人		
		String copyWriterTo = String.format("【转移提醒】您好，【%s】转移给您部门公海%s个任务，请及时跟进",
				SessionManager.getUserName(),countTo);
		String leaderCopyWriterTo = String.format("【转移提醒】您好，【%s】转移给【%s】的公海%s个任务，请知悉",
				 SessionManager.getUserName(),orgaTo.getDepartmentName(),countTo);
		
		sendNotify(task,copyWriterTo,leaderIdTo);
		
		if(orgaTo.getDirectLeaderId() != null){
			sendNotify(task,leaderCopyWriterTo,orgaTo.getDirectLeaderId());
		}
		if(orgaTo.getSuperiorLeaderId() != null){
			sendNotify(task,leaderCopyWriterTo,orgaTo.getSuperiorLeaderId());
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
