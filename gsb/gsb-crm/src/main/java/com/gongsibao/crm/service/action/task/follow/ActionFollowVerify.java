package com.gongsibao.crm.service.action.task.follow;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.crm.NCustomerProduct;
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
		
		NCustomerTaskFoolow taskFoolow = (NCustomerTaskFoolow) ctx.getItem();
		NCustomerTaskQuality quality = getNCustomerTaskQuality(taskFoolow.getQualityId());
		QualityCategory category = quality != null ? quality.getIntentionCategory() : null;
		if (quality.getProductRequired()) {
			Boolean isHas = hasProduct(taskFoolow.getTaskId());
			if(!isHas){
				throw new BusinessException("此类质量的商机必须添加意向产品");
			}
		}
		
		//hasDistrict
		taskFoolow.setQualityCategory(category);
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
			oql.setSelects("id,intention_category,code,product_required");
			oql.setFilter("id=?");
			oql.getParameters().add("id", qualityId, Types.INTEGER);
		}
		
		IPersister<NCustomerTaskQuality> pm = PersisterFactory.create();
		NCustomerTaskQuality quality = pm.queryFirst(oql);
		return quality;
	}

	/**   
	 * @Title: hasProduct   
	 * @Description: TODO(判断商机是否有意向产品)   
	 * @param: @param taskId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	public Boolean hasProduct(Integer taskId) {

		Oql oql = new Oql();
		{
			oql.setType(NCustomerProduct.class);
			oql.setFilter("task_id=?");
			oql.getParameters().add("task_id", taskId, Types.INTEGER);
		}
		IPersister<NCustomerProduct> pm = PersisterFactory.create();
		return pm.queryCount(oql) > 0;
	}
	
	/**   
	 * @Title: hasDistrict   
	 * @Description: TODO(判断商机的意向产品是否有意向地区)   
	 * @param: @param taskId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	public Boolean hasDistrict(Integer taskId) {

		Oql oql = new Oql();
		{
			oql.setType(NCustomerProduct.class);
			oql.setFilter("task_id=? and (d_province_id is not null or d_city_id is not null or d_county_id is not null)");
			oql.getParameters().add("task_id", taskId, Types.INTEGER);
		}
		IPersister<NCustomerProduct> pm = PersisterFactory.create();
		return pm.queryCount(oql) > 0;
	}
}
