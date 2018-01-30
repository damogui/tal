package com.gongsibao.crm.service.action.task.follow;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

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
		NCustomerTaskFoolow foolow = (NCustomerTaskFoolow) ctx.getItem();
		NCustomerTaskQuality quality = getNCustomerTaskQuality(foolow.getQualityId());
		if (quality.getCode().contains("A")) {
			getNCustomerProduct(foolow.getTaskId());
		}
		foolow.setQualityCategory(QualityCategory.getItem(quality.getIntentionCategory().getValue()));
		ctx.setItem(foolow);

		Map<String, Object> setMap = new HashMap<>();
		setMap.put("code", quality.getCode());
		ctx.setStatus(setMap);
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
			oql.setSelects("intention_category,code");
			oql.setFilter("id=?");
			oql.getParameters().add("id", qualityId, Types.INTEGER);
		}
		IPersister<NCustomerTaskQuality> pm = PersisterFactory.create();
		NCustomerTaskQuality quality = pm.queryFirst(oql);
		if (quality == null) {
			throw new BusinessException("任务质量Id不存在，不能跟进！");
		}
		return quality;
	}

	/**
	 * 当客户质量为A类时，必须有意向产品否则抛异
	 * 
	 * @param taskId
	 */
	private void getNCustomerProduct(Integer taskId) {
		
		Oql oql = new Oql();
		{
			oql.setType(NCustomerProduct.class);
			oql.setSelects("id");
			oql.setFilter("task_id=?");
			oql.getParameters().add("task_id", taskId, Types.INTEGER);
		}
		IPersister<NCustomerProduct> pm = PersisterFactory.create();
		NCustomerProduct product = pm.queryFirst(oql);
		if (product == null) {
			throw new BusinessException("请先添加意向产品");
		}
	}
}
