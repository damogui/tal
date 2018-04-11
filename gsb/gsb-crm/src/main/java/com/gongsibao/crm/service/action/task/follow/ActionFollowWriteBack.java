package com.gongsibao.crm.service.action.task.follow;

import java.util.Date;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.MtableManager;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.dic.QualityCategory;

/**
 * @author hw 跟进回写：商机，客户相关
 */
public class ActionFollowWriteBack implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTaskFoolow taskFoolow = (NCustomerTaskFoolow) ctx.getItem();
		this.updateTask(taskFoolow);
		this.updateCustomer(taskFoolow);
	}

	private void updateTask(NCustomerTaskFoolow taskFoolow) {

		QualityCategory category = taskFoolow.getQualityCategory();
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(NCustomerTask.class).getTableName());
			updateBuilder.set("intention_category", taskFoolow.getQualityCategory().getValue());
			updateBuilder.set("quality_id", taskFoolow.getQualityId());
			updateBuilder.set("last_content", taskFoolow.getContent());
			updateBuilder.set("next_foolow_time", taskFoolow.getNextFoolowTime());
			updateBuilder.set("last_follow_time", new Date());
			updateBuilder.set("last_foolow_user_id", taskFoolow.getCreatorId());
			updateBuilder.set("quality_progress", taskFoolow.getQualityCategory().getValue());
			// 根据跟进质量表中的Code计算跟进状态；1-未分配、2-待跟进、3-跟进中、4-无法签单、5-已签单、6-未启动
			if (category == QualityCategory.C || category == QualityCategory.D) {

				updateBuilder.set("foolow_status", 4);
				
			} else if (category == QualityCategory.S) {

				updateBuilder.set("foolow_status", 5);
				
			} else if (category == QualityCategory.X) {

				updateBuilder.set("foolow_status", 6);
				
			} else {

				updateBuilder.set("foolow_status", 3);
			}
			updateBuilder.where("id =" + taskFoolow.getTaskId());
		}

		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(updateBuilder.toSQL(), null);
	}

	private void updateCustomer(NCustomerTaskFoolow taskFoolow) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(NCustomer.class).getTableName());
			updateBuilder.set("intention_category", taskFoolow.getQualityCategory().getValue());
			updateBuilder.set("quality_id", taskFoolow.getQualityId());
			updateBuilder.set("last_content", taskFoolow.getContent());
			updateBuilder.set("next_foolow_time", taskFoolow.getNextFoolowTime());
			updateBuilder.set("last_follow_time", new Date());
			updateBuilder.set("last_foolow_user_id", taskFoolow.getCreatorId());
			updateBuilder.where("pkid =" + taskFoolow.getCustomerId());
		}
		IPersister<NCustomer> pm = PersisterFactory.create();
		pm.executeNonQuery(updateBuilder.toSQL(), null);
	}
}
