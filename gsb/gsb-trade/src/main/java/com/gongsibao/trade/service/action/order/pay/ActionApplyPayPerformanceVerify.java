package com.gongsibao.trade.service.action.order.pay;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.PayWayType;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.entity.trade.dto.OrderRelationDTO;
import com.gongsibao.trade.base.IOrderService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import java.util.List;

/*验证回款业绩的操作*/
public class ActionApplyPayPerformanceVerify implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        // TODO Auto-generated method stub
        Pay pay = (Pay) ctx.getItem ();//进行校验金额
        List<OrderPayMap> orderPayMaps = pay.getOrderPayMaps ();
        if (orderPayMaps.size () == 0) {

            throw new BusinessException ("回款业绩必须分配！");

        }

        if (pay.getFiles ().size () == 0 &&pay.getPayWayType ().equals (PayWayType.OFFLINE_PAYMENT)) {//线上支付不需要凭证

            throw new BusinessException ("凭证必须上传");

        }

        //根据订单Id获取订单实体
        IOrderService orderService = ServiceFactory.create (IOrderService.class);
        SoOrder order = orderService.getByOrderNo (pay.getOrderPayMaps ().get (0).getOrderId ().toString ());

        if (order.getTotalPrice () < pay.getAmount ()) {


            throw new BusinessException ("回款业绩不能大于支付金额");
        }


    }

}
