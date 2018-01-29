package com.gongsibao.crm.service.action.task.follow;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.MtableManager;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

/**
 * @author hw
 * 跟进回写：任务，客户相关
 */
public class ActionFollowWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTaskFoolow foolow = (NCustomerTaskFoolow) ctx.getItem();
		this.updateTask(foolow);
		this.updateCustoemr(foolow);
	}
	
	private void updateTask(NCustomerTaskFoolow foolow){
		
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(NCustomerTask.class).getTableName());
			updateBuilder.set("intention_category", foolow.getQualityCategory().getValue());
			updateBuilder.set("quality_id", foolow.getQualityId());
			updateBuilder.set("last_content", foolow.getContent());
			updateBuilder.set("next_foolow_time", foolow.getNextFoolowTime());
			updateBuilder.set("last_follow_time", foolow.getCreateTime());
			updateBuilder.set("last_foolow_user_id", foolow.getCreatorId());
			updateBuilder.set("swt_customer_id", foolow.getCreatorId());
			
//	        UNALLOCATION(1, "未分配"), 
//			UNFollow(2, "待跟进"), 
//			FOLLOWING(3, "跟进中"), 
//			DEFEATED(4, "无法签单"), 
//			SIGNED(5, "已签单");
			//要根据跟进质量计算
			//updateBuilder.set("foolow_status", CustomerFollowStatus.FOLLOWING.getValue());
			
			updateBuilder.where("task_id =" + foolow.getTaskId());
		}
		
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(updateBuilder.toSQL(), null);
	}

	private void updateCustoemr(NCustomerTaskFoolow foolow){
		
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(NCustomer.class).getTableName());
			updateBuilder.set("intention_category", foolow.getQualityCategory().getValue());
			updateBuilder.set("quality_id", foolow.getQualityId());
			updateBuilder.set("last_content", foolow.getContent());
			updateBuilder.set("next_foolow_time", foolow.getNextFoolowTime());
			updateBuilder.set("last_follow_time", foolow.getCreateTime());
			updateBuilder.set("last_foolow_user_id", foolow.getCreatorId());
			updateBuilder.set("swt_customer_id", foolow.getCreatorId());
			updateBuilder.where("customer_id =" + foolow.getCustomerId());
		}
		
		IPersister<NCustomer> pm = PersisterFactory.create();
		pm.executeNonQuery(updateBuilder.toSQL(), null);
	}
}
