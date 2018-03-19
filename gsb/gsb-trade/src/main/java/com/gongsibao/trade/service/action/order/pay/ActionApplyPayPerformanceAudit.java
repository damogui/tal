package com.gongsibao.trade.service.action.order.pay;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PayWayType;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IPayService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

/*创建回款业绩审核*/
public class ActionApplyPayPerformanceAudit  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		// TODO Auto-generated method stub
        IAuditLogService auditLogService = ServiceFactory.create (IAuditLogService.class);//审核
        AuditLog   auditLog=new AuditLog ();
        auditLog.setType (AuditLogType.Sksq);




//        IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);//订单支付中间表
//
//        Pay entity = (Pay) ctx.getItem ();//
//
//        auditLogService.save ()
//        if (entity.getPayWayType ().equals (PayWayType.ONLINE_PAYMENT)) {//在线支付的话
//            int numLine = handleOnlinePay (entity);
//
//
//        } else {
//            //非在线支付
//            int numLine = handleOfflinePay (entity);
//
//
//        }

		
	}

}
