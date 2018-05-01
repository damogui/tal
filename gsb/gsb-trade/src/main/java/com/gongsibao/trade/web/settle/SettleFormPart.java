package com.gongsibao.trade.web.settle;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.settle.Settle;
import com.gongsibao.trade.base.settle.ISettleService;

public class SettleFormPart extends FormPart {
	ISettleService settleService = ServiceFactory.create(ISettleService.class);

	IAuditLogService auditService = ServiceFactory.create(IAuditLogService.class);

	public Settle settleDetail(Integer id) {
		if (null == id || id == 0) {
			return null;
		}

		return settleService.byId(id);
	}

	public List<AuditLog> getLogs(Integer id) {
		if (null == id || id == 0) {
			return null;
		}

		return auditService.queryByFormId(id, AuditLogType.Jssh);
	}

}
