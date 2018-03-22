package com.gongsibao.trade.service.action.order.pay;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;

/*创建回款业绩审核*/
public class ActionApplyPayAudit  implements IAction{

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
