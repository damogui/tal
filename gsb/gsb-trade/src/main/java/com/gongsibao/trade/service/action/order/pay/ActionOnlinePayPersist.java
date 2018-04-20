package com.gongsibao.trade.service.action.order.pay;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderPayStatusType;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.base.IPayService;
import com.gongsibao.trade.web.dto.OrderPayDTO;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.Arrays;
import java.util.Date;

/**
 * ClassName: ActionOnlinePayVerify
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 订单支付状态修改
 * @date 2018/4/20 14:40
 */
public class ActionOnlinePayPersist implements IAction {

    IOrderService orderService = ServiceFactory.create(IOrderService.class);
    IPayService payService = ServiceFactory.create(IPayService.class);

    @Override
    public void execute(ActionContext ctx) {
        OrderPayDTO dto = (OrderPayDTO) ctx.getItem();

        Integer orderId = dto.getOrderId();
        Integer payId = dto.getPayId();

        // 当前时间
        Date today = new Date();

        if (dto.isSuccess()) {
            // 累加已付金额
            orderService.addPaidPrice(orderId, dto.getTotalFee());

            SoOrder order = orderService.getByOrderId(orderId);
            int payStatus = 3013;
            if (order.getPaidPrice() < order.getPayablePrice()) {
                payStatus = 3012;
            } else {
                // 分次支付的订单应该付完款之后才能显示正在办理
                /** 302 订单处理状态：3021 待办理、3022 正在办理、3023 已取消、3024 已完成 */
                orderService.updateProcessStatusId(orderId, 3022);
                order.setPayStatus(OrderPayStatusType.Yfk);//将订单的状态更新成【已付款】
            }

            // 更新订单支付状态
            orderService.updatePayStatus(orderId, payStatus);

            // 支付完成
            if (payStatus == 3013 && !order.getIsInstallment()) {
                orderService.updatePayTime(orderId, today);
            }

            ctx.getStatus().put("order", order);
        }

        // 处理支付状态 /** 312 支付成功状态：3121 未支付、3122 待审核、3123 成功、3124 失败 */
        payService.updatePayStatus(payId, dto.isSuccess() ? 3123 : 3124, 3121, today, dto.getOnlineTradeNo());
        //该订单成功的付款记录信息
        int successCount = payService.countByOrderIds(Arrays.asList(orderId));
        //当付的款是第一笔款项时，更新订单的首款审核日期
        if (successCount == 0) {
            orderService.updateFistPayTime(orderId, today);
        }
    }
}
