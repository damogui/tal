package com.gongsibao.crm.service.action.task.follow;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.crm.base.INCustomerTaskQualityService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * 跟进：记录日志
 * @author Administrator
 *
 */
public class ActionFollowRecordLog implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTaskFoolow foolow = (NCustomerTaskFoolow) ctx.getItem();
		//获取商机
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		NCustomerTask taskEntity = taskService.getTaskQualityByTaskId(foolow.getTaskId());
		Integer qualityId = null;
		String qualityName = "";
		if(ctx.getStatus().get("originalQualityId")!=null){
			qualityId = Integer.valueOf(ctx.getStatus().get("originalQualityId").toString());
			INCustomerTaskQualityService quality = ServiceFactory.create(INCustomerTaskQualityService.class);
			NCustomerTaskQuality qualityEntity =  quality.byId(qualityId);
			qualityName  = qualityEntity.getCode();
		}
		if((qualityId == null && taskEntity.getQualityId()!=null) || !qualityId.equals(taskEntity.getQualityId())){
			SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(SessionManager.getUserId());
			
			//1.保存通知日志
			String copyWriter = "";
			String leaderCopyWriter = "";
			String getContact = NCustomerContact.handleContact(taskEntity.getCustomer());
			if(qualityId == null){
				 copyWriter = String.format("【商机质量变更提醒】您好，您有1个商机质量变更为【%s】，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						 taskEntity.getQuality().getCode(),taskEntity.getName(),taskEntity.getCustomer().getRealName(),getContact);
				 leaderCopyWriter = String.format("【商机质量变更提醒】您好，【%s】有1个商机质量变更为【%s】，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						organization.getEmployeeName(),taskEntity.getQuality().getCode(),taskEntity.getName(),taskEntity.getCustomer().getRealName(),getContact);
			}else{
				 copyWriter = String.format("【商机质量变更提醒】您好，您有1个商机质量从【%s】变更为【%s】，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						 qualityName,taskEntity.getQuality().getCode(),taskEntity.getName(),taskEntity.getCustomer().getRealName(),getContact);
				 leaderCopyWriter = String.format("【商机质量变更提醒】您好，【%s】有1个商机质量从【%s】变更为【%s】，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，请知悉",
						organization.getEmployeeName(),qualityName,taskEntity.getQuality().getCode(),taskEntity.getName(),taskEntity.getCustomer().getRealName(),getContact);
			}
			
			//通知业务员
			notify(taskEntity,organization,copyWriter,organization.getEmployeeId());
			//通知业务员的一级领导
			if(organization.getDirectLeaderId() != null){
				notify(taskEntity,organization,leaderCopyWriter,organization.getDirectLeaderId());
			}
			//通知业务员的二级领导
			if(organization.getSuperiorLeaderId() != null){
				notify(taskEntity,organization,leaderCopyWriter,organization.getAdminId());
			}
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
			notify.setIsSend(false);//自己跟进的没必要发送消息 hw 2018-04-17
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
