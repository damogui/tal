package com.gongsibao.crm.job;

import java.util.Date;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;

import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.utils.DateUtils;
import com.gongsibao.utils.NCustomerContact;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * 超时提醒（当前的日期大于下次预约时间，每天推迟到9点半提醒）
 * @author Administrator
 *
 */
public class TimeoutNoticeJob implements IJob{

	@Override
	public void execute(String par) {
		Date date = new Date();		
		
		INCustomerTaskService service = ServiceFactory.create(INCustomerTaskService.class);
		List<NCustomerTask> taskList = service.getTimeOutList(date);
		
		if(taskList != null && taskList.size() >0){
			for (NCustomerTask item : taskList) {
				String getContact = NCustomerContact.handleContact(item.getCustomer());
				int days = DateUtils.differentDays(item.getNextFoolowTime(), date);
				SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(item.getLastFoolowUserId());
				String copyWriter = String.format("【超时提醒】您好，您有1个商机已超预约时间【%s】天未跟进，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时跟进",
						days,item.getName(),item.getCustomer().getRealName(),getContact);
				
				String leaderCopyWriter = String.format("【超时提醒】您好，【%s】有1个商机已超预约时间【%s】天未跟进，商机名称【%s】，客户名称【%s】，客户联系方式【%s】，请及时安排跟进",
						organization.getEmployeeName(),days,item.getName(),item.getCustomer().getRealName(),getContact);
				
				sendNotify(item,organization,copyWriter,item.getLastFoolowUserId());
				
				//通知一级领导
				if(organization.getDirectLeaderId() != null){
					sendNotify(item,organization,leaderCopyWriter,organization.getDirectLeaderId());
				}
				//通知二级领导
				if(organization.getSuperiorLeaderId() != null){
					sendNotify(item,organization,leaderCopyWriter,organization.getSuperiorLeaderId());
				}
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
	private void sendNotify(NCustomerTask task,SalesmanOrganization organization,String copyWriter,Integer receivedId){
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
