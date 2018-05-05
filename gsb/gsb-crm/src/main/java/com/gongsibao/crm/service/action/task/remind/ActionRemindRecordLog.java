package com.gongsibao.crm.service.action.task.remind;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.util.NewBeanInstanceStrategy;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * 提醒：记录日志
 * @author Administrator
 *
 */
public class ActionRemindRecordLog implements IAction{
	ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
	
	
	
	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask task = (NCustomerTask) ctx.getItem();
		Map<String, Object> getMap = ctx.getStatus();
		
		String content = getMap.get("content").toString();
		
		//定义业务员
		Integer ownerId = 0;
		//1.商机有业务员，获取该业务员的上2级领导。2.商机没有业务员根据商机的部门和服务商 获取相应的领导，然后再获取该领导的上2级领导
		if(task.getOwnerId() != null){
			ownerId = task.getOwnerId();
		}else{
			ownerId = salesmanService.getLeaderId(task.getSupplierId(),task.getDepartmentId());
		}
		SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(ownerId);
		
		
		// 1.保存跟进记录
		INCustomerTaskFoolowService foolowService = ServiceFactory.create(INCustomerTaskFoolowService.class);
		NCustomerTaskFoolow foolowLog = new NCustomerTaskFoolow();
		{
			foolowLog.toNew();// 标示下类型，有多种
			foolowLog.setCustomerId(task.getCustomerId());
			foolowLog.setTaskId(task.getId());
			foolowLog.setQualityCategory(task.getIntentionCategory());
			foolowLog.setQualityId(task.getQualityId());
			try {
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = format.format(new Date());
				foolowLog.setNextFoolowTime(format.parse(dateStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			foolowLog.setContent(content);
			
			foolowLog.setSupplierId(task.getSupplierId());
			foolowLog.setDepartmentId(task.getDepartmentId());
			
			foolowLog.setCustomerId(task.getCustomerId());
			foolowLog.setCreatorId(SessionManager.getUserId());
			foolowLog.setCreator(SessionManager.getUserName());
			
			foolowService.remindFoolowSave(foolowLog);
		}		
		
		//2.通知日志
		String copyWriter = String.format("【二次咨询提醒】您好，【%s】提醒您及时跟进商机ID【%s】的客户，客户咨询内容为【%s】",
				SessionManager.getUserName(),task.getId(),content);
		
		if(organization!=null){
			//通知业务员
			if(organization.getEmployeeId() !=null){
				notify(task,organization,copyWriter,organization.getEmployeeId());
			}
			//通知直属领导
			if(organization.getDirectLeaderId() != null){
				notify(task,organization,copyWriter,organization.getDirectLeaderId());
			}
			//通知服务商管理员
			if(organization.getAdminId() != null){
				notify(task,organization,copyWriter,organization.getAdminId());
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
