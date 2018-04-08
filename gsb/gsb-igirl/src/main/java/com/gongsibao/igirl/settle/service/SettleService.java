package com.gongsibao.igirl.settle.service;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.dict.ResponseStatus;
import com.gongsibao.entity.igirl.settle.OrderProdCase;
import com.gongsibao.entity.igirl.settle.Settle;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.igirl.settle.base.IOrderProdCaseService;
import com.gongsibao.igirl.settle.base.ISettleService;
import com.gongsibao.utils.AmountUtils;
import com.gongsibao.utils.DateUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.service.PersistableService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SettleService extends PersistableService<Settle> implements ISettleService {

    private IOrderProdCaseService orderProdCaseService = ServiceFactory.create(IOrderProdCaseService.class);

    @Override
    public Result<Settle> saveSettle(List<Integer> orderProdCaseIds) {

        if (null == orderProdCaseIds || orderProdCaseIds.isEmpty()) {
            return new Result<>(ResponseStatus.FAILED, "明细订单不存在");
        }

        // 查询order_prod_case对象列表
        List<OrderProdCase> orderProdCaseList = orderProdCaseService.byIds(orderProdCaseIds);
        if (null == orderProdCaseList || orderProdCaseList.isEmpty()) {
            return new Result<>(ResponseStatus.FAILED, "明细订单不存在");
        }

        // 检查各项产品是否处于待结算状态
        List<Integer> orderProdIds = new ArrayList<>();

        // 总金额
        double totalAmount = 0d;
        // 税额
        double tax = 0d;
        // 佣金
        double commission = 0d;
        // 总成本
        double totalCost = 0d;
        // 总服务费
        double totalCharge = 0d;

        for (OrderProdCase orderProdCase : orderProdCaseList) {
            OrderProd orderProd = orderProdCase.getOrderProd();
            if (null == orderProd) {
                return new Result<>(ResponseStatus.FAILED, "明细订单[" + orderProdCase.getOrderProdId() + "]不存在");
            }

            if (orderProd.getSettleStatus().getValue() != SettleStatus.NO_SETTLEMENT.getValue()) {
                return new Result<>(ResponseStatus.FAILED, "明细订单[" + orderProdCase.getOrderProdId() + "]目前未[" + orderProd.getSettleStatus().getText() + "]状态");
            }
            orderProdIds.add(orderProdCase.getOrderProdId());

            totalCost = AmountUtils.add(totalCost, orderProdCase.getCost().doubleValue());
            totalCharge = AmountUtils.add(totalCharge, orderProdCase.getCharge().doubleValue());
        }

        totalAmount = totalCost + totalCharge;
        commission = AmountUtils.mul((1 - 0.0649), totalCharge);
        tax = totalCharge - commission;

        String date = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
        // 构建结算实体
        Settle settle = new Settle();
        settle.setTotalAmount(new BigDecimal(totalAmount));
        settle.setTax(new BigDecimal(tax));
        settle.setCommission(new BigDecimal(commission));
        settle.setTotalCost(new BigDecimal(totalCost));
        // 保存

        // 更新产品订单，结算状态改为结算中

        return new Result<>();
    }
}
