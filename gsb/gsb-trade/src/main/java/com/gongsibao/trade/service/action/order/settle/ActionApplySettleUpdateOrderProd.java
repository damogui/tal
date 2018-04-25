package com.gongsibao.trade.service.action.order.settle;

import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.trade.base.IOrderProdService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.List;

/**
 * Created by zhangchao on 2018/3/21.
 */
public class ActionApplySettleUpdateOrderProd implements IAction {
    IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

    @Override
    public void execute(ActionContext ctx) {
        List<Integer> orderProdIds = (List<Integer>) ctx.getStatus().get("orderProdIds");
        orderProdService.updateSettleStatus(orderProdIds, SettleStatus.DO_SETTLEMENT);
    }
}
