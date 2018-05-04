package com.gongsibao.trade.service.action.order.settle;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.entity.trade.settle.OrderProdSettle;
import com.gongsibao.entity.trade.settle.Settle;
import com.gongsibao.trade.base.settle.IOrderProdSettleService;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import java.util.List;

/**
 * 结算验证
 */
public class ActionApplySettleVerify implements IAction {

    IOrderProdSettleService orderProdSettleService = ServiceFactory.create(IOrderProdSettleService.class);

    @Override
    public void execute(ActionContext ctx) {
        List<Integer> orderProdCaseIds = (List<Integer>) ctx.getStatus().get("orderProdCaseIds");
        if (null == orderProdCaseIds || orderProdCaseIds.isEmpty()) {
            throw new BusinessException("明细订单不存在");
        }

        // 查询order_prod_case对象列表
        List<OrderProdSettle> orderProdCaseList = orderProdSettleService.byIds((List<Integer>) ctx.getStatus().get("orderProdCaseIds"));
        if (null == orderProdCaseList || orderProdCaseList.isEmpty()) {
            throw new BusinessException("明细订单不存在");
        }

        // 检查各项产品是否处于待结算状态
        Supplier supplier = SupplierSessionManager.getSupplier();
        if (null == supplier) {
            throw new BusinessException("请重新登录");
        }

        Integer supplierId = supplier.getId();

        if (null == supplierId || supplierId == 0) {
            throw new BusinessException("请重新登录");
        }

        for (OrderProdSettle orderProdCase : orderProdCaseList) {
            SoOrder soOrder = orderProdCase.getSoOrder();
            // 验证是否已付款
            if (soOrder.getPayablePrice() > 0 && soOrder.getPaidPrice().compareTo(soOrder.getPayablePrice()) < 0) {
                throw new BusinessException("订单[" + orderProdCase.getSoOrder().getNo() + "]未支付完成");
            }

            // 验证结算状态
            OrderProd orderProd = orderProdCase.getOrderProd();
            if (orderProd.getSettleStatus().getValue() != SettleStatus.NO_SETTLEMENT.getValue()) {
                throw new BusinessException("明细订单[" + orderProdCase.getOrderProdId() + "]目前未[" + orderProd.getSettleStatus().getText() + "]状态");
            }

            // 验证订单归属服务商
            if (supplierId.compareTo(orderProdCase.getSupplierId()) != 0) {
                throw new BusinessException("明细订单[" + orderProdCase.getOrderProdId() + "]不属于您");
            }
        }
        ctx.getStatus().put("orderProdCaseList", orderProdCaseList);
    }
}
