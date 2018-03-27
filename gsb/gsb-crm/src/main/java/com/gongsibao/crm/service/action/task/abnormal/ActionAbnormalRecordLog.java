package com.gongsibao.crm.service.action.task.abnormal;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * @author hw
 * 抽查异常：记录日志
 */
public class ActionAbnormalRecordLog  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		//1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
		Integer state = Integer.valueOf(ctx.getStatus().get("state").toString());
		NCustomerTask task = (NCustomerTask) ctx.getItem();
		Integer lastFoolowUserId = task.getLastFoolowUserId();
		SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(lastFoolowUserId);
		String getContact = NCustomerContact.handleContact(task.getCustomer());
		String content = task.getLastInspectionContent();
		String copyWriter = "";
		String leaderCopyWriter = "";
		//1.抽查不属实通知日志
		if(state.equals(3)){
			copyWriter = String.format("【抽查提醒】您好，您有1个无法签单商机被抽查，结果为不属实，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，不属实原因【%s】，请及时跟进",
					task.getName(),task.getCustomer().getRealName(),getContact,content);
			leaderCopyWriter = String.format("【抽查提醒】您好，【%s】有1个无法签单商机被抽查，结果为不属实，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，不属实原因【%s】，请及时处理",
					organization.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact,content);
			//通知该条商机的最后跟进人
			notify(task,organization,copyWriter,lastFoolowUserId);
			//通知一级领导
			if(organization.getDirectLeaderId() != null){
				notify(task,organization,leaderCopyWriter,organization.getDirectLeaderId());
			}
			//通知二级领导
			if(organization.getSuperiorLeaderId() != null){
				notify(task,organization,leaderCopyWriter,organization.getAdminId());
			}
		}
		//2.处理通知日志
		Integer type = Integer.valueOf(ctx.getStatus().get("type").toString());
		if(type.equals(2)){
			copyWriter = String.format("【抽查提醒】您好，您有1个无法签单商机被抽查，结果为不属实，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，不属实原因【%s】，请及时跟进",
					task.getName(),task.getCustomer().getRealName(),getContact,content);
			String checkCopyWriter = String.format("【抽查处理提醒】您好，【%s】的1个被抽查为不属实的无法签单商机，已处理，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，处理记录【%s】，请知悉",
					organization.getEmployeeName(),task.getName(),task.getCustomer().getRealName(),getContact,content);
			//通知该条商机的最后跟进人
			notify(task,organization,copyWriter,lastFoolowUserId);
			//通知抽查人(当前登录人)
			notify(task,organization,checkCopyWriter,SessionManager.getUserId());
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
			notify.setType(NotifyType.WEIXIN);
			notify.setCustomerId(task.getCustomerId());
			notify.setSupplierId(organization.getSupplierId());
			notify.setDepartmentId(organization.getDepartmentId());
			notify.setReceivedId(receivedId);
			notifyService.save(notify);
		}
	}
}
