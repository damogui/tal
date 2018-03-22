package com.gongsibao.bd.service.auditLog;

import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

public class PayAudit extends AbstractAuditLogService{

	@Override
	public List<AuditLog> getExtenAuditLogList(AuditLogType type, Integer formId, Integer addUserId) {
		
		
		return null;
	}

	/**   
	 * <p>Title: getDepartAuditLogList</p>   
	 * <p>Description:覆盖父类 </p>   
	 * @param type
	 * @param formId
	 * @param addUserId
	 * @return   
	 * @see com.gongsibao.bd.service.auditLog.AbstractAuditLogService#getDepartAuditLogList(com.gongsibao.entity.bd.dic.AuditLogType, java.lang.Integer, java.lang.Integer)   
	 */
	@Override
	protected List<AuditLog> getDepartAuditLogList(AuditLogType type, Integer formId, Integer addUserId){
		
		return null;
	}
}
