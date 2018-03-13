package com.gongsibao.trade.service.action.order.stage;

import java.math.BigDecimal;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INOrderStageService;
import com.gongsibao.u8.base.ISoOrderService;

/**   
 * @ClassName:  ActionApplyStageVerify   
 * @Description:TODO 申请分期校验
 * @author: 韩伟
 * @date:   2018年3月12日 下午4:53:02   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionApplyStageVerify implements IAction{

	private Integer getStageAllBigDecimal = 0;

	@Override
	public void execute(ActionContext ctx) {
		SoOrder order = (SoOrder) ctx.getItem();
		for (NOrderStage item : order.getStages()) {
			getStageAllBigDecimal += item.getAmount().intValue();
		}
		//根据订单Id获取订单实体
		ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
		order = orderService.getByOrderId(order.getId());
		Integer instaAmount = order.getPayablePrice().intValue() - order.getPaidPrice().intValue();
		if(!instaAmount.equals(getStageAllBigDecimal)){
			throw new BusinessException("分期金额和应付的分期金额不匹配！");
		}
	}

}
