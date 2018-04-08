package com.gongsibao.igirl.settle.service;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.dict.ResponseStatus;
import com.gongsibao.entity.igirl.settle.OrderProdCase;
import com.gongsibao.entity.igirl.settle.Settle;
import com.gongsibao.entity.igirl.settle.SettleHandleLog;
import com.gongsibao.entity.igirl.settle.SettleOrder;
import com.gongsibao.entity.igirl.settle.dict.SettleHandleStatus;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.igirl.settle.base.IOrderProdCaseService;
import com.gongsibao.igirl.settle.base.ISettleService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.utils.AmountUtils;
import com.gongsibao.utils.DateUtils;
import com.gongsibao.utils.SupplierSessionManager;
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

    private IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

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

        Integer supplierId = SupplierSessionManager.getSupplierId();
        if (null == supplierId || supplierId == 0) {
            return new Result<>(ResponseStatus.FAILED, "请重新登录");
        }

        for (OrderProdCase orderProdCase : orderProdCaseList) {
            SoOrder soOrder = orderProdCase.getSoOrder();
            // 验证是否已付款
            if (soOrder.getPayablePrice() > 0 && soOrder.getPaidPrice().compareTo(soOrder.getPayablePrice()) < 0) {
                return new Result<>(ResponseStatus.FAILED, "订单[" + orderProdCase.getSoOrder().getNo() + "]未支付完成");
            }

            // 验证结算状态
            OrderProd orderProd = orderProdCase.getOrderProd();
            if (orderProd.getSettleStatus().getValue() != SettleStatus.NO_SETTLEMENT.getValue()) {
                return new Result<>(ResponseStatus.FAILED, "明细订单[" + orderProdCase.getOrderProdId() + "]目前未[" + orderProd.getSettleStatus().getText() + "]状态");
            }

            // 验证订单归属服务商
            if (supplierId.compareTo(orderProdCase.getSupplierId()) != 0) {
                return new Result<>(ResponseStatus.FAILED, "明细订单[" + orderProdCase.getOrderProdId() + "]不属于您");
            }

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
            settle.setHandleStatus(SettleHandleStatus.PLATFORM_AUDITING);
        }

        // 构建明细订单关联id实体
        List<SettleOrder> settleOrderList = new ArrayList<>();
        for (OrderProdCase orderProdCase : orderProdCaseList) {
            SettleOrder settleOrder = new SettleOrder();
            {
                settleOrder.toNew();
                settleOrder.setOrderId(orderProdCase.getOrderId());
                settleOrder.setOrderProdId(orderProdCase.getOrderProdId());
                settleOrder.setCreatorId(supplierId);
                settleOrder.setCreateTime(today);
                settleOrder.setUpdateTime(today);
            }

            settleOrderList.add(settleOrder);
        }
        settle.setSettleOrderList(settleOrderList);

        // 构建日志
        SettleHandleLog log = new SettleHandleLog();
        {
            log.toNew();
            log.setPreviousStatus(SettleHandleStatus.WU);
            log.setAfterStatus(SettleHandleStatus.FINANCIAL_AUDITING);
            log.setMemo("提交结算申请");
            log.setCreatorId(supplierId);
            log.setCreator(SupplierSessionManager.getSupplier().getName());
            log.setCreateTime(today);
            log.setUpdateTime(today);
        }

        settle.getHandleLogList().add(log);
        // 保存
        settle = save(settle);

        // 更新产品订单，结算状态改为结算中
        orderProdService.updateSettleStatus(orderProdIds, SettleStatus.DO_SETTLEMENT);
        Result<Settle> result = new Result<>();
        result.setObj(settle);
        return result;
    }
}
