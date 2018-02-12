package com.gongsibao.crm.job;

import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.NotifyType;

/**
 * 待分配通知
 * @author Administrator
 *
 */
public class AssignmentNoticeJob implements IJob{

	@Override
	public void execute(String par) {
		INCustomerTaskService service = ServiceFactory.create(INCustomerTaskService.class);
		Map<Integer, Integer> assignmentMap = service.getAssignmentCountBySeas();
		for (Map.Entry entry : assignmentMap.entrySet()) {
			Integer receivedId =  (Integer) entry.getKey();
			int count = (Integer) entry.getValue();
			String copyWriterForm = String.format("【待分配提醒】您好，公海里有【%s】个新的任务未分配给您，请及时分配",count);
			sendNotify(copyWriterForm,receivedId);
		}
	}
	/**
	 * 添加通知
	 * @param task
	 */
	private void sendNotify(String copyWriter,Integer receivedId){
		INCustomerTaskNotifyService notifyService = ServiceFactory.create(INCustomerTaskNotifyService.class);
		NCustomerTaskNotify notify = new NCustomerTaskNotify();
		{			
			notify.toNew();
			notify.setContent(copyWriter);
			notify.setType(NotifyType.WEIXIN);
			notify.setReceivedId(receivedId);
			notifyService.save(notify);
		}
	}
}
