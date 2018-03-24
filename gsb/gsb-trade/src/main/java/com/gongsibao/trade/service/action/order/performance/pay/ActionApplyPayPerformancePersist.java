package com.gongsibao.trade.service.action.order.performance.pay;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.service.NDepPayService;
import com.gongsibao.trade.service.OrderService;

/**
 * @ClassName: ActionApplyPayPersist
 * @Description:TODO 持久化
 * @author: 韩伟
 * @date: 2018年3月22日 下午5:50:26
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionApplyPayPerformancePersist implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(ActionContext ctx) {

		List<NDepPay> depPayList = (List<NDepPay>) ctx.getItem();
		IPersistableService<NDepPay> service = (IPersistableService<NDepPay>) ReflectManager.newInstance(NDepPayService.class.getSuperclass());
		depPayList = service.saves(depPayList);
	}
}
