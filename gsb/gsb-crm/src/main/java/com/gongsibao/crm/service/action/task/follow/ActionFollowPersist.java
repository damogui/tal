package com.gongsibao.crm.service.action.task.follow;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.entity.crm.dic.TaskQualityProgress;

/**
 * @author hw 跟进保存
 */
public class ActionFollowPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTaskFoolow taskFoolow = (NCustomerTaskFoolow) ctx.getItem();
		IPersister<NCustomerTaskFoolow> pm = PersisterFactory.create();
		
		// 获取当前质量的分值
		NCustomerTaskQuality taskQuality = taskFoolow.getQuality();
		Integer currentScore = taskQuality == null ? 0 : taskQuality.getScore();

		// 获取上次的跟进分值
		Integer lastScore = this.getLastScore(taskFoolow.getTaskId());
		if (currentScore.compareTo(lastScore) == 1) {

			taskFoolow.setQualityProgress(TaskQualityProgress.GOUP);

		} else if (currentScore.compareTo(lastScore) == -1) {

			taskFoolow.setQualityProgress(TaskQualityProgress.DECLINE);

		} else {

			taskFoolow.setQualityProgress(TaskQualityProgress.INVARIABILITY);
		}
		pm.save(taskFoolow);
	}

	/**
	 * @Title: getLastScore
	 * @Description: TODO(获取上次跟进的分值)
	 * @param: @param taskId
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	private Integer getLastScore(Integer taskId) {

		Oql oql = new Oql();
		{
			oql.setType(NCustomerTask.class);
			oql.setSelects("NCustomerTask.{id,name},NCustomerTask.quality.{id,score}");
			oql.setFilter("id=?");
			oql.getParameters().add("id", taskId, Types.INTEGER);
		}
		INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
		NCustomerTask task = taskService.queryFirst(oql);
		if (task != null) {

			NCustomerTaskQuality quality = task.getQuality();
			if (quality != null) {

				return quality.getScore();
			}
		}
		return 0;
	}
}
