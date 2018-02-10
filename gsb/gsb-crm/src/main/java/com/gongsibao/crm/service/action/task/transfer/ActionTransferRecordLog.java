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

		// 2.转移通知
		Integer formSupplierId = (Integer) getMap.get("formSupplierId");
		Integer formDepartmentId = (Integer) getMap.get("formDepartmentId");
		Integer formUserId = (Integer) getMap.get("formUserId");
		int alloCount = Integer.valueOf(ctx.getStatus().get("alloCount").toString());
		boolean isNotify = (boolean)ctx.getStatus().get("isNotify");
		ProcessNoticeEnum noticeType = ProcessNotice.noticeType(formSupplierId, formDepartmentId, formUserId, task.getSupplierId(), task.getDepartmentId(), task.getOwnerId());
		//处理转移类型
		switch (noticeType.getValue()) {
		case 1:
			sameDepartmentSalesmanToSalesman(task,formUserId,alloCount,isNotify);
			break;
		case 2:
			sameDepartmentSeasToSalesman(task,alloCount,isNotify);
			break;
		case 3:
			diffeDepartmentSalesmanToSalesman(task,formUserId,alloCount,isNotify);
			break;
		case 4:
			diffeDepartmentSalesmanToSeas(task,formUserId,alloCount,isNotify);
			break;
		case 5:
			diffeDepartmentSeasToSalesman(task,formDepartmentId,alloCount,isNotify);
			break;
		case 6:
			diffeDepartmentSeasToSeas(task,formDepartmentId,alloCount,isNotify);
			break;
		}
	}
	/**
	 * 部门A的业务员A任务转移给部门B的业务员B
	 * @param task 任务实体
	 * @param formUserId 被转移的业务员
	 * @param alloCount 是否批量转移
	 * @param isNotify 批量的是否已经已经发送通知
	 */
	private void diffeDepartmentSalesmanToSalesman(NCustomerTask task,Integer formUserId,int alloCount,boolean isNotify){
		//1.被转移业务员和接收业务员的组织机构
		SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(formUserId);
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
		String getContact = NCustomerContact.handleContact(task.getCustomer());
		
		//2.添加批量和单个的不同文案
		String copyWriterForm = null;
		String copyWriterTo = null;
		String leaderCopyWriter = null;
		if(alloCount >1){
			if(!isNotify){
				copyWriterForm = String.format("【批量转移提醒】您好，【%s】把您的%s个任务转移给【%s】的【%s】，请知悉",
						SessionManager.getUserName(),alloCount,orgaTo.getDepartmentName(),orgaTo.getEmployeeName());
				
				 copyWriterTo = String.format("【批量转移提醒】您好，【%s】转移给您%s个任务，请及时跟进",
						 SessionManager.getUserName(),alloCount);
				 
				 leaderCopyWriter = String.format("【批量转移提醒】您好，【%s】转移%s个任务给【%s】的【%s】，请知悉",
						 SessionManager.getUserName(),alloCount,orgaTo.getDepartmentName(),orgaTo.getEmployeeName());
			}
		}else{
			copyWriterForm = String.format("【转移提醒】您好，【%s】把您的1个任务转移给【%s】，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
					SessionManager.getUserName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
			
			 copyWriterTo = String.format("【转移提醒】您好，【%s】从【%s】转移给您1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
					SessionManager.getUserName(),orgaForm.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
			 
			 leaderCopyWriter = String.format("【转移提醒】您好，【%s】从【%s】转移1个任务给【%s】，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						SessionManager.getUserName(),orgaForm.getEmployeeName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		}
		
		//3.发送通知
		if(copyWriterForm != null && copyWriterTo != null && leaderCopyWriter != null){
			sendNotify(task,copyWriterForm,formUserId);
			sendNotify(task,copyWriterTo,task.getOwnerId());
			
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
	}
	/**
	 * 部门A的业务员A任务转移给部门B的公海
	 * @param task 任务实体
	 * @param formUserId 被转移的业务员
	 * @param alloCount 是否批量转移
	 * @param isNotify 批量的是否已经已经发送通知
	 */
	private void diffeDepartmentSalesmanToSeas(NCustomerTask task,Integer formUserId,int alloCount,boolean isNotify){
		//1.被转移业务员和接收业务员的组织机构
		SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(formUserId);
		
		//获取接收部门的领导
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer leaderId = salesmanService.getLeaderId(null, task.getDepartmentId());
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(leaderId);
		
		String getContact = NCustomerContact.handleContact(task.getCustomer());		
		//2.添加批量和单个的不同文案
		String copyWriterForm = null;
		String copyWriterTo = null;
		String leaderCopyWriter = null;
		if(alloCount >1){
			if(!isNotify){
				copyWriterForm = String.format("批量转移提醒】您好，【%s】把您的%s个任务转移给【%s】的公海，请知悉",
						SessionManager.getUserName(),alloCount,orgaTo.getDepartmentName());
				
				 copyWriterTo = String.format("【批量转移提醒】您好，【%s】从【%s】的【%s】转移给您部门公海%s个任务，请及时分配跟进",
						 SessionManager.getUserName(),orgaForm.getDepartmentName(),orgaForm.getEmployeeName(),alloCount);
				 
				 leaderCopyWriter = String.format("【批量转移提醒】您好，【%s】从【%s】的【%s】转移%s个任务给【%s】的公海，请知悉",
						 SessionManager.getUserName(),orgaForm.getDepartmentName(),orgaForm.getEmployeeName(),alloCount,orgaTo.getDepartmentName());
			}
		}else{
			copyWriterForm = String.format("【转移提醒】您好，【%s】把您的1个任务转移给【%s】的公海，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
					SessionManager.getUserName(),orgaTo.getDepartmentName(),task.getName(),task.getCustomer().getRealName(),getContact);
			
			 copyWriterTo = String.format("【转移提醒】您好，【%s】从【%s】转移给您部门公海1个任务，待分配，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时分配跟进",
					SessionManager.getUserName(),orgaForm.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
			 
			 leaderCopyWriter = String.format("【转移提醒】您好，【%s】从【%s】转移1个任务给【%s】的公海，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						SessionManager.getUserName(),orgaForm.getEmployeeName(),orgaTo.getDepartmentName(),task.getName(),task.getCustomer().getRealName(),getContact);
		}
		
		//3.发送通知
		if(copyWriterForm != null && copyWriterTo != null && leaderCopyWriter != null){
			sendNotify(task,copyWriterForm,formUserId);
			sendNotify(task,copyWriterTo,leaderId);
			
			if(orgaForm.getDirectLeaderId() != null){
				sendNotify(task,leaderCopyWriter,orgaForm.getDirectLeaderId());
			}
			if(orgaForm.getSuperiorLeaderId() != null){
				sendNotify(task,leaderCopyWriter,orgaForm.getSuperiorLeaderId());
			}
		}
	}
	/**
	 * 部门A的公海任务转移给部门B的业务员B
	 * @param task 任务实体
	 * @param formDepartmentId 被转移任务的部门
	 * @param alloCount 是否批量转移
	 * @param isNotify 批量的是否已经已经发送通知
	 */
	private void diffeDepartmentSeasToSalesman(NCustomerTask task,Integer formDepartmentId,int alloCount,boolean isNotify){
		//1.被转移业务员和接收业务员的组织机构
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer leaderId = salesmanService.getLeaderId(null, formDepartmentId);
		SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(leaderId);
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
		
		String getContact = NCustomerContact.handleContact(task.getCustomer());		
		//2.添加批量和单个的不同文案
		String copyWriterForm = null;
		String copyWriterTo = null;
		String leaderCopyWriter = null;
		if(alloCount >1){
			if(!isNotify){
				copyWriterForm = String.format("【批量转移提醒】您好，【%s】把您部门公海的%s个任务转移给【%s】的【%s】，请知悉",
						SessionManager.getUserName(),alloCount,orgaTo.getDepartmentName(),orgaTo.getEmployeeName());
				
				 copyWriterTo = String.format("【批量转移提醒】您好，【%s】从【%s】的公海转移给您%s个任务，请及时跟进",
						 SessionManager.getUserName(),orgaForm.getDepartmentName(),alloCount);
				 
				 leaderCopyWriter = String.format("【批量转移提醒】您好，【%s】从【%s】的公海转移给【%s】%s个任务，请及时安排跟进",
						 SessionManager.getUserName(),orgaForm.getDepartmentName(),orgaTo.getEmployeeName(),alloCount);
			}
		}else{
			copyWriterForm = String.format("【转移提醒】您好，【%s】把您部门公海的1个任务转移给【%s】的【%s】，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
					SessionManager.getUserName(),orgaTo.getDepartmentName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
			
			 copyWriterTo = String.format("【转移提醒】您好，【%s】从【%s】的公海转移给您1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
					SessionManager.getUserName(),orgaForm.getDepartmentName(),task.getName(),task.getCustomer().getRealName(),getContact);
			 
			 leaderCopyWriter = String.format("【转移提醒】您好，【%s】从【%s】的公海转移给【%s】1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时安排跟进",
						SessionManager.getUserName(),orgaForm.getDepartmentName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		}
		
		//3.发送通知
		if(copyWriterForm != null && copyWriterTo != null && leaderCopyWriter != null){
			sendNotify(task,copyWriterForm,leaderId);
			sendNotify(task,copyWriterTo,task.getOwnerId());
			
			if(orgaTo.getDirectLeaderId() != null){
				sendNotify(task,leaderCopyWriter,orgaTo.getDirectLeaderId());
			}
			if(orgaTo.getSuperiorLeaderId() != null){
				sendNotify(task,leaderCopyWriter,orgaTo.getSuperiorLeaderId());
			}
		}
	}
	/**
	 * 部门A的公海任务转移给部门B的公海
	 * @param task 任务实体
	 * @param formDepartmentId 被转移任务的部门
	 * @param alloCount 是否批量转移
	 * @param isNotify 批量的是否已经已经发送通知
	 */
	private void diffeDepartmentSeasToSeas(NCustomerTask task,Integer formDepartmentId,int alloCount,boolean isNotify){
		//1.被转移部门和接收部门的组织机构
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer leaderIdForm = salesmanService.getLeaderId(null, formDepartmentId);
		SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(leaderIdForm);
		
		Integer leaderIdTo = salesmanService.getLeaderId(null, task.getDepartmentId());
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(leaderIdTo);
		
		String getContact = NCustomerContact.handleContact(task.getCustomer());		
		//2.添加批量和单个的不同文案
		String leaderCopyWriterTo = null;
		String leaderCopyWriterForm = null;
		if(alloCount >1){
			if(!isNotify){
				leaderCopyWriterTo = String.format("【批量转移提醒】您好，【%s】从【%s】的公海转移给您部门公海%s个任务，请及时分配跟进",
						SessionManager.getUserName(),orgaForm.getDepartmentName(),getContact);
				leaderCopyWriterForm = String.format("【批量转移提醒】您好，【%s】从您的部门转移%s个任务给【%s】的公海，请知悉",
						SessionManager.getUserName(),getContact,orgaTo.getDepartmentName());
			}
		}else{
			 leaderCopyWriterTo = String.format("【转移提醒】您好，【%s】从【%s】的公海转移给您部门公海1个任务，待分配任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时分配跟进",
						SessionManager.getUserName(),orgaForm.getDepartmentName(),task.getName(),task.getCustomer().getRealName(),getContact);
			 leaderCopyWriterForm = String.format("【转移提醒】您好，【%s】从您的部门转移1个任务给【%s】的公海，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						SessionManager.getUserName(),orgaTo.getDepartmentName(),task.getName(),task.getCustomer().getRealName(),getContact);
		}
		
		//3.发送通知
		if(leaderCopyWriterTo != null && leaderCopyWriterForm != null){
			sendNotify(task,leaderCopyWriterTo,leaderIdTo);
			sendNotify(task,leaderCopyWriterForm,leaderIdForm);
		}
	}
	/**
	 * 部门内部-业务员A任务转移给业务员B
	 * @param task 任务实体
	 * @param formUserId 被转移的业务员
	 * @param alloCount 是否批量转移
	 * @param isNotify 批量的是否已经已经发送通知
	 */
	private void sameDepartmentSalesmanToSalesman(NCustomerTask task,Integer formUserId,int alloCount,boolean isNotify){
		//1.被转移业务员和接收业务员的组织机构
		SalesmanOrganization orgaForm = SupplierSessionManager.getSalesmanOrganization(formUserId);
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
		String getContact = NCustomerContact.handleContact(task.getCustomer());
		
		//2.添加批量和单个的不同文案
		String copyWriterForm = null;
		String copyWriterTo = null;
		String leaderCopyWriter = null;
		if(alloCount >1){
			if(!isNotify){
				copyWriterForm = String.format("【批量转移提醒】您好，【%s】把您的%s个任务转移给【%s】，请知悉",
						SessionManager.getUserName(),alloCount,orgaTo.getEmployeeName());
				
				 copyWriterTo = String.format("【批量转移提醒】您好，【%s】转移给您%s个任务，请及时跟进",
						 SessionManager.getUserName(),alloCount);
				 
				 leaderCopyWriter = String.format("【批量转移提醒】您好，【%s】转移给【%s】%s个任务，请知悉",
						 SessionManager.getUserName(),orgaTo.getEmployeeName(),alloCount);
			}
		}else{
			copyWriterForm = String.format("【转移提醒】您好，【%s】把您的1个任务转移给【%s】，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
					SessionManager.getUserName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
			
			 copyWriterTo = String.format("【转移提醒】您好，【%s】从【%s】转移给您1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
					SessionManager.getUserName(),orgaForm.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
			 
			 leaderCopyWriter = String.format("【转移提醒】您好，【%s】从【%s】转移给【%s】1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						SessionManager.getUserName(),orgaForm.getEmployeeName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		}
		
		//3.发送通知
		if(copyWriterForm != null && copyWriterTo != null && leaderCopyWriter != null){
			sendNotify(task,copyWriterForm,formUserId);
			sendNotify(task,copyWriterTo,task.getOwnerId());
			
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
	}
	
	/**
	 * 部门内部-公海任务转移给业务员B
	 * @param task
	 * @param alloCount 是否批量转移
	 * @param isNotify 批量的是否已经已经发送通知
	 */
	private void sameDepartmentSeasToSalesman(NCustomerTask task,int alloCount,boolean isNotify){
		//1.公海负责人和接收业务员的组织机构
		SalesmanOrganization orgaHighSeas = SupplierSessionManager.getSalesmanOrganization(SessionManager.getUserId());
		SalesmanOrganization orgaTo = SupplierSessionManager.getSalesmanOrganization(task.getOwnerId());
		String getContact = NCustomerContact.handleContact(task.getCustomer());
		
		//2.添加批量和单个的不同文案
		String copyWriterHighSeas = null;
		String copyWriterTo = null;
		String leaderCopyWriter = null;
		if(alloCount > 1){
			if(!isNotify){
				copyWriterHighSeas = String.format("【批量转移提醒】您好，【%s】把您部门公海的%s个任务转移给【%s】，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						orgaHighSeas.getEmployeeName(),alloCount,orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
				
				copyWriterTo = String.format("【批量转移提醒】您好，【%s】从【%s】公海转移给您%s个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
						orgaHighSeas.getEmployeeName(),orgaHighSeas.getDepartmentName(),alloCount,task.getName(),task.getCustomer().getRealName(),getContact);
				
				 leaderCopyWriter = String.format("批量转移提醒】您好，【%s】从【%s】公海转移给【%s】%s个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						orgaHighSeas.getEmployeeName(),orgaHighSeas.getDepartmentName(),orgaTo.getEmployeeName(),alloCount,task.getName(),task.getCustomer().getRealName(),getContact);
			}
		}else {
			 copyWriterHighSeas = String.format("【转移提醒】您好，【%s】把您部门公海的1个任务转移给【%s】，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
					orgaHighSeas.getEmployeeName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
			 
			 copyWriterTo = String.format("【转移提醒】您好，【%s】从【%s】公海转移给您1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
						orgaHighSeas.getEmployeeName(),orgaHighSeas.getDepartmentName(),task.getName(),task.getCustomer().getRealName(),getContact);
			 
			 leaderCopyWriter = String.format("【转移提醒】您好，【%s】从【%s】公海转移给【%s】1个任务，任务名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
					orgaHighSeas.getEmployeeName(),orgaHighSeas.getDepartmentName(),orgaTo.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact);
		}		
		
		//3.发送通知
		if(copyWriterHighSeas != null && leaderCopyWriter != null){
			sendNotify(task,copyWriterHighSeas,SessionManager.getUserId());
			sendNotify(task,copyWriterTo,task.getOwnerId());
			
			if(orgaTo.getDirectLeaderId() != null){
				sendNotify(task,leaderCopyWriter,orgaTo.getDirectLeaderId());
			}
			if(orgaTo.getSuperiorLeaderId() != null){
				sendNotify(task,leaderCopyWriter,orgaTo.getSuperiorLeaderId());
			}	
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
