package com.gongsibao.crm.service.action.task.follow;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskQuality;

/**
 * @author hw
 * 跟进回写：任务，客户相关
 */
public class ActionFollowWriteBack implements IAction{

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTaskFoolow foolow = (NCustomerTaskFoolow) ctx.getItem();
		NCustomerTaskQuality getQuality = getNCustomerTaskQuality(foolow.getQualityId());
		this.updateTask(foolow,getQuality);
		this.updateCustoemr(foolow,getQuality);
	}
	
	private void updateTask(NCustomerTaskFoolow foolow,NCustomerTaskQuality quality){
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(NCustomerTask.class).getTableName());
			updateBuilder.set("intention_category", quality.getIntentionCategory().getValue());
			updateBuilder.set("quality_id", foolow.getQualityId());
			updateBuilder.set("last_content", foolow.getContent());
			updateBuilder.set("next_foolow_time", foolow.getNextFoolowTime());
			updateBuilder.set("last_follow_time", foolow.getCreateTime());
			updateBuilder.set("last_foolow_user_id", foolow.getCreatorId());
			
			//根据跟进质量表中的Code计算跟进状态；1-未分配、2-待跟进、3-跟进中、4-无法签单、5-已签单
			if(quality.getCode().contains("C") || quality.getCode().contains("D")){
				updateBuilder.set("foolow_status", 4);
			}else if(quality.getCode().contains("S")){
				updateBuilder.set("foolow_status", 5);
			}else if(quality.getCode().contains("X")){
				updateBuilder.set("foolow_status", 2);
			}else{
				updateBuilder.set("foolow_status", 3);
			}
			updateBuilder.where("id =" + foolow.getTaskId());
		}
		
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		pm.executeNonQuery(updateBuilder.toSQL(), null);
	}

	private void updateCustoemr(NCustomerTaskFoolow foolow,NCustomerTaskQuality quality){
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(NCustomer.class).getTableName());
			updateBuilder.set("intention_category", quality.getIntentionCategory().getValue());
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
	
	/**
	 * 根据质量Id获取客户质量实体
	 * @param qualityId
	 * @return
	 */
	private NCustomerTaskQuality getNCustomerTaskQuality(Integer qualityId){
		Oql oql=new Oql();{
			oql.setType(NCustomerTaskQuality.class);
			oql.setSelects("intention_category,code");
			oql.setFilter("id=?");
			oql.getParameters().add("id", qualityId, Types.INTEGER);
		}
		IPersister<NCustomerTaskQuality> pm = PersisterFactory.create();
		NCustomerTaskQuality quality = pm.queryFirst(oql);
		if(quality == null){
			throw new BusinessException("任务质量Id不存在，不能跟进！");
		}
		return quality;
	}
}
