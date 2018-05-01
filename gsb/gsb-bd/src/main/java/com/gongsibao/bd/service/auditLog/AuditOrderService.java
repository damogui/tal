package com.gongsibao.bd.service.auditLog;

import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;


/**
 * 订单改价申请审核
 * @author Administrator
 *
 */
public class AuditOrderService extends AbstractAuditService{
	
	@Override
	protected List<AuditLog> getExtenAuditLogList(Integer formId,
			Integer addUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuditLogType setAuditLogType() {
		// TODO Auto-generated method stub
		return AuditLogType.Ddgj;
	}

	@Override
	protected String setActionPath() {
		
		return "gsb/crm/audit/changeOrderPrice";
	}


}
