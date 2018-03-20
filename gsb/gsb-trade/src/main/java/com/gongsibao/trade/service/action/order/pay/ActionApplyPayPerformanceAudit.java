package com.gongsibao.trade.service.action.order.pay;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PayWayType;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IPayService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

/*创建回款业绩审核*/
public class ActionApplyPayPerformanceAudit  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		// TODO Auto-generated method stub
        Pay entity = (Pay) ctx.getItem ();//
        IAuditLogService auditLogService = ServiceFactory.create (IAuditLogService.class);//审核
        List<AuditLog>auditLogs=new ArrayList<> ();
        for (OrderPayMap item :entity.getOrderPayMaps ()
             ) {

            AuditLog   auditLog=new AuditLog ();

            auditLog.setType (AuditLogType.Sksq);
            auditLog.setFormId (item.getOrderId ());
            auditLog.setStatus (AuditLogStatusType.TOAUDIT);
            auditLog.setContent ("订单回款审核");
            auditLog.setLevel (1);//审核评级
            auditLogs.add (auditLog);
        }
        auditLogService.saves (auditLogs);

		
	}

}
