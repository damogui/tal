package com.gongsibao.trade.service.action.order.pay;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.base.IPayService;
import com.gongsibao.trade.web.dto.OrderPayDTO;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

/**
 * ClassName: ActionOnlinePayVerify
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/20 14:40
 */
public class ActionOnlinePayVerify implements IAction {

    IOrderService orderService = ServiceFactory.create(IOrderService.class);
    IPayService payService = ServiceFactory.create(IPayService.class);

    @Override
    public void execute(ActionContext ctx) {
        OrderPayDTO dto = (OrderPayDTO) ctx.getItem();
        if (dto.getOrderId() <= 0) {
            throw new BusinessException("订单编号[" + dto.getOrderId() + "]错误");
        }
        if (dto.getPayId() <= 0) {
            throw new BusinessException("支付编号[" + dto.getPayId() + "]错误");
        }
        if (dto.getTotalFee() <= 0) {
            throw new BusinessException("支付价格[ " + dto.getTotalFee() + " ]错误");
        }

        SoOrder order = orderService.getByOrderId(dto.getOrderId());

        if (null == order) {
            throw new BusinessException("订单编号[" + dto.getOrderId() + "]不存在");
        }

        if (order.getPaidPrice() >= order.getPayablePrice()) {
            throw new BusinessException("订单编号[" + dto.getOrderId() + "]已支付");
        }

        Pay pay = payService.byId(dto.getPayId());
        if (null == pay) {
            throw new BusinessException("支付编号[" + dto.getPayId() + "]不存在");
        }

        if (pay.getSuccessStatus().getValue() == 3123 || pay.getSuccessStatus().getValue() == 3124) {
            throw new BusinessException("支付状态[" + pay.getSuccessStatus().getValue() + "]错误");
        }

        ctx.getStatus().put("order", order);
        ctx.getStatus().put("pay", pay);

//
//        SoOrder soOrder = findById(orderId);
//        SoPay pay = soPayService.findById(payId);
//        if (null == soOrder || null == pay || pay.getSuccessStatusId() == 3123 || pay.getSuccessStatusId() == 3124
//                || soOrder.getPaidPrice() >= soOrder.getPayablePrice()) {
//            return;
//        }

    }
}
