package com.gongsibao.trade.service.action.order.settle;

import com.gongsibao.entity.trade.settle.OrderProdSettle;
import com.gongsibao.entity.trade.settle.Settle;
import com.gongsibao.entity.trade.settle.SettleOrder;
import com.gongsibao.entity.trade.settle.dict.SettleHandleStatus;
import com.gongsibao.trade.base.settle.ISettleService;
import com.gongsibao.utils.AmountUtils;
import com.gongsibao.utils.DateUtils;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 结算保存
 */
public class ActionApplySettleSave implements IAction {

    ISettleService settleService = ServiceFactory.create(ISettleService.class);

    @Override
    public void execute(ActionContext ctx) {

        // 总金额
        double totalAmount = 0d;
        // 税额
        double tax = 0d;
        // 佣金
        double commission = 0d;

        double totalCost = 0d;
        // 总服务费
        double totalCharge = 0d;

        Integer supplierId = SupplierSessionManager.getSupplierId();
        Integer userId = SessionManager.getUserId();
        String userName = SessionManager.getUserName();

        List<Integer> orderProdIds = new ArrayList<>();
        // 查询order_prod_case对象列表
        List<OrderProdSettle> orderProdCaseList = (List<OrderProdSettle>) ctx.getStatus().get("orderProdCaseList");
        for (OrderProdSettle orderProdCase : orderProdCaseList) {
            orderProdIds.add(orderProdCase.getOrderProdId());
            totalCost = AmountUtils.add(totalCost, orderProdCase.getCost().doubleValue());
            totalCharge = AmountUtils.add(totalCharge, orderProdCase.getCharge().doubleValue());
        }

        // 税率
        double taxRate = AmountUtils.div(Settle.TAX_RATE.doubleValue(), 100, 4);

        // 总金额
        totalAmount = AmountUtils.add(totalCost, totalCharge);

        // 佣金
        commission = AmountUtils.div(AmountUtils.mul((1 - taxRate), totalCharge), 1, 2);

        // 税额
        tax = AmountUtils.sub(totalCharge, commission);

        Date today = new Date();
        String dateStr = DateUtils.getDateStr(today);

        // 构建结算实体
        Settle settle = new Settle();
        {
            settle.toNew();
            settle.setTotalAmount(BigDecimal.valueOf(totalAmount));
            settle.setTax(BigDecimal.valueOf(tax));
            settle.setCommission(BigDecimal.valueOf(commission));
            settle.setTotalCost(BigDecimal.valueOf(totalCost));
            settle.setTotalCharge(BigDecimal.valueOf(totalCharge));
            settle.setTax(BigDecimal.valueOf(tax));
            settle.setTaxRate(Settle.TAX_RATE);

            settle.setSupplierId(supplierId);
            settle.setMemo("结算单" + dateStr);
            settle.setCreateTime(today);
            settle.setUpdateTime(today);
            settle.setCreator(userName);
            settle.setCreatorId(userId);
            settle.setHandleStatus(SettleHandleStatus.PLATFORM_AUDITING);
        }

        // 构建明细订单关联id实体
        List<SettleOrder> settleOrderList = new ArrayList<>();
        double tmpCommission = 0d;
        for (int i = 0; i < orderProdCaseList.size(); i++) {
            OrderProdSettle orderProdCase = orderProdCaseList.get(i);
            double orderProdCommission = 0d;
            if (i == orderProdCaseList.size() - 1) {
                // 前面损失的精度，在最后一个产品处补全
                orderProdCommission = AmountUtils.sub(commission, tmpCommission);
            } else {
                orderProdCommission = AmountUtils.div(AmountUtils.mul((1 - taxRate), orderProdCase.getCharge().doubleValue()), 1, 2);
            }

            SettleOrder settleOrder = new SettleOrder();
            {
                settleOrder.toNew();
                settleOrder.setOrderId(orderProdCase.getOrderId());
                settleOrder.setOrderProdId(orderProdCase.getOrderProdId());
                settleOrder.setCost(orderProdCase.getCost());
                settleOrder.setCharge(orderProdCase.getCharge());
                settleOrder.setCommission(BigDecimal.valueOf(orderProdCommission));

                settleOrder.setCreateTime(today);
                settleOrder.setUpdateTime(today);
                settleOrder.setCreator(userName);
                settleOrder.setCreatorId(userId);
            }

            settleOrderList.add(settleOrder);
        }
        settle.setSettleOrderList(settleOrderList);

        // 保存
        settle = settleService.save(settle);
        ctx.setItem(settle);
        ctx.getStatus().put("orderProdIds", orderProdIds);
    }
}
