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
import com.gongsibao.entity.crm.dic.QualityCategory;

/**
 * @author hw 跟进回写：任务，客户相关
 */
public class ActionFollowWriteBack implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTaskFoolow foolow = (NCustomerTaskFoolow) ctx.getItem();
		this.updateTask(foolow);
		this.updateCustoemr(foolow);
	}

	private void updateTask(NCustomerTaskFoolow foolow) {

		QualityCategory category = foolow.getQualityCategory();
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(NCustomerTask.class).getTableName());
			updateBuilder.set("intention_category", foolow.getQualityCategory().getValue());
			updateBuilder.set("quality_id", foolow.getQualityId());
			updateBuilder.set("last_content", foolow.getContent());
			updateBuilder.set("next_foolow_time", foolow.getNextFoolowTime());
			updateBuilder.set("last_follow_time", foolow.getCreateTime());
			updateBuilder.set("last_foolow_user_id", foolow.getCreatorId());

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
			updateBuilder.where("id =" + foolow.getTaskId());
		}

		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(updateBuilder.toSQL(), null);
	}

	private void updateCustoemr(NCustomerTaskFoolow foolow) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(NCustomer.class).getTableName());
			updateBuilder.set("intention_category", foolow.getQualityCategory().getValue());
			updateBuilder.set("quality_id", foolow.getQualityId());
			updateBuilder.set("last_content", foolow.getContent());
			updateBuilder.set("next_foolow_time", foolow.getNextFoolowTime());
			updateBuilder.set("last_follow_time", foolow.getCreateTime());
			updateBuilder.set("last_foolow_user_id", foolow.getCreatorId());
			updateBuilder.where("id =" + foolow.getCustomerId());
		}
		IPersister<NCustomer> pm = PersisterFactory.create();
		pm.executeNonQuery(updateBuilder.toSQL(), null);
	}
}
