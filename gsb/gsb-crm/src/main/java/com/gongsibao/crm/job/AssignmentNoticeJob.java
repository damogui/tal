package com.gongsibao.crm.job;

import java.util.Date;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;

import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.utils.DateUtils;

/**
 * 待分配通知(暂定每天半小时提醒一次（上班时间内）)
 * @author Administrator
 *
 */
public class AssignmentNoticeJob implements IJob{

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(String par) {
		boolean isInDate = DateUtils.isInDate(new Date(), "09:00:00", "19:00:00");
		if(isInDate){
			INCustomerTaskService service = ServiceFactory.create(INCustomerTaskService.class);
			Map<Integer, Integer> assignmentMap = service.getAssignmentCountBySeas();
			for (Map.Entry entry : assignmentMap.entrySet()) {
				Integer receivedId =  (Integer) entry.getKey();
				int count = (Integer) entry.getValue();
				String copyWriterForm = String.format("【待分配提醒】您好，公海里有【%s】个新的任务未分配给您，请及时分配",count);
				sendNotify(copyWriterForm,receivedId);
			}
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
