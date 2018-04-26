package com.gongsibao.bd.service.auditLog;

import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

/**
 * 产品定价申请审核
 * @author Administrator
 *
 */
public class ProductPriceAudit extends AbstractAuditLogService{

	@Override
	protected List<AuditLog> getExtenAuditLogList(Integer formId,
			Integer addUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuditLogType setAuditLogType() {
		// TODO Auto-generated method stub
		return AuditLogType.Cpdj;
	}

	@Override
	protected String setActionPath() {
		return "";
	}

	@Override
	public List<String> getAuditPassTel() {
		return null;
	}

	@Override
	public List<String> getAuditFailTel() {
		return null;
	}

	@Override
	public List<String> getAuditWaitTel(int level) {
		return null;
	}
}
