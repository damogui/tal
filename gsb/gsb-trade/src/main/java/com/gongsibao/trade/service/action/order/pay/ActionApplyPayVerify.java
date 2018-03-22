package com.gongsibao.trade.service.action.order.pay;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.PayWayType;
import com.gongsibao.trade.base.IOrderService;

/*验证回款业绩的操作*/
public class ActionApplyPayVerify implements IAction {

    @Override
    public void execute(ActionContext ctx) {
    	
        // TODO Auto-generated method stub
        IPersister<AuditLog> auditLogService = PersisterFactory.create ();

        Pay pay = (Pay) ctx.getItem ();//进行校验金额
        List<OrderPayMap> orderPayMaps = pay.getOrderPayMaps ();
        //校验订单是否已经处于审核状态是的话不能进行创建回款审核

        StringBuilder sb = new StringBuilder ();
        sb.append ("(");
        for (OrderPayMap item : orderPayMaps
                ) {
            if (item.equals (orderPayMaps.get (orderPayMaps.size () - 1))) {//最后一个不加,
                sb.append (item.getOrderId ());
            } else {
                sb.append (item.getOrderId ());
                sb.append (",");

            }


        }

        sb.append (")");

        String sql = String.format ("SELECT  IFNULL(MAX(form_id),0) FROM  bd_audit_log  WHERE  type_id=1045  AND     form_id  IN %s", sb.toString ());//查询是否存在订单审核状态
        QueryParameters qps = new QueryParameters ();
        Integer execNum = auditLogService.executeInt (sql, qps);
        if (execNum > 0) {

            throw new BusinessException (String.format ("订单号:%s正处于回款审核状态",execNum));

        }

        if (orderPayMaps.size () == 0) {

            throw new BusinessException ("回款业绩必须分配！");

        }

        if (pay.getFiles ().size () == 0 && pay.getPayWayType ().equals (PayWayType.OFFLINE_PAYMENT)) {//线上支付不需要凭证

            throw new BusinessException ("凭证必须上传");

        }

        //根据订单Id获取订单实体
        IOrderService orderService = ServiceFactory.create (IOrderService.class);
        SoOrder order = orderService.getByOrderId (pay.getOrderPayMaps ().get (0).getOrderId ());

        if (order.getTotalPrice () < pay.getAmount ()) {


            throw new BusinessException ("回款业绩不能大于支付金额");
        }


    }

}
