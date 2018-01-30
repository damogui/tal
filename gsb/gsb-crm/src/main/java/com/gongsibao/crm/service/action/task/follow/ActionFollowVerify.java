package com.gongsibao.crm.service.action.task.follow;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.entity.crm.dic.QualityCategory;

/**
 * @author hw 跟进校验
 */
public class ActionFollowVerify implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		// 这里要根据选择的不现客户质量，作不同的有效校验
		// 具体规则参考需求文档
		
		NCustomerTaskFoolow foolow = (NCustomerTaskFoolow) ctx.getItem();
		NCustomerTaskQuality quality = getNCustomerTaskQuality(foolow.getQualityId());
		QualityCategory category = quality.getIntentionCategory();
		
		if (category == QualityCategory.A) {
			
			Boolean isHas = hasProduct(foolow.getTaskId());
			if(!isHas){
				
				throw new BusinessException("请先添加意向产品");
			}
		}
		
		foolow.setQualityCategory(category);
		
		// 补齐任务对应的客户Id
		Integer customerId = this.getCustomerId(foolow.getTaskId());
		if(customerId == null){
			
			throw new BusinessException("任务不存在或已删除，不能跟进！");
		}
		foolow.setCustomerId(customerId);

		ctx.setItem(foolow);
	}
	

	private Integer getCustomerId(Integer taskId) {
		
		Oql oql = new Oql();
		{
			oql.setType(NCustomerTask.class);
			oql.setSelects("id,customerId");
			oql.setFilter("id=?");
			oql.getParameters().add("id", taskId, Types.INTEGER);
		}
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		NCustomerTask task = pm.queryFirst(oql);
		if (task == null) {

			return null;
		}
		return task.getCustomerId();
	}

	/**
	 * 根据质量Id获取客户质量实体
	 * 
	 * @param qualityId
	 * @return
	 */
	private NCustomerTaskQuality getNCustomerTaskQuality(Integer qualityId) {

		Oql oql = new Oql();
		{
			oql.setType(NCustomerTaskQuality.class);
			oql.setSelects("id,intention_category,code");
			oql.setFilter("id=?");
			oql.getParameters().add("id", qualityId, Types.INTEGER);
		}
		
		IPersister<NCustomerTaskQuality> pm = PersisterFactory.create();
		NCustomerTaskQuality quality = pm.queryFirst(oql);
		return quality;
	}

	/**   
	 * @Title: hasProduct   
	 * @Description: TODO(判断任务是否有意向产品)   
	 * @param: @param taskId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	private Boolean hasProduct(Integer taskId) {

		Oql oql = new Oql();
		{
			oql.setType(NCustomerProduct.class);
			oql.setFilter("task_id=?");
			oql.getParameters().add("task_id", taskId, Types.INTEGER);
		}
		IPersister<NCustomerProduct> pm = PersisterFactory.create();
		return pm.queryCount(oql) > 0;
	}
}
